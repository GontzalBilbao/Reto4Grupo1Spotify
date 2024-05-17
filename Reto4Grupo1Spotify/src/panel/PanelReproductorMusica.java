package panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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


	private int intinerador = 0;// Índice de la canción en reproducción
	private IControladorSonido controladorDeSonido;// Controlador de la reproducción de sonido
	private JLabel lblIconoGrande;// Etiqueta para mostrar un icono grande
	private JLabel lblImagenCancion;// Etiqueta para mostrar la imagen de la canción
	private JLabel lblTitulo;// Etiqueta para mostrar el título de la canción
	private JButton btnPlay;// Botón para reproducir una canción
	private JButton btnPlayStop;// Botón para detener la reproducción de una canción
	private JButton btnCancionAnterior;// Botón para reproducir la canción anterior
	private JButton btnCancionSiguiente;// Botón para reproducir la canción siguiente
	private JButton btnFavorito;// Botón para agregar una canción a favoritos
	private JButton btnmMenu;// Botón para acceder al menú de la aplicación
//	private boolean aleatorio = false;

	private JPopupMenu popupMenu;
	private String tituloAlbumSeleccionado = "";// Título del álbum seleccionado
	private String nombreCancionSeleccionada = "";// Nombre de la canción seleccionada
	private int numeroCancion = 0;// Índice de la canción seleccionada en el álbum
	private int numeroAlbum = 0;// Índice del álbum seleccionado
	private String idMusico = "";// ID del músico
	private boolean panelAnteriorAlbumCanciones;
	private String idCancionActual = "";
	private int idPlaylistElegida = 0;
	private ArrayList<Cancion> canciones = new ArrayList<Cancion>();// Lista de canciones
	private ArrayList<Album> albumes = new ArrayList<Album>();// Lista de álbumes
	private ArrayList<PlayList> playlists;
	
	private boolean anuncio = false;// Indica si se está reproduciendo un anuncio
	
	/**
     * Constructor de PanelReproductorMusica.
     * @param vp La ventana principal de la aplicación.
     * @param gestionBD El objeto para gestionar la base de datos.
     * @param gestionInfo El objeto para gestionar la información.
     */
	public PanelReproductorMusica(VentanaPrincipal vp, GestionBD gestionBD, GestionInformacion gestionInfo) {

		// Timer para controlar el anuncio
		Timer timerAnuncio = new Timer(20000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancionSiguiente.setEnabled(true);
				btnCancionAnterior.setEnabled(true);
			}
		});
		
		
		panelAnteriorAlbumCanciones = gestionInfo.devolverPanelAnteriorAlbumCanciones();
		System.out.println(panelAnteriorAlbumCanciones);
		
		if (panelAnteriorAlbumCanciones == true) {

			// Cargar información inicial
			idMusico = gestionInfo.devolverIdArtistaSeleccionado();// Obtener el ID del artista seleccionado
			tituloAlbumSeleccionado = gestionInfo.devolverAlbumSeleccionado();// Obtener el título del álbum
																				// seleccionado
			nombreCancionSeleccionada = gestionInfo.devolverCancionSeleccionado();// Obtener el nombre de la canción
																					// seleccionada
			gestionInfo.cargarAlbumesDelMusico(idMusico);// Cargar los álbumes del músico desde la base de datos
			albumes = gestionInfo.devolverAlbumes();// Obtener la lista de álbumes

			for (int i = 0; i < albumes.size(); i++) {

				if (tituloAlbumSeleccionado.equals(albumes.get(i).getTitulo())) {// Buscar el álbum seleccionado
					gestionInfo.cargarCancionesDelAlbum(albumes.get(i).getIdAlbum());// Cargar las canciones del álbum
																						// desde la base de datos
					canciones = gestionInfo.devolverCanciones();// Obtener la lista de canciones

					for (int j = 0; j < canciones.size(); j++) {
						if (nombreCancionSeleccionada.equals(canciones.get(j).getNombre())) {
							numeroCancion = j;// Establecer el índice de la canción seleccionada en el álbum
						}
					}
				}

			}
		} else {
			
			if (gestionInfo.devolverPlaylistSeleccionada().equalsIgnoreCase("Favoritos")) {
				canciones = gestionInfo.cancionesDePlaylistFavoritos();
				numeroCancion = 0;
		
			} else {
				String playlistSeleccionada = gestionInfo.devolverPlaylistSeleccionada();
				gestionInfo.cargarCancionesDePlaylist(playlistSeleccionada);
				canciones = gestionInfo.devolverCanciones();
				numeroCancion = 0;
				
			}
			
	
		}

		// Configuración del panel
		setSize(800, 600);
		setBackground(Color.WHITE);
		setLayout(null);

		// Inicializar el controlador de sonido con la lista de canciones
		controladorDeSonido = new ControladorDeSonido(canciones);

		intinerador = numeroCancion;

		// Etiqueta para mostrar un icono grande
		lblIconoGrande = new JLabel("");
		lblIconoGrande.setIcon(new ImageIcon("icono/spotifyicon.png"));
		lblIconoGrande.setBounds(0, 0, 100, 100);
		add(lblIconoGrande);

		// Botón ATRAS
		JButton btnAtras = new JButton("ATRAS");
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setBackground(Color.BLACK);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorDeSonido.parar();
				if (panelAnteriorAlbumCanciones == true) {
					controladorDeSonido.parar();
					vp.cambiarDePanel(6);
				} else {
					controladorDeSonido.parar();
					vp.cambiarDePanel(11);
				}
				
				
			}
		});
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAtras.setBounds(200, 36, 90, 35);
		add(btnAtras);

		// Botón PERFIL
		JButton btnPerfil = new JButton("PERFIL");
		btnPerfil.setBackground(Color.BLACK);
		btnPerfil.setForeground(Color.WHITE);
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorDeSonido.parar();
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
				controladorDeSonido.parar();
				/* Para que vuelba al inicio de reproduccion sin dar erro */

				if (intinerador == 0) {// Verifica si el índice de la canción actual es 0 (primera canción)
					intinerador = canciones.size() - 1; // Si es así, establece el índice a la última canción en la lista
				} else {
					intinerador = (intinerador - 1) % canciones.size();// De lo contrario, retrocede al índice de la canción anterior en la lista
				}

				if (gestionInfo.devolverPremium().equalsIgnoreCase("Premium")) { // Verifica si el usuario tiene una suscripción "Premium"

					controladorDeSonido.setCancionEnReproduccion(intinerador);// Actualiza el controlador de sonido con la nueva canción en reproducción
					// Actualiza la imagen de la canción y el título en la interfaz gráfica
					lblImagenCancion.setIcon(canciones.get(0).getImagen());
					lblTitulo.setText("<html>" + canciones.get(intinerador).getNombre() + "</html>");
					// Muestra el botón de reproducción y oculta el botón de detención
					btnPlay.setVisible(true);
					btnPlayStop.setVisible(false);
				} else {// Si no es Premium
					if (!anuncio) {
						// Si no hay anuncio, deshabilita los botones de navegación y control de reproducción
						btnCancionAnterior.setEnabled(false);
						btnCancionSiguiente.setEnabled(false);
						btnPlay.setVisible(false);
						btnPlayStop.setVisible(false);
						btnFavorito.setVisible(false);
						btnmMenu.setVisible(false);
						timerAnuncio.restart();// Reinicia el temporizador de anuncio

						// Detiene la reproducción de la canción y reproduce un anuncio
						controladorDeSonido.parar();
						controladorDeSonido.anuncio();
						// Muestra la imagen del anuncio y limpia el título de la canción
						lblImagenCancion.setIcon(new ImageIcon("anuncio/Anuncio.jpg"));
						lblTitulo.setText("");
						anuncio = true;
					} else {
						// Si ya hay un anuncio en reproducción
						// Muestra los botones de menú y favorito
						btnmMenu.setVisible(true);
						btnFavorito.setVisible(true);

						// Reproduce una canción aleatoria
						intinerador = controladorDeSonido.ramdom();
						controladorDeSonido.reproducir(intinerador);
						// Actualiza la imagen y el título de la canción en la interfaz gráfica
						lblImagenCancion.setIcon(canciones.get(0).getImagen());
						lblTitulo.setText("<html>" + canciones.get(intinerador).getNombre() + "</html>");
						btnPlayStop.setVisible(true);
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
				controladorDeSonido.parar();
				if (gestionInfo.devolverPremium().equalsIgnoreCase("Premium")) {
					intinerador = (intinerador + 1) % canciones.size();
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
						btnPlayStop.setVisible(false);
						btnPlay.setVisible(true);
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
			public void actionPerformed(ActionEvent e) {// Método que se ejecuta cuando se hace clic en el botón PLAY
				controladorDeSonido.reproducir(intinerador);// Reproduce la canción actual utilizando el controlador de sonido
				btnPlay.setVisible(false);// Oculta el botón PLAY
				btnPlayStop.setVisible(true);// Muestra el botón STOP
			}
		});
		btnPlay.setBounds(355, 425, 90, 30);
		add(btnPlay);

		btnFavorito = new JButton("FAVORITO");
		btnFavorito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Llama al método agregarFavorito de la clase GestionBD para agregar la canción actual a la lista de favoritos
				gestionBD.agregarFavorito(gestionInfo.devolverIdCliente(), canciones.get(intinerador).getIdAudio());
				JOptionPane.showMessageDialog(vp, "Cancion añadida a favoritos");
			}
		});
		btnFavorito.setBackground(Color.BLACK);
		btnFavorito.setForeground(Color.WHITE);
		btnFavorito.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnFavorito.setBounds(230, 470, 150, 30);
		add(btnFavorito);

		lblImagenCancion = new JLabel();
		lblImagenCancion.setIcon(canciones.get(intinerador).getImagen());
		lblImagenCancion.setBounds(275, 150, 250, 250);
		add(lblImagenCancion);

		lblTitulo = new JLabel("");
		lblTitulo.setBackground(Color.WHITE);
		lblTitulo.setText(canciones.get(intinerador).getNombre());
		lblTitulo.setForeground(Color.BLACK);
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
//				
//				if (gestionInfo.capacidadPlaylist(gestionInfo.devolverIdPlaylist(playlist)) == 3 && !gestionInfo.devolverPremium().equalsIgnoreCase("Premium")) {
//					JOptionPane.showMessageDialog(vp,
//							"Has llegado a la capacidad maxima de la playlist " + playlist + "!!");
//				} else {
//					gestionInfo.añadirCancionAPlaylist(canciones.get(intinerador).getIdAudio(),
//							gestionInfo.devolverIdPlaylist(playlist));
//				}
							
				// Llama al método añadirCancionAPlaylist de la clase GestionInformacion para agregar la canción actual a la playlist especificada
				
			}
		});

		popupMenu = new JPopupMenu();
		JMenu addToPlaylistMenu = new JMenu("Añadir a Playlist");

		gestionInfo.cargarPlayLists(gestionInfo.devolverUsuarioCliente());
		playlists = gestionInfo.devolverPlayLists();

		for (int i = 0; i < playlists.size(); i++) {
			System.out.println(playlists.get(i).getTitulo());
			JMenuItem menuItem = new JMenuItem(playlists.get(i).getTitulo());
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JMenuItem menuItem = (JMenuItem) e.getSource();
					String infoMenuItem = menuItem.getText();
					int idPlaylist = cogerIdPlaylistSeleccionada(infoMenuItem);
					if (gestionInfo.devolverPremium().equalsIgnoreCase("Premium")) {
						añadirCancionAPlaylist(idPlaylist, gestionInfo);
						JOptionPane.showMessageDialog(vp, lblTitulo.getText() + " añadida a " + infoMenuItem, "Canción añadida", JOptionPane.INFORMATION_MESSAGE);
					} else {
						if (gestionInfo.capacidadPlaylist(idPlaylist) >= 3) {
							JOptionPane.showMessageDialog(vp, "Hágase Premium para añadir más canciones a la playlist!", "Límite Alcanzado", JOptionPane.INFORMATION_MESSAGE);
						} else {
							añadirCancionAPlaylist(idPlaylist, gestionInfo);
						}
					}
				}
			});
			addToPlaylistMenu.add(menuItem);
		}

		popupMenu.add(addToPlaylistMenu);
		btnmMenu.setBounds(420, 470, 150, 30);
		add(btnmMenu);

	}
	
	private void añadirCancionAPlaylist(int idPlaylist, GestionInformacion gestionInfo) {
		String idCancion = cogerIdCancionEnReproduccion(lblTitulo.getText());
		LocalDate fechaActual = LocalDate.now();
		DateTimeFormatter fechaFormateada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fechaAgregacion = fechaActual.format(fechaFormateada);
		gestionInfo.agregarCancionAPlaylist(idPlaylist, idCancion, fechaAgregacion);
		
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