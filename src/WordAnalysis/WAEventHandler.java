package WordAnalysis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import Main.CompilePrinciple_App;

public class WAEventHandler implements ActionListener{
	private static WAEventHandler waEventHandler = null;
	private ArrayList<String> key_arr;
	private ArrayList<String> separator_arr;

	public WAEventHandler(){
		key_arr = new ArrayList<String>();
		separator_arr = new ArrayList<String>();
		//System.out.println("按钮触动了");
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("key_direct_input")){
			new WADialog().getDialog().keyShowDialog();
		}


		if(e.getActionCommand().equals("separator_direct_input")){
			new WADialog().getDialog().separatorShowDialog();
		}

		if(e.getActionCommand().equals("separator_file_load")){
			// 创建文件选择器
			JFileChooser fileChooser = new JFileChooser();
			// 设置当前目录
			fileChooser.setCurrentDirectory(new File("."));
			fileChooser.setAcceptAllFileFilterUsed(false);
			final String[][] fileENames = { { ".java", "JAVA源程序 文件(*.java)" },
					{ ".doc", "MS-Word 2003 文件(*.doc)" },
					{ ".xls", "MS-Excel 2003 文件(*.xls)" },
					{".txt","文本文件(*.txt)"}
			};

			// 显示所有文件
			fileChooser.addChoosableFileFilter(new FileFilter() {
				public boolean accept(File file) {
					return true;
				}
				public String getDescription() {
					return "所有文件(*.*)";
				}
			});

			// 循环添加需要显示的文件
			for (final String[] fileEName : fileENames) {
				fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
					public boolean accept(File file) {
						if (file.getName().endsWith(fileEName[0]) || file.isDirectory()) {
							return true;
						}

						return false;
					}

					public String getDescription() {
						return fileEName[1];
					}
				});
			}

			fileChooser.showDialog(null, null);

			//读取选中的文件
			BufferedReader reader = null;
			File file = null;
			try {
				file = fileChooser.getSelectedFile();
				if(file!=null){
					reader = new BufferedReader(new FileReader(file));
				}

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String line;
//		      StringBuilder sb = new StringBuilder();

			try {
				if(file!=null){
					if(!separator_arr.isEmpty()){
						separator_arr.clear();
					}
					while((line=reader.readLine())!=null){
						separator_arr.add(line);
						/*  sb.append(line+"\n");
						  System.out.println(line);*/
					}
				}
			}catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				if(file !=null){
					reader.close();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if(e.getActionCommand().equals("key_file_load")){
			// 创建文件选择器
			JFileChooser fileChooser = new JFileChooser();
			// 设置当前目录
			fileChooser.setCurrentDirectory(new File("."));
			fileChooser.setAcceptAllFileFilterUsed(false);
			final String[][] fileENames = { { ".java", "JAVA源程序 文件(*.java)" },
					{ ".doc", "MS-Word 2003 文件(*.doc)" },
					{ ".xls", "MS-Excel 2003 文件(*.xls)" },
					{".txt","文本文件(*.txt)"}
			};

			// 显示所有文件
			fileChooser.addChoosableFileFilter(new FileFilter() {
				public boolean accept(File file) {
					return true;
				}
				public String getDescription() {
					return "所有文件(*.*)";
				}
			});

			// 循环添加需要显示的文件
			for (final String[] fileEName : fileENames) {
				fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
					public boolean accept(File file) {
						if (file.getName().endsWith(fileEName[0]) || file.isDirectory()) {
							return true;
						}

						return false;
					}

					public String getDescription() {
						return fileEName[1];
					}
				});
			}

			fileChooser.showDialog(null, null);

			//读取选中的文件
			BufferedReader reader = null;
			File file = null;
			try {
				file = fileChooser.getSelectedFile();
				if(file!=null){
					reader = new BufferedReader(new FileReader(file));
				}

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String line;
		      /*StringBuilder sb = new StringBuilder();
		      */
			try {
				if(file!=null){
					if(!key_arr.isEmpty()){
						key_arr.clear();
					}
					while((line=reader.readLine())!=null){

						key_arr.add(line);
						  /*sb.append(line+"\n");
						  System.out.println(line);*/
					}
				}
			}catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				if(file !=null){
					reader.close();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		if(e.getActionCommand().equals("look_key_table")){
			new WADialog().getDialog().lookKeyDialog();
		}

		if(e.getActionCommand().equals("look_separator_table")){
			new WADialog().getDialog().lookSeparatorDialog();
		}

		if(e.getActionCommand().equals("wa_open_panel")){
			/*new LLAJpanel().getJpanel().setVisible(false);
			new WAJpanel().getJpanel().setVisible(true);
			//new WAJpanel().getJpanel().repaint();*/

			new CompilePrinciple_App().getCard().show(new CompilePrinciple_App().getJpanel(), "wa_panel");

		}

		if(e.getActionCommand().equals("wa_submit")){
			//new WAJpanel().getJpanel().clickSubmit();
			new WAJpanel().getJpanel().showResult();
			new WAJpanel().getJpanel().repaint();
		}
	}

	public static WAEventHandler getWAEvent(){
		if(waEventHandler==null){
			waEventHandler = new WAEventHandler();

		}
		return waEventHandler;

	}

	public ArrayList<String> getKeyArr(){
		return key_arr;
	}

	public ArrayList<String> getSeparatorArr(){
		return separator_arr;
	}

}
