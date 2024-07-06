package Tierras;
import Utilerias.*;

public class Hijo extends Thread {
	
	private Hectarea [] VH;
	private int NoHijo;
	
	public Hijo(Hectarea [] VH, int NoHijo) {
		this.VH = VH;
		this.NoHijo = NoHijo;
	}
	
	public void run() {
		int NAleatorio;
		while(true) {
			if(!AunHay())
				return;
			NAleatorio = Rutinas.nextInt(VH.length);
			VH[NAleatorio].getS().Espera();
			if(VH[NAleatorio].isBand()) {
				VH[NAleatorio].getS().Libera();
				continue;
			}
			VH[NAleatorio].setDueño(NoHijo);
			VH[NAleatorio].setBand(true);
			VH[NAleatorio].getS().Libera();
			
		}
	}
	
	public boolean AunHay() {
		for(int i = 0 ; i < VH.length ; i++)
			if(!VH[i].isBand())
				return true;
		return false;
	}
}
