package net.zaizheli.web.mvc.controllers;

import java.util.Calendar;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.zaizheli.constants.AjaxResultCode;
import net.zaizheli.constants.ApplicationConstants;
import net.zaizheli.constants.UserStatus;
import net.zaizheli.domains.User;
import net.zaizheli.domains.UserPreference;
import net.zaizheli.exceptions.ResourceNotFoundException;
import net.zaizheli.repositories.UserPreferenceRepository;
import net.zaizheli.repositories.UserRepository;
import net.zaizheli.vo.AjaxResult;
import net.zaizheli.vo.SignInCredentialVo;
import net.zaizheli.vo.ValidationEngineError;
import net.zaizheli.web.utils.AjaxUtil;
import net.zaizheli.web.utils.EncryptUtil;
import net.zaizheli.web.utils.SessionUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignInController {

	@Autowired
	private UserRepository userPepository;
	@Autowired
	private UserPreferenceRepository userPreferenceRepository;
	@Autowired
	private SessionUtil sessionUtil;
	@Autowired
	private AjaxUtil ajaxUtil;
	@Autowired
	private EncryptUtil encryptUtil;

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String signIn(HttpSession session) {
		if (session.getAttribute(ApplicationConstants.SESSION_SIGNIN_USER) != null) {
			return "redirect:/";
		}
		return "sign.in";
	}

	@RequestMapping(value = "/checksignin", method = RequestMethod.GET)
	public @ResponseBody
	AjaxResult checkSignIn(HttpServletRequest request, ModelAndView mav,
			HttpSession session) {
		if (!ajaxUtil.isAjaxRequest(request)) {
			throw new ResourceNotFoundException();
		}
		if (sessionUtil.getSignInUser(session) != null) {
			Calendar c = Calendar.getInstance();
			c.setTimeZone(TimeZone.getTimeZone("UTC"));
			return new AjaxResult(AjaxResultCode.SUCCESS, c.getTimeInMillis());
		} else {
			return new AjaxResult(AjaxResultCode.NEED_SIGNIN);
		}
	}

	@RequestMapping(value = "/signin/validate", method = RequestMethod.POST)
	public @ResponseBody
	Object[] validateSignIn(@Valid SignInCredentialVo signInCredentialVo,
			BindingResult result, Model model, HttpSession session) {
		User existed = null;
		if (!result.hasFieldErrors("signInName")) {
			existed = userPepository.getByEmail(signInCredentialVo
					.getSignInName());
			if (existed == null) {
				result.addError(new FieldError("signInCredentialVo",
						"signInName", "邮箱不存在哦，(>_<)"));
			} else {
				if (!encryptUtil.match(signInCredentialVo.getSignInPassword(),
						existed.getPassword())) {
					result.addError(new FieldError("signInCredentialVo",
							"signInPassword", "啊，密码错误了，(>_<)"));
				}
				if(existed.getStatus() == UserStatus.INVALID){
					result.addError(new FieldError("signInCredentialVo",
							"signInName", "你干了什么坏事，被封号了。 (⊙＿⊙) "));
				} 
			}
		}
		if (result.hasErrors()) {
			return ValidationEngineError.normalize(ValidationEngineError
					.from(result));
		}else{
			if (existed != null) {
				session.setAttribute(ApplicationConstants.SESSION_SIGNIN_USER,
						existed);
				UserPreference up = userPreferenceRepository.getByUser(existed);
				if (up != null) {
					sessionUtil.setSignInUserPrefer(up, session);
				}
			}
			return new ValidationEngineError[]{};
		}
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String signIn(@Valid SignInCredentialVo signInCredentialVo,
			BindingResult result, Model model, HttpSession session) {
		User existed = null;
		if (!result.hasFieldErrors("signInName")) {
			existed = userPepository.getByEmail(signInCredentialVo
					.getSignInName());
			if (existed == null) {
				result.addError(new FieldError("signInCredentialVo",
						"signInName", "邮箱不存在哦，(>_<)"));
			} else {
				if (!encryptUtil.match(signInCredentialVo.getSignInPassword(),
						existed.getPassword())) {
					result.addError(new FieldError("signInCredentialVo",
							"signInPassword", "啊，密码错误了，(>_<)"));
				}
				if(existed.getStatus() == UserStatus.INVALID){
					result.addError(new FieldError("signInCredentialVo",
							"signInName", "你干了什么坏事，被封号了 (⊙＿⊙)"));
				}
			}
		}

		if (result.hasErrors()) {
			return "sign.in";
		}

		if (existed != null) {
			session.setAttribute(ApplicationConstants.SESSION_SIGNIN_USER,
					existed);
			UserPreference up = userPreferenceRepository.getByUser(existed);
			if (up != null) {
				sessionUtil.setSignInUserPrefer(up, session);
			}
		}

		return "redirect:/";
	}
}
