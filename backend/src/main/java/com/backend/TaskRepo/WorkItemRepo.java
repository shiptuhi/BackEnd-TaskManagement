package com.backend.TaskRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.TaskModel.WorkItem;
public interface WorkItemRepo extends JpaRepository<WorkItem, Long> {
	@Query("SELECT w FROM WorkItem w ORDER BY w.id ASC")
	List<WorkItem> findWorkItems();
	
	@Query("SELECT w FROM WorkItem w WHERE w.id = :id")
	WorkItem findWorkItemById(long id);
	
	@Query("SELECT w FROM WorkItem w WHERE w.name = :name")
	WorkItem findWorkItem(String name);
	
	@Query("SELECT count(w.id) FROM WorkItem w")
	Long generatedCode();
	
	@Query("SELECT w.name FROM WorkItem w WHERE module_workitem in (SELECT m FROM Module m WHERE m.name = :module)")
	List<String> findWorkItem_Name(@Param("module") String module);

	@Query("SELECT w FROM WorkItem w WHERE " +
            "(:code is null or lower(w.code) like lower(concat('%', :code,'%')))" +
            "AND (:name is null or lower(w.name) like lower(concat('%', :name,'%')))" +
            "AND (module_workitem in (SELECT m FROM Module m WHERE :module_workitem is null or lower(m.name) like lower(concat('%', :module_workitem,'%'))))" +
            "AND project_workitem in (SELECT p FROM Project p WHERE :project_workitem is null or lower(p.name) like lower(concat('%', :project_workitem,'%')))" +
            "AND (:priority is null or lower(w.priority) like lower(concat('%', :priority,'%')))" +
			
            "AND emp_workitem_1 in (SELECT e FROM Employee e WHERE :emp_workitem_1 is null or lower(e.name) like lower(concat('%', :emp_workitem_1,'%')))" +
			
//			"AND (:emp_workitem_2 is null or lower(w.emp_workitem_2) like lower(concat('%', :emp_workitem_2,'%')))" +
//			"AND (:dateStart is null or lower(w.dateStart) like lower(concat('%', :dateStart,'%')))" +
//			"AND (:dateEnd is null or lower(w.dateEnd) like lower(concat('%', :dateEnd,'%')))" +
//			"AND (:content is null or lower(w.content) like lower(concat('%', :content,'%')))" +
//			"AND (:status is null or lower(w.status) like lower(concat('%', :status,'%')))" +

			" ORDER BY w.id ASC ")
	List<WorkItem> searchWorkItems(@Param("code") String code, 
									@Param("name") String name,
									@Param("module_workitem") String module_workitem,
									@Param("project_workitem") String project_workitem,
									@Param("priority") String priority,
									@Param("emp_workitem_1") String emp_workitem_1);
//									@Param("emp_workitem_2") String emp_workitem_2,
//									@Param("dateStart") String dateStart,
//									@Param("dateEnd") String dateEnd, 
//									@Param("content") String content, 
//									@Param("status") String status_workitem
	
	Boolean existsByCode (String code);
}
