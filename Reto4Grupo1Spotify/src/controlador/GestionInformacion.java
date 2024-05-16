package controlador;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import modelo.Album;
import modelo.Cancion;
import modelo.CancionesFavoritas;
import modelo.Cliente;
import modelo.MasEscuchado;
import modelo.Musico;
import modelo.PlayList;
import modelo.Podcast;
import modelo.Podcaster;
import modelo.PodcastsFavoritos;
import modelo.TopPlayList;

public class GestionInformacion {

	private GestionBD gestionBD;
//	private String musico;
//	private String podcaster;
//	private String tipoDePerfil;
	private String artistaSeleccionado = "";
	private String albumSeleccionado = "";
	private String nombrePodcastSeleccionado = "";
	private String cancionSeleccionada = "";
	private String idArtista = "";
//	private int recogerIndiceCancion;
	private int recogerIndicePodcast;
	private String idCliente;
	private String premium;
	private String usuarioCliente = "";
	private boolean panelAnterior;
	private String playlistSeleccionada = "";

	public ArrayList<Cliente> clientes = new ArrayList<Cliente>();

	public ArrayList<Podcaster> podcasters = new ArrayList<Podcaster>();
	public ArrayList<Podcast> podcasts = new ArrayList<Podcast>();

	public ArrayList<Musico> musicos = new ArrayList<Musico>();
	public ArrayList<Album> albumes = new ArrayList<Album>();
	public ArrayList<Cancion> canciones = new ArrayList<Cancion>();

	public ArrayList<PlayList> playlists = new ArrayList<PlayList>();

	public ArrayList<CancionesFavoritas> topCanciones = new ArrayList<CancionesFavoritas>();
	public ArrayList<PodcastsFavoritos> topPodcasts = new ArrayList<PodcastsFavoritos>();
	public ArrayList<MasEscuchado> masEscuchado = new ArrayList<MasEscuchado>();
	public ArrayList<TopPlayList> topPlaylist = new ArrayList<TopPlayList>();

	public GestionInformacion() {
		gestionBD = new GestionBD();
	}

	public boolean validarContrasena(boolean campos, String contraseña, String confirmarContraseña) {
		boolean vuelta = false;
		Pattern regexContraseña = Pattern
				.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$_@$!%*?&])([A-Za-z\\d$@_!%*?&]|[^ ]){8,15}$"); // Regex
																												// para
																												// comprobar
																												// que
																												// la
																												// nueva
																												// contraseña
																												// a
																												// registrar
																												// cumple
																												// unos
																												// requisitos
		Matcher validarContraseña = regexContraseña.matcher(contraseña);

		if (campos == false) {
			vuelta = false;
		} else if (!contraseña.equals(confirmarContraseña)) {
			JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error de Registro",
					JOptionPane.ERROR_MESSAGE);
			vuelta = false;
		} else if (!validarContraseña.find()) {
			JOptionPane.showMessageDialog(null, "La contraseña no cumple los requisitos", "Error de Registro",
					JOptionPane.ERROR_MESSAGE);
			vuelta = false;
		} else {
			vuelta = true;
		}
		return vuelta;
	}

	// Guarda el Id del Artista y lo devuelve
	public void guardarIdArtistaSeleccionado(String idArtista) {
		this.idArtista = idArtista;
	}

	public String devolverIdArtistaSeleccionado() {
		return idArtista;
	}

	// Guarda el nombre del Artista y lo devuelve
	public void guardarArtistaSeleccionado(String nombreArtista) {
		this.artistaSeleccionado = nombreArtista;
	}

	public String devolverArtistaSeleccionado() {
		return artistaSeleccionado;
	}

	// Guardael titulo del Album y lo devuelve
	public void guardarAlbumSeleccionado(String tituloAlbum) {
		this.albumSeleccionado = tituloAlbum;
	}

	public String devolverAlbumSeleccionado() {
		return albumSeleccionado;
	}

	// Guarda el nombre de la Cancion y lo devuelve
	public void guardarCancionSeleccionado(String cancionSeleccionada) {
		this.cancionSeleccionada = cancionSeleccionada;
	}

	public String devolverCancionSeleccionado() {
		return cancionSeleccionada;
	}

