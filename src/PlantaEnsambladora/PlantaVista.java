package PlantaEnsambladora;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import Utilerias.*;

public class PlantaVista extends JFrame {
	
	private JButton BtnInicia, BtnDetener,BtnContinuar;
	private MiCanvas canvas;
	private Vector<Carro> VCarros;
	private Vector<Robot> VRobots;
	private int N, cuenta;
	public static final int NESTACIONES = 7;
	private Semaforo Solicitud;
	private Semaforo[] VSemaforos;
	private JLabel jLProduccion;
	private boolean Pausa;
	private Refrescador refrescador;
	
	public PlantaVista() {
		super("Planta Ensambladora NOSSAN");
		HazInterfaz();
		VCarros = new Vector<Carro>();
		VRobots = new Vector<Robot>();
		N = Rutinas.nextInt(8,15);
		iniciaRobots();
		hazEscuchas();
		Solicitud = new Semaforo(1);
		Pausa = false;
		refrescador = new Refrescador(this,30);
	}
	
	public Semaforo getSolicitud() {
		return Solicitud;
	}

	public void setSolicitud(Semaforo solicitud) {
		Solicitud = solicitud;
	}

	private void hazEscuchas() {
		PlantaModeloControlador control = new PlantaModeloControlador(this);
		BtnInicia.addActionListener(control);
		BtnDetener.addActionListener(control);
		BtnContinuar.addActionListener(control);
	}

	private void iniciaRobots() {
		int x = getWidth()/20;
		int t = getCanvas().getHeight()/N;
		int y;
		for(int r = 0; r < 5; r++) {
			y = getCanvas().getHeight()*r/N;
			VRobots.add(new Robot(Rutinas.AjustarImagen("ImagenesPlanta/ChasisRobot.png",t,t).getImage(), t, x, y,1));
			VRobots.get(r).setLinea(r+1);
		}
		x += getWidth() / NESTACIONES;
		for(int r = 0; r < 4; r++) {
			y = getCanvas().getHeight()*r/N;
			VRobots.add(new Robot(Rutinas.AjustarImagen("ImagenesPlanta/motorRobot.png",t,t).getImage(), t, x, y,2));
			VRobots.get(r).setLinea(r+1);
		}
		x += getWidth() / NESTACIONES;
		for(int r = 0; r < 2; r++) {
			y = getCanvas().getHeight()*r/N;
			VRobots.add(new Robot(Rutinas.AjustarImagen("ImagenesPlanta/transmisionRobot.png",t,t).getImage(), t, x, y,3));
			VRobots.get(r).setLinea(r+1);
		}
		x += getWidth() / NESTACIONES;
		for(int r = 0; r < 3; r++) {
			y = getCanvas().getHeight()*r/N;
			VRobots.add(new Robot(Rutinas.AjustarImagen("ImagenesPlanta/carroceriaRobot.png",t,t).getImage(), t, x, y,4));
			VRobots.get(r).setLinea(r+1);
		}
		x += getWidth() / NESTACIONES;
		for(int r = 0; r < 3; r++) {
			y = getCanvas().getHeight()*r/N;
			VRobots.add(new Robot(Rutinas.AjustarImagen("ImagenesPlanta/interioresRobot.png",t,t).getImage(), t, x, y,5));
			VRobots.get(r).setLinea(r+1);
		}
		x += getWidth() / NESTACIONES;
		for(int r = 0; r < N; r++) {
			y = getCanvas().getHeight()*r/N;
			VRobots.add(new Robot(Rutinas.AjustarImagen("ImagenesPlanta/llantasRobot.png",t,t).getImage(), t, x, y,6));
			VRobots.get(r).setLinea(r+1);
		}
		x += getWidth() / NESTACIONES;
		for(int r = 0; r < N; r++) {
			y = getCanvas().getHeight()*r/N;
			VRobots.add(new Robot(Rutinas.AjustarImagen("ImagenesPlanta/pruebaRobot.png",t,t).getImage(), t, x, y,7));
			VRobots.get(r).setLinea(r+1);
		}
	}

