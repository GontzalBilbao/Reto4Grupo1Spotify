package controlador;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import modelo.Album;
import modelo.Audio;
import modelo.Cancion;
import modelo.Cliente;
import modelo.Musico;
import modelo.Podcast;
import modelo.Podcaster;

public class GestionBD {
	private Connection conexion;
	public ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	public ArrayList<Audio> audios = new ArrayList<Audio>();
	public ArrayList<Album> album = new ArrayList<Album>();
	public ArrayList<Podcaster> podcasters = new ArrayList<Podcaster>();
	public ArrayList<Podcast> podcasts = new ArrayList<Podcast>();
	public ArrayList<Musico> musicos = new ArrayList<Musico>();
	public ArrayList<Album> albumes = new ArrayList<Album>();
	public ArrayList<Cancion> canciones = new ArrayList<Cancion>();

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

	public void cargarClientes() {
		clientes.clear();
		clientes = queryClientes();
	}

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

	public String queryTipoDePerfil(String usuario) {
		String premiun = null;
		try {
			PreparedStatement consulta = conexion.prepareStatement("SELECT tipo FROM cliente WHERE usuario = ?");
			consulta.setString(1, usuario);
			ResultSet resultadoConsulta = consulta.executeQuery();
			while (resultadoConsulta.next()) {
				premiun = resultadoConsulta.getString(1);
			}
			consulta.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return premiun;
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

	public ArrayList<Cliente> devolverClientes() {
		return clientes;
	}

	public ArrayList<Musico> queryMusico() {
		ImageIcon imagen = new ImageIcon();
		ArrayList<Musico> musicos = new ArrayList<Musico>();
		try {
			String query = "SELECT * FROM Musico";
			PreparedStatement consulta = conexion.prepareStatement(query);
			ResultSet resultadoConsulta = consulta.executeQuery();
			while (resultadoConsulta.next()) {
				Blob imagenBlob = resultadoConsulta.getBlob(3);
				byte[] arrayImagen = imagenBlob.getBytes(1, (int) imagenBlob.length());
				imagen = new ImageIcon(arrayImagen);
				musicos.add(new Musico(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
						resultadoConsulta.getString(4), imagen, resultadoConsulta.getString(5)));
			}
			consulta.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return musicos;
	}

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

	public ArrayList<Audio> queryAudioCancion(String nombre) {
		ImageIcon imagen = new ImageIcon();
		ArrayList<Audio> audios = new ArrayList<Audio>();
		try {
			String query = "SELECT * FROM `audio` join cancion on audio.idAudio = cancion.idCancion join album on cancion.idAlbum = album.idAlbum Where album.titulo = ?";
			PreparedStatement consulta = conexion.prepareStatement(query);
			consulta.setString(1, nombre);
			ResultSet resultadoConsulta = consulta.executeQuery();
			while (resultadoConsulta.next()) {
				Blob imagenBlob = resultadoConsulta.getBlob(4);
				byte[] arrayImagen = imagenBlob.getBytes(1, (int) imagenBlob.length());
				imagen = new ImageIcon(arrayImagen);
				audios.add(new Audio(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
						resultadoConsulta.getString(3), imagen, resultadoConsulta.getString(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return audios;
	}

	public ArrayList<Album> queryAlbum() {
		ImageIcon imagen = new ImageIcon();
		ArrayList<Album> album = new ArrayList<Album>();
		try {
			String query = "SELECT * FROM album";
			PreparedStatement consulta = conexion.prepareStatement(query);
			ResultSet resultadoConsulta = consulta.executeQuery();
			while (resultadoConsulta.next()) {
				Blob imagenBlob = resultadoConsulta.getBlob(5);
				byte[] arrayImagen = imagenBlob.getBytes(1, (int) imagenBlob.length());
				imagen = new ImageIcon(arrayImagen);
				album.add(new Album(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
						resultadoConsulta.getString(3), resultadoConsulta.getString(4), imagen,
						resultadoConsulta.getString(6)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return album;
	}

	public ArrayList<Audio> queryAudioPodcast(String idAudio) {
		ImageIcon imagen = new ImageIcon();
		ArrayList<Audio> audios = new ArrayList<Audio>();
		try {
			String query = "SELECT * FROM audio WHERE idAudio = ?";
			PreparedStatement consulta = conexion.prepareStatement(query);
			ResultSet resultadoConsulta = consulta.executeQuery();
			while (resultadoConsulta.next()) {
				Blob imagenBlob = resultadoConsulta.getBlob(4);
				byte[] arrayImagen = imagenBlob.getBytes(1, (int) imagenBlob.length());
				imagen = new ImageIcon(arrayImagen);
				audios.add(new Audio(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
						resultadoConsulta.getString(3), imagen, resultadoConsulta.getString(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return audios;
	}

	public void cargarPodcasters() {
		podcasters.clear();
		podcasters = queryPodcasters();
	}

	public ArrayList<Podcaster> devolverPodcasters() {
		return podcasters;
	}

	public void cargarPodcastsDelPodcaster(String idPodcaster) {
		podcasts.clear();
		podcasts = queryPodcastsDelPodcaster(idPodcaster);
	}

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

	public ArrayList<Podcast> devolverPodcasts() {
		return podcasts;
	}

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

	public ArrayList<Musico> devolverMusicos() {
		return musicos;
	}

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

	public String sacarPremiun(String usuario) {
		String premiun = null;
		try {
			String query = "SELECT `tipo`FROM `cliente` WHERE `usuario` = ?;";
			PreparedStatement consulta = conexion.prepareStatement(query);
			consulta.setString(1, usuario);
			ResultSet resultadoConsulta = consulta.executeQuery();

			while (resultadoConsulta.next()) {
				premiun = resultadoConsulta.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return premiun;
	}
	
	
	
	
//	SELECT audio.idAudio, podcast.idPodcaster, audio.nombre, audio.duracion, podcast.colaboradores, podcast.descripcion, audio.imagen, audio.tipo
//	FROM audio JOIN podcast audio.idAudio = podcast.idAudio
//	WHERE podcast.idPodcaster 'NSN03';

}
