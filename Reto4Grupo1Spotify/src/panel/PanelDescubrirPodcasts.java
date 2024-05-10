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
<<<<<<< HEAD
=======

>>>>>>> refs/remotes/origin/Dani
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
<<<<<<< HEAD
=======

>>>>>>> refs/remotes/origin/Dani
import controlador.GestionBD;
import controlador.GestionInformacion;
import modelo.Podcaster;
import vista.VentanaPrincipal;

public class PanelDescubrirPodcasts extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Podcaster> podcasters = new ArrayList<Podcaster>();
<<<<<<< HEAD

=======
	
>>>>>>> refs/remotes/origin/Dani
	/**
	 * Create the panel.
	 */
<<<<<<< HEAD

=======
>>>>>>> refs/remotes/origin/Dani
	public PanelDescubrirPodcasts(VentanaPrincipal vp, GestionBD gestionBD, GestionInformacion gestionInfo) {
<<<<<<< HEAD

=======
>>>>>>> refs/remotes/origin/Dani
		setSize(800, 600);
		setBackground(Color.WHITE);
		setLayout(null);
<<<<<<< HEAD

=======
		
>>>>>>> refs/remotes/origin/Dani
		gestionBD.cargarPodcasters();
		podcasters = gestionBD.devolverPodcasters();
		for (int i = 0; i < podcasters.size(); i++) {
//			System.out.println(podcasters.get(i).getDescripcion());
		}

		// Crear un panel para contener los JLabels
				JPanel panel = new JPanel();
//				panel.setBackground(new java.awt.Color(0, 0, 0, 0));
//				panel.setOpaque(false);
<<<<<<< HEAD
		panel.setLayout(new GridLayout(1, 0));
=======
				panel.setLayout(new GridLayout(1, 0));
>>>>>>> refs/remotes/origin/Dani

		// Agregar JLabels al panel
<<<<<<< HEAD
		for (int i = 0; i < podcasters.size(); i++) {
			JPanel panelItem = new JPanel();
			panelItem.setBorder(null);
=======
				for (int i = 0; i < podcasters.size(); i++) {
					JPanel panelItem = new JPanel();
					panelItem.setBorder(null);
>>>>>>> refs/remotes/origin/Dani
//					panelItem.setOpaque(false);
//					panelItem.setBackground(new java.awt.Color(0, 0, 0, 0));
<<<<<<< HEAD
			panelItem.setLayout(new GridLayout(0, 1));
=======
					panelItem.setLayout(new GridLayout(0,1));
>>>>>>> refs/remotes/origin/Dani

<<<<<<< HEAD
			// Cargar imagen
			ImageIcon imageIcon = podcasters.get(i).getImagen();
			Image image = imageIcon.getImage();
			Image newImage = image.getScaledInstance(180, 180, Image.SCALE_SMOOTH);
			ImageIcon newImageIcon = new ImageIcon(newImage);
			JLabel imageLabel = new JLabel(newImageIcon);
			panelItem.add(imageLabel);
=======
		// Cargar imagen
					ImageIcon imageIcon = podcasters.get(i).getImagen();
					Image image = imageIcon.getImage();
					Image newImage = image.getScaledInstance(180, 180, Image.SCALE_SMOOTH);
					ImageIcon newImageIcon = new ImageIcon(newImage);
					JLabel imageLabel = new JLabel(newImageIcon);
					panelItem.add(imageLabel);
>>>>>>> refs/remotes/origin/Dani

<<<<<<< HEAD
			// Agregar JLabels debajo de la imagen
			JLabel label1 = new JLabel(podcasters.get(i).getNombreArtistico()); // podcasts.get(i).getNombre()
=======
		// Agregar JLabels debajo de la imagen
					JLabel label1 = new JLabel(podcasters.get(i).getNombreArtistico()); //podcasts.get(i).getNombre()
>>>>>>> refs/remotes/origin/Dani
//					JLabel label2 = new JLabel("Autor: " + i);
<<<<<<< HEAD
			label1.setSize(50, 50);
			panelItem.add(label1);
=======
					label1.setSize(50, 50);
					panelItem.add(label1);
>>>>>>> refs/remotes/origin/Dani
//					panelItem.add(label2);
<<<<<<< HEAD
			// Le damos nombre para identificarlo
			panelItem.setName("panel " + i);
			// Añadir un borde al panelItem
			panelItem.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			// Añadir escuchador al panel
			panelItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JPanel clickedPanel = (JPanel) e.getSource();
					gestionInfo.guardarArtistaSeleccionado(((JLabel) clickedPanel.getComponent(1)).getText());
					vp.cambiarDePanel(9);
					JOptionPane.showMessageDialog(null, "Has hecho clic en: " + clickedPanel.getName()
							+ " que tiene los labels:" + ((JLabel) clickedPanel.getComponent(1)).getText()); // + " y "
=======
		//Le damos nombre para identificarlo
					panelItem.setName("panel " + i);
		// Añadir un borde al panelItem
					panelItem.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// Añadir escuchador al panel
					panelItem.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							JPanel clickedPanel = (JPanel) e.getSource();
							gestionInfo.guardarArtistaSeleccionado(((JLabel) clickedPanel.getComponent(1)).getText());
							vp.cambiarDePanel(9);
							JOptionPane.showMessageDialog(null,
									"Has hecho clic en: " + clickedPanel.getName() + " que tiene los labels:"
											+ ((JLabel) clickedPanel.getComponent(1)).getText()); //+ " y "
>>>>>>> refs/remotes/origin/Dani
//											+ ((JLabel) clickedPanel.getComponent(2)).getText());

						}
					});
		// Agregar panelItem al panel principal
					panel.add(panelItem);
				}

		// Crear un JScrollPane y agregar el panel
				JScrollPane scrollPane = new JScrollPane(panel);
		//Como se mueve muy despacio vamos a darle un poco de velocidad
				scrollPane.getVerticalScrollBar().setUnitIncrement(30);
				scrollPane.setBorder(null);
