package ReversePoland;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Main.CompilePrinciple_App;

public class RPJpanel extends JPanel{
	private static RPJpanel jpanel = null;
	private JTextArea input_textArea = null;
	private JTextArea display_textArea = null;
	private JTextField result_textField =null;
	private JTable table =null;
	private ArrayList<String> input_data;
	private ArrayList<String> input_apha_data =null;
	private String [][]input_str =null;
	private String [][]result_str = null;
	private Boolean isExpression =false;
	private ArrayList<String> list_progress = null;
	private ArrayList<String> list_simple_out = null;

	private JScrollPane pane_input=null,pane_display=null,pane_table=null;
	private JButton button_input=null,button_submit=null;

	private RPJpanel(){
		this.setLayout(null);

		input_data = new ArrayList<String>();
		list_progress = new ArrayList<String>();
		list_simple_out = new ArrayList<String>();

		JLabel title = new JLabel("逆波兰");
		title.setEnabled(true);
		title.setOpaque(true);
		title.setForeground(Color.black);
		title.setBounds(320,20,60,40);
		this.add(title);

		JLabel tip_input = new JLabel("请在下面输入语句");
		tip_input.setBounds(3,65,120,40);
		tip_input.setForeground(Color.black);
		this.add(tip_input);

		button_input = new JButton("输入数据");
		button_input.setMargin(new Insets(5,5,5,5));
		button_input.setBounds(613, 102, 70, 33);
		button_input.setBackground(new Color(166,166,166));
		button_input.addActionListener(RPEventHandler.getRPEventHandler());
		button_input.setActionCommand("button_input");

		button_submit = new JButton("提交");
		button_submit.setBounds(613, 140, 70, 30);
		button_submit.setBackground(new Color(166,166,166));
		button_submit.addActionListener(RPEventHandler.getRPEventHandler());
		button_submit.setActionCommand("button_submit");

		input_textArea = new JTextArea();
		input_textArea.setSize(600, 75);
		input_textArea.setMargin(new Insets(2,2,2,2));
		pane_input = new JScrollPane(input_textArea);
		pane_input.setBounds(0, 100, 600, 75);

		display_textArea = new JTextArea();
		display_textArea.setSize(180,230);
		display_textArea.setMargin(new Insets(2,2,2,2));
		display_textArea.setEditable(false);
		display_textArea.setBackground(Color.white);
		pane_display =new JScrollPane(display_textArea);
		pane_display.setBounds(544, 215, 150,230);


		String[] table_title= new String[5];
		table_title[0] = "步骤";table_title[1] = "当前符号"; table_title[2]="输入区";table_title[3]="运算符号栈";table_title[4] ="输出区";

		table = new JTable(new String[0][0],table_title);
		table.getTableHeader().setEnabled(false);
		pane_table = new JScrollPane(table);
		pane_table.setBounds(0, 215, 544, 230);


		result_textField =new JTextField();
		result_textField.setEditable(false);
		result_textField.setMargin(new Insets(5,1,1,1));
		result_textField.setBounds(0, 175, 694,40);

		this.add(button_input);
		this.add(button_submit);
		this.add(pane_display);
		this.add(pane_input);
		this.add(pane_table);
		this.add(result_textField);
	}

	public void paint(Graphics g){
		super.paint(g);



		/*g.setColor(Color.green);
		g.fillRect(0, 175, 694,40);

		g.setColor(Color.blue);
		g.fillRect(0, 215, 694, 230);

		g.setColor(Color.yellow);
		g.fillRect(514, 215, 180,230);

		g.setColor(Color.black);
		g.fillRect(0, 100, 600, 75);

		g.setColor(Color.white);
		g.fillRect(613, 100, 70, 30);
		g.fillRect(613, 140, 70, 30);*/
	}

