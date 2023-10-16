import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import dataAccess.DataAccessKopiatu;
import domain.Event;
import domain.Registered;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class RankingLortuDAWTest {
	
	@Mock
	protected EntityManager db;
	
	@Mock
    protected  EntityTransaction  et;
	
    @InjectMocks
    public DataAccessKopiatu sut;
    
    private TypedQuery<Registered> Rquery;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		Mockito.doReturn(et).when(db).getTransaction();
	    sut=new DataAccessKopiatu(db);
	    Rquery = mock(TypedQuery.class);
	}
	
	@Test
	/**
	 * @author josus
	 * Define el comportamiento del TypedQuery para que devuelva una
	 * lista de tipo Registered vacía y, a continuación comprueva que 
	 * el método rankingLortu devuelve una lista vacía.
	 */
	public void testRankingLortuDBVacia() {
		//Configuro el comportamiento del mock TypedQuery para que devuelva una lista vacía
		when(db.createQuery("SELECT r FROM Registered r", Registered.class)).thenReturn(Rquery);
		when(Rquery.getResultList()).thenReturn(new ArrayList<Registered>());
		
		//Llamo al método rankingLortu()
		List<Registered> result = sut.rankingLortu();
		
		//Verifico que el resultado no sea nulo
		assertNotNull(result);
		
		//Verifico que la lista devuelta es vacía
		assertTrue(result.isEmpty());
	}
	
	@Test
	/**
	 * @author josus
	 * Crea un objeeto de tipo Registered y define el comportamiento 
	 * del TypedQuery para que devuelva una lista de tipo Registered
	 * con unicamente dicho elemento. A continuación comprueba que el 
	 * método devuelve la lista con el único elemento.
	 */
	public void testRankingLortuUnicoRegistro() {
		//Simulo un Registered
		Registered user = new Registered("user1", "psswrd1", 101234);
		
		//Creo una lista con el unico usuario
		List<Registered> listaConUnRegistro = new ArrayList<>();
        listaConUnRegistro.add(user);
        
        //Defino el comportamiento del TypedQuery para que devuelva el unico usuario
        when(db.createQuery("SELECT r FROM Registered r", Registered.class)).thenReturn(Rquery);
        when(Rquery.getResultList()).thenReturn(listaConUnRegistro);
        
        //Llamo al método rankingLortu()
      	List<Registered> result = sut.rankingLortu();
      		
      	//Verifico que la lista resultante contenga al unico usuario
      	assertTrue(result.contains(user));
      	
      	//Verifico que el método no haya devuelto más de un solo dato
      	assertEquals(1, result.size());
	}
	
	@Test
	/**
	 * @author josus
	 * Crea varios objetos de tipo Registered, a los que asigna ganancias distintas. 
	 * Configura el TypedQuery para que devuelva una lista de estos usuarios pero no
	 * ordenados en ranking. A continuación comprueba que los elementos de la lista 
	 * que devuelve el método rankingLortu son los mismos que se han instanciado.
	 * Por último comprueba que los elementos que devuelve el método están ordenados
	 * de mayor a menor por ganancias en apuestas.
	 */
	public void testRankingLortuVariosRegistros() {
		//Simulo varios Registered
		Registered user1 = new Registered("user1", "psswrd1", 101234);
		Registered user2 = new Registered("user2", "psswrd2", 201234);
		Registered user3 = new Registered("user3", "psswrd3", 301234);
		Registered user4 = new Registered("user4", "psswrd4", 401234);
		
		//Establezco unas ganancias
		user1.setIrabazitakoa(150.00);
		user2.setIrabazitakoa(200.00);
		user3.setIrabazitakoa(350.00);
		user4.setIrabazitakoa(75.00);
		
		//Añado los usuarios a una lista de tal forma que no estén ordenados por ganancias
		List<Registered> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(user1);
        listaUsuarios.add(user2);
        listaUsuarios.add(user3);
        listaUsuarios.add(user4);
        
        //Defino el comportamiento del TypedQuery para que devuelva la lista de usuarios
        when(db.createQuery("SELECT r FROM Registered r", Registered.class)).thenReturn(Rquery);
        when(Rquery.getResultList()).thenReturn(listaUsuarios);
        
        //Llamo al método rankingLortu()
      	List<Registered> result = sut.rankingLortu();
      	
      	//Verifico que el resultado tenga el numero correcto de usuarios
      	assertEquals(result.size(), listaUsuarios.size());
      	
      	//Verifico que la lista resultante contenga los mismos usuarios
      	for (Registered r : result) {
			assertTrue(listaUsuarios.contains(r));
		}
      	
      	//Verifico que la lista resultante está ordenada en ranking de ganancias
      	assertTrue(isSorted(result));
	}
	
	/**
	 * @author josus
	 * @param list
	 * @return boolean
	 * Método que recibe una lista de Registered y devuelve true si esta lista está
	 * ordenada de mayor a menor por ganancias mediante el método getIrabazitakoa
	 * de la clase Registered. Devuelve Faalse en caso contrario.
	 */
	private boolean isSorted(List<Registered> list) {
		List<Registered> listaOrdenada = new ArrayList<>(list);
		listaOrdenada.sort(Comparator.comparingDouble(Registered::getIrabazitakoa).reversed());
		return list.equals(listaOrdenada);
	}

}
