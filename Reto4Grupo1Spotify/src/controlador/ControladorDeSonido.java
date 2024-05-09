package controlador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;

import modelo.Cancion;

public class ControladorDeSonido {

	private ArrayList<Cancion> canciones;
	private int cancionEnReproduccion;
	private Clip cancionEnCurso;
	private Random random = new Random();
	private int numeroAleatorioAnterior = -1;
	private long continuar = 0;
	private boolean enReproduccion = true;

	public ControladorDeSonido(ArrayList<Cancion> canciones) {
		this.canciones = canciones;
		try {
			cancionEnCurso = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			System.out.println(e.getMessage());
		}
	}

	public void setCancionEnReproduccion(int can) {
		if (canciones.get(cancionEnReproduccion).sonando()) {
			cancionEnCurso.stop();
		}
	}

	public void reproducir(int cola) {
		try {
			if (canciones.get(cola).sonando()) {
				cancionEnCurso.stop();
				cancionEnCurso.close();
			}
			cancionEnCurso.open(
					AudioSystem.getAudioInputStream(new File("cancion/" + canciones.get(cola).getNombre() + ".wav")));
			
			cancionEnCurso.start();
			enReproduccion = false;

		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}

	public void pausar() {
		cancionEnCurso.stop();
	}

	public boolean cancionActiva() {
		return cancionEnCurso.isActive();
	}

	public void bucle(boolean activo, int cola) {

		if (activo == true) {
			reproducir(cola);
			cancionEnCurso.loop(100);
		} else {
			reproducir(cola);
			cancionEnCurso.loop(0);
			cancionEnCurso.stop();

		}

	}

	public int ramdom() {
		int numeroAleatorioActual;

		do {
			numeroAleatorioActual = random.nextInt(canciones.size());
		} while (numeroAleatorioActual == numeroAleatorioAnterior);

		numeroAleatorioAnterior = numeroAleatorioActual;
		return numeroAleatorioActual;
	}

	public long seguirCancion() {
		cancionEnCurso.stop();
		return cancionEnCurso.getMicrosecondPosition();
	}

	public void continuarCancion(JButton btnplay2) {

		if (!enReproduccion) {
			continuar = cancionEnCurso.getMicrosecondPosition();
			cancionEnCurso.stop();
			btnplay2.setText("PLAY");
			enReproduccion = true;
		} else {
			cancionEnCurso.setMicrosecondPosition(continuar);
			cancionEnCurso.start();
			btnplay2.setText("STOP");
			enReproduccion = false;
		}

	}

}
