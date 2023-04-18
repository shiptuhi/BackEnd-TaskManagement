package com.backend.TaskRepo;

import com.backend.TaskModel.Employee;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	@Query("SELECT e FROM Employee e ORDER BY e.id")
	List<Employee> findEmployees();
	
	@Query("SELECT e FROM Employee e WHERE e.username = :username ")
	Employee find(String username);
	
	@Query("SELECT e FROM Employee e WHERE e.id = :id ")
	Employee findEId(long id);

	Optional<Employee> findByUsername(String username);

	Optional<Employee> findById(Long id);

}
