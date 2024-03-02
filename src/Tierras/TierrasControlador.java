package Tierras;
import java.awt.event.*;

public class TierrasControlador implements ActionListener {
	
	private TierrasVista Vista;
	private TierrasModelo Modelo;
	
	public TierrasControlador(TierrasVista Vista, TierrasModelo Modelo) {
		this.Vista = Vista;
		this.Modelo = Modelo;
	}
	
	public void actionPerformed(ActionEvent Evt) {
		Vista.getBtnInicio().setEnabled(false);
		Modelo.Repartir();
		Vista.setHectareas(Modelo.getHectareas());
		Vista.MuestraHectareasXHijo(Modelo.getHectareasXHijo());
	}
}
