package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.Event;
import domain.Registered;


public class AdminGUI extends JFrame {
	private static String etiquetas = "Etiquetas";
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonCreateQuery = null;
	private JButton jButtonQueryQueries = null;

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	protected JLabel jLabelSelectOption;
	private JButton btnNewButton;
	private JButton btnEtiquetas;
	
	private JButton jButtonEmaitzaIpini;
	private JButton jButtonDesLogin;
	private JFrame thisw;
	private JButton btnGertaerakKopiatu;
	
	/**
	 * This is the default constructor
	 */
	public AdminGUI(Registered u) {
		super();
		thisw=this;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {

					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(495, 300);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle(etiquetas).getString("AdminTitle"));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setBackground(Color.WHITE);
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getBoton3());
			jContentPane.add(getBoton2());
			jContentPane.add(getBtnNewButton());
			jContentPane.add(getBtnNewButton_1());
			jContentPane.add(getJButtonEmaitzaIpini());
			
			JButton jButtonGertaerakEzabatu = new JButton(ResourceBundle.getBundle(etiquetas).getString("GertaerakEzabatu")); //$NON-NLS-1$ //$NON-NLS-2$
			jButtonGertaerakEzabatu.setForeground(Color.DARK_GRAY);
			jButtonGertaerakEzabatu.setBackground(Color.PINK);
			jButtonGertaerakEzabatu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new GertaeraEzabatuGUI(new Vector<Event>());
					a.setVisible(true);
				}
			});
			jButtonGertaerakEzabatu.setBounds(239, 124, 232, 30);
			jContentPane.add(jButtonGertaerakEzabatu);
			jContentPane.add(getJButtonDesLogin());
			jContentPane.add(getBtnGertaerakKopiatu());
		}
		return jContentPane;
	}


	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton2() {
		if (jButtonCreateQuery == null) {
			jButtonCreateQuery = new JButton();
			jButtonCreateQuery.setForeground(Color.DARK_GRAY);
			jButtonCreateQuery.setBackground(Color.PINK);
			jButtonCreateQuery.setBounds(10, 84, 213, 30);
			jButtonCreateQuery.setText(ResourceBundle.getBundle(etiquetas).getString("CreateQuery"));
			jButtonCreateQuery.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new CreateQuestionGUI(new Vector<Event>());
					a.setVisible(true);
				}
			});
		}
		return jButtonCreateQuery;
	}
	
	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setForeground(Color.DARK_GRAY);
			jButtonQueryQueries.setBackground(Color.PINK);
			jButtonQueryQueries.setBounds(10, 44, 213, 30);
			jButtonQueryQueries.setText(ResourceBundle.getBundle(etiquetas).getString("QueryQueries"));
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new FindQuestionsGUI();

					a.setVisible(true);
				}
			});
		}
		return jButtonQueryQueries;
	}
	

	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle(etiquetas).getString("SelectOption"));
			jLabelSelectOption.setOpaque(true);
			jLabelSelectOption.setBackground(Color.PINK);
			jLabelSelectOption.setBounds(110, 10, 249, 20);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton(ResourceBundle.getBundle(etiquetas).getString("KuotakIpini")); //$NON-NLS-1$ //$NON-NLS-2$
			btnNewButton.setBackground(Color.PINK);
			btnNewButton.setForeground(Color.DARK_GRAY);
			btnNewButton.setBounds(239, 44, 232, 30);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new KuotakIpiniGUI(new Vector<Event>());
					a.setVisible(true);
				}
			});
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_1() {
		if (btnEtiquetas == null) {
			btnEtiquetas = new JButton(ResourceBundle.getBundle(etiquetas).getString("GertaerakSortu")); //$NON-NLS-1$ //$NON-NLS-2$
			btnEtiquetas.setBackground(Color.PINK);
			btnEtiquetas.setForeground(Color.DARK_GRAY);
			btnEtiquetas.setBounds(239, 84, 232, 30);
			btnEtiquetas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new GertaerakSortuGUI();
					a.setVisible(true);
				}
			});
		}
		return btnEtiquetas;
	}
	private JButton getJButtonEmaitzaIpini() {
		if (jButtonEmaitzaIpini == null) {
			jButtonEmaitzaIpini = new JButton(ResourceBundle.getBundle(etiquetas).getString("EmaitzaIpini"));
			jButtonEmaitzaIpini.setForeground(Color.DARK_GRAY);
			jButtonEmaitzaIpini.setBackground(Color.PINK);
			jButtonEmaitzaIpini.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new EmaitzakIpiniGUI(new Vector<Event>());
					a.setVisible(true);
				}
			});
			jButtonEmaitzaIpini.setBounds(10, 124, 213, 30);
		}
		return jButtonEmaitzaIpini;
	}
	
	
	private JButton getJButtonDesLogin() {
		if (jButtonDesLogin == null) {
			jButtonDesLogin = new JButton(ResourceBundle.getBundle(etiquetas).getString("DesLogin")); //$NON-NLS-1$ //$NON-NLS-2$
			jButtonDesLogin.setForeground(Color.WHITE);
			jButtonDesLogin.setBackground(Color.DARK_GRAY);
			jButtonDesLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new MainUserGUI();
					a.setVisible(true);
					thisw.setVisible(false);
				}
			});
			jButtonDesLogin.setBounds(332, 208, 139, 28);
		}
		return jButtonDesLogin;
	}
	
	private JButton getBtnGertaerakKopiatu() {
		if (btnGertaerakKopiatu == null) {
			btnGertaerakKopiatu = new JButton(ResourceBundle.getBundle(etiquetas).getString("GertaerakKopiatu")); //$NON-NLS-1$ //$NON-NLS-2$
			btnGertaerakKopiatu.setForeground(Color.DARK_GRAY);
			btnGertaerakKopiatu.setBackground(Color.PINK);
			btnGertaerakKopiatu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new GertaerakKopiatuGUI();
					a.setVisible(true);
				}
			});
			btnGertaerakKopiatu.setBounds(239, 164, 232, 28);
		}
		return btnGertaerakKopiatu;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

