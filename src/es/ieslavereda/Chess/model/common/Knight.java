package es.ieslavereda.Chess.model.common;

import java.util.Set;

public class Knight extends Pieza{

	public Knight(Color color, Coordenada posicion, Tablero tablero) {
		super(posicion, tablero);
		
		if(color==Color.WHITE)
			tipo = Tipo.WHITE_KNIGHT;
		else
			tipo = Tipo.BLACK_KNIGHT;
		
		colocate(posicion);
		
	}

	@Override
	public Set<Coordenada> getNextMovements() {
		// TODO Auto-generated method stub
		return null;
	}

}
