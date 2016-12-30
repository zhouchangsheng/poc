package ReversePoland;

import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class RPMenu extends JMenu{
	private static RPMenu menu3=null;

	private RPMenu(){

	}

	public static JMenu getJMenu(){
		if(menu3==null){
			menu3 = new RPMenu();
			RPEventHandler rpEventHandler = RPEventHandler.getRPEventHandler();
			menu3.setText("逆波兰");

			JMenuItem menuItem_open_panel = new JMenuItem("打开面板");


			menu3.add(menuItem_open_panel);



			menuItem_open_panel.addActionListener(rpEventHandler);
			menuItem_open_panel.setActionCommand("rp_open_panel");
		}
		return menu3;
	}
}
