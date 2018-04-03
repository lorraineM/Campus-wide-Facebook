package net.zaizheli.web.mvc.controllers;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.zaizheli.domains.User;
import net.zaizheli.repositories.UserRepository;
import net.zaizheli.vo.ForgetPasswordVo;
import net.zaizheli.web.utils.EncryptUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ForgetPasswordController {
	@Autowired
	private JavaMailSender sender; 	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EncryptUtil encryptUtil;
	
	@RequestMapping(value="/password", method=RequestMethod.GET)
	public String signUp(Model model, HttpSession session){
		return "password";
	}
	
	@RequestMapping(value = "/password", method = RequestMethod.POST)
	public String sendMail(@Valid ForgetPasswordVo forgetPasswordVo, BindingResult result) {
		User existed=null;
		String password=KeyGenerators.string().generateKey();
		password=password.substring(0, 8);
		if(!result.hasFieldErrors("email")) {
			existed=userRepository.getByEmail(forgetPasswordVo.getEmail());
			if(existed==null) {
				result.addError(new FieldError("forgetPasswordVo","email","邮箱不存在哦，(>_<)"));
				return "password";
			}
		}
		if(result.hasErrors()) return "password";
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		MimeMessage mailMessage = senderImpl.createMimeMessage();	
		try {
		   MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true,"utf-8");
		   messageHelper.setTo(forgetPasswordVo.getEmail()); 
		   messageHelper.setFrom("password_zaizheli@163.com");
		   messageHelper.setSubject("您在在浙里的新密码");		   
		   messageHelper.setText("<html><head></head><body><p><b>"+existed.getName()+"，</b></p>&nbsp;&nbsp;&nbsp;&nbsp;您好!</p>"+
		   		                 "</p><p>&nbsp;&nbsp;&nbsp;&nbsp;您在在浙里平台的新密码为"+password+
		   		                 "。</p><p>&nbsp;&nbsp;&nbsp;&nbsp;您可以登录后进行密码修改。下次不要忘记了~</p>"+
		   		                 "</p><p>&nbsp;&nbsp;&nbsp;&nbsp;谢谢您对在浙里平台的支持！</p></br></br><p><b>"+
		   		                 "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
		   		                 "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
		   		                 "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
		   		                 "在浙里开发团队</b></p></body></html>",true);
		   sender.send(mailMessage);
		} catch (Exception e) {
			result.addError(new FieldError("forgetPasswordVo","email","啊啊，邮件发送失败(请确认邮件地址是否合法或继续加油，再试一次~)"));
			e.printStackTrace(); 
			return "password";
		}
		
		password=encryptUtil.encrypt(password);
		userRepository.set(existed.getId(), "password", password);
		result.addError(new FieldError("forgetPasswordVo","email","已发送新密码到邮箱，去查收吧~"));
		return "password";
	}
		 
}
