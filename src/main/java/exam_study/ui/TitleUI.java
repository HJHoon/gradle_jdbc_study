package exam_study.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exam_study.dao.TitleDao;
import exam_study.daoimpl.TitleDaoImpl;
import exam_study.dto.Title;
import exam_study.ui.content.PanelTitle;
import exam_study.ui.content.PanelTitleList;

@SuppressWarnings("serial")
public class TitleUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	
	private TitleDao dao;
	private List<Title> titleList;

	private PanelTitleList pTitleList;

	private PanelTitle pTitle;
	private JButton btnCancel;
	private JButton btnAdd;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TitleUI frame = new TitleUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TitleUI() throws SQLException {
		dao = new TitleDaoImpl();
		titleList = dao.selectTitleByAll();
		
		initComponents();
	}
	
	private void initComponents() {
		setTitle("직책 관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pTitle = new PanelTitle();
		contentPane.add(pTitle);
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);
		pBtn.setLayout(new BoxLayout(pBtn, BoxLayout.X_AXIS));
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);
		
		pTitleList = new PanelTitleList();
		pTitleList.setTitleList(titleList);
		pTitleList.reloadData();
		contentPane.add(pTitleList);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			if (btnAdd.getText().equals("추가")) {
				actionPerformedBtnAdd(e);
			}else {
				actionPerformedBtnUpdate(e);
			}
		}
		if (e.getSource() == btnCancel) {
			try {
				actionPerformedBtnCancel(e);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private void actionPerformedBtnUpdate(ActionEvent e) {
		Title title = pTitle.getUpdateTitle();
		
		int res = -1;
		try {
			res = dao.updateTitle(title);
			refresh();
			btnAdd.setText("추가");
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	protected void actionPerformedBtnCancel(ActionEvent e) {
		pTitle.clearTf();
	}
	
	protected void actionPerformedBtnAdd(ActionEvent e) throws SQLException {
		Title title = pTitle.getTitle(); //panel에 입력한 정보를 객체로 받아옴
		
		dao.insertTitle(title);
		JOptionPane.showMessageDialog(null, String.format("%s 직책이 추가되었습니다.", title.getTitleName()));
		pTitle.clearTf();
		pTitleList.setTitleList(dao.selectTitleByAll());
		pTitleList.reloadData();
	}
	
	public void refresh() throws SQLException{
		pTitle.setTitle(dao.selectTitleByAll());
		pTitleList.setTitleList(dao.selectTitleByAll());
		pTitleList.reloadData();
	}
	
	public void updateTitleUI(Title searchTitle) {
		pTitle.setTitle(searchTitle);
		btnAdd.setText("수정");
		pTitle.setSearchTitleNo(searchTitle);
	}
	
	public void deleteTitleUI(Title searchTitle) {
		int res = -1;
		
		try {
			res = dao.deleteTitle(searchTitle);
			JOptionPane.showMessageDialog(null, "삭제완료");
			refresh();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
