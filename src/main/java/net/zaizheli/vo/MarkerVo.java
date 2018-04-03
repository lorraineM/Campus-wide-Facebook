package net.zaizheli.vo;

import java.io.Serializable;
import java.util.Date;

import net.zaizheli.constants.ApplicationConfig;
import net.zaizheli.domains.Activity;
import net.zaizheli.domains.User;


@SuppressWarnings("serial")
public class MarkerVo implements Serializable {
	
	private String activityId;
	private String name;
	private String summary;
	private String placeId;
	private String placeAddr;
	private String imageUrl;
	private int imageHeight;
	private Date createdAt;
	private String createdById;
	private String createdByName;
	private String createdByAvatarUrl;
	private String category;
	private Double[] lngLat;
	
	public String getSpotId() {
		return activityId;
	}
	public void setSpotId(String activityId) {
		this.activityId = activityId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getPlaceId() {
		return placeId;
	}
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	public String getPlaceAddr() {
		return placeAddr;
	}
	public void setPlaceAddr(String placeAddr) {
		this.placeAddr = placeAddr;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getImageHeight() {
		return imageHeight;
	}
	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getCreatedById() {
		return createdById;
	}
	public void setCreatedById(String createdById) {
		this.createdById = createdById;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public String getCreatedByAvatarUrl() {
		return createdByAvatarUrl;
	}
	public void setCreatedByAvatarUrl(String createdByAvatarUrl) {
		this.createdByAvatarUrl = createdByAvatarUrl;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Double[] getLngLat() {
		return lngLat;
	}
	public void setLngLat(Double[] lngLat) {
		this.lngLat = lngLat;
	}
	
	public static MarkerVo from(Activity activity){
		if(activity == null || activity.getLngLat()==null ||
				activity.getLngLat().length!=2) return null;
		MarkerVo vo = new MarkerVo();
		vo.setSpotId(activity.getId());
		vo.setName(activity.getTitle());
		vo.setCategory(activity.getType());
		vo.setLngLat(activity.getLngLat());
		vo.setCreatedAt(activity.getCreatedAt());
		if(activity.getPlace()!=null){
			vo.setPlaceId(activity.getPlace().getId());
			vo.setPlaceAddr(new StringBuilder()
				.append(activity.getPlace().getCity())
				.append(activity.getPlace().getDistrict())
				.append(activity.getPlace().getStreet())
				.toString());
		}
		if(activity.getImage()!=null){
			vo.setImageUrl(new StringBuilder()
				.append(ApplicationConfig.base)
				.append(ApplicationConfig.imageRefer)
				.append("/")
				.append(activity.getImage().getResId())
				.toString());
			if(activity.getImage().getOrgSize()!=null &&
					activity.getImage().getOrgSize().length==2){
				vo.setImageHeight(Math.round(192*activity.getImage().getOrgSize()[0]/
						activity.getImage().getOrgSize()[1]));
			}
			
		}
		if(activity.getCreatedBy()!=null){
			User user = activity.getCreatedBy();
			vo.setCreatedById(user.getId());
			vo.setCreatedByName(user.getName());
			if(user.getAvatar()!=null){
				vo.setCreatedByAvatarUrl(new StringBuilder()
					.append(ApplicationConfig.base)
					.append(ApplicationConfig.imageRefer)
					.append("/")
					.append(user.getAvatar().getResId())
					.toString());
			}
		}
		return vo;
	}
}
