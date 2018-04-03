package net.zaizheli.web.mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import net.zaizheli.constants.ApplicationConfig;
import net.zaizheli.domains.Activity;
import net.zaizheli.repositories.ActivityRepository;
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
public class HotActivitiesViewController {
	
	@Autowired
	private ActivityRepository activityRepository;
	@Autowired
	private SessionUtil sessionUtil;
	
	@RequestMapping(value = "/hot", method = RequestMethod.GET)	
	private String toMap( Model model, 
			HttpServletRequest request) {
		Pageable pageable = new PageRequest(0, 
				ApplicationConfig.hotActivitySize, 
				new Sort(new Order(Direction.DESC, "hot")));
		Page<Activity> activities = activityRepository.findAll(pageable);
		model.addAttribute("activities", activities.getContent());
		return "hot";
	}
}
