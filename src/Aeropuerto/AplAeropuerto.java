package Aeropuerto;

public class AplAeropuerto {
	
	public AplAeropuerto() {
		AeropuertoInterfaz Vista = new AeropuertoInterfaz();
		AeropuertoControlador Controlador = new AeropuertoControlador(Vista);
		Vista.setControlador(Controlador);
		Vista.Muestra();
	}
	
	public static void main(String [] a) {
		new AplAeropuerto();
	}
}
