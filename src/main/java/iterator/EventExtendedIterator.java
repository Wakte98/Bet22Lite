package iterator;

import java.util.Vector;

import domain.Event;

public class EventExtendedIterator implements ExtendedIterator<Event>{
	
	protected Vector<Event> events;
	protected int position;
	protected int lastIndex;
	
	public EventExtendedIterator(Vector<Event> events) {
		this.events=events;
		this.lastIndex=events.size()-1;
		goFirst();
	}

	@Override
	public boolean hasNext() {
		return position<lastIndex-1;
	}

	@Override
	public Event next() {
		this.position+=1;
		return events.elementAt(position);
	}

	@Override
	public Event previous() {
		this.position-=1;
		return events.elementAt(position);
	}

	@Override
	public boolean hasPrevious() {
		return position>0;
	}

	@Override
	public void goFirst() {
		this.position=-1;
		
	}

	@Override
	public void goLast() {
		this.position=lastIndex+1;	
	}

}
