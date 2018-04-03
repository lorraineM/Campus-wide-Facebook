package net.zaizheli.web.mvc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.zaizheli.constants.ApplicationConfig;
import net.zaizheli.constants.ApplicationStatus;
import net.zaizheli.domains.Application;
import net.zaizheli.repositories.ActivityRepository;
import net.zaizheli.repositories.ApplicationRepository;
import net.zaizheli.web.utils.SessionUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping("/activity")
public class ViewApplicationStatusController {
	@Autowired
	ActivityRepository activityRepository;
	@Autowired 
	SessionUtil sessionUtil;
	@Autowired
	ApplicationRepository applicationRepository;
	
	@RequestMapping(value="/{id}/applications/ins/{no}", method=RequestMethod.GET)
	public String view0(@PathVariable String id, Model model, 
			@PathVariable int no, HttpServletRequest request, HttpSession session){
		Pageable pageable = new PageRequest(no >= 0 ? no : 0, 
				ApplicationConfig.masonryPageSize, 
				new Sort(new Order(Direction.ASC, "createdAt")));
		Page<Application> applications = applicationRepository.findByActivityAndstatus(id, ApplicationStatus.申请中, pageable);
		model.addAttribute("applications",applications.getContent());
		
		return "application/applicant";
	}
	
	@RequestMapping(value="/{id}/applications/agrees/{no}", method=RequestMethod.GET)
	public String view1(@PathVariable String id, Model model, 
			@PathVariable int no, HttpServletRequest request, HttpSession session){
		Pageable pageable = new PageRequest(no >= 0 ? no : 0, 
				ApplicationConfig.masonryPageSize, 
				new Sort(new Order(Direction.ASC, "createdAt")));
		Page<Application> applications = applicationRepository.findByActivityAndstatus(id, ApplicationStatus.已加入, pageable);
		model.addAttribute("applications",applications.getContent());		
		return "application/applicant";
	}
	
	@RequestMapping(value="/{id}/applications/refuses/{no}", method=RequestMethod.GET)
	public String view2(@PathVariable String id, Model model, 
			@PathVariable int no, HttpServletRequest request, HttpSession session){
		Pageable pageable = new PageRequest(no >= 0 ? no : 0, 
				ApplicationConfig.masonryPageSize, 
				new Sort(new Order(Direction.ASC, "createdAt")));
		Page<Application> applications = applicationRepository.findByActivityAndstatus(id, ApplicationStatus.拒绝, pageable);

		model.addAttribute("applications",applications.getContent());
		
		return "application/applicant";
	}
}
