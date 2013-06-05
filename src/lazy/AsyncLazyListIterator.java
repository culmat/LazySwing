package lazy;

import java.util.Iterator;

public class AsyncLazyListIterator<T> implements Iterator<T> {
	final AsyncLazyList<T> list;
	protected int index;

	public AsyncLazyListIterator(AsyncLazyList<T> list) {
		this.list = list;
	}

	@Override
	public boolean hasNext() {
		return index >= list.size();
	}

	@Override
	public T next() {
		return list.get(index++);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
