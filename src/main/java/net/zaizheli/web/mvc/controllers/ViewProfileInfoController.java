package net.zaizheli.web.mvc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.zaizheli.domains.User;
import net.zaizheli.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/profiles")
public class ViewProfileInfoController {
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value="/{id}/private", method=RequestMethod.GET)
	public String viewPrivate(@PathVariable String id, Model model, 
			HttpServletRequest request, HttpSession session){
		
		User user = userRepository.findOne(id);
		model.addAttribute("user", user);
		return "profiles/private";
	}
	
	@RequestMapping(value="/{id}/public", method=RequestMethod.GET)
	public String viewPublic(@PathVariable String id, Model model, 
			HttpServletRequest request, HttpSession session){
		User user = userRepository.findOne(id);
		model.addAttribute("user", user);
		return "profiles/public";
	}
}
