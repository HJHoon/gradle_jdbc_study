package exam_study.ui.content;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import exam_study.dto.Employee;

@SuppressWarnings("serial")
public class PanelEmployeeList extends JPanel {

	private List<Employee> empList;
	private JTable table;
	
	public PanelEmployeeList() {
		
		initComponents();
	}
	private void initComponents() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
	
	
	public void reloadData() {
		table.setModel(new DefaultTableModel(getRows(), getColumnNames()));
		
		tableCellAlignment(SwingConstants.CENTER, 0, 1, 2, 4, 5, 6, 7);
		tableCellAlignment(SwingConstants.RIGHT, 3);
		tableSetWidth(100, 100, 90, 180, 70, 140, 180, 190);
	}
	private String[] getColumnNames() {
		return new String[] {"번호","사원명","직책","급여","성별","부서","직속상사","입사일"};
	}
	private Object[][] getRows() {
		Object[][] rows = new Object[empList.size()][];
		for (int i = 0; i < empList.size(); i++) {
			rows[i] = empList.get(i).toArray();
		}
		return rows;
	}
	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}
	
	// 테이블 셀 내용의 정렬
		protected void tableCellAlignment(int align, int... idx) {
			DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
			dtcr.setHorizontalAlignment(align);

			TableColumnModel model = table.getColumnModel();
			for (int i = 0; i < idx.length; i++) {
				model.getColumn(idx[i]).setCellRenderer(dtcr);
			}
		}

		// 테이블 셀의 폭 설정
		protected void tableSetWidth(int... width) {
			TableColumnModel cModel = table.getColumnModel();

			for (int i = 0; i < width.length; i++) {
				cModel.getColumn(i).setPreferredWidth(width[i]);
			}
		}
}
