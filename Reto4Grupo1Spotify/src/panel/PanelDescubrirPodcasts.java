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

public class PanelDescubrirPodcasts extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelDescubrirPodcasts(VentanaPrincipal v) {
		setSize(800, 600);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		JLabel lblLogin = new JLabel("DESCUBIR PODCASTS");
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
				v.cambiarDePanel(3);
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
