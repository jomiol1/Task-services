package com.task.service.rest.dto;

import java.util.Date;

public class TaskResponse {
	
	private Long id;
	private String description;
	private Date creationDate;
	
	public TaskResponse(Long id, String description, Date creationDate) {
		super();
		this.id = id;
		this.description = description;
		this.creationDate = creationDate;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
