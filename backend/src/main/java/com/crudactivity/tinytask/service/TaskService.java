package com.crudactivity.tinytask.service;

import com.crudactivity.tinytask.entity.Task;
import com.crudactivity.tinytask.exception.NotFoundException;
import com.crudactivity.tinytask.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Task service.
 */
@Service
public class TaskService {
    private final TaskRepository taskRepository;

    /**
     * Instantiates a new Task service.
     *
     * @param taskRepository the task repository
     */
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Save task task.
     *
     * @param task the task
     * @return the task
     */
    public Task saveTask( Task task){
        return taskRepository.save(task);
    }

    /**
     * Get all tasks list.
     *
     * @return the list
     */
    public  List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<Task> findById(Long id){
        return taskRepository.findById(id);
    }

    /**
     * Delete task.
     *
     * @param id the id
     */
    public void deleteTask(Long id){
        if(findById(id).isEmpty()){
            throw new NotFoundException("Task not found");
        }
        taskRepository.deleteById(id);
    }
}
