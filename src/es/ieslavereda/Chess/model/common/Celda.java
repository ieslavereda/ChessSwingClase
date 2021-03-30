package es.ieslavereda.Chess.model.common;


import java.awt.Dimension;
import java.io.Serializable;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class Celda extends JButton implements Serializable {

	private Pieza pieza;

	private Dimension dimension = new Dimension(50, 50);

	private java.awt.Color colorCeldaNegra = new java.awt.Color(210, 129, 64);
	private java.awt.Color colorCeldaBlanca = new java.awt.Color(230, 205, 174);

	public Celda() {
		super();
		pieza = null;

		setPreferredSize(dimension);
	}

	public Pieza getPieza() {
		return pieza;
	}

	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	
		System.out.println(pieza);
		
		if (pieza != null) {
			setIcon(new ImageIcon(Celda.class.getResource("/es/ieslavereda/Chess/recursos/" + pieza.getFileName())));
		} else {
			setIcon(null);
		}
	}

	public boolean contienePieza() {
		return pieza != null;
	}

	public void setCeldaBackground(Color color) {
		if (color == Color.WHITE)
			setBackground(colorCeldaBlanca);
		else
			setBackground(colorCeldaNegra);
	}

	public void resaltar(java.awt.Color color, int size) { 
		setBorder(new LineBorder(color, size));
	}

	@Override
	public String toString() {
		if (pieza == null)
			return " ";
		else
			return pieza.toString();
	}
}
