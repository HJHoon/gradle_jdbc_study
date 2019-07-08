package exam_study.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exam_study.dao.DepartmentDao;
import exam_study.dao.EmployeeDao;
import exam_study.dao.TitleDao;
import exam_study.daoimpl.DepartmentDapImpl;
import exam_study.daoimpl.EmployeeDaoImpl;
import exam_study.daoimpl.TitleDaoImpl;
import exam_study.dto.Department;
import exam_study.dto.Employee;
import exam_study.dto.Title;
import exam_study.ui.content.PanelEmployee;
import exam_study.ui.content.PanelEmployeeList;

@SuppressWarnings("serial")
public class EmployeeUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	
	private List<Title> titleList;
	private List<Department> deptList;
	private List<Employee> empList;
	
	private EmployeeDao empdao;
	private DepartmentDao deptdao;
	private TitleDao tdao;
	
	
	private JButton btnAdd;
	private JButton btnCancel;

	private PanelEmployee pEmpText;
	
	private PanelEmployeeList pEmpList;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeUI frame = new EmployeeUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EmployeeUI() {
		 empdao = new EmployeeDaoImpl();
		 deptdao = new DepartmentDapImpl();
		 tdao = new TitleDaoImpl();
		
		 
		initComponents();
	}
	private void initComponents() {
		setTitle("사원 관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 574, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		pEmpText = new PanelEmployee();
		try {
			empList=empdao.selectEmployeeByAll();
			deptList=deptdao.selectDepartmentByAll();
			titleList=tdao.selectTitleByAll();
			pEmpText.setTitles(titleList);
			pEmpText.setManagements(empList);
			pEmpText.setEmpTf(); // set 해줌 함수 밑에 호출 해야함 !!!
			pEmpText.setDepartments(deptList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pEmpText.setBounds(5, 5, 541, 191);
		contentPane.add(pEmpText);
		
		pEmpList = new PanelEmployeeList();
		
		pEmpList.setEmpList(empList);
		pEmpList.reloadData();
		
		pEmpList.setBounds(5, 227, 541, 249);
		contentPane.add(pEmpList);
		
		JPanel pBtn = new JPanel();
		pBtn.setBounds(5, 195, 541, 33);
		contentPane.add(pBtn);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Employee emp = pEmpText.getEmployee();
		
		int res;
		try {
			res = empdao.insertEmployee(emp);
			if(res != -1) {
				JOptionPane.showMessageDialog(null, String.format("%s 사원이 추가되었습니다.",emp.getEmpName()));
				refresh();
			}
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
	private void refresh() throws SQLException{
		pEmpText.setManagements(empdao.selectEmployeeByAll());
		pEmpList.setEmpList(empdao.selectEmployeeByAll());
		pEmpList.reloadData();
	}

	protected void actionPerformedBtnCancel(ActionEvent e) {
	}
}
