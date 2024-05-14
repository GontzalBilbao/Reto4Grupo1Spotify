package panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import controlador.ControladorDeSonido;
import controlador.GestionBD;
import controlador.GestionInformacion;
import interfaces.IControladorSonido;
import modelo.Album;
import modelo.Cancion;
import modelo.PlayList;
import vista.VentanaPrincipal;

public class PanelReproductorMusica extends JPanel {

	private static final long serialVersionUID = 1L;

	private int intinerador = 0;
	private IControladorSonido controladorDeSonido;
//	private boolean aleatorio = false;
	private JLabel lblIconoGrande;
	private JLabel lblImagenCancion;
	private JLabel lblTitulo;
	private JButton btnPlay;
	private JButton btnPlayStop;
	private JButton btnCancionAnterior;
	private JButton btnCancionSiguiente;
	private JButton btnFavorito;
	private JButton btnmMenu;
	private JPopupMenu popupMenu;
	
	private String tituloAlbumSeleccionado = "";
	private String nombreCancionSeleccionada = "";
	private int numeroCancion = 0;
	private int numeroAlbum = 0;
	private String idMusico = "";
	private String idCancionActual = "";
	private int idPlaylistElegida = 0;
	
	private ArrayList<Cancion> canciones = new ArrayList<Cancion>();
	private ArrayList<Album> albumes = new ArrayList<Album>();
	private ArrayList<PlayList> playlists = new ArrayList<PlayList>();

	protected boolean anuncio = false;

	
	/**
	 * Create the panel.
	 */

	public PanelReproductorMusica(VentanaPrincipal vp, GestionBD gestionBD, GestionInformacion gestionInfo) {
		
		Timer timerAnuncio = new Timer(20000, new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	btnCancionSiguiente.setEnabled(true);
		        btnCancionAnterior.setEnabled(true);
		    }
		});
		
		
		idMusico = gestionInfo.devolverIdArtistaSeleccionado();
		tituloAlbumSeleccionado = gestionInfo.devolverAlbumSeleccionado();
		nombreCancionSeleccionada = gestionInfo.devolverCancionSeleccionado();
		gestionBD.cargarAlbumesDelMusico(idMusico);
		albumes = gestionBD.devolverAlbumes();
		for (int i = 0; i < albumes.size(); i++) {
			if(tituloAlbumSeleccionado.equals(albumes.get(i).getTitulo())) {
				gestionBD.cargarCancionesDelAlbum(albumes.get(i).getIdAlbum());
				canciones = gestionBD.devolverCanciones();
				numeroAlbum = i;
				for (int j = 0; j < canciones.size(); j++) {
					if (nombreCancionSeleccionada.equals(canciones.get(j).getNombre())) {
						numeroCancion = j;
					}
				}
			}
			
		}
		
		setSize(800, 600);
		setBackground(Color.DARK_GRAY);
		setLayout(null);	
		
		controladorDeSonido = new ControladorDeSonido(canciones);

		intinerador = gestionInfo.pasarIndiceCancion();

		lblIconoGrande = new JLabel("");
		lblIconoGrande.setIcon(new ImageIcon("icono/spotifyicon.png"));
		lblIconoGrande.setBounds(0, 0, 100, 100);
		add(lblIconoGrande);

		JButton btnAtras = new JButton("ATRAS");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(6);
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

		btnCancionAnterior = new JButton("<");
		btnCancionAnterior.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCancionAnterior.setBackground(Color.BLACK);
		btnCancionAnterior.setForeground(Color.WHITE);
		btnCancionAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* Para que vuelba al inicio de reproduccion sin dar erro */

				if (intinerador == 0) {
					intinerador = canciones.size() - 1;
				} else {
					intinerador = (intinerador - 1)
							% canciones.size();
				}
				
				if (gestionInfo.devolverPremiun().equalsIgnoreCase("Premiun")) {
					intinerador = (intinerador + 1)
							% canciones.size();

					controladorDeSonido.setCancionEnReproduccion(intinerador);
					lblImagenCancion.setIcon(canciones.get(0).getImagen());
					lblTitulo.setText("<html>" + canciones.get(intinerador).getNombre() + "</html>");
					btnPlay.setVisible(true);
					btnPlayStop.setVisible(false);
				} else {
					if (!anuncio) {
						btnCancionAnterior.setEnabled(false);
						btnCancionSiguiente.setEnabled(false);
						btnPlay.setVisible(false);
						btnPlayStop.setVisible(false);
						btnFavorito.setVisible(false);
						btnmMenu.setVisible(false);
						timerAnuncio.restart();
						
						controladorDeSonido.parar();
						controladorDeSonido.anuncio();
						lblImagenCancion.setIcon(new ImageIcon("anuncio/Anuncio.jpg"));
						lblTitulo.setText("");
						anuncio = true;
					} else {
						btnmMenu.setVisible(true);
						btnFavorito.setVisible(true);
						
						intinerador = controladorDeSonido.ramdom();
						controladorDeSonido.reproducir(intinerador);
						lblImagenCancion.setIcon(canciones.get(0).getImagen());
						lblTitulo.setText("<html>" + canciones.get(intinerador).getNombre() + "</html>");
						btnPlayStop.setVisible(true );	
						anuncio = false;
					}
					
				}
				
				
			}
		});
		btnCancionAnterior.setBounds(165, 265, 70, 30);
		add(btnCancionAnterior);

		btnCancionSiguiente = new JButton(">");
		btnCancionSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (gestionInfo.devolverPremiun().equalsIgnoreCase("Premiun")) {
					intinerador = (intinerador + 1)
							% canciones.size();

					controladorDeSonido.setCancionEnReproduccion(intinerador);
					lblImagenCancion.setIcon(canciones.get(0).getImagen());
					lblTitulo.setText("<html>" + canciones.get(intinerador).getNombre() + "</html>");
					btnPlay.setVisible(true);
					btnPlayStop.setVisible(false);
				} else {
					if (!anuncio) {
						btnCancionAnterior.setEnabled(false);
						btnCancionSiguiente.setEnabled(false);
						btnPlay.setVisible(false);
						btnPlayStop.setVisible(false);
						btnFavorito.setVisible(false);
						btnmMenu.setVisible(false);
						timerAnuncio.restart();
						
						
						controladorDeSonido.parar();
						controladorDeSonido.anuncio();
						lblImagenCancion.setIcon(new ImageIcon("anuncio/Anuncio.jpg"));
						lblTitulo.setText("");
						anuncio = true;
					} else {	
						btnmMenu.setVisible(true);
						btnFavorito.setVisible(true);
						
						intinerador = controladorDeSonido.ramdom();
						controladorDeSonido.reproducir(intinerador);
						lblImagenCancion.setIcon(canciones.get(0).getImagen());
						lblTitulo.setText("<html>" + canciones.get(intinerador).getNombre() + "</html>");
						btnPlayStop.setVisible(true);	
						anuncio = false;
					}
					
				}
				
				
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
				controladorDeSonido.reproducir(intinerador);
				btnPlay.setVisible(false);
				btnPlayStop.setVisible(true);
			}
		});
		btnPlay.setBounds(355, 425, 90, 30);
		add(btnPlay);

		btnFavorito = new JButton("FAVORITO");
		btnFavorito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestionBD.agregarFavorito(gestionInfo.devolverIdCliente() ,canciones.get(intinerador).getIdAudio());
			}
		});
		btnFavorito.setBackground(Color.BLACK);
		btnFavorito.setForeground(Color.WHITE);
		btnFavorito.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnFavorito.setBounds(230, 470, 150, 30);
		add(btnFavorito);

		lblImagenCancion = new JLabel();
		lblImagenCancion.setIcon(albumes.get(numeroAlbum).getImagen());
		lblImagenCancion.setBounds(275, 150, 250, 250);
		add(lblImagenCancion);

		lblTitulo = new JLabel("");
		lblTitulo.setText(canciones.get(numeroCancion).getNombre());
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(275, 100, 250, 30);
		add(lblTitulo);

		btnPlayStop = new JButton("STOP");
		btnPlayStop.setVisible(false);
		btnPlayStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorDeSonido.continuarCancion(btnPlayStop);
			}
		});
		btnPlayStop.setBackground(Color.BLACK);
		btnPlayStop.setForeground(Color.WHITE);
		btnPlayStop.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnPlayStop.setBounds(355, 425, 90, 30);
		add(btnPlayStop);

		btnmMenu = new JButton("MENU");
		btnmMenu.setBackground(Color.BLACK);
		btnmMenu.setForeground(Color.WHITE);
		btnmMenu.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnmMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPopupMenu(btnmMenu);

				
