package com.backend.TaskRepo;

import com.backend.TaskModel.Customer;

import org.springframework.stereotype.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
	@Query("SELECT c FROM Customer c ORDER BY c.id")
	List<Customer> findCustomers();
	
	@Query("SELECT c FROM Customer c WHERE c.id = :id")
	Customer findCustomerById(long id);
	
	@Query("SELECT c.partner FROM Customer c ")
	List<String> findCustomers_partners();
	
	@Query("SELECT c FROM Customer c WHERE " +
			"c.id LIKE CONCAT('%',:id, '%')" +
			"AND c.name LIKE CONCAT('%',:name, '%')")
	List<Customer> listAll(@Param("id") long id, @Param("name") String name);
	

}
