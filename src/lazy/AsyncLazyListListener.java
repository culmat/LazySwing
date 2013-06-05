package lazy;

import java.util.EventListener;

public interface AsyncLazyListListener<T> extends EventListener {
	void dataLoaded(int index, T data);
}
