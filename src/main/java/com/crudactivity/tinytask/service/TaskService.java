package com.crudactivity.tinytask.service;

import com.crudactivity.tinytask.entity.Task;
import com.crudactivity.tinytask.exception.NotFoundException;
import com.crudactivity.tinytask.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public Task saveTask( Task task){
        return taskRepository.save(task);
    }

    public  List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Optional<Task> findById(Long id){
        return taskRepository.findById(id);
    }

    public void deleteTask(Long id){
        if(findById(id).isEmpty()){
            throw new NotFoundException("Task not found");
        }
        taskRepository.deleteById(id);
    }
}
