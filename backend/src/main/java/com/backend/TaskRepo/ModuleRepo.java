package com.backend.TaskRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.backend.TaskModel.Module;


@Repository
public interface ModuleRepo extends JpaRepository<Module, Long> {
	
	@Query("SELECT m FROM Module m ORDER BY m.id ASC")
	List<Module> findModules();
	
	@Query("SELECT m FROM Module m WHERE m.id = :id")
	Module findModuleById(long id);
	
	@Query("SELECT m FROM Module m WHERE m.name = :name")
	Module findModule(String name);
	
	@Query("SELECT count(m.id) FROM Module m")
	Long generatedCode();
	
	@Query("SELECT m.name FROM Module m WHERE project in (SELECT p FROM Project p WHERE p.name=:project)")
	List<String> findModule_Name(@Param("project") String project);
	
	@Query("SELECT m FROM Module m WHERE " +
			"(:code is null or lower(m.code) like lower(concat('%', :code,'%')))" +
            "AND (:name is null or lower(m.name) like lower(concat('%', :name,'%')))" +
            "AND (project in (SELECT p FROM Project p WHERE :project is null or lower(p.name) like lower(concat('%', :project,'%'))))" +
            
//            "AND (:dateStart is null or m.dateStart = :dateStart )" +
            
            "AND (:emp is null or m in (SELECT e FROM Employee e JOIN e.mods e2 WHERE lower(e2.name) LIKE lower(concat('%', :emp,'%'))))" +
			"AND (:note is null or lower(m.note) like lower(concat('%', :note,'%')))" +
			"ORDER BY m.id ASC")

	List<Module> findModules(@Param("code") String code, @Param("name") String name,
							@Param("project") String project,
//							@Param("dateStart") Date dateStart, 
							@Param("emp") String emp,
							@Param("note") String note);

	Boolean existsByCode (String code);
}
