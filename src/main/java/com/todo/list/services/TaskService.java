package com.todo.list.services;

import com.todo.list.models.DeletedTaskModel;
import com.todo.list.models.TaskModel;
import com.todo.list.repositories.DeletedTaskRepository;
import com.todo.list.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private DeletedTaskRepository deletedTaskRepository;

    public TaskService () {}

    public List<TaskModel> getAllTasks () {
        return taskRepository.findAll(Sort.by(Sort.Direction.ASC, "isFinished").and(Sort.by(Sort.Direction.DESC, "createdDate")));
    }

    public TaskModel getTaskById (Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public TaskModel saveTask (TaskModel task) {
//        if (task.getId() == null) {
//            Long availableId = findAvailableId();
//            task.setId(availableId);
//        }

        return taskRepository.save(task);
    }

    public void deleteTask (Long id) {
        taskRepository.deleteById(id);
        deletedTaskRepository.save(new DeletedTaskModel(id));
    }

//    private Long findAvailableId() {
//        List<DeletedTaskModel> deletedIds = deletedTaskRepository.findAll();
//        if (!deletedIds.isEmpty()) {
//            Long availableId = deletedIds.get(0).getDeletedId();
//            deletedTaskRepository.deleteById(deletedIds.get(0).getId());
//            return availableId;
//        }
//
//        return taskRepository.count() + 1;
//    }
}
