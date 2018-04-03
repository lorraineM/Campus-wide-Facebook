package net.zaizheli.web.mvc.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import net.zaizheli.constants.ApplicationStatus;
import net.zaizheli.domains.Activity;
import net.zaizheli.domains.Application;
import net.zaizheli.domains.FollowShip;
import net.zaizheli.domains.User;
import net.zaizheli.repositories.ActivityRepository;
import net.zaizheli.repositories.ApplicationRepository;
import net.zaizheli.repositories.FollowShipRepository;
import net.zaizheli.web.utils.SessionUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/activities")
public class ViewSingleActivityController {

	@Autowired
	ActivityRepository activityRepository;
	@Autowired
	SessionUtil sessionUtil;
	@Autowired
	ApplicationRepository applicationRepository;
	@Autowired
	FollowShipRepository followShipRepository;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String view(@PathVariable String id, Model model, 
			HttpServletRequest request, HttpSession session){
		int status = 0;
		User user = sessionUtil.getSignInUser(session);
		List<String> myUsers = new ArrayList<String> ();
		if (user != null) {
			List<String> types = new ArrayList<String>();
			types.add(ApplicationStatus.已加入.name());
			types.add(ApplicationStatus.申请中.name());
			Application app = applicationRepository.findByActivityAndapplicant(id, user.getId(), types);		
			if (app != null) {
				if(app.getStatus() == ApplicationStatus.已加入)
					status = 2;
				else status = 1;
			}
			List<FollowShip> fss = followShipRepository.findByFollowedAndStatus(user.getId(), 0);
			if(fss!=null){
				for(FollowShip fs : fss){
					if(fs.getTarget()==null) continue;
					String name = "\"" + fs.getTarget().getName() + "\"";
					myUsers.add(name);
				}
			}	
		}
		Activity activity = activityRepository.findOne(id);
		Date date = new Date();		
		if(activity.getEndedAt().getTime() <= date.getTime() && activity.getStatus() == "征集成员中") {
			activity.setStatus("已结束");
			activityRepository.save(activity);
		}
		model.addAttribute("myUsers",myUsers);
		model.addAttribute("status",status);
		model.addAttribute("activity", activity);
		model.addAttribute("no", 1);
		return "activity/single";	
	}
	
	@RequestMapping(value="/{id}/{no}", method=RequestMethod.GET)
	public String viewP(@PathVariable String id, @PathVariable int no ,Model model, 
			HttpServletRequest request, HttpSession session){	
		int status = 0;
		User user = sessionUtil.getSignInUser(session);
		List<String> myUsers = new ArrayList<String> ();
		if(user != null) {
			List<String> types = new ArrayList<String>();
			types.add(ApplicationStatus.已加入.name());
			types.add(ApplicationStatus.申请中.name());
			Application app = applicationRepository.findByActivityAndapplicant(id, user.getId(), types);
			if (app != null) {
				if(app.getStatus() == ApplicationStatus.已加入)
					status = 2;
				else status = 1;
			}
			List<FollowShip> fss = followShipRepository.findByFollowedAndStatus(user.getId(), 0);		
			if(fss!=null){
				for(FollowShip fs : fss){
					if(fs.getTarget()==null) continue;
					String name = "\"" + fs.getTarget().getName() + "\"";
					myUsers.add(name);
				}
			}
			
		}
		Activity activity = activityRepository.findOne(id);
		Date date = new Date();	
		if(activity.getEndedAt().getTime() <= date.getTime() && activity.getStatus() == "征集成员中") {
			activity.setStatus("已结束");
			activityRepository.save(activity);
		}
		model.addAttribute("myUsers",myUsers);
		model.addAttribute("status",status);
		model.addAttribute("activity", activity);
		model.addAttribute("no", no);
		return "activity/single";
	}
	
}
