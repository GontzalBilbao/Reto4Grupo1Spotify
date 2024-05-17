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

import modelo.Podcaster;

import vista.VentanaPrincipal;

public class PanelGestionarPodcaster extends JPanel {

	private static final long serialVersionUID = 1L;

	private ArrayList<Podcaster> podcasters = new ArrayList<Podcaster>();

	private JLabel lblPodcasterActual;
	private String podcasterActual;

	public PanelGestionarPodcaster(VentanaPrincipal vp, GestionBD gestionBD, GestionInformacion gestionInfo) {
		setSize(vp.getSize());
//		setBackground(Color.DARK_GRAY);
		setLayout(null);

		gestionBD.cargarPodcasters();
		podcasters = gestionBD.devolverPodcasters();

		JPanel panelPodcasters = new JPanel();
		panelPodcasters.setBorder(new LineBorder(Color.black, 1, true));
		panelPodcasters.setLayout(new GridLayout(0, 1));
		add(panelPodcasters);
// Agregar JLabels al panel
		for (int i = 0; i < podcasters.size(); i++) {
			JPanel panelItem = new JPanel();
			panelItem.setBorder(null);
			panelItem.setLayout(new GridLayout());

// Cargar imagen
			ImageIcon imageIcon = podcasters.get(i).getImagen();
			Image image1 = imageIcon.getImage();
			Image newImage = image1.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
			ImageIcon newImageIcon = new ImageIcon(newImage);
			JLabel imageLabel = new JLabel(newImageIcon);
			panelItem.add(imageLabel);

// Agregar JLabels debajo de la imagen
			JLabel label1 = new JLabel(podcasters.get(i).getNombreArtistico()); // podcasts.get(i).getNombre()
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
					podcasterActual = ((JLabel) clickedPanel.getComponent(1)).getText();
					lblPodcasterActual.setText("Podcast Actual: " + podcasterActual);
				}
			});
// Agregar panelItem al panel principal
			panelPodcasters.add(panelItem);
		}

// Crear un JScrollPane y agregar el panel
		JScrollPane scrollPanePodcasters = new JScrollPane(panelPodcasters);
// Como se mueve muy despacio vamos a darle un poco de velocidad
		scrollPanePodcasters.getVerticalScrollBar().setUnitIncrement(30);
		scrollPanePodcasters.setSize(500, 425);
		scrollPanePodcasters.setLocation(30, 100);
		add(scrollPanePodcasters);

		JLabel lblPodcasters = new JLabel("PODCASTERS");
		lblPodcasters.setHorizontalAlignment(SwingConstants.CENTER);
		lblPodcasters.setFont(new Font("Sitka Subheading", Font.BOLD, 40));
		lblPodcasters.setBounds(30, 11, 550, 67);
		add(lblPodcasters);

		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog("Nombre del artista?");
			}
		});
		btnModificar.setBounds(560, 145, 200, 50);
		add(btnModificar);

		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (podcasterActual != null) {

					boolean borrado = gestionBD.borrarMusico(podcasterActual);
					if (borrado != false) {
						JOptionPane.showMessageDialog(null, "Podcaster borrado!");
						panelPodcasters.repaint();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecciona un podcaster para eliminarlo");
				}
				repaint();
				panelPodcasters.repaint();

			}
		});

		JButton btnAñadir = new JButton("AÑADIR");
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(21);
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

		lblPodcasterActual = new JLabel("Podcaster Actual: Ninguno");
		lblPodcasterActual.setBounds(560, 120, 200, 20);
		add(lblPodcasterActual);

	}

}
