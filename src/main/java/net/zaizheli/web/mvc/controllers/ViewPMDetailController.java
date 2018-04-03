package net.zaizheli.web.mvc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.zaizheli.domains.Message;
import net.zaizheli.domains.User;
import net.zaizheli.repositories.MessageRepository;
import net.zaizheli.web.utils.SessionUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/message/pm")
public class ViewPMDetailController {
	@Autowired
	private SessionUtil sessionUtil;
	@Autowired
	MessageRepository messageRepository;
	
	@RequestMapping(value="/{id}/detail", method=RequestMethod.GET)
	public String inform(@PathVariable String id, Model model, 
			HttpServletRequest request, HttpSession session){
		User user = sessionUtil.getSignInUser(session);
		model.addAttribute("user",user);
		Message message = messageRepository.findOne(id);
		if( message.getStatus() == 0 && user.getId() != message.getFrom().getId()) {
			message.setStatus(1);
			messageRepository.save(message);
		}
		model.addAttribute("message",message);
		return "message/pm.detail";
	}

}
