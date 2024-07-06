package Tierras;
import Utilerias.*;
import java.util.*;

public class Hectarea extends Elemento {
	
	private String CalidadTierra;
	private int Dueño;
	
	public Hectarea() {
		super(1);
		Dueño = 0;
		CalidadTierra = AveriguaCalidad();
	}
	
	private String AveriguaCalidad() {
		double NAleatorio = new Random().nextDouble();
		if(NAleatorio < 0.33)
			return "Mala";
		
		if(NAleatorio < 0.66)
			return "Buena";
		
		return "Excelente";
	}
	
	public String getCalidad() {
		return CalidadTierra;
	}
	
	public void setDueño(int Dueño) {
		this.Dueño = Dueño;
	}
	
	public int getDueño() {
		return Dueño;
	}
}
