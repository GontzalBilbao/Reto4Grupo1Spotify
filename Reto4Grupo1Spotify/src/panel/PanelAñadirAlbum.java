package panel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.GestionBD;
import modelo.Musico;
import vista.VentanaPrincipal;

public class PanelAñadirAlbum extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField txtNombre;
	private JTextField txtGenero;
	private JTextField txtLanzamiento;
	private JComboBox<String> comBoxMusicos;

	private String[] arrayMusicos;

	private ArrayList<Musico> musicos = new ArrayList<Musico>();

	public PanelAñadirAlbum(VentanaPrincipal v, GestionBD gestionBD) {
		setSize(800, 600);
//		setBackground(Color.DARK_GRAY);
		setLayout(null);

		gestionBD.cargarMusicos();
		musicos = gestionBD.devolverMusicos();

		cargarArtistas();

		JButton btnFinalizar = new JButton("FINALIZAR");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnFinalizar.setBounds(560, 450, 200, 50);
		add(btnFinalizar);

		JButton btnAtras = new JButton("ATRAS");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.cambiarDePanel(18);
			}
		});
		btnAtras.setBounds(650, 25, 100, 35);
		add(btnAtras);

		JLabel lblAñadirAlbum = new JLabel("AÑADIR ALBUM");
		lblAñadirAlbum.setBounds(125, 15, 500, 60);
		lblAñadirAlbum.setHorizontalAlignment(SwingConstants.CENTER);
		lblAñadirAlbum.setFont(new Font("Sitka Subheading", Font.BOLD, 40));
		add(lblAñadirAlbum);

		JLabel lblNombre = new JLabel("Nombre del album: ");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(115, 185, 200, 20);
		add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNombre.setBounds(115, 220, 200, 25);
		add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblGenero = new JLabel("Genero musical:");
		lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGenero.setBounds(115, 255, 200, 20);
		add(lblGenero);

		txtGenero = new JTextField();
		txtGenero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtGenero.setColumns(10);
		txtGenero.setBounds(115, 290, 200, 25);
		add(txtGenero);

		JLabel lblLanzamiento = new JLabel("Año de lanzamiento del album:");
		lblLanzamiento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLanzamiento.setBounds(115, 325, 200, 20);
		add(lblLanzamiento);

		txtLanzamiento = new JTextField();
		txtLanzamiento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtLanzamiento.setColumns(10);
		txtLanzamiento.setBounds(115, 360, 200, 25);
		add(txtLanzamiento);

		JLabel lblImagen = new JLabel("Imagen del album:");
		lblImagen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblImagen.setBounds(355, 185, 200, 20);
		add(lblImagen);

		JButton btnImagenAlbum = new JButton("Seleccionar");

		btnImagenAlbum.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser elegirArchivo = new JFileChooser();
				int ValorDevuelto = elegirArchivo.showOpenDialog(null);
				if (ValorDevuelto == JFileChooser.APPROVE_OPTION) {
					File archivoSeleccionado = elegirArchivo.getSelectedFile();
					try {
						guardarImagen(archivoSeleccionado);
						JOptionPane.showMessageDialog(null, "Archivo agregado exitosamente.");
					} catch (IOException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error al agregar el archivo.");
					}
				}
			}
		});
		btnImagenAlbum.setBounds(355, 220, 110, 25);
		add(btnImagenAlbum);

		JLabel lblMostrarImagen = new JLabel();
		lblMostrarImagen.setBounds(560, 185, 200, 200);
		add(lblMostrarImagen);

		comBoxMusicos = new JComboBox<String>(arrayMusicos);
		comBoxMusicos.setBounds(235, 100, 200, 35);
		add(comBoxMusicos);

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

	private void cargarArtistas() {
		arrayMusicos = new String[musicos.size()];
		for (int i = 0; i < musicos.size(); i++) {
			arrayMusicos[i] = musicos.get(i).getNombreArtistico();
		}

	}

}
