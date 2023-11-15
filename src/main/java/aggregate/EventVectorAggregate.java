package aggregate;

import java.util.Vector;

import domain.Event;
import iterator.EventExtendedIterator;
import iterator.ExtendedIterator;

public class EventVectorAggregate implements Aggregate<Event>{
	
	protected Vector<Event> events;
	
	public EventVectorAggregate (Vector<Event> events) {
		this.events=events;
	}

	@Override
	public ExtendedIterator<Event> createIterator() {
		return new EventExtendedIterator(events);
	}

}
