package es.ieslavereda.Chess.controladores;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import es.ieslavereda.Chess.config.MyConfig;
import es.ieslavereda.Chess.model.common.Celda;
import es.ieslavereda.Chess.model.common.Color;
import es.ieslavereda.Chess.model.common.Pieza;
import es.ieslavereda.Chess.vista.Preferencias;
import es.ieslavereda.Chess.vista.VistaPrincipal;
import es.ieslavereda.Chess.model.common.*;

public class ControladorPrincipal implements ActionListener {

	private VistaPrincipal vista;
	private Color turno;
	private Pieza piezaSeleccionada;
	private Preferencias jfPreferencias;
	private GestionFichasEliminadas gestionFichasEliminadas;
	

	public ControladorPrincipal(VistaPrincipal vista) {
		super();
		this.vista = vista;

		inicializar();
	}

	private void inicializar() {

		gestionFichasEliminadas = new ControladorFichasEliminadas(vista.getPanelEliminados());
				
		turno = Color.WHITE;

		Component[] components = vista.getPanelTablero().getComponents();

		for (Component component : components) {
			if (component instanceof Celda) {
				((Celda) component).addActionListener(this);
			}
		}

		// Añadimos los ActionListener
		vista.getMntmPreferences().addActionListener(this);

		// Añadimos los ActionCommand
		vista.getMntmPreferences().setActionCommand("Abrir preferencias");

	}

	public void go() {
		vista.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		String comando = arg0.getActionCommand();
		if (comando.equals("Abrir preferencias")) {
			abrirPreferencias();
		} else if (comando.equals("Cambiar Color Celda Blanca")) {
			cambiarColorCeldaBlanca();
		} else if (arg0.getSource() instanceof Celda) {
			comprobarMovimiento((Celda) arg0.getSource());
		}

	}

	private void cambiarColorCeldaBlanca() {

		java.awt.Color color = JColorChooser.showDialog(jfPreferencias.getBtnColorCeldaBlanca(),
				"Selecciona color de las celdas blancas", jfPreferencias.getBtnColorCeldaBlanca().getBackground());

		if (color != null) {
			jfPreferencias.getBtnColorCeldaBlanca().setBackground(color);
			MyConfig.getInstancia().setWhiteCellColor(color);
			Celda.colorCeldaBlanca = color;
			((Tablero) vista.getPanelTablero()).repaintBoard();
		}

	}

	private void abrirPreferencias() {

		jfPreferencias = new Preferencias();

		jfPreferencias.setVisible(true);

		// Añadimos ActionListener
		jfPreferencias.getBtnColorCeldaBlanca().addActionListener(this);
		jfPreferencias.getBtnColorCeldaNegra().addActionListener(this);
		jfPreferencias.getBtnColorBordeCelda().addActionListener(this);
		jfPreferencias.getBtnColorBordeCeldaComer().addActionListener(this);

		// Añadimos ActionCommand
		jfPreferencias.getBtnColorCeldaBlanca().setActionCommand("Cambiar Color Celda Blanca");
		jfPreferencias.getBtnColorCeldaNegra().setActionCommand("Cambiar Color Celda Negra");
		jfPreferencias.getBtnColorBordeCelda().setActionCommand("Cambiar Color Borde Celda");
		jfPreferencias.getBtnColorBordeCeldaComer().setActionCommand("Cambiar Color Borde Celda Comer");

	}

	private void comprobarMovimiento(Celda c) {

		if (piezaSeleccionada == null) {
			movimientoSinPiezaSeleccionada(c);
		} else {
			movimientoConPiezaSeleccionada(c);
		}

	}

	private void movimientoConPiezaSeleccionada(Celda c) {

		Tablero tablero = (Tablero) vista.getPanelTablero();

		if (!piezaSeleccionada.getNextMovements().contains(tablero.getCoordenadaOfCelda(c))) {
			JOptionPane.showMessageDialog(vista, "No puedes mover la pieza a esa posicion", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {

			desmarcarPosiblesDestinos();
			
			if(c.contienePieza())
				gestionFichasEliminadas.addPiece(c.getPieza());
			
			piezaSeleccionada.moveTo(tablero.getCoordenadaOfCelda(c));
		}

		
	}

	private void movimientoSinPiezaSeleccionada(Celda c) {

		if (!c.contienePieza()) {
			JOptionPane.showMessageDialog(vista, "Debes seleccionar una pieza", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (c.getPieza().getColor() != turno) {
			JOptionPane.showMessageDialog(vista, "Debes seleccionar una pieza de tu color", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (c.getPieza().getNextMovements().size() == 0) {
			JOptionPane.showMessageDialog(vista, "Esa pieza no la puedes mover", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			piezaSeleccionada = c.getPieza();
			marcarPosiblesDestinos();

		}

	}

	private void marcarPosiblesDestinos() {
		Set<Coordenada> posiblesMovimientos = piezaSeleccionada.getNextMovements();
		Tablero tablero = (Tablero) vista.getPanelTablero();

		for (Coordenada cord : posiblesMovimientos) {
			Celda celda = tablero.getCeldaAt(cord);
			if (celda.contienePieza())
				celda.resaltar(Celda.colorBordeCeldaComer, 2);
			else
				celda.resaltar(Celda.colorBordeCelda, 2);

		}
	}

	private void desmarcarPosiblesDestinos() {
		Set<Coordenada> posiblesMovimientos = piezaSeleccionada.getNextMovements();
		Tablero tablero = (Tablero) vista.getPanelTablero();

		for (Coordenada cord : posiblesMovimientos) {
			Celda celda = tablero.getCeldaAt(cord);

			celda.resaltar(null, 1);

		}

	}
}
