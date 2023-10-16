import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Registered;

public class RankingLortuDABTest {
	
	@Mock
	private DataAccess dbManager;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	/**
	 * @author josus
	 * Comprueba que rankingLortu devuelve una lista vacía cuando no hay
	 * entradas de tipo Registered en la base de datos.
	 */
	public void testRankingLortuDBVacia() {
		List<Registered> listaVacia = new ArrayList<Registered>();
		//Establezco el comportamiento del mock paran que dbManager.rankingLortu() devuelva la lista result vacia
        Mockito.doReturn(listaVacia).when(dbManager).rankingLortu();
        
        //Llamo al método rankigLortu()
        BLFacadeImplementation facade = new BLFacadeImplementation(dbManager);
        List<Registered> result = facade.rankingLortu();
        
        assertEquals(listaVacia, result);
	}
	
	@Test
	/**
	 * @author josus
	 * Comprueba que rankingLortu devuelve una lista con un solo elemento 
	 * cunado solo hay una entrada de tripo Registered en la base de 
	 * datos, y se asegura de que sea el elemento esperado.
	 */
	public void testRankingLortuUnicoRegistro() {
		List<Registered> unicoRegistro = new ArrayList<Registered>();
		
		Registered user1 = mock(Registered.class);
		when(user1.getUsername()).thenReturn("user1");
		when(user1.getPassword()).thenReturn("psswrd1");
		when(user1.getBankAccount()).thenReturn(10123);
		
		String name = user1.getUsername();
		String password = user1.getPassword();
		Integer bankAccount = user1.getBankAccount();
		
		unicoRegistro.add(user1);
		
		//Establezco el comportamiento del mock paran que dbManager.rankingLortu() devuelva la lista con un único registro
        Mockito.doReturn(unicoRegistro).when(dbManager).rankingLortu();
        
        BLFacadeImplementation facade = new BLFacadeImplementation(dbManager);
        facade.storeRegistered(name, password, bankAccount);
        List<Registered> result = facade.rankingLortu();
        assertEquals(unicoRegistro, result);
	}
	
	@Test
	/**
	 * @author josus
	 * Genera varias instancias del tipo Registered, los añade a la 
	 * base de datosy comprueba que todos ellos se encuentren en el resultante
	 * a la llamada al método rankingLortu. Comprueba tambien que rankingLortu
	 * no esté devolviendo datos de más. 
	 */
	public void testRankingLortuVariosRegistros() {
		List<Registered> variosRegistros = new ArrayList<Registered>();
		
		Registered user1 = mock(Registered.class);
		when(user1.getUsername()).thenReturn("user1");
		when(user1.getPassword()).thenReturn("psswrd1");
		when(user1.getBankAccount()).thenReturn(10123);
		
		Registered user2 = mock(Registered.class);
		when(user2.getUsername()).thenReturn("user2");
		when(user2.getPassword()).thenReturn("psswrd2");
		when(user2.getBankAccount()).thenReturn(20123);
		
		String name2 = user2.getUsername();
		String password2 = user2.getPassword();
		Integer bankAccount2 = user2.getBankAccount();
		
		Registered user3 = mock(Registered.class);
		when(user3.getUsername()).thenReturn("user3");
		when(user3.getPassword()).thenReturn("psswrd3");
		when(user3.getBankAccount()).thenReturn(30123);
		
		String name3 = user3.getUsername();
		String password3 = user3.getPassword();
		Integer bankAccount3 = user3.getBankAccount();
		
		variosRegistros.add(user1);
		variosRegistros.add(user2);
		variosRegistros.add(user3);
		
		//Establezco el comportamiento del mock paran que dbManager.rankingLortu() devuelva la lista con varios registros
        Mockito.doReturn(variosRegistros).when(dbManager).rankingLortu();
        
        //Añado los usuarios a la base de datos
        BLFacadeImplementation facade = new BLFacadeImplementation(dbManager);
        facade.storeRegistered(name2, password2, bankAccount2);
        facade.storeRegistered(name3, password3, bankAccount3);
        
        //Llamo a la funcion rankingLortu
        List<Registered> result = facade.rankingLortu();
        
        //Compruebo que el numero de elementos devueltos es el esperado
        assertEquals(variosRegistros.size(), result.size());
        
        //Compruebo que cada elemento de la lista de usuarios que he creado esté en el resultado
        for (Registered registered : result) {
			assertTrue(variosRegistros.contains(registered));
		}
        
	}

}
