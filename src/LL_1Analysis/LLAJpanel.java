package LL_1Analysis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import Main.CompilePrinciple_App;
import ReversePoland.RPEventHandler;


public class LLAJpanel extends JPanel{
	public static LLAJpanel llaJpanel = null;
	private ArrayList<String> FIRST_E = null;
	private ArrayList<String> FIRST_F = null;
	private ArrayList<String> FIRST_T = null;
	private ArrayList<String> FIRST_S = null;
	private ArrayList<String> FIRST_G = null;
	private ArrayList<String> FOLLOW_E =null;
	private ArrayList<String> FOLLOW_F = null;
	private ArrayList<String> FOLLOW_T =null;
	private ArrayList<String> FOLLOW_S =null;
	private ArrayList<String> FOLLOW_G =null;

	private LinkedList<String> collector_E = null;
	private LinkedList<String> collector_F = null;
	private LinkedList<String> collector_T = null;
	private LinkedList<String> collector_S = null;
	private LinkedList<String> collector_G = null;


	private Iterator<String> iterator = null;
	private LinkedList<String> input_data = null;

	private ArrayList<String> list_step = null;
	private ArrayList<String> list_analysis_stack =null;
	private ArrayList<String> list_remain_input_str= null;
	private ArrayList<String> list_use_expression = null;
	private ArrayList<String> list_behavior = null;
	private LinkedList<String> list_stack_data = null;
//private Array

	private JTextArea textArea ;
	private JTable lla_result_table;
	private JScrollPane lla_scrollPane,textAreaPane;
	private JButton button_submit;

