package com.crudactivity.tinytask.repository;

import com.crudactivity.tinytask.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Task repository.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
}
