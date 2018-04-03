package net.zaizheli.web.mvc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.zaizheli.constants.AjaxResultCode;
import net.zaizheli.constants.MessageType;
import net.zaizheli.domains.User;
import net.zaizheli.repositories.MessageRepository;
import net.zaizheli.vo.AjaxResult;
import net.zaizheli.web.utils.SessionUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GetUnreadMessageController {

	@Autowired
	private SessionUtil sessionUtil;
	@Autowired
	MessageRepository messageRepository;
	
	@RequestMapping(value="/message/get", method=RequestMethod.GET)
	public @ResponseBody AjaxResult get(Model model, 
			HttpServletRequest request, HttpSession session){
		User user = sessionUtil.getSignInUser(session);
		if (user == null ) {
			return new AjaxResult(AjaxResultCode.NEED_SIGNIN);
		}
		int size = messageRepository.findByToAndstatus(user.getId(), 0).size();
		String s = String.valueOf(size);
		return new AjaxResult(AjaxResultCode.SUCCESS,s);
	}
	
	@RequestMapping(value="/message/inform/get", method=RequestMethod.GET)
	public @ResponseBody AjaxResult inform(Model model, 
			HttpServletRequest request, HttpSession session){
		User user = sessionUtil.getSignInUser(session);
		if (user == null ) {
			return new AjaxResult(AjaxResultCode.NEED_SIGNIN);
		}
		int size = messageRepository.findMes(user.getId(), MessageType.INFORM.name(), 0).size();
		String s = String.valueOf(size);
		return new AjaxResult(AjaxResultCode.SUCCESS,s);
	}
	
	@RequestMapping(value="/message/pm/get", method=RequestMethod.GET)
	public @ResponseBody AjaxResult pm(Model model, 
			HttpServletRequest request, HttpSession session){
		User user = sessionUtil.getSignInUser(session);
		if (user == null ) {
			return new AjaxResult(AjaxResultCode.NEED_SIGNIN);
		}
		int size = messageRepository.findMes(user.getId(), MessageType.PM.name(), 0).size();
		String s = String.valueOf(size);
		return new AjaxResult(AjaxResultCode.SUCCESS,s);
	}
	
	@RequestMapping(value="/message/at/get", method=RequestMethod.GET)
	public @ResponseBody AjaxResult at(Model model, 
			HttpServletRequest request, HttpSession session){
		User user = sessionUtil.getSignInUser(session);
		if (user == null ) {
			return new AjaxResult(AjaxResultCode.NEED_SIGNIN);
		}
		int size = messageRepository.findMes(user.getId(), MessageType.AT.name(), 0).size();
		String s = String.valueOf(size);
		return new AjaxResult(AjaxResultCode.SUCCESS,s);
	}
		
}
