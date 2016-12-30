package WordAnalysis;

import java.awt.Color;
import java.awt.Font;
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

import Main.CompilePrinciple_App;

public class WAJpanel extends JPanel{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static WAJpanel waJpanel = null;
	private JTextArea textArea ;
	private JTable wa_result_table;
	private JScrollPane wa_scrollPane;

	public WAJpanel(){
		this.setLayout(null);


		wa_result_table = new JTable(new String[0][0],new String[0]);
		wa_scrollPane = new JScrollPane(wa_result_table);
		wa_scrollPane.setVisible(false);
		this.add(wa_scrollPane);

		JLabel title = new JLabel("词法分析");
		title.setEnabled(true);
		title.setOpaque(true);
		title.setForeground(Color.black);
		title.setBounds(320,20,60,40);
		this.add(title);

		JLabel tip_input = new JLabel("请输入语句");
		tip_input.setBounds(120,98,90,40);
		tip_input.setForeground(Color.black);
		this.add(tip_input);

		JButton button_submit = new JButton("提交");
		button_submit.setBackground(new Color(166,166,166));
		button_submit.setBounds(520,100,60,30);
		button_submit.addActionListener(WAEventHandler.getWAEvent());
		button_submit.setActionCommand("wa_submit");
		this.add(button_submit);

		textArea = new JTextArea();
		textArea.setSize(300, 20);
		textArea.setMargin(new Insets(5, 5, 5, 5));
		textArea.setFont(new Font("serif",Font.LAYOUT_LEFT_TO_RIGHT,15));
		textArea.setAutoscrolls(true);
		//textArea.setWrapStyleWord(true);
		//textArea.setComponentPopupMenu(new JPopupTextField().getPopupMenu());
		textArea.setLineWrap(true);
		JScrollPane textScrollPane = new JScrollPane(textArea);
		textScrollPane.setBounds(200, 100, 300, 35);
		this.add(textScrollPane);

	}

	/*public void paint(Graphics g){
		super.paint(g);
				g.setColor(Color.red);
		g.drawString("Hello word", 200, 300);
		g.fillRect(0, 0, 40, 50);


	}*/


	public static WAJpanel getJpanel(){
		if(waJpanel ==null){
			waJpanel = new WAJpanel();
		}
		return waJpanel;
	}


