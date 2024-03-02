package Aeropuerto;
import java.awt.*;
import Utilerias.*;

public class Avion extends Thread {
	
	private int x, y, Turno, Intentos;
	private Image Imagen;
	private Elemento S;
	private AeropuertoInterfaz Vista;
	private boolean VuelaIzq;
	public static final int TAMAÑO = 100;
	
	public Avion(int x, int y, int Turno, Elemento S, AeropuertoInterfaz Vista) {
		VuelaIzq = false;
		Intentos = 1;
		Imagen = Rutinas.AjustarImagen("ImagenesAeropuerto/AvionDer.png", TAMAÑO, TAMAÑO).getImage();
		this.x = x;
		this.y = y;
		this.Turno = Turno;
		this.S = S;
		this.Vista = Vista;
	}
	
	public void run() {
		while(true) {
			Vuela();
			S.getS().Espera();
			if(S.isBand()) {
				S.getS().Libera();
				continue;
			}
			S.setBand(true);
			S.getS().Libera();
			Desciende();
			if(Vista.getTurnoSiguiente() != Turno) {
				Asciende();
				S.setBand(false);
				Intentos++;
				continue;
			}
			Aterriza();
			Vista.EliminaAvion(this);
			try {sleep(10000);} catch(InterruptedException IE) {}
			S.setBand(false);
			break;
		}
	}
	
	public void Dibuja(Graphics g) {
		g.drawImage(Imagen, x, y, null);
	}
	
	public void Vuela() {
		try {Thread.sleep(10);} catch(InterruptedException IE) {}
		if(x + TAMAÑO >= Vista.getWidth()) {
			VuelaIzq = true;
			Imagen = Rutinas.AjustarImagen("ImagenesAeropuerto/AvionIzq.png", TAMAÑO, TAMAÑO).getImage();
		}
		if(x <= 0) {
			VuelaIzq = false;
			Imagen = Rutinas.AjustarImagen("ImagenesAeropuerto/AvionDer.png", TAMAÑO, TAMAÑO).getImage();
		}
		if(VuelaIzq) {
			x--;
			return;
		}
		x++;
	}
	
	public void Desciende() {
		while(y < Vista.getHeight() * 4 / 10) {
			y++;
			Vuela();
		}
	}
	
	public void Asciende() {
		while(y > 100) {
			y--;
			Vuela();
		}
	}
	
	public void Aterriza() {
		while(y + TAMAÑO < Vista.getHeight()) {
			y++;
			Vuela();
		}
	}
	
	public int getIntentos() {
		return Intentos;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getTurno() {
		return Turno;
	}
}
