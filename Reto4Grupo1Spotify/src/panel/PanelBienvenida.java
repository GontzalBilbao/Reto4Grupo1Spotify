package panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import vista.VentanaPrincipal;

public class PanelBienvenida extends JPanel{

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelBienvenida(VentanaPrincipal v) {
		setSize(800, 600);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		JLabel lblBienvenida = new JLabel("BIENVENIDO A SPOTIFY");
		lblBienvenida.setFont(new Font("Lucida Bright", Font.BOLD, 20));
		lblBienvenida.setForeground(Color.GREEN);
		lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenida.setBounds(270, 150, 260, 51);
		add(lblBienvenida);
		
		JLabel lblIconoGrande = new JLabel("");
		lblIconoGrande.setIcon(new ImageIcon("icono/spotifyicon.png"));
		lblIconoGrande.setBounds(350, 200, 100, 100);
		add(lblIconoGrande);

	}
	
	
}
