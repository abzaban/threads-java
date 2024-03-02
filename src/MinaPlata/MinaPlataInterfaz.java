package MinaPlata;
import javax.swing.*;

import Utilerias.*;

public class MinaPlataInterfaz extends JFrame {
	
	private JButton BtnIniciar;
	private JTextArea TxtAreaConsola;
	private Pais [] Paises; 
	private Refrescador Refresher;
	
	public MinaPlataInterfaz() {
		super("Exportación de plata");
		HazInterfaz();
		InicializaPaises();
	}
	
	private void HazInterfaz() {
		setSize(600, 800);
		setLocationRelativeTo(null);
		setLayout(null);
		
		BtnIniciar = new JButton("Iniciar");
		BtnIniciar.setBounds(getWidth() - 100, 0, 100, 50);
		
		TxtAreaConsola = new JTextArea();
//		TxtAreaConsola.setBounds(0, getHeight() - 300, getWidth(), 300);
		TxtAreaConsola.setEditable(false);
		Refresher = new Refrescador(this,30);
		JScrollPane Barrita = new JScrollPane(TxtAreaConsola);
		Barrita.setBounds(0, getHeight() - 300, getWidth(), 300);
		
		
		add(BtnIniciar);
		add(Barrita);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	public void ActualizaConsola(String Tipo, String Continente, int Cantidad, int Calidad) {
		if(Tipo.compareTo("PETICION") == 0) {
			TxtAreaConsola.setText("Pais " + Continente + " solicito " + Cantidad + " toneladas de plata de calidad " +
								  (Calidad == 0 ? "Regular" : Calidad == 1 ? "Buena" : "Excelente") + "\n" + TxtAreaConsola.getText());
			return;
		}
		TxtAreaConsola.setText("Pais " + Continente + " recibio " + Cantidad + " toneladas de plata de calidad " +
							  (Calidad == 0 ? "Regular" : Calidad == 1 ? "Buena" : "Excelente") + "\n" + TxtAreaConsola.getText());
	}
	
	public void IniciaHilos() {
		BtnIniciar.setEnabled(false);
		repaint();
		for(int i = 0 ; i < Paises.length ; i++)
			Paises[i].start();
	}
	
	private void InicializaPaises() {
		int NEuropeos = Rutinas.nextInt(10, 30);
		int NAsiaticos = Rutinas.nextInt(5, 7);
		
		Paises = new Pais [NEuropeos + NAsiaticos];
		Productor P = new Productor(this);
		Semaforo S = new Semaforo(1);
		
		for(int i = 0 ; i < NEuropeos ; i++)
			Paises[i] = new Pais(this, P, "Europeo", 0, 0,S);
		for(int i = NEuropeos ; i < NEuropeos + NAsiaticos ; i++)
			Paises[i] = new Pais(this, P, "Asiatico", 0, 0,S);
	}
	
	public void setControlador(MinaPlataControlador C) {
		BtnIniciar.addActionListener(C);
	}
	
	public void Muestra() {
		setVisible(true);
		Refresher.start();
	}
}

