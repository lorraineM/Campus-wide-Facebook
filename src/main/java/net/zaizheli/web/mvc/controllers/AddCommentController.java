package net.zaizheli.web.mvc.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.zaizheli.constants.ActionType;
import net.zaizheli.constants.ApplicationConfig;
import net.zaizheli.constants.ApplicationConstants;
import net.zaizheli.constants.MessageType;
import net.zaizheli.domains.Action;
import net.zaizheli.domains.Activity;
import net.zaizheli.domains.Comment;
import net.zaizheli.domains.Message;
import net.zaizheli.domains.User;
import net.zaizheli.repositories.ActionRepository;
import net.zaizheli.repositories.ActivityRepository;
import net.zaizheli.repositories.CommentRepository;
import net.zaizheli.repositories.MessageRepository;
import net.zaizheli.repositories.UserRepository;
import net.zaizheli.vo.CommentFormBean;
import net.zaizheli.web.utils.AjaxUtil;
import net.zaizheli.web.utils.SessionUtil;
import net.zaizheli.web.utils.TextUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/activity")
@SessionAttributes(ApplicationConstants.SESSION_SIGNIN_USER)
public class AddCommentController {
	
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	ActionRepository actionRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ActivityRepository activityRepository;
	@Autowired
	SessionUtil sessionUtil;
	@Autowired
	private AjaxUtil ajaxUtil;
	@Autowired
	private TextUtil textUtil;
	@Autowired
	MessageRepository messageRepository;
	
	@ModelAttribute("cmtFormBean")
	public CommentFormBean creatFormBean() {
		CommentFormBean bean = new CommentFormBean();
		return bean;
	}

	@RequestMapping(value="/addcmt", method=RequestMethod.POST)
	public String add(@Valid CommentFormBean bean,
			BindingResult result,
			Model model, HttpServletRequest request, HttpSession session){	
		User signInUser=sessionUtil.getSignInUser(session);
		if(signInUser==null) {
			return "redirect:/signin";
		}
		Activity activity= activityRepository.findOne(bean.getActId());
		if(activity==null){
			throw new RuntimeException("Invalid activity id:" + bean.getActId());
		}
		// save comment
		Comment cmt = Comment.from(bean, signInUser);
		String con=bean.getContent();		
		con = textUtil.removeHtml(con);
		con = At(con, signInUser, activity);
		con = textUtil.turn(con);
		con = link(con);
		cmt.setContent(con);
		Comment baseCmt = commentRepository.findOne(bean.getCmtId());
		User user = userRepository.findOne(bean.getReplyToId());
		if(baseCmt!=null  && user!=null && bean.getContent().indexOf("回复 "+user.getName()+":")==0) {
			cmt.setReplyTo(user);
			String context = cmt.getContent();
			int index = context.indexOf(":");
			String content = "<a href=\"#" + baseCmt.getId() + "\">" + context.substring(0,index)
					+ " (第" + baseCmt.getFloor() + "层):</a><br>";
			if(context.substring(index+1,index+2) == "\n")
					content = content + context.substring(index+2);
			else content = content + context.substring(index+1);
			cmt.setContent(content);	
		}	
		cmt.setActivity(activity);
		cmt.setFloor(activity.getCommentCount()+1);				
		// inc commented count of orignal activity
		activityRepository.inc(activity.getId(), "commentCount", 1);
		activityRepository.inc(activity.getId(), "hot", 1);
		// incr comment count of sign in user
		userRepository.inc(signInUser.getId(), "commentCount", 1);	
		cmt = commentRepository.save(cmt);
		
		int size = commentRepository.findByActivity(activity.getId()).size();
		int total = 0 ;
		if (size % ApplicationConfig.cmtPageSize == 0)
	      total = (int)(size/ ApplicationConfig.cmtPageSize);
		else {
			total = (int)(size/ ApplicationConfig.cmtPageSize)+1;
		}
		StringBuilder url = new StringBuilder();
		url.append("/activities/").append(activity.getId());
		url.append("/").append(total).append("#").append(cmt.getId());
		
		// save cmt activity
		Action action= new Action();
		action.setOwner(signInUser.getId());
		action.setCreatedAt(new Date());
		action.setTargetActivity(activity.getId());
		if (baseCmt!=null && user != null && bean.getContent().indexOf("回复 "+user.getName()+":")==0 ) {
			action.setTargetUser(user.getId());	
			Message message = new Message();
			message.setFrom(signInUser);
			message.setTo(user);
			message.setContent(signInUser.getName()+"在活动  <a href='/activities/" + activity.getId() 
					+ "'>" + activity.getTitle()+"</a> 中回复了您");
			message.setStatus(0);
			message.setType(MessageType.INFORM);
			message.setActivity(activity);
			message.setCreatedAt(new Date());
			messageRepository.save(message);
		}
		action.setContent(con);
		action.setType(ActionType.COMMENT);
		action.setBy(sessionUtil.getBy(session));
		actionRepository.save(action);
		return "redirect:" + url.toString();
	}
	
	public String At(String content, User signInUser, Activity activity) {
		int start=0, end;
        while ((start = content.indexOf("@", start)) != -1) {
        	end = content.indexOf(" ", start);
        	String name = content.substring(start+1, end);
        	User user = userRepository.getByName(name);
        	if( user != null) {
        		String html = content.substring(0, start) + "<ahref='/profiles/" + user.getId() 
    					+ "'>";
        		int index = start;
        		start = html.length();
        		content = html + content.substring(index,end) + 
        				  "</a>" + content.substring(end);
        		Message message = new Message();
        		message.setFrom(signInUser);
    			message.setTo(user);
    			message.setContent(signInUser.getName()+" 在活动 <a href='/activities/" + activity.getId() 
    					+ "'>" + activity.getTitle()+"</a> 中@了您");
    			message.setStatus(0);
    			message.setType(MessageType.AT);
    			message.setActivity(activity);
    			message.setCreatedAt(new Date());
        		messageRepository.save(message);
        	}
        	start += 1;
        }       
        return content;  
	}
	
	public String link(String content) {
		content = content.replaceAll("<ahref='/profiles/", "<a href='/profiles/");    
        return content;		
	}
}
