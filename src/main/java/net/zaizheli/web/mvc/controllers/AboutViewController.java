package net.zaizheli.web.mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AboutViewController {

	@RequestMapping(value = "/about", method = RequestMethod.GET)	
	private String toMap( Model model, 
			HttpServletRequest request) {
		return "about";
	}
}
