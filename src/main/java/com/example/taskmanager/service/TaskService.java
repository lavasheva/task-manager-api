package com.example.taskmanager.service;


import com.example.taskmanager.exeption.TaskNotFoundException;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(){return taskRepository.findAll();}
    public Optional<Task> getTaskById(Long id){
        return Optional.of(taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id)));
    }
    public Task createTask(Task task){return taskRepository.save(task);}
    public Task updateTask(Long id, Task updateTask){
        Task task = taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException(id));
        task.setTitle(updateTask.getTitle());
        task.setDescription(updateTask.getDescription());
        task.setStatus(updateTask.getStatus());

        return taskRepository.save(task);
    }

    public void deleteTask(Long id){taskRepository.deleteById(id);}
}