//				JFrame f = new JFrame();
//				String playlist = JOptionPane.showInputDialog(f, "Introduzca el nombre de la playlist:");
//
//				if (gestionInfo.capacidadDePlaylist(gestionInfo.devolverIdPlaylist(playlist)) == 3) {
//					JOptionPane.showMessageDialog(null,
//							"Has llegado a la capacidad maxima de la playlist " + playlist + "!!");
//				} else {
//					gestionInfo.añadirCancionAPlaylist(canciones.get(intinerador).getIdAudio(),
//							gestionInfo.devolverIdPlaylist(playlist));
//				}
			}
		});
		popupMenu = new JPopupMenu();
        JMenu addToPlaylistMenu = new JMenu("Añadir a Playlist");
        
		gestionBD.cargarPlayLists(gestionInfo.devolverClienteSeleccionado());
		playlists = gestionBD.devolverPlayLists();
		
        for (int i = 0; i < playlists.size(); i++) {
        	System.out.println(playlists.get(i).getTitulo());
        	JMenuItem menuItem = new JMenuItem(playlists.get(i).getTitulo());
        	menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			        JMenuItem menuItem = (JMenuItem) e.getSource();
			        String infoMenuItem = menuItem.getText();
			        int idPlaylist = cogerIdPlaylistSeleccionada(infoMenuItem);
			        String idCancion = cogerIdCancionEnReproduccion(lblTitulo.getText());
//			        String fechaAgregacion = 
//					addToPlaylist(e);
				}
			});
        	addToPlaylistMenu.add(menuItem);
		}
        
        popupMenu.add(addToPlaylistMenu);
		btnmMenu.setBounds(420, 470, 150, 30);
		add(btnmMenu);

	}
    private String cogerIdCancionEnReproduccion(String text) {
		for (int i = 0; i < canciones.size(); i++) {
			if (canciones.get(i).getNombre().equals(text)) {
				idCancionActual = canciones.get(i).getIdAudio();
			} else {
				
			}
		}
    	return idCancionActual;
	}
	private int cogerIdPlaylistSeleccionada(String titulo) {
		for (int i = 0; i < playlists.size(); i++) {
			if (playlists.get(i).getTitulo().equals(titulo)) {
				idPlaylistElegida = playlists.get(i).getIdList();
			} else {
				
			}
		}
		
		return idPlaylistElegida;
		
	}
	private void showPopupMenu(Component component) {
        popupMenu.show(component, 0, component.getHeight());
    }
}