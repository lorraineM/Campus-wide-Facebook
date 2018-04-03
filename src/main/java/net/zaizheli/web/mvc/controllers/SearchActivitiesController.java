package net.zaizheli.web.mvc.controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;


import net.zaizheli.constants.ActionType;
import net.zaizheli.constants.AjaxResultCode;
import net.zaizheli.constants.ApplicationConfig;
import net.zaizheli.domains.Action;
import net.zaizheli.domains.Activity;
import net.zaizheli.exceptions.ResourceNotFoundException;
import net.zaizheli.repositories.ActionRepository;
import net.zaizheli.repositories.ActivityRepository;
import net.zaizheli.repositories.CityMetaRepository;
import net.zaizheli.repositories.CommentRepository;
import net.zaizheli.repositories.UserRepository;
import net.zaizheli.vo.AjaxResult;
import net.zaizheli.vo.MarkerVo;
import net.zaizheli.vo.PinVo;
import net.zaizheli.web.utils.AjaxUtil;
import net.zaizheli.web.utils.MapUtil;
import net.zaizheli.web.utils.SessionUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/spots")
public class SearchActivitiesController {
	private static final Logger logger = 
			LoggerFactory.getLogger(SearchActivitiesController.class);
	@Autowired
	private CityMetaRepository cityMetaRepository;
	@Autowired
	private ActivityRepository activityRepository;
	@Autowired
	private ActionRepository actionRepository;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	private AjaxUtil ajaxUtil;
	@Autowired
	MapUtil mapUtil;
	@Autowired
	SessionUtil sessionUtil;
	
	@RequestMapping(value="/search/list", method=RequestMethod.GET)
	public String search(Model model, HttpServletRequest request){
		Iterable<Activity> activities = doSearch(request, false);
		Collection<PinVo> pins = new ArrayList<PinVo>();
		if(activities!=null){
			for(Activity activity : activities){
				try{
					Action act = actionRepository.getByOwnerAndTargetActivityAndType(
							activity.getCreatedBy().getId(), activity.getId(), ActionType.ACTIVITY.name());
					if(activity.getPlace()==null){
						pins.add(PinVo.from(activity,
								cityMetaRepository.getByPinyin(StringUtils.hasText(activity.getCity())?
										activity.getCity():ApplicationConfig.defaultCityPinyin), act));
					}else{
						pins.add(PinVo.from(activity,null, act));
					}
				}catch(Exception e){
					logger.warn(e.getMessage(), e);
				}
			}
		}
		logger.info("**** Get " + (pins!=null?pins.size():0)+" activities");
		model.addAttribute("pins", pins);
		return "activity/list";
	}
	
	private Iterable<Activity> doSearch(HttpServletRequest request, boolean isMarker){
		String city = request.getParameter("city");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String category = request.getParameter("category");
		String summaryLike = request.getParameter("keyword");
		String no = request.getParameter("no");
		logger.info("city:" + city);
		logger.info("startTime:" + startTime);
		logger.info("endTime:" + endTime);
		logger.info("category:" + category);
		logger.info("keyword:" + summaryLike);
		logger.info("no:" + no);
		int pageNo = 0;
		try{
			pageNo = Integer.parseInt(no);
		}catch(NumberFormatException nfe){
			logger.info("paramater page no is invalid, will use 0 as default.");
		}
		Pageable pageable = new PageRequest(Math.max(pageNo, 0), 
				ApplicationConfig.masonryPageSize, 
				new Sort(new Order(Direction.DESC, "createdAt")));
		if(ApplicationConfig.defaultCityPinyin.equals(city)){
			city = "";
		}
		
		Iterable<Activity> activities = null;
		if(!isMarker){
			activities = activityRepository.search(StringUtils.trimWhitespace(city), 
					StringUtils.trimWhitespace(category), startTime, endTime,
						StringUtils.trimWhitespace(summaryLike), pageable);
		}else{
			activities = activityRepository.searchMarker( StringUtils.trimWhitespace(city), 
					StringUtils.trimWhitespace(category), startTime, endTime,
						StringUtils.trimWhitespace(summaryLike), pageable);
		}
		return activities;
	}
	
	@RequestMapping(value="/search/marker", method=RequestMethod.GET)
	public @ResponseBody AjaxResult marker(Model model, HttpServletRequest request){
		if(!ajaxUtil.isAjaxRequest(request)){
			throw new ResourceNotFoundException();
		}
		Iterable<Activity> activities = doSearch(request, true);
		Collection<MarkerVo> markers = new ArrayList<MarkerVo>();
		if(activities!=null){
			for(Activity activity : activities){
				MarkerVo vo = MarkerVo.from(activity);
				if(vo!=null){
					markers.add(MarkerVo.from(activity));
				}
			}
		}
		return new AjaxResult(AjaxResultCode.SUCCESS, markers);
	}
}