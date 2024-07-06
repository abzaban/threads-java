package MinaPlata;
import Utilerias.*;

public class Productor {
	
	private int [] PlataEuropa, PlataAsia;
	private MinaPlataInterfaz Vista;
	static final int REGULAR = 0, BUENA = 1, EXCELENTE = 2;
	
	public Productor(MinaPlataInterfaz Vista) {
		this.Vista = Vista;
		PlataEuropa = new int [3];
		PlataAsia = new int [3];
		CalculaPlata();
	}
	
	private void CalculaPlata() {
		int Total = Rutinas.nextInt(10000, 20000);
		
		PlataEuropa[REGULAR] = (Total / 2) * 3 / 10;
		PlataEuropa[BUENA] = (Total / 2) * 6 / 10;
		PlataEuropa[EXCELENTE] = (Total / 2) * 1 / 10;
		
		PlataAsia[REGULAR] = PlataEuropa[REGULAR];
		PlataAsia[BUENA] = PlataEuropa[BUENA];
		PlataAsia[EXCELENTE] = PlataEuropa[EXCELENTE];
	}
	
	public void PidePlata(int Cantidad, int Calidad, String Continente) {
		if(Continente.compareTo("Europeo") == 0)
			if(Cantidad > PlataEuropa[Calidad]) {
				Vista.ActualizaConsola("CONSECION", Continente, PlataEuropa[Calidad], Calidad);
				PlataEuropa[Calidad] = 0;
			}
			else {
				Vista.ActualizaConsola("CONSECION", Continente, Cantidad, Calidad);
				PlataEuropa[Calidad] -= Cantidad;
			}
		else
			if(Cantidad > PlataAsia[Calidad]) {
				Vista.ActualizaConsola("CONSECION", Continente, PlataAsia[Calidad], Calidad);
				PlataAsia[Calidad] = 0;
			}
			else {
				Vista.ActualizaConsola("CONSECION", Continente, Cantidad, Calidad);
				PlataAsia[Calidad] -= Cantidad;
			}
		try {Thread.sleep(25);} catch(InterruptedException IE) {}
	}
	
	public boolean HayaPlata(String Continente) {
		if(Continente.compareTo("Europeo") == 0)
			return !(PlataEuropa[REGULAR] == 0 || PlataEuropa[BUENA] == 0 || PlataEuropa[EXCELENTE] == 0);
		return !(PlataAsia[REGULAR] == 0 || PlataAsia[BUENA] == 0 || PlataAsia[EXCELENTE] == 0);
	}
}
