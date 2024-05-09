package panel;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import controlador.GestionBD;
import vista.VentanaPrincipal;

public class PanelAñadirMusico extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtImagen;
	private JTextField txtDescripcion;

	/**
	 * Create the panel.
	 */
	public PanelAñadirMusico(VentanaPrincipal v, GestionBD gestionBD) {
		setSize(800, 600);
		setBackground(SystemColor.control);
		setLayout(null);

		JButton btnSiguiente = new JButton("SIGUIENTE");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.cambiarDePanel(16);
			}
		});
		btnSiguiente.setBounds(560, 450, 200, 50);
		add(btnSiguiente);

		JLabel lblAñadirMusico = new JLabel("AÑADIR MUSICO");
		lblAñadirMusico.setBounds(125, 15, 550, 65);
		lblAñadirMusico.setHorizontalAlignment(SwingConstants.CENTER);
		lblAñadirMusico.setFont(new Font("Sitka Subheading", Font.BOLD, 40));
		add(lblAñadirMusico);

		JButton btnAtras = new JButton("ATRAS");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.cambiarDePanel(14);
			}
		});
		btnAtras.setBounds(650, 25, 100, 35);
		add(btnAtras);

		JLabel lblNombre = new JLabel("Nombre Artistico: ");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(115, 185, 200, 20);
		add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNombre.setBounds(115, 220, 200, 25);
		add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblDescripcion = new JLabel("Descripcion(opcional):");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescripcion.setBounds(115, 255, 200, 20);
		add(lblDescripcion);

		txtDescripcion = new JTextField();
		txtDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(115, 290, 200, 25);
		add(txtDescripcion);

		JLabel lblImagen = new JLabel("Imagen del artista:");
		lblImagen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblImagen.setBounds(355, 185, 200, 20);
		add(lblImagen);

		JButton btnImagenArtista = new JButton("Seleccionar");

		btnImagenArtista.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser elegirArchivo = new JFileChooser();
				int ValorDevuelto = elegirArchivo.showOpenDialog(null);
				if (ValorDevuelto == JFileChooser.APPROVE_OPTION) {
					File selectedFile = elegirArchivo.getSelectedFile();
					try {
						guardarImagen(selectedFile);
						JOptionPane.showMessageDialog(null, "Archivo agregado exitosamente.");
					} catch (IOException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error al agregar el archivo.");
					}
				}
			}
		});
		btnImagenArtista.setBounds(355, 220, 110, 25);
		add(btnImagenArtista);

		JLabel lblTipoArtista = new JLabel("Solista o Grupo:");
		lblTipoArtista.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipoArtista.setBounds(115, 325, 170, 20);
		add(lblTipoArtista);

		JComboBox<String> comBoxTipoArtista = new JComboBox<String>();
		comBoxTipoArtista.setModel(new DefaultComboBoxModel<String>(new String[] { "SOLISTA", "GRUPO" }));
		comBoxTipoArtista.setBounds(115, 360, 90, 25);
		add(comBoxTipoArtista);

	}

	private void guardarImagen(File file) throws IOException {
		// Directorio de la carpeta donde se almacenarán los archivos
		File directory = new File("Canciones");
		if (!directory.exists()) {
			directory.mkdir(); // Crea el directorio si no existe
		}

		// Copiar el archivo seleccionado a la carpeta del proyecto
		File destination = new File(directory.getPath() + File.separator + file.getName());
		Files.copy(file.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}
}
