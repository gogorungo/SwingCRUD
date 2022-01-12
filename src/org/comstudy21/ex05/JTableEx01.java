package org.comstudy21.ex05;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JTableEx01 extends JFrame {
	Container contentPane;
	JTable table;
	DefaultTableModel tableModel;
	JScrollPane scrollPane;
	
	Object[][] rowData;
	Object[] colNames;
	
	public JTableEx01() {
		init();
		start();
	}
	

	private void start() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	private void init() {
		contentPane = getContentPane();
		contentPane.setBackground(Color.white);
		setSize(640, 480);
		//데이터와 테이블 생성
		mkTbl();
	}

	private void mkTbl() {
		// 표 위에 목록
		colNames = new Object[] {"idx","name","phone"};
		rowData = new Object[][] {
			{1, "Kim","1111-1111"},
			{2, "lee","2222-2222"},
			{3, "park","3333-3333"},
			{4, "kang","4444-4444"},
			{5, "hong","5555-5555"}
		};
		
		tableModel = new DefaultTableModel(rowData, colNames);
		table = new JTable(tableModel);
		
		scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane);
		
		//// ----- 내용 변경
		tableModel.setDataVector(null, colNames); // 내용 비움
		tableModel.addRow(new Object[] {3, "ccc","7777"});
		tableModel.addRow(new Object[] {2, "bbb","8888"});
		tableModel.addRow(new Object[] {1, "aaa","9999"});
		
	}

	public static void main(String[] args) {
		new JTableEx01().setVisible(true);
	}
}
