package net.zaizheli.vo;

import java.io.Serializable;
import java.util.Date;

import net.zaizheli.constants.ActionType;
import net.zaizheli.constants.ByType;
import net.zaizheli.domains.Action;
import net.zaizheli.domains.Activity;
import net.zaizheli.domains.User;

@SuppressWarnings("serial")
public class ActionVo implements Serializable{
	
	private String id;
	private ActionType type;
	private ByType by;
	private String content;
	private Activity targetActivity;
	private User targetUser;
	private User owner;
	private ActionVo basedOn;
	private Date createdAt;
	private int commentedCount;
	private int forwardedCount;
	private String picId;
	
	public String getPicId() {
		return picId;
	}
	public void setPicId(String picId) {
		this.picId = picId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ActionType getType() {
		return type;
	}
	public void setType(ActionType type) {
		this.type = type;
	}
	public ByType getBy() {
		return by;
	}
	public void setBy(ByType by) {
		this.by = by;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getTargetUser() {
		return targetUser;
	}
	public void setTargetUser(User targetUser) {
		this.targetUser = targetUser;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public ActionVo getBasedOn() {
		return basedOn;
	}
	public void setBasedOn(ActionVo basedOn) {
		this.basedOn = basedOn;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public int getCommentedCount() {
		return commentedCount;
	}
	public void setCommentedCount(int commentedCount) {
		this.commentedCount = commentedCount;
	}
	public int getForwardedCount() {
		return forwardedCount;
	}
	public void setForwardedCount(int forwardedCount) {
		this.forwardedCount = forwardedCount;
	}
	public Activity getTargetActivity() {
		return targetActivity;
	}
	public void setTargetActivity(Activity targetActivity) {
		this.targetActivity = targetActivity;
	}
	
	public static ActionVo from(Action action){
		if(action==null) return null;
		ActionVo vo = new ActionVo();
		vo.setBy(action.getBy());
		vo.setId(action.getId());
		vo.setCommentedCount(action.getCommentedCount());
		vo.setContent(action.getContent());
		vo.setCreatedAt(action.getCreatedAt());
		vo.setForwardedCount(action.getForwardedCount());
		vo.setType(action.getType());
		vo.setPicId(action.getPicId());
		return vo;
	}
}
