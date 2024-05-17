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

import interfaces.IControladorSonido;
import modelo.Podcast;

public class ControladorDePodcast implements IControladorSonido {

	private ArrayList<Podcast> canciones;
	private int cancionEnReproduccion;
	private Clip cancionEnCurso;
	private Random random = new Random();// Generador de números aleatorios
	private int numeroAleatorioAnterior = -1;// Número aleatorio anterior generado
	private long continuar = 0;// Posición de reproducción para continuar después de pausar
	private boolean enReproduccion = true;

	public ControladorDePodcast(ArrayList<Podcast> canciones) {
		this.canciones = canciones;
		try {
			// Inicialización del Clip de la canción en curso
			cancionEnCurso = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void setCancionEnReproduccion(int can) {
		 // Detiene la canción en reproducción actual
		if (canciones.get(cancionEnReproduccion).sonando()) {
			cancionEnCurso.stop();
		}
	}

	@Override
	public void reproducir(int cola) {
		try {
			// Detiene y cierra la canción en curso si está reproduciendo otra canción
			if (canciones.get(cola).sonando()) {
				cancionEnCurso.stop();
				cancionEnCurso.close();
			}
			// Abre y reproduce la nueva canción
			cancionEnCurso.open(
					AudioSystem.getAudioInputStream(new File("cancion/" + canciones.get(cola).getNombre() + ".wav")));

			cancionEnCurso.start();
			enReproduccion = false;

		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void pausar() {
		// Pausa la canción en reproducción
		cancionEnCurso.stop();
	}

	@Override
	public boolean cancionActiva() {
		// Verifica si la canción en curso está activa (reproduciéndose)
		return cancionEnCurso.isActive();
	}

	@Override
	public void bucle(boolean activo, int cola) {

		// Establece el bucle de reproducción de la canción
		if (activo == true) {
			reproducir(cola);
			cancionEnCurso.loop(100);// Bucle continuo
		} else {
			reproducir(cola);
			cancionEnCurso.loop(0);// No se repite
			cancionEnCurso.stop();// Detiene la reproducción

		}

	}

	@Override
	public int ramdom() {
		// Genera un número aleatorio para seleccionar un podcast diferente
		int numeroAleatorioActual;

		do {
			numeroAleatorioActual = random.nextInt(canciones.size());
		} while (numeroAleatorioActual == numeroAleatorioAnterior);

		numeroAleatorioAnterior = numeroAleatorioActual;
		return numeroAleatorioActual;
	}

	@Override
	public long seguirCancion() {
		// Detiene la canción en curso y devuelve la posición de reproducción actual
		cancionEnCurso.stop();
		return cancionEnCurso.getMicrosecondPosition();
	}

	@Override
	public void continuarCancion(JButton btnplay2) {

		// Método para continuar o detener la reproducción de la canción
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

	@Override
	public void parar() {
		cancionEnCurso.stop();
		cancionEnCurso.close();

	}

	@Override
	public void anuncio() {
		// Reproduce un anuncio
				try {
					cancionEnCurso.open(AudioSystem.getAudioInputStream(new File("anuncio/Anuncio.wav")));
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cancionEnCurso.start();
			
	}

}
