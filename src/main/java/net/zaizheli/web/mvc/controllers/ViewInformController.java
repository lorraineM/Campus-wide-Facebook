package net.zaizheli.web.mvc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.zaizheli.constants.AjaxResultCode;
import net.zaizheli.constants.ApplicationConfig;
import net.zaizheli.constants.MessageType;
import net.zaizheli.domains.Message;
import net.zaizheli.domains.User;
import net.zaizheli.repositories.MessageRepository;
import net.zaizheli.vo.AjaxResult;
import net.zaizheli.vo.PageVo;
import net.zaizheli.web.utils.SessionUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ViewInformController {

	@Autowired
	private SessionUtil sessionUtil;
	@Autowired
	MessageRepository messageRepository;
	
	@RequestMapping(value="/message/inform/view/{no}", method=RequestMethod.GET)
	public String inform(@PathVariable int no, Model model, 
			HttpServletRequest request, HttpSession session){
		User user = sessionUtil.getSignInUser(session);
		Pageable pageable = new PageRequest(Math.max(no-1, 0), 
				ApplicationConfig.messagePageSize, 
				new Sort(new Order(Direction.DESC, "createdAt")));
		Page<Message> messages = messageRepository.findByToAndtype(user.getId(), MessageType.INFORM.name(), pageable);
		int size = messageRepository.findByToAndtype(user.getId(), MessageType.INFORM.name()).size();
		int total = 0 ;
		if (size % ApplicationConfig.messagePageSize == 0)
	      total = (int)(size/ ApplicationConfig.messagePageSize);
		else {
			total = (int)(size/ ApplicationConfig.messagePageSize)+1;
		}
		PageVo pageVo = new PageVo();
		pageVo.setSize(ApplicationConfig.messagePageSize);
		pageVo.setNumber(no);
		pageVo.setTotalPages(total);
		pageVo.setContent(messages.getContent());
		model.addAttribute("messages", messages.getContent());
		model.addAttribute("pageVo",pageVo);
		
		return "message/inform.single";
	}
	
	@RequestMapping(value="/message/inform/{id}/set", method=RequestMethod.GET)
	public @ResponseBody AjaxResult set(@PathVariable String id, Model model, 
			HttpServletRequest request, HttpSession session){
		Message message = messageRepository.findOne(id);
	    message.setStatus(1);
	    messageRepository.save(message);	
		return new AjaxResult(AjaxResultCode.SUCCESS);
	}

}
