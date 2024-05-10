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
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

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
	private int podcastSeleccionado = 0;
	private IControladorSonido controladorDePodcast;
	private boolean aleatorio = false;
	private JLabel lblIconoGrande;
	private JLabel lblImagenPodcast;
	private JLabel lblTitulo;
	private JButton btnPlay;
	private JButton btnPlayStop;

	private ArrayList<Podcaster> podcasters;
	private ArrayList<Podcast> podcasts;
	private String nombreArtista = "";
	private String audioSeleccionado = "";

	private int podcasterSeleccionado;

	/**
	 * Create the panel.
	 */

	public PanelReproductorPodcasts(VentanaPrincipal vp, GestionBD gestionBD, GestionInformacion gestionInfo) {

		gestionBD.cargarPodcasters();
		podcasters = gestionBD.devolverPodcasters();
		nombreArtista = gestionInfo.devolverArtistaSeleccionado();
		System.out.println(nombreArtista);
		for (int i = 0; i < podcasters.size(); i++) {
			if (nombreArtista.equals(podcasters.get(i).getNombreArtistico())) {
				gestionBD.cargarPodcastsDelPodcaster(podcasters.get(i).getIdPodcaster());
				podcasts = gestionBD.devolverPodcasts();
				podcasterSeleccionado = i;
			}
		}
		audioSeleccionado = gestionInfo.devolverAudioSeleccionado();
		for (int i = 0; i < podcasts.size(); i++) {
			if (audioSeleccionado.equals(podcasts.get(i).getNombre())) {
				podcastSeleccionado = i;
			}
		}
//		gestionBD.cargarPodcastsDelPodcaster(podcasters.get(0).getIdPodcaster());
//		podcasts = gestionBD.devolverPodcasts();

		setSize(800, 600);
		setBackground(Color.DARK_GRAY);
		setLayout(null);

		controladorDePodcast = new ControladorDePodcast(podcasts);

		intinerador = gestionInfo.pasarIndicePodcast();

//		for (int i = 0; i < 5; i++) {
//			System.out.println(gestionBD.queryAudioPodcast().get(i).getNombre());
//		}

		setSize(800, 600);
		setBackground(Color.DARK_GRAY);
		setLayout(null);

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(this, popupMenu);

		lblIconoGrande = new JLabel("");
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

		JButton btnCancionAnterior = new JButton("<");
		btnCancionAnterior.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCancionAnterior.setBackground(Color.BLACK);
		btnCancionAnterior.setForeground(Color.WHITE);
		btnCancionAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* Para que vuelba al inicio de reproduccion sin dar erro */
				if (aleatorio = true)
					if (aleatorio = true)
						if (intinerador == 0) {
							intinerador = podcasts.size() - 1;
						} else {
							intinerador = (intinerador - 1) % podcasts.size();
						}
				controladorDePodcast.setCancionEnReproduccion(intinerador);
				lblImagenPodcast.setIcon(podcasters.get(podcastSeleccionado).getImagen());
				lblTitulo.setText("<html>" + podcasts.get(intinerador).getNombre() + "</html>");

			}
		});
		btnCancionAnterior.setBounds(165, 265, 70, 30);
		add(btnCancionAnterior);

		JButton btnCancionSiguiente = new JButton(">");
		btnCancionSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				intinerador = (intinerador + 1) % gestionBD.queryAudioPodcast().size();

				controladorDePodcast.setCancionEnReproduccion(intinerador);
				lblImagenPodcast.setIcon(podcasters.get(podcasterSeleccionado).getImagen());
				lblTitulo.setText("<html>" + podcasts.get(podcastSeleccionado).getNombre() + "</html>");
				btnPlay.setVisible(true);
				btnPlayStop.setVisible(false);
			}
		});
		btnCancionSiguiente.setBackground(Color.BLACK);
		btnCancionSiguiente.setForeground(Color.WHITE);
		btnCancionSiguiente.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCancionSiguiente.setBounds(565, 265, 70, 30);
		add(btnCancionSiguiente);

		btnPlay = new JButton("PLAY");
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
		btnPlay.setBounds(355, 425, 90, 30);
		add(btnPlay);

		JButton btnFavorito = new JButton("FAVORITO");
		btnFavorito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFavorito.setBackground(Color.BLACK);
		btnFavorito.setForeground(Color.WHITE);
		btnFavorito.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnFavorito.setBounds(230, 470, 150, 30);
		add(btnFavorito);

		lblImagenPodcast = new JLabel();
		lblImagenPodcast.setIcon(podcasters.get(podcasterSeleccionado).getImagen());
		lblImagenPodcast.setBounds(275, 150, 250, 250);
		add(lblImagenPodcast);

		lblTitulo = new JLabel("");
		lblTitulo.setText(podcasts.get(podcastSeleccionado).getNombre());
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(275, 100, 250, 30);
		add(lblTitulo);

		btnPlayStop = new JButton("STOP");
		btnPlayStop.setVisible(false);
		btnPlayStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorDePodcast.continuarCancion(btnPlayStop);
			}
		});
		btnPlayStop.setBackground(Color.BLACK);
		btnPlayStop.setForeground(Color.WHITE);
		btnPlayStop.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnPlayStop.setBounds(355, 425, 90, 30);
		add(btnPlayStop);

		JButton btnmMenu = new JButton("MENU");
		btnmMenu.setBackground(Color.BLACK);
		btnmMenu.setForeground(Color.WHITE);
		btnmMenu.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnmMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnmMenu.setBounds(420, 470, 150, 30);
		add(btnmMenu);

		JPopupMenu popupMenu_1 = new JPopupMenu();
		addPopup(btnmMenu, popupMenu_1);

		JMenuItem mntmAñadirAPlayList = new JMenuItem("Añadir  a PalyList");
		mntmAñadirAPlayList.setForeground(Color.WHITE);
		mntmAñadirAPlayList.setBackground(Color.BLACK);
		popupMenu_1.add(mntmAñadirAPlayList);

		JMenuItem mntmExportarPodcast = new JMenuItem("Exportear Podcast");
		mntmExportarPodcast.setBackground(Color.BLACK);
		mntmExportarPodcast.setForeground(Color.WHITE);
		popupMenu_1.add(mntmExportarPodcast);
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
