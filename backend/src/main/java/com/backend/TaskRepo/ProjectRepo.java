package com.backend.TaskRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.backend.TaskModel.Project;


@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {
	@Query("SELECT p FROM Project p ORDER BY p.id ASC")
	List<Project> findProjects();
	
	@Query("SELECT p FROM Project p WHERE p.id = :id")
	Project findProjectById(long id);
	
	@Query("SELECT p FROM Project p WHERE p.name = :name")
	Project findProject(String name);
	
	@Query("SELECT p.name FROM Project p ")
	List<String> listProjectName();
	
	@Query("SELECT p FROM Project p WHERE " +
            "(:code is null or lower(p.code) like lower(concat('%', :code,'%')))" +
            "AND (:name is null or lower(p.name) like lower(concat('%', :name,'%')))" +
            "AND (:status is null or lower(p.status) like lower(concat('%', :status,'%')))" +
//			"AND (:dateStart is null or p.dateStart like lower(concat('%', :dateStart,'%')))" +
//			"AND (:dateEnd is null or p.dateEnd like lower(concat('%', :dateEnd,'%')))" +
			"AND customer in (SELECT id FROM Customer c WHERE :partner is null or lower(c.partner) like lower(concat('%', :partner,'%')))" +
			"AND (:note is null or lower(p.note) like lower(concat('%', :note,'%')))" +
			"ORDER BY p.id ASC")
	
	List<Project> searchProjects(@Param("code") String code, @Param("name") String name,
								@Param("status") String status, 
//								@Param("dateStart") Date dateStart,
//								@Param("dateEnd") Date dateEnd,
								@Param("partner") String customer, @Param("note") String note);
	
	@Query("SELECT p FROM Project p WHERE " +
			"p.name LIKE CONCAT('%',:query, '%')" +
			"OR p.code LIKE CONCAT('%',:query, '%')" +
			"OR customer in (SELECT c FROM Customer c WHERE c.partner LIKE CONCAT('%',:query, '%'))"+
			"ORDER BY p.id ASC")
	List<Project> searchProjectsWithSearchBar(String query);
	
	Boolean existsByCode (String code);

}
