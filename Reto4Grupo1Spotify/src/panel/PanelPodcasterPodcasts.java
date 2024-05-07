package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
		
		JLabel lblPodcast = new JLabel("PODCAST");
		lblPodcast.setHorizontalAlignment(SwingConstants.CENTER);
		lblPodcast.setForeground(Color.GREEN);
		lblPodcast.setFont(new Font("Lucida Bright", Font.BOLD, 20));
		lblPodcast.setBounds(270, 100, 260, 51);
		add(lblPodcast);
		
		JLabel lblIconoGrande = new JLabel("");
		lblIconoGrande.setIcon(new ImageIcon("icono/spotifyicon.png"));
		lblIconoGrande.setBounds(0, 0, 100, 100);
		add(lblIconoGrande);
		
		JButton btnAtras = new JButton("ATRAS");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(3);
			}
		});
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAtras.setBounds(200, 36, 90, 35);
		add(btnAtras);
		
		JButton btnPerfil = new JButton("PERFIL");
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(11);
			}
		});
		btnPerfil.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnPerfil.setBounds(500, 36, 90, 35);
		add(btnPerfil);
	}

}
