package MinaPlata;
import java.awt.*;
import Utilerias.*;

public class Pais extends Thread {
	
	private MinaPlataInterfaz Vista;
	private Productor P;
	private String Continente;
	private int X, Y;
	private Image Imagen;
	private Semaforo S;
	
	public Pais(MinaPlataInterfaz Vista, Productor P, String Continente, int X, int Y,Semaforo S) {
		this.Vista = Vista;
		this.P = P;
		this.Continente = Continente;
		this.X = X;
		this.Y = Y;
		this.S = S;
		Imagen = Rutinas.AjustarImagen("ImagenesMina/Pin.png", 50, 100).getImage();
	}
	
	public void run() {
		int Cantidad, Calidad;
		while(P.HayaPlata(Continente)) {
			Cantidad = Rutinas.nextInt(1, 3);
			Calidad = Rutinas.nextInt(0, 2);
			S.Espera();
			Vista.ActualizaConsola("PETICION", Continente, Cantidad, Calidad);
			P.PidePlata(Cantidad, Calidad, Continente);
			S.Libera();
		}
		System.out.println("Me mori unu");
	}
	
	public void Dibuja(Graphics g) {
		g.drawImage(Imagen, X, Y, null);
	}
}
