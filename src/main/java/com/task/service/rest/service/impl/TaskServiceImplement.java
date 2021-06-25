package com.task.service.rest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.service.constant.StatusCode;
import com.task.service.model.entity.TaskEntity;
import com.task.service.model.repository.TaskRepository;
import com.task.service.rest.dto.GenericResponse;
import com.task.service.rest.dto.TaskRequest;
import com.task.service.rest.dto.TaskResponse;
import com.task.service.rest.service.TaskService;

@Service
public class TaskServiceImplement implements TaskService{
	
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public GenericResponse<List<TaskResponse>> getTasks() {
		
		List<TaskEntity> tasks = taskRepository.findByActiveIsTrue();
		
	    GenericResponse<List<TaskResponse>> response = new GenericResponse<>();
	    response.setStatusCode(StatusCode.OK.getCode());
	    response.setMessage(StatusCode.OK.getDescription());
	    
	    if(!tasks.isEmpty()) {
			List<TaskResponse> taskResponse = new ArrayList<TaskResponse>();
			
	    	tasks.forEach(item->{
	    		taskResponse.add(new TaskResponse(item.getId(),item.getDescription(),item.getCreationDate()));
	    	});
	    	
	    	response.setData(taskResponse);
	    	
	    }
		return response;
	}

	@Override
	public GenericResponse<TaskResponse> findById(Long id) {
		
		Optional<TaskEntity> optionalTask = taskRepository.findById(id);
		
	    GenericResponse<TaskResponse> response = new GenericResponse<>();
	    
	    if(!optionalTask.isPresent()) {
	         response.setStatusCode(StatusCode.NOT_FOUND.getCode());
	         response.setMessage(StatusCode.NOT_FOUND.getDescription());
	    }else {
	    	TaskEntity taskEntity = optionalTask.get();
	    	
	        response.setStatusCode(StatusCode.OK.getCode());
	        response.setMessage(StatusCode.OK.getDescription());
	        response.setData(new TaskResponse(taskEntity.getId(),taskEntity.getDescription(),taskEntity.getCreationDate()));
	    }
		
		return response;
	}

	@Override
	public GenericResponse<TaskResponse> create(TaskRequest request) {
		TaskEntity taskEntity = new TaskEntity();
		taskEntity.setDescription(request.getDescription());
		taskEntity.setCreationDate(request.getCreationDate());
		taskEntity.setActive(true);
		
		taskRepository.save(taskEntity);
		
	    GenericResponse<TaskResponse> response = new GenericResponse<>();
	    response.setStatusCode(StatusCode.OK.getCode());
        response.setMessage(StatusCode.OK.getDescription());
        response.setData(new TaskResponse(taskEntity.getId(),taskEntity.getDescription(),taskEntity.getCreationDate()));
		
		
		return response;
	}

	@Override
	public GenericResponse<TaskResponse> update(TaskRequest request, Long id) {
		
		Optional<TaskEntity> optionalTask = taskRepository.findById(id);
		
	    GenericResponse<TaskResponse> response = new GenericResponse<>();
	    
	    if(!optionalTask.isPresent()) {
	         response.setStatusCode(StatusCode.NOT_FOUND.getCode());
	         response.setMessage(StatusCode.NOT_FOUND.getDescription());
	    }else {
	    	TaskEntity taskEntity = optionalTask.get();
			taskEntity.setDescription(request.getDescription());
			taskEntity.setCreationDate(request.getCreationDate());
			
			taskRepository.save(taskEntity);
	    	
	        response.setStatusCode(StatusCode.OK.getCode());
	        response.setMessage(StatusCode.OK.getDescription());
	        response.setData(new TaskResponse(taskEntity.getId(),taskEntity.getDescription(),taskEntity.getCreationDate()));
	    }
		
		return response;
	}

	@Override
	public GenericResponse<Void> delete(Long id) {
		
		Optional<TaskEntity> optionalTask = taskRepository.findById(id);
		
	    GenericResponse<Void> response = new GenericResponse<>();
	    
	    if(!optionalTask.isPresent()) {
	         response.setStatusCode(StatusCode.NOT_FOUND.getCode());
	         response.setMessage(StatusCode.NOT_FOUND.getDescription());
	    }else {
	    	TaskEntity taskEntity = optionalTask.get();
			taskEntity.setActive(false);
			
			taskRepository.save(taskEntity);
	    	
	        response.setStatusCode(StatusCode.OK.getCode());
	        response.setMessage(StatusCode.OK.getDescription());
	    }
		
		return response;
	}

}
