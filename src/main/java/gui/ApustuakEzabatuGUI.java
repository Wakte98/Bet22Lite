package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Registered;

public class ApustuakEzabatuGUI extends JFrame{
	
	private BLFacade businessLogic = MainGUI.getBusinessLogic();
	
	private static final long serialVersionUID = 1L;
	private Registered user;

	private JComboBox<ApustuAnitza> comboBox;
	//DefaultComboBoxModel<ApustuaContainer> modelApustua = new DefaultComboBoxModel<ApustuaContainer>();

	private JButton jButtonClose;

	private JButton jButtonEzabatu;

	private JLabel lblComboBox; 
	
	DefaultComboBoxModel<ApustuAnitza> listApustuak = new DefaultComboBoxModel<ApustuAnitza>();
	
	private boolean amaituta;
	
	public ApustuakEzabatuGUI(Registered u) {
		user = u; 
		this.getContentPane().setLayout(null);
		
		lblComboBox = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ApustuLista"));
		lblComboBox.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblComboBox.setHorizontalAlignment(SwingConstants.CENTER);
		lblComboBox.setBounds(186, 62, 215, 21);
		getContentPane().add(lblComboBox);
		
		comboBox = new JComboBox<ApustuAnitza>();
		comboBox.setBounds(10, 105, 570, 21);
		comboBox.setModel(listApustuak);
		for(ApustuAnitza ap : businessLogic.findApustuAnitza(user)){
			amaituta=false;
			if(ap.getEgoera().equals("jokoan")) {
				extracted(ap);	
				if(!amaituta) {
					listApustuak.addElement(ap);
				}
			}
		}
		getContentPane().add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed();
			}
		});
		jButtonClose.setBounds(347, 245, 101, 33);
		getContentPane().add(jButtonClose);
		
		
		jButtonEzabatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Ezabatu"));
		if(listApustuak.getSize()==0) {
			jButtonEzabatu.setEnabled(false); 
		}else {
			jButtonEzabatu.setEnabled(true);
		}
		jButtonEzabatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApustuAnitza apustua = (ApustuAnitza) comboBox.getSelectedItem();
				
				businessLogic.apustuaEzabatu(user, apustua);
				
				listApustuak.removeAllElements();
				extracted2();
				if(listApustuak.getSize()==0) {
					jButtonEzabatu.setEnabled(false); 
				}else {
					jButtonEzabatu.setEnabled(true);
				}
			}

			private void extracted2() {
				for(ApustuAnitza ap : businessLogic.findApustuAnitza(user)){
					amaituta=false;
					if(ap.getEgoera().equals("jokoan")) {
						extracted(ap);	
						if(!amaituta) {
							listApustuak.addElement(ap);
						}
					}
				}
			}
		});
		jButtonEzabatu.setBounds(160, 246, 117, 30);
		getContentPane().add(jButtonEzabatu);
		this.setSize(new Dimension(604, 370));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("ApustuaEzabatu"));
	}

	private void extracted(ApustuAnitza ap) {
		for(Apustua a: ap.getApustuak()) {
			if(new Date().compareTo(businessLogic.findEventFromApustua(a).getEventDate())>=0) {
				amaituta=true;
			}
		}
	}
	
	private void jButtonClose_actionPerformed() {
		this.setVisible(false);
	}
	
}
