package lazy.impl;

import javax.swing.event.EventListenerList;

import lazy.AsyncLazyList;
import lazy.AsyncLazyListListener;

public abstract class AsyncLazyListSupport<T> implements AsyncLazyList<T>, AsyncLazyListListener<T> {
	EventListenerList listeners = new EventListenerList();

	@Override
	public void addListener(AsyncLazyListListener<T> listener) {
		listeners.add(AsyncLazyListListener.class, listener);
	}

	@Override
	public void removeListener(AsyncLazyListListener<T> listener) {
		listeners.remove(AsyncLazyListListener.class, listener);
	}

	protected void fireDataLoaded(int index, T data) {
		Object[] listeners = this.listeners.getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == AsyncLazyListListener.class) {
				((AsyncLazyListListener<T>) listeners[i + 1]).dataLoaded(index, data);
			}
		}
	}

}
