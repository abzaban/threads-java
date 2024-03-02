package PlantaEnsambladora;
import java.awt.event.*;
import javax.swing.*;

public class PlantaModeloControlador implements ActionListener {
	
	private PlantaVista Vista;
	
	public PlantaModeloControlador(PlantaVista Vista) {
		this.Vista = Vista;
	}
	
	public void actionPerformed(ActionEvent Evt) {
		JButton aux = ((JButton)Evt.getSource());
		if(aux == Vista.getBtnInicia()) {
			Vista.getBtnInicia().setEnabled(false);
			Vista.getBtnDetener().setEnabled(true);
			Vista.iniciaHilos();
			return;
		}
		if(aux == Vista.getBtnDetener()) {
			Vista.detenerHilos();
			return;
		}
		Vista.continuar();
	}
}

