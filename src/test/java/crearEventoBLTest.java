import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import exceptions.EventFinished;

import java.util.Date;

public class crearEventoBLTest {
    @Mock
    private DataAccess dbManager;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGertaerakSortu_FutureEventDate() throws EventFinished {
        // Simular una fecha de evento en el futuro
        Date futureEventDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000); // Un día en el futuro
        String description = "Barcelona-Real Madrid";
        String sport = "Futbol";

        // Configurar el comportamiento del mock para que dbManager.gertaerakSortu devuelva true
        Mockito.doReturn(true).when(dbManager).gertaerakSortu(description, futureEventDate, sport);

        // Llamar al método que se va a probar
        BLFacadeImplementation facade = new BLFacadeImplementation(dbManager);
        boolean result = facade.gertaerakSortu(description, futureEventDate, sport);

        // Verificar que el evento se crea con éxito
        assertTrue(result);
    }

    @Test(expected = EventFinished.class)
    public void testGertaerakSortu_PastEventDate() throws EventFinished {
        // Simular una fecha de evento en el pasado
        Date pastEventDate = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000); // Un día en el pasado
        String description = "Barcelona-Real Madrid";
        String sport = "Futbol";

        // Configurar el comportamiento del mock para que dbManager.gertaerakSortu devuelva true
        Mockito.doReturn(true).when(dbManager).gertaerakSortu(description, pastEventDate, sport);

        // Llamar al método que se va a probar, debe lanzar una excepción EventFinished
        BLFacadeImplementation facade = new BLFacadeImplementation(dbManager);
        facade.gertaerakSortu(description, pastEventDate, sport);
    }
}
