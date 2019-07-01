package gradle_jdbc_study.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gradle_jdbc_study.dao.DepartmentDao;
import gradle_jdbc_study.daoimpl.DepartmentDaoImpl;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ErpManagementUI extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JButton btnDeptDelete;
	private JButton btnDeptUpdate;
	private JButton btnDeptSearch;
	private JButton btnDeptAdd;
	private JButton btnDeptList;
	
	private DepartmentDao deptDao;
	private JButton btnDeptAdd_1;
	private JButton btnDeptUpdate_1;
	private JButton btnDeptDelete_1;
	private JButton btnDeptSearch_1;
	private JButton btnDeptList_1;
	

	
	public ErpManagementUI() {
		deptDao = new DepartmentDaoImpl();
		initComponents();
	}


	private void initComponents() {
		setTitle("부서 관리 메뉴");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 704, 177);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 10, 10));
		
		JPanel pDept = new JPanel();
		contentPane.add(pDept);
		pDept.setLayout(new GridLayout(1, 0, 10, 0));
		
		btnDeptAdd_1 = new JButton("부서 추가");
		btnDeptAdd_1.addActionListener(this);
		pDept.add(btnDeptAdd_1);
		
		btnDeptUpdate_1 = new JButton("부서 수정");
		btnDeptUpdate_1.addActionListener(this);
		pDept.add(btnDeptUpdate_1);
		
		btnDeptDelete_1 = new JButton("부서 삭제");
		btnDeptDelete_1.addActionListener(this);
		pDept.add(btnDeptDelete_1);
		
		btnDeptSearch_1 = new JButton("부서 검색");
		btnDeptSearch_1.addActionListener(this);
		pDept.add(btnDeptSearch_1);
		
		btnDeptList_1 = new JButton("부서 목록");
		btnDeptList_1.addActionListener(this);
		pDept.add(btnDeptList_1);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnDeptSearch_1) {
			actionPerformedBtnDeptSearch_1(e);
		}
		if (e.getSource() == btnDeptDelete_1) {
			actionPerformedBtnDeptDelete_1(e);
		}
		if (e.getSource() == btnDeptList_1) {
			actionPerformedBtnDeptList_1(e);
		}
		if (e.getSource() == btnDeptUpdate_1) {
			actionPerformedBtnDeptUpdate_1(e);
		}
		if (e.getSource() == btnDeptAdd_1) {
			actionPerformedBtnDeptAdd_1(e);
		}
		if (e.getSource() == btnDeptDelete_1) {
			String deptNo = JOptionPane.showInputDialog("삭제할 부서번호를 입력하세요");
		}
	}
	protected void actionPerformedBtnDeptAdd_1(ActionEvent e) {
		
	}
	protected void actionPerformedBtnDeptUpdate_1(ActionEvent e) {
		
	}
	protected void actionPerformedBtnDeptList_1(ActionEvent e) {
		
	}
	protected void actionPerformedBtnDeptDelete_1(ActionEvent e) {
		
	}
	protected void actionPerformedBtnDeptSearch_1(ActionEvent e) {
		
	}
}