	public void HazInterfaz() {
		setLayout(null);
		setSize(1000, 750);
		BtnInicia = new JButton("Iniciar");
		BtnInicia.setBounds(getWidth() - 200,0,200,50);
		BtnContinuar = new JButton("Continuar");
		BtnContinuar.setBounds(getWidth() - 200,0,200,50);
		BtnDetener = new JButton("Detener");
		BtnDetener.setBounds(BtnInicia.getX() - 200,0,200,50);
		BtnDetener.setEnabled(false);
		setCanvas(new MiCanvas());
		getCanvas().setBounds(0, 50, getWidth(), getHeight()-100);
		jLProduccion = new JLabel("Produccion : " + cuenta);
		jLProduccion.setBounds(10,0,150,50);
		add(jLProduccion);
		add(getCanvas());
		add(BtnInicia);
		
		add(BtnDetener);
		
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void Arranca() {
		setVisible(true);
		getCanvas().iniciaGraficos();
	}
	
	public void LiberaRobot(int linea, int estacion) {
		for(int r=0;r<VRobots.size();r++) {
			if(VRobots.get(r).getLinea() == linea && VRobots.get(r).getEstacion() == estacion && VRobots.get(r).isOcupado()) {
				VRobots.get(r).setOcupado(false);
				return;
			}
		}
	}
	
	public void PideRobot(int linea, int estacion) {
		Robot aux = null;
		while(aux==null) {
			Solicitud.Espera();
			for(int r=0; r<VRobots.size();r++) {
				if(VRobots.get(r).getEstacion()==estacion && !VRobots.get(r).isOcupado()) {
					aux = VRobots.get(r);
					aux.setOcupado(true);
					aux.setLinea(linea);
					Solicitud.Libera();
					while(aux.mover(getCanvas().getHeight()*(linea-1)/N)) {
						try {Thread.sleep(10);} catch(InterruptedException IE) {}
					}
					return;
				}
			}
			Solicitud.Libera();
		}
	}
	
	public void retiraCarro(Carro C) {
			VCarros.remove(C);
			Solicitud.Espera();
			jLProduccion.setText("Produccion : " + (++cuenta));
			Solicitud.Libera();
	}
	
	public void generaCarro(int linea) {
		Carro aux = new Carro(VSemaforos, Rutinas.AjustarImagen("ImagenesPlanta/planosCarro.png", getCanvas().getHeight()/N, getCanvas().getHeight()/N).getImage(),
				-getCanvas().getHeight()/N, getCanvas().getHeight()*(linea-1)/N, linea, getCanvas().getHeight()/N, this);
		VCarros.add(aux);
		aux.start();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		canvas.repaint();
	}
	
	public JButton getBtnInicia() {
		return BtnInicia;
	}
	
	public void setBtnInicia(JButton btnInicia) {
		BtnInicia = btnInicia;
	}
	
	public void iniciaHilos() {
		VSemaforos = new Semaforo[7];
		VSemaforos[0] = new Semaforo(5);
		VSemaforos[1] = new Semaforo(4);
		VSemaforos[2] = new Semaforo(2);
		VSemaforos[3] = new Semaforo(3);
		VSemaforos[4] = new Semaforo(3);
		VSemaforos[5] = new Semaforo(N);
		VSemaforos[6] = new Semaforo(N);
		for (int i = 0; i < N; i++) {
			VCarros.add(new Carro(VSemaforos, Rutinas.AjustarImagen("ImagenesPlanta/planosCarro.png", getCanvas().getHeight()/N, getCanvas().getHeight()/N).getImage(),
					-getCanvas().getHeight()/N, getCanvas().getHeight()*i/N, i+1, getCanvas().getHeight()/N, this));
			VCarros.get(i).start();
		}		
		refrescador.start();
	}
	
	public void detenerHilos() {
		remove(BtnInicia);
		add(BtnContinuar);
		BtnDetener.setEnabled(false);
		BtnContinuar.setEnabled(true);
		repaint();
		Pausa = true;
	}
	
	public void continuar() {
		BtnContinuar.setEnabled(false);
		BtnDetener.setEnabled(true);
		Pausa = false;
	}

	public boolean isPausa() {
		return Pausa;
	}
	public JButton getBtnDetener() {
		return BtnDetener;
	}

	public JButton getBtnContinuar() {
		return BtnContinuar;
	}

	public MiCanvas getCanvas() {
		return canvas;
	}

	public void setCanvas(MiCanvas canvas) {
		this.canvas = canvas;
	}
	
	class MiCanvas extends Canvas {
		private Graphics dibujador;
		private Image backbuffer;
		
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(backbuffer,0,0,getWidth(),getHeight(),null);
			Dibuja();
		}
		
		public void Dibuja() {
			dibujador.drawImage(Rutinas.AjustarImagen("ImagenesPlanta/fondoNossanRobot.png", getWidth(), getHeight()).getImage(), 0, 0, null);
			
			for(int l=0;l<N;l++)
				dibujador.drawImage(Rutinas.AjustarImagen("ImagenesPlanta/cintaRobot.png", getWidth(), getHeight() / N).getImage(), 0, getHeight() * l / N, null);
				
			for(int i=0;i<VCarros.size();i++)
				VCarros.get(i).Dibujar(dibujador);
			
			for (int i = 0; i < VRobots.size(); i++)
				VRobots.get(i).dibuja(dibujador);
		}
		
		public void iniciaGraficos() {
			backbuffer = createImage(getWidth(), getHeight());
			dibujador = backbuffer.getGraphics();
		}
		
		public Graphics getDibujador() {
			return dibujador;
		}
		
		public Image getBackbuffer() {
			return backbuffer;
		}
	}
}
