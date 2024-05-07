package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controlador.ControladorDeSonido;
import controlador.GestionBD;
import modelo.Audio;
import vista.VentanaPrincipal;

public class PanelReproductorMusica extends JPanel {

	private static final long serialVersionUID = 1L;

	private int intinerador = 0;
	private ControladorDeSonido controladorDeSonido;
	private boolean random = false;
	private boolean bucle = false;
	private boolean aleatorio = false;
	private JLabel lblReproductor;
	private JLabel lblIconoGrande;
	private JLabel lblImagenCancion;
	private JLabel lblTitulo;
	private JButton btnPlay;
	private JButton btnPlayStop;

	/**
	 * Create the panel.
	 */
	public PanelReproductorMusica(VentanaPrincipal v, GestionBD gestion) {
		ArrayList<Audio> cancion = new ArrayList<Audio>();
		cancion.add(new Audio("PPPPP", "A Little Piece of Heaven", 0, new ImageIcon("imagen/Avenged-Sevenfold.jpg"), "xxx"));
		controladorDeSonido = new ControladorDeSonido(gestion.lecturaImagenEnBD());
		
		setSize(800, 600);
		setBackground(Color.DARK_GRAY);
		setLayout(null);

		lblReproductor = new JLabel("REPRODUCTOR MÚSICA");
		lblReproductor.setHorizontalAlignment(SwingConstants.CENTER);
		lblReproductor.setForeground(Color.GREEN);
		lblReproductor.setFont(new Font("Lucida Bright", Font.BOLD, 20));
		lblReproductor.setBounds(270, 100, 260, 51);
		add(lblReproductor);

		lblIconoGrande = new JLabel("");
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

		JButton btnCancionAnterior = new JButton("<");
		btnCancionAnterior.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCancionAnterior.setBackground(Color.BLACK);
		btnCancionAnterior.setForeground(Color.WHITE);
		btnCancionAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* Para que vuelba al inicio de reproduccion sin dar erro */
				if (aleatorio = true)
					controladorDeSonido.setCancionEnReproduccion(intinerador);
					lblImagenCancion.setIcon(gestion.lecturaImagenEnBD().get(intinerador).getImagen());
					lblTitulo.setText("<html>" + gestion.lecturaImagenEnBD().get(intinerador).getNombre() + "</html>");
					
			}
		});
		btnCancionAnterior.setBounds(250, 475, 70, 30);
		add(btnCancionAnterior);

		JButton btnCancionSiguiente = new JButton(">");
		btnCancionSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancionSiguiente.setBackground(Color.BLACK);
		btnCancionSiguiente.setForeground(Color.WHITE);
		btnCancionSiguiente.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCancionSiguiente.setBounds(480, 475, 70, 30);
		add(btnCancionSiguiente);

		btnPlay = new JButton("PLAY");
		btnPlay.setForeground(Color.WHITE);
		btnPlay.setBackground(Color.BLACK);
		btnPlay.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorDeSonido.reproducir(0);
				btnPlay.setVisible(false);
				btnPlayStop.setVisible(true);
			}
		});
		btnPlay.setBounds(355, 475, 90, 30);
		add(btnPlay);

		JButton btnFavorito = new JButton("FAV");
		btnFavorito.setBackground(Color.BLACK);
		btnFavorito.setForeground(Color.WHITE);
		btnFavorito.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnFavorito.setBounds(570, 475, 80, 30);
		add(btnFavorito);
		
		lblImagenCancion = new JLabel();
		lblImagenCancion.setIcon(gestion.lecturaImagenEnBD().get(intinerador).getImagen());
		lblImagenCancion.setBounds(275, 200, 250, 250);
		add(lblImagenCancion);
		
		lblTitulo = new JLabel("");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(275, 170, 250, 20);
		add(lblTitulo);
		
		btnPlayStop = new JButton("STOP");
		btnPlayStop.setVisible(false);
		btnPlayStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorDeSonido.continuarCancion();
			}
		});
		btnPlayStop.setBackground(Color.BLACK);
		btnPlayStop.setForeground(Color.WHITE);
		btnPlayStop.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnPlayStop.setBounds(355, 475, 90, 30);
		add(btnPlayStop);
	}
}
