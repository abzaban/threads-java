package Tierras;

public class AplTierras {
	
	public AplTierras() {
		TierrasVista Vista = new TierrasVista();
		TierrasModelo Modelo = new TierrasModelo();
		TierrasControlador Controlador = new TierrasControlador(Vista, Modelo);
		Vista.setControlador(Controlador);
		Vista.Muestra();
	}
	
	public static void main(String [] a) {
		new AplTierras();
	}
}
