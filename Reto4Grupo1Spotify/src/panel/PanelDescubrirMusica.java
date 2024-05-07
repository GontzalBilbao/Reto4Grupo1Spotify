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

public class PanelDescubrirMusica extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelDescubrirMusica(VentanaPrincipal vp, GestionBD gestionBD, GestionInformacion gestionInfo) {
		setSize(800, 600);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		JLabel lblLogin = new JLabel("DESCUBRIR MUSICA");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(Color.GREEN);
		lblLogin.setFont(new Font("Lucida Bright", Font.BOLD, 20));
		lblLogin.setBounds(270, 100, 260, 51);
		add(lblLogin);
		
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
		
		JLabel lblImagenArtista1 = new JLabel("");
		lblImagenArtista1.setIcon(new ImageIcon("icono/spotifyicon.png"));
		lblImagenArtista1.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagenArtista1.setBounds(13, 220, 180, 200);
		add(lblImagenArtista1);
		
		JLabel lblImagenArtista2 = new JLabel("");
		lblImagenArtista2.setIcon(new ImageIcon("icono/spotifyicon.png"));
		lblImagenArtista2.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagenArtista2.setBounds(206, 220, 180, 200);
		add(lblImagenArtista2);
		
		JLabel lblImagenArtista3 = new JLabel("");
		lblImagenArtista3.setIcon(new ImageIcon("icono/spotifyicon.png"));
		lblImagenArtista3.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagenArtista3.setBounds(399, 220, 180, 200);
		add(lblImagenArtista3);
		
		JLabel lblImagenArtista4 = new JLabel("");
		lblImagenArtista4.setIcon(new ImageIcon("icono/spotifyicon.png"));
		lblImagenArtista4.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagenArtista4.setBounds(595, 220, 180, 200);
		add(lblImagenArtista4);
		
		JLabel lblNombreArtista1 = new JLabel("ARTISTA 1");
		lblNombreArtista1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNombreArtista1.setForeground(Color.WHITE);
		lblNombreArtista1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreArtista1.setBounds(13, 450, 180, 35);
		add(lblNombreArtista1);
		
		JLabel lblNombreArtista2 = new JLabel("ARTISTA 2");
		lblNombreArtista2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNombreArtista2.setForeground(Color.WHITE);
		lblNombreArtista2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreArtista2.setBounds(206, 450, 180, 35);
		add(lblNombreArtista2);
		
		JLabel lblNombreArtista3 = new JLabel("ARTISTA 3");
		lblNombreArtista3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNombreArtista3.setForeground(Color.WHITE);
		lblNombreArtista3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreArtista3.setBounds(399, 450, 180, 35);
		add(lblNombreArtista3);
		
		JLabel lblNombreArtista4 = new JLabel("ARTISTA 4");
		lblNombreArtista4.setForeground(Color.WHITE);
		lblNombreArtista4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNombreArtista4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreArtista4.setBounds(595, 450, 180, 35);
		add(lblNombreArtista4);
		
		
	}

}
