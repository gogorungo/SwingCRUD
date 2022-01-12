package org.comstudy21.ex05;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class JTableEx extends JFrame {
	Container conPan= getContentPane();
	JTable table;
	DefaultTableModel dTable;
	JScrollPane scrollPane;
	
	Object[][] rowData;
	Object[] colNames;
	
	JPanel leftPan, leftInPan1, leftInPan2, leftInPan3, leftInPan4, botPan;
	JLabel numLa, nameLa, emailLa, phoneLa;
	JTextField numTf, nameTf, emailTf, phoneTf;
	JButton allB, insertB, deleteB, searchB, cancleB;
	
	public JTableEx() {
		this("",700,300);
		
	}

	public JTableEx(String title, int w, int h) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("전체보기");
		setSize(w,h);
		
		Toolkit tk = this.getToolkit().getDefaultToolkit();
		int scrWidth = (int)tk.getScreenSize().getWidth();
		int scrHeigth = (int)tk.getScreenSize().getHeight();
		
		int x = scrWidth/2 - w/2;
		int y = scrHeigth/2 - h/2;
		
		this.setLocation(x,y);
		
		display();
	}



	private void display() {
		conPan = getContentPane();
		botPan = new JPanel(new FlowLayout());
		leftPan = new JPanel(new GridLayout(5,1));
		leftInPan1 = new JPanel();
		leftInPan2 = new JPanel();
		leftInPan3 = new JPanel();
		leftInPan4 = new JPanel();
		
		numLa = new JLabel("번        호");
		nameLa = new JLabel("이        름");
		emailLa = new JLabel("이  메  일");
		phoneLa = new JLabel("전화번호");
		numTf = new JTextField(10);
		nameTf = new JTextField(10);
		emailTf = new JTextField(10);
		phoneTf = new JTextField(10);

		
		conPan.add(BorderLayout.WEST, leftPan);
		conPan.add(BorderLayout.SOUTH, botPan);
		
		leftPan.add(leftInPan1);
		leftPan.add(leftInPan2);
		leftPan.add(leftInPan3);
		leftPan.add(leftInPan4);
		
		leftInPan1.add(numLa);
		leftInPan2.add(nameLa);
		leftInPan3.add(emailLa);
		leftInPan4.add(phoneLa);
		
		leftInPan1.add(numTf);
		leftInPan2.add(nameTf);
		leftInPan3.add(emailTf);
		leftInPan4.add(phoneTf);
		
		allB = new JButton("전체보기");
		insertB = new JButton("추 가");
		deleteB = new JButton("삭 제");
		searchB = new JButton("검 색");
		cancleB = new JButton("취 소");
		
		botPan.add(allB);
		botPan.add(insertB);
		botPan.add(deleteB);
		botPan.add(searchB);
		botPan.add(cancleB);

		JTableEx();
	}

	private void JTableEx() {
		colNames = new Object[] {"번호","이름","이메일","전화번호"};
		rowData = new Object[][] {
			{1, "김씨","Kim@kim.com","1111-1111"},
			{2, "이씨","Lee@lee.com","2222-2222"},
			{3, "박씨","Park@park.com","3333-3333"},
			{4, "강씨","Kang@kang.com","4444-4444"},
			{5, "홍씨","Hong@hong.com","5555-5555"}
		};
		
		dTable = new DefaultTableModel(rowData, colNames);
		table = new JTable(dTable);
		
		scrollPane = new JScrollPane(table);
		conPan.add(scrollPane);
		
	}

	public static void main(String[] args) {
		new JTableEx().setVisible(true);
	}
}
