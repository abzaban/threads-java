package Aeropuerto;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import Utilerias.*;

public class AeropuertoInterfaz extends JFrame {
	
	private JButton BtnInicia;
	private JLabel EtqTurnoActual, EtqFracasos;
	private Graphics g;
	private Image BackBuffer;
	private Vector<Avion> VAviones;
	private int TurnoActual, Fracasos, N;
	private Refrescador Refresh;
	
	public AeropuertoInterfaz() {
		super("Aeropuerto Internacional de la Ciudad de México");
		N = Rutinas.nextInt(2, 10);
		TurnoActual = 1;
		Fracasos = 0;
		HazInterfaz();
		VAviones = new Vector<Avion>();
		Refresh = new Refrescador(this, 30);
	}
	
	public void HazInterfaz() {
		setSize(700, 600);
		setLocationRelativeTo(null);
		setLayout(null);
		
		BtnInicia = new JButton("Iniciar");
		BtnInicia.setBounds(getWidth() - 100, 0, 100, 50);
		add(BtnInicia);
		
		EtqTurnoActual = new JLabel("Turno actual: "+TurnoActual);
		EtqTurnoActual.setBounds(0, 0, 100, 50);
		add(EtqTurnoActual);
		
		EtqFracasos = new JLabel("Intentos del último avión aterrizado: "+Fracasos);
		EtqFracasos.setBounds(101, 0, 250, 50);
		add(EtqFracasos);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(BackBuffer, 0, 101, getWidth(), getHeight(), null);
		Dibuja();
	}
	
	public void Dibuja() {
		super.paint(g);
		g.drawImage(Rutinas.AjustarImagen("ImagenesAeropuerto/Pista.jpg", getWidth(), getHeight()).getImage(), 0, 0, null);
		for(int i = 0 ; i < VAviones.size() ; i++)
			VAviones.get(i).Dibuja(g);
	}
	
	public void IniciaAviones() {
		Elemento S = new Elemento(1);
		int x, y = 101;
		for(int i = 0 ; i < N ; i++) {
			x = Rutinas.nextInt(0, getWidth() - Avion.TAMAÑO);
			VAviones.add(new Avion(x, y, (i+1), S, this));
			VAviones.get(i).start();
		}
	}
	
	public int getTurnoSiguiente() {
		return TurnoActual;
	}
	
	public void EliminaAvion(Avion Aux) {
		VAviones.remove(Aux);
		
		if(TurnoActual == N)
			EtqTurnoActual.setText("Finalizado");
		else {
			TurnoActual++;
			EtqTurnoActual.setText("Turno actual: "+TurnoActual);
		}
		
		Fracasos = Aux.getIntentos();
		EtqFracasos.setText("Intentos del último avión aterrizado: "+Fracasos);
	}
	
	public void setControlador(AeropuertoControlador C) {
		BtnInicia.addActionListener(C);
	}
	
	public void Muestra() {
		setVisible(true);
		BackBuffer = createImage(getWidth(), getHeight());
		g = BackBuffer.getGraphics();
		Refresh.start();
	}
}
