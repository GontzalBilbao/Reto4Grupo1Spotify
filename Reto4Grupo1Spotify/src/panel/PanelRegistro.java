package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import controlador.GestionBD;
import controlador.GestionInformacion;
import modelo.Cliente;
import vista.VentanaPrincipal;

public class PanelRegistro extends JPanel {

	public String Suscripciones[] = { "Free", "Premium" };
	public String Idiomas[] = { "ES", "EU", "EN", "FR", "DE", "CA", "GA", "AR" };
	private String condicionesContrasena1 = "- Más 8 caracteres - 1 mayúscula";
	private String condicionesContrasena2 = "- 1 especial - 1 número";
	private String fechaNac = "";
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtUsuario;
	private JTextField txtContrasena;
	private JTextField txtConfContrasena;
	private JComboBox<String> comboBoxSuscripcion;
	private JComboBox<String> comboBoxIdioma;
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	private JDateChooser dateChooser;

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelRegistro(VentanaPrincipal vp, GestionInformacion gestionInfo) {

//		generarIdCliente();
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

				validarFechaSeleccionada();
				boolean campos = validarCamposVacios();
				boolean contra = gestionInfo.validarContrasena(campos, txtContrasena.getText(),
						txtConfContrasena.getText());
				if (campos == false) {
					JOptionPane.showMessageDialog(null, "Alguno de los campos está vacío", "Error de Registro",
							JOptionPane.ERROR_MESSAGE);
				} else if (fechaNac.equals("")) {
					JOptionPane.showMessageDialog(null, "Introduzca la fecha", "Error de Registro",
							JOptionPane.ERROR_MESSAGE);
				} else if (campos == true && contra == true) {
					String idCliente = generarIdCliente(gestionInfo);
					String fechaRegistro = generarFechaRegistro();
					gestionInfo.agregarNuevoCliente(idCliente, txtNombre.getText(), txtApellido.getText(),
							txtUsuario.getText(), txtContrasena.getText(), fechaNac, fechaRegistro,
							comboBoxSuscripcion.getSelectedItem().toString(),
							comboBoxIdioma.getSelectedItem().toString());
					JOptionPane.showMessageDialog(null, "El Usuario se ha guardado con éxito", "Usuario Guardado",
							JOptionPane.INFORMATION_MESSAGE);
					vp.cambiarDePanel(1);
				}

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

		comboBoxSuscripcion = new JComboBox<String>(Suscripciones);
		comboBoxSuscripcion.setBounds(375, 339, 89, 22);
		add(comboBoxSuscripcion);

		comboBoxIdioma = new JComboBox<String>(Idiomas);
		comboBoxIdioma.setBounds(375, 381, 89, 22);
		add(comboBoxIdioma);

		JButton btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(1);
			}
		});
		btnAtras.setBounds(10, 11, 89, 23);
		add(btnAtras);

		JLabel lblFechaNac = new JLabel("Fecha de nacimiento:");
		lblFechaNac.setBounds(231, 178, 144, 14);
		add(lblFechaNac);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(375, 175, 144, 20);
		add(dateChooser);

		JLabel lblCondicionesContra1 = new JLabel(
				"<html>" + condicionesContrasena1.replaceAll("\\n", "<br>") + "</html>");
		lblCondicionesContra1.setVisible(false);
		lblCondicionesContra1.setBounds(563, 241, 115, 56);
		add(lblCondicionesContra1);

		JLabel lblCondicionesContra2 = new JLabel(
				"<html>" + condicionesContrasena2.replaceAll("\\n", "<br>") + "</html>");
		lblCondicionesContra2.setVisible(false);
		lblCondicionesContra2.setBounds(563, 275, 72, 56);
		add(lblCondicionesContra2);

		JButton btnCondicionesContra = new JButton();
		btnCondicionesContra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lblCondicionesContra1.isVisible()) {
					lblCondicionesContra1.setVisible(false);
					lblCondicionesContra2.setVisible(false);
				} else {
					lblCondicionesContra1.setVisible(true);
					lblCondicionesContra2.setVisible(true);
				}
			}
		});
		btnCondicionesContra.setBackground(null);
		btnCondicionesContra.setIcon(new ImageIcon("icono/iconoAyuda-Pequeño.png"));
		btnCondicionesContra.setBounds(529, 254, 24, 23);
		add(btnCondicionesContra);
	}

	private void validarFechaSeleccionada() {

		Date fecha = dateChooser.getDate();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		if (fecha != null) {
			fechaNac = formatoFecha.format(fecha);
		} else {
			fechaNac = "";
		}

	}

	private String generarFechaRegistro() {
		LocalDate fechaRegistro = LocalDate.now();
		String fechaRegistroString = fechaRegistro.toString();
		return fechaRegistroString;
	}

	private String generarIdCliente(GestionInformacion gestionInfo) {
		gestionInfo.cargarClientes();
		clientes = gestionInfo.devolverClientes();
		String idCliente = "C000";
		int idClientePuro = clientes.size() + 1;
		if (idClientePuro < 10) {
			idCliente += Integer.toString(idClientePuro);
		} else if (idClientePuro > 10) {
			idCliente = "C00" + Integer.toString(idClientePuro);
		} else if (idClientePuro > 100) {
			idCliente = "C0" + Integer.toString(idClientePuro);
		} else {
			idCliente = "C" + Integer.toString(idClientePuro);
		}
		for (int i = 0; i < clientes.size(); i++) {
			System.out.println(clientes.get(i).getNombre());
		}
		System.out.println(idCliente);
		return idCliente;
	}

	private boolean validarCamposVacios() {
		boolean campos = false;
		if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtUsuario.getText().isEmpty()
				|| txtContrasena.getText().isEmpty() || txtConfContrasena.getText().isEmpty()
				|| txtConfContrasena.getText().isEmpty()) {
			campos = false;
		} else {
			campos = true;
		}
		return campos;

	}
}
