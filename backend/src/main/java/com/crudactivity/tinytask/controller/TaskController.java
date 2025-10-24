package com.crudactivity.tinytask.controller;

import com.crudactivity.tinytask.entity.Task;
import com.crudactivity.tinytask.exception.BadRequestException;
import com.crudactivity.tinytask.exception.NotFoundException;
import com.crudactivity.tinytask.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * The type Task controller.
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;


    /**
     * Instantiates a new Task controller.
     *
     * @param taskService the task service
     */
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Get all tasks response entity.
     *
     * @return the response entity
     */
    @GetMapping
    ResponseEntity<?> getAllTasks(){
        return new ResponseEntity<>(taskService.getAllTasks(),HttpStatus.OK);
    }

    /**
     * Get task by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/{id}")
    ResponseEntity<?> getTaskById(@PathVariable Long id){
        Optional<Task> task = taskService.findById(id);
        if(task.isEmpty()){
            throw new NotFoundException("Task not found");
        }
        return ResponseEntity.ok(task);
    }

    /**
     * Create task response entity.
     *
     * @param task the task
     * @return the response entity
     */
    @PostMapping
    ResponseEntity<?> createTask(@RequestBody Task task){
        if(task.getTitle().isEmpty()){
            throw new BadRequestException("Title cannot be empty");
        }
        Task taskSaved = taskService.saveTask(task);
        return ResponseEntity.ok(taskSaved);
    }

    /**
     * Update status task response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @PatchMapping("/toggle/{id}")
    ResponseEntity<?> updateStatusTask(@PathVariable Long id){
        Optional<Task> task = taskService.findById(id);
        if(task.isEmpty()){
            throw new NotFoundException("Task Not found");
        }
        task.get().setDone(!task.get().isDone());
        Task taskSaved = taskService.saveTask(task.get());
        return ResponseEntity.ok(taskSaved);
    }

    /**
     * Delete task response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteTask(@PathVariable Long id){
       if(taskService.findById(id).isEmpty()){
           throw new NotFoundException("Task not found");
       }
       taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }




}
