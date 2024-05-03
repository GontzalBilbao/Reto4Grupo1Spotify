package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controlador.GestionInformacion;
import modelo.Artista;
import modelo.Musico;
import vista.VentanaPrincipal;
import javax.swing.JComboBox;

public class PanelDescubrirMusica extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox comboBoxArtistas;

	
	private ArrayList<Musico> musicos;
	/**
	 * Create the panel.
	 */
	public PanelDescubrirMusica(VentanaPrincipal v, GestionInformacion gestionInfo) {
		
		musicos = gestionInfo.devolverMusico();
		
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
		
		JComboBox<String> comboBoxMusico = new JComboBox<String>();
		comboBoxMusico.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboBoxMusico.setBounds(255, 250, 300, 30);
		add(comboBoxMusico);
		for (int i = 0; i < musicos.size(); i++) {
			comboBoxMusico.addItem(musicos.get(i).getNombreArtistico());
		}
	
		//Para centrar el texto del comboBox
		DefaultListCellRenderer centrado = new DefaultListCellRenderer();
		centrado.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		comboBoxMusico.setRenderer(centrado);
		
		JButton btnIrAMusico = new JButton("IR A MUSICO");
		btnIrAMusico.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnIrAMusico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreMusico = comboBoxMusico.getSelectedItem().toString();
				v.cambiarDePanel(5);
			}
		});
		btnIrAMusico.setBounds(530, 450, 180, 35);
		add(btnIrAMusico);
		
	}
}
