package exam_study.dto;

import java.util.Date;

public class Employee {
	private int empNo;
	private String empName;
	private Title title;
	private Employee manager;
	private int salary;
	private boolean gender;
	private Department dno;
	private Date hireDate;

	public Employee() {
	}

	public Employee(int empNo) {
		this.empNo = empNo;
	}

	public Employee(int empNo, String empName) {
		this.empNo = empNo;
		this.empName = empName;
	}

	public Employee(int empNo, String empName, Title title, Employee manager, int salary, boolean gender,
			Department dno, Date hireDate) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.manager = manager;
		this.salary = salary;
		this.gender = gender;
		this.dno = dno;
		this.hireDate = hireDate;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean isMale) {
		this.gender = isMale;
	}

	public Department getDno() {
		return dno;
	}

	public void setDno(Department dno) {
		this.dno = dno;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + empNo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (empNo != other.empNo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		return String.format("%s(%s)", empName, empNo);
		
//		return String.format(
//				"Employee [empNo=%s, empName=%s, title=%s, manager=%s, salary=%s, isMale=%s, dno=%s, hireDate=%s]",
//				empNo, empName, title.getTitleNo(), manager.getEmpNo(), salary, gender ? "남자" : "여자", dno.getDeptNo(),
//				hireDate);
	}

	public Object[] toArray() {
		return new Object[] { 
				String.format("E%06d", empNo), 
				empName,
				title.getTitleName(),
				String.format("%,d", salary),
				gender ? "남자" : "여자",
				String.format("%s(%s층)", dno.getDeptName(), dno.getFloor()),
				manager.getEmpNo()==0?"":String.format("%s(%s)", manager.getEmpName(), manager.getEmpNo()),
				String.format("%tF", hireDate) };
	}
}
