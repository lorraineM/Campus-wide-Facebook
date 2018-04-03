package net.zaizheli.web.mvc.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.zaizheli.constants.ActionType;
import net.zaizheli.constants.ApplicationConfig;
import net.zaizheli.domains.Action;
import net.zaizheli.domains.Activity;
import net.zaizheli.domains.User;
import net.zaizheli.exceptions.UnauthorizedOperationException;
import net.zaizheli.repositories.ActionRepository;
import net.zaizheli.repositories.ActivityRepository;
import net.zaizheli.repositories.UserRepository;
import net.zaizheli.vo.ActionVo;
import net.zaizheli.web.utils.SessionUtil;

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
@RequestMapping("/messages")
public class ViewUserCommentsController {
	@Autowired
	ActionRepository actionRepository;
	@Autowired
	ActivityRepository activityRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	SessionUtil sessionUtil;
	
	@RequestMapping(value="/{id}/cmtbased/{no}", method=RequestMethod.GET)
	public String cmtBased(@PathVariable String id, 
			@PathVariable int no, Model model, 
			HttpServletRequest request, HttpSession session){
		List<String> types = new ArrayList<String>();
		types.add(ActionType.ACTIVITY.name());
		types.add(ActionType.FORWARD.name());
		Iterable<Action> as = actionRepository.findByOwnerAndTypeIn(id, types);
		List<String> ids = new ArrayList<String>();
		for (Action a: as){
			ids.add(a.getId());
		}
		Pageable pageable = new PageRequest(Math.max(no, 0), 
				ApplicationConfig.listPageSize, 
				new Sort(new Order(Direction.DESC, "createdAt")));
		types.clear();
		types.add(ActionType.COMMENT.name());
		User signInUser = sessionUtil.getSignInUser(session);
		if(signInUser==null){
			throw new UnauthorizedOperationException();
		}
		Iterable<Action> acts = actionRepository
				.findByBasedOnInAndTypeInAndOwnerNot(ids, types, 
						signInUser.getId(),pageable);
		model.addAttribute("activities", toActVos(acts));
		return "dashboard/actions";
	}
	
	@RequestMapping(value="/{id}/cmtfrom/{no}", method=RequestMethod.GET)
	public String cmtFrom(@PathVariable String id, 
			@PathVariable int no, Model model, 
			HttpServletRequest request, HttpSession session){
		Pageable pageable = new PageRequest(Math.max(no, 0), 
				ApplicationConfig.listPageSize, 
				new Sort(new Order(Direction.DESC, "createdAt")));
		Iterable<Action> acts = actionRepository.findByOwnerAndType(id, 
				ActionType.COMMENT, pageable);
		model.addAttribute("activities", toActVos(acts));
		return "dashboard/actions";
	}
	
	private Collection<ActionVo> toActVos(Iterable<Action> acts){
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
			if(StringUtils.hasText(act.getBasedOn())){
				Action basedOnAct = actionRepository.findOne(act.getBasedOn());
				ActionVo basedOn = ActionVo.from(basedOnAct);
				basedOn.setOwner(userRepository.findOne(basedOnAct.getOwner()));
				vo.setBasedOn(basedOn);
			}
			actions.add(vo);
		}
		return actions;
	}


}
