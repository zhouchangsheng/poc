package LL_1Analysis;

import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class LLAMenu extends JMenu{
	private static LLAMenu menu2=null;
	private LLAMenu(){

	}

	public static LLAMenu getMenu(){
		if(menu2==null){
			menu2 = new LLAMenu();
			LLAEventHandler  llaEventHandler = LLAEventHandler.getLLAEvent();
			menu2.setText("LL(1)分析");

			JMenuItem menuItem_open_panel = new JMenuItem("打开面板");
			JMenuItem menuItem_look_text = new JMenuItem("查看文法");
			JMenuItem menuItem_look_first_follow = new JMenuItem("查看FIRST及FOLLOW集");


			menu2.add(menuItem_open_panel);
			menu2.add(menuItem_look_text);
			menu2.add(menuItem_look_first_follow);


			menuItem_open_panel.addActionListener(llaEventHandler);
			menuItem_open_panel.setActionCommand("lla_open_panel");

			menuItem_look_text.addActionListener(llaEventHandler);
			menuItem_look_text.setActionCommand("lla_look_text");

			menuItem_look_first_follow.addActionListener(llaEventHandler);
			menuItem_look_first_follow.setActionCommand("lla_look_first_follow");
		}
		return menu2;
	}
}
