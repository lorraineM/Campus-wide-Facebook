package net.zaizheli.web.mvc.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.zaizheli.constants.ActionType;
import net.zaizheli.constants.AjaxResultCode;
import net.zaizheli.constants.ApplicationStatus;
import net.zaizheli.constants.MessageType;
import net.zaizheli.domains.Action;
import net.zaizheli.domains.Activity;
import net.zaizheli.domains.Application;
import net.zaizheli.domains.Join;
import net.zaizheli.domains.Message;
import net.zaizheli.domains.User;
import net.zaizheli.repositories.ActionRepository;
import net.zaizheli.repositories.ActivityRepository;
import net.zaizheli.repositories.ApplicationRepository;
import net.zaizheli.repositories.JoinRepository;
import net.zaizheli.repositories.MessageRepository;
import net.zaizheli.vo.AjaxResult;
import net.zaizheli.web.utils.SessionUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/activity")
public class JoinActivityController {
	
	@Autowired
	ActivityRepository activityRepository;
	@Autowired 
	SessionUtil sessionUtil;
	@Autowired
	JoinRepository joinRepository;
	@Autowired
	ApplicationRepository applicationRepository;
	@Autowired
	ActionRepository actionRepository;
	@Autowired
	MessageRepository messageRepository;
		
	@RequestMapping(value="/{id}/join", method=RequestMethod.GET)
	public @ResponseBody AjaxResult join(@PathVariable String id, Model model, 
			HttpServletRequest request, HttpSession session){
		User user = sessionUtil.getSignInUser(session);
		List<String> types = new ArrayList<String>();
		types.add(ApplicationStatus.已加入.name());
		types.add(ApplicationStatus.申请中.name());
        Application app = applicationRepository.findByActivityAndapplicant(id, user.getId(), types);
        if (app!=null) {
        	if(app.getStatus()==ApplicationStatus.申请中) {
        	     return new AjaxResult(AjaxResultCode.INVALID, "之前提交过了吧，申请正在等待活动创始人同意中哦 >o<");
        	}
        	if(app.getStatus()==ApplicationStatus.已加入) {
        		return new AjaxResult(AjaxResultCode.INVALID, "不用提交申请了，你已经是成员了哦 >o<");
        	}
        }       
		Activity activity=activityRepository.findOne(id);
		if(activity.getCurrentNum() >= activity.getMaxNum()) {
			return new AjaxResult(AjaxResultCode.INVALID, "抱歉，活动已经满员了  >o<");
		}
		if(activity.getApply()==1) {
			return new AjaxResult(AjaxResultCode.NEED_APP,id);
		}    		
		Application application = new Application();
		application.setActivity(activity);
		application.setApplicant(user);
		application.setStatus(ApplicationStatus.申请中);
		application.setCreatedAt(new Date());
		application.setUpdatedAt(application.getCreatedAt());
		applicationRepository.save(application);
		activityRepository.inc(id, "applicationCount", 1);
		activityRepository.inc(id, "inJudgingCount", 1);
		Action action = new Action();
		action.setOwner(user.getId());
		action.setTargetActivity(id);
		action.setCreatedAt(new Date());
		action.setType(ActionType.APPLY);
		action.setBy(sessionUtil.getBy(session));
		actionRepository.save(action);
		return new AjaxResult(AjaxResultCode.SUCCESS);
	}
	
	@RequestMapping(value="/{id}/quit", method=RequestMethod.GET)
	public @ResponseBody AjaxResult quit(@PathVariable String id, Model model, 
			HttpServletRequest request, HttpSession session){
		User user = sessionUtil.getSignInUser(session);
		List<String> types = new ArrayList<String>();
		types.add(ApplicationStatus.已加入.name());
		types.add(ApplicationStatus.申请中.name());
        Application app = applicationRepository.findByActivityAndapplicant(id, user.getId(), types);     
        if (app == null) {
        	return new AjaxResult(AjaxResultCode.INVALID,"您已不在活动成员行列中了");
        }
		Activity activity=activityRepository.findOne(id);
		activityRepository.inc(id, "applicationCount", -1);
		activityRepository.inc(activity.getId(),"currentNum",-1);
		activity.updateHot();
		activityRepository.save(activity);
		Join join = joinRepository.findByActivityAndjoiner(activity.getId(),app.getApplicant().getId());
		Action action = new Action();
		action.setOwner(user.getId());
		action.setTargetActivity(id);
		action.setCreatedAt(new Date());
		action.setType(ActionType.QUIT);
		action.setBy(sessionUtil.getBy(session));
		actionRepository.save(action);
		Message message = new Message();
		message.setFrom(null);
		User applicant = app.getApplicant();
		message.setTo(applicant);
		message.setContent("您退出了活动"+activity.getTitle());
		message.setStatus(0);
		message.setType(MessageType.INFORM);
		messageRepository.save(message);
		applicationRepository.delete(app);
		joinRepository.delete(join);
		return new AjaxResult(AjaxResultCode.SUCCESS);
	}
	

}
