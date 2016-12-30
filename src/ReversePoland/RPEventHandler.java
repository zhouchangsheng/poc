package ReversePoland;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import Main.CompilePrinciple_App;

public class RPEventHandler implements ActionListener{
	private static RPEventHandler rpEventHandler =null;

	private RPEventHandler(){

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("rp_open_panel")){
			CompilePrinciple_App.getCard().show(CompilePrinciple_App.getJpanel(), "rp_panel");
		}

		if(arg0.getActionCommand().equals("button_input")){
			if(RPJpanel.getJpanel().isEmptyof_input_str()){
				final JDialog input_tip_dialog = new JDialog(CompilePrinciple_App.getJframe(),"错误提示");
				input_tip_dialog.setVisible(true);
				input_tip_dialog.setSize(190,100);
				input_tip_dialog.setLocation(560,250);
				input_tip_dialog.setLayout(null);
				input_tip_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

				JLabel input_tip_text = new JLabel("无需要赋值的变量");
				input_tip_text.setForeground(Color.black);
				input_tip_text.setBounds(36, 5, 170, 20);
				input_tip_text.setToolTipText("无需要赋值的变量");


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
				RPDialog.getDialog().showInputDialog();
			}
		}

		if(arg0.getActionCommand().equals("button_submit")){
			RPJpanel.getJpanel().showResult();
		}
	}

	public static RPEventHandler getRPEventHandler(){
		if(rpEventHandler==null){
			rpEventHandler = new RPEventHandler();
		}

		return rpEventHandler;
	}

}
