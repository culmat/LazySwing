package components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class DelayedChangeListener implements DocumentListener {
	public static interface DelayedChangeHandler {
		void setText(String text);
	}

	final Timer timer;
	DocumentEvent event;
	String lastText;

	public DelayedChangeListener(int delay, final DelayedChangeHandler listener) {
		timer = new Timer(delay, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = getText(event);
				if (lastText == null || !lastText.equals(text)) {
					lastText = text;
					listener.setText(text);
				}
			}
		});
		timer.setRepeats(false);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		handle(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		handle(e);

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		handle(e);
	}

	private void handle(DocumentEvent e) {
		event = e;
		timer.restart();
	}

	private final static String getText(final DocumentEvent e) {
		final Document document = e.getDocument();
		try {
			return document.getText(0, document.getLength());
		} catch (final BadLocationException e1) {
			e1.printStackTrace();
			return e1.getMessage();
		}
	}
}
