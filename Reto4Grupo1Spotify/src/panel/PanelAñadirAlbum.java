package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import vista.VentanaPrincipal;

public class PanelAñadirAlbum extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField txtNombre;
	private JTextField txtImagen;
	private JTextField txtDescripcion;
	
	public PanelAñadirAlbum(VentanaPrincipal v) {
		setSize(800, 600);
//		setBackground(Color.DARK_GRAY);
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
		lblNombre.setBounds(115, 185, 170, 20);
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNombre.setBounds(115, 220, 170, 25);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblTipoArtista = new JLabel("Solista o Grupo:");
		lblTipoArtista.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipoArtista.setBounds(325, 185, 170, 20);
		add(lblTipoArtista);
		
		JLabel lblImagen = new JLabel("Ruta de la imagen:");
		lblImagen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblImagen.setBounds(115, 256, 170, 20);
		add(lblImagen);
		
		txtImagen = new JTextField();
		txtImagen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtImagen.setColumns(10);
		txtImagen.setBounds(115, 286, 170, 25);
		add(txtImagen);
		
		JLabel lblDescripcion = new JLabel("Descripcion(opcional):");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescripcion.setBounds(115, 322, 170, 20);
		add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(115, 357, 170, 25);
		add(txtDescripcion);
		
		JComboBox comBoxTipoArtista = new JComboBox();
		comBoxTipoArtista.setModel(new DefaultComboBoxModel(new String[] {"SOLISTA", "GRUPO"}));
		comBoxTipoArtista.setBounds(325, 220, 90, 25);
		add(comBoxTipoArtista);
		
		
	}

}
