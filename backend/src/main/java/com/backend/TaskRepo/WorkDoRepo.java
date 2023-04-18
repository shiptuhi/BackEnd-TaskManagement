package com.backend.TaskRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.TaskModel.WorkDo;

public interface WorkDoRepo extends JpaRepository<WorkDo, Long> {
	
	@Query("SELECT w FROM WorkDo w ORDER BY w.id")
	List<WorkDo> findWorkDos();

}
