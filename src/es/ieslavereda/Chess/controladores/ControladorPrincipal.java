package es.ieslavereda.Chess.controladores;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import es.ieslavereda.Chess.model.common.Celda;
import es.ieslavereda.Chess.model.common.Color;
import es.ieslavereda.Chess.model.common.Pieza;
import es.ieslavereda.Chess.vista.VistaPrincipal;

public class ControladorPrincipal implements ActionListener{

	private VistaPrincipal vista;
	private Color turno;
	private Pieza piezaSeleccionada;

	public ControladorPrincipal(VistaPrincipal vista) {
		super();
		this.vista = vista;

		inicializar();
	}

	private void inicializar() {
		
		turno = Color.WHITE;

		Component[] components = vista.getPanelTablero().getComponents();
		
		for(Component component : components) {
			if(component instanceof Celda) {
				((Celda) component).addActionListener(this);
			}
		}

	}

	public void go() {
		vista.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String comando = arg0.getActionCommand();
		if(comando.equals("sdf")) {
			
		}else if(comando.equals("fasdf")) {
			
		}else if(arg0.getSource() instanceof Celda){
			comprobarMovimiento((Celda)arg0.getSource());
		}
		
		
	}

	private void comprobarMovimiento(Celda c) {
		
		if(piezaSeleccionada==null) {
			movimientoSinPiezaSeleccionada(c);
		} else {
			movimientoConPiezaSeleccionada(c);
		}
		
	}

	private void movimientoConPiezaSeleccionada(Celda c) {
		
		
	}

	private void movimientoSinPiezaSeleccionada(Celda c) {
		
		if(!c.contienePieza()) {
			JOptionPane.showMessageDialog(vista, "Debes seleccionar una pieza", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(c.getPieza().getColor()!=turno) {
			JOptionPane.showMessageDialog(vista, "Debes seleccionar una pieza de tu color", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(c.getPieza().getNextMovements().getSize()==0) {
			JOptionPane.showMessageDialog(vista, "Esa pieza no la puedes mover", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		vista.getPanelTablero();
		
	}

}








