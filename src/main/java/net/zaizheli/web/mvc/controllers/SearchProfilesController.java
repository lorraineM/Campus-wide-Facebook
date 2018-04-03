package net.zaizheli.web.mvc.controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.zaizheli.constants.ApplicationConfig;
import net.zaizheli.constants.Gender;
import net.zaizheli.domains.Activity;
import net.zaizheli.domains.User;
import net.zaizheli.repositories.ActivityRepository;
import net.zaizheli.repositories.UserRepository;
import net.zaizheli.vo.PinUserVo;
import net.zaizheli.web.utils.SessionUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

@Controller
@RequestMapping(value = "/profiles")
public class SearchProfilesController {
	private static final Logger logger = 
		LoggerFactory.getLogger(SearchProfilesController.class);

	@Autowired
	private ActivityRepository activityRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	SessionUtil sessionUtil;
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public String search(Model model, HttpServletRequest request, 
			HttpSession session){
//		String city = request.getParameter("city");
		String gender = request.getParameter("gender");
		String nameLike = request.getParameter("keyword");
		int no = Integer.parseInt(request.getParameter("no"));
		Pageable pageable = new PageRequest(Math.max(no, 0), 
				ApplicationConfig.masonryPageSize, 
				new Sort(new Order(Direction.DESC, "createdAt")));
//		if(ApplicationConfig.defaultCityPinyin.equals(city)){
//			city = "";
//		}
		if(StringUtils.hasText(gender)){
			gender = gender.toUpperCase();
			try{
				Gender g = Gender.valueOf(gender);
				gender = g.name();
			}catch(RuntimeException re){
				logger.error(re.getMessage());
			}
		}
		Collection<PinUserVo> pins = new ArrayList<PinUserVo>();
		Page<User> users = null;
//		if(StringUtils.hasText(city) && g != null && StringUtils.hasText(keyword)){
//			users = userRepository.findByCityAndGenderAndNameLike(city, g, keyword, pageable);
//		}else if(StringUtils.hasText(city) && g!=null && !StringUtils.hasText(keyword)){
//			users = userRepository.findByCityAndGender(city, g, pageable);
//		}else if(StringUtils.hasText(city) && g==null && StringUtils.hasText(keyword)){
//			users = userRepository.findByCityAndNameLike(city, keyword, pageable);
//		}else if(!StringUtils.hasText(city) && g!=null && StringUtils.hasText(keyword)){
//			users = userRepository.findByGenderAndNameLike(g, keyword, pageable);
//		}else if(!StringUtils.hasText(city) && g==null && StringUtils.hasText(keyword)){
//			users = userRepository.findByNameLike(keyword, pageable);
//		}else if(!StringUtils.hasText(city) && g!=null && !StringUtils.hasText(keyword)){
//			users = userRepository.findByGender(g, pageable);
//		}else if(StringUtils.hasText(city) && g==null && !StringUtils.hasText(keyword)){
//			users = userRepository.findByCity(city, pageable);
//		}else{
//			users = userRepository.findAll(pageable);
//		}
		users = userRepository.search(
				StringUtils.trimWhitespace(gender), 
				StringUtils.trimWhitespace(nameLike), pageable);
		
		pageable = new PageRequest(Math.max(no, 0), 
				ApplicationConfig.masonryThumbPageSize, 
				new Sort(new Order(Direction.DESC, "updatedAt")));
		if(users!=null){
			for(User user : users){
				Page<Activity> activities = activityRepository.findByCreatedBy(user.getId(), pageable);
				pins.add(PinUserVo.from(user, activities.getContent()));
			}
		}
		model.addAttribute("pins", pins);
		return "profiles/people";
	}
	
}