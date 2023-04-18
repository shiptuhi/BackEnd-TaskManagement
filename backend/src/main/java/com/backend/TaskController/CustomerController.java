package com.backend.TaskController;

import com.backend.TaskModel.Customer;
import com.backend.TaskRepo.CustomerRepo;
import com.backend.TaskAuthentication.CustomerRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

	private final CustomerRepo customerRepo;

	@GetMapping("/list")
	public ResponseEntity<List<Customer>> getCustomers() {
		return new ResponseEntity<>(customerRepo.findAll(), HttpStatus.OK);
	}
	
	
	
	@GetMapping("/list/partner")
	public ResponseEntity<List<String>> getCustomers_Partners() {
		return new ResponseEntity<>(customerRepo.findCustomers_partners(), HttpStatus.OK);
	}

	@PostMapping("/form")
	public ResponseEntity<Customer> createCustomer(@RequestBody CustomerRequest newCus) {
		Customer cus = new Customer();
		cus.setName(newCus.getName());
		cus.setSystemName(newCus.getSystemName());
		cus.setEmail(newCus.getEmail());
		cus.setPhoneNo(newCus.getPhoneNo());
		cus.setPartner(newCus.getPartner());
		cus.setGender(newCus.getGender());
		cus.setNote(newCus.getNote());
		cus.setStatus(newCus.getStatus());


		return new ResponseEntity<>(customerRepo.save(cus), HttpStatus.CREATED);
	}
	
	@PutMapping("/form/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id,@RequestBody CustomerRequest upCustomer) {
		Customer cus = customerRepo.findCustomerById(id);
		cus.setName(upCustomer.getName());
		cus.setSystemName(upCustomer.getSystemName());
		cus.setEmail(upCustomer.getEmail());
		cus.setPhoneNo(upCustomer.getPhoneNo());
		cus.setPartner(upCustomer.getPartner());
		cus.setGender(upCustomer.getGender());
		cus.setNote(upCustomer.getNote());
		cus.setStatus(upCustomer.getStatus());
		return new ResponseEntity<>(customerRepo.save(cus), HttpStatus.CREATED);
	}

	@GetMapping("/search")
	public ResponseEntity<List<Customer>> searchCustomer(@RequestParam("id") long id, @RequestParam("name") String name,Pageable pageable) {
 
		List<Customer> searchData = customerRepo.listAll(id, name);
		return ResponseEntity.ok(searchData);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable(name = "id") Long id) { 
		customerRepo.deleteById(id);
		
		return new ResponseEntity< >("Successfully", HttpStatus.OK);
	}

}
