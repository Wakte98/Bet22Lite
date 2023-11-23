package businessLogic;

import java.net.URL;
import java.util.Locale;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import gui.MainGUI;


public class BLFactory {

	BLFacade appFacadeInterface;
	
	ConfigXML c=ConfigXML.getInstance();
	
	
	public BLFacade getLocalFactory () {
		
		System.out.println(c.getLocale());
		
		Locale.setDefault(new Locale(c.getLocale()));
		
		System.out.println("Locale: "+Locale.getDefault());
		
		
		try {
			if (c.isBusinessLogicLocal()) {

				DataAccess da= new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
				appFacadeInterface=new BLFacadeImplementation(da);
		
			}
		
			else { //If remote
			
				String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";
			 
				//URL url = new URL("http://localhost:9999/ws/ruralHouses?wsdl");
				URL url = new URL(serviceName);

	 
				//1st argument refers to wsdl document above
				//2nd argument is service name, refer to wsdl document above
				//QName qname = new QName("http://businessLogic/", "FacadeImplementationWSService");
				QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");
	 
				Service service = Service.create(url, qname);

				appFacadeInterface = service.getPort(BLFacade.class);
			} 
			
			MainGUI.setBussinessLogic(appFacadeInterface);
		
	}catch (Exception e) {		
		System.out.println("Error in ApplicationLauncher: "+e.toString());
	}
	
		
		return appFacadeInterface;

	}
	
}




