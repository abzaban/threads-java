package FilosofosComensales;
import javax.swing.*;
import Utilerias.*;
import java.awt.*;

public class FilosofosInterfaz extends JFrame {
	
	private JButton BtnIniciar;
	private Graphics g;
	private Image BackBuffer;
	private Filosofo [] VFilosofos;
	private Imagen [] VTenedores;
	private Imagen [] VPlatos;
	private Refrescador Refresh;
	private static final int TAMFILOSOFO = 80, TAMPLATO = 100, TAMTENEDOR = 80;
	
	public FilosofosInterfaz() {
		super("Filósofos Comensales");
		HazInterfaz();
		VFilosofos = new Filosofo [5];
		VTenedores = new Imagen [5];
		VPlatos = new Imagen [5];
		CreaFilosofos();
		CreaPlatos();
		CreaTenedores();
		Refresh = new Refrescador(this, 10);
	}
	
	public void HazInterfaz() {
		setSize(800, 700);
		setLocationRelativeTo(null);
		setLayout(null);
		
		BtnIniciar = new JButton("Iniciar");
		BtnIniciar.setBounds(getWidth() - 100, 0, 100, 50);
		add(BtnIniciar);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(BackBuffer, 0, 0, getWidth(), getHeight(), null);
		Dibuja();
	}
	
	public void Dibuja() {
		super.paint(g);
//		g.drawImage(Rutinas.AjustarImagen("ImagenesFilosofosComensales/Mesa.png", getWidth() - 250,
//					getHeight() - 150).getImage(), 125, 75, null);
		g.setColor(new Color(100, 75, 50));
		g.fillOval(125, 75, getWidth() - 250, getHeight() - 150);
		for( int i = 0 ; i < VTenedores.length ; i++) {
			g.drawImage(Rutinas.AjustarImagen("ImagenesFilosofosComensales/Plato.png", TAMPLATO,
					TAMPLATO).getImage(), VPlatos[i].getXActual(), VPlatos[i].getYActual(), null);
			
			g.drawImage(Rutinas.AjustarImagen("ImagenesFilosofosComensales/Tenedor.png", TAMTENEDOR,
					TAMTENEDOR).getImage(), VTenedores[i].getXActual(), VTenedores[i].getYActual(), null);
			
			g.drawImage(VFilosofos[i].getImagen(), VFilosofos[i].getX(), VFilosofos[i].getY(), null);
		}
	}
	
	public void MueveTenedor(Imagen Tenedor, int XObjetivo, int YObjetivo) {
//		while(Tenedor.getXActual() != XObjetivo && Tenedor.getYActual() != YObjetivo) {
//			System.out.println(Tenedor.getId());
//			if(Tenedor.getXActual() > XObjetivo) {
//				Tenedor.setXActual(Tenedor.getXActual() - 10);
//				if(Tenedor.getXActual() < XObjetivo)
//					Tenedor.setXActual(XObjetivo);
//			}
//			
//			if(Tenedor.getYActual() > YObjetivo) {
//				Tenedor.setYActual(Tenedor.getYActual() - 10);
//				if(Tenedor.getYActual() < YObjetivo)
//					Tenedor.setYActual(YObjetivo);
//			}
//			
//			if(Tenedor.getXActual() < XObjetivo) {
//				Tenedor.setXActual(Tenedor.getXActual() + 10);
//				if(Tenedor.getXActual() > XObjetivo)
//					Tenedor.setXActual(XObjetivo);
//			}
//			
//			if(Tenedor.getYActual() < YObjetivo) {
//				Tenedor.setYActual(Tenedor.getYActual() + 10);
//				if(Tenedor.getYActual() > YObjetivo)
//					Tenedor.setYActual(YObjetivo);
//			}
//			Dibuja();
//			try {Thread.sleep(10);} catch(InterruptedException IE) {}
//		}
		Tenedor.setXActual(XObjetivo);
		Tenedor.setYActual(YObjetivo);
		Dibuja();
	}
	
