package net.zaizheli.web.mvc.controllers;


import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.zaizheli.constants.AjaxResultCode;

import net.zaizheli.domains.User;
import net.zaizheli.vo.AjaxResult;
import net.zaizheli.vo.FBVo;
import net.zaizheli.web.utils.SessionUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/feedback")
public class SendFeedbackController {
	
	@Autowired
	private JavaMailSender sender; 
	@Autowired
	SessionUtil sessionUtil;
	
	@ModelAttribute("FBVo")
	public FBVo FBVo() {
		FBVo vo = new FBVo();
		return vo;
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public @ResponseBody AjaxResult create(@Valid FBVo vo, Model model, HttpServletRequest request, HttpSession session){	
		User signInUser=sessionUtil.getSignInUser(session);
		String mail=null,user=null;
		if (signInUser != null ) {
			mail = signInUser.getEmail();
			user = signInUser.getName();
		}
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		MimeMessage mailMessage = senderImpl.createMimeMessage();
		boolean flag = true;
		while (flag) {
			flag = false;
			try {
			   MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true,"utf-8");
			   messageHelper.setTo("blue.snow013@163.com"); 
			   messageHelper.setCc(InternetAddress.parse("<hfanglei@hotmail.com>,<yingwenjingdie@qq.com>"));  
			   messageHelper.setFrom("password_zaizheli@163.com");
			   messageHelper.setSubject("来自"+vo.getFb_mail()+"的 在浙里 的反馈" );		   
			   messageHelper.setText("<html><head></head><body><p><b>反馈信息：</b></p></p>"+
			   		                 "<p>&nbsp;&nbsp;&nbsp;&nbsp;"+vo.getFb_content()+
			   		                 "</p><br><p>额外信息(若反馈的为登录用户）：</p><p>信箱:"+ mail +
			   		                 "</p>昵称:" + user +"</p></body></html>",true);
			   sender.send(mailMessage);
			} catch (Exception e) {			
				flag = true;
			}
		}
		return new AjaxResult(AjaxResultCode.SUCCESS);
	}

}
