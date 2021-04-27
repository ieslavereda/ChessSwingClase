package es.ieslavereda.Chess;

import java.awt.EventQueue;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.alee.laf.WebLookAndFeel;
import com.alee.managers.style.StyleManager;

import es.ieslavereda.Chess.config.MyConfig;
import es.ieslavereda.Chess.controladores.ControladorPrincipal;
import es.ieslavereda.Chess.vista.MySkin;
import es.ieslavereda.Chess.vista.VistaPrincipal;

public class App {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					// Install WebLaF as application LaF
	                WebLookAndFeel.install();
	                
	                StyleManager.setSkin(MySkin.class);					
					
					MyConfig.getInstancia();
					
					// Creamos la vista
					VistaPrincipal frame = new VistaPrincipal();
					
					// Creamos el modelo
					//Modelo m = new Modelo();
					
					// Creamos el controlador pasando vista y modelo
					ControladorPrincipal c = new ControladorPrincipal(frame);
						
					
					// Arrancamos la aplicacion
					c.go();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
