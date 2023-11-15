package aggregate;

import iterator.ExtendedIterator;

public interface Aggregate<T> {
	public ExtendedIterator<T> createIterator();
}
