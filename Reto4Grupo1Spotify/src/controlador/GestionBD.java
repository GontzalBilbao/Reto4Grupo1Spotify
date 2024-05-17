package controlador;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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

public class GestionBD {
	private Connection conexion;

	public ArrayList<Cliente> clientes = new ArrayList<Cliente>();

	public ArrayList<Podcaster> podcasters = new ArrayList<Podcaster>();
	public ArrayList<Podcast> podcasts = new ArrayList<Podcast>();

	public ArrayList<Musico> musicos = new ArrayList<Musico>();
	public ArrayList<Album> albumes = new ArrayList<Album>();
	public ArrayList<Cancion> canciones = new ArrayList<Cancion>();

	public ArrayList<PlayList> playLists = new ArrayList<PlayList>();

	public ArrayList<CancionesFavoritas> cancionesFav = new ArrayList<CancionesFavoritas>();
	public ArrayList<PodcastsFavoritos> podcastsFav = new ArrayList<PodcastsFavoritos>();
	public ArrayList<MasEscuchado> masEscuchado = new ArrayList<MasEscuchado>();
	public ArrayList<TopPlayList> topPlaylist = new ArrayList<TopPlayList>();

	public GestionBD() {
		iniciarConexion();
	}

	public void iniciarConexion() {
		System.out.println("Conectando..........");
		try {
			Class.forName("com.mysql.jdbc.Driver");

			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdreto4", "root", "");
		} catch (ClassNotFoundException e) {
			System.out.println("Libreria no encontrada");
		} catch (SQLException e) {
			System.out.println("BBDD no encontrada");
		}
		System.out.println("Conexion establecida");
	}

	public void cerrarConexion() {
		System.out.println("Conectando......");
		try {
			if (!conexion.isClosed()) {
				conexion.close();
			}
		} catch (SQLException e) {
			System.out.println("No hay conexion con la BD");
		}
		System.out.println("Conexion cerrada");
	}

	/* QUERYS DE LOS CLIENTES */

//	public void cargarClientes() {
//		clientes.clear();
//		clientes = queryClientes();
//	}

