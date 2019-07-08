package exam_study.ui.content;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import exam_study.dto.Department;
import exam_study.dto.Employee;
import exam_study.dto.Title;

@SuppressWarnings("serial")
public class PanelEmployee extends JPanel {
	
	private JTextField tfEmpNo;
	private JTextField tfEmpName;
	private JComboBox<Title> cmbTitle;
	private JComboBox<Department> cmbDept;
	
	private JTextField tfDate;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JSpinner spSalary;
	private JRadioButton man;
	private JRadioButton woman;
	private List<Title> titleList;
	
	private List<Department> deptList;
	
	private List<Employee> mgnList;
	private DefaultComboBoxModel<Title> titleModels;
	private DefaultComboBoxModel<Department> deptModels;
	private DefaultComboBoxModel<Employee> mgnModels;
	private JComboBox cmbMgn;
	
	private Employee nextEmp;
	private Employee searchEmpNo;

	public PanelEmployee() {
		initComponents();
	}
	public List<Title> getTitleList() {
		return titleList;
	}
	public void setTitleList(List<Title> titleList) {
		this.titleList = titleList;
	}
	public List<Department> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<Department> deptList) {
		this.deptList = deptList;
	}
	public List<Employee> getMgnList() {
		return mgnList;
	}
	public void setMgnList(List<Employee> mgnList) {
		this.mgnList = mgnList;
	}
	private void initComponents() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pEmp = new JPanel();
		add(pEmp);
		pEmp.setLayout(new GridLayout(0, 2, 10, 5));
		
		JLabel lblEmpNo = new JLabel("번호");
		pEmp.add(lblEmpNo);
		lblEmpNo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tfEmpNo = new JTextField();
		pEmp.add(tfEmpNo);
		tfEmpNo.setColumns(10);
		
		JLabel lblEmpName = new JLabel("사원명");
		pEmp.add(lblEmpName);
		lblEmpName.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tfEmpName = new JTextField();
		pEmp.add(tfEmpName);
		tfEmpName.setColumns(10);
		
		JLabel lblTitle = new JLabel("직책");
		pEmp.add(lblTitle);
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		
		cmbTitle = new JComboBox<Title>();
		pEmp.add(cmbTitle);
		
		JLabel lblSalary = new JLabel("급여");
		pEmp.add(lblSalary);
		lblSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		
		spSalary = new JSpinner();
		spSalary.setModel(new SpinnerNumberModel(new Integer(1500000), new Integer(1000000), new Integer(5000000), new Integer(100000)));
		pEmp.add(spSalary);
		
		JLabel lblGender = new JLabel("성별");
		pEmp.add(lblGender);
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JPanel pgender = new JPanel();
		pEmp.add(pgender);
		pgender.setLayout(new GridLayout(0, 2, 0, 0));
		
		man = new JRadioButton("남");
		man.setSelected(true);
		buttonGroup.add(man);
		pgender.add(man);
		
		woman = new JRadioButton("여");
		buttonGroup.add(woman);
		pgender.add(woman);
		
		JLabel lblDept = new JLabel("부서");
		pEmp.add(lblDept);
		lblDept.setHorizontalAlignment(SwingConstants.RIGHT);
		
		cmbDept = new JComboBox<Department>();
		pEmp.add(cmbDept);
		
		JLabel lblMgn = new JLabel("직속상사");
		lblMgn.setHorizontalAlignment(SwingConstants.RIGHT);
		pEmp.add(lblMgn);
		
		cmbMgn = new JComboBox();
		pEmp.add(cmbMgn);
		
		JLabel lblDate = new JLabel("입사일");
		pEmp.add(lblDate);
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tfDate = new JTextField();
		pEmp.add(tfDate);
		tfDate.setColumns(10);
		tfDate.setText(String.format("%tF", new Date()));
	}
	
	public void setEmployee(Employee employee) {
		tfEmpNo.setText(employee.getEmpNo()+"");
		tfEmpName.setText(employee.getEmpName());
		cmbTitle.setSelectedItem(employee.getTitle());
		
		cmbDept.setSelectedItem(employee.getDno());
	}
	
	public void setTitles(List<Title> titleLists) {
		this.titleList = titleLists;
		this.titleModels = new DefaultComboBoxModel<Title>(new Vector<Title>(titleLists));
		cmbTitle.setModel(titleModels);
		cmbTitle.setSelectedIndex(-1);
	}
	
	public void setDepartments(List<Department> deptLists) {
		this.deptList = deptLists;
		System.out.println(deptList);
		this.deptModels = new DefaultComboBoxModel<Department>(new Vector<Department>(deptLists));
		cmbDept.setModel(deptModels);
		cmbDept.setSelectedIndex(-1);
	}
	
	public void setManagements(List<Employee> mgnLists) {
		this.mgnList = mgnLists;
		if (mgnModels != null) {
			mgnModels.removeAllElements();
		}
		this.mgnModels = new DefaultComboBoxModel<Employee>(new Vector<Employee>(mgnLists));
		System.out.println(mgnModels);
		System.out.println(cmbMgn);
		cmbMgn.setModel(mgnModels);
		cmbMgn.setSelectedIndex(-1);
	}
	
	public void setEmpTf() {
		String no1 = String.format("E%06d", mgnList.get(mgnList.size()-1).getEmpNo()+1);
		tfEmpNo.setText(no1);
		tfEmpNo.setEnabled(false);
		
	}
	
	public Employee getEmployee() {
		int empno = Integer.parseInt(tfEmpNo.getText().trim().substring(1));
		String empname = tfEmpName.getText().trim();
		Title title = (Title) cmbTitle.getSelectedItem();
		Employee manager = (Employee) cmbMgn.getSelectedItem();
		boolean gender = man.isSelected()?true:false;
		int salary = (Integer)spSalary.getValue();
		Department dno = (Department)cmbDept.getSelectedItem();
		Date hire_date = null;
		try {
			hire_date = new SimpleDateFormat("yyyy-MM-dd").parse(tfDate.getText().trim());
		}catch(ParseException e) {
			e.printStackTrace();
		}
		return new Employee(empno, empname, title, manager, salary, gender, dno, hire_date);
		
	}
}
