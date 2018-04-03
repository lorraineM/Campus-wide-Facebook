package net.zaizheli.web.mvc.controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.zaizheli.constants.ApplicationConfig;
import net.zaizheli.domains.Comment;
import net.zaizheli.repositories.ActivityRepository;
import net.zaizheli.repositories.CommentRepository;
import net.zaizheli.vo.PageVo;
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
public class ViewActivityCommentController {
	@Autowired
	ActivityRepository activityRepository;
	@Autowired 
	SessionUtil sessionUtil;
	@Autowired
	CommentRepository commentRepository;
	
	@RequestMapping(value="/{id}/comments/{no}", method=RequestMethod.GET)
	public String view(@PathVariable String id, @PathVariable int no, Model model, 
			HttpServletRequest request, HttpSession session){
		Pageable pageable = new PageRequest(Math.max(no-1, 0), 
				ApplicationConfig.cmtPageSize, 
				new Sort(new Order(Direction.ASC, "floor")));
		Page<Comment> comments = commentRepository.findByActivity(id, pageable);
		int size = commentRepository.findByActivity(id).size();
		int total = 0 ;
		if (size % ApplicationConfig.cmtPageSize == 0)
	      total = (int)(size/ ApplicationConfig.cmtPageSize);
		else {
			total = (int)(size/ ApplicationConfig.cmtPageSize)+1;
		}
		PageVo pageVo = new PageVo();
		pageVo.setSize(ApplicationConfig.cmtPageSize);
		pageVo.setNumber(no);
		pageVo.setTotalPages(total);
		pageVo.setContent(comments.getContent());
		model.addAttribute("comments", comments.getContent());
		model.addAttribute("pageVo",pageVo);
		return "activity/single_comment";
	}

}