	public ArrayList<Cliente> queryClientes() {
		try {
			Statement consulta = conexion.createStatement();
			String query = "SELECT * FROM Cliente";
			ResultSet resultadoConsulta = consulta.executeQuery(query);

			while (resultadoConsulta.next()) {
				clientes.add(new Cliente(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
						resultadoConsulta.getString(3), resultadoConsulta.getString(4), resultadoConsulta.getString(5),
						resultadoConsulta.getString(6), resultadoConsulta.getString(7), resultadoConsulta.getString(8),
						resultadoConsulta.getString(9)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}

	public void agregarCliente(String idCliente, String nombre, String apellido, String usuario, String contraseña,
			String fechaNac, String fechaRegistro, String tipoSuscp, String idIdioma) {

		try {
			Statement insertarDatos = conexion.createStatement();
			String insert = "INSERT INTO cliente values ('" + idCliente + "','" + nombre + "','" + apellido + "','"
					+ usuario + "','" + contraseña + "','" + fechaNac + "','" + fechaRegistro + "','" + tipoSuscp
					+ "','" + idIdioma + "')";
			insertarDatos.executeUpdate(insert);
			insertarDatos.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	public ArrayList<Cliente> devolverClientes() {
//		return clientes;
//
//	}

	public ArrayList<Podcaster> queryPodcasters() {
		ImageIcon imagen = new ImageIcon();
		try {
			String query = "SELECT * FROM Podcaster";
			PreparedStatement consulta = conexion.prepareStatement(query);
			ResultSet resultadoConsulta = consulta.executeQuery();
			while (resultadoConsulta.next()) {
				Blob imagenBlob = resultadoConsulta.getBlob(4);
				byte[] arrayImagen = imagenBlob.getBytes(1, (int) imagenBlob.length());
				imagen = new ImageIcon(arrayImagen);
				podcasters.add(new Podcaster(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
						resultadoConsulta.getString(3), imagen, resultadoConsulta.getString(5)));
			}
			consulta.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return podcasters;
	}

	/* QUERYS DE LOS PODCASTERS */

//	public void cargarPodcasters() {
//		podcasters.clear();
//		podcasters = queryPodcasters();
//	}
//
//	public ArrayList<Podcaster> devolverPodcasters() {
//		return podcasters;
//	}

	/* QUERYS DE LOS PODCASTS POR EL PODCASTER */

//	public void cargarPodcastsDelPodcaster(String idPodcaster) {
//		podcasts.clear();
//		podcasts = queryPodcastsDelPodcaster(idPodcaster);
//	}

	public ArrayList<Podcast> queryPodcastsDelPodcaster(String idPodcaster) {
		try {
			String query = "SELECT audio.idAudio, podcast.idPodcaster, audio.nombre, audio.duracion, podcast.colaboradores, podcast.descripcion, audio.imagen, audio.tipo FROM audio JOIN podcast ON audio.idAudio = podcast.idAudio WHERE podcast.idPodcaster LIKE ?";
			PreparedStatement consulta = conexion.prepareStatement(query);
			consulta.setString(1, idPodcaster);
			ResultSet resultadoConsulta = consulta.executeQuery();

			while (resultadoConsulta.next()) {

				Blob imagenBlob = resultadoConsulta.getBlob(7);
				byte[] arrayImagen = imagenBlob.getBytes(1, (int) imagenBlob.length());
				ImageIcon imagen = new ImageIcon(arrayImagen);

				podcasts.add(new Podcast(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
						resultadoConsulta.getString(3), resultadoConsulta.getString(4), resultadoConsulta.getString(5),
						resultadoConsulta.getString(6), imagen, resultadoConsulta.getString(8)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return podcasts;
	}

//	public ArrayList<Podcast> devolverPodcasts() {
//		return podcasts;
//	}

	/* QUERYS DE LOS MUSICOS */

	public void cargarMusicos() {
		musicos.clear();
		musicos = queryMusicos();
	}

	public ArrayList<Musico> queryMusicos() {
		try {
			String query = "SELECT musico.idMusico, musico.nombreArtistico, musico.caracteristica, musico.imagen, musico.descripcion FROM musico";
			PreparedStatement consulta = conexion.prepareStatement(query);
			ResultSet resultadoConsulta = consulta.executeQuery();

			while (resultadoConsulta.next()) {

				Blob imagenBlob = resultadoConsulta.getBlob(4);
				byte[] arrayImagen = imagenBlob.getBytes(1, (int) imagenBlob.length());
				ImageIcon imagen = new ImageIcon(arrayImagen);

				musicos.add(new Musico(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
						resultadoConsulta.getString(3), imagen, resultadoConsulta.getString(5)));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return musicos;
	}

//	public ArrayList<Musico> devolverMusicos() {
//		return musicos;
//	}

	/* QUERYS DE LOS ALBUMES POR EL MUSICO */

	public void cargarAlbumesDelMusico(String idMusico) {
		albumes.clear();
		albumes = queryAlbumesDelMusico(idMusico);
	}

	public ArrayList<Album> queryAlbumesDelMusico(String idMusico) {
		ArrayList<Album> albumes = new ArrayList<Album>();
		try {

			String query = "SELECT album.idAlbum, album.titulo, album.año, album.genero, album.imagen, album.idMusico FROM musico JOIN album ON musico.idMusico = album.idMusico WHERE musico.idMusico LIKE ?";

			PreparedStatement consulta = conexion.prepareStatement(query);
			consulta.setString(1, idMusico);
			ResultSet resultadoConsulta = consulta.executeQuery();

			while (resultadoConsulta.next()) {

				Blob imagenBlob = resultadoConsulta.getBlob(5);
				byte[] arrayImagen = imagenBlob.getBytes(1, (int) imagenBlob.length());
				ImageIcon imagen = new ImageIcon(arrayImagen);

				albumes.add(new Album(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
						resultadoConsulta.getString(3), resultadoConsulta.getString(4), imagen,
						resultadoConsulta.getString(6)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return albumes;
	}

	public ArrayList<Album> devolverAlbumes() {
		return albumes;
	}

	/* QUERYS DE LOS CANCIONES POR EL ALBUM */

	public void cargarCancionesDelAlbum(String idAlbum) {
		canciones.clear();
		canciones = queryCancionesDelAlbum(idAlbum);
	}

	public ArrayList<Cancion> queryCancionesDelAlbum(String idAlbum) {
		try {
			String query = "SELECT audio.idAudio, cancion.idAlbum, audio.nombre, audio.duracion, cancion.artistaInvitado, album.imagen, audio.tipo FROM album JOIN cancion JOIN audio ON album.idAlbum = cancion.idAlbum AND cancion.idCancion = audio.idAudio WHERE album.idAlbum LIKE ?";
			PreparedStatement consulta = conexion.prepareStatement(query);
			consulta.setString(1, idAlbum);
			ResultSet resultadoConsulta = consulta.executeQuery();

			while (resultadoConsulta.next()) {

				Blob imagenBlob = resultadoConsulta.getBlob(6);
				byte[] arrayImagen = imagenBlob.getBytes(1, (int) imagenBlob.length());
				ImageIcon imagen = new ImageIcon(arrayImagen);

				canciones.add(new Cancion(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
						resultadoConsulta.getString(3), resultadoConsulta.getString(4), resultadoConsulta.getString(5),
						imagen, resultadoConsulta.getString(7)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return canciones;
	}

	public ArrayList<Cancion> devolverCanciones() {
		return canciones;
	}

	public String sacarPremium(String usuario) {
		String premium = "";
		try {
			String query = "SELECT `tipo`FROM `cliente` WHERE `usuario` = ?;";
			PreparedStatement consulta = conexion.prepareStatement(query);
			consulta.setString(1, usuario);
			ResultSet resultadoConsulta = consulta.executeQuery();

			while (resultadoConsulta.next()) {
				premium = resultadoConsulta.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(premium);
		return premium;
	}

	/* ESTADISTICAS */

	public ArrayList<CancionesFavoritas> topCancionesFavoritas() {

		try {
			Statement consulta = conexion.createStatement();
			String query = "CALL CancionesFavoritas();";
			ResultSet resultadoConsulta = consulta.executeQuery(query);

			while (resultadoConsulta.next()) {
				cancionesFav.add(new CancionesFavoritas(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
						resultadoConsulta.getString(3), resultadoConsulta.getInt(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cancionesFav;
	}

	public ArrayList<PodcastsFavoritos> topPodcastsFavoritos() {

		try {
			Statement consulta = conexion.createStatement();
			String query = "CALL PodcastsFavoritos();";
			ResultSet resultadoConsulta = consulta.executeQuery(query);

			while (resultadoConsulta.next()) {
				podcastsFav.add(new PodcastsFavoritos(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
						resultadoConsulta.getString(3), resultadoConsulta.getInt(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return podcastsFav;
	}

	public ArrayList<MasEscuchado> topMasEscuchado() {

		try {
			Statement consulta = conexion.createStatement();
			String query = "CALL topMasEscuchado();";
			ResultSet resultadoConsulta = consulta.executeQuery(query);

			while (resultadoConsulta.next()) {
				masEscuchado.add(new MasEscuchado(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
						resultadoConsulta.getString(3), resultadoConsulta.getInt(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return masEscuchado;
	}

	public ArrayList<TopPlayList> topPlayList() {

		try {
			Statement consulta = conexion.createStatement();
			String query = "CALL topPlaylist();";
			ResultSet resultadoConsulta = consulta.executeQuery(query);

			while (resultadoConsulta.next()) {
				topPlaylist.add(new TopPlayList(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
						resultadoConsulta.getString(3), resultadoConsulta.getInt(4), resultadoConsulta.getInt(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return topPlaylist;
	}

	public void agregarFavorito(String idCliente, String idAudio) {
		try {
			Statement insertarDatos = conexion.createStatement();
			String insert = "INSERT INTO gustos values ('" + idCliente + "','" + idAudio + "')";
			insertarDatos.executeUpdate(insert);
			insertarDatos.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* PLAYLIST */

//	public void cargarPlayLists(String cliente) {
//		playLists.clear();
//		playLists = queryPlayListasDelUsuario(cliente);
//	}

	public ArrayList<PlayList> queryPlayListasDelUsuario(String usuario) {

		try {
			String query = "SELECT * from playlist join cliente on playlist.idCliente = cliente.idCliente where usuario = ?";
			PreparedStatement consulta = conexion.prepareStatement(query);
			consulta.setString(1, usuario);
			ResultSet resultadoConsulta = consulta.executeQuery();

			while (resultadoConsulta.next()) {
				playLists.add(new PlayList(resultadoConsulta.getInt(1), resultadoConsulta.getString(2),
						resultadoConsulta.getString(3), resultadoConsulta.getString(4)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return playLists;
	}

//	public ArrayList<PlayList> devolverPlayLists() {
//		return playLists;
//	}

	public void añadirPlayList(String nuevaPlayList, String idCliente) {

		try {
			PreparedStatement consulta = conexion.prepareStatement("INSERT INTO `playlist`(`idList`, `titulo`, `fechaCreacion`, `idCliente`) VALUES (?,?,?,?);");
			consulta.setString(1, null);
			consulta.setString(2, nuevaPlayList);
			LocalDate fechaSinFormato = LocalDate.now();
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String fechaCreacion = formato.format(fechaSinFormato);
			consulta.setString(3, fechaCreacion);
			consulta.setString(4, idCliente);
			consulta.executeUpdate();
			consulta.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void eliminarPlayList(String playListSeleccionada) {
		try {
			Statement eliminarDatos = conexion.createStatement();
			String delete = "DELETE FROM playList WHERE titulo = '" + playListSeleccionada + "'";
			eliminarDatos.executeUpdate(delete);
			eliminarDatos.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* PERFIL DEL CLIENTE */

//	public void cargarIdCliente(String usuario) {
//		clientes.clear();
//		clientes = queryIdUsuario(usuario);
//	}
//
//	public ArrayList<Cliente> queryIdUsuario(String usuario) {
//		try {
//			String query = "SELECT * from cliente where usuario = ?";
//			PreparedStatement consulta = conexion.prepareStatement(query);
//			consulta.setString(1, usuario);
//			ResultSet resultadoConsulta = consulta.executeQuery();
//
//			while (resultadoConsulta.next()) {
//				clientes.add(new Cliente(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
//						resultadoConsulta.getString(3), resultadoConsulta.getString(4), resultadoConsulta.getString(5),
//						resultadoConsulta.getString(6), resultadoConsulta.getString(7), resultadoConsulta.getString(8),
//						resultadoConsulta.getString(9)));
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return clientes;
//	}
//
//	public ArrayList<Cliente> devolverIdUsuario() {
//		return clientes;
//	}

	/* CANCIONES DE PLAYLIST */

//	public void cargarCancion(String titulo) {
//		canciones.clear();
//		canciones = queryCancion(titulo);
//	}

//	public ArrayList<Cancion> queryCancion(String titulo) {
//		try {
//			String query = "SELECT playlist.titulo, playlist.fechaCreacion, audio.nombre, audio.tipo, audio.duracion, audio.imagen, playlistcanciones.fechaPlayListCancion from playlist join playlistCanciones on playlist.idList = playlistCanciones.idList join cancion on playlistcanciones.idCancion = cancion.idCancion join audio on audio.idaudio = cancion.idCancion where titulo = ?";
//			PreparedStatement consulta = conexion.prepareStatement(query);
//			consulta.setString(1, titulo);
//			ResultSet resultadoConsulta = consulta.executeQuery();
//
//			while (resultadoConsulta.next()) {
//				Blob imagenBlob = resultadoConsulta.getBlob(6);
//				byte[] arrayImagen = imagenBlob.getBytes(1, (int) imagenBlob.length());
//				ImageIcon imagen = new ImageIcon(arrayImagen);
//
//				canciones.add(new Cancion(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
//						resultadoConsulta.getString(3), resultadoConsulta.getString(4), resultadoConsulta.getString(5),
//						imagen, resultadoConsulta.getString(7)));
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return canciones;
//	}
//
//	public ArrayList<Cancion> devolverCancion() {
//		return canciones;
//	}

	public void eliminarCancionDePlayList(String cancionSeleccionada) {
		try {
			Statement eliminarDatos = conexion.createStatement();
			String delete = "delete \r\n"
					+ "from playlistcanciones\r\n"
					+ "where idCancion =   (select idCancion\r\n"
					+ "                from cancion\r\n"
					+ "                where idCancion =   (select idaudio\r\n"
					+ "                                    from audio\r\n"
					+ "                                    where nombre = '" + cancionSeleccionada + "'));";
			
			eliminarDatos.executeUpdate(delete);
			eliminarDatos.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int idPlaylist(String titulo) {
		int idPlaylist = 0;
		try {

			PreparedStatement consulta = conexion.prepareStatement("SELECT idList FROM `playlist` Where titulo = ?;");
			consulta.setString(1, titulo);

			ResultSet resultadoConsulta = consulta.executeQuery();

			while (resultadoConsulta.next()) {
				idPlaylist = resultadoConsulta.getInt(1);
			}

			resultadoConsulta.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return idPlaylist;
	}

	public void insertCancionEnPlaylist(String idAudio, int idPlaylist) {
		try {
			PreparedStatement consulta = conexion.prepareStatement("INSERT INTO playlistcanciones VALUES (?,?,?)");
			consulta.setInt(1, idPlaylist);
			consulta.setString(2, idAudio);
			LocalDate fechaSinFormato = LocalDate.now();
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String fechaCreacion = formato.format(fechaSinFormato);
			consulta.setString(3, fechaCreacion);
			consulta.executeUpdate();
			JOptionPane.showMessageDialog(null, "Cancion añadida!");
			// Cambia al Panel para iniciar sesión

			// Cierra la consulta
			consulta.close();

		} catch (Exception e) {
			System.out.println(e);
//			JOptionPane.showMessageDialog(null, "Campos inválidos");
		}

	}

	public String sacarIdCliente(String usuario) {
		String idCliente = null;
		try {
			String query = "SELECT `idCliente`FROM `cliente` WHERE `usuario` = ?;";
			PreparedStatement consulta = conexion.prepareStatement(query);
			consulta.setString(1, usuario);
			ResultSet resultadoConsulta = consulta.executeQuery();

			while (resultadoConsulta.next()) {
				idCliente = resultadoConsulta.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idCliente;
	}

	public int capacidadDePlaylist(int idPlaylist) {
		int capacidad = 0;
		try {
			PreparedStatement consulta = conexion
					.prepareStatement("SELECT COUNT(IDCancion) FROM `playlistcanciones` Where IDlist = ?");
			consulta.setInt(1, idPlaylist);
			ResultSet resultadoConsulta = consulta.executeQuery();
			while (resultadoConsulta.next()) {
				capacidad = resultadoConsulta.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return capacidad;

	}

	public void agregarCancionAPlaylist(int idPlaylist, String idCancion, String fechaAgregacion) {

		try {
			Statement insertarDatos = conexion.createStatement();
			String insert = "INSERT INTO playlistcanciones VALUES ('" + idPlaylist + "','" + idCancion + "','"
					+ fechaAgregacion + "')";
			insertarDatos.executeUpdate(insert);
			insertarDatos.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	public void cargarCancionesDePlaylist(String playlistSeleccionada) {
//		canciones.clear();
//		canciones = queryCancionesDePlaylist(playlistSeleccionada);
//
//	}

	public ArrayList<Cancion> queryCancionesDePlaylist(String playlistSeleccionada) {
		try {
			String query = "SELECT audio.idAudio, cancion.idAlbum, audio.nombre, audio.duracion, cancion.artistaInvitado, album.imagen, audio.tipo FROM audio JOIN cancion JOIN playlistcanciones JOIN playlist JOIN album ON audio.idAudio = cancion.idCancion AND cancion.idCancion = playlistcanciones.idCancion AND playlistcanciones.idList = playlist.idList AND cancion.idAlbum = album.idAlbum WHERE playlist.titulo LIKE ?;";
			PreparedStatement consulta = conexion.prepareStatement(query);
			consulta.setString(1, playlistSeleccionada);
			ResultSet resultadoConsulta = consulta.executeQuery();

			while (resultadoConsulta.next()) {

				Blob imagenBlob = resultadoConsulta.getBlob(6);
				byte[] arrayImagen = imagenBlob.getBytes(1, (int) imagenBlob.length());
				ImageIcon imagen = new ImageIcon(arrayImagen);

				canciones.add(new Cancion(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
						resultadoConsulta.getString(3), resultadoConsulta.getString(4), resultadoConsulta.getString(5),
						imagen, resultadoConsulta.getString(7)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return canciones;
	}


	/* INSERTS MENU ADMINISTRADOR */

	public boolean nuevoMusico(String nomArtistico, String desc, String tipo, File destino) {

		boolean añadido = false;

		String idMusico = generarIdMusico(nomArtistico);

		if (idMusico != "") {
			try {
				PreparedStatement consulta = conexion.prepareStatement(
						"INSERT INTO Musico  (idMusico, nombreArtistico, Imagen, Caracteristica, Descripcion) VALUES (?,?,?,?,?)");
				consulta.setString(1, idMusico);

				consulta.setString(2, nomArtistico);

				InputStream imagen = new FileInputStream(destino);
				consulta.setBlob(3, imagen);

				consulta.setString(4, tipo);

				consulta.setString(5, desc);

				consulta.executeUpdate();

				añadido = true;
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		return añadido;
	}

	public boolean nuevoAlbum(String nomMusico, String titulo, String fecha, String genero, File destino) {

		boolean añadido = false;
		String idMusico = "";
		for (int i = 0; i < musicos.size(); i++) {
			if (nomMusico.equals(musicos.get(i).getNombreArtistico())) {
				idMusico = musicos.get(i).getIdMusico();
			}
		}

		if (idMusico != "") {
			String idAlbum = generarIdAlbum(idMusico, nomMusico);
			try {
				PreparedStatement consulta = conexion.prepareStatement(
						"INSERT INTO album  (IDAlbum, Titulo, Año, Genero, Imagen,IDMusico) VALUES (?,?,?,?,?,?)");
				consulta.setString(1, idAlbum);

				consulta.setString(2, titulo);

				consulta.setString(3, fecha);

				consulta.setString(4, genero);

				InputStream imagen = new FileInputStream(destino);
				consulta.setBlob(5, imagen);

				consulta.setString(6, idMusico);

				consulta.executeUpdate();

				añadido = true;

			} catch (SQLException | FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "No se ha podido añadir el Album", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		return añadido;
	}

	public boolean nuevoAudio(String nombre, String nomAudio, String duracion, ImageIcon imagen, String tipo) {

		boolean añadido = false;
		String idAlbum = "";
		String idPodcaster = "";
		String idAudio = null;

		if (tipo.equalsIgnoreCase("Cancion")) {
			for (int i = 0; i < albumes.size(); i++) {
				if (nombre.equals(albumes.get(i).getTitulo())) {
					idAlbum = albumes.get(i).getIdAlbum();
				}
			}

		} else if (tipo.equalsIgnoreCase("Podcast")) {
			for (int i = 0; i < podcasts.size(); i++) {
				if (nombre.equals(podcasts.get(i).getNombre())) {
					idPodcaster = podcasts.get(i).getIdPodcaster();
				}
			}
		}

		if (idAlbum != "" || idPodcaster != "") {
			if (tipo.equalsIgnoreCase("Cancion")) {
				idAudio = generarIdCancion(idAlbum, nombre);
			} else if (tipo.equalsIgnoreCase("Podcast")) {
				idAudio = generarIdPodcast(idPodcaster, nombre);
			}
			if (idAudio != null) {
				try {
					System.out.println(idAudio + "\n" + nomAudio + "\n" + duracion + "\n" + tipo);

					PreparedStatement consulta = conexion.prepareStatement(
							"INSERT INTO Audio  (idAudio, nombre, duracion, imagen, tipo) VALUES (?,?,?,?,?)");

					consulta.setString(1, idAudio);

					consulta.setString(2, nomAudio);

					consulta.setString(3, duracion);

					// Convertir ImageIcon a BufferedImage
					BufferedImage bufferedImage = new BufferedImage(imagen.getIconWidth(), imagen.getIconHeight(),
							BufferedImage.TYPE_INT_RGB);
					bufferedImage.getGraphics().drawImage(imagen.getImage(), 0, 0, null);

					// Convertir BufferedImage a ByteArrayOutputStream
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write(bufferedImage, "jpg", baos);
					byte[] imageBytes = baos.toByteArray();

					// Convertir ByteArrayOutputStream a Blob
					Blob imageBlob = new javax.sql.rowset.serial.SerialBlob(imageBytes);

					consulta.setBlob(4, imageBlob);

					consulta.setString(5, "cancion");

					consulta.executeUpdate();

					añadido = true;

				} catch (SQLException | FileNotFoundException e) {
					e.printStackTrace();
//					JOptionPane.showMessageDialog(null, "No se ha podido añadir el Audio", "ERROR",
//							JOptionPane.ERROR_MESSAGE);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return añadido;

	}

	public boolean nuevaCancion(String nomAlbum, String invitado) {

		boolean añadido = false;

		String idAlbum = "";
		for (int i = 0; i < albumes.size(); i++) {
			if (nomAlbum.equals(albumes.get(i).getTitulo())) {
				idAlbum = albumes.get(i).getIdAlbum();
			}
		}
		String idCancion = generarIdCancion(idAlbum, nomAlbum);
		if (idAlbum != "") {
			try {
				PreparedStatement consulta = conexion
						.prepareStatement("INSERT INTO Cancion  (ArtistaInvitado, idAlbum, idCancion) VALUES (?,?,?)");
				consulta.setString(1, invitado);

				consulta.setString(2, idAlbum);

				consulta.setString(3, idCancion);

				consulta.executeUpdate();

				añadido = true;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return añadido;
	}

	public boolean nuevoPodcaster(String nomArtistico, String genero, File destino, String desc) {

		boolean añadido = false;

		String idPodcaster = generarIdPodcaster(nomArtistico);

		if (idPodcaster != "") {
			try {
				PreparedStatement consulta = conexion.prepareStatement(
						"INSERT INTO Podcaster  (idPodcaster, nombreArtistico, genero, imagen, Descripcion) VALUES (?,?,?,?,?)");
				consulta.setString(1, idPodcaster);

				consulta.setString(2, nomArtistico);

				consulta.setString(3, genero);

				InputStream imagen = new FileInputStream(destino);
				consulta.setBlob(4, imagen);

				consulta.setString(5, desc);

				consulta.executeUpdate();

				añadido = true;

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		return añadido;
	}

	public boolean nuevoPodcast(String nomPodcaster, String colabs, String desc) {
		boolean añadido = false;

		String idPodcaster = "";
		for (int i = 0; i < albumes.size(); i++) {
			if (nomPodcaster.equals(podcasters.get(i).getNombreArtistico())) {
				idPodcaster = podcasters.get(i).getIdPodcaster();
			}
		}

		String idPodcast = generarIdPodcast(idPodcaster, nomPodcaster);
		if (idPodcaster != "") {
			try {
				PreparedStatement consulta = conexion.prepareStatement(
						"INSERT INTO Podcast  (idAudio, idPodcaster, colaboradores, descripcion) VALUES (?,?,?,?)");
				consulta.setString(1, idPodcast);

				consulta.setString(2, idPodcaster);

				consulta.setString(3, colabs);

				consulta.setString(4, desc);

				consulta.executeUpdate();

				añadido = true;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return añadido;

	}

	/* GENERAR ID PARA TABLAS (pasar a gestionDeLaInformacion) */

	public String generarIdMusico(String nomArtistico) {
		String codigo = nomArtistico.toUpperCase();

		char[] musico = new char[5];

		if (codigo.contains(" ")) {
			String[] palabras = codigo.split(" ");
			palabras[0].getChars(0, 2, musico, 0);
			palabras[1].getChars(0, 3, musico, 2);
		} else {
			codigo.getChars(0, 5, musico, 0);
		}
		String idMusico = "";
		for (int i = 0; i < musico.length; i++) {
			idMusico += musico[i];
		}
		return idMusico;
	}

	public String generarIdAlbum(String idMusico, String nomArtistico) {

		int cantAlbumes = numeroDeAlbumesPorMusico(nomArtistico) + 1;

		char[] album = new char[3];

		idMusico.getChars(0, 3, album, 0);

		String idAlbum = "";
		for (int i = 0; i < album.length; i++) {
			idAlbum += album[i];
		}
		if (cantAlbumes < 10) {
			idAlbum += 0;
			idAlbum += cantAlbumes;
		} else if (cantAlbumes > 10) {
			idAlbum += cantAlbumes;
		}
		System.out.println(idAlbum);
		return idAlbum;
	}

	public String generarIdCancion(String idAlbum, String tituloAlbum) {

		int cantCanciones = numeroDeCancionesPorAlbum(tituloAlbum) + 1;
		System.out.println(cantCanciones);

		String idCancion = "";

		idCancion += idAlbum;

		idCancion += "-";
		if (cantCanciones < 10) {
			idCancion += 0;
			idCancion += cantCanciones;
		} else if (cantCanciones > 10) {
			idCancion += cantCanciones;
		}

		return idCancion;
	}

	public String generarIdPodcaster(String nomArtistico) {
		String codigo = nomArtistico.toUpperCase();

		char[] podcaster = new char[5];

		if (codigo.contains(" ")) {
			String[] palabras = codigo.split(" ");
			palabras[0].getChars(0, 2, podcaster, 0);
			palabras[1].getChars(0, 3, podcaster, 2);
		} else {
			codigo.getChars(0, 5, podcaster, 0);
		}
		String idPodcaster = "";
		for (int i = 0; i < podcaster.length; i++) {
			idPodcaster += podcaster[i];
		}
		return idPodcaster;
	}

	public String generarIdPodcast(String idPodcaster, String nombre) {

		int cantPodcasts = numeroPodcastsPorPodcaster(nombre) + 1;

		String idPodcast = "";

		char[] podcaster = new char[8];

		idPodcaster.getChars(0, 5, podcaster, 0);

		idPodcast += idPodcaster;

		idPodcast += "-";
		if (cantPodcasts < 10) {
			idPodcast += 0;
			idPodcast += cantPodcasts;
		} else if (cantPodcasts > 10) {
			idPodcast += cantPodcasts;
		}

		return idPodcast;
	}

	/* CANTIDAD DE DATOS ALMACENADOS EN LAS TABLAS */

	public int numeroDeAlbumesPorMusico(String nombreMusico) {

		int cantidad = 0;

		try {
			Statement consulta = conexion.createStatement();
			String query = "select count(al.idAlbum) from album as al join musico as mu ON al.idMusico "
					+ "= mu.idMusico where mu.nombreArtistico like '" + nombreMusico + "';";
			ResultSet resultadoConsulta = consulta.executeQuery(query);
			while (resultadoConsulta.next()) {
				cantidad = resultadoConsulta.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cantidad;
	}

	public int numeroDeCancionesPorAlbum(String tituloAlbum) {

		System.out.println("numero:" + tituloAlbum);

		int cantidad = 0;

		try {
			Statement consulta = conexion.createStatement();
			String query = "select count(can.idCancion) from cancion as can join Album as al"
					+ " ON al.idAlbum = can.idAlbum WHERE al.titulo like '" + tituloAlbum + "';";
			ResultSet resultadoConsulta = consulta.executeQuery(query);
			while (resultadoConsulta.next()) {
				cantidad = resultadoConsulta.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cantidad;
	}

	public int numeroPodcastsPorPodcaster(String nombre) {

		int cantidad = 0;

		try {
			Statement consulta = conexion.createStatement();
			String query = "select count(pod.idPodcast) from podcast as pod join Podcaster as per ON pod.idPodcaster"
					+ "= per.idPodcaster where pere.nombreArtistico like '" + nombre + "';";
			ResultSet resultadoConsulta = consulta.executeQuery(query);
			while (resultadoConsulta.next()) {
				cantidad = resultadoConsulta.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cantidad;

	}
	
	public boolean borrarMusico(String idMusico) {

		boolean borrado = false;

		try {

			PreparedStatement consulta = conexion.prepareStatement("DELETE FROM Musico WHERE idMusico = ? ;");
			consulta.setString(1, idMusico);

			consulta.executeUpdate();

			borrado = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(borrado);
		return borrado;

	}

	public boolean borrarAlbum(String tituloAlbum) {

		boolean borrado = false;

		try {
			PreparedStatement consulta = conexion.prepareStatement("DELETE FROM Album WHERE titulo = ?");
			consulta.setString(1, tituloAlbum);
			borrado = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return borrado;

	}

	public boolean borrarAudio(String nombreAudio) {

		boolean borrado = false;

		try {
			PreparedStatement consulta = conexion.prepareStatement("DELETE FROM Audio WHERE nombre = ?");
			consulta.setString(1, nombreAudio);
			borrado = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return borrado;

	}

	
	public ArrayList<Cancion> cancionesDeFavoritos(String idCliente) {
		ImageIcon imagen = new ImageIcon();
		ArrayList<Cancion> favoritos = new ArrayList<Cancion>();
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"SELECT Au.IDAudio,Ca.IDAlbum, Au.Nombre, Au.Duracion, Ca.artistaInvitado, Al.imagen, Au.Tipo FROM `gustos` Gu join audio Au on Gu.IDAudio = AU.IDAudio join Cancion Ca on Au.IDAudio = CA.idCancion join album Al on Ca.idAlbum = Al.idAlbum WHERE IDCliente = ?;");
			consulta.setString(1, idCliente);
			ResultSet resultadoConsulta = consulta.executeQuery();
			while (resultadoConsulta.next()) {
				Blob imagenBlob = resultadoConsulta.getBlob(6);
				byte[] arrayImagen = imagenBlob.getBytes(1, (int) imagenBlob.length());
				imagen = new ImageIcon(arrayImagen);
				favoritos.add(new Cancion(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
						resultadoConsulta.getString(3), resultadoConsulta.getString(4), resultadoConsulta.getString(5),
						imagen, resultadoConsulta.getString(7)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return favoritos;
	}

	public boolean borrarPodcaster(String nombrePodcaster) {

		boolean borrado = false;

		try {
			PreparedStatement consulta = conexion.prepareStatement("DELETE FROM Podcaster WHERE nombre = ?");
			consulta.setString(1, nombrePodcaster);
			borrado = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return borrado;

	}

}

