package panel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import vista.VentanaPrincipal;

public class PanelMenuAdministrador extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelMenuAdministrador(VentanaPrincipal vp) {
		setSize(800, 600);
//		setBackground(Color.DARK_GRAY);
		setLayout(null);

		JButton btnAtras = new JButton("CERRAR SESION");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(1);
			}
		});
		btnAtras.setBounds(615, 25, 135, 35);
		add(btnAtras);

		JButton btnArtistas = new JButton("MUSICOS");
		btnArtistas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(14);
			}
		});
		btnArtistas.setBounds(50, 150, 220, 60);
		add(btnArtistas);

		JButton btnAlbumes = new JButton("ALBUMES");
		btnAlbumes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(18);
			}
		});
		btnAlbumes.setBounds(50, 300, 220, 60);
		add(btnAlbumes);

		JButton btnCanciones = new JButton("CANCIONES");
		btnCanciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(19);
			}
		});
		btnCanciones.setBounds(50, 450, 220, 60);
		add(btnCanciones);

		JButton btnPodcasters = new JButton("PODCASTERS");
		btnPodcasters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(20);
			}
		});
		btnPodcasters.setBounds(500, 150, 220, 60);
		add(btnPodcasters);

		JButton btnPodcasts = new JButton("PODCASTS");
		btnPodcasts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(22);
			}
		});
		btnPodcasts.setBounds(500, 300, 220, 60);
		add(btnPodcasts);

		JButton btnEstadisticas = new JButton("ESTADISTICAS");
		btnEstadisticas.setBounds(500, 450, 220, 60);
		btnEstadisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(24);
			}
		});
		add(btnEstadisticas);

		JLabel lblGestionar = new JLabel("GESTIONAR");
		lblGestionar.setFont(new Font("Arial", Font.PLAIN, 40));
		lblGestionar.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionar.setBounds(215, 50, 350, 70);
		add(lblGestionar);
	}
}
