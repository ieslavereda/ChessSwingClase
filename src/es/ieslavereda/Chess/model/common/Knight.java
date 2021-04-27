package es.ieslavereda.Chess.model.common;

import java.util.LinkedHashSet;
import java.util.Set;

import es.ieslavereda.Chess.vista.JPTablero;

public class Knight extends Pieza{

	private Set<Coordenada> posicionesCandidatas;
	public Knight(Color color, Coordenada posicion, JPTablero tablero) {
		super(posicion, tablero);
		
		if(color==Color.WHITE)
			tipo = Tipo.WHITE_KNIGHT;
		else
			tipo = Tipo.BLACK_KNIGHT;
		
		colocate(posicion);
		
	}

	@Override
	public Set<Coordenada> getNextMovements() {
		Set<Coordenada> posicionesCandidatas = new LinkedHashSet<Coordenada>();

		// Comprobamos que la ficha este en el tablero
		if (posicion == null)
			return posicionesCandidatas;

		// up
		addCoordenada(posicion.up().up().left());
		addCoordenada(posicion.up().up().right());

		// down
		addCoordenada(posicion.down().down().left());
		addCoordenada(posicion.down().down().right());

		// right
		addCoordenada(posicion.right().right().up());
		addCoordenada(posicion.right().right().down());

		// left
		addCoordenada(posicion.left().left().up());
		addCoordenada(posicion.left().left().down());

		return posicionesCandidatas;
	}
	protected void addCoordenada(Coordenada c) {
		if (tablero.contiene(c) && (tablero.getCeldaAt(c).getPieza() == null || tablero.getCeldaAt(c).getPieza().getColor() != getColor()))
			posicionesCandidatas.add(c);
	}

}
