package Tierras;

public class TierrasModelo {
	
	private Hijo [] VHijos;
	private Hectarea [] VHectareas;
	private final static int NoHijos = 3, NoHectareas = 100;
	
	public TierrasModelo() {
		VHijos = new Hijo [NoHijos];
		VHectareas = new Hectarea [NoHectareas];
		
		InicializaHijos();
		InicializaHectareas();
	}
	
	public void InicializaHijos() {
		for(int i = 0 ; i < NoHijos ; i++)
			VHijos[i] = new Hijo(VHectareas, i+1);
	}
	
	public void InicializaHectareas() {
		for(int i = 0 ; i < NoHectareas ; i++)
			VHectareas[i] = new Hectarea();
	}
	
	public void Repartir() {
		for(int i = 0 ; i < NoHijos ; i++)
			VHijos[i].start();
		
		while(EstanVivos());
	}
	
	public boolean EstanVivos() {
		for(int i = 0 ; i < NoHijos ; i++)
			if(VHijos[i].isAlive())
				return true;
		return false;
	}
	
	public Hectarea[] getHectareas() {
		return VHectareas;
	}
	
	public int[] getHectareasXHijo() {
		int [] V = new int [NoHijos];
		for(int i = 0 ; i < NoHectareas ; i++)
			V[VHectareas[i].getDueño() - 1]++;
		return V;
	}
}
