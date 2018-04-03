package net.zaizheli.domains;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document
public class UserGroupXref implements Serializable {
	@Id
	private String id;
	@DBRef
	@NotNull
	private Group group;
	@DBRef
	@NotNull
	private User user;
	private Date createdAt;
	private Date updatedAt;
	private int status;   // 0: invalid  1:valid
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
					.append(id, ((UserGroupXref)obj).getId())
					.isEquals();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(id).append(group).append(user)
				.toString();
	}
	
}
