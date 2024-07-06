package Aeropuerto;
import java.awt.event.*;
import javax.swing.*;

public class AeropuertoControlador implements ActionListener {
	
	private AeropuertoInterfaz Vista;
	
	public AeropuertoControlador(AeropuertoInterfaz Vista) {
		this.Vista = Vista;
	}
	
	public void actionPerformed(ActionEvent Evt) {
		Vista.IniciaAviones();
		((JButton)(Evt.getSource())).setEnabled(false);
	}
}
