package com.backend.TaskController;

import com.backend.TaskModel.Customer;
import com.backend.TaskRepo.CustomerRepo;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class CustomerController {

	private final CustomerRepo customerRepo;

	@GetMapping("/customer-detail")
	public List<Customer> getCustomers() {
		return customerRepo.findCustomers();
	}

}
