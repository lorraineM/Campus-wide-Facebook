package net.zaizheli.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;


@SuppressWarnings("serial")
public class ActivityCreationVo implements Serializable {
	@NotNull
	private String imageUrl;
	@NotNull
	private String title;
	@NotNull
	private String type;
	@NotNull
	private String startDate;
	@NotNull
	private String startTime;
	@NotNull
	private String endDate;
	@NotNull
	private String endTime;
	@NotNull
	private int num;
	@NotNull
	private String status;
	private String fullAddr;
	private String placeId;
	private String city;
	private String addr;   //地址备注
	private int fee;
	private int apply;
	private boolean realNameReq;	
	private boolean ageReq;
	private boolean birthdayReq;
	private boolean telReq;
	private boolean addressReq;
	private boolean schoolReq;	
	private boolean noteReq;
	@NotNull
	private String editor1;
	
	public boolean isRealNameReq() {
		return realNameReq;
	}
	public void setRealNameReq(boolean realNameReq) {
		this.realNameReq = realNameReq;
	}
	public boolean isAgeReq() {
		return ageReq;
	}
	public void setAgeReq(boolean ageReq) {
		this.ageReq = ageReq;
	}
	public boolean isBirthdayReq() {
		return birthdayReq;
	}
	public void setBirthdayReq(boolean birthdayReq) {
		this.birthdayReq = birthdayReq;
	}
	public boolean isTelReq() {
		return telReq;
	}
	public void setTelReq(boolean telReq) {
		this.telReq = telReq;
	}
	public boolean isAddressReq() {
		return addressReq;
	}
	public void setAddressReq(boolean addressReq) {
		this.addressReq = addressReq;
	}
	public boolean isSchoolReq() {
		return schoolReq;
	}
	public void setSchoolReq(boolean schoolReq) {
		this.schoolReq = schoolReq;
	}
	public boolean isNoteReq() {
		return noteReq;
	}
	public void setNoteReq(boolean noteReq) {
		this.noteReq = noteReq;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFullAddr() {
		return fullAddr;
	}
	public void setFullAddr(String fullAddr) {
		this.fullAddr = fullAddr;
	}
	public String getPlaceId() {
		return placeId;
	}
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public int getApply() {
		return apply;
	}
	public void setApply(int apply) {
		this.apply = apply;
	}
	public String getEditor1() {
		return editor1;
	}
	public void setEditor1(String editor1) {
		this.editor1 = editor1;
	}
	
}
