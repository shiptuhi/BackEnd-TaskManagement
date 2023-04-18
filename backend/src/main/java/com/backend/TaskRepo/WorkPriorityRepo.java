package com.backend.TaskRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.TaskModel.WorkPriority;

public interface WorkPriorityRepo extends JpaRepository<WorkPriority, Long>{

	@Query("SELECT w FROM WorkPriority w WHERE w.id = :id")
	WorkPriority getPriority(long id);
}
