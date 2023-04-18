package com.backend.TaskRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.TaskModel.WorkStatus;

public interface WorkStatusRepo extends JpaRepository<WorkStatus, Long> {
	
	@Query("SELECT w FROM WorkStatus w WHERE w.id = :id")
	WorkStatus getStatus(long id);
}
