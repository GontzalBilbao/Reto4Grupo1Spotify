package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.sql.PreparedStatement;

import modelo.Artista;
import modelo.Cliente;
import modelo.Musico;
import modelo.Podcaster;
import vista.VentanaPrincipal;

public class GestionBD {
	private Connection conexion;
	public ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	public ArrayList<Musico> musicos = new ArrayList<Musico>();
	public ArrayList<Podcaster> podcasters = new ArrayList<Podcaster>();

	public GestionBD() {
		iniciarConexion();
	}

	public void iniciarConexion() {
		System.out.println("Conectando..........");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/bdreto4", "root", "");
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

	public ArrayList<Cliente> queryClientes(String usuario, String contrasena, VentanaPrincipal v) {
		try {

			String query = "SELECT usuario, contraseña FROM Cliente Where usuario = ?";

			PreparedStatement consulta = conexion.prepareStatement(query);
			consulta.setString(1, usuario);
			ResultSet resultadoConsulta = consulta.executeQuery();
			
			if (resultadoConsulta.next() && usuario.equals(resultadoConsulta.getString(1))
					&& contrasena.equals(resultadoConsulta.getString(2))) {
				JOptionPane.showMessageDialog(null, "Bienvenid@!!!!");
				v.cambiarDePanel(3);
			} else {
				JOptionPane.showMessageDialog(null, "Valores incorrectos!!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}

//	public ArrayList<Cliente> devolverClientes() {
//		return clientes;
//	}
	
	public ArrayList<Musico> queryMusico(){
		try {
			String query = "SELECT * FROM Musico";
			PreparedStatement consulta = conexion.prepareStatement(query);
			ResultSet resultadoConsulta = consulta.executeQuery();
			while (resultadoConsulta.next()){
				musicos.add(new Musico(resultadoConsulta.getString(2), resultadoConsulta.getString(2), resultadoConsulta.getString(3),
							resultadoConsulta.getString(4), resultadoConsulta.getString(5)));
			}
			consulta.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}return musicos;
	}
	
	public ArrayList<Podcaster> queryPodcasters(){
		try {
			String query = "SELECT * FROM Podcaster";
			PreparedStatement consulta = conexion.prepareStatement(query);
			ResultSet resultadoConsulta = consulta.executeQuery();
			while (resultadoConsulta.next()) {
				podcasters.add(new Podcaster(resultadoConsulta.getString(1), resultadoConsulta.getString(2), resultadoConsulta.getString(3),
						resultadoConsulta.getString(4), resultadoConsulta.getString(5)));
			}
			consulta.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}return podcasters;
	}
}
