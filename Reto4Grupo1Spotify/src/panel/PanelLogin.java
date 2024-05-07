package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.GestionBD;
import controlador.GestionInformacion;
import modelo.Cliente;
import vista.VentanaPrincipal;

public class PanelLogin extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtContraseña;
	private JTextField txtUsuario;
	private JButton btnLogin;
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	private String admin = "admin";
	private String adminContra = "1234";

	/**
	 * Create the panel.
	 */

	public PanelLogin(VentanaPrincipal v, GestionBD gestionBD, GestionInformacion gestionInfo) {
		setSize(800, 600);
		setBackground(Color.DARK_GRAY);
		setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(Color.GREEN);
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsuario.setBounds(220, 250, 90, 20);
		add(lblUsuario);

		txtContraseña = new JTextField();
		txtContraseña.setBounds(330, 300, 200, 20);
		add(txtContraseña);
		txtContraseña.setColumns(10);

		JLabel lblContraseña = new JLabel("Contraseña:");
		lblContraseña.setHorizontalAlignment(SwingConstants.LEFT);
		lblContraseña.setForeground(Color.GREEN);
		lblContraseña.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblContraseña.setBounds(220, 300, 90, 20);
		add(lblContraseña);

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(330, 250, 200, 20);
		add(txtUsuario);

		JLabel lblLogin = new JLabel("INICIO DE SESION");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(Color.GREEN);
		lblLogin.setFont(new Font("Lucida Bright", Font.BOLD, 20));
		lblLogin.setBounds(270, 100, 260, 51);
		add(lblLogin);

		JButton btnRegistrarse = new JButton("REGISTRARSE");
		btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRegistrarse.setForeground(Color.BLACK);
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.cambiarDePanel(2);
			}
		});
		btnRegistrarse.setBounds(90, 450, 180, 35);
		add(btnRegistrarse);

		btnLogin = new JButton("INICIAR SESIÓN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean adminCorrecto = validarAdmin(gestionBD);
				boolean usuarioCorrecto = validarUsuario(gestionBD);
				
				if (adminCorrecto == true) {
					JOptionPane.showMessageDialog(null, "Bienvenido/a Admin", "Administrador",
							JOptionPane.INFORMATION_MESSAGE);
					v.cambiarDePanel(13);
				} else {
					
					if (usuarioCorrecto == true) {
						JOptionPane.showMessageDialog(null, "Bienvenido/a", "Cliente", JOptionPane.INFORMATION_MESSAGE);
						v.cambiarDePanel(3);
					} else {
						JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error de Login",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogin.setBounds(530, 450, 180, 35);
		add(btnLogin);

		JLabel lblIconoGrande = new JLabel("");
		lblIconoGrande.setIcon(new ImageIcon("icono/spotifyicon.png"));
		lblIconoGrande.setBounds(0, 0, 100, 100);
		add(lblIconoGrande);

	}

	private boolean validarUsuario(GestionBD gestionBD) {
		boolean usuarioCorrecto = false;
		gestionBD.queryClientes();
		clientes = gestionBD.devolverClientes();
		for (int i = 0; i < clientes.size(); i++) {
			if (txtUsuario.getText().equals(clientes.get(i).getUsuario())) {
				if (txtContraseña.getText().equals(clientes.get(i).getContrasena())) {
					usuarioCorrecto = true;
				} else {

				}
			} else {

			}
		}
		return usuarioCorrecto;
	}

	private boolean validarAdmin(GestionBD gestionBD) {
		boolean adminCorrecto = false;
		String usuario = txtUsuario.getText();
		String contra = txtContraseña.getText();
		if (usuario.equals(admin) && contra.equals(adminContra)) {
			adminCorrecto = true;
		} else {

		}
		return adminCorrecto;
	}
}
