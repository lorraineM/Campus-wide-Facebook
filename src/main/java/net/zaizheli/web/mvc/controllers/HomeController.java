package net.zaizheli.web.mvc.controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.zaizheli.repositories.CityMetaRepository;
import net.zaizheli.vo.FilterElementVo;
import net.zaizheli.web.utils.SessionUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@Autowired
	private CityMetaRepository cityMetaRepository;
	@Autowired
	private SessionUtil sessionUtil;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	private String toHome(Model model,
			HttpServletRequest request, HttpSession session) {
		String city= null;
		if (request.getParameter("city") != null) {
			city = new String(request.getParameter("city"));
		}
		String category = null;
		if (request.getParameter("category") != null) {
			category = new String(request.getParameter("category"));
		}	
		String startTime = null;
		if (request.getParameter("startTime") != null) {
			startTime = new String(request.getParameter("startTime"));
		}
		String endTime = null;
		if (request.getParameter("endTime") != null) {
			endTime = new String(request.getParameter("endTime"));
		}
		String keyword = null;
		if (request.getParameter("keyword") != null) {
			keyword = new String(request.getParameter("keyword"));
		}
	
		Collection<FilterElementVo> filters = new ArrayList<FilterElementVo>();
		StringBuilder sb = new StringBuilder();
		FilterElementVo filter = null;

		// set city filter
		filter = new FilterElementVo();
		filter.setType("city");
		filter.setTypeLabel("在哪里");
		filter.setValue("");
		filter.setLabel("无所谓");
		if (StringUtils.hasText(city)) {
			filter.setLabel(city);
			filter.setValue(city);
		}
		filters.add(filter);
		sb.append("city=").append(filter.getValue()).append("&");

		// set category filter
		filter = new FilterElementVo();
		filter.setType("category");
		filter.setTypeLabel("分类");
		filter.setValue("");
		filter.setLabel("全部");
		if (StringUtils.hasText(category)) {
			filter.setLabel(category);
			filter.setValue(category);
		}
		filters.add(filter);
		sb.append("category=").append(filter.getValue()).append("&");
		
		//set startTime filter
		filter = new FilterElementVo();
		filter.setType("startTime");
		filter.setTypeLabel("起始时间");
		filter.setValue("");
		filter.setLabel("");
		if (StringUtils.hasText(startTime)) {
			filter.setLabel(startTime);
			filter.setValue(startTime);
		}
		filters.add(filter);
		sb.append("startTime=").append(filter.getValue()).append("&");
		
		//set endTime filter
		filter = new FilterElementVo();
		filter.setType("endTime");
		filter.setTypeLabel("终止时间");
		filter.setValue("");
		filter.setLabel("");
		if (StringUtils.hasText(endTime)) {
			filter.setLabel(endTime);
			filter.setValue(endTime);
		}
		filters.add(filter);
		sb.append("endTime=").append(filter.getValue()).append("&");

		// set keyword filter
		filter = new FilterElementVo();
		filter.setType("keyword");
		filter.setTypeLabel("关键词");
		filter.setValue("");
		filter.setLabel("未选择");
		if (StringUtils.hasText(keyword)) {
			filter.setLabel(keyword);
			filter.setValue(keyword);
		}
		filters.add(filter);
		sb.append("keyword=").append(filter.getValue()).append("&");

		model.addAttribute("filters", filters);
		model.addAttribute("qStr", sb.toString());
		return "home";
	}

}
