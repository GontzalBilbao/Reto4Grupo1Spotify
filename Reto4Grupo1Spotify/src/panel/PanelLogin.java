package panel;

import java.awt.Color;

import javax.swing.JPanel;

import vista.VentanaPrincipal;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelLogin extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldUsusario;
	private JTextField textFieldContraseña;
	private JButton btnRegistrarse_1;

	/**
	 * Create the panel.
	 */
	public PanelLogin(VentanaPrincipal v) {
		setSize(800, 600);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		JLabel lblUsuario = new JLabel("Ususrio:");
		lblUsuario.setForeground(Color.GREEN);
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsuario.setBounds(220, 250, 90, 20);
		add(lblUsuario);
		
		textFieldUsusario = new JTextField();
		textFieldUsusario.setBounds(330, 300, 200, 20);
		add(textFieldUsusario);
		textFieldUsusario.setColumns(10);
		
		JLabel lblContraseña = new JLabel("Contraseña:");
		lblContraseña.setHorizontalAlignment(SwingConstants.LEFT);
		lblContraseña.setForeground(Color.GREEN);
		lblContraseña.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblContraseña.setBounds(220, 300, 90, 20);
		add(lblContraseña);
		
		textFieldContraseña = new JTextField();
		textFieldContraseña.setColumns(10);
		textFieldContraseña.setBounds(330, 250, 200, 20);
		add(textFieldContraseña);
		
		JLabel lblLogin = new JLabel("INICIO DE SESION");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(Color.GREEN);
		lblLogin.setFont(new Font("Lucida Bright", Font.BOLD, 20));
		lblLogin.setBounds(270, 100, 260, 51);
		add(lblLogin);
		
		JButton btnRegistrarse = new JButton("REGISTRARSE");
		btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRegistrarse.setForeground(Color.BLACK);
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRegistrarse.setBounds(90, 450, 180, 35);
		add(btnRegistrarse);
		
		btnRegistrarse_1 = new JButton("INICIAR SESIÓN");
		btnRegistrarse_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.cambiarDePanel(2);
			}
		});
		btnRegistrarse_1.setForeground(Color.BLACK);
		btnRegistrarse_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRegistrarse_1.setBounds(530, 450, 180, 35);
		add(btnRegistrarse_1);
		
		JLabel lblIconoGrande = new JLabel("");
		lblIconoGrande.setIcon(new ImageIcon("icono/spotifyicon.png"));
		lblIconoGrande.setBounds(0, 0, 100, 100);
		add(lblIconoGrande);

	}
}
