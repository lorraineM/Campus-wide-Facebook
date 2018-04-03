package net.zaizheli.web.mvc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import net.zaizheli.domains.User;
import net.zaizheli.web.utils.SessionUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MessageController {
	
	@Autowired
	private SessionUtil sessionUtil;	
	
//	@RequestMapping(value="/message/cmt", method=RequestMethod.GET)
//	public String cmt(Model model, 
//			HttpServletRequest request, HttpSession session){
//		User user = sessionUtil.getSignInUser(session);
//		if(user == null){
//			return "redirect:/signin";
//		}
//		model.addAttribute("user", user);
//		return "message/cmt";
//		
//	}
	
	@RequestMapping(value="/message/pm", method=RequestMethod.GET)
	public String pm(Model model, 
			HttpServletRequest request, HttpSession session){
		User user = sessionUtil.getSignInUser(session);
		if(user == null){
			return "redirect:/signin";
		}
		model.addAttribute("user", user);
		model.addAttribute("no", 1);
		return "message/pm";
	}
	
	@RequestMapping(value="/message/pm/to/{no}", method=RequestMethod.GET)
	public String pmTo(@PathVariable int no, Model model, 
			HttpServletRequest request, HttpSession session){
		User user = sessionUtil.getSignInUser(session);
		if(user == null){
			return "redirect:/signin";
		}
		model.addAttribute("user", user);
		model.addAttribute("no", no);
		return "message/pm";
	}
		
	@RequestMapping(value="/message/pm/from/{no}", method=RequestMethod.GET)
	public String pmFrom(@PathVariable int no, Model model, 
			HttpServletRequest request, HttpSession session){
		User user = sessionUtil.getSignInUser(session);
		if(user == null){
			return "redirect:/signin";
		}
		model.addAttribute("user", user);
		model.addAttribute("no", no);
		return "message/pm.from";
	}	
	
	@RequestMapping(value="/message/pm/{no}", method=RequestMethod.GET)
	public String pmP(@PathVariable int no, Model model, 
			HttpServletRequest request, HttpSession session){
		User user = sessionUtil.getSignInUser(session);
		if(user == null){
			return "redirect:/signin";
		}
		model.addAttribute("user", user);
		model.addAttribute("no", no);
		return "message/pm";
	}
	
	@RequestMapping(value="/message/inform", method=RequestMethod.GET)
	public String inform(Model model, 
			HttpServletRequest request, HttpSession session){
		User user = sessionUtil.getSignInUser(session);
		if(user == null){
			return "redirect:/signin";
		}
		model.addAttribute("user", user);
		model.addAttribute("no", 1);
		return "message/inform";
	}
	
	@RequestMapping(value="/message/inform/{no}", method=RequestMethod.GET)
	public String informP(@PathVariable  int no, Model model, 
			HttpServletRequest request, HttpSession session){
		User user = sessionUtil.getSignInUser(session);
		if(user == null){
			return "redirect:/signin";
		}
		model.addAttribute("user", user);
		model.addAttribute("no", no);
		return "message/inform";
	}
	
	@RequestMapping(value="/message/atme", method=RequestMethod.GET)
	public String atme(Model model, 
			HttpServletRequest request, HttpSession session){
		User user = sessionUtil.getSignInUser(session);
		if(user == null){
			return "redirect:/signin";
		}
		model.addAttribute("user", user);
		model.addAttribute("no", 1);
		return "message/atme";
	}
	
	@RequestMapping(value="/message/atme/{no}", method=RequestMethod.GET)
	public String atme(@PathVariable  int no, Model model, 
			HttpServletRequest request, HttpSession session){
		User user = sessionUtil.getSignInUser(session);
		if(user == null){
			return "redirect:/signin";
		}
		model.addAttribute("user", user);
		model.addAttribute("no", no);
		return "message/atme";
	}
	
	@RequestMapping(value="/message/atme", method=RequestMethod.POST)
	public String atmeP(Model model, 
			HttpServletRequest request, HttpSession session){
		return atme(model, request, session);	
	}
	
//	@RequestMapping(value="/message/cmt", method=RequestMethod.POST)
//	public String cmtP(Model model, 
//			HttpServletRequest request, HttpSession session){
//		return cmt(model, request, session);	
//	}
	
	@RequestMapping(value="/message/pm", method=RequestMethod.POST)
	public String pmP(Model model, 
			HttpServletRequest request, HttpSession session){
		return pm(model, request, session);	
	}
	
	@RequestMapping(value="/message/inform", method=RequestMethod.POST)
	public String informP(Model model, 
			HttpServletRequest request, HttpSession session){
		return inform(model, request, session);	
	}

}
