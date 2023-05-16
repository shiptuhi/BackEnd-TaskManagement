package com.backend.TaskRepo;

import com.backend.TaskModel.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepo extends JpaRepository<Role, Long>  {
	
//	@Query("SELECT r FROM Role r ORDER BY r.id")
//	List<Role> a();
	
	Optional<Role> findByName(String name);


}
