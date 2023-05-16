package com.backend.TaskRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.TaskModel.WorkDo;

public interface WorkDoRepo extends JpaRepository<WorkDo, Long> {
	
	@Query("SELECT w FROM WorkDo w ORDER BY w.id ASC")
	List<WorkDo> findWorkDoes();
	
	@Query("SELECT w FROM WorkDo w WHERE emp_workdo_3 in (SELECT e FROM Employee e WHERE e.id= :id)")
	List<WorkDo> getWorkDoByEmpId(Long id);
	
	@Query("SELECT w FROM WorkDo w WHERE w.id = :id")
	WorkDo findWorkDoById(Long id);
	
	@Query("SELECT count(w.id) FROM WorkDo w")
	Long generatedCode();
	
	@Query("SELECT w FROM WorkDo w WHERE " +
            "(:code is null or lower(w.code) like lower(concat('%', :code,'%')))" +
            "AND (:name is null or lower(w.name) like lower(concat('%', :name,'%')))" +
            
            "AND workItem in (SELECT w FROM WorkItem w WHERE :workItem is null or lower(w.name) like lower(concat('%', :workItem,'%')))" +
            "AND module_workdo in (SELECT m FROM Module m WHERE :module_workdo is null or lower(m.name) like lower(concat('%', :module_workdo,'%')))" +
            "AND project_workdo in (SELECT p FROM Project p WHERE :project_workdo is null or lower(p.name) like lower(concat('%', :project_workdo,'%')))" +
            
            "AND (:type is null or lower(w.priority) like lower(concat('%', :type,'%')))" +
            
            "AND (:priority is null or lower(w.priority) like lower(concat('%', :priority,'%')))" +
            
            "AND (:group is null or lower(w.group) like lower(concat('%', :group,'%')))" +
			
            "AND emp_workdo_3 in (SELECT e FROM Employee e WHERE :emp_workdo_3 is null or lower(e.name) like lower(concat('%', :emp_workdo_3,'%')))" +
			
			"AND (:content is null or lower(w.content) like lower(concat('%', :content,'%')))" +
			"AND (:status is null or lower(w.status) like lower(concat('%', :status,'%')))" +
			" ORDER BY w.id ASC ")
	List<WorkDo> searchWorkDoes(@Param("code") String code, 
								@Param("name") String name,
								@Param("workItem") String workItem,
								@Param("module_workdo") String module_workdo,
								@Param("project_workdo") String project_workdo,
								
								@Param("type") String type,
								
								@Param("priority") String priority,
								
								@Param("group") String group,
								
								@Param("emp_workdo_3") String emp_workdo_3,
								
								
								@Param("content") String content, 
								@Param("status") String status_workitem);
	
	Boolean existsByCode (String code);
	
	@Query("DELETE FROM WorkDo w WHERE w.id = :id")
	Long delete(@Param("id") long id);
	
	

	@Query("SELECT "
			+ "SUM(CASE WHEN w.type = 0 THEN 1 ELSE 0 END),"
			+ "SUM(CASE WHEN w.type = 1 THEN 1 ELSE 0 END),"
			+ "SUM(CASE WHEN w.type = 2 THEN 1 ELSE 0 END) "
			+ "FROM WorkDo w")
	String test1();
	
	@Query("SELECT "
			+ "SUM(CASE WHEN w.priority = 0 THEN 1 ELSE 0 END) ,"
			+ "SUM(CASE WHEN w.priority = 1 THEN 1 ELSE 0 END) ,"
			+ "SUM(CASE WHEN w.priority = 2 THEN 1 ELSE 0 END) ,"
			+ "SUM(CASE WHEN w.priority = 3 THEN 1 ELSE 0 END) "
			+ "FROM WorkDo w")
	String test2();
	
	@Query("SELECT "
			+ "SUM(CASE WHEN w.group = 0 THEN 1 ELSE 0 END) ,"
			+ "SUM(CASE WHEN w.group = 1 THEN 1 ELSE 0 END) "
			+ "FROM WorkDo w")
	String test3();
	
	@Query("SELECT "
			+ "SUM(CASE WHEN w.status = 0 THEN 1 ELSE 0 END) ,"
			+ "SUM(CASE WHEN w.status = 1 THEN 1 ELSE 0 END) ,"
			+ "SUM(CASE WHEN w.status = 2 THEN 1 ELSE 0 END) ,"
			+ "SUM(CASE WHEN w.status = 3 THEN 1 ELSE 0 END) ,"
			+ "SUM(CASE WHEN w.status = 4 THEN 1 ELSE 0 END) "
			+ "FROM WorkDo w")
	String test4();
	
	
//	@Query("SELECT sum(w.type) FROM WorkDo w WHERE w.type=0")
//	Long test();
}
