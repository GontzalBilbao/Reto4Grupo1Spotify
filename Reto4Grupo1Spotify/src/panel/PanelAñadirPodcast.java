package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.GestionBD;
import modelo.Podcaster;
import vista.VentanaPrincipal;

public class PanelAñadirPodcast extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField txtNombre;
	private JTextField txtDuracion;
	private JTextField txtColabs;
	private JTextField txtDescripcion;
	private JLabel lblMostrarImagen;

	private String nombreEscrito;

	private String tipo = "podcast";

	private ImageIcon ImagenReescalada;

	private JComboBox<String> comBoxPodcasters;

	private String[] arrayPodcasters;

	private ArrayList<Podcaster> podcasters = new ArrayList<Podcaster>();

	public PanelAñadirPodcast(VentanaPrincipal vp, GestionBD gestionBD) {
		setSize(800, 600);
//		setBackground(Color.DARK_GRAY);
		setLayout(null);

		gestionBD.cargarPodcasters();
		podcasters = gestionBD.devolverPodcasters();

		cargarPodcasters();

		JButton btnFinalizar = new JButton("FINALIZAR");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				nombreEscrito = txtNombre.getText();

				boolean validado = validarCampos(nombreEscrito);

				if (validado != false) {

					boolean audioAñadido = gestionBD.nuevoAudio(comBoxPodcasters.getSelectedItem().toString(),
							nombreEscrito, txtDuracion.getText(), ImagenReescalada, tipo);

					if (audioAñadido != false) {
						System.out.println("Audio añadido");
						boolean podcastAñadida = gestionBD.nuevoPodcast(comBoxPodcasters.getSelectedItem().toString(),
								txtColabs.getText(), txtDescripcion.getText());

						if (podcastAñadida != false) {
							JOptionPane.showMessageDialog(null, "Se ha agregado la cancion.");
						}
					}
				}
			}
		});
		btnFinalizar.setBounds(560, 450, 200, 50);
		add(btnFinalizar);

		JLabel lblAñadirMusico = new JLabel("AÑADIR PODCAST");
		lblAñadirMusico.setBounds(125, 15, 550, 65);
		lblAñadirMusico.setHorizontalAlignment(SwingConstants.CENTER);
		lblAñadirMusico.setFont(new Font("Arial", Font.PLAIN, 40));
		add(lblAñadirMusico);

		JButton btnAtras = new JButton("ATRAS");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(22);
			}
		});
		btnAtras.setBounds(650, 25, 100, 35);
		add(btnAtras);

		JLabel lblNombre = new JLabel("Nombre Podcast:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(115, 185, 200, 20);
		add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNombre.setBounds(115, 220, 200, 25);
		add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblDuracion = new JLabel("Duracion (m:s):");
		lblDuracion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDuracion.setBounds(115, 255, 200, 20);
		add(lblDuracion);

		txtDuracion = new JTextField();
		txtDuracion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDuracion.setColumns(10);
		txtDuracion.setBounds(115, 290, 200, 25);
		add(txtDuracion);

		JLabel lblColabs = new JLabel("Colaboradores:");
		lblColabs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblColabs.setBounds(115, 325, 200, 20);
		add(lblColabs);

		txtColabs = new JTextField();
		txtColabs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtColabs.setBounds(115, 360, 200, 25);
		add(txtColabs);
		txtColabs.setColumns(10);

		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescripcion.setBounds(115, 395, 200, 20);
		add(lblDescripcion);

		txtDescripcion = new JTextField();
		txtDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(115, 430, 200, 25);
		add(txtDescripcion);

		lblMostrarImagen = new JLabel();
		lblMostrarImagen.setBounds(560, 185, 200, 200);
		add(lblMostrarImagen);

		comBoxPodcasters = new JComboBox<String>(arrayPodcasters);
		comBoxPodcasters.setBounds(235, 120, 200, 35);
		add(comBoxPodcasters);

		comBoxPodcasters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < podcasters.size(); i++) {
					if (comBoxPodcasters.getSelectedItem() != null) {
						if (comBoxPodcasters.getSelectedItem().toString()
								.equals(podcasters.get(i).getNombreArtistico())) {
							Image image = podcasters.get(i).getImagen().getImage();
							Image nuevaImagen = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
							ImagenReescalada = new ImageIcon(nuevaImagen);
							lblMostrarImagen.setIcon(ImagenReescalada);
							lblMostrarImagen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
						}
					}
				}
			}
		});

		JLabel lblComBoxPodcaster = new JLabel("Podcaster:");
		lblComBoxPodcaster.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblComBoxPodcaster.setBounds(235, 90, 200, 20);
		add(lblComBoxPodcaster);

	}

	private void cargarPodcasters() {
		arrayPodcasters = new String[podcasters.size()];
		for (int i = 0; i < podcasters.size(); i++) {
			arrayPodcasters[i] = podcasters.get(i).getNombreArtistico();
		}

	}

	private boolean validarCampos(String txtNombre) {
		boolean validar = false;
		if (txtNombre.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio.");
			validar = false;
		} else if (lblMostrarImagen.getIcon() == null) {
			JOptionPane.showMessageDialog(null, "Selecciona un podcaster.");
			validar = false;
		} else {
			validar = true;
		}
		return validar;
	}

}
