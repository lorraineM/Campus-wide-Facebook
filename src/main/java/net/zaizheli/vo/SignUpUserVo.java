package net.zaizheli.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
//import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("serial")
public class SignUpUserVo implements Serializable {
	
	@NotBlank
	private String name;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String password;
	private String passwordRe;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordRe() {
		return passwordRe;
	}
	public void setPasswordRe(String passwordRe) {
		this.passwordRe = passwordRe;
	}
}
