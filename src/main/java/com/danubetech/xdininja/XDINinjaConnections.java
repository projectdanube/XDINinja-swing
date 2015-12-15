import java.awt.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Mon Dec 14 19:30:38 CET 2015
 */



/**
 * @author zezrtaz bjhbkjhbk
 */
public class XDINinjaConnections extends JFrame {
	public XDINinjaConnections() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - zezrtaz bjhbkjhbk
		panel1 = new JPanel();
		scrollPane2 = new JScrollPane();
		textArea1 = new JTextArea();
		scrollPane1 = new JScrollPane();
		textPane1 = new JTextPane();
		panel2 = new JPanel();
		button1 = new JButton();

		//======== this ========
		setTitle("XDI Ninja!");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== panel1 ========
		{

			// JFormDesigner evaluation mark
			panel1.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			panel1.setLayout(new GridLayout());

			//======== scrollPane2 ========
			{
				scrollPane2.setViewportView(textArea1);
			}
			panel1.add(scrollPane2);

			//======== scrollPane1 ========
			{
				scrollPane1.setViewportView(textPane1);
			}
			panel1.add(scrollPane1);
		}
		contentPane.add(panel1, BorderLayout.CENTER);

		//======== panel2 ========
		{
			panel2.setLayout(new FlowLayout());

			//---- button1 ----
			button1.setText("text");
			panel2.add(button1);
		}
		contentPane.add(panel2, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - zezrtaz bjhbkjhbk
	private JPanel panel1;
	private JScrollPane scrollPane2;
	private JTextArea textArea1;
	private JScrollPane scrollPane1;
	private JTextPane textPane1;
	private JPanel panel2;
	private JButton button1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
