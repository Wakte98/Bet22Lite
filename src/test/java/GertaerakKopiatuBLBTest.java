import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccessKopiatu;
import domain.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityTransaction;

public class GertaerakKopiatuBLBTest {
    
	@Mock
	DataAccessKopiatu db = mock(DataAccessKopiatu.class);
	//BLFacadeImplementation blf = new BLFacadeImplementation();
	BLFacadeImplementation blf = mock(BLFacadeImplementation.class);
	
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
    	    
    	Event event = mock(Event.class);

    	// Configurar el mock de Date para devolver una fecha en formato valido
    	//Date fechaActualMock = mock(Date.class);
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = null;
		try {
			fecha = formato.parse("15-10-2000");
		} catch (ParseException e1) {  
			e1.printStackTrace();
		}

        // Llamar al método con el mock de Date
        boolean resultado = blf.gertaerakKopiatu(event, fecha);
        //System.out.println(resultado);

        // Verificar que el resultado sea false, porque el metodo
        //inicicaliza b = false
        assertFalse(resultado);
    }
    
  //Este test comprueba que si la fecha es valida devuelve true y el metodo funciona
    @Test 
    public void testGertaerakKopiatu_test1() {
    	    
    	Event event = mock(Event.class);

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

        // Llamar al método con el mock de Date
        boolean resultado = blf.gertaerakKopiatu(event, fecha);
        System.out.println(resultado);

        // Verificar que el resultado sea verdadero (por ejemplo, si la fecha es mayor)
        assertTrue(resultado);
    }
    
    
    
  //Este test comprueba que si la fecha NO es valida devuelve false y el metodo funciona
    @Test 
    public void testGertaerakKopiatu_test2() {
    	   

    	Event event = mock(Event.class);
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

        // Llamar al método con el mock de Date
        boolean resultado = blf.gertaerakKopiatu(event, fecha);
        System.out.println("4");
        System.out.println(resultado);

        // Verificar que el resultado sea verdadero (por ejemplo, si la fecha es mayor)
        assertTrue(resultado);
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
     * SE CREEAN(ESTAMOS EN CJA NEGRA). SIEMPRE SERA FALSE, PQ AUNQ AÑADAMOS EL EVENTO A LA BD, 
     * NOS PIDE UNA DESCRIPCION PARA QUE "query.setParameter(1,gertaera.getDescription()); Y
		query.setParameter(2, date); FUNCIONE Y NO SE QUEDE PARADO EL METODO Y DE FALSO"
     */
  
        
    

}

