package com.cydeo.repository;

import com.cydeo.entity.Project;
import com.cydeo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select count (t) from Task t where t.project.projectCode = ?1 and t.taskStatus <> 'COMPLETED'")
    int totalNonCompletedTasks(String projectCode);

    @Query(nativeQuery = true, value = "select count(*) from tasks t join projects p on t.project_id = p.id" +
            "where p.project_code = ?1 and t.task_status = 'COMPLETE'")
    int totalCompletedTasks(String projectCode);
}
