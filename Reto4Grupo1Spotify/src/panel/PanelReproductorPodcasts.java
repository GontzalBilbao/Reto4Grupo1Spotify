package panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import controlador.ControladorDePodcast;
import controlador.GestionBD;
import controlador.GestionInformacion;
import interfaces.IControladorSonido;
import modelo.Podcast;
import modelo.Podcaster;
import vista.VentanaPrincipal;

public class PanelReproductorPodcasts extends JPanel {

	private static final long serialVersionUID = 1L;

	private int intinerador = 0;
	private int numeroPodcast = 0;
	private IControladorSonido controladorDePodcast;
//	private boolean aleatorio = false;
	private JLabel lblIconoGrande;
	private JLabel lblImagenPodcast;
	private JLabel lblTitulo;
	private JButton btnPlay;
	private JButton btnPlayStop;
	private JButton btnCancionAnterior;
	private JButton btnCancionSiguiente;

	private ArrayList<Podcaster> podcasters;
	private ArrayList<Podcast> podcasts;
	private String nombrePodcaster = "";
	private String nombrePodcastSeleccionado = "";

	private int numeroPodcaster;
	
	protected boolean anuncio= false;

	/**
	 * Create the panel.
	 */

	public PanelReproductorPodcasts(VentanaPrincipal vp, GestionInformacion gestionInfo) {

		Timer timerAnuncio = new Timer(20000, new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	btnCancionSiguiente.setEnabled(true);
		        btnCancionAnterior.setEnabled(true);
		    }
		});
		
		
		gestionInfo.cargarPodcasters();
		podcasters = gestionInfo.devolverPodcasters();
		nombrePodcaster = gestionInfo.devolverArtistaSeleccionado();
		nombrePodcastSeleccionado = gestionInfo.devolverNombrePodcastSeleccionado();
		System.out.println(nombrePodcaster);
		for (int i = 0; i < podcasters.size(); i++) {
			if (nombrePodcaster.equals(podcasters.get(i).getNombreArtistico())) {
				gestionInfo.cargarPodcastsDelPodcaster(podcasters.get(i).getIdPodcaster());
				podcasts = gestionInfo.devolverPodcasts();
				numeroPodcaster = i;
				for (int j = 0; j < podcasts.size(); j++) {
					if (nombrePodcastSeleccionado.equals(podcasts.get(j).getNombre())) {
						numeroPodcast = j;
					}
				}
			}
		}
		
//		for (int i = 0; i < podcasts.size(); i++) {
//			if (nombrePodcastSeleccionado.equals(podcasts.get(i).getNombre())) {
//				numeroPodcast = i;
//			}
//		}
//		gestionBD.cargarPodcastsDelPodcaster(podcasters.get(0).getIdPodcaster());
//		podcasts = gestionBD.devolverPodcasts();

		setSize(800, 600);
		setBackground(Color.WHITE);

//		System.out.println(podcasts);
		
		controladorDePodcast = new ControladorDePodcast(podcasts);

		intinerador = gestionInfo.pasarIndicePodcast();
//		intinerador = 2;

		
		
