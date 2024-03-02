package Utilerias;
import javax.swing.*;

public class Refrescador extends Thread {
	
	private JFrame Vista;
	public int Frecuencia;
	private static final int MILIPORSEG = 1000;
	
	public Refrescador(JFrame vista, int hz) {
		Vista = vista;
		Frecuencia = hz;
	}
	
	public Refrescador(JFrame vista) {
		this(vista, 60);
	}
	
	public void run() {
		while(true) {
			Vista.repaint();
			try {Thread.sleep(MILIPORSEG / Frecuencia);} catch(InterruptedException IE) {}
		}
	}
	
	public JFrame getVista() {
		return Vista;
	}
	
	public int getHertz() {
		return Frecuencia;
	}
	
	public void setHertz(int hertz) {
		Frecuencia = hertz;
	}
}
