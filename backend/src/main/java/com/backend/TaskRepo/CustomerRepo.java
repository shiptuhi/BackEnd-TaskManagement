package com.backend.TaskRepo;

import com.backend.TaskModel.Customer;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
	
	@Query("SELECT c FROM Customer c ORDER BY c.id")
	List<Customer> findCustomers();

}
