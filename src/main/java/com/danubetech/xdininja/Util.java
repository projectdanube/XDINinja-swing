package com.danubetech.xdininja;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class Util {

	public static void initJFrame(JFrame jframe) {

		((JComponent) jframe.getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

		removeEval(jframe.getContentPane());
	}

	private static void removeEval(Container container) {

		for (Component component : container.getComponents()) {

			if (component instanceof Container) removeEval((Container) component);

			if (component instanceof JComponent) {

				if (((JComponent) component).getBorder() instanceof CompoundBorder && ((CompoundBorder) ((JComponent) component).getBorder()).getOutsideBorder() instanceof TitledBorder) {

					component.removePropertyChangeListener(component.getPropertyChangeListeners()[0]);
					((JComponent) component).setBorder(null);
				}
			}
		}
	}

	public static void info(String info) {

		JOptionPane.showMessageDialog(null, info);
	}

	public static void error(Exception ex) {

		ex.printStackTrace(System.err);
		JOptionPane.showMessageDialog(null, "" + ex.getClass().getSimpleName() + ": " + ex.getMessage());
	}
}
