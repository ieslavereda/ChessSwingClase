package es.ieslavereda.Chess.model.common;

import java.util.HashSet;
import java.util.Set;

public class Rook extends Pieza {

	public Rook(Color color, Coordenada posicion, Tablero tablero) {
		super(posicion, tablero);
		// TODO Auto-generated constructor stub
		
		if(color==Color.WHITE)
			tipo = Tipo.WHITE_ROOK;
		else
			tipo = Tipo.BLACK_ROOK;
		
		colocate(posicion);
	}

	@Override
	public Set<Coordenada> getNextMovements() {
		
		return getNextMovements(this);
	}
	
	public static Set<Coordenada> getNextMovements(Pieza p){
		
		Tablero t = p.tablero;
		Set<Coordenada> lista = new HashSet<>();
		Coordenada c;
		
		// UP 
		c= p.posicion.up();
		while(t.contiene(c) && t.getPiezaAt(c)==null) {
			lista.add(c);
			c=c.up();
		}
		if(t.contiene(c) && t.getPiezaAt(c).getColor() == p.getColor()) 
			lista.add(c);
		
		// Right
		
		// Down
		
		// Left
		
		
		return lista;
	}

}
