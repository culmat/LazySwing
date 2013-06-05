package components;

public class Entry {

	public final int row;

	public Entry(int row) {
		this.row = row;
	}

	@Override
	public String toString() {
		return "e" + row;
	}

}