//		for (int i = 0; i < 5; i++) {
//			System.out.println(gestionBD.queryAudioPodcast().get(i).getNombre());
//		}

		setSize(800, 600);
		setLayout(null);
		setLayout(null);

		lblIconoGrande = new JLabel("");
		lblIconoGrande.setBounds(0, 0, 100, 100);
		lblIconoGrande.setIcon(new ImageIcon("icono/spotifyicon.png"));
		add(lblIconoGrande);

		JButton btnAtras = new JButton("ATRAS");
		btnAtras.setBounds(200, 36, 90, 35);
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setBackground(Color.BLACK);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(9);
			}
		});
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(btnAtras);

		JButton btnPerfil = new JButton("PERFIL");
		btnPerfil.setBounds(500, 36, 90, 35);
		btnPerfil.setForeground(Color.WHITE);
		btnPerfil.setBackground(Color.BLACK);
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(11);
			}
		});
		btnPerfil.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(btnPerfil);

		btnCancionAnterior = new JButton("<");
		btnCancionAnterior.setBounds(165, 265, 70, 30);
		btnCancionAnterior.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCancionAnterior.setBackground(Color.BLACK);
		btnCancionAnterior.setForeground(Color.WHITE);
		btnCancionAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (intinerador == 0) {
					intinerador = podcasts.size() - 1;
				} else {
					intinerador = (intinerador - 1)
							% podcasts.size();
				}
				
				if (gestionInfo.devolverPremium().equalsIgnoreCase("Premium")) {

					controladorDePodcast.setCancionEnReproduccion(intinerador);
					lblImagenPodcast.setIcon(podcasters.get(numeroPodcaster).getImagen());
					lblTitulo.setText("<html>" + podcasts.get(intinerador).getNombre() + "</html>");
					btnPlay.setVisible(true);
					btnPlayStop.setVisible(false);
				} else {
					if (!anuncio) {
						btnCancionAnterior.setEnabled(false);
						btnCancionSiguiente.setEnabled(false);
						btnPlay.setVisible(false);
						btnPlayStop.setVisible(false);
//						btnFavorito.setVisible(false);
//						btnmMenu.setVisible(false);
						timerAnuncio.restart();
						
						controladorDePodcast.parar();
						controladorDePodcast.anuncio();
						lblImagenPodcast.setIcon(new ImageIcon("anuncio/Anuncio.jpg"));
						lblTitulo.setText("");
						anuncio = true;
					} else {
						
						intinerador = controladorDePodcast.ramdom();
						controladorDePodcast.reproducir(intinerador);
						lblImagenPodcast.setIcon(podcasters.get(numeroPodcaster).getImagen());
						lblTitulo.setText("<html>" + podcasts.get(intinerador).getNombre() + "</html>");
						btnPlayStop.setVisible(true );	
						anuncio = false;
					}
					
				}
				
			}
		});
		add(btnCancionAnterior);

		btnCancionSiguiente = new JButton(">");
		btnCancionSiguiente.setBounds(565, 265, 70, 30);
		btnCancionSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if (gestionInfo.devolverPremium().equalsIgnoreCase("Premium")) {
					intinerador = (intinerador + 1)
							% podcasts.size();

					controladorDePodcast.setCancionEnReproduccion(intinerador);
					lblImagenPodcast.setIcon(podcasters.get(numeroPodcaster).getImagen());
					lblTitulo.setText("<html>" + podcasts.get(intinerador).getNombre() + "</html>");
					btnPlay.setVisible(true);
					btnPlayStop.setVisible(false);
				} else {
					if (!anuncio) {
						btnCancionAnterior.setEnabled(false);
						btnCancionSiguiente.setEnabled(false);
						btnPlay.setVisible(false);
						btnPlayStop.setVisible(false);
						timerAnuncio.restart();
						
						
						controladorDePodcast.parar();
						controladorDePodcast.anuncio();
						lblImagenPodcast.setIcon(new ImageIcon("anuncio/Anuncio.jpg"));
						lblTitulo.setText("");
						anuncio = true;
					} else {	
						
						intinerador = controladorDePodcast.ramdom();
						controladorDePodcast.reproducir(intinerador);
						lblImagenPodcast.setIcon(podcasters.get(numeroPodcaster).getImagen());
						lblTitulo.setText("<html>" + podcasts.get(intinerador).getNombre() + "</html>");
						btnPlayStop.setVisible(true);	
						anuncio = false;
					}
					
				}
				
				
			}
		});
		btnCancionSiguiente.setBackground(Color.BLACK);
		btnCancionSiguiente.setForeground(Color.WHITE);
		btnCancionSiguiente.setFont(new Font("Tahoma", Font.BOLD, 20));
		add(btnCancionSiguiente);

		btnPlay = new JButton("PLAY");
		btnPlay.setBounds(355, 425, 90, 30);
		btnPlay.setForeground(Color.WHITE);
		btnPlay.setBackground(Color.BLACK);
		btnPlay.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorDePodcast.reproducir(intinerador);
				btnPlay.setVisible(false);
				btnPlayStop.setVisible(true);
			}
		});
		add(btnPlay);

		lblImagenPodcast = new JLabel();
		lblImagenPodcast.setBounds(275, 150, 250, 250);
		lblImagenPodcast.setIcon(podcasters.get(numeroPodcaster).getImagen());
		add(lblImagenPodcast);

		lblTitulo = new JLabel("");
		lblTitulo.setBounds(275, 100, 250, 30);
		lblTitulo.setBackground(Color.BLACK);
		lblTitulo.setText(podcasts.get(numeroPodcast).getNombre());
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitulo);

		btnPlayStop = new JButton("STOP");
		btnPlayStop.setBounds(355, 425, 90, 30);
		btnPlayStop.setVisible(false);
		btnPlayStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorDePodcast.continuarCancion(btnPlayStop);
			}
		});
		btnPlayStop.setBackground(Color.BLACK);
		btnPlayStop.setForeground(Color.WHITE);
		btnPlayStop.setFont(new Font("Tahoma", Font.BOLD, 20));
		add(btnPlayStop);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}