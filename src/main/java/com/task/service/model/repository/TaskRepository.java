package com.task.service.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.task.service.model.entity.TaskEntity;

@Repository
public interface TaskRepository extends CrudRepository<TaskEntity, Long>{
    
	public List<TaskEntity> findByActiveIsTrue();

    public Optional<TaskEntity> findById(Long id);
}
