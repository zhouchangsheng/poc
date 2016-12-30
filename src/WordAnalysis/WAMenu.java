package WordAnalysis;

import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class WAMenu extends JMenu{
	private static WAMenu menu1=null;
	private WAMenu(){

	}

	public static WAMenu getMenu(){
		if(menu1==null){
			menu1 = new WAMenu();
			WAEventHandler  waEventHandler = WAEventHandler.getWAEvent();
			menu1.setText("词法分析");
			JMenu menu1_lead_sign = new JMenu("导入符号表");
			JMenu menu1_lead_sign_key = new JMenu("关键字表");
			JMenu menu1_lead_sign_separator = new JMenu("分隔符表");
			JMenu menu1_look_sign = new JMenu("查看符号表");


			JMenuItem menuItem_key_1 = new JMenuItem("文件导入");
			JMenuItem menuItem_key_2 = new JMenuItem("直接输入");
			JMenuItem menuItem_look_key = new JMenuItem("查看关键字表");
			JMenuItem menuItem_look_separator = new JMenuItem("查看分隔符表");
			JMenuItem menuItem_open_panel = new JMenuItem("打开面板");

			menu1_look_sign.add(menuItem_look_key);
			menu1_look_sign.add(menuItem_look_separator);

			menu1_lead_sign_key.add(menuItem_key_1);
			menu1_lead_sign_key.add(menuItem_key_2);

			JMenuItem menuItem_separator_1 = new JMenuItem("文件导入");
			JMenuItem menuItem_separator_2 = new JMenuItem("直接输入");
			menu1_lead_sign_separator.add(menuItem_separator_1);
			menu1_lead_sign_separator.add(menuItem_separator_2);

			menu1_lead_sign.add(menu1_lead_sign_key);
			menu1_lead_sign.add(menu1_lead_sign_separator);

			menu1.add(menuItem_open_panel);
			menu1.add(menu1_lead_sign);
			menu1.add(menu1_look_sign);


			menuItem_key_1.addActionListener(waEventHandler);
			menuItem_key_1.setActionCommand("key_file_load");

			menuItem_key_2.addActionListener(waEventHandler);
			menuItem_key_2.setActionCommand("key_direct_input");

			menuItem_separator_1.addActionListener(waEventHandler);
			menuItem_separator_1.setActionCommand("separator_file_load");

			menuItem_separator_2.addActionListener(waEventHandler);
			menuItem_separator_2.setActionCommand("separator_direct_input");

			menuItem_look_key.addActionListener(waEventHandler);
			menuItem_look_key.setActionCommand("look_key_table");

			menuItem_look_separator.addActionListener(waEventHandler);
			menuItem_look_separator.setActionCommand("look_separator_table");

			menuItem_open_panel.addActionListener(waEventHandler);
			menuItem_open_panel.setActionCommand("wa_open_panel");
		}
		return menu1;
	}
}
