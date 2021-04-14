package es.ieslavereda.Chess.model.common;

import java.util.HashSet;
import java.util.Set;

public class Bishop extends Pieza {

	public Bishop(Color color,Coordenada posicion, Tablero tablero) {
		super(posicion, tablero);
		
		if(color==Color.WHITE)
			tipo = Tipo.WHITE_BISHOP;
		else
			tipo = Tipo.BLACK_BISHOP;
		
		colocate(posicion);
	}

	@Override
	public Set<Coordenada> getNextMovements() {
		// TODO Auto-generated method stub
		return getNextMovements(this);
	}
	
	public static Set<Coordenada> getNextMovements(Pieza p){
		
		Tablero t = p.tablero;
		Set<Coordenada> lista = new HashSet<>();
		Coordenada c;
		
		// UP 

		// Right
		
		// Down
		
		// Left
		
		
		return lista;
	}
}
