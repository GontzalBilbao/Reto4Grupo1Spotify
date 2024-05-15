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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.GestionBD;
import controlador.GestionInformacion;
import modelo.Podcaster;
import vista.VentanaPrincipal;

public class PanelAñadirPodcast extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField txtNombre;
	private JTextField txtGenero;
	private JTextField txtDescripcion;
	private JLabel lblMostrarImagen;

	private JComboBox<String> comBoxPodcasters;

	private String[] arrayPodcasters;

	private ArrayList<Podcaster> podcasters = new ArrayList<Podcaster>();

	public PanelAñadirPodcast(VentanaPrincipal vp, GestionInformacion gestionInfo) {
		setSize(vp.getSize());
//		setBackground(Color.DARK_GRAY);
		setLayout(null);

		gestionInfo.cargarPodcasters();
		podcasters = gestionInfo.devolverPodcasters();

		cargarPodcasters();

		JButton btnFinalizar = new JButton("FINALIZAR");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(22);
			}
		});
		btnFinalizar.setBounds(560, 450, 200, 50);
		add(btnFinalizar);

		JLabel lblAñadirMusico = new JLabel("AÑADIR PODCASTER");
		lblAñadirMusico.setBounds(125, 15, 550, 65);
		lblAñadirMusico.setHorizontalAlignment(SwingConstants.CENTER);
		lblAñadirMusico.setFont(new Font("Sitka Subheading", Font.BOLD, 40));
		add(lblAñadirMusico);

		JButton btnAtras = new JButton("ATRAS");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(22);
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

		JLabel lblGenero = new JLabel("Descripcion:");
		lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGenero.setBounds(115, 255, 200, 20);
		add(lblGenero);

		txtDescripcion = new JTextField();
		txtDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(115, 290, 200, 25);
		add(txtDescripcion);

		JLabel lblTipoArtista = new JLabel("Genero:");
		lblTipoArtista.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipoArtista.setBounds(115, 325, 170, 20);
		add(lblTipoArtista);

		txtGenero = new JTextField();
		txtGenero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtGenero.setColumns(10);
		txtGenero.setBounds(115, 360, 100, 25);
		add(txtGenero);

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
						if (comBoxPodcasters.getSelectedItem().toString().equals(podcasters.get(i).getNombreArtistico())) {
							Image image = podcasters.get(i).getImagen().getImage();
							Image nuevaImagen = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
							ImageIcon ImagenReescalada = new ImageIcon(nuevaImagen);
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

}
