package WordAnalysis;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import Main.CompilePrinciple_App;

public class WADialog{
	private static WADialog dialog = null;

	public WADialog(){
	}

	public void keyShowDialog(){
		final JDialog key_dialog = new JDialog(CompilePrinciple_App.getJframe(),"关键词");
		key_dialog.setVisible(true);
		key_dialog.setSize(400,500);
		key_dialog.setLocation(500,120);
		key_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		Container container = key_dialog.getContentPane();
		container.setLayout(null);

		JButton button = new JButton("确定");
		button.setBounds(230,60,90,40);
		container.add(button);

		JButton buttonAdd = new JButton("增加");
		buttonAdd.setBounds(100,60,90,40);
		container.add(buttonAdd);

		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				key_dialog.dispose();
			}
		});

		buttonAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){

			}

		});

	}

	public void separatorShowDialog(){
		final JDialog separator_dialog = new JDialog(CompilePrinciple_App.getJframe(),"分隔符");
		separator_dialog.setVisible(true);
		separator_dialog.setSize(400,500);
		separator_dialog.setLocation(500,120);
		separator_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		Container container = separator_dialog.getContentPane();
		container.setLayout(null);

		JButton button = new JButton("确定");
		button.setBounds(230,60,90,40);
		container.add(button);

		JButton buttonAdd = new JButton("增加");
		buttonAdd.setBounds(100,60,90,40);
		container.add(buttonAdd);

		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				separator_dialog.dispose();
			}
		});

		buttonAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){

			}

		});
	}

	public void lookKeyDialog(){
		if(WAEventHandler.getWAEvent().getKeyArr().isEmpty()){
			final JDialog key_dialog = new JDialog(CompilePrinciple_App.getJframe(),"错误提示");
			key_dialog.setVisible(true);
			key_dialog.setSize(150,100);
			key_dialog.setLocation(580,250);
			key_dialog.setLayout(null);
			key_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			JLabel key_text = new JLabel("请先输入关键字");
			key_text.setForeground(Color.black);
			key_text.setBounds(20, 0, 100, 20);
			key_text.setToolTipText("请先输入关键字表");


			JButton key_button = new JButton("确定");
			key_button.setBounds(45, 30, 60, 30);

			key_dialog.getContentPane().add(key_text);
			key_dialog.getContentPane().add(key_button);

			key_button.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					key_dialog.dispose();
				}

			});
		}else{
			final JDialog key_dialog = new JDialog(CompilePrinciple_App.getJframe(),"关键字表");
			key_dialog.setVisible(true);
			key_dialog.setSize(400,500);
			key_dialog.setLocation(500,120);
			key_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			String[] arr_title = new String[2];
			String[][] arr_content = new WAArrayDistri().getStr(2, WAEventHandler.getWAEvent().getKeyArr());
			arr_title[0]= "指针";
			arr_title[1] = "关键字";


			JTable table = new JTable(arr_content,arr_title);
			table.setRowHeight(30);
			table.setEnabled(false);
			table.getTableHeader().setEnabled(false);

			JScrollPane scrollPane = new JScrollPane(table);


			key_dialog.add(scrollPane);
		}
	}

	public void lookSeparatorDialog(){
		if(WAEventHandler.getWAEvent().getSeparatorArr().isEmpty()){
			final JDialog separator_dialog = new JDialog(CompilePrinciple_App.getJframe(),"错误提示");
			separator_dialog.setVisible(true);
			separator_dialog.setSize(150,100);
			separator_dialog.setLocation(580,250);
			separator_dialog.setLayout(null);
			separator_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			JLabel separator_text = new JLabel("请先输入分隔符");
			separator_text.setForeground(Color.black);
			separator_text.setBounds(20, 0, 100, 20);
			separator_text.setToolTipText("请先输入分隔符表");


			JButton separator_button = new JButton("确定");
			separator_button.setBounds(45, 30, 60, 30);

			separator_dialog.getContentPane().add(separator_text);
			separator_dialog.getContentPane().add(separator_button);

			separator_button.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					separator_dialog.dispose();
				}

			});
		}else{
			final JDialog separator_dialog = new JDialog(CompilePrinciple_App.getJframe(),"分界符表");
			separator_dialog.setVisible(true);
			separator_dialog.setSize(400,500);
			separator_dialog.setLocation(500,120);
			separator_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			int height = 0;
			int content_height = 0;

			//算术运算符表
			String[] arr_arithMetic_title = new String[2];
			arr_arithMetic_title[0]= "指针";
			arr_arithMetic_title[1] = "算术运算符";

			String[][] arr_arithMetic_content = new WAArrayDistri().getStr(2, WAEventHandler.getWAEvent().getSeparatorArr() , "算术运算符");

			int arithMetic_length = arr_arithMetic_content.length;

			content_height = 30+arithMetic_length*30;

			JTable table_arithMetic = new JTable(arr_arithMetic_content,arr_arithMetic_title);
			table_arithMetic.setRowHeight(30);
			table_arithMetic.setEnabled(false);
			table_arithMetic.getTableHeader().setEnabled(false);


			JLabel label_arithMetic_operator = new JLabel("算术运算符(ID,符号)【种别：2】");
			JPanel jpanel_arithMetic_operator = new JPanel();
			JPanel jpanel_display_arith = new JPanel();
			jpanel_display_arith.add(label_arithMetic_operator);
			jpanel_arithMetic_operator.setLayout(new BorderLayout());
			jpanel_arithMetic_operator.add(jpanel_display_arith,BorderLayout.NORTH);
			jpanel_arithMetic_operator.add(table_arithMetic,BorderLayout.CENTER);
			jpanel_arithMetic_operator.setBounds(3, height, 362, content_height);
			//System.out.println(.getSize().getHeight());
			height +=content_height;

			//关系运算符表
			String[] arr_relation_title = new String[2];
			arr_relation_title[0]= "指针";
			arr_relation_title[1] = "关系运算符";

			String[][] arr_relation_content = new WAArrayDistri().getStr(2, WAEventHandler.getWAEvent().getSeparatorArr() , "关系运算符");

			int relation_length = arr_relation_content.length;
			content_height = 30+relation_length*30;

			JTable table_relation = new JTable(arr_relation_content,arr_relation_title);
			table_relation.setRowHeight(30);
			table_relation.setEnabled(false);

			JLabel label_relation_operator = new JLabel("关系运算符(ID,符号)【种别：3】");
			JPanel jpanel_relation_operator = new JPanel();
			JPanel jpanel_display_relation = new JPanel();
			jpanel_display_relation.add(label_relation_operator);
			jpanel_relation_operator.setLayout(new BorderLayout());
			jpanel_relation_operator.add(jpanel_display_relation,BorderLayout.NORTH);
			jpanel_relation_operator.add(table_relation,BorderLayout.CENTER);
			jpanel_relation_operator.setBounds(3, height, 362,content_height);
			height +=content_height;


			//逻辑运算符
			String[] arr_logic_title = new String[2];
			arr_logic_title[0]= "指针";
			arr_logic_title[1] = "逻辑运算符";

			String[][] arr_logic_content = new WAArrayDistri().getStr(2, WAEventHandler.getWAEvent().getSeparatorArr() , "逻辑运算符");

			int logic_length = arr_logic_content.length;
			content_height = 30+logic_length*30;

			JTable table_logic = new JTable(arr_logic_content,arr_logic_title);
			table_logic.setRowHeight(30);
			//table.setTableHeader(new JTableHeader(" "));
			table_logic.setEnabled(false);

			JLabel label_logic_operator = new JLabel("逻辑运算符(ID,符号)【种别：4】");
			JPanel jpanel_logic_operator = new JPanel();
			JPanel jpanel_display_logic = new JPanel();
			jpanel_display_logic.add(label_logic_operator);
			jpanel_logic_operator.setLayout(new BorderLayout());
			jpanel_logic_operator.add(jpanel_display_logic,BorderLayout.NORTH);
			jpanel_logic_operator.add(table_logic,BorderLayout.CENTER);
			jpanel_logic_operator.setBounds(3, height, 362,content_height);
			height +=content_height;

			//位操作运算符
			String[] arr_bit_title = new String[2];
			arr_bit_title[0]= "指针";
			arr_bit_title[1] = "位操作运算符";

			String[][] arr_bit_content = new WAArrayDistri().getStr(2, WAEventHandler.getWAEvent().getSeparatorArr() , "位操作运算符");

			int bit_length = arr_bit_content.length;
			content_height = 30+bit_length*30;

			JTable table_bit = new JTable(arr_bit_content,arr_bit_title);
			table_bit.setRowHeight(30);
			table_bit.setEnabled(false);

			JLabel label_bit_operator = new JLabel("位操作运算符(ID,符号)【种别：5】");
			JPanel jpanel_bit_operator = new JPanel();
			JPanel jpanel_display_bit = new JPanel();
			jpanel_display_bit.add(label_bit_operator);
			jpanel_bit_operator.setLayout(new BorderLayout());
			jpanel_bit_operator.add(jpanel_display_bit,BorderLayout.NORTH);
			jpanel_bit_operator.add(table_bit,BorderLayout.CENTER);
			jpanel_bit_operator.setBounds(3, height, 362,content_height);
			height+=content_height;

			//赋值运算符
			String[] arr_assign_title = new String[2];
			arr_assign_title[0]= "指针";
			arr_assign_title[1] = "赋值运算符";

			String[][] arr_assign_content = new WAArrayDistri().getStr(2, WAEventHandler.getWAEvent().getSeparatorArr() , "赋值运算符");

			int assign_length = arr_assign_content.length;
			content_height = 30+assign_length*30;

			JTable table_assign = new JTable(arr_assign_content,arr_assign_title);
			table_assign.setRowHeight(30);
			table_assign.setEnabled(false);

			JLabel label_assign_operator = new JLabel("赋值运算符(ID,符号)【种别：6】");
			JPanel jpanel_assign_operator = new JPanel();
			JPanel jpanel_display_assign = new JPanel();
			jpanel_display_assign.add(label_assign_operator);
			jpanel_assign_operator.setLayout(new BorderLayout());
			jpanel_assign_operator.add(jpanel_display_assign,BorderLayout.NORTH);
			jpanel_assign_operator.add(table_assign,BorderLayout.CENTER);
			jpanel_assign_operator.setBounds(3, height, 362,content_height);
			height+=content_height;

			//条件运算符
			String[] arr_condition_title = new String[2];
			arr_condition_title[0]= "指针";
			arr_condition_title[1] = "条件运算符";

			String[][] arr_condition_content = new WAArrayDistri().getStr(2, WAEventHandler.getWAEvent().getSeparatorArr() , "条件运算符");

			int condition_length = arr_condition_content.length;
			content_height = 30+condition_length*30;

			JTable table_condition = new JTable(arr_condition_content,arr_condition_title);
			table_condition.setRowHeight(30);
			table_condition.setEnabled(false);

			JLabel label_condition_operator = new JLabel("条件运算符(ID,符号)【种别：7】");
			JPanel jpanel_condition_operator = new JPanel();
			JPanel jpanel_display_condition = new JPanel();
			jpanel_display_condition.add(label_condition_operator);
			jpanel_condition_operator.setLayout(new BorderLayout());
			jpanel_condition_operator.add(jpanel_display_condition,BorderLayout.NORTH);
			jpanel_condition_operator.add(table_condition,BorderLayout.CENTER);
			jpanel_condition_operator.setBounds(3, height, 362,content_height);
			height+=content_height;

			//分隔符
			String[] arr_boundary_title = new String[2];
			arr_boundary_title[0]= "指针";
			arr_boundary_title[1] = "分隔符";

			String[][] arr_boundary_content = new WAArrayDistri().getStr(2, WAEventHandler.getWAEvent().getSeparatorArr() , "分隔符");

			int boundary_length = arr_boundary_content.length;
			content_height = 30+boundary_length*30;

			JTable table_boundary = new JTable(arr_boundary_content,arr_boundary_title);
			table_boundary.setRowHeight(30);
			table_boundary.setEnabled(false);

			JLabel label_boundary_operator = new JLabel("分隔符(ID,符号)【种别：8】");
			JPanel jpanel_boundary_operator = new JPanel();
			JPanel jpanel_display_boundary = new JPanel();
			jpanel_display_boundary.add(label_boundary_operator);
			jpanel_boundary_operator.setLayout(new BorderLayout());
			jpanel_boundary_operator.add(jpanel_display_boundary,BorderLayout.NORTH);
			jpanel_boundary_operator.add(table_boundary,BorderLayout.CENTER);
			jpanel_boundary_operator.setBounds(3, height, 362,content_height);
			height+=content_height;

			//记号符
			String[] arr_remark_title = new String[2];
			arr_remark_title[0]= "指针";
			arr_remark_title[1] = "记号符";

			String[][] arr_remark_content = new WAArrayDistri().getStr(2, WAEventHandler.getWAEvent().getSeparatorArr() , "记号符");
			int remark_length = arr_remark_content.length;
			content_height = 30+remark_length*30;

			JTable table_remark = new JTable(arr_remark_content,arr_remark_title);
			table_remark.setRowHeight(30);
			table_remark.setEnabled(false);

			JLabel label_remark_operator = new JLabel("记号符(ID,符号)【种别：0】");
			JPanel jpanel_remark_operator = new JPanel();
			JPanel jpanel_display_remark = new JPanel();
			jpanel_display_remark.add(label_remark_operator);
			jpanel_remark_operator.setLayout(new BorderLayout());
			jpanel_remark_operator.add(jpanel_display_remark,BorderLayout.NORTH);
			jpanel_remark_operator.add(table_remark,BorderLayout.CENTER);
			jpanel_remark_operator.setBounds(3, height, 362,content_height);
			height+=content_height;

			//总面板
			JPanel jpanel = new JPanel();
			jpanel.setLayout(null);
			jpanel.setPreferredSize(new Dimension(360,height));
			jpanel.add(jpanel_arithMetic_operator);
			jpanel.add(jpanel_relation_operator);
			jpanel.add(jpanel_logic_operator);
			jpanel.add(jpanel_bit_operator);
			jpanel.add(jpanel_assign_operator);
			jpanel.add(jpanel_condition_operator);
			jpanel.add(jpanel_boundary_operator);
			jpanel.add(jpanel_remark_operator);

			JScrollPane scrollPane = new JScrollPane(jpanel);

			separator_dialog.add(scrollPane);
		}
	}

	public static WADialog getDialog(){
		if(dialog==null){
			dialog = new WADialog();
		}

		return dialog;
	}
}
