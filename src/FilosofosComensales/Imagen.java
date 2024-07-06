package FilosofosComensales;

public class Imagen {
	
	private int xInicial, yInicial, xActual, yActual, Id;
	
	public Imagen(int x, int y, int Id) {
		xInicial = xActual = x;
		yInicial = yActual = y;
		this.Id = Id;
	}
	
	public int getId() {
		return Id;
	}
	
	public int getXInicial() {
		return xInicial;
	}
	
	public int getYInicial() {
		return yInicial;
	}
	
	public int getYActual() {
		return yActual;
	}

	public void setYActual(int yActual) {
		this.yActual = yActual;
	}
	
	public int getXActual() {
		return xActual;
	}
	
	public void setXActual(int xActual) {
		this.xActual = xActual;
	}
}
