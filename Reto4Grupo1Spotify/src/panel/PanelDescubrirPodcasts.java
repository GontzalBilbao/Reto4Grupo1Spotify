package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
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

import controlador.GestionBD;
import controlador.GestionInformacion;
import modelo.Podcast;
import modelo.Podcaster;
import vista.VentanaPrincipal;

public class PanelDescubrirPodcasts extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Podcaster> podcasters = new ArrayList<Podcaster>();
	private ArrayList<Podcast> podcasts = new ArrayList<Podcast>();
	
	/**
	 * Create the panel.
	 */
	public PanelDescubrirPodcasts(VentanaPrincipal vp, GestionBD gestionBD, GestionInformacion gestoinInfo) {
		setSize(800, 600);
		setBackground(Color.WHITE);
		setLayout(null);
		
		gestionBD.queryPodcasters("Nadie Sabe Nada");
		podcasters = gestionBD.devolverPodcasters();
		for (int i = 0; i < podcasters.size(); i++) {
//			System.out.println(podcasters.get(i).getDescripcion());
		}
		
		gestionBD.queryPodcastsDelPodcaster(podcasters.get(0).getIdPodcaster());
		podcasts = gestionBD.devolverPodcasts();

		// Crear un panel para contener los JLabels
				JPanel panel = new JPanel();
//				panel.setBackground(new java.awt.Color(0, 0, 0, 0));
//				panel.setOpaque(false);
				panel.setLayout(new GridLayout(0, 1));

		// Agregar JLabels al panel
				for (int i = 0; i < 20; i++) {
					JPanel panelItem = new JPanel();
					panelItem.setBorder(null);
//					panelItem.setOpaque(false);
//					panelItem.setBackground(new java.awt.Color(0, 0, 0, 0));
					panelItem.setLayout(new GridLayout());

		// Cargar imagen
					ImageIcon imageIcon = podcasters.get(0).getImagen();
					Image image = imageIcon.getImage();
					Image newImage = image.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
					ImageIcon newImageIcon = new ImageIcon(newImage);
					JLabel imageLabel = new JLabel(newImageIcon);
					panelItem.add(imageLabel);

		// Agregar JLabels debajo de la imagen
					JLabel label1 = new JLabel("Episodio: " + i); //podcasts.get(i).getNombre()
//					JLabel label2 = new JLabel("Autor: " + i);
					panelItem.add(label1);
//					panelItem.add(label2);
		//Le damos nombre para identificarlo
					panelItem.setName("panel " + i);
		// Añadir un borde al panelItem
					panelItem.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// Añadir escuchador al panel
					panelItem.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							JPanel clickedPanel = (JPanel) e.getSource();
							JOptionPane.showMessageDialog(null,
									"Has hecho clic en: " + clickedPanel.getName() + " que tiene los labels:"
											+ ((JLabel) clickedPanel.getComponent(1)).getText()); //+ " y "
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
				scrollPane.setSize(300,500);
				scrollPane.setLocation(453,50);
		// Agregar el JScrollPane a la ventana
				add(scrollPane);
				
				JLabel lblTituloLista = new JLabel("PODCASTS");
				lblTituloLista.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
				lblTituloLista.setBounds(540, 11, 130, 28);
				add(lblTituloLista);
				
				JButton btnAtrás = new JButton("Atrás");
				btnAtrás.setBounds(10, 11, 89, 23);
				add(btnAtrás);
				
				JLabel lblNombre = new JLabel("Nombre: " + podcasters.get(0).getNombreArtista());
				lblNombre.setBounds(204, 68, 228, 14);
				add(lblNombre);
				
				JLabel lblImagenArtista = new JLabel("New label");
				Image image = podcasters.get(0).getImagen().getImage();
				Image nuevaImagen = image.getScaledInstance(175, 190, Image.SCALE_SMOOTH);
				ImageIcon ImagenReescalada = new ImageIcon(nuevaImagen);
				lblImagenArtista.setIcon(ImagenReescalada);
				lblImagenArtista.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				lblImagenArtista.setBounds(20, 50, 175, 190);
				add(lblImagenArtista);
				

				
				JLabel lblGenero = new JLabel("Género: " + podcasters.get(0).getGenero());
				lblGenero.setBounds(204, 93, 228, 14);
				add(lblGenero);
				
				JLabel lblDescripcion = new JLabel("<html>" + "Descripción: " + podcasters.get(0).getDescripcion().replaceAll("\\n", "<br>") + "</html>");
				lblDescripcion.setVerticalAlignment(SwingConstants.TOP);
				lblDescripcion.setHorizontalAlignment(SwingConstants.LEFT);
				lblDescripcion.setBounds(204, 118, 228, 120);
				add(lblDescripcion);
				
				JButton btnPerfil = new JButton("Perfil");
				btnPerfil.setBounds(685, 11, 89, 23);
				add(btnPerfil);


	}

}
