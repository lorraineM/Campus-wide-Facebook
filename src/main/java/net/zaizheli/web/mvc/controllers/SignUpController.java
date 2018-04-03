package net.zaizheli.web.mvc.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.zaizheli.constants.ApplicationConstants;
import net.zaizheli.domains.User;
import net.zaizheli.repositories.UserRepository;
import net.zaizheli.vo.SignUpUserVo;
import net.zaizheli.web.utils.EncryptUtil;
import net.zaizheli.web.utils.SessionUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SignUpController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SessionUtil sessionUtil;
	@Autowired
	private EncryptUtil encryptUtil;
	
	
	@ModelAttribute("signUpUserVo")
	public SignUpUserVo createSignUpUserVo() {
		SignUpUserVo vo = new SignUpUserVo();
		return vo;
	}

	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signUp(Model model, HttpSession session){
		if(session.getAttribute(ApplicationConstants.SESSION_SIGNIN_USER)!=null){
			return "redirect:/";
		}
		model.addAttribute(ApplicationConstants.SESSION_LAST_VISITED_URL, 
				sessionUtil.getLastVisitedUrl(session));
		return "sign.up";
	}

	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signUp(@Valid SignUpUserVo signUpUserVo,
			BindingResult result,
			Model model, HttpSession session){
		User existed = null;
		if(!result.hasFieldErrors("email")){
			existed = userRepository.getByEmail(signUpUserVo.getEmail());
			if(existed !=null){
				result.addError(new FieldError("signUpUserVo", "email", "这个邮箱已经用过了,换一个吧   (￣ω￣)"));
			}
		}
		if(!result.hasFieldErrors("name")){
			existed = userRepository.getByName(signUpUserVo.getName());
			if(existed != null){
				result.addError(new FieldError("signUpUserVo", "name", "这个昵称太抢手了,换一个吧   (￣ω￣)"));
			}
		}
		if(result.hasErrors()){
			return "sign.up";
		}
		String password=encryptUtil.encrypt(signUpUserVo.getPassword());
		signUpUserVo.setPassword(password);
		session.setAttribute(ApplicationConstants.SESSION_SIGNIN_USER, 
				userRepository.save(User.from(signUpUserVo)));
		return "redirect:" + sessionUtil.getLastVisitedUrl(session);
	}
}
