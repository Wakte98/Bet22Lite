package gui;

import javax.swing.JTable;

import domain.Registered;
import adapter.UserAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class WindowTable extends JFrame {
	private Registered user;
	private JTable tabla;
	private UserAdapter uAdapter;
	

	public WindowTable(Registered user) {
		super("Apuestas	realizadas	por	"+	user.getUsername()+":");
		setBounds(10, 10, 1000, 600);
		this.user = user;
		UserAdapter adapt = new UserAdapter(user);
		tabla = new JTable(adapt);
		tabla.setPreferredScrollableViewportSize(new Dimension(500,	70));
		//Creamos un JscrollPane	y	le agregamos la JTable
		JScrollPane	scrollPane =	new JScrollPane(tabla);
		//Agregamos el	JScrollPane	al contenedor
		getContentPane().add(scrollPane,	BorderLayout.CENTER);
		}
}