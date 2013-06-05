package components;

import lazy.AsyncLazyListListener;
import lazy.impl.AbstractAsyncLazyList;

public class DummyList extends AbstractAsyncLazyList<Entry> {

	private int offset;

	public DummyList(int offset) {
		super(new Entry(-1));
		this.offset = offset;
	}

	@Override
	public int size() {
		return 2000;
	}

	@Override
	protected Runnable getBatch(final int batchIndex, final AsyncLazyListListener<Entry> handler) {
		return new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(350);
					for (int i = batchIndex * 10; i < batchIndex * 10 + 10; i++) {
						Thread.sleep(150);
						handler.dataLoaded(i, new Entry(i + offset));
					}
				} catch (InterruptedException e) {
				}
			}
		};
	}

	@Override
	protected int getBatchIndex(int index) {
		return index / 10;
	}

}
