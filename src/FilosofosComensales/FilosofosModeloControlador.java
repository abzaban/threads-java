package FilosofosComensales;
import java.awt.event.*;

import Utilerias.Rutinas;

public class FilosofosModeloControlador implements ActionListener {
	
	private FilosofosInterfaz Vista;
	private Filosofo [] VFilosofos;
	private Imagen [] VTenedores, VPlatos;
	
	public FilosofosModeloControlador(FilosofosInterfaz Vista) {
		this.Vista = Vista;
	}
	
	private void IniciaSimulacion() {
		VFilosofos = Vista.getVFilosofos();
		VTenedores = Vista.getVTenedores();
		VPlatos = Vista.getVPlatos();
		AsignaUtensilios();
		for(int i = 0 ; i < VFilosofos.length ; i++) {
			VFilosofos[i].start();
			System.out.println("Filosofo -----> "+VFilosofos[i].getX()+" "+VFilosofos[i].getY());
			System.out.println("Tenedor derecho ------> "+VFilosofos[i].getTenedorDer().getXInicial()+" "+VFilosofos[i].getTenedorDer().getYInicial());
			System.out.println("Tenedor izquierdo -------> "+VFilosofos[i].getTenedorIzq().getXInicial()+" "+VFilosofos[i].getTenedorIzq().getYInicial());
			System.out.println("Plato ------> "+VFilosofos[i].getPlato().getXInicial()+" "+VFilosofos[i].getPlato().getYInicial());
			System.out.println();
		}
	}
	
	public void AsignaUtensilios() {
		for(int i = 0 ; i < VFilosofos.length ; i++) {
			if(i + 1 == VFilosofos.length) {
				VFilosofos[i].setTenedorIzq(VTenedores[0]);
				VFilosofos[i].setTenedorDer(VTenedores[i]);
				VFilosofos[i].setPlato(VPlatos[i]);
				break;
			}
			VFilosofos[i].setTenedorIzq(VTenedores[i + 1]);
			VFilosofos[i].setTenedorDer(VTenedores[i]);
			VFilosofos[i].setPlato(VPlatos[i]);
		}
	}
	
	public void actionPerformed(ActionEvent Evt) {
		Vista.getBtnInicia().setEnabled(false);
		IniciaSimulacion();
	}
}
