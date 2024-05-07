package panel;

import java.awt.Color;

import javax.swing.JPanel;

import controlador.GestionBD;
import controlador.GestionInformacion;
import vista.VentanaPrincipal;

public class PanelPodcasterPodcasts extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelPodcasterPodcasts(VentanaPrincipal vp, GestionBD gestionBD, GestionInformacion gestionInfo) {
		setSize(800, 600);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
	}

}
