package net.zaizheli.web.mvc.controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import net.zaizheli.constants.ApplicationConfig;
import net.zaizheli.domains.Action;
import net.zaizheli.domains.Activity;
import net.zaizheli.domains.User;
import net.zaizheli.repositories.ActionRepository;
import net.zaizheli.repositories.ActivityRepository;
import net.zaizheli.repositories.UserRepository;
import net.zaizheli.vo.ActionVo;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/profiles")
public class ViewUserActionController {
	
	@Autowired
	ActivityRepository activityRepository;
	@Autowired
	ActionRepository actionRepository;
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value="/action/{id}/{no}", method=RequestMethod.GET)
	public String view(@PathVariable String id, 
			@PathVariable int no, Model model, 
			HttpServletRequest request, HttpSession session){
		Pageable pageable = new PageRequest(no >= 0 ? no : 0, 
				ApplicationConfig.listPageSize, 
				new Sort(new Order(Direction.DESC, "createdAt")));
		Iterable<Action> acts = actionRepository.findByOwner(id, pageable);
		Collection<ActionVo> actions = new ArrayList<ActionVo>();
		for(Action act : acts){
			ActionVo vo = ActionVo.from(act);
			if(act.getOwner()!=null){
				User owner = userRepository.findOne(act.getOwner());
				vo.setOwner(owner);
			}
			if(act.getTargetActivity()!=null){
				Activity activity = activityRepository.findOne(act.getTargetActivity());
				vo.setTargetActivity(activity);
			}
			if(act.getTargetUser()!=null){
				User targetUser = userRepository.findOne(act.getTargetUser());
				vo.setTargetUser(targetUser);
			}
			actions.add(vo);
		}
		model.addAttribute("actions", actions);
		return "profiles/actions";
	}
	
}
