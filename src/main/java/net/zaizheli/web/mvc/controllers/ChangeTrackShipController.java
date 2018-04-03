package net.zaizheli.web.mvc.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.zaizheli.constants.ActionType;
import net.zaizheli.constants.AjaxResultCode;
import net.zaizheli.constants.ApplicationConstants;
import net.zaizheli.domains.Action;
import net.zaizheli.domains.Activity;
import net.zaizheli.domains.TrackShip;
import net.zaizheli.domains.User;
import net.zaizheli.exceptions.ResourceNotFoundException;
import net.zaizheli.repositories.ActionRepository;
import net.zaizheli.repositories.ActivityRepository;
import net.zaizheli.repositories.TrackShipRepository;
import net.zaizheli.repositories.UserRepository;
import net.zaizheli.vo.AjaxResult;
import net.zaizheli.web.utils.AjaxUtil;
import net.zaizheli.web.utils.SessionUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/ops")
@SessionAttributes(ApplicationConstants.SESSION_SIGNIN_USER)
public class ChangeTrackShipController {
	
	@Autowired
	TrackShipRepository trackShipRepository;
	@Autowired
	ActionRepository actionRepository;
	@Autowired
	ActivityRepository activityRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	SessionUtil sessionUtil;
	@Autowired
	private AjaxUtil ajaxUtil;
	
	@RequestMapping(value="/track/{targetId}", method=RequestMethod.GET)
	public @ResponseBody AjaxResult follow(@PathVariable String targetId,
			@ModelAttribute(ApplicationConstants.SESSION_SIGNIN_USER) User signInUser,
			Model model, HttpServletRequest request, HttpSession session){
		if(!ajaxUtil.isAjaxRequest(request)){
			throw new ResourceNotFoundException();
		}
		Activity target = activityRepository.findOne(targetId);
		if(target==null){
			throw new RuntimeException("Invalid activity id : " + targetId);
		}
		TrackShip ts = trackShipRepository.getByTargetAndTracked(targetId, signInUser.getId());
		boolean tracked = false;
		if(ts!=null && ts.getStatus() == ApplicationConstants.TRACKSHIP_DISABLED){
			tracked = true;
			ts.setStatus(ApplicationConstants.TRACKSHIP_NORMAL);
			ts.setUpdatedAt(new Date());
			userRepository.inc(signInUser.getId(), "trackCount", 1);
			activityRepository.inc(target.getId(), "trackCount", 1);
			activityRepository.inc(target.getId(), "hot", 1);
			trackShipRepository.save(ts);
		}else if(ts==null){
			tracked = true;
			ts = new TrackShip();
			ts.setCreatedAt(new Date());
			ts.setUpdatedAt(ts.getCreatedAt());
			ts.setTarget(target);
			ts.setTracked(signInUser);
			ts.setStatus(ApplicationConstants.TRACKSHIP_NORMAL);
			userRepository.inc(signInUser.getId(), "trackCount", 1);
			activityRepository.inc(target.getId(), "trackCount", 1);
			activityRepository.inc(target.getId(), "hot", 1);
			trackShipRepository.save(ts);
		}
		if(tracked){
			trackShipRepository.save(ts);
			// save action
			Action action = new Action();
			action.setOwner(signInUser.getId());
			action.setCreatedAt(new Date());
			action.setTargetActivity(target.getId());
			action.setType(ActionType.TRACK);
			action.setBy(sessionUtil.getBy(session));
			actionRepository.save(action);
		}
		return new AjaxResult(AjaxResultCode.SUCCESS);
	}
	
	@RequestMapping(value="/detrack/{targetId}", method=RequestMethod.GET)
	public @ResponseBody AjaxResult defollow(@PathVariable String targetId,
			@ModelAttribute(ApplicationConstants.SESSION_SIGNIN_USER) User signInUser,
			Model model, HttpServletRequest request, 
			HttpSession session){
		if(!ajaxUtil.isAjaxRequest(request)){
			throw new ResourceNotFoundException();
		}
		Activity target = activityRepository.findOne(targetId);
		if(target==null){
			throw new RuntimeException("Invalid activity id : " + targetId);
		}
		TrackShip ts = trackShipRepository.getByTargetAndTracked(targetId, signInUser.getId());
		if(ts!=null && ts.getStatus() == ApplicationConstants.TRACKSHIP_NORMAL){
			ts.setStatus(ApplicationConstants.TRACKSHIP_DISABLED);
			ts.setUpdatedAt(new Date());
			userRepository.inc(signInUser.getId(), "trackCount", -1);
			activityRepository.inc(target.getId(), "trackCount", -1);
			activityRepository.inc(target.getId(), "hot", -1);
			trackShipRepository.save(ts);
		}
		return new AjaxResult(AjaxResultCode.SUCCESS);
	}
	
}
