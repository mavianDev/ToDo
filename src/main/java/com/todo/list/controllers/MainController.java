package com.todo.list.controllers;

import com.todo.list.models.TaskModel;
import com.todo.list.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping ("/")
public class MainController {
    private final TaskService service;

    public MainController (TaskService service) {
        this.service = service;
    }

    @GetMapping ("/")
    public String mainPage (Model model) {
        model.addAttribute("title", "ToDo Application");
        model.addAttribute("tasks", service.getAllTasks());
        return "page";
    }

    @GetMapping("/create")
    public String showCreateForm (Model model) {
        model.addAttribute("tasks", new TaskModel());
        model.addAttribute("title", "Создание задачи");
        return "task-create";
    }

    @PostMapping("/create")
    public String createTask (Model model, @ModelAttribute TaskModel task) {
        task.setCreatedDate(LocalDateTime.now());
        service.saveTask(task);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editTask (@PathVariable Long id, Model model) {
        Optional<TaskModel> task = Optional.ofNullable(service.getTaskById(id));
        if (task.isPresent()) {
            model.addAttribute("tasks", task.get());
            return "task-edit";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateTask (@PathVariable Long id, @ModelAttribute TaskModel updatedTask) {
        Optional<TaskModel> task = Optional.ofNullable(service.getTaskById(id));
        if (task.isPresent()) {
            TaskModel existingTask = task.get();
            existingTask.setTaskName(updatedTask.getTaskName());
            existingTask.setTaskDescription(updatedTask.getTaskDescription());
            updatedTask.setCreatedDate(LocalDateTime.now());
            service.saveTask(existingTask);
        }
        return "redirect:/";
    }

    @PostMapping("/complete/{id}")
    public String completeTask(@PathVariable Long id) {
        Optional<TaskModel> task = Optional.ofNullable(service.getTaskById(id));
        if (task.isPresent()) {
            TaskModel existingTask = task.get();
            existingTask.setFinished(true);
            service.saveTask(existingTask);
        }
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask (Model model, @PathVariable Long id) {
        service.deleteTask(id);
        return "redirect:/";
    }
}
