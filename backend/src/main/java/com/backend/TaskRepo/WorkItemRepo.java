package com.backend.TaskRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.backend.TaskModel.WorkItem;
public interface WorkItemRepo extends JpaRepository<WorkItem, Long> {
	@Query("SELECT w FROM WorkItem w ORDER BY w.id")
	List<WorkItem> findWorkItems();
	
	@Query("SELECT w FROM WorkItem w WHERE w.id = :id")
	WorkItem findWorkItemById(long id);

}
