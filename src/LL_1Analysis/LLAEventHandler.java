package LL_1Analysis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import Main.CompilePrinciple_App;

public class LLAEventHandler implements ActionListener{
	private static LLAEventHandler llaEventHandler = null;
	//WADialog waDialog;
	
	public LLAEventHandler(){
//		JFrame jframe = new CompilePrinciple_App().getJframe();
//		waDialog =new WADialog(jframe);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("lla_open_panel")){
			//waDialog.keyShowDialog();
			CompilePrinciple_App.getCard().show(CompilePrinciple_App.getJpanel(), "lla_panel");
		}else if(e.getActionCommand().equals("button_submit")){
			LLAJpanel.getJpanel().showStr();
		}else if(e.getActionCommand().equals("lla_look_text")){
			LLADialog.getDialog().showText();
		}else if(e.getActionCommand().equals("lla_look_first_follow")){
			LLADialog.getDialog().showFirstFollow();
		}
		
	}
	
	public static LLAEventHandler getLLAEvent(){
		if(llaEventHandler==null){
			llaEventHandler = new LLAEventHandler();
			
		}
		return llaEventHandler; 
		
	}

}
