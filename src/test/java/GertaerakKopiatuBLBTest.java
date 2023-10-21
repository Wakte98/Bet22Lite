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
import org.mockito.junit.MockitoJUnitRunner;

import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccessKopiatu;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Sport;
import net.bytebuddy.asm.Advice.Argument;

import javax.persistence.EntityTransaction;

public class GertaerakKopiatuBLBTest {
    
	@Mock
	DataAccessKopiatu db = Mockito.mock(DataAccessKopiatu.class);
	//BLFacadeImplementation blf = new BLFacadeImplementation();
	BLFacadeImplementation blf = Mockito.mock(BLFacadeImplementation.class);
	
	@Mock
    protected  EntityTransaction  et;
	
    @InjectMocks
    public DataAccessKopiatu sut;
    //protected DataAccessEliminarApuesta sut=new DataAccessEliminarApuesta(db);
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

        boolean resultado = blf.gertaerakKopiatu(event, fecha);
        //System.out.println(resultado);

        // Verificar que el resultado sea false, porque el metodo
        //inicicaliza b = false
        assertFalse(resultado);
    }
    
  //Este test comprueba que si la fecha es valida devuelve true y el metodo funciona
    @Test 
    public void testGertaerakKopiatu_test1() {
    	    
    	Event event = Mockito.mock(Event.class);

    	// Configurar el mock de Date para devolver una fecha valida
//        Date fechaActualMock = mock(Date.class);
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = null;
		try {
			fecha = formato.parse("15-10-24");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		Mockito.when(blf.gertaerakKopiatu(event, fecha)).thenReturn(true);
		System.out.println("3");

        boolean resultado = blf.gertaerakKopiatu(event, fecha);
        System.out.println(resultado);

        // Verificar que el resultado sea verdadero (por ejemplo, si la fecha es mayor)
        assertTrue(resultado);
    }
    
    
    
  //Este test comprueba que si la fecha NO es valida devuelve false y el metodo funciona
    @Test 
    public void testGertaerakKopiatu_test2() {
    	   

    	Event event = Mockito.mock(Event.class);
    	System.out.println("1");
    	// Configurar el mock de Date para devolver una formato valido
//        Date fechaActualMock = mock(Date.class);
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = null;
		try {
			fecha = formato.parse("15-10-24");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		System.out.println("2");
		
		Mockito.when(blf.gertaerakKopiatu(event, fecha)).thenReturn(true);
		System.out.println("3");

        boolean resultado = blf.gertaerakKopiatu(event, fecha);
        System.out.println("4");
        System.out.println(resultado);

        // Verificar que el resultado sea verdadero (por ejemplo, si la fecha es mayor)
        assertTrue(resultado);
    }
    
        
    

}

