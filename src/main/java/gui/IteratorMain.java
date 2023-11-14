package gui;

import java.text.SimpleDateFormat;
import java.util.Date;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Event;
import iterator.ExtendedIterator;

public class IteratorMain {

	public static void main(String[] args) {
		int isLocal = 1;
		BLFacade blFacade = new BLFacadeImplementation();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = sdf.parse("17/12/2023"); // 17 del mes que viene
			java.sql.Date sqlDate = java.sql.Date.valueOf(sdf.toString());
			ExtendedIterator<Event> i = blFacade.getEventsIterator(sqlDate);
			Event e;
			System.out.println("_____________________");
			System.out.println("RECORRIDO	HACIA	ATRAS");
			i.goLast(); // Hacia atrás
			while (i.hasPrevious()) {
				e = i.previous();
				System.out.println(e.toString());
			}
			System.out.println();
			System.out.println("_____________________");
			System.out.println("RECORRIDO	HACIA	ADELANTE");
			i.goFirst(); // Hacia adelante
			while (i.hasNext()) {
				e = i.next();
				System.out.println(e.toString());
			}
		} catch (Exception e) {
			System.out.println("Problems	with	date??	" + "17/12/2020");
		}

	}

}
