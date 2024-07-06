package Tierras;
import javax.swing.*;

import Utilerias.Rutinas;

import java.awt.*;

public class TierrasVista extends JFrame {
	
	private JButton BtnInicio;
	private Hectarea [] VHectareas;
	private Graphics g;
	private Image backbuffer = null;
	
	public TierrasVista() {
		super("Herencia");
		HazInterfaz();
	}
	
	public void HazInterfaz() {
		setLayout(null);
		setSize(700, 800);
		
		BtnInicio = new JButton("Inicio");
		BtnInicio.setBounds(getWidth() - 100, 0, 100, 50);
		add(BtnInicio);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	public void setHectareas(Hectarea [] VH) {
		VHectareas = VH;
		Dibuja();
	}
	
	public void Dibuja() {
		super.paint(g);
		int x, y;
		if(VHectareas == null)
			return;
		for(int i = 0 ; i < VHectareas.length ; i++) {
			x = (i % 10) * (getWidth() / 10);
			y = (i / 10) * (getWidth() / 10) + BtnInicio.getWidth();
			g.drawImage(Rutinas.AjustarImagen("ImagenesTierras/tierra" + VHectareas[i].getCalidad() + ".png",
					    getWidth() / 10, getWidth() / 10).getImage(), x, y, null);
			g.drawImage(Rutinas.AjustarImagen("ImagenesTierras/hermano" + VHectareas[i].getDueño() + ".png",
					    getWidth() / 10, getWidth() / 10).getImage(), x, y, null);
		}
		repaint();
	}
	
	public void MuestraHectareasXHijo(int [] V) {
		JDialog JD = new JDialog();
		JD.setLayout(new GridLayout(0, 1));
		JD.setSize(300, 300);
		
		for(int i = 0 ; i < V.length ; i++) {
			JD.add(new JLabel("Hijo "+(i+1)+" heredó "+V[i]+" hectáreas", JLabel.CENTER));
		}
		
		JD.setLocationRelativeTo(null);
		JD.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JD.setResizable(false);
		JD.setModal(true);
		JD.setVisible(true);
	}
	
	public void paint(Graphics g) {
		g.drawImage(backbuffer, 0, 0, getWidth(), getHeight(), this);
	}
	
	public void setControlador(TierrasControlador C) {
		BtnInicio.addActionListener(C);
	}
	
	public void Muestra() {
		setVisible(true);
		backbuffer = createImage(getWidth(), getHeight());
		g = backbuffer.getGraphics();
		Dibuja();
	}
	
	public JButton getBtnInicio() {
		return BtnInicio;
	}
}
