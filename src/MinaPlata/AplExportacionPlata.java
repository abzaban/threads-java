package MinaPlata;

public class AplExportacionPlata {
	
	public AplExportacionPlata() {
		MinaPlataInterfaz Vista = new MinaPlataInterfaz();
		MinaPlataControlador Controlador = new MinaPlataControlador(Vista);
		Vista.setControlador(Controlador);
		Vista.Muestra();
	}
	
	public static void main(String [] a) {
		new AplExportacionPlata();
	}
}
