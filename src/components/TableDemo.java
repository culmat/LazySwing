package components;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import lazy.AsyncLazyList;
import lazy.swing.AsyncLazyListTableModel;

import components.DelayedChangeListener.DelayedChangeHandler;

public class TableDemo extends JPanel {

	public TableDemo() {
		super(new BorderLayout());
		AsyncLazyList<Entry> list = new DummyList(13);
		final AsyncLazyListTableModel<Entry> model = new AsyncLazyListTableModel<>(list);
		JTable table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);
		table.setTableHeader(null);
		table.setDefaultRenderer(Object.class, new EntryRenderer());
		// Add the scroll pane to this panel.
		add(scrollPane, BorderLayout.CENTER);
		JTextField query = new JTextField();
		add(query, BorderLayout.NORTH);
		query.getDocument().addDocumentListener(new DelayedChangeListener(350, new DelayedChangeHandler() {
			@Override
			public void setText(String text) {
				System.out.println(text);
				try {
					int i = Integer.parseInt(text);
					model.setList(new DummyList(i));
					repaint();
				} catch (Exception e) {

				}
			}
		}));
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("TableDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		TableDemo newContentPane = new TableDemo();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
