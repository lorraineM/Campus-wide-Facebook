package net.zaizheli.vo;

import java.io.Serializable;
import java.util.Date;

import net.zaizheli.constants.ApplicationConfig;
import net.zaizheli.domains.Action;
import net.zaizheli.domains.Activity;
import net.zaizheli.domains.CityMeta;
import net.zaizheli.domains.User;
import net.zaizheli.web.utils.DomainObjectUtil;


@SuppressWarnings("serial")
public class PinVo implements Serializable {
	
	private String activityId;
	private String actId;
	private String title;
	private String placeId;
	private String placeAddr;
	private String city;
	private String addr;
	private String imageUrl;
	private int imageHeight;
	private Date createdAt;
	private String createdById;
	private String createdByName;
	private String createdByAvatarUrl;
	private String type;
	private Double[] lngLat;;
	private long commentCount;
	private long trackCount;
	private long shareCount;
	private long agreeCount;
	private int currentNum;           //当前参与人数
	
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(long commentCount) {
		this.commentCount = commentCount;
	}
	public long getTrackCount() {
		return trackCount;
	}
	public void setTrackCount(long trackCount) {
		this.trackCount = trackCount;
	}
	public long getShareCount() {
		return shareCount;
	}
	public void setShareCount(long shareCount) {
		this.shareCount = shareCount;
	}
	public long getAgreeCount() {
		return agreeCount;
	}
	public void setAgreeCount(long agreeCount) {
		this.agreeCount = agreeCount;
	}
	public int getCurrentNum() {
		return currentNum;
	}
	public void setCurrentNum(int currentNum) {
		this.currentNum = currentNum;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getActId() {
		return actId;
	}
	public void setActId(String actId) {
		this.actId = actId;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public Double[] getLngLat() {
		return lngLat;
	}
	public void setLngLat(Double[] lngLat) {
		this.lngLat = lngLat;
	}
	
	
	public static PinVo from(Activity activity, CityMeta cityMeta,
			Action act){
		if(activity == null) return null;
		PinVo vo = new PinVo();
		vo.setActivityId(activity.getId());
		if(act != null){
			vo.setActId(act.getId());
		}
		vo.setTitle(activity.getTitle());
		vo.setType(activity.getType());
		vo.setLngLat(activity.getLngLat());
		vo.setCreatedAt(activity.getCreatedAt());
		vo.setCity(activity.getCity());
		vo.setCurrentNum(activity.getCurrentNum());
		vo.setCommentCount(activity.getCommentCount());
		vo.setTrackCount(activity.getTrackCount());
		vo.setShareCount(activity.getShareCount());
		vo.setAgreeCount(activity.getAgreeCount());
		vo.setAddr(activity.getAddr());
		if(activity.getPlace()!=null){
			vo.setPlaceId(activity.getPlace().getId());
			vo.setPlaceAddr(new StringBuilder()
				.append(activity.getPlace().getCity())
				.append(activity.getPlace().getDistrict())
				.append(activity.getPlace().getStreet())
				.toString());
		}else if(cityMeta!=null){
			vo.setPlaceAddr(cityMeta.getName());
			vo.setCity(cityMeta.getPinyin());
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
			vo.setCreatedByAvatarUrl(
				DomainObjectUtil.getAvatarUrl(user.getAvatar(), 
					user.getGender()));
		}
		return vo;
	}
}
