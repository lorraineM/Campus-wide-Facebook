package net.zaizheli.domains;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import net.zaizheli.constants.ApplicationStatus;
import net.zaizheli.vo.ApplicationVo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document
public class Application implements Serializable {
	@Id
	private String id;
	@DBRef
	@NotNull
	@Indexed
	private Activity activity;
	@DBRef
	@NotNull
	private User applicant;
	private Date createdAt;
	private Date updatedAt;
	@NotNull
	private ApplicationStatus status;  
	private String realName;	
	private int age;
	private String birthday;
	private String tel;
	private String city;
	private String address;
	private String school;	
	private String note;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public User getApplicant() {
		return applicant;
	}
	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public ApplicationStatus getStatus() {
		return status;
	}
	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public static Application from(ApplicationVo vo) {
		if (vo == null) return null;
		Application app = new Application();
		app.setRealName(vo.getRealName());
		app.setAddress(vo.getAddress());
		app.setAge(app.getAge());
		app.setBirthday(vo.getBirthday());
		app.setCity(vo.getCity());
		app.setCreatedAt(new Date());
		app.setNote(vo.getNote());
		app.setSchool(vo.getSchool());
		app.setTel(vo.getTel());
		app.setUpdatedAt(app.getCreatedAt());
		return app;
	}
	
	
}
