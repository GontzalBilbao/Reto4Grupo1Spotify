package panel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controlador.GestionBD;
import modelo.CancionesFavoritas;
import modelo.MasEscuchado;
import modelo.PodcastsFavoritos;
import modelo.TopPlayList;
import vista.VentanaPrincipal;

public class PanelEstadistica extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	private JLabel lblEstadisticas;
	private JList<CancionesFavoritas> listaCanciones = new JList<CancionesFavoritas>();
	private JList<PodcastsFavoritos> listaPodcasts = new JList<PodcastsFavoritos>();
	private JList<MasEscuchado> listaMasEscuchado = new JList<MasEscuchado>();
	private JList<TopPlayList> listaTopPlaylist = new JList<TopPlayList>();
	
	private ArrayList<CancionesFavoritas> topCanciones;
	private ArrayList<PodcastsFavoritos> topPodcasts;
	private ArrayList<MasEscuchado> masEscuchado;
	private ArrayList<TopPlayList> topPlaylist;
	
	public PanelEstadistica(VentanaPrincipal vp, GestionBD gestionBD) {
		setSize(800, 600);
		setLayout(null);
		
		listaCanciones.removeAll();
		listaPodcasts.removeAll();
		listaMasEscuchado.removeAll();
		listaTopPlaylist.removeAll();
		
		topCanciones = gestionBD.topCancionesFavoritas();
		topPodcasts = gestionBD.topPodcastsFavoritos();
		masEscuchado = gestionBD.topMasEscuchado();
		topPlaylist = gestionBD.topPlayList();
		
		JMenuBar menuEstadisticas = new JMenuBar();
		menuEstadisticas.setBounds(0, 0, 800, 30);
		add(menuEstadisticas);
		
		JMenuItem mntmCancionesMasGustadas = new JMenuItem("TOP CANCIONES");
		mntmCancionesMasGustadas.setHorizontalAlignment(SwingConstants.CENTER);
		mntmCancionesMasGustadas.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmCancionesMasGustadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblEstadisticas.setText("CANCIONES MAS GUSTADAS");

				listaCanciones.removeAll();
				
				listaCanciones.setModel(new AbstractListModel<CancionesFavoritas>() {
					private static final long serialVersionUID = 1L;
					public int getSize() {
						return topCanciones.size();
					}
					public CancionesFavoritas getElementAt(int index) {
						return topCanciones.get(index);
					}
				});
				
				listaPodcasts.setVisible(false);
				listaCanciones.setVisible(true);
				listaMasEscuchado.setVisible(false);
				listaTopPlaylist.setVisible(false);
				
				
				listaCanciones.setBounds(50, 130, 700, 400);
				add(listaCanciones);
				listaCanciones.repaint();
			}
		});
		menuEstadisticas.add(mntmCancionesMasGustadas);
		
		JMenuItem mntmPodcastsMasGustadas = new JMenuItem("TOP PODCASTS");
		mntmPodcastsMasGustadas.setHorizontalAlignment(SwingConstants.CENTER);
		mntmPodcastsMasGustadas.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmPodcastsMasGustadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblEstadisticas.setText("PODCASTS MAS GUSTADOS");
				
				listaPodcasts.removeAll();
				listaPodcasts.setModel(new AbstractListModel<PodcastsFavoritos>() {
					private static final long serialVersionUID = 1L;
					
					public int getSize() {
						return topPodcasts.size();
					}
					public PodcastsFavoritos getElementAt(int index) {
						return topPodcasts.get(index);
					}
				});
				
				listaPodcasts.setVisible(true);
				listaCanciones.setVisible(false);
				listaMasEscuchado.setVisible(false);
				listaTopPlaylist.setVisible(false);
				
				
				listaPodcasts.setBounds(50, 130, 700, 400);
				add(listaPodcasts);
				listaPodcasts.repaint();
				
			}
		});
		menuEstadisticas.add(mntmPodcastsMasGustadas);
		
		JMenuItem mntmMasEscuchado = new JMenuItem("MAS ESCUCHADO");
		mntmMasEscuchado.setHorizontalAlignment(SwingConstants.CENTER);
		mntmMasEscuchado.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmMasEscuchado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblEstadisticas.setText("TOP MAS ESCUCHADO");
				
				listaMasEscuchado.removeAll();
				listaMasEscuchado.setModel(new AbstractListModel<MasEscuchado>() {
					private static final long serialVersionUID = 1L;
					
					public int getSize() {
						return masEscuchado.size();
					}
					public MasEscuchado getElementAt(int index) {
						return masEscuchado.get(index);
					}
				});
				
				listaMasEscuchado.setVisible(true);
				listaPodcasts.setVisible(false);
				listaCanciones.setVisible(false);
				listaTopPlaylist.setVisible(false);
				
				
				listaMasEscuchado.setBounds(50, 130, 700, 400);
				add(listaMasEscuchado);
				listaMasEscuchado.repaint();
				
			}
		});
		menuEstadisticas.add(mntmMasEscuchado);
		
		JMenuItem mntmFavoritos = new JMenuItem("TOP PLAYLIST");
		mntmFavoritos.setHorizontalAlignment(SwingConstants.CENTER);
		mntmFavoritos.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmFavoritos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblEstadisticas.setText("TOP PLAYLIST");
				
				listaTopPlaylist.removeAll();
				listaTopPlaylist.setModel(new AbstractListModel<TopPlayList>() {
					private static final long serialVersionUID = 1L;
					
					public int getSize() {
						return topPlaylist.size();
					}
					public TopPlayList getElementAt(int index) {
						return topPlaylist.get(index);
					}
				});
				
				listaPodcasts.setVisible(false);
				listaCanciones.setVisible(false);
				listaMasEscuchado.setVisible(false);
				listaTopPlaylist.setVisible(true);
				
				listaTopPlaylist.setBounds(50, 130, 700, 400);
				add(listaTopPlaylist);
				listaTopPlaylist.repaint();
				
			}
		});
		menuEstadisticas.add(mntmFavoritos);
		
		JMenuItem mntmAtras = new JMenuItem("ATRAS");
		mntmAtras.setHorizontalAlignment(SwingConstants.CENTER);
		mntmAtras.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				vp.cambiarDePanel(13);
				
			}
		});
		menuEstadisticas.add(mntmAtras);
		
		lblEstadisticas = new JLabel("ESTADISTICAS");
		lblEstadisticas.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEstadisticas.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadisticas.setBounds(120, 60, 550, 45);
		add(lblEstadisticas);
		
		



		
		
		
	}
}
