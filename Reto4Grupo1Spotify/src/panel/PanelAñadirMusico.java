package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import controlador.GestionBD;
import vista.VentanaPrincipal;

public class PanelAñadirMusico extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JLabel lblMostrarImagen;
	private String nombreEscrito;

	private File destino;

	private String insNombre;
	private String insDesc;
	private String insTipo;

	private JComboBox<String> comBoxTipoArtista;

	public PanelAñadirMusico(VentanaPrincipal vp, GestionBD gestionBD) {
		setSize(vp.getSize());
		setBackground(SystemColor.control);
		setLayout(null);

		JButton btnFinalizar = new JButton("FINALIZAR");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombreEscrito = txtNombre.getText();
				boolean validado = validarCampos(nombreEscrito);

				if (validado != false) {
					gestionBD.nuevoMusico(txtNombre.getText(), txtDescripcion.getText(),
							comBoxTipoArtista.getSelectedItem().toString(), destino);
					JOptionPane.showMessageDialog(null, "Se ha agregado el musico.");
					vp.cambiarDePanel(14);
				}
			}
		});
		btnFinalizar.setBounds(560, 450, 200, 50);
		add(btnFinalizar);

		JLabel lblAñadirMusico = new JLabel("AÑADIR MUSICO");
		lblAñadirMusico.setBounds(125, 15, 550, 65);
		lblAñadirMusico.setHorizontalAlignment(SwingConstants.CENTER);
		lblAñadirMusico.setFont(new Font("Sitka Subheading", Font.BOLD, 40));
		add(lblAñadirMusico);

		JButton btnAtras = new JButton("ATRAS");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(14);
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

		JLabel lblTipoArtista = new JLabel("Solista o Grupo:");
		lblTipoArtista.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipoArtista.setBounds(115, 325, 170, 20);
		add(lblTipoArtista);

		comBoxTipoArtista = new JComboBox<String>();
		comBoxTipoArtista.setModel(new DefaultComboBoxModel<String>(new String[] { "SOLISTA", "GRUPO" }));
		comBoxTipoArtista.setBounds(115, 360, 90, 25);
		add(comBoxTipoArtista);

		JLabel lblImagen = new JLabel("Imagen del artista:");
		lblImagen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblImagen.setBounds(355, 185, 200, 20);
		add(lblImagen);

		JButton btnImagenArtista = new JButton("Seleccionar");

		btnImagenArtista.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Crea un JFileChooser, que sirve para elegir un archivo guardado en el sistema
				// local
				JFileChooser selectorDeImagen = new JFileChooser();
				// Se genera un filtro para que a la hora de seleccionar, te filtre que tipo de
				// extensión tendrán los archivos (Imagenes, Audios...)
				selectorDeImagen.setFileFilter(new FileNameExtensionFilter("Imágenes", "jpg", "png", "jpeg", "gif"));
				// El siguiente objeto lo que hace es devolver un valor dependiendo de si has
				// hecho click en aceptar o cancelar en el PopUp
				int seleccion = selectorDeImagen.showOpenDialog(null);
				// Si has dado a aceptar, proseguirá, si no no hará nada (0, APPROVE, 1 CANCEL,
				// 2 ERROR)
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					// Guarda el archivo seleccionado
					File imagen = selectorDeImagen.getSelectedFile();

					try {
						// Copiar el archivo seleccionado a la carpeta "imagenes" dentro del proyecto
						File carpetaImagenes = new File("imagenes");
						if (!carpetaImagenes.exists()) {
							carpetaImagenes.mkdir();
						}
						// Crea la ruta del archivo para poder mostrarla más adelante
						destino = new File("imagenes/" + imagen.getName());
						// Creamos el objeto para leer el archivo a nivel de bytes que traigamos
						InputStream leerImagen = new FileInputStream(imagen);
						// Creamos el objeto para poder escribir el archivo en la carpeta
						OutputStream escribirImagen = new FileOutputStream(destino);
						// Con lo siguiente creamos un buffer para ahorrar tiempo y procesamiento, en
						// este caso es importante ya que queremos que en el mismo "Run" se cargue el
						// archivo del sistema, y al mismo tiempo se visualice, por lo tanto necesitamos
						// que sea lo más rápido y eficiente posible
						byte[] buffer = new byte[1024];
						int longitud;
						// Mientras siga habiendo bytes por leer, seguirá escribiendo
						while ((longitud = leerImagen.read(buffer)) > 0) {
							escribirImagen.write(buffer, 0, longitud);
						}
						leerImagen.close();
						escribirImagen.close();
						// Para mostrar la imagen, sólo hará falta la ruta de la imagen, pero "destino"
						// es un String, debemos dejar claro que es una ruta (PathName)
						ImageIcon icono = new ImageIcon(destino.getPath());
						Image image = icono.getImage();
						Image nuevaImagen = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
						ImageIcon ImagenReescalada = new ImageIcon(nuevaImagen);
						lblMostrarImagen.setIcon(ImagenReescalada);
						lblMostrarImagen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		btnImagenArtista.setBounds(355, 220, 110, 25);
		add(btnImagenArtista);

		lblMostrarImagen = new JLabel();
		lblMostrarImagen.setBounds(560, 185, 200, 200);
		add(lblMostrarImagen);

	}

	private boolean validarCampos(String txtNombre) {
		boolean validar = false;
		if (txtNombre.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "El nombre del musico es obligatorio.");
			validar = false;
		} else if (lblMostrarImagen.getIcon() == null) {
			JOptionPane.showMessageDialog(null, "La imagen del musico es obligatoria..");
			validar = false;
		} else {
			validar = true;
		}
		return validar;
	}

}