	public LLAJpanel(){
		this.setLayout(null);

		textArea = new JTextArea();
		textArea.setFont(new Font("serif",Font.LAYOUT_LEFT_TO_RIGHT,15));
		textAreaPane = new JScrollPane(textArea);
		textArea.setMargin(new Insets(2,2,2,2));
		textAreaPane.setBounds(0,85,694,100);
		this.add(textAreaPane);

		JLabel title = new JLabel("LL(1)分析");
		title.setEnabled(true);
		title.setOpaque(true);
		title.setForeground(Color.black);
		title.setBounds(320,20,60,40);
		this.add(title);

		button_submit = new JButton("提交");
		button_submit.setBounds(613, 50, 73, 30);
		button_submit.setBackground(new Color(166,166,166));
		button_submit.addActionListener(LLAEventHandler.getLLAEvent());
		button_submit.setActionCommand("button_submit");
		this.add(button_submit);

		String[] lla_title = new String[5];
		lla_title[0] = "步骤";
		lla_title[1] = "分析栈";
		lla_title[2] = "剩余字符串";
		lla_title[3] = "所用产生式";
		lla_title[4] = "动作";
		lla_result_table = new JTable(new String[0][0],lla_title);


		lla_scrollPane = new JScrollPane(lla_result_table);
		lla_scrollPane.setVisible(true);
		lla_scrollPane.setBounds(0,185,694,260);
		this.add(lla_scrollPane);

		FIRST_E = new ArrayList<String>();
		FIRST_E.add("(");
		FIRST_E.add("i");

		FIRST_F = FIRST_E;

		FIRST_T = FIRST_F;

		FIRST_S = new ArrayList<String>();
		FIRST_S.add("*");
		FIRST_S.add("/");
		FIRST_S.add("ε");  //这里代表空

		FIRST_G = new ArrayList<String>();
		FIRST_G.add("+");
		FIRST_G.add("-");
		FIRST_G.add("ε");

		FOLLOW_E = new ArrayList<String>();
		FOLLOW_E.add(")");
		FOLLOW_E.add("#");

		FOLLOW_F = new ArrayList<String>();
		FOLLOW_F.add(")");
		FOLLOW_F.add("#");
		FOLLOW_F.add("+");
		FOLLOW_F.add("-");
		FOLLOW_F.add("*");
		FOLLOW_F.add("/");

		FOLLOW_T = new ArrayList<String>();
		FOLLOW_T.add(")");
		FOLLOW_T.add("#");
		FOLLOW_T.add("+");
		FOLLOW_T.add("-");
		FOLLOW_S = FOLLOW_T;
		FOLLOW_G = FOLLOW_E;

		input_data = new LinkedList<String>();
		list_stack_data = new LinkedList<String>();

		collector_E = new LinkedList<String>();
		collector_E.add("TG");

		collector_F = new LinkedList<String>();
		collector_F.add("(E)");
		collector_F.add("i");

		collector_T = new LinkedList<String>();
		collector_T.add("FS");

		collector_S = new LinkedList<String>();
		collector_S.add("*FS");
		collector_S.add("/FS");
		collector_S.add("ε");

		collector_G = new LinkedList<String>();
		collector_G.add("+TG");
		collector_G.add("-TG");
		collector_G.add("ε");

		list_step = new ArrayList<String>();
		list_analysis_stack = new ArrayList<String>();
		list_remain_input_str = new ArrayList<String>();
		list_use_expression = new ArrayList<String>();
		list_behavior = new ArrayList<String>();
	}

	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.red);
		//g.drawString("LLA Jpanel", 200, 300);
		//g.fillRect(0, 0, 40, 50);

		//g.fillRect(0,185,694,260);
		g.setColor(Color.black);
		//g.fillRect(0,145,694,40);
	}

	public void showStr(){
		input_data.clear();
		list_step.clear();
		list_analysis_stack.clear();
		list_remain_input_str.clear();
		list_use_expression.clear();
		list_behavior.clear();
		list_stack_data.clear();

		getLinkedListSimpleChar();
		//System.out.println(input_data);
		if(textArea.getText().replaceAll("\\s*", "").isEmpty()){
			final JDialog input_tip_dialog = new JDialog(CompilePrinciple_App.getJframe(),"错误提示");
			input_tip_dialog.setVisible(true);
			input_tip_dialog.setSize(190,100);
			input_tip_dialog.setLocation(560,250);
			input_tip_dialog.setLayout(null);
			input_tip_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			JLabel input_tip_text = new JLabel("请输入符号串");
			input_tip_text.setForeground(Color.black);
			input_tip_text.setBounds(46, 5, 170, 20);
			input_tip_text.setToolTipText("请输入符号串");


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
			if(input_data.contains("#")){
				if(isInputData()){
					anaylisProgress();
				}else{
					final JDialog input_tip_dialog = new JDialog(CompilePrinciple_App.getJframe(),"错误提示");
					input_tip_dialog.setVisible(true);
					input_tip_dialog.setSize(190,100);
					input_tip_dialog.setLocation(560,250);
					input_tip_dialog.setLayout(null);
					input_tip_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

					JLabel input_tip_text = new JLabel("终结符不合法");
					input_tip_text.setForeground(Color.black);
					input_tip_text.setBounds(46, 5, 170, 20);
					input_tip_text.setToolTipText("终结符不合法");


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
			}else{
				final JDialog input_tip_dialog = new JDialog(CompilePrinciple_App.getJframe(),"错误提示");
				input_tip_dialog.setVisible(true);
				input_tip_dialog.setSize(190,100);
				input_tip_dialog.setLocation(560,250);
				input_tip_dialog.setLayout(null);
				input_tip_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

				JLabel input_tip_text = new JLabel("请在结尾输入#");
				input_tip_text.setForeground(Color.black);
				input_tip_text.setBounds(46, 5, 170, 20);
				input_tip_text.setToolTipText("请在结尾输入#");


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


	}

	public void anaylisProgress(){

		lla_scrollPane.remove(lla_result_table);
		this.remove(lla_scrollPane);
		String input_str=input_data.getFirst();
		String stack_top="";
		String first_son = "";
		String expression ="";
		String behavior ="初始化";
		int step = 0;
		list_stack_data.add("#");
		list_stack_data.add("E");
		Boolean isError = false;

		do{

			if(stack_top.matches("E|F|T|S|G")){
				if(isNonTerFirst(stack_top,input_str)){
					first_son = getNonTerSon(stack_top,input_str);
					expression = stack_top+"->"+first_son;
					behavior = "POP,"+"PUSH("+getReverseStack(first_son)+")";
				}else if(isNonTerFollow(input_str)&&isNonHasNull(stack_top)){
					expression = stack_top + "->ε";
					behavior = "POP";
					list_stack_data.removeLast();
				}
			}else if(stack_top.equals(input_str)){
				list_stack_data.removeLast();
				input_data.removeFirst();
				expression ="";
				behavior = "GETNEXT(I)";

			}

			if(stack_top.isEmpty()){
				stack_top = list_stack_data.getLast();
			}else{
				input_str = input_data.getFirst();
				stack_top = list_stack_data.getLast();
			}



			list_step.add(step+"");
			list_analysis_stack.add(getLinkedListToString(list_stack_data));
			list_remain_input_str.add(getLinkedListToString(input_data));
			list_use_expression.add(expression);
			list_behavior.add(behavior);


			step++;

		}while(!stack_top.equals("#"));/////////////////
		/*System.out.println(list_step);
		System.out.println(list_analysis_stack);
		System.out.println(list_remain_input_str);
		System.out.println(list_use_expression);
		System.out.println(list_behavior);*/


		if(isError){
			final JDialog input_tip_dialog = new JDialog(CompilePrinciple_App.getJframe(),"错误提示");
			input_tip_dialog.setVisible(true);
			input_tip_dialog.setSize(190,100);
			input_tip_dialog.setLocation(560,250);
			input_tip_dialog.setLayout(null);
			input_tip_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			JLabel input_tip_text = new JLabel("不存在该文法");
			input_tip_text.setForeground(Color.black);
			input_tip_text.setBounds(46, 5, 170, 20);
			input_tip_text.setToolTipText("不存在该文法");


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
			String[] lla_title = new String[5];
			lla_title[0] = "步骤";
			lla_title[1] = "分析栈";
			lla_title[2] = "剩余字符串";
			lla_title[3] = "所用产生式";
			lla_title[4] = "动作";
			lla_result_table = new JTable(getTableContent(),lla_title);
			lla_result_table.setEnabled(false);

			lla_scrollPane = new JScrollPane(lla_result_table);
			lla_scrollPane.setVisible(true);
			lla_scrollPane.setBounds(0,185,694,260);
			this.add(lla_scrollPane);
		}
	}

	private String[][] getTableContent(){
		int row_count = list_step.size();
		String [][] table_content = new String[row_count][5];
		for(int i=0;i<row_count;i++){
			table_content[i][0] = list_step.get(i);
			table_content[i][1] = list_analysis_stack.get(i);
			table_content[i][2] = list_remain_input_str.get(i);
			table_content[i][3] = list_use_expression.get(i);
			table_content[i][4] = list_behavior.get(i);
		}
		return table_content;
	}

	private Boolean isNonHasNull(String stack_top){
		Boolean judge = false;
		if(stack_top.equals("E")){
			if(FIRST_E.contains("ε")){
				judge = true;
			}
		}else if(stack_top.equals("F")){
			if(FIRST_F.contains("ε")){
				judge = true;
			}
		}else if(stack_top.equals("T")){
			if(FIRST_T.contains("ε")){
				judge = true;
			}
		}else if(stack_top.equals("S")){
			if(FIRST_S.contains("ε")){
				judge = true;
			}
		}else if(stack_top.equals("G")){
			if(FIRST_G.contains("ε")){
				judge = true;
			}
		}

		return judge;
	}

	private String getReverseStack(String str){
		String result ="";
		char[] str_char = str.toCharArray();
		int length = str_char.length;
		list_stack_data.removeLast();
		for(int i=length-1;i>=0;i--){
			list_stack_data.add(str_char[i]+"");
			result += str_char[i];
		}
		return result;
	}

	private String getLinkedListToString(LinkedList<String> list){
		String list_str ="";
		iterator = list.iterator();
		while(iterator.hasNext()){
			list_str +=iterator.next();
		}

		return list_str;
	}

	private String getNonTerSon(String stack_top,String input_str){
		if(input_str.matches("\\+|\\*|\\(|\\)")){
			input_str = "\\"+input_str;
		}
		String first_son = "";
		String temp ="";
		if(stack_top.equals("E")){
			iterator = collector_E.iterator();
			first_son = iterator.next();
			while(iterator.hasNext()){
				temp = iterator.next();
				if(temp.matches(".*"+input_str+".*")){
					first_son = temp;
					break;
				}
			}
		}else if(stack_top.equals("F")){
			iterator = collector_F.iterator();
			first_son = iterator.next();
			while(iterator.hasNext()){
				temp = iterator.next();
				if(temp.matches(".*"+input_str+".*")){
					first_son = temp;
					break;
				}
			}
		}else if(stack_top.equals("T")){
			iterator = collector_T.iterator();
			first_son = iterator.next();
			while(iterator.hasNext()){
				temp = iterator.next();
				if(temp.matches(".*"+input_str+".*")){
					first_son = temp;
					break;
				}
			}
		}else if(stack_top.equals("S")){
			iterator = collector_S.iterator();
			first_son = iterator.next();
			while(iterator.hasNext()){
				temp = iterator.next();
				if(temp.matches(".*"+input_str+".*")){
					first_son = temp;
					break;
				}
			}
		}else if(stack_top.equals("G")){
			iterator = collector_G.iterator();
			first_son = iterator.next();
			while(iterator.hasNext()){
				temp = iterator.next();
				if(temp.matches(".*"+input_str+".*")){
					first_son = temp;
					break;
				}
			}
		}

		return first_son;
	}

	private Boolean isNonTerFirst(String stack_top,String input_str){
		Boolean judge = false;
		if(stack_top.equals("E")){
			if(FIRST_E.contains(input_str)){
				judge = true;
			}
		}else if(stack_top.equals("F")){
			if(FIRST_F.contains(input_str)){
				judge = true;
			}
		}else if(stack_top.equals("T")){
			if(FIRST_T.contains(input_str)){
				judge = true;
			}
		}else if(stack_top.equals("S")){
			if(FIRST_S.contains(input_str)){
				judge = true;
			}
		}else if(stack_top.equals("G")){
			if(FIRST_G.contains(input_str)){
				judge = true;
			}
		}
		return judge;
	}

	private Boolean isNonTerFollow(String input_str){
		Boolean judge = false;
		if(FOLLOW_E.contains(input_str)){
			judge = true;
			return judge;
		}

		if(FOLLOW_F.contains(input_str)){
			judge = true;
			return judge;
		}

		if(FOLLOW_T.contains(input_str)){
			judge = true;
			return judge;
		}

		if(FOLLOW_S.contains(input_str)){
			judge = true;
			return judge;
		}

		if(FOLLOW_G.contains(input_str)){
			judge = true;
			return judge;
		}

		return judge;
	}

	private void getLinkedListSimpleChar(){
		input_data.clear();
		String str = textArea.getText();
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

	private Boolean isInputData(){
		iterator = input_data.iterator();
		String temp = "";

		Boolean result = true;
		while(iterator.hasNext()){
			temp= iterator.next();
			if(!temp.matches("\\+|-|\\*|/|\\(|\\)|i|#")){
				result = false;
				break;
			}
		}

		return result;
	}

	public static LLAJpanel getJpanel(){
		if(llaJpanel ==null){
			llaJpanel = new LLAJpanel();
		}
		return llaJpanel;


	}
}
