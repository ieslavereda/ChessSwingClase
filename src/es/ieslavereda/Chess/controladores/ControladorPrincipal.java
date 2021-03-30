package es.ieslavereda.Chess.controladores;

import es.ieslavereda.Chess.vista.VistaPrincipal;

public class ControladorPrincipal {
	
	private VistaPrincipal vista;

	public ControladorPrincipal(VistaPrincipal vista) {
		super();
		this.vista = vista;
		
		inicializar();
	}

	private void inicializar() {
		// TODO Auto-generated method stub
		
	}
	
	public void go() {
		vista.setVisible(true);
	}
	
	

}
