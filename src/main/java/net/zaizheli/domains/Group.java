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
public class Group implements Serializable{
	@Id
	private String id;
	@NotNull
	private String name;
	@NotNull
	private int num;
	@DBRef
	@NotNull
	private User createdBy;
	private Date createdAt;
	private String summary;
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
					.append(id, ((Group)obj).getId())
					.isEquals();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
					.append(name)
					.toString();
	}
	
}
