package es.ieslavereda.Chess.model.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Rectangle;
import java.awt.GridLayout;

public class Tablero extends JPanel {

	private HashMap<Coordenada, Celda> tablero;
	private Lista<Pieza> blancas;
	private Lista<Pieza> blancasEliminadas;
	private Lista<Pieza> negras;
	private Lista<Pieza> negrasEliminadas;
	private Pieza blackKing;
	private Pieza whiteKing;

	public Tablero() {
		super();
		setBounds(new Rectangle(0, 0, 500, 500));
		setLayout(new GridLayout(10, 10, 0, 0));

		tablero = new HashMap<Coordenada, Celda>();
		blancas = new Lista<>();
		blancasEliminadas = new Lista<>();
		negras = new Lista<>();
		negrasEliminadas = new Lista<>();

		inicializar();
	}

	private void inicializar() {

		// Inicializamos el tablero
		for (int fila = 0; fila < 8; fila++) {
			for (int col = 0; col < 8; col++)
				tablero.put(new Coordenada((char) ('A' + col), 1 + fila), new Celda());
		}

		whiteKing = new King(Color.WHITE, new Coordenada('E', 1), this);
		blancas.addHead(whiteKing);

		blancas.addHead(new Rook(Color.WHITE, new Coordenada('A', 1), this));
		blancas.addHead(new Knight(Color.WHITE, new Coordenada('B', 1), this));
		blancas.addHead(new Bishop(Color.WHITE, new Coordenada('C', 1), this));
		blancas.addHead(new Queen(Color.WHITE, new Coordenada('D', 1), this));
		blancas.addHead(new Bishop(Color.WHITE, new Coordenada('F', 1), this));
		blancas.addHead(new Knight(Color.WHITE, new Coordenada('G', 1), this));
		blancas.addHead(new Rook(Color.WHITE, new Coordenada('H', 1), this));

		blackKing = new King(Color.BLACK, new Coordenada('E', 8), this);
		negras.addHead(blackKing);
		negras.addHead(new Rook(Color.BLACK, new Coordenada('A', 8), this));
		negras.addHead(new Knight(Color.BLACK, new Coordenada('B', 8), this));
		negras.addHead(new Bishop(Color.BLACK, new Coordenada('C', 8), this));
		negras.addHead(new Queen(Color.BLACK, new Coordenada('D', 8), this));
		negras.addHead(new King(Color.BLACK, new Coordenada('E', 8), this));
		negras.addHead(new Bishop(Color.BLACK, new Coordenada('F', 8), this));
		negras.addHead(new Knight(Color.BLACK, new Coordenada('G', 8), this));
		negras.addHead(new Rook(Color.BLACK, new Coordenada('H', 8), this));

		for (int i = 0; i < 8; i++) {
			blancas.addHead(new Pawn(Color.WHITE, new Coordenada((char) ('A' + i), 2), this));
			negras.addHead(new Pawn(Color.BLACK, new Coordenada((char) ('A' + i), 7), this));
		}

		addToPanel();

	}

	private void addToPanel() {

		// Añadir parte superior
		add(getNewLabel(""));
		for (int i = 0; i < 8; i++)
			add(getNewLabel(String.valueOf((char) ('A' + i))));
		add(getNewLabel(""));

		for (int fil = 8; fil >= 1; fil--) {
			add(getNewLabel(String.valueOf(fil)));
			for (int col = 0; col < 8; col++) {

				Coordenada c = new Coordenada((char) ('A' + col), fil);

				Celda celda = tablero.get(c);
				if ((fil + col) % 2 == 0)
					celda.setAsWhiteCell();
				else
					celda.setAsBlackCell();

				add(celda);
			}
			add(getNewLabel(String.valueOf(fil)));
		}

		// Añadir parte inferior
		add(getNewLabel(""));
		for (int i = 0; i < 8; i++)
			add(getNewLabel(String.valueOf((char) ('A' + i))));
		add(getNewLabel(""));

	}

	public boolean contiene(Coordenada c) {
		return !(c.getRow() > 8 || c.getRow() < 1 || c.getColumn() < 'A' || c.getColumn() > 'H');
	}

	public Pieza getPiezaAt(Coordenada c) {
		if (!contiene(c))
			return null;
		else
			return getCeldaAt(c).getPieza();
	}

	public Lista<Pieza> getBlancas() {
		return blancas;
	}

	public void eliminarPieza(Pieza p) {

		if (p.getColor() == Color.WHITE) {
			blancasEliminadas.addHead(blancas.getAndRemove(p));
		} else
			negrasEliminadas.addHead(negras.getAndRemove(p));

	}

	public Celda getCeldaAt(Coordenada c) {
		return tablero.get(c);
	}

	public boolean blackKingIsAlive() {
		return negras.contains(blackKing);
	}

	public boolean whiteKingIsAlive() {
		return blancas.contains(whiteKing);
	}

	private JLabel getNewLabel(String text) {
		JLabel label = new JLabel(text);
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBackground(java.awt.Color.DARK_GRAY);
		label.setForeground(java.awt.Color.WHITE);
		return label;
	}

	public Coordenada getCoordenadaOfCelda(Celda c) {

		Set<Coordenada> coordenadas = tablero.keySet();

		Iterator<Coordenada> it = coordenadas.iterator();
		boolean encontrado = false;
		Coordenada coordenada=null;
		
		while (it.hasNext() && !encontrado) {
			coordenada = it.next();
			if(tablero.get(coordenada).equals(c))
				encontrado=true;
		}
		
		if(encontrado)
			return coordenada;
		else
			return null;

	}

}
