package gui;

import java.awt.Color;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.UIManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Event;
import iterator.ExtendedIterator;

public class ApplicationLauncher { 
	
	
	
	public static void main(String[] args) {
		
		BLFactory x = new BLFactory();
		
		x.getLocalFactory();
		
	}
		

}

