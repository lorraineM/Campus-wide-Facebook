package net.zaizheli.web.mvc.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.zaizheli.constants.AjaxResultCode;
import net.zaizheli.constants.MessageType;
import net.zaizheli.domains.Message;
import net.zaizheli.domains.User;
import net.zaizheli.repositories.MessageRepository;
import net.zaizheli.repositories.UserRepository;
import net.zaizheli.vo.AjaxResult;
import net.zaizheli.vo.PMVo;
import net.zaizheli.web.utils.AjaxUtil;
import net.zaizheli.web.utils.SessionUtil;
import net.zaizheli.web.utils.TextUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/message/pm")
public class CreatePMController {
	
	@Autowired
	SessionUtil sessionUtil;
	@Autowired
	private AjaxUtil ajaxUtil;
	@Autowired
	private TextUtil textUtil;
	@Autowired
	MessageRepository messageRepository;
	@Autowired
	UserRepository userRepository;
	
	@ModelAttribute("PMVo")
	public PMVo PMVo() {
		PMVo vo = new PMVo();
		return vo;
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public @ResponseBody AjaxResult create(@Valid PMVo vo, Model model, HttpServletRequest request, HttpSession session){	
		User signInUser=sessionUtil.getSignInUser(session);
		if (signInUser == null ) {
			return new AjaxResult(AjaxResultCode.NEED_SIGNIN);
		}
		if (signInUser.getId() == vo.getTo()) {
			return new AjaxResult(AjaxResultCode.INVALID, "不能和自己发私信哦，去找一个朋友聊聊吧~");
		}
		Message message = new Message();
		message.setFrom(signInUser);
		User user = userRepository.findOne(vo.getTo());
		message.setTo(user);
		String title = textUtil.removeHtml(vo.getTitle());
		message.setTitle(title);
		String con=vo.getContent();		
		con = textUtil.removeHtml(con);
		con = textUtil.turn(con);
		message.setContent(con);
		if (vo.getBase() != null) {
			Message mes = messageRepository.findOne(vo.getBase());
			message.setBase(mes);
		}
		message.setType(MessageType.PM);
		message.setStatus(0);
		message.setCreatedAt(new Date());
		messageRepository.save(message);
		return new AjaxResult(AjaxResultCode.SUCCESS);
	}
}
