package com.backend.TaskRepo;

import com.backend.TaskModel.Customer;

import org.springframework.stereotype.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
	@Query("SELECT c FROM Customer c ORDER BY c.id ASC")
	List<Customer> findCustomers();
	
	@Query("SELECT c FROM Customer c WHERE c.id = :id")
	Customer findCustomerById(long id);
	
	@Query("SELECT c FROM Customer c WHERE c.name = :name")
	Customer findCustomer(String name);
	
	@Query("SELECT c.partner FROM Customer c ")
	List<String> findCustomers_partners();
	
	@Query("SELECT c.name FROM Customer c ")
	List<String> findCustomers_Name();

	Boolean existsByName (String name);
	
	Boolean existsBySystemName (String systemname);
	
	Boolean existsByPhoneNo(String phoneNo);
	
	Boolean existsByEmail (String email);
	
	@Query("SELECT c FROM Customer c WHERE " +
            "(:name is null or lower(c.name) like lower(concat('%', :name,'%')))" +
            "AND (:systemName is null or lower(c.systemName) like lower(concat('%', :systemName,'%')))" +
			"AND (:email is null or lower(c.email) like lower(concat('%', :email,'%')))" +
			"AND (:phoneNo is null or lower(c.phoneNo) like lower(concat('%', :phoneNo,'%')))" +
			"AND (:partner is null or lower(c.partner) like lower(concat('%', :partner,'%')))" +
			"AND (:gender is null or lower(c.gender) like lower(concat('%', :gender,'%')))" +
			"AND (:note is null or lower(c.note) like lower(concat('%', :note,'%')))" +
			"AND (:status is null or lower(c.status) like lower(concat('%', :status,'%')))"+
			"ORDER BY c.id ASC ")
	
	List<Customer> searchCustomers(@Param("name") String name, @Param("systemName") String systemname,
								@Param("email") String email, @Param("phoneNo") String phoneNo,
								@Param("partner") String partner, @Param("gender") String gender,
								@Param("note") String note, @Param("status") String status);
	
    @Query("SELECT c FROM Customer c WHERE " +
            "c.name LIKE CONCAT('%',:query, '%')" +
            "Or c.systemName LIKE CONCAT('%', :query, '%')" +
    		"Or c.partner LIKE CONCAT('%', :query, '%')" +
    		"ORDER BY c.id ASC ")
	List<Customer> searchCustomersWithSearchBar(String query);
}
