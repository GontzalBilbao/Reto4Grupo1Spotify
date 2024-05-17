package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
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

import com.toedter.calendar.JDateChooser;

import controlador.GestionBD;
import modelo.Musico;
import vista.VentanaPrincipal;

public class PanelAñadirAlbum extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtTitulo;
	private JTextField txtGenero;
	private String nombreEscrito;
	private JLabel lblMostrarImagen;

	private String lanzamiento = "";
	private JDateChooser dateChooser;

	private File destino;

	private JComboBox<String> comBoxMusicos;

	private String[] arrayMusicos;

	private ArrayList<Musico> musicos = new ArrayList<Musico>();

	public PanelAñadirAlbum(VentanaPrincipal vp, GestionBD gestionBD) {
		setSize(vp.getSize());
		setLayout(null);

		gestionBD.cargarMusicos();
		musicos = gestionBD.devolverMusicos();

		cargarMusicos();

		JButton btnFinalizar = new JButton("FINALIZAR");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				validarFechaSeleccionada();

				nombreEscrito = txtTitulo.getText();
				boolean validado = validarCampos(nombreEscrito);

				if (validado != false) {
					boolean añadido = gestionBD.nuevoAlbum(comBoxMusicos.getSelectedItem().toString(), nombreEscrito,
							lanzamiento, txtGenero.getText(), destino);

					if (añadido != false) {
						JOptionPane.showMessageDialog(null, "Se ha agregado el album.");
						vp.cambiarDePanel(18);
					}
				}
			}
		});
		btnFinalizar.setBounds(560, 450, 200, 50);
		add(btnFinalizar);

		JButton btnAtras = new JButton("ATRAS");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(18);
			}
		});
		btnAtras.setBounds(650, 25, 100, 35);
		add(btnAtras);

		JLabel lblAñadirAlbum = new JLabel("AÑADIR ALBUM");
		lblAñadirAlbum.setBounds(125, 15, 500, 60);
		lblAñadirAlbum.setHorizontalAlignment(SwingConstants.CENTER);
		lblAñadirAlbum.setFont(new Font("Arial", Font.PLAIN, 40));
		add(lblAñadirAlbum);

		JLabel lblComBoxMusico = new JLabel("Musico");
		lblComBoxMusico.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblComBoxMusico.setBounds(235, 90, 200, 20);
		add(lblComBoxMusico);

		txtTitulo = new JTextField();
		txtTitulo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTitulo.setBounds(115, 220, 200, 25);
		add(txtTitulo);
		txtTitulo.setColumns(10);

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

		dateChooser = new JDateChooser();
		dateChooser.setBounds(115, 360, 200, 25);
		add(dateChooser);

		JLabel lblImagen = new JLabel("Imagen del album:");
		lblImagen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblImagen.setBounds(355, 185, 200, 20);
		add(lblImagen);

		JButton btnImagenAlbum = new JButton("Seleccionar");

		btnImagenAlbum.addActionListener(new ActionListener() {
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
		btnImagenAlbum.setBounds(355, 220, 110, 25);
		add(btnImagenAlbum);

		lblMostrarImagen = new JLabel();
		lblMostrarImagen.setBounds(560, 185, 200, 200);
		add(lblMostrarImagen);

		comBoxMusicos = new JComboBox<String>(arrayMusicos);
		comBoxMusicos.setBounds(235, 120, 200, 35);
		add(comBoxMusicos);

		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTitulo.setBounds(115, 185, 200, 20);
		add(lblTitulo);

	}

	private boolean validarCampos(String txtNombre) {
		boolean validar = false;
		if (txtNombre.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio.");
			validar = false;
		} else if (lanzamiento.equals("")) {
			JOptionPane.showMessageDialog(null, "Introduzca la fecha", "Fecha no seleccionada",
					JOptionPane.ERROR_MESSAGE);
			validar = false;
		} else if (lblMostrarImagen.getIcon() == null) {
			JOptionPane.showMessageDialog(null, "La imagen del album es obligatoria..");
			validar = false;
		} else {
			validar = true;
		}
		return validar;
	}

	private void cargarMusicos() {
		arrayMusicos = new String[musicos.size()];
		for (int i = 0; i < musicos.size(); i++) {
			arrayMusicos[i] = musicos.get(i).getNombreArtistico();
		}

	}

	private void validarFechaSeleccionada() {

		Date fecha = dateChooser.getDate();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		if (fecha != null) {
			lanzamiento = formatoFecha.format(fecha);
		} else {
			lanzamiento = "";
		}

	}
}