	public void showResult(){
		if(WAEventHandler.getWAEvent().getKeyArr().isEmpty()&&WAEventHandler.getWAEvent().getSeparatorArr().isEmpty()){
			final JDialog separator_dialog = new JDialog(CompilePrinciple_App.getJframe(),"错误提示");
			separator_dialog.setVisible(true);
			separator_dialog.setSize(190,100);
			separator_dialog.setLocation(560,250);
			separator_dialog.setLayout(null);
			separator_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			JLabel separator_text = new JLabel("请先输入分隔符与关键字");
			separator_text.setForeground(Color.black);
			separator_text.setBounds(15, 0, 170, 20);
			separator_text.setToolTipText("请先输入分隔符表与关键字");


			JButton separator_button = new JButton("确定");
			separator_button.setBounds(60, 30, 60, 30);

			separator_dialog.getContentPane().add(separator_text);
			separator_dialog.getContentPane().add(separator_button);

			separator_button.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					separator_dialog.dispose();
				}

			});
		}else if(WAEventHandler.getWAEvent().getKeyArr().isEmpty()){
			final JDialog separator_dialog = new JDialog(CompilePrinciple_App.getJframe(),"错误提示");
			separator_dialog.setVisible(true);
			separator_dialog.setSize(150,100);
			separator_dialog.setLocation(580,250);
			separator_dialog.setLayout(null);
			separator_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			JLabel separator_text = new JLabel("请先输入关键字");
			separator_text.setForeground(Color.black);
			separator_text.setBounds(20, 0, 100, 20);
			separator_text.setToolTipText("请先输入关键字");


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
		}else if(WAEventHandler.getWAEvent().getSeparatorArr().isEmpty()){
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
		}else if(textArea.getText().toString().replaceAll("(\r|\t|\n| )", "").isEmpty()){
			final JDialog input_dialog = new JDialog(CompilePrinciple_App.getJframe(),"错误提示");
			input_dialog.setVisible(true);
			input_dialog.setSize(150,100);
			input_dialog.setLocation(580,250);
			input_dialog.setLayout(null);
			input_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			JLabel input_text = new JLabel("请先输入语句");
			input_text.setForeground(Color.black);
			input_text.setBounds(20, 0, 100, 20);
			input_text.setToolTipText("请先输入语句");


			JButton input_button = new JButton("确定");
			input_button.setBounds(45, 30, 60, 30);

			input_dialog.getContentPane().add(input_text);
			input_dialog.getContentPane().add(input_button);

			input_button.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					input_dialog.dispose();
				}

			});
		}else{
			String textArea_str = (textArea.getText().toString()+" ").replaceAll("( +|\t)"," ");
			int note1 = -1;
			int note1_end = -1;
			int note2 = -1;
			int note3 = -1;

			do{
				note1 = textArea_str.indexOf("//");
				note1_end = textArea_str.indexOf("\n", note1);
				note2 = textArea_str.indexOf("/*");
				note3 = textArea_str.indexOf("*/");
				if(note1>-1){
					if(note1_end>-1){
						textArea_str = textArea_str.substring(0, note1) + textArea_str.substring(note1_end, textArea_str.length()-1);
					}else{
						textArea_str = textArea_str.substring(0,note1)+" ";
					}
				}

				if(note2>-1){
					if(note3>-1){
						textArea_str = textArea_str.substring(0, note2)+textArea_str.substring(note3, textArea_str.length()-1);
					}else{
						textArea_str = textArea_str.substring(0, note2)+" ";
					}
				}
			}while(note1!=-1||note2!=-1);



			if(note1==-1&&note2==-1&&note3==-1){

				ArrayList<String> source_signal = WAEventHandler.getWAEvent().getSeparatorArr();
				String arithMetic_pattern =getSimplePattern(getSimpleArrayList(new WAArrayDistri().getArithMeticOperator(source_signal)));
				String relation_pattern = getSimplePattern(getSimpleArrayList(new WAArrayDistri().getRelationOperator(source_signal)));
				String logic_pattern = getSimplePattern(getSimpleArrayList(new WAArrayDistri().getLogicOperator(source_signal)));
				String bit_pattern = getSimplePattern(getSimpleArrayList(new WAArrayDistri().getBitOperator(source_signal)));
				String assign_pattern =getSimplePattern(getSimpleArrayList(new WAArrayDistri().getAssignOperator(source_signal)));
				String condition_pattern =getSimplePattern(getSimpleArrayList(new WAArrayDistri().getConditionOperator(source_signal)));
				String boundary_pattern =getSimplePattern(getSimpleArrayList(new WAArrayDistri().getBoundaryOperator(source_signal)));
				String remark_pattern = getSimplePattern(getSimpleArrayList(new WAArrayDistri().getRemarkOperator(source_signal)));

				char[] str = textArea_str.toCharArray();
				int str_length = str.length;
				int pointer_row=1,pointer_column=1;
				ArrayList<String> desti_arr = new ArrayList<String>();
				desti_arr.clear();
				String  pointer_current;
				String key_signal ="";
				String number ="";
				String arithMetic ="";
				String relation ="";
				String logic = "";
				String bit ="";
				String assign="";
				String condition = "";
				String boundary="";
				String error ="";
				String semicolon = "";

				for(int i=0;i<str_length;i++){
					//pointer_cache.append(str[i]);
					pointer_current = ""+str[i];


					if(!pointer_current.matches(" ")&&!pointer_current.matches("\n")){
						//关键字或标识符拼接
						key_signal+=pointer_current;

						//常数拼接
						number+=pointer_current;

						//结束符拼接
						semicolon+=pointer_current;
					}


				/*	if(!pointer_current.matches(" ")&&!pointer_current.matches("\n")){
						number+=pointer_current;
					}*/

					//算术运算符拼接
					if(pointer_current.matches("("+arithMetic_pattern+")")){
						arithMetic+=pointer_current;
					}

					//关系运算符拼接
					if(pointer_current.matches("("+relation_pattern+")")){
						relation+=pointer_current;
					}

					//逻辑运算符
					if(pointer_current.matches("("+logic_pattern+")")){
						logic+=pointer_current;
					}
					//位操作运算符
					if(pointer_current.matches("("+bit_pattern+")")){
						bit+=pointer_current;
					}
					//赋值运算符
					if(pointer_current.matches("("+assign_pattern+")")){
						assign+=pointer_current;
					}
					//条件运算符
					if(pointer_current.matches("("+condition_pattern+")")){
						condition+=pointer_current;
					}
					//分隔符
					if(pointer_current.matches("("+boundary_pattern+")")){
						boundary+=pointer_current;
					}

					//错误符号
					if(!pointer_current.matches(" ")&&!pointer_current.matches("\n")){
						error+=pointer_current;
					}




					//空格段字
					if(pointer_current.matches(" ")||pointer_current.matches("\n")){
						//关键字或标识符分析程序
						if(!key_signal.isEmpty()){
							key_signal = key_signal.replaceAll("("+remark_pattern+")","");
							if( WAEventHandler.getWAEvent().getKeyArr().contains(key_signal)){
								desti_arr.add(key_signal);
								desti_arr.add("(1,"+key_signal+")");
								desti_arr.add("关键字");
								desti_arr.add("("+pointer_row+","+pointer_column+")");

								error="";
								pointer_column++;
							}else if(key_signal.matches("[a-z|A-Z]+")){
								desti_arr.add(key_signal);
								desti_arr.add("(9,"+key_signal+")");
								desti_arr.add("标识符");
								desti_arr.add("("+pointer_row+","+pointer_column+")");

								error="";
								pointer_column++;
							}

							key_signal="";

						}

						//常数分析程序
						if(!number.isEmpty()){
							if(number.matches("([0-9]+)")||number.matches("([0-9]+)("+remark_pattern+")")){
								number = number.replaceAll("("+remark_pattern+"|\\s)", "");
								desti_arr.add(number);
								desti_arr.add("(10,"+number+")");
								desti_arr.add("常数");
								desti_arr.add("("+pointer_row+","+pointer_column+")");
								pointer_column++;
								error="";
							}
							number="";

						}

						//算术运算符分析程序
						if(!arithMetic.isEmpty()){
							if(new WAArrayDistri().getArithMeticOperator(source_signal).contains(arithMetic)){
								desti_arr.add(arithMetic);
								desti_arr.add("(2,"+arithMetic+")");
								desti_arr.add("算术运算符");
								desti_arr.add("("+pointer_row+","+pointer_column+")");
								pointer_column++;
								error="";
							}
							arithMetic="";

						}

						//关系运算符分析程序
						if(!relation.isEmpty()){
							if(new WAArrayDistri().getRelationOperator(source_signal).contains(relation)){
								desti_arr.add(relation);
								desti_arr.add("(3,"+relation+")");
								desti_arr.add("关系运算符");
								desti_arr.add("("+pointer_row+","+pointer_column+")");
								pointer_column++;
								error="";
							}
							relation="";

						}

						//逻辑运算符分析程序
						if(!logic.isEmpty()){
							if(new WAArrayDistri().getLogicOperator(source_signal).contains(logic)){
								desti_arr.add(logic);
								desti_arr.add("(4,"+logic+")");
								desti_arr.add("逻辑运算符");
								desti_arr.add("("+pointer_row+","+pointer_column+")");
								pointer_column++;
								error ="";
							}
							logic = "";

						}

						//位操作运算符分析程序
						if(!bit.isEmpty()){
							if(new WAArrayDistri().getBitOperator(source_signal).contains(bit)){
								//位操作运算符
								desti_arr.add(bit);
								desti_arr.add("(5,"+bit+")");
								desti_arr.add("位操作运算符");
								desti_arr.add("("+pointer_row+","+pointer_column+")");
								pointer_column++;
								error ="";
							}
							bit = "";

						}

						//赋值运算符分析程序
						if(!assign.isEmpty()){
							if(new WAArrayDistri().getAssignOperator(source_signal).contains(assign)){
								desti_arr.add(assign);
								desti_arr.add("(6,"+assign+")");
								desti_arr.add("赋值运算符");
								desti_arr.add("("+pointer_row+","+pointer_column+")");
								pointer_column++;
								error="";
							}
							assign="";

						}

						//条件运算符分析程序
						if(!condition.isEmpty()){
							if(new WAArrayDistri().getConditionOperator(source_signal).contains(condition)){
								//条件运算符
								desti_arr.add(condition);
								desti_arr.add("(7,"+condition+")");
								desti_arr.add("条件运算符");
								desti_arr.add("("+pointer_row+","+pointer_column+")");
								pointer_column++;
								error ="";
							}
							condition ="";

						}

						//分隔符分析程序
						if(!boundary.isEmpty()){
							if(new WAArrayDistri().getBoundaryOperator(source_signal).contains(boundary)){
								desti_arr.add(boundary);
								desti_arr.add("(8,"+boundary+")");
								desti_arr.add("分隔符");
								desti_arr.add("("+pointer_row+","+pointer_column+")");
								pointer_column++;
								error ="";
							}
							boundary ="";

						}



						if(!semicolon.isEmpty()){
							if(semicolon.matches("(.*)("+remark_pattern+")(.*)")){
								semicolon = semicolon.replaceAll("[0-9|a-z|A-Z|-|_]","");
								desti_arr.add(semicolon);
								desti_arr.add("(0,"+semicolon+")");
								desti_arr.add("记号符");
								desti_arr.add("("+pointer_row+","+pointer_column+")");
								pointer_column++;
								error ="";
							}
							semicolon ="";
						}

						//错误输入
						if(!error.isEmpty()){
							desti_arr.add(error);
							desti_arr.add("(11,Error)");
							desti_arr.add("Error");
							desti_arr.add("("+pointer_row+","+pointer_column+")");
							error ="";
							pointer_column++;
						}
						if(pointer_current.matches("\n")){
							pointer_column=1;
							pointer_row++;
						}
					}

				}

				String[] title = new String[4];
				title[0] = "单词";
				title[1] = "二元序列(T,A)";
				title[2] = "类型";
				title[3] = "位置(行列)";

				///
				wa_scrollPane.remove(wa_result_table);
				this.remove(wa_scrollPane);


				String[][] content = new WAArrayDistri().getWAResult(desti_arr, 4);

				wa_result_table = new JTable(content,title);
				wa_scrollPane = new JScrollPane(wa_result_table);
				wa_scrollPane.setBounds(130,180,440,200);

				/*System.out.println("点击了一下");
				System.out.println("-------------------");

				for(int i=0;i<desti_arr.size();i++){
					System.out.println(desti_arr.get(i));
					if((i+1)%4==0)System.out.println("-----");
				}*/

				wa_result_table.setRowHeight(30);
				wa_result_table.setEnabled(false);
				wa_result_table.getTableHeader().setEnabled(false);
				this.add(wa_scrollPane);
				//wa_result_table.repaint();
			}else{
				final JDialog input_dialog = new JDialog(CompilePrinciple_App.getJframe(),"错误提示");
				input_dialog.setVisible(true);
				input_dialog.setSize(150,100);
				input_dialog.setLocation(580,250);
				input_dialog.setLayout(null);
				input_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

				JLabel input_text = new JLabel("无注释符与*/匹配");
				input_text.setForeground(Color.black);
				input_text.setBounds(20, 0, 100, 20);
				input_text.setToolTipText("无注释符与*/匹配");


				JButton input_button = new JButton("确定");
				input_button.setBounds(45, 30, 60, 30);

				input_dialog.getContentPane().add(input_text);
				input_dialog.getContentPane().add(input_button);

				input_button.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						input_dialog.dispose();
					}

				});
			}
		}
	}



	public String getSimplePattern(ArrayList<String> arr){
		StringBuilder sb= new StringBuilder();
		String str="";
		for(int i=0;i<arr.size();i++){
			if(arr.get(i).matches("[\\*|\\+|\\;|\\(|\\)|\\[|\\]|\\?|\\|]")){
				sb.append("\\");
			}
			sb.append(arr.get(i));
			if(i<arr.size()-1){
				sb.append("|");
			}
		}
		str += sb.toString();
		return str;
	}

	public ArrayList<String> getSimpleArrayList(ArrayList<String> str){
		int str_length = str.size();
		ArrayList<String> temp = new ArrayList<String>();
		ArrayList<String> desti = new ArrayList<String>();
		String str_str;
		for(int i=0;i<str_length;i++){
			str_str = (str.get(i)+"").replaceAll("\\s*", "");
			if(str_str.length()==1){
				temp.add(str_str);
			}else{
				char[] temp_str =str_str.toCharArray();
				int temp_length = temp_str.length;
				for(int j=0;j<temp_length;j++){
					temp.add(temp_str[j]+"");
				}
			}
		}

		for(int i=0;i<temp.size();i++){
			if(!desti.contains(temp.get(i))){
				desti.add(temp.get(i));
			}
		}

		return desti;
	}

}
