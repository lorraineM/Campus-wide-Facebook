package net.zaizheli.vo.formbean;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
public class UserBasicInfoFormBean implements Serializable {
	
	@NotEmpty
	private String name;
	private String gender;
	private String city;
	private String summary;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
}
