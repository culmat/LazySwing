package lazy.swing;

import javax.swing.table.AbstractTableModel;

import lazy.AsyncLazyList;
import lazy.AsyncLazyListListener;

public class AsyncLazyListTableModel<T> extends AbstractTableModel implements AsyncLazyListListener<T> {

	private AsyncLazyList<T> list;

	public AsyncLazyListTableModel(AsyncLazyList<T> list) {
		setList(list);
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public T getValueAt(int rowIndex, int columnIndex) {
		return list.get(rowIndex);
	}

	@Override
	public void dataLoaded(final int index, T data) {
		fireTableCellUpdated(index, 0);
	}

	public void setList(AsyncLazyList<T> list) {
		this.list = list;
		list.addListener(this);
		fireTableDataChanged();
	}

}
