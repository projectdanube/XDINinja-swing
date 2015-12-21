package com.danubetech.xdininja;

import javax.swing.WindowConstants;

public class XDINinja {

	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				XDINinjaMain frame = new XDINinjaMain();
				frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
