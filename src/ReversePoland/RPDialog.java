package ReversePoland;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import Main.CompilePrinciple_App;

public class RPDialog{
	protected static final String Dao = null;
	private static RPDialog dialog = null;

	public void showInputDialog(){
		final JDialog input_dialog = new JDialog(CompilePrinciple_App.getJframe(),"输入数据");
		input_dialog.setVisible(true);
		input_dialog.setSize(250,300);
		input_dialog.setLocation(560,150);
		input_dialog.setLayout(new BorderLayout());
		input_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		String[] arr_title = new String[2];
		arr_title[0]= "变量名";
		arr_title[1] = "数值";



		final JTable table = new JTable(RPJpanel.getJpanel().getKeyValue(),arr_title);
		table.setRowHeight(30);
		table.setEnabled(true);
		table.addComponentListener((ComponentListener) table.getEditorComponent());

		table.getTableHeader().setEnabled(false);
		//table.setModel();

		JScrollPane scrollPane = new JScrollPane(table);

		JButton submit = new JButton("确定");
		submit.setBackground(new Color(166,166,166));
		input_dialog.add(scrollPane,BorderLayout.CENTER);
		input_dialog.add(submit,BorderLayout.SOUTH);

		submit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				table.getCellEditor().stopCellEditing();
				int row_count = table.getRowCount();
				for(int i=0;i<row_count;i++){
					RPJpanel.getJpanel().setKeyValue((String)table.getValueAt(i, 0), (String)table.getValueAt(i,1));
				}

				input_dialog.dispose();
			}

		});



	}

	public static RPDialog getDialog(){
		if(dialog ==null){
			dialog = new RPDialog();
		}
		return dialog;
	}

}
