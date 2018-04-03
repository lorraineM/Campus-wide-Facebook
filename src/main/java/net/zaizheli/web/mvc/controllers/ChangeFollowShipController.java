package net.zaizheli.web.mvc.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.zaizheli.constants.ActionType;
import net.zaizheli.constants.AjaxResultCode;
import net.zaizheli.constants.ApplicationConstants;
import net.zaizheli.domains.Action;
import net.zaizheli.domains.FollowShip;
import net.zaizheli.domains.User;
import net.zaizheli.exceptions.ResourceNotFoundException;
import net.zaizheli.exceptions.UnauthorizedOperationException;
import net.zaizheli.repositories.ActionRepository;
import net.zaizheli.repositories.FollowShipRepository;
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
public class ChangeFollowShipController {
	
	@Autowired
	FollowShipRepository followShipRepository;
	@Autowired
	ActionRepository actionRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	SessionUtil sessionUtil;
	@Autowired
	private AjaxUtil ajaxUtil;
	
	@RequestMapping(value="/follow/{targetId}", method=RequestMethod.GET)
	public @ResponseBody AjaxResult follow(@PathVariable String targetId,
			@ModelAttribute(ApplicationConstants.SESSION_SIGNIN_USER) User signInUser,
			Model model, HttpServletRequest request, HttpSession session){
		if(!ajaxUtil.isAjaxRequest(request)){
			throw new ResourceNotFoundException();
		}
		User target = userRepository.findOne(targetId);
		if(target==null){
			throw new RuntimeException("Invalid user id : " + targetId);
		}
		if (targetId.equals(signInUser.getId())) {
			return new AjaxResult(AjaxResultCode.SUCCESS);
		}
		FollowShip fs = followShipRepository.getByTargetAndFollowed(targetId, signInUser.getId());
		boolean followed = false;
		if(fs!=null && fs.getStatus() == ApplicationConstants.FOLLOWSHIP_DISABLED){
			followed = true;
			fs.setStatus(ApplicationConstants.FOLLOWSHIP_NORMAL);
			fs.setUpdatedAt(new Date());
			userRepository.inc(signInUser.getId(), "followCount", 1);
			userRepository.inc(target.getId(), "fansCount", 1);
		}else if(fs==null){
			followed = true;
			fs = new FollowShip();
			fs.setCreatedAt(new Date());
			fs.setUpdatedAt(fs.getCreatedAt());
			fs.setTarget(target);
			fs.setFollowed(signInUser);
			fs.setStatus(ApplicationConstants.FOLLOWSHIP_NORMAL);
			userRepository.inc(signInUser.getId(), "followCount", 1);
			userRepository.inc(target.getId(), "fansCount", 1);
		}
		if(followed){
			followShipRepository.save(fs);
			// save action
			Action action = new Action();
			action.setOwner(signInUser.getId());
			action.setCreatedAt(new Date());
			action.setTargetUser(target.getId());
			action.setType(ActionType.FOLLOW);
			action.setBy(sessionUtil.getBy(session));
			actionRepository.save(action);
		}
		return new AjaxResult(AjaxResultCode.SUCCESS);
	}
	
	@RequestMapping(value="/defollow/{targetId}", method=RequestMethod.GET)
	public @ResponseBody AjaxResult defollow(@PathVariable String targetId,
			@ModelAttribute(ApplicationConstants.SESSION_SIGNIN_USER) User signInUser,
			Model model, HttpServletRequest request, 
			HttpSession session){
		if(!ajaxUtil.isAjaxRequest(request)){
			throw new ResourceNotFoundException();
		}
		if(signInUser==null){
			throw new UnauthorizedOperationException();
		}
		User target = userRepository.findOne(targetId);
		if(target==null){
			throw new RuntimeException("Invalid user id : " + targetId);
		}
		FollowShip fs = followShipRepository.getByTargetAndFollowed(targetId, signInUser.getId());
		if(fs!=null && fs.getStatus() == ApplicationConstants.FOLLOWSHIP_NORMAL){
			fs.setStatus(ApplicationConstants.FOLLOWSHIP_DISABLED);
			fs.setUpdatedAt(new Date());
			userRepository.inc(signInUser.getId(), "followCount", -1);
			userRepository.inc(target.getId(), "fansCount", -1);
			followShipRepository.save(fs);
		}
		return new AjaxResult(AjaxResultCode.SUCCESS);
	}
}
