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

import vista.VentanaPrincipal;

public class PanelMenuCliente extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelMenuCliente(VentanaPrincipal v) {
		setSize(800, 600);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		JLabel lblLogin = new JLabel("MENU CLIENTE");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(Color.GREEN);
		lblLogin.setFont(new Font("Lucida Bright", Font.BOLD, 20));
		lblLogin.setBounds(270, 100, 260, 51);
		add(lblLogin);
		
		JButton btnDescubirMusica = new JButton("DESCUBRIR MUSICA");
		btnDescubirMusica.setHorizontalAlignment(SwingConstants.LEFT);
		btnDescubirMusica.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDescubirMusica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.cambiarDePanel(4);
			}
		});
		btnDescubirMusica.setBounds(275, 200, 250, 35);
		add(btnDescubirMusica);
		
		JButton btnDescubirPodcast = new JButton("DESCUBRIR PODCASTS");
		btnDescubirPodcast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.cambiarDePanel(8);
			}
		});
		btnDescubirPodcast.setHorizontalAlignment(SwingConstants.LEFT);
		btnDescubirPodcast.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDescubirPodcast.setBounds(275, 300, 250, 35);
		add(btnDescubirPodcast);
		
		JButton btnMisPlaylist = new JButton("MIS PLAYLISTS");
		btnMisPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.cambiarDePanel(12);
			}
		});
		btnMisPlaylist.setHorizontalAlignment(SwingConstants.LEFT);
		btnMisPlaylist.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnMisPlaylist.setBounds(275, 400, 250, 35);
		add(btnMisPlaylist);
		
		JLabel lblIconoGrande = new JLabel("");
		lblIconoGrande.setIcon(new ImageIcon("icono/spotifyicon.png"));
		lblIconoGrande.setBounds(0, 0, 100, 100);
		add(lblIconoGrande);
		
		JButton btnAtras = new JButton("ATRAS");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.cambiarDePanel(1);
			}
		});
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAtras.setBounds(200, 36, 90, 35);
		add(btnAtras);
		
		JButton btnPerfil = new JButton("PERFIL");
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.cambiarDePanel(11);
			}
		});
		btnPerfil.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnPerfil.setBounds(500, 36, 90, 35);
		add(btnPerfil);
		

	}

}
