package exam_study;

import java.awt.EventQueue;

import exam_study.ui.ErpManagementUI;

public class ErpMain {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErpManagementUI frame = new ErpManagementUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
