package exam_study.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exam_study.dao.EmployeeDao;
import exam_study.dto.Department;
import exam_study.dto.Employee;
import exam_study.dto.Title;
import exam_study.jdbc.ConnectionProvider;

public class EmployeeDaoImpl implements EmployeeDao {
	static final Logger log = LogManager.getLogger();

	@Override
	public List<Employee> selectEmployeeByAll() throws SQLException {
		String sql = "select e.empno, e.empname, tno, tname, e.manager, m.empname as m_name, e.salary, e.gender, deptno, deptname, floor, e.hire_date "
				+ "from employee e left join title t on e.title = t.tno join department d on e.dno = d.deptno join employee m on e.manager = m.empno "
				+ "union "
				+ "select e.empno, e.empname, tno, tname, e.manager, null, e.salary, e.gender, deptno, deptname, floor, e.hire_date "
				+ "from employee e left join title t on e.title = t.tno join department d on e.dno = d.deptno where manager is null "
				+ "order by empno";
		List<Employee> lists = null;

		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			log.trace(pstmt);
			if (rs.next()) {
				lists = new ArrayList<Employee>();
				do {
					lists.add(getEmployee(rs));
				} while (rs.next());
			}
		}
		return lists;
	}

	private Employee getEmployee(ResultSet rs) throws SQLException {
		return new Employee(rs.getInt("empNo"), rs.getString("empName"), new Title(rs.getInt("tNo"), rs.getString("tName")), 
				new Employee(rs.getInt("manager"), rs.getString("m_name")), rs.getInt("salary"), rs.getBoolean("gender"),
				new Department(rs.getInt("deptno"), rs.getString("deptname"), rs.getInt("floor")), 
				rs.getDate("hire_date"));
	}

	@Override
	public int insertEmployee(Employee employee) throws SQLException {
		String sql = "insert into employee values(?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, employee.getEmpNo());
			pstmt.setString(2, employee.getEmpName());
			pstmt.setInt(3, employee.getTitle().getTitleNo());
			pstmt.setInt(4, employee.getManager().getEmpNo());
			pstmt.setInt(5, employee.getSalary());
			pstmt.setBoolean(6, employee.getGender());
			pstmt.setInt(7, employee.getDno().getDeptNo());
			pstmt.setDate(8, new Date(employee.getHireDate().getTime()));

			log.trace(pstmt);
			return pstmt.executeUpdate();
		}
	}

	@Override
	public int updateEmployee(Employee employee) throws SQLException {
		String sql = "update employee set empname=?, title=?, manager=?, salary=?, gender=?, dno=?, hire_date=? where empno=?";

		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, employee.getEmpName());
			pstmt.setInt(2, employee.getTitle().getTitleNo());
			pstmt.setInt(3, employee.getManager().getEmpNo());
			pstmt.setInt(4, employee.getSalary());
			pstmt.setBoolean(5, employee.getGender());
			pstmt.setInt(6, employee.getDno().getDeptNo());
			pstmt.setDate(7, (Date) employee.getHireDate());
			pstmt.setInt(8, employee.getEmpNo());

			log.trace(pstmt);
			return pstmt.executeUpdate();
		}
	}

	@Override
	public int deleteEmployee(Employee employee) throws SQLException {
		String sql = "delete from employee where empno = ?";

		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, employee.getEmpNo());

			log.trace(pstmt);
			return pstmt.executeUpdate();
		}
	}

}
