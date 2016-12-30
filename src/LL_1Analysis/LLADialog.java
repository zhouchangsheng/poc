package LL_1Analysis;

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
import ReversePoland.RPJpanel;

public class LLADialog {
	private static LLADialog dialog = null;
	
	public void showText(){
		final JDialog input_dialog = new JDialog(CompilePrinciple_App.getJframe(),"G文法");
		input_dialog.setVisible(true);
		input_dialog.setSize(250,300);
		input_dialog.setLocation(560,150);
		input_dialog.setLayout(new BorderLayout());
		input_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		String[] arr_title = new String[1];
		arr_title[0]= "无左递归文法";
		
		String[][] arr_content = new String[8][1];
		arr_content[0][0]="E->TG";
		arr_content[1][0]="G->+TG|-TG";
		arr_content[2][0]="G->ε";
		arr_content[3][0]="T->FS";
		arr_content[4][0]="S->*FS|/FS";
		arr_content[5][0]="S->ε";
		arr_content[6][0]="F->(E)";
		arr_content[7][0]="F->i";
		
		
		final JTable table = new JTable(arr_content,arr_title);
		table.setRowHeight(30);
		table.setEnabled(true);
		//table.addComponentListener((ComponentListener) table.getEditorComponent());
	
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
			
				input_dialog.dispose();
			}
			
		});
	}
	
	public void showFirstFollow(){
		final JDialog input_dialog = new JDialog(CompilePrinciple_App.getJframe(),"FIRST和FOLLOW集");
		input_dialog.setVisible(true);
		input_dialog.setSize(482,300);
		input_dialog.setLocation(430,200);
		input_dialog.setLayout(null);
		input_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		String[] arr_title_first = new String[1];
		arr_title_first[0]= "FIRST集";
		
		String[][] arr_content_first = new String[5][1];
		arr_content_first[0][0]="FIRST(E)={(,i}";
		arr_content_first[1][0]="FIRST(F)={(,i}";
		arr_content_first[2][0]="FIRST(T)={(,i}";
		arr_content_first[3][0]="FIRST(S)={*,/,ε}";
		arr_content_first[4][0]="FIRST(G)={+,-,ε}";
		
		
		final JTable table_first = new JTable(arr_content_first,arr_title_first);
		table_first.setRowHeight(30);
		table_first.setEnabled(true);
		table_first.getTableHeader().setEnabled(false);
		
		JScrollPane scrollPane_first = new JScrollPane(table_first);
		scrollPane_first.setBounds(0,0,233,230);
		
		
		String[] arr_title_follow = new String[1];
		arr_title_follow[0]= "FOLLOW集";
		
		String[][] arr_content_follow = new String[5][1];
		arr_content_follow[0][0]="FOLLOW(E)={),#}";
		arr_content_follow[1][0]="FOLLOW(F)={),#,+,-,*,/}";
		arr_content_follow[2][0]="FOLLOW(T)={),#,+,-}";
		arr_content_follow[3][0]="FOLLOW(S)={),#,+,-}";
		arr_content_follow[4][0]="FOLLOW(G)={),#}";
		
		final JTable table_follow = new JTable(arr_content_follow,arr_title_follow);
		table_follow.setRowHeight(30);
		table_follow.setEnabled(true);
		table_follow.getTableHeader().setEnabled(false);

		JScrollPane scrollPane_follow = new JScrollPane(table_follow);
		scrollPane_follow.setBounds(233, 0, 233, 230);
		
		JButton submit = new JButton("确定");
		submit.setBounds(0,230,482,30);
		submit.setBackground(new Color(166,166,166));
		input_dialog.add(scrollPane_first);
		input_dialog.add(scrollPane_follow);
		input_dialog.add(submit);
		
		submit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			
				input_dialog.dispose();
			}
			
		});
	}
	
	public static LLADialog getDialog(){
		if(dialog ==null){
			dialog = new LLADialog();
		}
		
		return dialog;
	}
}
