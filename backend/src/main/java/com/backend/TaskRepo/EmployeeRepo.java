package com.backend.TaskRepo;

import com.backend.TaskModel.Employee;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	@Query("SELECT e FROM Employee e ORDER BY e.id ASC")
	List<Employee> findEmployees();

	@Query("SELECT e FROM Employee e WHERE e.username = :username ")
	Employee find(String username);

	@Query("SELECT e FROM Employee e WHERE e.id = :id ")
	Employee findEId(long id);
	
	@Query("SELECT e FROM Employee e WHERE e.name = :name ")
	Employee findE(String name);

	
	@Query("SELECT e.name FROM Employee e ")
	List<String> listEmployee_Name();
	
	
	Optional<Employee> findByUsername(String username);

	Optional<Employee> findById(Long id);

	Boolean existsByUsername(String username);

	Boolean existsByPhoneNo(String phoneNo);

	Boolean existsByEmail(String email);
	
	Boolean existsByPassword(String password);

	@Query("SELECT e FROM Employee e WHERE" + "(:name is null or lower(e.name) like lower(concat('%', :name,'%')))"
			+ " AND (:username is null or lower(e.username) like lower(concat('%', :username,'%')))"
			+ " AND (:phoneNo is null or lower(e.phoneNo) like lower(concat('%', :phoneNo,'%')))"
			+ " AND (:role is null or e in (SELECT e FROM Employee e JOIN e.role e2 WHERE lower(e2.name) like lower(concat('%', :role,'%'))))"
			+ "ORDER BY e.id ASC ")
	List<Employee> searchEmployees(@Param("name") String name, @Param("username") String username,
			@Param("phoneNo") String phoneNo, @Param("role") String role);
}
