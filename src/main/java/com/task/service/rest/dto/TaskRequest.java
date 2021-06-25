package com.task.service.rest.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TaskRequest {
	@NotNull
	@NotEmpty
	private String description;
	@NotNull
	private Date creationDate;
	
	public TaskRequest() {}
	
	public TaskRequest(String description, Date creationDate) {
		super();
		this.description = description;
		this.creationDate = creationDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
