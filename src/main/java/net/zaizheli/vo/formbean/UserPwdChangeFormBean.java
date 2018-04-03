package net.zaizheli.vo.formbean;

import java.io.Serializable;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("serial")

public class UserPwdChangeFormBean implements Serializable {

	@NotBlank
	private String oldPwd;
	@NotBlank
	@Length(min = 6, max = 30)
	private String newPwd;
	@NotBlank
	private String newPwdRe;
	
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public String getNewPwdRe() {
		return newPwdRe;
	}
	public void setNewPwdRe(String newPwdRe) {
		this.newPwdRe = newPwdRe;
	}
}
