package Utilerias;

public class Elemento {
	
	private Semaforo S;
	private boolean Band;
	
	public Elemento(int Recursos) {
		S = new Semaforo(Recursos);
		Band = false;
	}
	
	public Semaforo getS() {
		return S;
	}
	
	public void setS(Semaforo s) {
		S = s;
	}
	
	public boolean isBand() {
		return Band;
	}
	
	public void setBand(boolean band) {
		Band = band;
	}
}
