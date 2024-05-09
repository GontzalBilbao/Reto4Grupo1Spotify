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

import modelo.CancionesFavoritas;
import modelo.Cliente;
import modelo.MasEscuchado;
import modelo.Podcast;
import modelo.Podcaster;
import modelo.PodcastsFavoritos;
import modelo.TopPlayList;

public class GestionBD {
	private Connection conexion;
	public ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	public ArrayList<Podcaster> podcasters = new ArrayList<Podcaster>();
	public ArrayList<Podcast> podcasts = new ArrayList<Podcast>();

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
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/bdreto4", "root", "");
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

	public ArrayList<Cliente> devolverClientes() {
		return clientes;
	}

	public ArrayList<Podcaster> queryPodcasters(String nombreArtistico) {
		try {
			String query = "SELECT * FROM Podcaster WHERE NombreArtistico LIKE ?";
			PreparedStatement consulta = conexion.prepareStatement(query);
			consulta.setString(1, nombreArtistico);
			ResultSet resultadoConsulta = consulta.executeQuery();

			while (resultadoConsulta.next()) {

				Blob imagenBlob = resultadoConsulta.getBlob(4);
				byte[] arrayImagen = imagenBlob.getBytes(1, (int) imagenBlob.length());
				ImageIcon imagen = new ImageIcon(arrayImagen);

				podcasters.add(new Podcaster(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
						resultadoConsulta.getString(3), imagen, resultadoConsulta.getString(5)));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return podcasters;
	}

	public ArrayList<Podcaster> devolverPodcasters() {
		return podcasters;
	}

	public ArrayList<Podcaster> queryPodcastsDelPodcaster(String idPodcaster) {
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
		return podcasters;
	}

	public ArrayList<Podcast> devolverPodcasts() {
		return podcasts;
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

//	SELECT audio.idAudio, podcast.idPodcaster, audio.nombre, audio.duracion, podcast.colaboradores, podcast.descripcion, audio.imagen, audio.tipo
//	FROM audio JOIN podcast audio.idAudio = podcast.idAudio
//	WHERE podcast.idPodcaster 'NSN03';
}