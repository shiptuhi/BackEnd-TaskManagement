package com.backend.TaskRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.TaskModel.Project;


@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {
	@Query("SELECT p FROM Project p ORDER BY p.id")
	List<Project> findProjects();
	
	@Query("SELECT p FROM Project p WHERE p.id = :id")
	Project findProjectById(long id);

}
