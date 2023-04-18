package com.backend.TaskRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.backend.TaskModel.Module;

@Repository
public interface ModuleRepo extends JpaRepository<Module, Long> {
	
	@Query("SELECT m FROM Module m ORDER BY m.id")
	List<Module> findModules();
	
	@Query("SELECT m FROM Module m WHERE m.id = :id")
	Module findModuleById(long id);

}
