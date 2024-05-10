package panel;

import java.awt.Color;

import javax.swing.JPanel;

import vista.VentanaPrincipal;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class PanelPerfil extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelPerfil(VentanaPrincipal v) {
		setSize(800, 600);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		JLabel lblPlaylists = new JLabel("PLAYLISTS");
		lblPlaylists.setBounds(376, 100, 260, 51);
		lblPlaylists.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlaylists.setForeground(Color.GREEN);
		lblPlaylists.setFont(new Font("Lucida Bright", Font.BOLD, 20));
		add(lblPlaylists);
		
		JList listaPlaylist = new JList();
		listaPlaylist.setBounds(330, 150, 350, 355);
		add(listaPlaylist);
		
		JLabel lblUsuario = new JLabel("-Usuario-");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setForeground(Color.GREEN);
		lblUsuario.setFont(new Font("Lucida Bright", Font.BOLD, 40));
		lblUsuario.setBounds(50, 382, 250, 50);
		add(lblUsuario);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Lucida Bright", Font.BOLD, 18));
		lblTipo.setForeground(Color.LIGHT_GRAY);
		lblTipo.setBounds(90, 432, 80, 30);
		add(lblTipo);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(70, 390, 5, 72);
		add(panel);
		
		JPanel panelFoto = new JPanel();
		panelFoto.setBackground(Color.LIGHT_GRAY);
		panelFoto.setBounds(70, 167, 200, 200);
		add(panelFoto);
		panelFoto.setLayout(null);
		
		JLabel lblLetra = new JLabel("L");
		lblLetra.setForeground(Color.DARK_GRAY);
		lblLetra.setFont(new Font("Lucida Bright", Font.BOLD, 99));
		lblLetra.setHorizontalAlignment(SwingConstants.CENTER);
		lblLetra.setBounds(0, 0, 200, 200);
		panelFoto.add(lblLetra);
		
		JButton btnAtras = new JButton("Atrás");
		btnAtras.setBounds(75, 85, 89, 23);
		add(btnAtras);

	}
}
