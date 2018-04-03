package net.zaizheli.web.mvc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import net.zaizheli.domains.User;
import net.zaizheli.repositories.UserRepository;
import net.zaizheli.web.utils.SessionUtil;

import org.apache.commons.lang.ArrayUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/profiles")
public class ViewProfileController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	SessionUtil sessionUtil;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String view(@PathVariable String id, Model model, 
			HttpServletRequest request, HttpSession session){
		User user=sessionUtil.getSignInUser(session);
		if(user==null) {
			return "redirect:/signin";
		}
		model.addAttribute("user", user);
		return view(id, "c_activity", model, request, session);
	}
	
	@RequestMapping(value="/{id}/{view}", method=RequestMethod.GET)
	public String view(@PathVariable String id, 
			@PathVariable String view, Model model, 
			HttpServletRequest request, HttpSession session){
		User signInuser=sessionUtil.getSignInUser(session);
		if(signInuser==null) {
			return "redirect:/signin";
		}
		model.addAttribute("user", signInuser);
		String[] validViews = new String[]{"c_activity","activity", "circle", "follow", "fan"};
		if(!ArrayUtils.contains(validViews, view)){
			return "redirect:/profiles/"+id;
		}
//		if(ArrayUtils.indexOf(validViews, view) > 2){
//			model.addAttribute("viewUser", true);
//		}
		User user = userRepository.findOne(id);
		model.addAttribute("view", view);
		model.addAttribute("user", user);
		return "profile";
	}
}
