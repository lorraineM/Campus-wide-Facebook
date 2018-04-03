package net.zaizheli.web.mvc.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.zaizheli.constants.AjaxResultCode;
import net.zaizheli.repositories.ActivityRepository;
import net.zaizheli.repositories.CommentRepository;
import net.zaizheli.vo.AjaxResult;

@Controller
public class GiveAgreementController {
	
	@Autowired
	ActivityRepository activityRepository;
	@Autowired
	CommentRepository commentRepository;
	
	@RequestMapping(value="/ops/agree/{id}", method=RequestMethod.GET)
	public @ResponseBody AjaxResult agree(@PathVariable String id,
			       Model model, HttpSession session) {
		activityRepository.inc(id, "agreeCount", 1);
		activityRepository.inc(id, "hot", 0.1);
		return new AjaxResult(AjaxResultCode.SUCCESS);
	}
	
	@RequestMapping(value="/comment/agree/{id}", method=RequestMethod.GET)
	public @ResponseBody AjaxResult cagree(@PathVariable String id,
			       Model model, HttpSession session) {
		commentRepository.inc(id, "agreeCount", 1);
		return new AjaxResult(AjaxResultCode.SUCCESS);
	}

}
