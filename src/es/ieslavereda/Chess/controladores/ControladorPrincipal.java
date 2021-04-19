package es.ieslavereda.Chess.controladores;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import es.ieslavereda.Chess.config.MyConfig;
import es.ieslavereda.Chess.vista.JPTurno;
import es.ieslavereda.Chess.vista.Preferencias;
import es.ieslavereda.Chess.vista.VistaPrincipal;
import es.ieslavereda.Chess.model.common.*;

public class ControladorPrincipal implements ActionListener {

	private VistaPrincipal vista;
	private JPTurno jpTurno;
	private Pieza piezaSeleccionada;
	private Preferencias jfPreferencias;
	private GestionFichasEliminadas gestionFichasEliminadas;
	private DefaultListModel<Movimiento> dlm;

	public ControladorPrincipal(VistaPrincipal vista) {
		super();
		this.vista = vista;

		inicializar();
	}

	private void inicializar() {

		gestionFichasEliminadas = new ControladorFichasEliminadas(vista.getPanelEliminados());
				
		jpTurno = vista.getPanelTurno();
		
		dlm = new DefaultListModel<Movimiento>();
		vista.getPanelMovimientos().getList().setModel(dlm);

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

			Movimiento m = null;
			
			desmarcarPosiblesDestinos();
			
			if(c.contienePieza()) {
				if((tablero.getCoordenadaOfCelda(c).getRow()==1||tablero.getCoordenadaOfCelda(c).getRow()==8) && piezaSeleccionada instanceof Pawn) {
					m = new Movimiento(
							piezaSeleccionada.getPosicion(),
							tablero.getCoordenadaOfCelda(c),
							Movimiento.RISE_KILLING,
							c.getPieza(),
							null,
							piezaSeleccionada);		
					
				}else {
					m = new Movimiento(
							piezaSeleccionada.getPosicion(),
							tablero.getCoordenadaOfCelda(c),
							Movimiento.KILL,
							c.getPieza(),
							null,
							null);	
				}
				
				gestionFichasEliminadas.addPiece(c.getPieza());
			}
			
			if(m==null && (tablero.getCoordenadaOfCelda(c).getRow()==1||tablero.getCoordenadaOfCelda(c).getRow()==8) && piezaSeleccionada instanceof Pawn) {
				m = new Movimiento(
						piezaSeleccionada.getPosicion(),
						tablero.getCoordenadaOfCelda(c),
						Movimiento.RISE,
						null,
						null,
						piezaSeleccionada);
				
			}else if(m==null) {
				m = new Movimiento(
						piezaSeleccionada.getPosicion(),
						tablero.getCoordenadaOfCelda(c),
						Movimiento.NOT_KILL,
						null,
						null,
						null);
			}
			
			dlm.addElement(m);
			
			piezaSeleccionada.moveTo(tablero.getCoordenadaOfCelda(c));
			if(m.getTipoAccion()==Movimiento.RISE || m.getTipoAccion()==Movimiento.RISE_KILLING) {
				m.setFichaGenerada(c.getPieza());
			}
			
			piezaSeleccionada=null;
			vista.getPanelTurno().getJLabelPieza().setIcon(null);
			jpTurno.cambioTurno();
			
		}

		
	}

	private void movimientoSinPiezaSeleccionada(Celda c) {

		if (!c.contienePieza()) {
			JOptionPane.showMessageDialog(vista, "Debes seleccionar una pieza", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (c.getPieza().getColor() != jpTurno.getTurno()) {
			JOptionPane.showMessageDialog(vista, "Debes seleccionar una pieza de tu color", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (c.getPieza().getNextMovements().size() == 0) {
			JOptionPane.showMessageDialog(vista, "Esa pieza no la puedes mover", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			piezaSeleccionada = c.getPieza();
			vista.getPanelTurno().getJLabelPieza().setIcon(new ImageIcon(JPTurno.class.getResource("/es/ieslavereda/Chess/recursos/"+piezaSeleccionada.getFileName())));
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

			celda.resaltar(java.awt.Color.GRAY, 1);

		}

	}
	
	
}
