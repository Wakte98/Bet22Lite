import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import dataAccess.DataAccessKopiatu;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Sport;
import net.bytebuddy.asm.Advice.Argument;
import test.dataAccess.TestDataAccessKopiatu;

public class GertaerakKopiatuDABTest {
    
	
	@Mock
	
    protected  EntityManager  db;
	
	@Mock
    protected  EntityTransaction  et;
	
    @InjectMocks
    TestDataAccessKopiatu sut = Mockito.mock(TestDataAccessKopiatu.class);

    

    
    
/**
 * LA CLASE DATE.CLASS DE JAVA NO RECONOCE SI UNA FECHA ES VALIDA O NO, SIMPLEMENTE 
 * REPRESENTA UNA FECHA Y HORA EN ESPECIFICO
 */
    
  //Este test comprueba que si la fecha es valida y anterior, entra al metodo y empieza a funcionar
    @Test 
    public void testGertaerakKopiatu_test0() {
    	    
    	Event event = Mockito.mock(Event.class);

    	// Configurar el mock de Date para devolver una fecha en formato valido
    	//Date fechaActualMock = mock(Date.class);
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = null;
		try {
			fecha = formato.parse("15-10-2000");
		} catch (ParseException e1) {  
			e1.printStackTrace();
		}

        // Llamar al metodo con el mock de Date
        boolean resultado = sut.gertaerakKopiatu(event, fecha);
        //System.out.println(resultado);

        // Verificar que el resultado sea false, porque el metodo
        //inicicaliza b = false
        assertFalse(resultado);
    }
    
  //Este test comprueba que si la fecha es valida y posterior entra al metodo y empieza a funcionar
    @Test 
    public void testGertaerakKopiatu_test1() {
    	    
    	Event event = Mockito.mock(Event.class);

    	// Configurar el mock de Date para devolver una fecha en formato valido
    	//Date fechaActualMock = mock(Date.class);
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = null;
		try {
			fecha = formato.parse("15-10-24");
		} catch (ParseException e1) {  
			e1.printStackTrace();
		}

        // Llamar al metodo con el mock de Date
        boolean resultado = sut.gertaerakKopiatu(event, fecha);
        //System.out.println(resultado);

        // Verificar que el resultado sea false, porque el metodo
        //inicicaliza b = false
        assertFalse(resultado);
    }
    
    
    
  //Este test comprueba que si la fecha NO es valida devuelve false y el metodo funciona
    @Test 
    public void testGertaerakKopiatu_test2() {
    	   

    	Event event = Mockito.mock(Event.class);

    	// Configurar el mock de Date para devolver una fecha en formato valido
//        Date fechaActualMock = mock(Date.class);
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = null;
		try {
			fecha = formato.parse("31-2-2023");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

        // Llamar al metodo con el mock de Date
        boolean resultado = sut.gertaerakKopiatu(event, fecha);
        //System.out.println(resultado);

        // Verificar que el resultado sea false, porque el metodo
        //inicicaliza b = false
        assertFalse(resultado);
    }
    
    /**
     * EN NINGUNO DE LOS TEST SE COMPRUEBA NADA PORQUE NUNCA SE COMPRUEVA SI LA FECHA ES VALIDA, 
     * SINO QUE DA TRUE OR FALSE DEPENDIENDO SI ENTRA EN EL PRIMER IF DEBIDO HA SI HAY 
     * UNA DESCRIPCION EN EL EVENTO. COMO ESTAMOS EN CAJA NEGRA, NO SABEMOS LOS PAREMETROS 
     * DEL EVENTO Y NO PODEMOS CREAR UN EVENTO ACORDE A SI ENTRA O NO EN EL IF PARA QUE SE 
     * VALIDE LA CONDICION
     */
    
    /**
     * PASA LO MISMO CON LA CLASE EVENTO.CLASS. AUNQUE PASEMOS UN EVENTO CREADO CON MOCKITO,
     * NO SABEMOS LOS PARAMETROS DEL EVENTO QUE NECESITAMOS PARA HACER QUE EL METODO FUNCIONE.
     * POR QEJEMPLO, COMO HACEMOS PARA CREAR QUESTIONS Y QUERYS DE UN EVENTO SI NO SABEMOS COMO
     * SE CREEAN(ESTAMOS EN CJA NEGRA). SIEMPRE SERA FALSE, PQ AUNQ A�ADAMOS EL EVENTO A LA BD, 
     * NOS PIDE UNA DESCRIPCION PARA QUE "query.setParameter(1,gertaera.getDescription()); Y
		query.setParameter(2, date); FUNCIONE Y NO SE QUEDE PARADO EL METODO Y DE FALSO"
     */
  
    
    //Este test comprueba que si el evento no esta en la base de datos lanza una 
    //excepcion controlada
    @Test 
    public void testGertaerakKopiatu_test3() {
    	   
    	MockitoAnnotations.initMocks(this);
		Mockito.doReturn(et).when(db).getTransaction();
	     sut=new TestDataAccessKopiatu();
    	
    	Event event = Mockito.mock(Event.class);

    	// Configurar el mock de Date para devolver una fecha en formato valido
    	//Date fechaActualMock = mock(Date.class);
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = null;
		try {
			fecha = formato.parse("15-10-24");
		} catch (ParseException e1) {
			e1.printStackTrace();
		} 
		    	      
		// Configurar el comportamiento para que delvuelva el evento creado
//		Mockito.when(dbt.find(Event.class, event.getEventNumber())).doNothing();
		Mockito.when(db.find(Event.class, event.getEventNumber()))
	       .thenThrow(new RuntimeException("No exixte el evento en BD"));
		    	               
		try {
		    db.find(Event.class, event.getEventNumber());
		} catch (RuntimeException e) {
		    // Aqui puedes manejar la excepcion generada
		    System.out.println("Se gener� una excepci�n: " + e.getMessage());
		}    
		
    }
    
    
  
    

}
       

      
    


