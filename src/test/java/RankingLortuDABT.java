import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import dataAccess.DataAccess;
import domain.Registered;

class RankingLortuDABT {

	private DataAccess dataAccess;
	
	@BeforeEach
	void setUp() throws Exception {
		dataAccess = new DataAccess(true);
	}

	@Test
	void testRankingLortuDBVacia() {
		System.out.println("Comenzando el test 1");
		List<Registered> result = dataAccess.rankingLortu();
		for(Registered reg: result) {
			System.out.println(reg.getUsername() + "test1");
		}
        assertTrue(result.isEmpty());
	}

	@Test
	void testRankingLortuUnicoRegistro() {
		System.out.println("Comenzando el test 2");
		Registered singleElement = new Registered("User1", "contraseña1", 123456789, false);
		dataAccess.storeRegistered("User1", "contraseña1", 123456789);
		List<Registered> result = dataAccess.rankingLortu();
        assertEquals(1, result.size());
        
        assertEquals(result.get(0).getUsername(), singleElement.getUsername());
        assertEquals(result.get(0).getPassword(), singleElement.getPassword());
        assertEquals(result.get(0).getBankAccount(), singleElement.getBankAccount());
        assertEquals(result.get(0).isAdmin(), singleElement.isAdmin());
        
	}
	
	@Test
	void testRankingLortuVariosRegistros() {
		System.out.println("Comenzando el test 3");
		Registered user5 = new Registered("User5", "contraseña5", 523456789, false);
		Registered user2 = new Registered("User2", "contraseña2", 223456789, false);
		Registered user3 = new Registered("User3", "contraseña3", 323456789, false);
		Registered user4 = new Registered("User4", "contraseña4", 423456789, false);
		
		user5.setIrabazitakoa(30.00);
		user2.setIrabazitakoa(20.00);
		user3.setIrabazitakoa(50.00);
		user4.setIrabazitakoa(10.00);
		
		dataAccess.storeRegistered("User5", "contraseña5", 523456789);
		dataAccess.storeRegistered("User2", "contraseña2", 223456789);
		dataAccess.storeRegistered("User3", "contraseña3", 323456789);
		dataAccess.storeRegistered("User4", "contraseña4", 423456789);
		
		List<Registered> result = dataAccess.rankingLortu();
		
		for(Registered reg: result) {
			System.out.println(reg.getUsername() + "test3");
		}
		
		assertEquals(4, result.size());
		
	}
}
