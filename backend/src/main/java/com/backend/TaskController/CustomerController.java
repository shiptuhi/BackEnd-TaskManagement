package com.backend.TaskController;

import com.backend.TaskModel.Customer;
import com.backend.TaskRepo.CustomerRepo;
import com.backend.TaskAuthentication.CustomerRequest;

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
		return new ResponseEntity<>(customerRepo.findCustomers(), HttpStatus.OK);
	}

	@GetMapping("/list/partner")
	public ResponseEntity<List<String>> getCustomers_Partners() {
		return new ResponseEntity<>(customerRepo.findCustomers_partners(), HttpStatus.OK);
	}
	
	@GetMapping("/list/name")
	public ResponseEntity<List<String>> getCustomers_Name() {
		return new ResponseEntity<>(customerRepo.findCustomers_Name(), HttpStatus.OK);
	}

	@PostMapping("/form")
	public ResponseEntity<?> createCustomer(@RequestBody CustomerRequest newCus) {
		
		if(customerRepo.existsBySystemName(newCus.getSystemName())) {
			return new ResponseEntity<>("Error: System name is already taken!",HttpStatus.BAD_REQUEST);
		}
		if(customerRepo.existsByPhoneNo(newCus.getPhoneNo())) {
			return new ResponseEntity<>("Error: Phone number is already taken!",HttpStatus.BAD_REQUEST);
		}
		if(customerRepo.existsByEmail(newCus.getEmail())) {
			return new ResponseEntity<>("Error: Email is already taken!",HttpStatus.BAD_REQUEST);
		}
		Customer cus = new Customer();
		cus.setName(newCus.getName());
		cus.setSystemName(newCus.getSystemName());
		cus.setEmail(newCus.getEmail());
		cus.setPhoneNo(newCus.getPhoneNo());
		cus.setPartner(newCus.getPartner());
		cus.setGender(newCus.getGender());
		cus.setNote(newCus.getNote());
		cus.setStatus(newCus.getStatus());

		return new ResponseEntity<>(customerRepo.save(cus), HttpStatus.OK);
	}

	@PutMapping("/form/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable("id") long id,
			@RequestBody CustomerRequest upCustomer) {
		Customer cus = customerRepo.findCustomerById(id);

		if(!cus.getSystemName().equals(upCustomer.getSystemName()) &&customerRepo.existsBySystemName(upCustomer.getSystemName())) {
			return new ResponseEntity<>("Error: System name is already taken!",HttpStatus.BAD_REQUEST);
		}
		if(!cus.getPhoneNo().equals(upCustomer.getPhoneNo()) &&customerRepo.existsByPhoneNo(upCustomer.getPhoneNo())) {
			return new ResponseEntity<>("Error: Phone number is already taken!",HttpStatus.BAD_REQUEST);
		}
		if(!cus.getEmail().equals(upCustomer.getEmail()) &&customerRepo.existsByEmail(upCustomer.getEmail())) {
			return new ResponseEntity<>("Error: Email is already taken!",HttpStatus.BAD_REQUEST);
		}
		
		cus.setName(upCustomer.getName());
		cus.setSystemName(upCustomer.getSystemName());
		cus.setEmail(upCustomer.getEmail());
		cus.setPhoneNo(upCustomer.getPhoneNo());
		cus.setPartner(upCustomer.getPartner());
		cus.setGender(upCustomer.getGender());
		cus.setNote(upCustomer.getNote());
		cus.setStatus(upCustomer.getStatus());
		return new ResponseEntity<>(customerRepo.save(cus), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id) {

		Optional<Customer> searchData = customerRepo.findById(id);
		if (searchData.isPresent()) {
			return new ResponseEntity<>(searchData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Customer>> searchCustomer(@RequestParam(value = "name", required = false) String name,
														@RequestParam(value = "systemName",required = false) String systemname,
														@RequestParam(value = "email", required = false) String email,
														@RequestParam(value = "phoneNo", required = false) String phoneNo,
														@RequestParam(value = "partner", required = false) String partner,
														@RequestParam(value = "gender", required = false) String gender,
														@RequestParam(value = "note", required = false) String note,
														@RequestParam(value = "status", required = false) String status){

		List<Customer> cus = customerRepo.searchCustomers(name, systemname, email, phoneNo, partner,gender, note, status);

			
	    return new ResponseEntity<>(cus,HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Customer>> searchCustomerWithSearchBar(@RequestParam("search") String query){
		List<Customer> cus = customerRepo.searchCustomersWithSearchBar(query);
		return new ResponseEntity<>(cus,HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
		customerRepo.deleteById(id);

		return new ResponseEntity<>("Successfully", HttpStatus.OK);
	}

}
