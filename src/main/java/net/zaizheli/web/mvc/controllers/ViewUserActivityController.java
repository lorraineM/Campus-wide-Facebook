package net.zaizheli.web.mvc.controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import net.zaizheli.constants.ActionType;
import net.zaizheli.constants.ApplicationConfig;
import net.zaizheli.domains.Action;
import net.zaizheli.domains.Activity;
import net.zaizheli.domains.Join;
import net.zaizheli.domains.User;
import net.zaizheli.repositories.ActionRepository;
import net.zaizheli.repositories.ActivityRepository;
import net.zaizheli.repositories.CityMetaRepository;
import net.zaizheli.repositories.JoinRepository;
import net.zaizheli.repositories.UserRepository;
import net.zaizheli.vo.PinVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/profiles")
public class ViewUserActivityController {
	
	@Autowired
	CityMetaRepository cityMetaRepository;
	@Autowired
	ActivityRepository activityRepository;
	@Autowired
	JoinRepository joinRepository;
	@Autowired
	ActionRepository actionRepository;
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value="/{id}/c_activitys/{no}", method=RequestMethod.GET)
	public String c_activities(@PathVariable String id,
			@PathVariable int no, Model model, 
			HttpServletRequest request, HttpSession session){
		User user = userRepository.findOne(id);
		model.addAttribute("user", user);
		Pageable pageable = new PageRequest(no >= 0 ? no : 0, 
				ApplicationConfig.masonryPageSize, 
				new Sort(new Order(Direction.DESC, "createdAt")));
		Iterable<Activity> activities = activityRepository.findByCreatedBy(id, pageable);
		Collection<PinVo> pins = new ArrayList<PinVo>();
		if(activities!=null){
			for(Activity activity : activities){
				Action act = actionRepository.getByOwnerAndTargetActivityAndType(
						activity.getCreatedBy().getId(), activity.getId(), ActionType.ACTIVITY.name());
				if(activity.getPlace()==null){
					pins.add(PinVo.from(activity,
							cityMetaRepository.getByPinyin(StringUtils.hasText(activity.getCity())?
									activity.getCity():ApplicationConfig.defaultCityPinyin), act));
				}else{
					pins.add(PinVo.from(activity,null,act));
				}
			}
		}
		model.addAttribute("pins", pins);
		return "activity/list";
	}
	
	
	@RequestMapping(value="/{id}/activitys/{no}", method=RequestMethod.GET)
	public String activities(@PathVariable String id,
			@PathVariable int no, Model model, 
			HttpServletRequest request, HttpSession session){
		User user = userRepository.findOne(id);
		model.addAttribute("user", user);
		Pageable pageable = new PageRequest(no >= 0 ? no : 0, 
				ApplicationConfig.masonryPageSize, 
				new Sort(new Order(Direction.DESC, "createdAt")));
		Iterable<Join> joins = joinRepository.findByJoiner(id, pageable);
		Collection<PinVo> pins = new ArrayList<PinVo>();
		if(joins!=null){
			for(Join join : joins){
				Activity ac=join.getActivity();
				Action act = actionRepository.getByOwnerAndTargetActivityAndType(
						ac.getCreatedBy().getId(), ac.getId(), ActionType.ACTIVITY.name());
				if(ac.getPlace()==null){
					pins.add(PinVo.from(ac,
							cityMetaRepository.getByPinyin(StringUtils.hasText(ac.getCity())?
									ac.getCity():ApplicationConfig.defaultCityPinyin), act));
				}else{
					pins.add(PinVo.from(ac,null,act));
				}
			}
		}
		model.addAttribute("pins", pins);
		return "activity/list";
	}
	
	
}
