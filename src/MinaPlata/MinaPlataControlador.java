package MinaPlata;
import java.awt.event.*;

public class MinaPlataControlador implements ActionListener {
	
	private MinaPlataInterfaz Vista;
	
	public MinaPlataControlador(MinaPlataInterfaz Vista) {
		this.Vista = Vista;
	}
	
	public void actionPerformed(ActionEvent Evt) {
		Vista.IniciaHilos();
	}
}
