package lazy;

public interface AsyncLazyList<T> extends Iterable<T> {
	T get(int index);

	int size();

	void addListener(AsyncLazyListListener<T> listener);

	void removeListener(AsyncLazyListListener<T> listener);
}
