package panel;

import java.awt.Color;

import javax.swing.JPanel;

import controlador.GestionBD;
import vista.VentanaPrincipal;

public class PanelGestionarCancion extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelGestionarCancion(VentanaPrincipal v, GestionBD gestionBD) {
		setSize(800, 600);
		setBackground(Color.DARK_GRAY);
		setLayout(null);

	}

}
