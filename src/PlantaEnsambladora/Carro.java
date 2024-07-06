package PlantaEnsambladora;
import Utilerias.*;
import java.awt.*;

public class Carro extends Thread {
	
	private Semaforo[] VSemaforos;
	private Image imagen;
	private int X,Y,linea,tamaño;
	private PlantaVista Vista;
	private static final int ESPERA = 100, CHASIS = 1, MOTOR = 2, TRANSMISION = 3, CARROCERIA = 4,
							 INTERIORES = 5, LLANTAS = 6, PRUEBA = 7, ALMACEN = 8;
	
	public Carro(Semaforo[] sems, Image img,int x,int y, int linea, int t,PlantaVista vista) {
		VSemaforos = sems;
		imagen = img;
		X = x;
		Y = y;
		this.linea = linea;
		tamaño = t;
		Vista = vista;
	}
	
	public void run() {
		mover(CHASIS);
		verificarPausa();
		VSemaforos[CHASIS - 1].Espera();
		Vista.PideRobot(linea, CHASIS);
		try {sleep(20000);} catch(InterruptedException IE) {}
		imagen = Rutinas.AjustarImagen("ImagenesPlanta/chasisCarro.png", tamaño, tamaño).getImage();
		Vista.LiberaRobot(linea, CHASIS);
		VSemaforos[0].Libera();
		verificarPausa();
		mover(MOTOR);
		
		Vista.generaCarro(linea);
		
		verificarPausa();
		VSemaforos[MOTOR - 1].Espera();
		Vista.PideRobot(linea, MOTOR);
		try {sleep(6000);} catch(InterruptedException IE) {}
		imagen = Rutinas.AjustarImagen("ImagenesPlanta/motorCarro.png", tamaño, tamaño).getImage();
		VSemaforos[TRANSMISION - 1].Espera();
		Vista.PideRobot(linea, TRANSMISION);
		Vista.LiberaRobot(linea, MOTOR);
		VSemaforos[MOTOR - 1].Libera();
		verificarPausa();
		mover(TRANSMISION);
		verificarPausa();
		try {sleep(4000);} catch(InterruptedException IE) {}
		imagen = Rutinas.AjustarImagen("ImagenesPlanta/transmisionCarro.png", tamaño, tamaño).getImage();
		Vista.LiberaRobot(linea, TRANSMISION);
		VSemaforos[TRANSMISION - 1].Libera();
		verificarPausa();
		mover(CARROCERIA);
				
		verificarPausa();
		VSemaforos[CARROCERIA - 1].Espera();
		Vista.PideRobot(linea, CARROCERIA);
		try {sleep(10000);} catch(InterruptedException IE) {}
		imagen = Rutinas.AjustarImagen("ImagenesPlanta/carroceria" + linea + "Carro.png", tamaño, tamaño).getImage();
		Vista.LiberaRobot(linea, CARROCERIA);
		VSemaforos[CARROCERIA - 1].Libera();
		verificarPausa();
		mover(INTERIORES);
		
		verificarPausa();
		VSemaforos[INTERIORES - 1].Espera();
		Vista.PideRobot(linea, INTERIORES);
		try {sleep(5000);} catch(InterruptedException IE) {}
		imagen = Rutinas.AjustarImagen("ImagenesPlanta/interiores" + linea + "Carro.png", tamaño, tamaño).getImage();
		Vista.LiberaRobot(linea, INTERIORES);
		VSemaforos[INTERIORES - 1].Libera();
		verificarPausa();
		mover(LLANTAS);
		
		verificarPausa();
		VSemaforos[LLANTAS - 1].Espera();
		Vista.PideRobot(linea, LLANTAS);
		try {sleep(5000);} catch(InterruptedException IE) {}
		imagen = Rutinas.AjustarImagen("ImagenesPlanta/llantas" + linea + "Carro.png", tamaño, tamaño).getImage();
		Vista.LiberaRobot(linea, LLANTAS);
		VSemaforos[LLANTAS - 1].Libera();
		verificarPausa();
		mover(PRUEBA);
		
		verificarPausa();
		VSemaforos[PRUEBA - 1].Espera();
		Vista.PideRobot(linea, PRUEBA);
		try {sleep(10000);} catch(InterruptedException IE) {}
		imagen = Rutinas.AjustarImagen("ImagenesPlanta/listo" + linea + "Carro.png", tamaño, tamaño).getImage();
		Vista.LiberaRobot(linea, PRUEBA);
		VSemaforos[PRUEBA- 1].Libera();
		verificarPausa();
		mover(ALMACEN);
	}
	
	private void mover(int estacion) {
		while(X < Vista.getCanvas().getWidth()/20 + Vista.getCanvas().getWidth()*(estacion-1)/7) {
			X++;
			try {Thread.sleep(5);} catch(InterruptedException IE) {}
		}
		if(estacion == 8)
			Vista.retiraCarro(this);
	}
	
	public void verificarPausa() {
		while(Vista.isPausa())
			try {Thread.sleep(100);} catch(InterruptedException IE) {}
	}
	
	public void Dibujar(Graphics g) {
		g.drawImage(imagen, X, Y, tamaño, tamaño,null);
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}
}
