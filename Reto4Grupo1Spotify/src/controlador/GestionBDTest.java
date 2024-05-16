package controlador;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

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

public class GestionBDTest {

	private static GestionBD gestionBD;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		gestionBD = new GestionBD();
	}

	@Test
	public void testQueryClientes() {
		ArrayList<Cliente> clientes = gestionBD.queryClientes();

		Cliente cliente1 = clientes.get(0);
		Cliente esperado = new Cliente("C0001", "Juan", "Pérez", "juanito", "contraseña123", "1990-05-15", "2024-04-17",
				"premium", "CA");

		assertEquals(esperado.getUsuario(), cliente1.getUsuario());
	}

	@Test
	public void testQueryPodcasters() {
		ArrayList<Podcaster> podcasters = gestionBD.queryPodcasters();

		Podcaster podcaster1 = podcasters.get(0);
		ImageIcon imagen = new ImageIcon("/Reto4Grupo1Spotify/icono/imagen_testJUnit.jpg");
		Podcaster esperado = new Podcaster("BM001", "Black Mango Podcast", "Educación", imagen,
				"Un podcast que explora los rincones más oscuros y fascinantes de la cultura pop, desde conspiraciones hasta misterios inexplicables, todo con un toque de humor ácido y análisis profundo.");

		assertEquals(esperado.getNombreArtistico(), podcaster1.getNombreArtistico());
	}

	@Test
	public void testQueryPodcastsDelPodcaster() {
		String IdPodcaster = "BM001";
		ArrayList<Podcast> podcasts = gestionBD.queryPodcastsDelPodcaster(IdPodcaster);

		Podcast podcast1 = podcasts.get(0);
		ImageIcon imagen = new ImageIcon("/Reto4Grupo1Spotify/icono/imagen_testJUnit.jpg");
		Podcast esperado = new Podcast("BMPOD01", "BM001", "Episodio 1 Black Mango", "131", "null",
				"Episodio 1 de Black Mango, donde discutimos temas interesantes sobre la vida y la sociedad.", imagen,
				"podcast");

		assertEquals(esperado.getNombre(), podcast1.getNombre());
	}

	@Test
	public void testQueryMusicos() {
		ArrayList<Musico> musicos = gestionBD.queryMusicos();

		Musico musico1 = musicos.get(0);
		ImageIcon imagen = new ImageIcon("/Reto4Grupo1Spotify/icono/imagen_testJUnit.jpg");
		Musico esperado = new Musico("AV3NS", "Avenged Sevenfold", "Grupo", imagen,
				"Avenged Sevenfold es una banda de heavy metal estadounidense formada en 1999 en Huntington Beach, California. La banda está compuesta por M. Shadows, Synyster Gates, Zacky Vengeance, Johnny Christ y Brooks Wackerman.");

		assertEquals(esperado.getNombreArtistico(), musico1.getNombreArtistico());

	}

	@Test
	public void testQueryAlbumesDelMusico() {
		String IdMusico = "AV3NS";
		ArrayList<Album> albumes = gestionBD.queryAlbumesDelMusico(IdMusico);

		Album musico1 = albumes.get(0);
		ImageIcon imagen = new ImageIcon("/Reto4Grupo1Spotify/icono/imagen_testJUnit.jpg");
		Album esperado = new Album("AVEN01", "Nightmare", "2010-07-27", "Heavy Metal", imagen, "AV3NS");

		assertEquals(esperado.getTitulo(), musico1.getTitulo());
	}

	@Test
	public void testQueryCancionesDelAlbum() {
		String IdAlbum = "BM001";
		ArrayList<Cancion> canciones = gestionBD.queryCancionesDelAlbum(IdAlbum);

		Cancion cancion1 = canciones.get(0);

		ImageIcon imagen = new ImageIcon("/Reto4Grupo1Spotify/icono/imagen_testJUnit.jpg");
		Cancion esperado = new Cancion("AVEN01-01", "AVEN01", "Nightmare", "6:14", "null", imagen, "cancion");

		assertEquals(esperado.getNombre(), cancion1.getNombre());
	}

	@Test
	public void testSacarPremium() {
		String premium = gestionBD.sacarPremium("juanito");
		String esperado = "premium";
		assertEquals(esperado, premium);
	}

	@Test
	public void testTopCancionesFavoritas() {
		ArrayList<CancionesFavoritas> cancionesFav = gestionBD.topCancionesFavoritas();

		CancionesFavoritas cancionFav1 = cancionesFav.get(0);

		CancionesFavoritas esperado = new CancionesFavoritas("Let It Be", "3:40", "NULL", 309870);

		assertEquals(esperado.getNombreCancion(), cancionFav1.getNombreCancion());
	}

	@Test
	public void testTopPodcastsFavoritos() {
		ArrayList<PodcastsFavoritos> podcastsFav = gestionBD.topPodcastsFavoritos();

		PodcastsFavoritos podcastFav1 = podcastsFav.get(0);

		PodcastsFavoritos esperado = new PodcastsFavoritos("Episodio 1 Black Mango", "131", "NULL", 986000);
		assertEquals(esperado.getNombrePodcast(), podcastFav1.getNombrePodcast());
	}

	@Test
	public void testTopMasEscuchado() {
		ArrayList<MasEscuchado> masEscuchados = gestionBD.topMasEscuchado();

		MasEscuchado masEscuchado1 = masEscuchados.get(0);

		MasEscuchado esperado = new MasEscuchado("Episodio 1 Black Mango", "131", "NULL", 986000);
		assertEquals(esperado.getNombre(), masEscuchado1.getNombre());

	}

	@Test
	public void testTopPlayList() {
		ArrayList<TopPlayList> topPlaylists = gestionBD.topPlayList();

		TopPlayList topPlaylist1 = topPlaylists.get(0);

		TopPlayList esperado = new TopPlayList("Episodio 1 Nepe", "68", "podcast", 678000, 34656);
		assertEquals(esperado.getNombreAudio(), topPlaylist1.getNombreAudio());
	}

	@Test
	public void testQueryPlayListasDelUsuario(String cliente) {
		String usuario = "BM001";
		ArrayList<PlayList> playlists = gestionBD.queryPlayListasDelUsuario(usuario);

		PlayList cancion1 = playlists.get(0);

		PlayList esperado = new PlayList(1, "Playlist para Dormir", "2024-04-17", "C0001");

		assertEquals(esperado.getTitulo(), cancion1.getTitulo());
	}

	@Test
	public void testIdPlaylist(String titulo) {
		String nombre = "Playlist para Dormir";
		int playlist = gestionBD.idPlaylist(nombre);

		int esperado = 1;
	
		assertEquals(esperado, playlist);
	}

	@Test
	public void testSacarIdCliente(String usuario) {
		String usuario1 = "juanito";
		String idCliente = gestionBD.sacarIdCliente(usuario1);
		
		String esperado = "C0001";
		
		assertEquals(esperado, idCliente);
	}

	@Test
	public void testQueryCancionesDePlaylist(String playlistSeleccionada) {
		String tituloPlaylist = "Playlist para Dormir";
		ArrayList<Cancion> canciones = gestionBD.queryCancionesDePlaylist(tituloPlaylist);

		Cancion cancion1 = canciones.get(0);

		ImageIcon imagen = new ImageIcon("/Reto4Grupo1Spotify/icono/imagen_testJUnit.jpg");
		Cancion esperado = new Cancion("AVEN01-01", "AVEN01", "Nightmare", "6:14", "null", imagen, "cancion");

		
		assertEquals(esperado.getNombre(), cancion1.getNombre());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		gestionBD.cerrarConexion();
	}

}
