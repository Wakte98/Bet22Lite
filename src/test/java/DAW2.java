//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import configuration.ConfigXML;
//import dataAccess.DataAccess;
//import dataAccess.DataAccessKopiatu;
//import domain.Event;
//import domain.Question;
//import domain.Quote;
//import domain.Sport;
//import exceptions.EventFinished;
//import exceptions.QuestionAlreadyExist;
//import test.businessLogic.TestFacadeImplementation;
//import test.dataAccess.TestDataAccess;
//import test.dataAccess.TestDataAccessKopiatu;
//
//
//public class DAW2 {
//
//	 //sut:system under test
//	 static DataAccess sut=new DataAccess();
//	 
//	 //additional operations needed to execute the test 
//	 static TestDataAccessKopiatu testdb=new TestDataAccessKopiatu(); 
//	
//
//	private Event ev;
//	
//	
//	//Este test comprueba que si la lista NO esta vac�a, NO entra en el IF y acaba el metodo
//	@Test 
//	public void test1() {
//		try {
//			
//			//Elegimos una fecha valida para comprobar que se accede al metodo
//	        //String fechaStr = "2023-10-15"; Ejemplo de cadena que representa una fecha
//	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//	        Date future = null;
//			try {
//				future = sdf.parse("2024-10-15");
//			} catch (ParseException e) { 
//				e.printStackTrace();
//			}
//			//Creamos un evento
//			Event event1 = new Event(1, "Bar�a-Madrid", future, null, null);
//			Event event2 = new Event(2, "Bar�a-Madrid", future, null, null);
//			
//			//configure the state of the system (create object in the dabatase)
//			testdb.open();
//			ev = testdb.addEvent(event1);
//			ev = testdb.addEvent(event2);
//			testdb.close();	
//			
//			
//			//invoke System Under Test (sut)  
//			boolean result =sut.gertaerakKopiatu(event1, future);
//			
//			
//			//verify the results
//			assertFalse(result);
//		
//			
//		} finally {
//				  //Remove the created objects in the database (cascade removing)   
//				testdb.open();
//				boolean b=testdb.removeEvent(ev);
//				testdb.close();
//				System.out.println("Finally "+b);          
//		}
//	}	
//		
//					
//			
//	//Este test comprueba que si la lista de preguntas del evento SI esta vacia,
//    // no recorre el primer for y acaba el metodo
//	@Test 
//	public void test2() {
//		try {
//			
//			//Elegimos una fecha valida para comprobar que se accede al metodo
//	        //String fechaStr = "2023-10-15"; Ejemplo de cadena que representa una fecha
//	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//	        Date future = null;
//			try {
//				future = sdf.parse("2024-10-15");
//			} catch (ParseException e) { 
//				e.printStackTrace();
//			}
//			//Creamos un evento
//			Event event = new Event(2, "Bar�a-Madrid", future, null, null);
//			
//			
//			//configure the state of the system (create object in the dabatase)
//			testdb.open(); 
//			ev = testdb.addEvent(event);
//			testdb.close();	
//			
//			
//			//invoke System Under Test (sut)  
//			boolean result =sut.gertaerakKopiatu(event, future);
//			
//			
//			//verify the results
//			assertTrue(result);
//			
//		} finally {
//				  //Remove the created objects in the database (cascade removing)   
//				testdb.open();
//				boolean b=testdb.removeEvent(ev);
//				testdb.close();
//				System.out.println("Finally "+b);          
//		}
//	}
//	
//	//Este test comprueba que la lista de preguntas del evento NO esta vacia, pero
//    //la lista de Quotes de la pregunta SI esta vacia, recorre el primer bucle, pero no el segundo
//    @Test 
//	public void test3() {
//		try {
//			
//			//Elegimos una fecha valida para comprobar que se accede al metodo
//	        //String fechaStr = "2023-10-15"; Ejemplo de cadena que representa una fecha
//	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//	        Date future = null;
//			try {
//				future = sdf.parse("2024-10-15");
//			} catch (ParseException e) { 
//				e.printStackTrace();
//			}
//			//Creamos un evento
//			Event event = new Event(2, "Bar�a-Madrid", future, null, null);
//			//Creamos un Sport
//			Sport sp1;
//			//Creamos preguntas
//			Question q1;
//			Question q2;
//			
//			
//			//configure the state of the system (create object in the dabatase)
//			testdb.open(); 
//			ev = testdb.addEvent(event);
//			sp1 = testdb.addSport(event, "Futbol");
//			q1 = testdb.addQuestions(event, "¿Quién ganará el partido?", 1);
//			q2 = testdb.addQuestions(event, "¿Quién ganará el partido?", 2);
//			testdb.close();	
//			
//			
//			//invoke System Under Test (sut)  
//			boolean result =sut.gertaerakKopiatu(event, future);
//			
//			
//			//verify the results
//			assertTrue(result);
//			
//		} finally {
//				  //Remove the created objects in the database (cascade removing)   
//				testdb.open();
//				boolean b=testdb.removeEvent(ev);
//				testdb.close();
//				System.out.println("Finally "+b);          
//		}
//	}
//    
//    
//    //Este test comprueba que la lista de preguntas del evento NO esta vacia, y
//    //la lista de Quotes de la pregunta NO esta vacia, por lo que recorre el  
//    //primer y segundo bucle
//    @Test 
//	public void test4() {
//		try {
//			
//			//Elegimos una fecha valida para comprobar que se accede al metodo
//	        //String fechaStr = "2023-10-15"; Ejemplo de cadena que representa una fecha
//	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//	        Date future = null;
//			try {
//				future = sdf.parse("2024-10-15");
//			} catch (ParseException e) { 
//				e.printStackTrace();
//			}
//			//Creamos un evento
//			Event event = new Event(2, "Bar�a-Madrid", future, null, null);
//			//Creamos un Sport
//			Sport sp1;
//			//Creamos preguntas
//			Question q1;
//			Question q2;
//			//Creamos cuotas
//			Quote qu1;
//			Quote qu2;
//			
//			
//			//configure the state of the system (create object in the dabatase)
//			testdb.open(); 
//			ev = testdb.addEvent(event);
//			sp1 = testdb.addSport(event, "Futbol");
//			q1 = testdb.addQuestions(event, "¿Quién ganará el partido?", 1);
//			q2 = testdb.addQuestions(event, "¿Quién ganará el partido?", 2);
//			qu1 = testdb.addQuotes(event, 3.14, "X", q1);
//			qu2 = testdb.addQuotes(event, 3.5, "1", q2);
//			testdb.close();	
//			
//			
//			//invoke System Under Test (sut)  
//			boolean result =sut.gertaerakKopiatu(event, future);
//			
//			
//			//verify the results
//			assertTrue(result);
//			
//		} finally {
//				  //Remove the created objects in the database (cascade removing)   
//				testdb.open();
//				boolean b=testdb.removeEvent(ev);
//				testdb.close();
//				System.out.println("Finally "+b);          
//		}
//	}
//}