package com.task.service.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.service.rest.dto.GenericResponse;
import com.task.service.rest.dto.TaskRequest;
import com.task.service.rest.dto.TaskResponse;
import com.task.service.rest.service.TaskService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/task")
@Api(
        value = "/task",
        produces = "application/json")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
    @GetMapping(
            path = "/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<List<TaskResponse>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getTasks());
    }
    
    @GetMapping(
            path = "/findById/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<TaskResponse>> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findById(id));
    }
    
    @PostMapping(
            path = "/create",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<TaskResponse>> create(@Valid @RequestBody TaskRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.create(request));
    }
	
    @PutMapping(
            path = "/update/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<TaskResponse>> update(@Valid @RequestBody TaskRequest request, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.update(request, id));
    }
    
    @DeleteMapping(
            path = "/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<Void>> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.delete(id));
    }


}
