package lazy.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import lazy.AsyncLazyList;
import lazy.AsyncLazyListIterator;
import lazy.AsyncLazyListListener;

public abstract class AbstractAsyncLazyList<T> extends AsyncLazyListSupport<T> implements AsyncLazyList<T>, AsyncLazyListListener<T> {
	private final T def;
	private final SortedMap<Integer, T> loaded = Collections.synchronizedSortedMap(new TreeMap<Integer, T>());
	private final Set<Integer> runningBatches = Collections.synchronizedSet(new HashSet<Integer>());

	public AbstractAsyncLazyList(T defaultEntry) {
		def = defaultEntry;
	}

	@Override
	public Iterator<T> iterator() {
		return new AsyncLazyListIterator<>(this);
	}

	@Override
	public T get(int index) {
		T entry = loaded.get(index);
		if (entry == null) {
			loadAsnc(index);
			return def;
		}
		return entry;
	}

	private void loadAsnc(int index) {
		int batchIndex = getBatchIndex(index);
		if (!runningBatches.contains(batchIndex)) {
			runningBatches.add(batchIndex);
			new Thread(getBatch(batchIndex, this)).start();
		}
	}

	protected abstract Runnable getBatch(final int batchIndex, final AsyncLazyListListener<T> handler);

	protected abstract int getBatchIndex(int index);

	public void dataLoaded(int index, T data) {
		loaded.put(index, data);
		fireDataLoaded(index, data);
	}

}
