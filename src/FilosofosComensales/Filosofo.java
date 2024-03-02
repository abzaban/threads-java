package FilosofosComensales;
import java.awt.*;
import Utilerias.Elemento;

public class Filosofo extends Thread {
	
	private int x, y;
	private Image Imagen;
	private Elemento [] VE;
	private Imagen TenedorIzq, TenedorDer, Plato;
	private FilosofosInterfaz Vista;
	
	public Filosofo(int x, int y, Image Imagen, Elemento [] VE, FilosofosInterfaz Vista) {
		this.x = x;
		this.y = y;
		this.Imagen = Imagen;
		this.VE = VE;
		this.Vista = Vista;
	}
	
	public void run() {
		while(true) {
			VE[TenedorIzq.getId()].getS().Espera();
			if(VE[TenedorIzq.getId()].isBand()) {
				VE[TenedorIzq.getId()].getS().Libera();
				continue;
			}
			VE[TenedorDer.getId()].getS().Espera();
			if(VE[TenedorDer.getId()].isBand()) {
				VE[TenedorDer.getId()].getS().Libera();
				VE[TenedorIzq.getId()].getS().Libera();
				continue;
			}
			VE[TenedorIzq.getId()].setBand(true);
			VE[TenedorDer.getId()].setBand(true);
			VE[TenedorDer.getId()].getS().Libera();
			VE[TenedorIzq.getId()].getS().Libera();
			
			Vista.MueveTenedor(TenedorIzq, Plato.getXActual(), Plato.getYActual());
			Vista.MueveTenedor(TenedorDer, Plato.getXActual(), Plato.getYActual());
			
			try {sleep(10000);} catch(InterruptedException IE) {}
			
			Vista.MueveTenedor(TenedorIzq, TenedorIzq.getXInicial(), TenedorIzq.getYInicial());
			Vista.MueveTenedor(TenedorDer, TenedorDer.getXInicial(), TenedorDer.getYInicial());
			
			VE[TenedorDer.getId()].setBand(false);
			VE[TenedorIzq.getId()].setBand(false);
			break;
		}
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
	
	public Image getImagen() {
		return Imagen;
	}

	public Imagen getTenedorIzq() {
		return TenedorIzq;
	}

	public void setTenedorIzq(Imagen tenedorIzq) {
		TenedorIzq = tenedorIzq;
	}

	public Imagen getTenedorDer() {
		return TenedorDer;
	}

	public void setTenedorDer(Imagen tenedorDer) {
		TenedorDer = tenedorDer;
	}
	
	public Imagen getPlato() {
		return Plato;
	}
	
	public void setPlato(Imagen Plato) {
		this.Plato = Plato;
	}
}
