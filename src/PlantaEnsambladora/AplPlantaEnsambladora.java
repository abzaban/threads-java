package PlantaEnsambladora;

public class AplPlantaEnsambladora {
	
	public static void main(String[] args) {
		PlantaVista vista = new PlantaVista();
		PlantaModeloControlador mc = new PlantaModeloControlador(vista);
		vista.Arranca();
	}
}
