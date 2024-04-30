package panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import vista.VentanaPrincipal;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelMenuCliente extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelMenuCliente(VentanaPrincipal v) {
		setSize(800, 600);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		JLabel lblLogin = new JLabel("MENU CLIENTE");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(Color.GREEN);
		lblLogin.setFont(new Font("Lucida Bright", Font.BOLD, 20));
		lblLogin.setBounds(270, 100, 260, 51);
		add(lblLogin);
		
		JButton btnNewButton = new JButton("DESCUBRIR MUSICA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(362, 199, 89, 23);
		add(btnNewButton);
		

	}

}
