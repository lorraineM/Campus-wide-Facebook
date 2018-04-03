package net.zaizheli.domains;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import net.zaizheli.constants.MessageType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document
public class Message implements Serializable{
	@Id
	private String id;
	@DBRef
	@NotNull
	private User from;
	@DBRef
	@NotNull
	private User to;	
	@DBRef
	private Activity activity; 
	@DBRef
	private Message base;
	private String content;
	private String title;
	private MessageType type;
	private int status;          //0:未读             1:已读 
	@NotNull
	private Date createdAt;
	
	public Message getBase() {
		return base;
	}
	public void setBase(Message base) {
		this.base = base;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getTo() {
		return to;
	}
	public void setTo(User to) {
		this.to = to;
	}
	public User getFrom() {
		return from;
	}
	public void setFrom(User from) {
		this.from = from;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
					.append(id).toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj==null) {
			return false;
		} else if(!(obj instanceof Group)) {
			return false;
		}
		return new EqualsBuilder()
					.append(id, ((Message)obj).getId())
					.isEquals();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
					.append(type)
					.toString();
	}
	
}
