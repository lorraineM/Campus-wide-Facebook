package net.zaizheli.web.mvc.controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.zaizheli.constants.ApplicationConfig;
import net.zaizheli.domains.Activity;
import net.zaizheli.domains.User;
import net.zaizheli.repositories.ActivityRepository;
import net.zaizheli.repositories.UserRepository;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/categories")
public class GetActivitiesFromCategoryController {
	
	@Autowired
	ActivityRepository activityRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	SessionUtil sessionUtil;
	
	@RequestMapping(value="/thumb", method=RequestMethod.GET)
	public String thumb(Model model, HttpServletRequest request, 
			HttpSession session){
		
		String uid = request.getParameter("uid");
		String category = request.getParameter("category");
		Pageable pageable = new PageRequest(0,
				ApplicationConfig.categoryThumbPageSize, new Sort(new Order(
						Direction.DESC, "createdAt")));
		User user = userRepository.findOne(uid);
		Page<Activity> spots = activityRepository.findByCreatedByAndtype(uid, category, pageable);
		model.addAttribute("category", category);
		model.addAttribute("user", user);
		if(spots!=null){
			model.addAttribute("total", spots.getTotalElements());
			Collection<Activity> thumbs = new ArrayList<Activity>();
			for (Activity spot : spots) {
				thumbs.add(spot);
			}
			model.addAttribute("thumbs", thumbs);
		}else{
			model.addAttribute("total", 0);
		}
		return "categories/single.thumb";
	}
}
