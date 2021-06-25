package com.task.service.rest.service;

import java.util.List;

import com.task.service.rest.dto.GenericResponse;
import com.task.service.rest.dto.TaskRequest;
import com.task.service.rest.dto.TaskResponse;

public interface TaskService {

    public GenericResponse<List<TaskResponse>> getTasks();
    
    public GenericResponse<TaskResponse> findById(Long id);
    
    public GenericResponse<TaskResponse> create(TaskRequest request);
    
    public GenericResponse<TaskResponse> update(TaskRequest request, Long id);
    
    public GenericResponse<Void> delete(Long id);
    
}
