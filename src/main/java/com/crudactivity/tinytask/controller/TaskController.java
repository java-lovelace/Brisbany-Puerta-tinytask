package com.crudactivity.tinytask.controller;

import com.crudactivity.tinytask.entity.Task;
import com.crudactivity.tinytask.exception.BadRequestException;
import com.crudactivity.tinytask.exception.NotFoundException;
import com.crudactivity.tinytask.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    ResponseEntity<?> getAllTasks(){
        return new ResponseEntity<>(taskService.getAllTasks(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getTaskById(@PathVariable Long id){
        Optional<Task> task = taskService.findById(id);
        if(task.isEmpty()){
          throw new NotFoundException("Task not found");
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping
    ResponseEntity<?> createTask(@RequestBody Task task){
        if(task.getTitle().isEmpty()){
            throw new BadRequestException("Title cannot be empty");
        }
        Task taskSaved = taskService.saveTask(task);
        return ResponseEntity.ok(taskSaved);
    }





}
