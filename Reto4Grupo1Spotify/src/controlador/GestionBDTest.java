package controlador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modelo.Album;
import modelo.Cancion;
import modelo.Cliente;
import modelo.Musico;
import modelo.Podcast;
import modelo.Podcaster;

public class GestionBDTest {

	private static GestionBD gestionBD;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		gestionBD = new GestionBD();
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCargarClientes() {
		gestionBD.cargarClientes();

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
	public void testQueryTipoDePerfil() {

	}

	@Test
	public void testAgregarCliente() {
		fail("Not yet implemented");
	}

	@Test
	public void testDevolverClientes() {
		gestionBD.cargarClientes();
		ArrayList<Cliente> clientes = gestionBD.devolverClientes();
		assertNotNull(clientes.get(1).getIdCliente());
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
		
	}

	@Test
	public void testTopPodcastsFavoritos() {
		fail("Not yet implemented");
	}

	@Test
	public void testTopMasEscuchado() {
		fail("Not yet implemented");
	}

	@Test
	public void testTopPlayList() {
		fail("Not yet implemented");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		gestionBD.cerrarConexion();
	}

}
