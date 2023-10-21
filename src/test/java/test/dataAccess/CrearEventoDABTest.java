package test.dataAccess;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import dataAccess.DataAccessCrearEvento;
import domain.Event;
import domain.Sport;
import domain.Team;
public class CrearEventoDABTest { 
	@Mock
    protected  EntityManager  db;
	
	@Mock
    protected  EntityTransaction  et;

    @InjectMocks
    protected DataAccessCrearEvento sut;
    //protected DataAccessEliminarApuesta sut=new DataAccessEliminarApuesta(db);

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
		Mockito.doReturn(et).when(db).getTransaction();
	     sut=new DataAccessCrearEvento(db);

    }

    @Test
    public void testGertaerakSortu_EventCreationSuccess() {
        // Simular una descripción, fecha de evento y deporte
        String description = "Barcelona-Real Madrid";
        Date eventDate = new Date();
        String sport = "";
        // Configurar el comportamiento del mock para que el deporte exista
        Sport spo = new Sport(sport); 
        //Mockito.when(db.find(Sport.class, sport)).thenReturn(spo);
		Mockito.doReturn(spo).when(db).find(Sport.class,"");
        // Configurar una consulta que no devuelve ningún evento existente para la fecha dada
        TypedQuery<Event> Equery = Mockito.mock(TypedQuery.class);
        Mockito.when(db.createQuery("SELECT e FROM Event e WHERE e.getEventDate() =?1 ", Event.class))
                .thenReturn(Equery);
        Mockito.when(Equery.setParameter(1, eventDate)).thenReturn(Equery);
        Mockito.when(Equery.getResultList()).thenReturn(List.of());
        // Llamar al método que se va a probar
        boolean result = sut.gertaerakSortu(description, eventDate, sport);
        System.out.println(result); // Agrega esta línea para verificar la configuración del mock
        // Verificar que se crea el evento correctamente
        assertTrue(result);
        Mockito.verify(db).persist(Mockito.any(Event.class));
    }
    @Test
    public void testGertaerakSortu_SportNotFound() {
        // Simular una descripción, fecha de evento y deporte
        String description = "Barcelona-Real Madrid";
        Date eventDate = new Date();
        String sport = "Football";
        // Configurar el comportamiento del mock para que el deporte no exista
        Mockito.when(db.find(Sport.class, sport)).thenReturn(null);
        // Llamar al método que se va a probar
        boolean result = sut.gertaerakSortu(description, eventDate, sport);
        // Verificar que el método retorna false cuando el deporte no se encuentra
        assertFalse(result);
    }
    @Test
    public void testGertaerakSortu_EventAlreadyExists() {
        // Simular una descripción, fecha de evento y deporte
        String description = "Barcelona-Real Madrid";
        Date eventDate = new Date();
        String sport = "Football";
        Team team1 = new Team("Real_Madrid");
        Team team2 = new Team("Barcelona");
        // Configurar el comportamiento del mock para que el deporte exista
        Sport spo = new Sport(sport);
        Mockito.when(db.find(Sport.class, sport)).thenReturn(spo);
        // Configurar una consulta que devuelve un evento con la misma descripción y fecha
        TypedQuery<Event> Equery = Mockito.mock(TypedQuery.class);
        Mockito.when(db.createQuery("SELECT e FROM Event e WHERE e.getEventDate() =?1 ", Event.class))
                .thenReturn(Equery);
        Mockito.when(Equery.setParameter(1, eventDate)).thenReturn(Equery);
        Mockito.when(Equery.getResultList()).thenReturn(List.of(new Event(description, eventDate,team1,team2)));
        // Llamar al método que se va a probar
        boolean result = sut.gertaerakSortu(description, eventDate, sport);
        // Verificar que el método retorna false cuando el evento ya existe
        assertFalse(result);
    }
    @Test
    public void testGertaerakSortu_EventCreationWithoutEqualDescription() {
        // Simular una descripción, fecha de evento y deporte
        String description = "Barcelona-Real Madrid";
        Date eventDate = new Date();
        String sport = "Futbol";
        // Configurar el comportamiento del mock para que el deporte exista
        Sport spo = new Sport(sport);
        Mockito.doReturn(spo).when(db).find(Sport.class, sport);
        // Configurar una consulta que no devuelve ningún evento existente para la fecha dada
        TypedQuery<Event> Equery = Mockito.mock(TypedQuery.class);
        Mockito.when(db.createQuery("SELECT e FROM Event e WHERE e.getEventDate() =?1 ", Event.class))
                .thenReturn(Equery);
        Mockito.when(Equery.setParameter(1, eventDate)).thenReturn(Equery);
        // Simular que existe otro evento pero no la misma descripción
        Event existingEvent = new Event("Real Zaragoza - Huesca", eventDate, new Team("Real Zaragoza"), new Team("Huesca"));
        Mockito.when(Equery.getResultList()).thenReturn(List.of(existingEvent));
        // Llamar al método que se va a probar
        boolean result = sut.gertaerakSortu(description, eventDate, sport);
        // Verificar que se crea el evento correctamente si no existe una descripción igual
        assertTrue(result);
        // Verificar que se ejecuta el bloque de código cuando la descripción no existe
        Mockito.verify(db).persist(Mockito.any(Event.class));
    }
}