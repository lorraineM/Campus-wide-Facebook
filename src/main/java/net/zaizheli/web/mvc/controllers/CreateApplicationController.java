package net.zaizheli.web.mvc.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.zaizheli.constants.ActionType;
import net.zaizheli.constants.AjaxResultCode;
import net.zaizheli.constants.ApplicationStatus;
import net.zaizheli.domains.Action;
import net.zaizheli.domains.Activity;
import net.zaizheli.domains.Application;
import net.zaizheli.domains.User;
import net.zaizheli.repositories.ActionRepository;
import net.zaizheli.repositories.ActivityRepository;
import net.zaizheli.repositories.ApplicationRepository;
import net.zaizheli.repositories.JoinRepository;
import net.zaizheli.repositories.UserRepository;
import net.zaizheli.vo.AjaxResult;
import net.zaizheli.vo.ApplicationVo;
import net.zaizheli.web.utils.SessionUtil;
import net.zaizheli.web.utils.TextUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/application")
public class CreateApplicationController {  

	@Autowired
	ActivityRepository activityRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	JoinRepository joinRepository;
	@Autowired
	SessionUtil sessionUtil;
	@Autowired
	ApplicationRepository applicationRepository;
	@Autowired
	ActionRepository actionRepository;
	@Autowired 
	TextUtil textUtil;
	
	@ModelAttribute("ApplicationVo")
	public ApplicationVo creatApplicationVo() {
		ApplicationVo vo = new ApplicationVo();
		return vo;
	}
	
	@RequestMapping(value="/{id}/create", method=RequestMethod.GET)
	public String create(@PathVariable String id, Model model, HttpSession session){
		if (sessionUtil.getSignInUser(session) == null) {
			return "redirect:/signin";
		}
		Activity activity=activityRepository.findOne(id);
		model.addAttribute("activity", activity);
		User signInuser=sessionUtil.getSignInUser(session);
		model.addAttribute("user",signInuser);
		return "application/apply";
	}
		
	@RequestMapping(value="/{id}/create", method=RequestMethod.POST)
	public @ResponseBody AjaxResult create(@PathVariable String id, @Valid ApplicationVo vo, 
			BindingResult result, ModelAndView mav, HttpSession session) throws ParseException{
		User user = sessionUtil.getSignInUser(session);
		List<String> types = new ArrayList<String>();
		types.add(ApplicationStatus.已加入.name());
		types.add(ApplicationStatus.申请中.name());
        Application ap = applicationRepository.findByActivityAndapplicant(id, user.getId(), types);
        if (ap!=null) {
        	if(ap.getStatus()==ApplicationStatus.申请中) {
        	     return new AjaxResult(AjaxResultCode.INVALID, "之前提交过了吧，申请正在等待活动创始人同意中哦 >o<");
        	}
        	if(ap.getStatus()==ApplicationStatus.已加入) {
        		return new AjaxResult(AjaxResultCode.INVALID, "不用提交申请了，你已经是成员了哦 >o<");
        	}
        }       
		Activity act=activityRepository.findOne(id);
		if(act.getCurrentNum() >= act.getMaxNum()) {
			return new AjaxResult(AjaxResultCode.INVALID, "抱歉，活动已经满员了  >o<");
		}
		//remove html
		vo.setAddress(textUtil.removeHtml(vo.getAddress()));
		vo.setRealName(textUtil.removeHtml(vo.getRealName()));
		vo.setSchool(textUtil.removeHtml(vo.getSchool()));
	    String note = textUtil.removeHtml(vo.getNote());
	    vo.setNote(textUtil.turn(note));
		Application app = Application.from(vo);
        app.setApplicant(user);
        Activity activity = activityRepository.findOne(id);
        app.setActivity(activity);
        app.setStatus(ApplicationStatus.申请中);
        applicationRepository.save(app);
		activityRepository.inc(id, "applicationCount", 1);
		activityRepository.inc(id, "inJudgingCount", 1);
		activityRepository.inc(id, "hot", 0.3);
		Action action = new Action();
		action.setOwner(user.getId());
		action.setTargetActivity(id);
		action.setCreatedAt(new Date());
		action.setType(ActionType.APPLY);
		action.setBy(sessionUtil.getBy(session));
		actionRepository.save(action);
		return new AjaxResult(AjaxResultCode.SUCCESS);
	}
}

