package components;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class EntryRenderer extends JLabel implements TableCellRenderer {
	private static final Color BRIGHTER = new Color(200, 217, 233);
	final Color defaultBG;
	final static Color selectedBG = Color.blue;
	final static Color focusBG = Color.lightGray;

	public EntryRenderer() {
		defaultBG = getBackground();
		setOpaque(true); // MUST do this for background to show up.
	}

	public Component getTableCellRendererComponent(JTable table, Object obj, boolean isSelected, boolean hasFocus, int row, int column) {
		Entry entry = (Entry) obj;
		setText("row: " + row + ", data: " + entry.row);
		if (hasFocus) {
			setBackground(focusBG);
			setText(getText() + " .");
		} else if (isSelected) {
			setBackground(selectedBG);
		} else {
			if (entry.row % 10 == 0) {
				setBackground(BRIGHTER);
			} else {
				setBackground(defaultBG);
			}
		}
		return this;
	}
}