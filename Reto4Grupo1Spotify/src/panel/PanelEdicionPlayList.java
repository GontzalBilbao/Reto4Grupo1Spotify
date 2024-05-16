package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import controlador.GestionBD;
import controlador.GestionInformacion;
import modelo.Cancion;
import vista.VentanaPrincipal;

public class PanelEdicionPlayList extends JPanel {
	
//	private ArrayList<PlayList> playlists = new ArrayList<PlayList>();
	private ArrayList<Cancion> canciones = new ArrayList<Cancion>();
	
	private String titulo = null;
//	private String nombre = null;
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelEdicionPlayList(VentanaPrincipal vp, GestionBD gestionBD, GestionInformacion gestionInfo) {
		setSize(800, 600);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		JButton btnNewButton = new JButton("Atrás");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(12);
			}
		});
		btnNewButton.setBounds(97, 57, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Perfil");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.nPanel = 12;
				vp.cambiarDePanel(11);
			}
		});
		btnNewButton_1.setBounds(602, 57, 89, 23);
		add(btnNewButton_1);
		
		titulo = gestionInfo.devolverPlaylistSeleccionada();
		gestionInfo.cargarCancionesDePlaylist(titulo);
		canciones = gestionInfo.devolverCanciones();
//		gestionBD.queryCancion(titulo);
//		canciones = gestionBD.devolverCancion();
//		for (int i = 0; i < canciones.size(); i++) {
//			System.out.println(canciones.get(i).getNombre());
//			
//			nombre = canciones.get(i).getNombre();
//		}
			
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		
		for (int i = 0; i < canciones.size(); i++) {
			JPanel panelItem = new JPanel();
			panelItem.setBorder(null);
			panelItem.setLayout(new GridLayout(1, 0));
			
			JLabel lblnombreCancion = new JLabel(canciones.get(i).getNombre());
			lblnombreCancion.setSize(50, 50);
			panelItem.add(lblnombreCancion);
	
			panelItem.setName("panel " + i);
			panelItem.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
			JLabel lblEliminar = new JLabel("Eliminar");
			lblnombreCancion.setSize(50, 50);
			panelItem.add(lblEliminar);
			
			panelItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JPanel clickedPanel = (JPanel) e.getSource();
					
					String cancionSeleccionada = ((JLabel) clickedPanel.getComponent(0)).getText();
//					System.out.println(cancionSeleccionada);
					gestionInfo.eliminarCancionDePlayList(cancionSeleccionada);
					
//					panel.removeAll();
					vp.cambiarDePanel(25);
					
					JOptionPane.showMessageDialog(null, cancionSeleccionada + " eliminada con éxito", cancionSeleccionada + " eliminada",
							JOptionPane.INFORMATION_MESSAGE);
				}
			});
			
		panel.add(panelItem);
	}
		
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(101, 157, 600, 350);
		add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("-"+ titulo.toUpperCase() + "-");
		lblNewLabel.setForeground(new Color(0, 255, 0));
		lblNewLabel.setFont(new Font("Lucida Bright", Font.BOLD, 20));
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setBounds(101, 102, 600, 50);
		add(lblNewLabel);
	}
}