//	public String devolverTipoDePerfil() {
//		return tipoDePerfil;
//	}

//	public void recogerMusico(String nombreMusico) {
//		this.musico = nombreMusico;
//	}

//	public String pasrverMusico() {
//		return musico;
//	}

//	public ArrayList<Podcaster> devolverPodcaster() {
//		return gestionBD.queryPodcasters();
//	}

//	public void recogerPodcaster(String nombrePodcaster) {
//		this.podcaster = nombrePodcaster;
//	}

//	public String pasrverPodcaster() {
//		return podcaster;
//	}



//	public int pasarIndiceCancion() {
//		return recogerIndiceCancion;
//	}

	public void recogerIndicePodcast(int indice) {
		this.recogerIndicePodcast = indice;
	}

	public int pasarIndicePodcast() {
		return recogerIndicePodcast;
	}

	// Saca si el Cliente es Premium o no y lo devuelve
	public void sacarPremium(String usuario) {
		this.premium = gestionBD.sacarPremium(usuario);
	}

	public String devolverPremium() {
		return premium;
	}

	// Guarda el nombre del Podcast y lo devuelve
	public void guardarNombrePodcastSeleccionado(String nombrePodcastSeleccionado) {
		this.nombrePodcastSeleccionado = nombrePodcastSeleccionado;
	}

	public String devolverNombrePodcastSeleccionado() {
		return nombrePodcastSeleccionado;
	}

	/**/
	public int devolverIdPlaylist(String titulo) {
		return gestionBD.idPlaylist(titulo);
	}

	// Saca el id del Cliente y lo devuelve
	public void sacarIdCliente(String usuario) {
		this.idCliente = gestionBD.sacarIdCliente(usuario);
	}

	public String devolverIdCliente() {
		return idCliente;
	}

	/**/
	public void añadirCancionAPlaylist(String idAudio, int idPlaylist) {
		gestionBD.insertCancionEnPlaylist(idAudio, idPlaylist);
	}

	// Guarda el usuario del Cliente
	public void guardarUsuarioCliente(String usuario) {
		this.usuarioCliente = usuario;
	}

	public String devolverUsuarioCliente() {
		return usuarioCliente;
	}

	// Guarda si el panel anterior es el PanelAlbumCancion o no y lo devuelve
	public void guardarPanelAnteriorAlbumCanciones(boolean panelAnterior) {
		this.panelAnterior = panelAnterior;
	}

	public boolean devolverPanelAnteriorAlbumCanciones() {
		return panelAnterior;
	}

	// Guarda el nombre de la Playlist y lo devuelve
	public void guardarPlaylistSeleccionada(String nombrePlaylist) {
		this.playlistSeleccionada = nombrePlaylist;
	}

	public String devolverPlaylistSeleccionada() {
		return playlistSeleccionada;
	}

	// Carga el arraylist "clientes" y lo devuelve
	public void cargarClientes() {
		clientes.clear();
		clientes = gestionBD.queryClientes();
	}

	public ArrayList<Cliente> devolverClientes() {
		return clientes;
	}

	// Agrega un nuevo cliente
	public void agregarNuevoCliente(String idCliente, String nombre, String apellido, String usuario, String contraseña,
			String fechaNac, String fechaRegistro, String tipoSuscp, String idIdioma) {
		gestionBD.agregarCliente(idCliente, nombre, apellido, usuario, contraseña, fechaNac, fechaRegistro, tipoSuscp,
				idIdioma);
	}

	// Carga el arraylist "podcasters" y lo devuelve
	public void cargarPodcasters() {
		podcasters.clear();
		podcasters = gestionBD.queryPodcasters();
	}

	public ArrayList<Podcaster> devolverPodcasters() {
		return podcasters;
	}

	// Carga el arraylist "podcasts" dependiendo del podcaster y lo devuelve
	public void cargarPodcastsDelPodcaster(String idPodcaster) {
		podcasts.clear();
		podcasts = gestionBD.queryPodcastsDelPodcaster(idPodcaster);
	}

	public ArrayList<Podcast> devolverPodcasts() {
		return podcasts;
	}

	// Carga el arraylist "musicos" y lo devuelve
	public void cargarMusicos() {
		musicos.clear();
		musicos = gestionBD.queryMusicos();
	}

	public ArrayList<Musico> devolverMusicos() {
		return musicos;
	}

	// Carga el arraylist "albumes" dependiendo del musico y lo devuelve
	public void cargarAlbumesDelMusico(String idMusico) {
		albumes.clear();
		albumes = gestionBD.queryAlbumesDelMusico(idMusico);
	}

	public ArrayList<Album> devolverAlbumes() {
		return albumes;
	}

	// Carga el arraylist "canciones" dependiendo del album y lo devuelve
	public void cargarCancionesDelAlbum(String idAlbum) {
		canciones.clear();
		canciones = gestionBD.queryCancionesDelAlbum(idAlbum);
	}

	public ArrayList<Cancion> devolverCanciones() {
		return canciones;
	}

	// Carga el top canciones más favoritas en el ArrayList "topCanciones" y lo
	// devuelve
	public void cargarTopCancionesFavoritas() {
		topCanciones.clear();
		topCanciones = gestionBD.topCancionesFavoritas();
	}

	public ArrayList<CancionesFavoritas> devolverTopCancionesFavoritas() {
		return topCanciones;
	}

	// Carga el top podcasts más favoritos en el ArrayList "topPodcasts" y lo
	// devuelve
	public void cargarTopPodcastsFavoritos() {
		topPodcasts.clear();
		topPodcasts = gestionBD.topPodcastsFavoritos();
	}

	public ArrayList<PodcastsFavoritos> devolverTopPodcastsFavoritos() {
		return topPodcasts;
	}

	// Carga el top canciones mas escuchadas en el ArrayList "masEscuchado" y lo
	// devuelve
	public void cargarTopMasEscuchado() {
		masEscuchado.clear();
		masEscuchado = gestionBD.topMasEscuchado();

	}

	public ArrayList<MasEscuchado> devolverTopMasEscuchado() {
		return masEscuchado;
	}

	// Carga el top playlist mas escuchadas en el ArrayList "topPlaylist" y lo
	// devuelve
	public void cargarTopPlaylist() {
		topPlaylist.clear();
		topPlaylist = gestionBD.topPlayList();
	}

	public ArrayList<TopPlayList> devolverTopPlaylist() {
		return topPlaylist;
	}

	// Agrega la cancion a la Playlist de favoritos
	public void agregarFavorito(String idCliente, String idAudio) {
		gestionBD.agregarFavorito(idCliente, idAudio);
	}

	// Carga las Playlist del cliente en el ArrayList "playlists" y lo devuelve
	public void cargarPlayLists(String cliente) {
		playlists.clear();
		playlists = gestionBD.queryPlayListasDelUsuario(cliente);
	}

	public ArrayList<PlayList> devolverPlayLists() {
		return playlists;
	}

	// Crea una nueva playlist
	public void añadirPlayList(String nuevaPlayList, String idCliente) {
		gestionBD.añadirPlayList(nuevaPlayList, idCliente);
	}

	// Elimina la playlist que concuerde con el nombre dado
	public void eliminarPlayList(String nombrePlaylist) {
		gestionBD.eliminarPlayList(nombrePlaylist);
	}

	// Agrega la canción a la playlist con cierto ID
	public void agregarCancionAPlaylist(int idPlaylist, String idCancion, String fechaAgregacion) {
		gestionBD.agregarCancionAPlaylist(idPlaylist, idCancion, fechaAgregacion);
	}

	// Carga las canciones dentro de una playlist
	public void cargarCancionesDePlaylist(String playlistSeleccionada) {
		canciones.clear();
		canciones = gestionBD.queryCancionesDePlaylist(playlistSeleccionada);
	}

	// Devuelve la cantidad de canciones que tiene una Playlist
	public int capacidadPlaylist(int idPlaylist) {
		int capacidadPlaylist = gestionBD.capacidadDePlaylist(idPlaylist);
		return capacidadPlaylist;
	}
	
//	public void guardarTituloPlayListSeleccionada(String titulo) {
//		this.playlistSeleccionada = titulo;
//	}
//	
//	public String devolverTituloPlayListSeleccionada() {
//		return playlistSeleccionada;
//		
//	}

}
