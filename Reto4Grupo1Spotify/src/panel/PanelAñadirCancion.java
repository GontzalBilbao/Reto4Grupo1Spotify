package panel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controlador.GestionBD;
import vista.VentanaPrincipal;

public class PanelAñadirCancion extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelAñadirCancion(VentanaPrincipal v, GestionBD gestionBD) {
		setSize(800, 600);
//		setBackground(Color.DARK_GRAY);
		setLayout(null);

		JButton btnFinalizar = new JButton("FINALIZAR");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.cambiarDePanel(17);
			}
		});
		btnFinalizar.setBounds(560, 450, 200, 50);
		add(btnFinalizar);

		JLabel lblAñadirMusico = new JLabel("AÑADIR CANCION");
		lblAñadirMusico.setBounds(125, 15, 550, 65);
		lblAñadirMusico.setHorizontalAlignment(SwingConstants.CENTER);
		lblAñadirMusico.setFont(new Font("Sitka Subheading", Font.BOLD, 40));
		add(lblAñadirMusico);

		JButton btnAtras = new JButton("ATRAS");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.cambiarDePanel(16);
			}
		});
		btnAtras.setBounds(650, 25, 100, 35);
		add(btnAtras);

	}

}
