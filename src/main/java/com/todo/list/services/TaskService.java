package com.todo.list.services;

import com.todo.list.models.TaskModel;
import com.todo.list.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public TaskService () {}

    public List<TaskModel> getAllTasks () {
        return taskRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
    }

    public TaskModel getTaskById (Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public TaskModel saveTask (TaskModel task) {
        return taskRepository.save(task);
    }

    public void deleteTask (Long id) {
        taskRepository.deleteById(id);
    }
}
