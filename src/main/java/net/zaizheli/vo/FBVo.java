package net.zaizheli.vo;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document
public class FBVo implements Serializable{
	
	private String fb_mail;	
	private String fb_content;
	public String getFb_mail() {
		return fb_mail;
	}
	public void setFb_mail(String fb_mail) {
		this.fb_mail = fb_mail;
	}
	public String getFb_content() {
		return fb_content;
	}
	public void setFb_content(String fb_content) {
		this.fb_content = fb_content;
	}
	
	
	
	

		
}
