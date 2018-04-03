package net.zaizheli.vo;

import java.io.Serializable;
import java.util.List;

import net.zaizheli.constants.Gender;
import net.zaizheli.domains.Activity;
import net.zaizheli.domains.Application;
import net.zaizheli.domains.User;
import net.zaizheli.web.utils.DomainObjectUtil;

@SuppressWarnings("serial")
public class PinUserVo implements Serializable {
	
	private String userId;
	private String city;
	private Gender gender;
	private String name;
	private boolean avatarUnset;
	private String avatarUrl;
	private int fansCount;
	private int followCount;
	private int activityCount;
	private String summary;
	private List<Activity> activities; 
	private Application application;
	
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAvatarUnset() {
		return avatarUnset;
	}
	public void setAvatarUnset(boolean avatarUnset) {
		this.avatarUnset = avatarUnset;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public int getFansCount() {
		return fansCount;
	}
	public void setFansCount(int fansCount) {
		this.fansCount = fansCount;
	}
	public int getFollowCount() {
		return followCount;
	}
	public void setFollowCount(int followCount) {
		this.followCount = followCount;
	}
	public int getActivityCount() {
		return activityCount;
	}
	public void setActivityCount(int activityCount) {
		this.activityCount = activityCount;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public List<Activity> getActivities() {
		return activities;
	}
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	
	public static PinUserVo from(User user, List<Activity> activities){
		if(user==null) return null;
		PinUserVo vo = new PinUserVo();
		vo.setUserId(user.getId());
		vo.setCity(user.getCity());
		vo.setGender(user.getGender());
		vo.setFansCount(user.getFansCount());
		vo.setFollowCount(user.getFollowCount());
		vo.setActivityCount(user.getActivityCount());
		vo.setName(user.getName());
		vo.setSummary(user.getSummary());
		if(user.getAvatar()==null){
			vo.setAvatarUnset(true);
		}
		vo.setAvatarUrl(DomainObjectUtil.getAvatarUrl(
			user.getAvatar(), user.getGender()));
		if(activities!=null){
			vo.setActivities(activities);
		}
		return vo;
	}
	
}