	public void showResult(){
		getKeyValue();

		if(isModifiedof_input_str_value()){
			final JDialog input_tip_dialog = new JDialog(CompilePrinciple_App.getJframe(),"错误提示");
			input_tip_dialog.setVisible(true);
			input_tip_dialog.setSize(190,100);
			input_tip_dialog.setLocation(560,250);
			input_tip_dialog.setLayout(null);
			input_tip_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			JLabel input_tip_text = new JLabel("请先给变量赋值");
			input_tip_text.setForeground(Color.black);
			input_tip_text.setBounds(46, 5, 170, 20);
			input_tip_text.setToolTipText("请先给变量赋值");


			JButton input_tip_button = new JButton("确定");
			input_tip_button.setBackground(new Color(166,166,166));
			input_tip_button.setBounds(58, 30, 60, 30);

			input_tip_dialog.getContentPane().add(input_tip_text);
			input_tip_dialog.getContentPane().add(input_tip_button);

			input_tip_button.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					input_tip_dialog.dispose();
				}

			});
		}else if((input_textArea.getText().replaceAll("\\s*","")).isEmpty()){
			final JDialog input_tip_dialog = new JDialog(CompilePrinciple_App.getJframe(),"错误提示");
			input_tip_dialog.setVisible(true);
			input_tip_dialog.setSize(190,100);
			input_tip_dialog.setLocation(560,250);
			input_tip_dialog.setLayout(null);
			input_tip_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			JLabel input_tip_text = new JLabel("请先输入语句");
			input_tip_text.setForeground(Color.black);
			input_tip_text.setBounds(46, 5, 170, 20);
			input_tip_text.setToolTipText("请先输入语句");


			JButton input_tip_button = new JButton("确定");
			input_tip_button.setBackground(new Color(166,166,166));
			input_tip_button.setBounds(58, 30, 60, 30);

			input_tip_dialog.getContentPane().add(input_tip_text);
			input_tip_dialog.getContentPane().add(input_tip_button);

			input_tip_button.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					input_tip_dialog.dispose();
				}

			});
		}else{
			rp_compute();
		}


	}

	private String getStrExceptLast(String str,String part){
		char [] str_char = str.toCharArray();
		int length = str_char.length;
		if(length>=2){
			if(((str_char[length-1]+"").equals("+")&&(str_char[length-2]+"").equals("+"))||((str_char[length-1]+"").equals("-")&&(str_char[length-2]+"").equals("-"))){
				if(part.equals("except_lastOne")){
					str =str.substring(0, length-2);
				}

				if(part.equals("lastOne")){
					str =str.substring(length-2);
				}
			}else{
				if(part.equals("except_lastOne")){
					if(str.length()>1){
						str =str.substring(0, str.length()-1);
					}else if(str.length()==1){
						str ="";
					}
				}

				if(part.equals("lastOne")){
					if(str.length()>1){
						str =str.substring(str.length()-1);
					}else if(str.length()==1){
						return str;
					}
				}
			}
		}else{
			if(part.equals("except_lastOne")){
				if(str.length()>1){
					str =str.substring(0, str.length()-1);
				}else if(str.length()==1){
					str ="";
				}
			}

			if(part.equals("lastOne")){
				if(str.length()>1){
					str =str.substring(str.length()-1);
				}else if(str.length()==1){
					return str;
				}
			}
		}


		return str;
	}


	private void rp_compute(){
		isExpression = true;
		list_progress.clear();
		list_simple_out.clear();
		this.remove(result_textField);
		pane_display.remove(display_textArea);
		this.remove(pane_display);
		pane_table.remove(table);
		this.remove(pane_table);
		ArrayList<String> list_step = new ArrayList<String>();
		ArrayList<String> list_present_sign = new ArrayList<String>();
		ArrayList<String> list_compute_sign = new ArrayList<String>();
		ArrayList<String> list_out_area = new ArrayList<String>();
		ArrayList<String> list_input_area = new ArrayList<String>();
		ArrayList<String> input_data_temp = getCopyArrayList(input_data);
		//System.out.println(input_data_temp);

		int step_index = 0;
		String present_sign_str="",compute_sign_str="",out_sign_str="",compute_sign_top_str="";
		present_sign_str += input_data_temp.get(0);
		input_data_temp.remove(0);

		while(input_data_temp.contains("#")||!compute_sign_str.isEmpty()){
			if(present_sign_str.equals("#")){
				if(compute_sign_str.isEmpty()){
					break;
				}else{
					compute_sign_top_str = getStrExceptLast(compute_sign_str,"lastOne");
					list_simple_out.add(compute_sign_top_str);
					setExpreResult();
					out_sign_str +=compute_sign_top_str ;
					compute_sign_str = getStrExceptLast(compute_sign_str,"except_lastOne");
				}


				if(!compute_sign_top_str.matches("(\\^|-|\\+|\\*|/|%|\\+\\+|--)")){
					//System.out.println("非法字符");
					isExpression = false;
					break;
				}
			}else if(present_sign_str.matches("[a-z|A-Z|0-9]+")){
				out_sign_str+=present_sign_str;
				list_simple_out.add(present_sign_str);
				setExpreResult();
			}else if(compute_sign_top_str.isEmpty()||present_sign_str.equals("(")){
				compute_sign_top_str = present_sign_str;
				compute_sign_str+=present_sign_str;
			}else if(present_sign_str.matches("(\\*|/|%)")&&compute_sign_top_str.matches("(-|\\+)")){
				compute_sign_top_str = present_sign_str;
				compute_sign_str+=present_sign_str;
			}else if(present_sign_str.matches("(\\*|/|%)")&&compute_sign_top_str.matches("(\\*|/|%|\\^|--|\\+\\+)")){
				out_sign_str+=compute_sign_top_str;
				list_simple_out.add(compute_sign_top_str);
				setExpreResult();
				compute_sign_str = getStrExceptLast(compute_sign_str,"except_lastOne")+present_sign_str;
				compute_sign_top_str = getStrExceptLast(compute_sign_str,"lastOne");
			}else if(present_sign_str.matches("(\\+|-)")&&compute_sign_top_str.matches("(\\^|-|\\+|\\*|/|%|\\+\\+|--)")){
				out_sign_str+=compute_sign_top_str;
				list_simple_out.add(compute_sign_top_str);
				setExpreResult();
				compute_sign_str = getStrExceptLast(compute_sign_str,"except_lastOne");
				compute_sign_top_str = getStrExceptLast(compute_sign_str,"lastOne");
				System.out.println(compute_sign_top_str);
				continue;
			}else if(present_sign_str.matches("\\^")&&compute_sign_top_str.matches("(\\^|\\+\\+|--)")){
				out_sign_str+=compute_sign_top_str;
				list_simple_out.add(compute_sign_top_str);
				setExpreResult();
				compute_sign_str = getStrExceptLast(compute_sign_str,"except_lastOne")+present_sign_str;
				compute_sign_top_str = getStrExceptLast(compute_sign_str,"lastOne");
			}else if(present_sign_str.matches("\\^")&&compute_sign_top_str.matches("(-|\\+|\\*|/|%)")){
				compute_sign_top_str = present_sign_str;
				compute_sign_str+=present_sign_str;
			}else if(present_sign_str.matches("(\\+\\+|--)")&&compute_sign_top_str.matches("(\\+|-|\\*|%|/|\\^|--|\\+\\+)")){
				out_sign_str+=compute_sign_top_str;
				list_simple_out.add(compute_sign_top_str);
				setExpreResult();
				compute_sign_str = getStrExceptLast(compute_sign_str,"except_lastOne")+present_sign_str;
				compute_sign_top_str = getStrExceptLast(compute_sign_str,"lastOne");
			}else if(present_sign_str.equals(")")){
				if(compute_sign_top_str.equals("(")){
					out_sign_str+="";

					compute_sign_str = getStrExceptLast(compute_sign_str,"except_lastOne");
					compute_sign_top_str = getStrExceptLast(compute_sign_str,"lastOne");

					System.out.println("sign_str:"+compute_sign_str.length()+":"+compute_sign_str);
					System.out.println("sign_top_str:"+compute_sign_top_str.length()+":"+compute_sign_top_str);

					present_sign_str = "";
				}else if(!compute_sign_top_str.equals(")")){
					out_sign_str+=compute_sign_top_str;
					list_simple_out.add(compute_sign_top_str);
					setExpreResult();
					compute_sign_str = getStrExceptLast(compute_sign_str,"except_lastOne");
					compute_sign_top_str = getStrExceptLast(compute_sign_str,"lastOne");
				}else{
					//System.out.println("sign_str:"+compute_sign_str.length()+":"+compute_sign_str);
					//System.out.println("sign_top_str:"+compute_sign_top_str.length()+":"+compute_sign_top_str);
					//System.out.println("+++++语法错误");
					isExpression = false;
					break;
				}

			}else if(compute_sign_top_str.equals("(")&&!present_sign_str.equals(")")){
				compute_sign_top_str = present_sign_str;
				compute_sign_str+=present_sign_str;
			}else if(!present_sign_str.matches("([\\^|-|\\+|\\*|/|%|a-z|0-9|A-Z])(--|\\+\\+)")){
				//System.out.println("输入表达式不合法");
				isExpression = false;
				break;
			}
			list_step.add(step_index+"");
			list_input_area.add(getArrayListToString(input_data_temp));
			list_out_area.add(out_sign_str);
			list_compute_sign.add(compute_sign_str);
			list_present_sign.add(present_sign_str);

			if(!present_sign_str.equals(")")&&input_data_temp.size()>0){
				present_sign_str = input_data_temp.get(0);
				input_data_temp.remove(0);
			}
			step_index++;
		}



		if(isExpression){
			int temp_length = list_simple_out.size();
			for(int i=0;i<temp_length;i++){
				setExpreResult();
			}
			result_str = getStrContent(list_step,list_present_sign,list_input_area,list_compute_sign,list_out_area,5);


			result_textField =new JTextField("分析结果：       "+list_out_area.get(list_out_area.size()-1)+"   运算结果：  "+list_simple_out.get(0));
			result_textField.setEditable(false);
			result_textField.setMargin(new Insets(5,1,1,1));
			result_textField.setBounds(0, 175, 694,40);

			display_textArea = new JTextArea(getArrayListToVerticalString(list_progress));
			display_textArea.setSize(180,230);
			display_textArea.setMargin(new Insets(2,2,2,2));
			display_textArea.setEditable(false);
			display_textArea.setBackground(Color.white);
			pane_display =new JScrollPane(display_textArea);
			pane_display.setBounds(544, 215, 150,230);

			String[] table_title= new String[5];
			table_title[0] = "步骤";table_title[1] = "当前符号"; table_title[2]="输入区";table_title[3]="运算符号栈";table_title[4] ="输出区";

			table = new JTable(result_str,table_title);
			//table.getTableHeader().setEnabled(false);
			table.setEnabled(false);
			pane_table = new JScrollPane(table);
			pane_table.setBounds(0, 215, 544, 230);

			this.add(result_textField);
			this.add(pane_display);
			this.add(pane_table);
		}else{
			final JDialog input_tip_dialog = new JDialog(CompilePrinciple_App.getJframe(),"语句错误");
			input_tip_dialog.setVisible(true);
			input_tip_dialog.setSize(190,100);
			input_tip_dialog.setLocation(560,250);
			input_tip_dialog.setLayout(null);
			input_tip_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			JLabel input_tip_text = new JLabel("输入语句错误");
			input_tip_text.setForeground(Color.black);
			input_tip_text.setBounds(46, 5, 170, 20);
			input_tip_text.setToolTipText("请先输入正确语句");


			JButton input_tip_button = new JButton("确定");
			input_tip_button.setBackground(new Color(166,166,166));
			input_tip_button.setBounds(58, 30, 60, 30);

			input_tip_dialog.getContentPane().add(input_tip_text);
			input_tip_dialog.getContentPane().add(input_tip_button);

			input_tip_button.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					input_tip_dialog.dispose();
				}

			});
		}

	}



	private double getStringToValue(String value_str){
		int input_str_length =input_str.length;
		double value= 0;
		if(value_str.matches("[0-9]+[\\.]*[0-9]*")){
			value = Double.parseDouble(value_str);
		}
		for(int i=0;i<input_str_length;i++){
			if(input_str[i][0].equals(value_str)){
				if(input_str[i][1].matches("[0-9]+")){
					value = Double.parseDouble(input_str[i][1]);
					break;
				}else{
					value = 0;
					break;
				}
			}
		}
		return value;
	}

	private void setExpreResult(){
		int length= list_simple_out.size();
		int start = 0;
		String sign_one = "";
		String num1="1";
		String num2="1";
		double db_num1=1;
		double db_num2=1;
		double db_sum = 0;
		for(;start<length;){
			sign_one = list_simple_out.get(start);
			if(sign_one.matches("(\\^|-|\\+|\\*|/|%|\\+\\+|--)")){
				break;
			}
			start++;
		}

		//	System.out.println(list_simple_out);

		if(start<length&&list_simple_out.size()>1){
			//System.out.println("运算符号"+sign_one);
			if(!sign_one.matches("(\\+\\+|--)")){
				if(start>=2){
					num2=list_simple_out.get(start-1);
					num1=list_simple_out.get(start-2);
				}
			}else{
				num2 = list_simple_out.get(start-1);
			}

			db_num1 = getStringToValue(num1);
			db_num2 = getStringToValue(num2);

			switch(sign_one){
				case "+":{
					db_sum = db_num1+db_num2;
					list_progress.add(db_sum+" = "+db_num1+" + "+db_num2);
					break;
				}
				case "-":{
					db_sum = db_num1-db_num2;
					list_progress.add(db_sum+" = "+db_num1+" - "+db_num2);
					break;
				}
				case "*":{
					db_sum = db_num1*db_num2;
					list_progress.add(db_sum+" = "+db_num1+" * "+db_num2);
					break;
				}
				case "/":{
					db_sum = db_num1/db_num2;
					list_progress.add(db_sum+" = "+db_num1+" / "+db_num2);
					break;
				}
				case "%":{
					db_sum = db_num1%db_num2;
					list_progress.add(db_sum+" = "+db_num1+" % "+db_num2);
					break;
				}
				case "^":{
					db_sum = Math.pow(db_num1,db_num2);
					list_progress.add(db_sum+" = "+db_num1+"^"+db_num2);
					break;
				}
				case "++":{
					db_sum = db_num2++;
					list_progress.add(db_sum+" = "+db_num2+"++");
					break;
				}
				case "--":{
					db_sum = db_num2--;
					list_progress.add(db_sum+" = "+db_num2+"--");
					break;
				}
			}

			if(!sign_one.matches("(\\+\\+|--)")){
				if(start>=2){
					list_simple_out.add(start-2, db_sum+"");
					list_simple_out.remove(start-1);
					list_simple_out.remove(start-1);
					list_simple_out.remove(start-1);
				}
			}else{
				list_simple_out.add(start-1, db_sum+"");
				list_simple_out.remove(start);
				list_simple_out.remove(start);
			}
		}


	}

	private String getArrayListToString(ArrayList<String> arr){
		int length = arr.size();
		String result = "";
		for(int i=0;i<length;i++){
			result+=arr.get(i);
		}

		return result;
	}

	private String getArrayListToVerticalString(ArrayList<String> arr){
		int length = arr.size();
		String result ="";
		for(int i=0;i<length;i++){
			result+=arr.get(i)+"\n";
		}

		return result;
	}

	private ArrayList<String> getCopyArrayList(ArrayList<String> arr) {
		int length = arr.size();
		ArrayList<String> result = new ArrayList<String>();
		for(int i=0;i<length;i++){
			result.add(arr.get(i));
		}
		result.add("#");
		return result;
	}

	public static RPJpanel getJpanel(){
		if(jpanel==null){
			jpanel = new RPJpanel();
		}
		return jpanel;
	}

	private String[][] getStrContent(ArrayList<String> step,ArrayList<String> present,ArrayList<String> input,ArrayList<String> compute,ArrayList<String> out,int column){
		int str_length = step.size();
		String [][] str = new String[str_length][column];
		for(int i=0;i<str_length;i++){
			str[i][0] = step.get(i);
			str[i][1] = present.get(i);
			str[i][2] = input.get(i);
			str[i][3] = compute.get(i);
			str[i][4] = out.get(i);
		}

		return str;
	}

	public Boolean isModifiedof_input_str_value(){
		int length = input_str.length;
		Boolean temp = false;
		for(int i=0;i<length;i++){
			if(input_str[i][1]==null||input_str[i][1].isEmpty()){
				temp=true;
				break;
			}
		}
		return temp;
	}

	public void setKeyValue(String key,String value){
		int input_str_length = input_str.length;
		for(int i=0;i<input_str_length;i++){
			if(input_str[i][0].equals(key)){
				input_str[i][1] = value;
			}
		}
	}

	public Boolean isEmptyof_input_str(){
		getKeyValue();
		Boolean temp = false;
		if(input_str==null||input_str.length==0){
			temp = true;
		}
		return temp;
	}

	public String[][] getKeyValue(){
		getArrayListSimpleChar();
		if(input_str==null){
			input_apha_data = new ArrayList<String>();
			for(int i=0;i<input_data.size();i++){
				if(input_data.get(i).matches("[a-z|A-Z]+")){
					if(!input_apha_data.contains(input_data.get(i))){
						input_apha_data.add(input_data.get(i));
					}
				}
			}

			int temp_length = input_apha_data.size();
			int column=2;
			input_str = new String[temp_length][column];
			for(int i=0;i<temp_length;i++){
				input_str[i][0] = input_apha_data.get(i);
			}
		}else{
			ArrayList<String> input_modify_temp = new ArrayList<String>();
			for(int i=0;i<input_data.size();i++){
				if(input_data.get(i).matches("[a-z|A-Z]+")){
					if(!input_apha_data.contains(input_data.get(i))){
						/////添加一个新的
						input_str = modifyInput_Str_Add(input_data.get(i));
						input_apha_data.add(input_data.get(i));

					}
					if(!input_modify_temp.contains(input_data.get(i))){
						input_modify_temp.add(input_data.get(i));
					}
				}
			}

			for(int i=0;i<input_apha_data.size();i++){
				if(!input_modify_temp.contains(input_apha_data.get(i))){
					///删除已有、无用的
					input_str = modifyInput_Str_Del(input_apha_data.get(i));
					input_apha_data.remove(i);

				}
			}
		}


		return input_str;
	}

	private String[][] modifyInput_Str_Del(String str){
		int lenth = input_str.length;
		String[][] temp_input_str = new String[lenth-1][2];
		for(int i=0, k = i;i<lenth;i++){
			if(k<lenth-1){
				temp_input_str[k][0] = input_str[i][0];
				temp_input_str[k][1] = input_str[i][1];
			}

			if(!input_str[i][0].equals(str)){
				k++;
			}

		}
		return temp_input_str;
	}

	private String[][] modifyInput_Str_Add(String str){
		int length=input_str.length;
		String[][] temp_input_str =new String[length+1][2];
		for(int k=0;k<length;k++){
			temp_input_str[k][0] = input_str[k][0];
			temp_input_str[k][1] = input_str[k][1];
		}
		temp_input_str[length][0] =str;
		return temp_input_str;
	}

	private void getArrayListSimpleChar(){
		input_data.clear();
		String str = input_textArea.getText();
		char [] char_str = str.toCharArray();
		int char_str_length = char_str.length;
		String apha_sign="";
		String num_sign="";
		String plus_minus = "";
		for(int i=0;i<char_str_length;i++){
			if((char_str[i]+"").matches("[a-z|A-Z]")){
				apha_sign +=char_str[i]+"";
				num_sign=num_sign.replaceAll("\\s*", "");
				if(!num_sign.isEmpty()){
					input_data.add(num_sign);
					num_sign="";
				}

				int length = plus_minus.length();
				if(length==1||length==2){
					input_data.add(plus_minus);
					plus_minus="";
				}
				if(length==3){

					input_data.add(plus_minus.substring(0, length-1));
					input_data.add(plus_minus.substring(length-1));
					plus_minus="";
				}
			}else if((char_str[i]+"").matches("[^a-z|A-Z|0-9]")){
				num_sign=num_sign.replaceAll("\\s*", "");
				if(!num_sign.isEmpty()){
					input_data.add(num_sign);
					num_sign="";
				}

				apha_sign=apha_sign.replaceAll("\\s*","");
				if(!apha_sign.isEmpty()){
					input_data.add(apha_sign);
					apha_sign="";
				}

				if(!(char_str[i]+"").matches("(\\+|-|\\s*)")){
					int length = plus_minus.length();
					if(length==1||length==2){
						input_data.add(plus_minus);
						plus_minus="";
					}
					if(length==3){
						input_data.add(plus_minus.substring(0, length-1));
						input_data.add(plus_minus.substring(length-1));
						plus_minus="";
					}

					input_data.add(char_str[i]+"");
				}else if((char_str[i]+"").matches("(\\+|-)")){
					plus_minus+=char_str[i]+"";
				}else{
					int length = plus_minus.length();
					if(length==1||length==2){
						input_data.add(plus_minus);
						plus_minus="";
					}
					if(length==3){
						input_data.add(plus_minus.substring(0, length-1));
						input_data.add(plus_minus.substring(length-1));
						plus_minus="";
					}
				}
			}else if((char_str[i]+"").matches("([0-9])")){
				num_sign += char_str[i]+"";
				apha_sign=apha_sign.replaceAll("\\s*","");
				if(!apha_sign.isEmpty()){
					input_data.add(apha_sign);
					apha_sign="";
				}

				int length = plus_minus.length();
				if(length==1||length==2){
					input_data.add(plus_minus);
					plus_minus="";
				}
				if(length==3){
					input_data.add(plus_minus.substring(0, length-1));
					input_data.add(plus_minus.substring(length-1));
					plus_minus="";
				}
			}
			if(i==char_str_length-1){
				num_sign=num_sign.replaceAll("\\s*", "");
				if(!num_sign.isEmpty()){
					input_data.add(num_sign);
					num_sign="";
				}

				apha_sign=apha_sign.replaceAll("\\s*","");
				if(!apha_sign.isEmpty()){
					input_data.add(apha_sign);
					apha_sign="";
				}

				int length = plus_minus.length();
				if(length==1||length==2){
					input_data.add(plus_minus);
					plus_minus="";
				}
				if(length==3){
					input_data.add(plus_minus.substring(0, length-1));
					input_data.add(plus_minus.substring(length-1));
					plus_minus="";
				}
			}
		}

	}
}
