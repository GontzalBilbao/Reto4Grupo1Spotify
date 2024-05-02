package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.GestionBD;
import controlador.GestionInformacion;
import modelo.Cliente;
import vista.VentanaPrincipal;

public class PanelRegistro extends JPanel {

	public String Suscripciones[] = { "Free", "Premium" };
	public String Idiomas[] = { "ES", "EU", "EN", "FR", "DE", "CA", "GA", "AR" };
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtUsuario;
	private JTextField txtContrasena;
	private JTextField txtConfContrasena;
	private JTextField txtFechaNac;
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelRegistro(VentanaPrincipal vp, GestionBD gestionBD, GestionInformacion gestionInfo) {

//		gestionBD.queryClientes();
//		clientes = gestionBD.devolverClientes();
//		for (int i = 0; i < clientes.size(); i++) {
//			System.out.println(clientes.get(i).getNombre());
//		}
		
		setSize(800, 600);
		setBackground(Color.DARK_GRAY);
		setLayout(null);

		JLabel lblTituloRegistro = new JLabel("REGISTRARSE");
		lblTituloRegistro.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblTituloRegistro.setBounds(276, 11, 201, 43);
		add(lblTituloRegistro);

		txtNombre = new JTextField();
		txtNombre.setBounds(375, 94, 144, 20);
		add(txtNombre);
		txtNombre.setColumns(10);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				gestionInfo.validarRegistro(vp);
			}
			
		});
		btnGuardar.setBounds(685, 527, 89, 23);
		add(btnGuardar);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(231, 97, 144, 14);
		add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(231, 136, 144, 14);
		add(lblApellido);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(231, 217, 144, 14);
		add(lblUsuario);

		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setBounds(231, 258, 144, 14);
		add(lblContrasena);

		JLabel lblConfContrasena = new JLabel("Confirmar contraseña:");
		lblConfContrasena.setBounds(231, 300, 144, 14);
		add(lblConfContrasena);

		JLabel lblSuscripcion = new JLabel("Suscripción:");
		lblSuscripcion.setBounds(231, 343, 144, 14);
		add(lblSuscripcion);

		JLabel lblIdioma = new JLabel("Idioma:");
		lblIdioma.setBounds(231, 385, 144, 14);
		add(lblIdioma);

		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(375, 133, 144, 20);
		add(txtApellido);

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(375, 214, 144, 20);
		add(txtUsuario);

		txtContrasena = new JTextField();
		txtContrasena.setColumns(10);
		txtContrasena.setBounds(375, 255, 144, 20);
		add(txtContrasena);

		txtConfContrasena = new JTextField();
		txtConfContrasena.setColumns(10);
		txtConfContrasena.setBounds(375, 297, 144, 20);
		add(txtConfContrasena);

		JComboBox comboBoxSuscripcion = new JComboBox(Suscripciones);
		comboBoxSuscripcion.setBounds(375, 339, 89, 22);
		add(comboBoxSuscripcion);

		JComboBox comboBoxIdioma = new JComboBox(Idiomas);
		comboBoxIdioma.setBounds(375, 381, 89, 22);
		add(comboBoxIdioma);

		JButton btnAtras = new JButton("Atrás");
		btnAtras.setBounds(10, 11, 89, 23);
		add(btnAtras);

		JLabel lblFechaNac = new JLabel("Fecha de nacimiento:");
		lblFechaNac.setBounds(231, 178, 144, 14);
		add(lblFechaNac);

		txtFechaNac = new JTextField();
		txtFechaNac.setColumns(10);
		txtFechaNac.setBounds(375, 175, 144, 20);
		add(txtFechaNac);
	}

}
