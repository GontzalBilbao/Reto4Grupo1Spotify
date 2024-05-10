package interfaces;

import javax.swing.JButton;

public interface IControladorSonido {

	void setCancionEnReproduccion(int can);

	void reproducir(int cola);

	void pausar();

	boolean cancionActiva();

	void bucle(boolean activo, int cola);

	int ramdom();

	long seguirCancion();

	void continuarCancion(JButton btnplay2);

	void parar();

	void anuncio();
	
}