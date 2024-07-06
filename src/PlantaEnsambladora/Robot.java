package PlantaEnsambladora;
import Utilerias.*;
import java.awt.*;
public class Robot {
	private Image imagen;
	private int X,Y,tamaño, linea, estacion;
	private boolean Ocupado;
	public Robot(Image img,int t,int x,int y,int e) {
		imagen = img;
		tamaño = t;
		X=x;
		Y=y;
		estacion = e;
		Ocupado = false;
	}
	
	public void dibuja(Graphics g) {
		g.drawImage(imagen, X, Y, tamaño, tamaño,null);
	}
	public boolean mover(int y) {
		if(Y == y)
			return false;
		if(Y > y) {
			Y--;
			return true;
		}
		Y++;
		return true;
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}
	public int getTamaño() {
		return tamaño;
	}
	public void setTamaño(int t) {
		tamaño = t;
	}

	public int getLinea() {
		return linea;
	}

	public int getEstacion() {
		return estacion;
	}

	public void setLinea(int linea) {
		this.linea = linea;
	}

	public boolean isOcupado() {
		return Ocupado;
	}

	public void setOcupado(boolean ocupado) {
		Ocupado = ocupado;
	}
}
