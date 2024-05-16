package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.GestionInformacion;
import modelo.Cliente;
import vista.VentanaPrincipal;

public class PanelLogin extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtContraseña;
	private JTextField txtUsuario;
	private JButton btnLogin;
	private String admin = "admin";
	private String adminContra = "1234";
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();

	/**
	 * Create the panel.
	 */


	public PanelLogin(VentanaPrincipal vp, GestionInformacion gestionInfo) {


		gestionInfo.cargarClientes();
		clientes = gestionInfo.devolverClientes();
		setSize(800, 600);
		setBackground(Color.WHITE);
		setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(Color.BLACK);
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsuario.setBounds(220, 250, 90, 20);
		add(lblUsuario);

		txtContraseña = new JPasswordField();
		txtContraseña.setBounds(330, 300, 200, 20);
		add(txtContraseña);
		txtContraseña.setColumns(10);


		JLabel lblContraseña = new JLabel("Contraseña:");
		lblContraseña.setHorizontalAlignment(SwingConstants.LEFT);
		lblContraseña.setForeground(Color.BLACK);
		lblContraseña.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblContraseña.setBounds(220, 300, 90, 20);
		add(lblContraseña);

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(330, 250, 200, 20);
		add(txtUsuario);

		JLabel lblLogin = new JLabel("INICIO DE SESION");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(Color.BLACK);
		lblLogin.setFont(new Font("Lucida Bright", Font.BOLD, 20));
		lblLogin.setBounds(270, 100, 260, 51);
		add(lblLogin);

		JButton btnRegistrarse = new JButton("REGISTRARSE");
		btnRegistrarse.setBackground(Color.BLACK);
		btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRegistrarse.setForeground(Color.WHITE);
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(2);
			}
		});
		btnRegistrarse.setBounds(90, 450, 180, 35);
		add(btnRegistrarse);


        KeyListener enterKeyListener = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnLogin.doClick();
                }
            }
        };

        txtUsuario.addKeyListener(enterKeyListener);
        txtContraseña.addKeyListener(enterKeyListener);
        
		btnLogin = new JButton("INICIAR SESIÓN");
		btnLogin.setBackground(Color.BLACK);
		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {


				boolean adminCorrecto = validarAdmin();
				boolean usuarioCorrecto = validarUsuario(gestionInfo);

				if (adminCorrecto == true) {
					JOptionPane.showMessageDialog(vp, "Bienvenido/a Admin", "Administrador",
							JOptionPane.INFORMATION_MESSAGE);
					vp.cambiarDePanel(13);
				} else {

					if (usuarioCorrecto == true) {
						JOptionPane.showMessageDialog(vp, "Bienvenido/a", "Cliente", JOptionPane.INFORMATION_MESSAGE);
						gestionInfo.guardarUsuarioCliente(txtUsuario.getText());
						vp.cambiarDePanel(3);
					} else {
						JOptionPane.showMessageDialog(vp, "Usuario o contraseña incorrectos", "Error de Login",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}

		});

		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogin.setBounds(530, 450, 180, 35);
		add(btnLogin);


		JLabel lblIconoGrande = new JLabel("");
		lblIconoGrande.setIcon(new ImageIcon("icono/spotifyicon.png"));
		lblIconoGrande.setBounds(0, 0, 100, 100);
		add(lblIconoGrande);

	}

	private boolean validarUsuario(GestionInformacion gestionInfo) {
		boolean usuarioCorrecto = false;
		gestionInfo.cargarClientes();
		clientes = gestionInfo.devolverClientes();
//		gestionBD.queryClientes();
//		clientes = gestionBD.devolverClientes();
		for (int i = 0; i < clientes.size(); i++) {
			if (txtUsuario.getText().equals(clientes.get(i).getUsuario())) {
				if (txtContraseña.getText().equals(clientes.get(i).getContrasena())) {
					gestionInfo.sacarPremium(txtUsuario.getText());
					gestionInfo.sacarIdCliente(txtUsuario.getText());
					usuarioCorrecto = true;
				} else {

				}
			} else {

			}
		}
		return usuarioCorrecto;
	}

	private boolean validarAdmin() {
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
