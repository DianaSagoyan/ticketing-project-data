package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.repository.TaskRepository;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void save(TaskDTO task) {

    }

    @Override
    public void update(TaskDTO task) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<TaskDTO> listAllTasks() {
        return null;
    }

    @Override
    public TaskDTO findById(Long id) {
        return null;
    }
}
