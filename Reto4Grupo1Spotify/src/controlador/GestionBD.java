package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.sql.PreparedStatement;

import modelo.Cliente;
import vista.VentanaPrincipal;

public class GestionBD {
	private Connection conexion;
	public ArrayList<Cliente> clientes = new ArrayList<Cliente>();

	public GestionBD() {
		iniciarConexion();
	}

	public void iniciarConexion() {
		System.out.println("Conectando...........");
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

	public ArrayList<Cliente> devolverClientes() {
		return clientes;
	}
}
