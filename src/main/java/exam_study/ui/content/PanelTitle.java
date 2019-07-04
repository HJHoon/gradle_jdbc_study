package exam_study.ui.content;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import exam_study.dto.Title;

@SuppressWarnings("serial")
public class PanelTitle extends JPanel {
	private JTextField tfTno;
	private JTextField tfTname;
	private Title searchTitleNo;
	private Title nextTitle;
	
	public void setSearchTitleNo(Title searchTitleNo) {
		this.searchTitleNo = searchTitleNo;
	}

	public PanelTitle() {

		initComponents();
	}
	
	private void initComponents() {
		setLayout(new GridLayout(0, 2, 10, 5));
		
		JLabel lblTno = new JLabel("번호");
		lblTno.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTno);
		
		tfTno = new JTextField();
		add(tfTno);
		tfTno.setColumns(10);
		
		JLabel lblTname = new JLabel("직책명");
		lblTname.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTname);
		
		tfTname = new JTextField();
		tfTname.setColumns(10);
		add(tfTname);
	}
	
	public Title getTitle() {
		int tno = Integer.parseInt(tfTno.getText().trim());
		String tname = tfTname.getText().trim();
		
		return new Title(tno, tname);
	}
	
	public Title getUpdateTitle() {
		int tno = searchTitleNo.getTitleNo();
		String tname = tfTname.getText().trim();
		
		return new Title(tno, tname);
	}
	
	public void setTitle(Title title) {
		tfTno.setText(title.getTitleNo()+"");
		tfTname.setText(title.getTitleName());
	}
	
	public void clearTf() {
		tfTno.setText("");
		tfTname.setText("");
	}
	
	public void setTitle(List<Title> list) {
		nextTitle = list.get(list.size()-1);
		String no = String.format("T%03d", nextTitle.getTitleNo()+1);
		tfTno.setText(no);
		tfTno.setEnabled(false);
		tfTname.setText("");
	}
}
