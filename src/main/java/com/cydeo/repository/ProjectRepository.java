package com.cydeo.repository;

import com.cydeo.entity.Project;
import com.cydeo.entity.User;
import com.cydeo.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findByProjectCode(String code);

    List<Project> findByAssignedManager(User manager);

    List<Project> findAllByProjectStatusIsNotAndAssignedManager(Status status, User assignedManager);

}