	private void CreaFilosofos() {
		Elemento [] VE = new Elemento [5];
		for(int i = 0 ; i < VE.length ; i++)
			VE[i] = new Elemento(1);
		
		VFilosofos[0] = new Filosofo(getWidth() / 2 - 200 - TAMFILOSOFO / 2, 40, Rutinas.AjustarImagen("ImagenesFilosofosComensales/Filosofo1.png", TAMFILOSOFO, TAMFILOSOFO).getImage(), VE, this);
		VFilosofos[1] = new Filosofo(getWidth() / 2 + 200 - TAMFILOSOFO / 2, 40, Rutinas.AjustarImagen("ImagenesFilosofosComensales/Filosofo2.png", TAMFILOSOFO, TAMFILOSOFO).getImage(), VE, this);
		VFilosofos[2] = new Filosofo(getWidth() - TAMFILOSOFO, getHeight() / 2 - TAMFILOSOFO / 2, Rutinas.AjustarImagen("ImagenesFilosofosComensales/Filosofo3.png", TAMFILOSOFO, TAMFILOSOFO).getImage(), VE, this);
		VFilosofos[3] = new Filosofo(getWidth() / 2 - TAMFILOSOFO / 2, getHeight() - TAMFILOSOFO, Rutinas.AjustarImagen("ImagenesFilosofosComensales/Filosofo5.png", TAMFILOSOFO, TAMFILOSOFO).getImage(), VE, this);
		VFilosofos[4] = new Filosofo(0, getHeight() / 2 - TAMFILOSOFO / 2,Rutinas.AjustarImagen("ImagenesFilosofosComensales/Filosofo4.png", TAMFILOSOFO, TAMFILOSOFO).getImage(), VE, this);
	}
	
	private void CreaPlatos() {
		VPlatos[0] = new Imagen(VFilosofos[0].getX() + 75, VFilosofos[0].getY() + 75, 0);
		VPlatos[1] = new Imagen(VFilosofos[1].getX() - 75, VFilosofos[1].getY() + 75, 1);
		VPlatos[2] = new Imagen(VFilosofos[2].getX() - 150, VFilosofos[2].getY(), 2);
		VPlatos[3] = new Imagen(VFilosofos[3].getX(), VFilosofos[3].getY() - 150, 3);
		VPlatos[4] = new Imagen(VFilosofos[4].getX() + 150, VFilosofos[4].getY(), 4);
	}
	
	private void CreaTenedores() {
		VTenedores[0] = new Imagen(VPlatos[0].getXActual() - 75, VPlatos[0].getYActual() + 75, 0);
		VTenedores[1] = new Imagen(VPlatos[1].getXActual() - 120, VPlatos[1].getYActual(), 1);
		VTenedores[2] = new Imagen(VPlatos[2].getXActual(), VPlatos[2].getYActual() - 100, 2);
		VTenedores[3] = new Imagen(VPlatos[3].getXActual() + 125, VPlatos[3].getYActual() - 50, 3);
		VTenedores[4] = new Imagen(VPlatos[4].getXActual() + 75, VPlatos[4].getYActual() + 125, 4);
	}
	
	public void setControlador(FilosofosModeloControlador MC) {
		BtnIniciar.addActionListener(MC);
	}
	
	public void Muestra() {
		setVisible(true);
		BackBuffer = createImage(getWidth(), getHeight());
		g = BackBuffer.getGraphics();
		Refresh.start();
	}
	
	public JButton getBtnInicia() {
		return BtnIniciar;
	}
	
	public Filosofo[] getVFilosofos() {
		return VFilosofos;
	}
	
	public Imagen[] getVPlatos() {
		return VPlatos;
	}
	
	public Imagen[] getVTenedores() {
		return VTenedores;
	}
	
	public void setTenedores(Imagen [] VTenedores) {
		this.VTenedores = VTenedores;
	}
}
