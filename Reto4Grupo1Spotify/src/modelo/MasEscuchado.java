package modelo;

import java.util.Objects;

public class MasEscuchado {
	
	private String nombre;
	private String duracion;
	private String tipo;
	private int reproducciones;
	
	public MasEscuchado() {
		
	}

	public MasEscuchado(String nombre, String duracion, String tipo, int reproducciones) {
		this.nombre = nombre;
		this.duracion = duracion;
		this.tipo = tipo;
		this.reproducciones = reproducciones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getReproducciones() {
		return reproducciones;
	}

	public void setReproducciones(int reproducciones) {
		this.reproducciones = reproducciones;
	}

	@Override
	public int hashCode() {
		return Objects.hash(duracion, nombre, reproducciones, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MasEscuchado other = (MasEscuchado) obj;
		return Objects.equals(duracion, other.duracion) && Objects.equals(nombre, other.nombre)
				&& reproducciones == other.reproducciones && Objects.equals(tipo, other.tipo);
	}

	@Override
	public String toString() {
		return "Nombre=" + nombre + " || Duracion=" + duracion + " || Tipo=" + tipo + " || Reproducciones="
				+ reproducciones;
	}
	
	
	
	
}
