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
import net.zaizheli.domains.FollowShip;
import net.zaizheli.domains.TrackShip;
import net.zaizheli.domains.User;
import net.zaizheli.repositories.ActionRepository;
import net.zaizheli.repositories.ActivityRepository;
import net.zaizheli.repositories.CityMetaRepository;
import net.zaizheli.repositories.FollowShipRepository;
import net.zaizheli.repositories.TrackShipRepository;
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
@RequestMapping("/actions")
public class ViewActionsForUserController {
	
	@Autowired
	ActionRepository actionRepository;
	@Autowired
	FollowShipRepository followShipRepository;
	@Autowired
	TrackShipRepository trackShipRepository;
	@Autowired
	ActivityRepository activityRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	SessionUtil sessionUtil;
	@Autowired
	CityMetaRepository cityMetaRepository;
	
	@RequestMapping(value="/{id}/follow/{no}", method=RequestMethod.GET)
	public String follow(@PathVariable String id, 
			@PathVariable int no, Model model, 
			HttpServletRequest request, HttpSession session){
		Iterable<FollowShip> fss = followShipRepository
				.findByFollowedAndStatus(id, 0);
		List<String> ids = new ArrayList<String>();
		for (FollowShip fs: fss){
			if(fs.getTarget()!=null){
				ids.add(fs.getTarget().getId());
			}
		}
		List<String> types = new ArrayList<String>();
		types.add(ActionType.ACTIVITY.name());
		types.add(ActionType.FORWARD.name());
		types.add(ActionType.COMMENT.name());
		types.add(ActionType.JOIN.name());
		types.add(ActionType.TRACK.name());
		types.add(ActionType.SETTING.name());
		types.add(ActionType.FOLLOW.name());
		types.add(ActionType.APPLY.name());
		types.add(ActionType.UPLOAD.name());
		types.add(ActionType.QUIT.name());
		Pageable pageable = new PageRequest(Math.max(no, 0), 
				ApplicationConfig.listPageSize, 
				new Sort(new Order(Direction.DESC, "createdAt")));
		Iterable<Action> acts = actionRepository
				.findByOwnerInAndTypeIn(ids, types, pageable);
		model.addAttribute("actions", toActVos(acts));
		return "dashboard/actions";
	}
	
	@RequestMapping(value="/{id}/track/{no}", method=RequestMethod.GET)
	public String track(@PathVariable String id, 
			@PathVariable int no, Model model, 
			HttpServletRequest request, HttpSession session){
		Iterable<TrackShip> tss = trackShipRepository.findByTrackedAndStatus(id, 0);
		List<String> ids = new ArrayList<String>();
		for (TrackShip ts: tss){
			if(ts.getTarget()!=null){
				ids.add(ts.getTarget().getId());
			}
		}
		List<String> types = new ArrayList<String>();
		types.add(ActionType.ACTIVITY.name());
		types.add(ActionType.FORWARD.name());
		types.add(ActionType.COMMENT.name());
		types.add(ActionType.SETTING.name());
		types.add(ActionType.JOIN.name());
		types.add(ActionType.APPLY.name());
		types.add(ActionType.UPLOAD.name());
		types.add(ActionType.QUIT.name());
		Pageable pageable = new PageRequest(Math.max(no, 0), 
				ApplicationConfig.listPageSize, 
				new Sort(new Order(Direction.DESC, "createdAt")));
		Iterable<Action> acts = actionRepository
				.findByTragetActivityInAndTypeIn(ids, types, pageable);
		model.addAttribute("actions", toActVos(acts));
		return "dashboard/actions";
	}
	
//	@RequestMapping(value="/{id}/tracks/{no}", method=RequestMethod.GET)
//	public String tracks(@PathVariable String id,
//			@PathVariable int no, Model model, 
//			HttpServletRequest request, HttpSession session){
//		User user = userRepository.findOne(id);
//		model.addAttribute("user", user);
//		Pageable pageable = new PageRequest(no >= 0 ? no : 0, 
//				ApplicationConfig.masonryPageSize, 
//				new Sort(new Order(Direction.DESC, "createdAt")));
//		Iterable<TrackShip> tss = trackShipRepository.findByTrackedAndStatus(id, 0, pageable);
//		Collection<PinVo> pins = new ArrayList<PinVo>();
//		if(tss!=null){
//			pageable = new PageRequest(0, 
//					ApplicationConfig.pinCmtPageSize, 
//					new Sort(new Order(Direction.DESC, "createdAt")));
//			for(TrackShip ts : tss){
//				if(ts.getTarget()==null) continue;
//				Activity activity = ts.getTarget();
//				Action act = actionRepository.getByOwnerAndTargetActivityAndType(
//						activity.getCreatedBy().getId(), activity.getId(), ActionType.ACTIVITY.name());
//				if(activity.getPlace()==null){
//					pins.add(PinVo.from(activity,
//							cityMetaRepository.getByPinyin(StringUtils.hasText(activity.getCity())?
//									activity.getCity():ApplicationConfig.defaultCityPinyin), act));
//				}else{
//					pins.add(PinVo.from(activity,null,act));
//				}
//			}
//		}
//		model.addAttribute("pins", pins);
//		return "activity/list";
//	}	

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
