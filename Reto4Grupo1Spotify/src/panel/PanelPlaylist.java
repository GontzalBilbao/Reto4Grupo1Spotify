package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controlador.GestionFicheros;
import controlador.GestionInformacion;
import modelo.Cancion;
import modelo.PlayList;
import vista.VentanaPrincipal;

public class PanelPlaylist extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldNuevaPlayList;
	private DefaultListModel<String> listModel;
	private String usuario = null;
	private String nuevaPlayList = null;
	private String idCliente = null;
	private String titulo = null;
	
	private ArrayList<PlayList> playlists = new ArrayList<PlayList>();
	private ArrayList<Cancion> canciones = new ArrayList<Cancion>();

	/**
	 * Create the panel.
	 */
	public PanelPlaylist(VentanaPrincipal vp, GestionInformacion gestionInfo, GestionFicheros gestionFicheros) {
		setSize(800, 600);
		setBackground(Color.WHITE);
		setLayout(null);
		
		
		JLabel lblLogin = new JLabel("MIS PLAYLISTS");
		lblLogin.setForeground(Color.BLACK);
		lblLogin.setFont(new Font("Lucida Bright", Font.BOLD, 20));
		lblLogin.setBounds(42, 110, 260, 51);
		add(lblLogin);
		
		JButton btnAtras = new JButton("ATRAS");
		btnAtras.setBackground(Color.BLACK);
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(3);
			}
		});
		btnAtras.setBounds(42, 43, 90, 35);
		add(btnAtras);
		
		JButton btnPerfil = new JButton("PERFIL");
		btnPerfil.setBackground(Color.BLACK);
		btnPerfil.setForeground(Color.WHITE);
		btnPerfil.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.nPanel = 12;
				vp.cambiarDePanel(11);
			}
		});
		btnPerfil.setBounds(675, 43, 90, 35);
		add(btnPerfil);
		
		JList<String> list = new JList<String>();
		list.setBounds(42, 163, 363, 354);
		add(list);
		
//		JList<String> lista = new JList<String>(playlists.toArray(new String[playlists.size()]));
//		list.add(lista);
//		
		usuario = gestionInfo.devolverUsuarioCliente();
//<<<<<<< HEAD
		gestionInfo.cargarPlayLists(usuario);
		playlists = gestionInfo.devolverPlayLists();
		
		idCliente = gestionInfo.devolverIdCliente();
//		gestionBD.queryIdUsuario(usuario);
//		clientes = gestionBD.devolverIdUsuario();
//		for (int i = 0; i < clientes.size(); i++) {
//		idCliente = clientes.get(i).getIdCliente();
//			
////>>>>>>> refs/remotes/origin/Isabella
//		}
		
		listModel = new DefaultListModel<String>();
		list.setBorder(new LineBorder(Color.black,1,true));
		for (int i = 0; i < playlists.size(); i++){
		    listModel.addElement(playlists.get(i).getTitulo());
		}
		list.setModel(listModel);
		
		textFieldNuevaPlayList = new JTextField();
		textFieldNuevaPlayList.setBounds(429, 213, 336, 35);
		add(textFieldNuevaPlayList);
		textFieldNuevaPlayList.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nueva PlayList");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblNewLabel.setBounds(429, 182, 336, 35);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("INSERTAR");
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if("".equals(textFieldNuevaPlayList.getText())) {
					
					JOptionPane.showMessageDialog(vp, "Introduzca una nueva PlayList", "Error",
							JOptionPane.ERROR_MESSAGE);
				}else {
					nuevaPlayList = textFieldNuevaPlayList.getText();
				
					gestionInfo.añadirPlayList(nuevaPlayList, idCliente);
					
					DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();
		            model.addElement(nuevaPlayList);
	
		            list.revalidate();
		            list.repaint();

					JOptionPane.showMessageDialog(vp, "PlayList añadida con éxito", "PlayList Guardada",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnNewButton.setBounds(429, 258, 163, 45);
		add(btnNewButton);
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setBackground(Color.BLACK);
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//<<<<<<< HEAD
//				gestionInfo.eliminarPlayList(list.getSelectedValue().toString());
//=======
				gestionInfo.eliminarPlayList(list.getSelectedValue().toString());
				
				DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();
	            model.removeElement(list.getSelectedValue());
	            
	            // Notificar al JList que se han realizado cambios en el modelo
	            list.revalidate();
	            list.repaint();
				
//>>>>>>> refs/remotes/origin/Isabella
				JOptionPane.showMessageDialog(vp, "PlayList eliminada con éxito", "PlayList Eliminada",
						JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		btnEliminar.setBounds(602, 258, 163, 45);
		add(btnEliminar);
		
		JButton btnNewButton_1 = new JButton("EXPORTAR");
		btnNewButton_1.setBackground(Color.BLACK);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				gestionFicheros.imprimirInformacion(playlists);
				JOptionPane.showMessageDialog(vp, "PlayList exportada con éxito", "PlayList Exportada",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton_1.setBounds(429, 313, 336, 81);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Seleccionar");
		btnNewButton_2.setBackground(Color.BLACK);
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				titulo = list.getSelectedValue().toString();
//				gestionBD.queryCancion(titulo);
//				canciones = gestionBD.devolverCancion();
//				
				titulo = list.getSelectedValue().toString();
				gestionInfo.cargarCancionesDePlaylist(titulo);
				canciones = gestionInfo.devolverCanciones();
				
				if (canciones.isEmpty()) {
		            JOptionPane.showMessageDialog(vp, "PlayList Vacia", "Error",
							JOptionPane.ERROR_MESSAGE);
		            
		        } else {
		        	gestionInfo.guardarPlaylistSeleccionada(list.getSelectedValue().toString());
					vp.cambiarDePanel(25);
		        }
			}
		});
		btnNewButton_2.setBounds(255, 128, 150, 23);
		add(btnNewButton_2);
	}
}
