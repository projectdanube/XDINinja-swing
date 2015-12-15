package com.danubetech.xdininja;

import javax.swing.JOptionPane;

public class Util {

	public static void error(Exception ex) {

		JOptionPane.showMessageDialog(null, "" + ex.getClass().getSimpleName() + ": " + ex.getMessage());
	}
}
