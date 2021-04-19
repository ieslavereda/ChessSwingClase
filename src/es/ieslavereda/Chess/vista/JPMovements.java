package es.ieslavereda.Chess.vista;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.AbstractListModel;

public class JPMovements extends JPanel {
	private JList list;
	private JButton btnPrev;
	private JButton btnNext;

	/**
	 * Create the panel.
	 */
	public JPMovements() {
		setBorder(new TitledBorder(null, "MOVEMENTS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new MigLayout("", "[grow]", "[grow][]"));
		
		list = new JList();
		add(list, "cell 0 0,grow");
		
		JPanel panelBotones = new JPanel();
		add(panelBotones, "cell 0 1,grow");
		
		btnPrev = new JButton("<");
		
		btnNext = new JButton(">");
		GroupLayout gl_panelBotones = new GroupLayout(panelBotones);
		gl_panelBotones.setHorizontalGroup(
			gl_panelBotones.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBotones.createSequentialGroup()
					.addComponent(btnPrev)
					.addPreferredGap(ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
					.addComponent(btnNext))
		);
		gl_panelBotones.setVerticalGroup(
			gl_panelBotones.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBotones.createSequentialGroup()
					.addGroup(gl_panelBotones.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPrev)
						.addComponent(btnNext))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelBotones.setLayout(gl_panelBotones);

	}

	public JList getList() {
		return list;
	}

	public JButton getBtnPrev() {
		return btnPrev;
	}

	public JButton getBtnNext() {
		return btnNext;
	}
	
}
