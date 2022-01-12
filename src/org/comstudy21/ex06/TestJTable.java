package org.comstudy21.ex06;

import static org.comstudy21.ex06.R.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.comstudy21.guiex.myframe.MyJFrame;

public class TestJTable extends MyJFrame {

	
	
	public TestJTable() {
		super("JTable 연습",640,480);
	}
	
	private void mkTableData() {
		columnNames = new Vector();
		columnNames.add("IDX");
		columnNames.add("NAME");
		columnNames.add("EMAIL");
		columnNames.add("PHONE");
		
		data = dao.selectAll();
	}
	
	@Override
	protected void displayLayer() {
		mkTableData();
		contentPan = getContentPane();
		
		contentPan.add(BorderLayout.WEST, new LeftPane());
		contentPan.add(BorderLayout.SOUTH, new BottomPane());
		
		//랩핑
		
		tbModel = new DefaultTableModel(data,columnNames);
		table = new JTable(tbModel);
		
		scrollPane = new JScrollPane(table);
		contentPan.add(scrollPane);
	}
	
	private void addRowDataTest() {
		tbModel.setDataVector(null, columnNames);
		tbModel.addRow(new Object[] {4,"aaa","aaa@naver.com","010-4444-4444"});
		tbModel.addRow(new Object[] {5,"bbb","bbb@naver.com","010-5555-5555"});
	}
	
	private void addRowData(Object[] dto) {
		tbModel.addRow(dto);
		
	}
	private void displayList() {
		tbModel.setDataVector(null, columnNames);
		Vector<Vector> saramList = dao.selectAll();
		for(Vector vector : saramList) {
			tbModel.addRow(vector);
		}
	}
	
	private void txtclear() {
		txtFld1.setText("");
		txtFld2.setText(""); 
		txtFld3.setText(""); 
		txtFld4.setText("");
	}
	
	private void displayOne(Vector vSearch) {
		tbModel.setDataVector(null, columnNames);
		tbModel.addRow(vSearch);
	}
	
	@Override
	protected void actionEvent() {
		//테이블 이벤트 핸들러 추가
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {
				//System.out.println(">>> 마우스를 눌렀다");
				JTable tbl = (JTable)e.getSource(); // 테이블
				//전체 행과 열
				int totalCol = tbl.getColumnCount();
				int totalRow = tbl.getRowCount();
				//선택한 컬럼의 행과 열
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				// 데이터 가져오기
				int idx = (int)tbModel.getValueAt(row, 0);
				String name = (String)tbModel.getValueAt(row, 1);
				String email = (String)tbModel.getValueAt(row, 2);
				String phone = (String)tbModel.getValueAt(row, 3);
				//System.out.println(idx +", "+name+", "+email+", "+phone);
				txtFld1.setText(""+idx);
				txtFld2.setText(name);
				txtFld3.setText(email);
				txtFld4.setText(phone);
			
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		
		
		// 버튼 이벤트 핸들러 추가
		allBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				displayList();
				txtclear();
			}
		});
		inputBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//textField의 데이터를 읽어온다
				String name = txtFld2.getText();
				String email = txtFld3.getText();
				String phone = txtFld4.getText();
				txtclear();
				// TableModel 에 반영해주기
				// dao에 저장 후 
				dao.insert(new SaramDto(0, name, email, phone));
				// list를 다시 그려준다.
				displayList();
				
				//addRowData(new Object[] {sequence++,name,email,phone});
			}


		});
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					displayOne(dao.search(txtFld2.getText()));
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(TestJTable.this,"이름을 입력하세요!");
				} finally {
					txtclear();
				}
			}
		});
		modifyBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Vector vector = new Vector();
					vector = dao.search(txtFld2.getText());
					int idx = (int) vector.get(0);
					String name = txtFld2.getText();
					String email = txtFld3.getText();
					String phone = txtFld4.getText();
					dao.modify(new SaramDto(idx, name, email, phone));
					displayList();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(TestJTable.this,"해당 정보가 없습니다!");
				} finally {
					txtclear();
				}
			}
		});
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dao.delete(txtFld2.getText());
					displayList();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(TestJTable.this,"해당 정보가 없습니다!");
				} finally {
					txtclear();
				}
			}
		});
		finishBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(TestJTable.this,"굿바이~");
				dispose();
				System.exit(0);		
			}
		});	
	}

	
	public static void main(String[] args) {
		new TestJTable().setVisible(true);
	}
}
