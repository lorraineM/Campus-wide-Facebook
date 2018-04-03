package net.zaizheli.domains;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;


import net.zaizheli.constants.ActionType;
import net.zaizheli.constants.ByType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document
public class Action implements Serializable {
	
	@Id
	private String id;
	@NotNull
	private ActionType type;
	@NotNull
	private ByType by;
	private String content;
	private String targetActivity;
	private String targetUser;
	private String basedOn;
	@NotNull
	private String owner;
	@NotNull
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
	public String getTargetActivity() {
		return targetActivity;
	}
	public void setTargetActivity(String targetActivity) {
		this.targetActivity = targetActivity;
	}
	public String getTargetUser() {
		return targetUser;
	}
	public void setTargetUser(String targetUser) {
		this.targetUser = targetUser;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
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
	public String getBasedOn() {
		return basedOn;
	}
	public void setBasedOn(String basedOn) {
		this.basedOn = basedOn;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(id)
				.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}else if(!(obj instanceof Activity)){
			return false;
		}
		return new EqualsBuilder()
				.append(id, ((Activity)obj).getId())
				.isEquals();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(owner)
				.append(type)
				.append(content)
				.toString();
	}
}
