package com.task.service.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.task.service.constant.StatusCode;
import com.task.service.rest.dto.GenericResponse;
import com.task.service.rest.dto.TaskRequest;
import com.task.service.rest.dto.TaskResponse;
import com.task.service.rest.service.TaskService;
import com.task.service.util.TestUtil;


@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private TaskService taskService;
	
	private static final Date NOW = java.util.Date.from(Instant.now());
	private static final TaskRequest REQUEST = new TaskRequest("task", NOW);
	private static final TaskResponse RESPONSE = new TaskResponse(1L,"task", NOW);
	
	@Test 
	public void testGetAllOK() throws Exception {
		
		List<TaskResponse> tasks = new ArrayList<>();
		tasks.add(RESPONSE);
		
	     GenericResponse<List<TaskResponse>> response = new GenericResponse<>();
	     response.setStatusCode(StatusCode.OK.getCode());
	     response.setMessage(StatusCode.OK.getDescription()); 
	     response.setData(tasks);
	     
	     Mockito.when(taskService.getTasks()).thenReturn(response);
	     
	     mvc.perform(get("/task/").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isOk())
	     .andExpect(jsonPath("statusCode").value(StatusCode.OK.getCode()));
	     
	}
	
	@Test 
	public void testFindByIdOK() throws Exception {
		
	     GenericResponse<TaskResponse> response = new GenericResponse<>();
	     response.setStatusCode(StatusCode.OK.getCode());
	     response.setMessage(StatusCode.OK.getDescription()); 
	     response.setData(RESPONSE);
	     
	     Mockito.when(taskService.findById(1L)).thenReturn(response);
	     
	     mvc.perform(get("/task/findById/1").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isOk())
	     .andExpect(jsonPath("statusCode").value(StatusCode.OK.getCode()))
	     .andExpect(jsonPath("data.id").value(RESPONSE.getId()))
	     .andExpect(jsonPath("data.description").value(RESPONSE.getDescription()))
	     .andExpect(jsonPath("data.creationDate").value(RESPONSE.getCreationDate().toString()));
	     
	}
	
	@Test 
	public void testCreateOK() throws Exception {
		
	     GenericResponse<TaskResponse> response = new GenericResponse<>();
	     response.setStatusCode(StatusCode.OK.getCode());
	     response.setMessage(StatusCode.OK.getDescription()); 
	     response.setData(RESPONSE);
	     
	     Mockito.when(taskService.create(REQUEST)).thenReturn(response);
	     
	     mvc.perform(post("/task/create").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
	    		 .content(TestUtil.convertObjectToJsonBytes(REQUEST)))
	     .andExpect(status().isOk());
	     
	}
	
	@Test 
	public void testUpdateOK() throws Exception {
		
	     GenericResponse<TaskResponse> response = new GenericResponse<>();
	     response.setStatusCode(StatusCode.OK.getCode());
	     response.setMessage(StatusCode.OK.getDescription()); 
	     response.setData(RESPONSE);
	     
	     Mockito.when(taskService.update(REQUEST, 1L)).thenReturn(response);
	     
	     mvc.perform(put("/task/update/1").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
	    		 .content(TestUtil.convertObjectToJsonBytes(REQUEST)))
	     .andExpect(status().isOk());
	     
	}
	
	@Test 
	public void testDeleteOK() throws Exception {
		
	     GenericResponse<Void> response = new GenericResponse<>();
	     response.setStatusCode(StatusCode.OK.getCode());
	     response.setMessage(StatusCode.OK.getDescription()); 
	     
	     Mockito.when(taskService.delete(1L)).thenReturn(response);
	     
	     mvc.perform(delete("/task/delete/1").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
	    		 .content(TestUtil.convertObjectToJsonBytes(REQUEST)))
	     .andExpect(status().isOk());
	     
	}

}
