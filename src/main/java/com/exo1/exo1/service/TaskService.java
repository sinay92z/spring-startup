package com.exo1.exo1.service;

import com.exo1.exo1.dto.TaskDto;
import com.exo1.exo1.entity.Task;
import com.exo1.exo1.mapper.TaskMapper;
import com.exo1.exo1.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;
    private TaskMapper taskMapper;

    public Page<TaskDto> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable).map(taskMapper::toDto);
    }

    @Cacheable("tasks")
    public TaskDto findById(long id) {
        return taskMapper.toDto(taskRepository.findById(id).orElse(null));
    }
    public TaskDto save(TaskDto taskDto) {
        return taskMapper.toDto(taskRepository.save(taskMapper.toEntity(taskDto)));
    }

    @CacheEvict(value = "tasks", key = "#id")
    public TaskDto update(Long id, TaskDto taskDto) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found with id " + id));
        taskDto.setId(existingTask.getId());
        return taskMapper.toDto(taskRepository.save(taskMapper.toEntity(taskDto)));
    }

    @CacheEvict(value = "tasks", key = "#id")
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }


}
