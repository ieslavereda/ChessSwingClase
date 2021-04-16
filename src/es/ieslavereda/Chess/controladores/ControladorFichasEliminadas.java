package es.ieslavereda.Chess.controladores;

import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import es.ieslavereda.Chess.model.common.Color;
import es.ieslavereda.Chess.model.common.GestionFichasEliminadas;
import es.ieslavereda.Chess.model.common.Pieza;
import es.ieslavereda.Chess.vista.JPFichasEliminadas;
import es.ieslavereda.Chess.vista.VistaPrincipal;

public class ControladorFichasEliminadas implements GestionFichasEliminadas {

	private JPFichasEliminadas vista;
	private HashMap<Pieza,JLabel> fichasEliminadas;
	
	public ControladorFichasEliminadas(JPFichasEliminadas panel) {
		vista=panel;
		fichasEliminadas = new HashMap<Pieza,JLabel>();
	}
	
	@Override
	public void addPiece(Pieza ficha) {
		
		if(ficha.getColor()==Color.WHITE) {
			
			add(ficha,vista.getPanelBlancas());
			
		}else {
			add(ficha,vista.getPanelNegras());
		}
		
	}

	@Override
	public void removePiece(Pieza ficha) {
		// TODO Auto-generated method stub
		
	}
	
	private void add(Pieza ficha, JPanel panel) {

		JLabel label = new JLabel();
		label.setOpaque(true);
		label.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/es/ieslavereda/Chess/recursos/" + ficha.getFileName())));

		panel.add(label);

		fichasEliminadas.put(ficha, label);
	}


}
