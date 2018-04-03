package net.zaizheli.domains;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;


import net.zaizheli.vo.CommentFormBean;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document
public class Comment implements Serializable {

	@Id
	private String id;
	@NotEmpty
	private String content;
	@NotNull
	@DBRef
	private Activity activity;
	@NotNull
	@DBRef
	private User createdBy;
	@DBRef
	private User replyTo;
	@NotNull
	private Date createdAt;
	@NotNull
	private int floor;               //第几楼
	private int agreeCount;

	
	public User getReplyTo() {
		return replyTo;
	}
	public void setReplyTo(User replyTo) {
		this.replyTo = replyTo;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public int getAgreeCount() {
		return agreeCount;
	}
	public void setAgreeCount(int agreeCount) {
		this.agreeCount = agreeCount;
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
		}else if(!(obj instanceof Comment)){
			return false;
		}
		return new EqualsBuilder()
				.append(id, ((Comment)obj).getId())
				.isEquals();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(activity)
				.append(createdBy)
				.append(createdAt)
				.toString();
	}
	
	public static Comment from(CommentFormBean formBean,
			User signInUser){
		if(formBean==null || signInUser==null) return null;
		Comment cmt = new Comment();
		cmt.setCreatedBy(signInUser);
		cmt.setCreatedAt(new Date());
		cmt.setAgreeCount(0);
		return cmt;
	}
	
	
}
