package FilosofosComensales;

public class AplFilosofosComensales {
	
	public AplFilosofosComensales() {
		FilosofosInterfaz Vista = new FilosofosInterfaz();
		FilosofosModeloControlador MC = new FilosofosModeloControlador(Vista);
		Vista.setControlador(MC);
		Vista.Muestra();
	}
	
	public static void main(String [] a) {
		new AplFilosofosComensales();
	}
}
