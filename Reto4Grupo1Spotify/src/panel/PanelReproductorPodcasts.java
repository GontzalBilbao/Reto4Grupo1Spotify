package panel;

import java.awt.Color;

import javax.swing.JPanel;

import controlador.GestionBD;
import controlador.GestionInformacion;
import vista.VentanaPrincipal;

public class PanelReproductorPodcasts extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelReproductorPodcasts(VentanaPrincipal vp, GestionBD gestionBD, GestionInformacion gestionInfo) {
		setSize(800, 600);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
	}

}
