package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controlador.GestionBD;
import controlador.GestionInformacion;
import modelo.Musico;
import vista.VentanaPrincipal;

public class PanelGestionarMusico extends JPanel {

	private static final long serialVersionUID = 1L;

	private ArrayList<Musico> musicos = new ArrayList<Musico>();

	private JLabel lblMusicoActual;
	private String musicoActual;

	public PanelGestionarMusico(VentanaPrincipal vp, GestionBD gestionBD, GestionInformacion gestionInfo) {
		setSize(800, 600);
//		setBackground(Color.DARK_GRAY);
		setLayout(null);

		gestionBD.cargarMusicos();
		musicos = gestionBD.devolverMusicos();

		JPanel panelMusicos = new JPanel();
		panelMusicos.setBorder(new LineBorder(Color.black, 1, true));

		add(panelMusicos);

//		panel.setBackground(new java.awt.Color(0, 0, 0, 0));
//		panel.setOpaque(false);
		panelMusicos.setLayout(new GridLayout(0, 1));

// Agregar JLabels al panel
		for (int i = 0; i < musicos.size(); i++) {
			JPanel panelItem = new JPanel();
			panelItem.setBorder(null);
//			panelItem.setOpaque(false);
//			panelItem.setBackground(new java.awt.Color(0, 0, 0, 0));
			panelItem.setLayout(new GridLayout());

// Cargar imagen
			ImageIcon imageIcon = musicos.get(i).getImagen();
			Image image1 = imageIcon.getImage();
			Image newImage = image1.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
			ImageIcon newImageIcon = new ImageIcon(newImage);
			JLabel imageLabel = new JLabel(newImageIcon);
			panelItem.add(imageLabel);

// Agregar JLabels debajo de la imagen
			JLabel label1 = new JLabel(musicos.get(i).getNombreArtistico()); // podcasts.get(i).getNombre()
//			JLabel label2 = new JLabel("Autor: " + i);
			panelItem.add(label1);
//			panelItem.add(label2);
// Le damos nombre para identificarlo
			panelItem.setName("panel " + i);
// Añadir un borde al panelItem
			panelItem.setBorder(BorderFactory.createLineBorder(Color.BLACK));
// Añadir escuchador al panel
			panelItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JPanel clickedPanel = (JPanel) e.getSource();
					musicoActual = ((JLabel) clickedPanel.getComponent(1)).getText();
					lblMusicoActual.setText("Musico Actual: " + musicoActual);
				}
			});
// Agregar panelItem al panel principal
			panelMusicos.add(panelItem);
		}

// Crear un JScrollPane y agregar el panel
		JScrollPane scrollPaneMusicos = new JScrollPane(panelMusicos);
// Como se mueve muy despacio vamos a darle un poco de velocidad
		scrollPaneMusicos.getVerticalScrollBar().setUnitIncrement(30);
		scrollPaneMusicos.setSize(500, 425);
		scrollPaneMusicos.setLocation(30, 100);
		add(scrollPaneMusicos);

		JLabel lblMusicos = new JLabel("MUSICOS");
		lblMusicos.setHorizontalAlignment(SwingConstants.CENTER);
		lblMusicos.setFont(new Font("Sitka Subheading", Font.BOLD, 40));
		lblMusicos.setBounds(30, 11, 550, 67);
		add(lblMusicos);

		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog("Nombre del artista?");
			}
		});
		btnModificar.setBounds(560, 145, 200, 50);
		add(btnModificar);

		JButton btnBorrar = new JButton("BORRAR");
		btnBorrar.setBounds(560, 435, 200, 50);

		add(btnBorrar);

		JButton btnAñadir = new JButton("AÑADIR");
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(15);
			}
		});
		btnAñadir.setBounds(560, 290, 200, 50);
		add(btnAñadir);

		JButton btnAtras = new JButton("ATRAS");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(13);
			}
		});
		btnAtras.setBounds(650, 25, 100, 35);
		add(btnAtras);

		lblMusicoActual = new JLabel("Musico Actial: Ninguno");
		lblMusicoActual.setBounds(560, 120, 200, 20);
		add(lblMusicoActual);
	}
}
