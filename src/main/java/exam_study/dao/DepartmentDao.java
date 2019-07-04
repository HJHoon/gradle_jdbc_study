package exam_study.dao;

import java.sql.SQLException;
import java.util.List;

import exam_study.dto.Department;

public interface DepartmentDao {
	List<Department> selectDepartmentByAll() throws SQLException;
	int insertDepartment(Department department) throws SQLException;
	int updateDepartment(Department department) throws SQLException;
	int deleteDepartment(Department department) throws SQLException;
}
