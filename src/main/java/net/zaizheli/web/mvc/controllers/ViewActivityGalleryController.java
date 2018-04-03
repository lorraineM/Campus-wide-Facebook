package net.zaizheli.web.mvc.controllers;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import net.zaizheli.domains.Activity;
import net.zaizheli.domains.Join;
import net.zaizheli.domains.Resource;
import net.zaizheli.domains.User;
import net.zaizheli.repositories.ActivityRepository;
import net.zaizheli.repositories.JoinRepository;
import net.zaizheli.repositories.ResourceRepository;
import net.zaizheli.web.utils.SessionUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/activity")
public class ViewActivityGalleryController {
	
	@Autowired
	ActivityRepository activityRepository;
	@Autowired 
	SessionUtil sessionUtil;
	@Autowired
	ResourceRepository resourceRepository;
	@Autowired
	JoinRepository joinRepository;
	
	@RequestMapping(value="/{id}/gallery", method=RequestMethod.GET)
	public String view(@PathVariable String id, Model model, 
			HttpServletRequest request, HttpSession session){
		User user = sessionUtil.getSignInUser(session);
		if(user == null){
			return "redirect:/signin";
		}
		String uid = user.getId();
		Activity activity = activityRepository.findOne(id);
		Join join = joinRepository.findByActivityAndjoiner(id, uid);
		List<Resource> resources = resourceRepository.findByActId(id);	
		model.addAttribute("resources", resources);
		model.addAttribute("activity",activity);
		model.addAttribute("join",join);
		return "activity/gallery";
	}
	
	@RequestMapping(value="/{id}/gallery", method=RequestMethod.POST)
	public String viewPost(@PathVariable String id, Model model, 
			HttpServletRequest request, HttpSession session){
		return view(id, model, request, session);	
	}

}
