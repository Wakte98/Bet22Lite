import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import dataAccess.DataAccessKopiatu;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Sport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class GertaerakKopiatuDAWTest {
    
	@Mock
    protected  EntityManager  db;
	
	@Mock
    protected  EntityTransaction  et;
	
    @InjectMocks
    public DataAccessKopiatu sut;
    
    @Before
    public void setUp() {
    	
        MockitoAnnotations.initMocks(this);
		Mockito.doReturn(et).when(db).getTransaction();
	     sut=new DataAccessKopiatu(db);

    }
    /**
     * PARA COMPROBAR LOS TEST DE LAS LISTAS VACIAS DE LOS BUCLES FOR:
     * COMENTAR EL RESTO DE TEST, YA QUE EL RESSTO DE TEST MARCAN UN 100 DE COVERAGE
     * Y NO SE IDENTIFICA SI EL TEST FUNCIONA SEGUN LO COMENTADO ANTES DEL MISMO TEST
     */
    
    
    //Este test comprueba que si la lista NO esta vacía, NO entra en el IF y acaba el metodo
    @Test 
    public void testGertaerakKopiatu_test1() {
    	
    	//Elegimos una fecha valida para comprobar que se accede al metodo
        //String fechaStr = "2023-10-15"; Ejemplo de cadena que representa una fecha
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date future = null;
		try {
			future = sdf.parse("2024-10-15");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//Creamos un evento
		Event event = new Event(2, "Barça-Madrid", future, null, null);
		// creamos una lista
		List<Event> resultList = new ArrayList<>();
		// Agregar elementos a resultList para simular una lista NO vacía
		resultList.add(new Event());

		// Mock de la consulta
		TypedQuery<Event> mockQuery = Mockito.mock(TypedQuery.class);
    	      
		// Configurar el comportamiento para que delvuelva el evento creado
		Mockito.when(db.find(Event.class, event.getEventNumber())).thenReturn(event);
    	               
		//Configurar para que devuelva mockQuery para cualquier consulta a la clase evento
        //con la llamada createQuery     
		Mockito.doReturn(mockQuery).when(db).createQuery(anyString(), eq(Event.class));
		
		//Configurar el comportamiento para que delvuelva la lista NO vacia
		when(mockQuery.getResultList()).thenReturn(resultList);
    	      
		//Llamada al metodo a comprobar
		boolean result = sut.gertaerakKopiatu(event, future);

		//Comprobamos que devuelve false si no accede el array de la consulta esta vacio
		assertFalse(result);     
    	
    }
    
    
    
    //Este test comprueba que si la lista de preguntas del evento SI esta vacia,
    // no recorre el primer for y acaba el metodo
    @Test
    public void testGertaerakKopiatu_test2() {
    	
        //Fecha valida y NO entra en el primer for
        //String fechaStr = "2023-10-15"; // Ejemplo de cadena que representa una fecha
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date future = null;
		try {
			future = sdf.parse("2024-10-15");
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	// Crear un objeto Event
				//creamos un evento y deporte
    	        Event event = new Event(2, "Barça-Madrid", future, null, null);
    	        Sport sport = mock(Sport.class);
    	        //creamos un vector Questions vacio
    	        Vector<Question> vq = new Vector<Question>();
    	        //añadimos el deporte y la lista de pregutnas al evento
    	        event.setSport(sport);
    	        event.setQuestions(vq);

   	        
        // Mock de la consulta
        TypedQuery<Event> mockQuery = Mockito.mock(TypedQuery.class);

        // Configurar el comportamiento del mockDb
        Mockito.when(db.find(Event.class, event.getEventNumber())).thenReturn(event);
        
        //Configurar para que devuelva mockQuery para cualquier consulta a la clase evento
        //con la llamada createQuery 
        Mockito.doReturn(mockQuery).when(db).createQuery(anyString(), eq(Event.class));
        
        //Configurar para que devuelva una lista vacia cuando preguntamos por la lista deel evento
        Mockito.when(mockQuery.getResultList()).thenReturn(List.of());

        // Instanciar la clase que contiene el método 
        DataAccessKopiatu sut = new DataAccessKopiatu(db);
        
        // Llamar al método que queremos probar
        boolean result = sut.gertaerakKopiatu(event, future);
  
        // Verificar que el método retorna true si date > hoy
        assertTrue(result);
        
    }
    
    
        
    //Este test comprueba que la lista de preguntas del evento NO esta vacia, pero
    //la lista de Quotes de la pregunta SI esta vacia, recorre el primer bucle, pero no el segundo
    @Test
    public void testGertaerakKopiatu_test3() {
    	
        //Fecha futura y todo funciona correctamente
        //String fechaStr = "2023-10-15"; // Ejemplo de cadena que representa una fecha
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date future = null;
		try {
			future = sdf.parse("2024-10-15");
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	// Crear un objeto Event
//    			Team Zgz = new Team("Zaragoza");
//    			Team Huesca = new Team("Huesca");
    	        Event event = new Event(2, "Barça-Madrid", future, null, null);
//    	        Event event1 = new Event();
    	        Sport sport = mock(Sport.class);
    	        //creamos un vector Questions NO vacio
    	        Vector<Question> vq = new Vector<Question>();
    	        //Creamos la pregunta
    	        Question q = mock(Question.class);
    	        //añadimos al vector Question q
    	        vq.add(q);
    	        //añadimos el deporte y la lista de pregutnas al evento
    	        event.setSport(sport);
    	        event.setQuestions(vq);
    	        //Creamos un vector vacio de Quote
    	        Vector<Quote> vk = new Vector<Quote>();
    	        
 

        // Mock de la consulta
        TypedQuery<Event> mockQuery = Mockito.mock(TypedQuery.class);
      
        // Configurar el comportamiento del mockDb
        Mockito.when(db.find(Event.class, event.getEventNumber())).thenReturn(event);
        
        //Configurar el comportamiento para que delvuelva el evento creado
        Mockito.doReturn(mockQuery).when(db).createQuery(anyString(), eq(Event.class));
        
        //Configurar para que devuelva la question creada anteriormente
        Mockito.when(db.find(Question.class, q.getQuestionNumber())).thenReturn(q);
        
        //Configurar para que devuelva un vector con quotes creado anteriormente(vk SI vacio)
        Mockito.when(q.getQuotes()).thenReturn(vk);
        
        // Instanciar la clase que contiene el método 
        DataAccessKopiatu sut = new DataAccessKopiatu(db);
        
        // Llamar al método que queremos probar
        boolean result = sut.gertaerakKopiatu(event, future);
       
        // Verificar que el método retorna true por lo que se copia correctamente el evento
        assertTrue(result);
        
    }
    
    
    //Este test comprueba que la lista de preguntas del evento NO esta vacia, y
    //la lista de Quotes de la pregunta NO esta vacia, por lo que recorre el  
    //primer y segundo bucle
    @Test
    public void testGertaerakKopiatu_test4() {
    	
        //Fecha futura y todo funciona correctamente
        //String fechaStr = "2023-10-15"; // Ejemplo de cadena que representa una fecha
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date future = null;
		try {
			future = sdf.parse("2024-10-15");
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	// Crear un objeto Event
//    			Team Zgz = new Team("Zaragoza");
//    			Team Huesca = new Team("Huesca");
    	        Event event = new Event(2, "Barça-Madrid", future, null, null);
//    	        Event event1 = new Event();
    	        Sport sport = mock(Sport.class);
    	        //creamos un vector Questions NO vacio
    	        Vector<Question> vq = new Vector<Question>();
    	        //Creamos la pregunta
    	        Question q = mock(Question.class);
    	        //añadimos al vector Question q
    	        vq.add(q);
    	        //añadimos el deporte y la lista de pregutnas al evento
    	        event.setSport(sport);
    	        event.setQuestions(vq);
    	        //Creamos un vector vacio de Quote
    	        Vector<Quote> vk = new Vector<Quote>();
    	        //creamos un par de Quotes
    	        Quote t = mock(Quote.class);
    	        Quote y = mock(Quote.class);
    	        //Añadimos al vector de Quotes las Quotes creadas
    	        vk.add(t);
    	        vk.add(y);
 

        // Mock de la consulta
        TypedQuery<Event> mockQuery = Mockito.mock(TypedQuery.class);
      
        // Configurar el comportamiento del mockDb
        Mockito.when(db.find(Event.class, event.getEventNumber())).thenReturn(event);
        
        //Configurar el comportamiento para que delvuelva el evento creado
        Mockito.doReturn(mockQuery).when(db).createQuery(anyString(), eq(Event.class));
        
        //Configurar para que devuelva la pregunta creada anteriormente
        Mockito.when(db.find(Question.class, q.getQuestionNumber())).thenReturn(q);
        
        //Configurar para que devuelva un vector con quotes NO vacio creado anteriormente
        Mockito.when(q.getQuotes()).thenReturn(vk);
        
        // Instanciar la clase que contiene el método
        DataAccessKopiatu sut = new DataAccessKopiatu(db);
        
        // Llamar al método que queremos probar
        boolean result = sut.gertaerakKopiatu(event, future);
       
        // Verificar que el método retorna true por lo que se copia correctamente el evento
        assertTrue(result);
        
    }
     
}  	