//				scrollPane.setBackground(new java.awt.Color(0, 0, 0, 0));
//				scrollPane.setOpaque(false);
<<<<<<< HEAD
		scrollPane.setSize(764, 367);
		scrollPane.setLocation(10, 131);
=======
				scrollPane.setSize(764,367);
				scrollPane.setLocation(10,131);
>>>>>>> refs/remotes/origin/Dani
		// Agregar el JScrollPane a la ventana
<<<<<<< HEAD
		add(scrollPane);

		JLabel lblTituloLista = new JLabel("DESCUBRIR PODCASTS");
		lblTituloLista.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		lblTituloLista.setBounds(262, 11, 276, 28);
		add(lblTituloLista);
=======
				add(scrollPane);
				
				JLabel lblTituloLista = new JLabel("DESCUBRIR PODCASTS");
				lblTituloLista.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
				lblTituloLista.setBounds(262, 11, 276, 28);
				add(lblTituloLista);
				
				JButton btnAtrás = new JButton("Atrás");
				btnAtrás.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						vp.cambiarDePanel(3);
					}
				});
				btnAtrás.setBounds(10, 11, 89, 23);
				add(btnAtrás);
			
				
				JButton btnPerfil = new JButton("Perfil");
				btnPerfil.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						vp.cambiarDePanel(11);
					}
				});
				btnPerfil.setBounds(685, 11, 89, 23);
				add(btnPerfil);
>>>>>>> refs/remotes/origin/Dani

<<<<<<< HEAD
		JButton btnAtrás = new JButton("Atrás");
		btnAtrás.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(3);
			}
		});
		btnAtrás.setBounds(10, 11, 89, 23);
		add(btnAtrás);

		JButton btnPerfil = new JButton("Perfil");
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(11);
			}
		});
		btnPerfil.setBounds(685, 11, 89, 23);
		add(btnPerfil);
=======
>>>>>>> refs/remotes/origin/Dani

	}

}