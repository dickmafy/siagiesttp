package modules.horario.domain;
import java.io.Serializable;

public class Horario implements Serializable
{
	private static final long serialVersionUID = 1L;
	private	Long 	inicio;
	private	Long 	fin;
	
	private	boolean	freeLunes;
	private	boolean	freeMartes;
	private	boolean	freeMiercoles;
	private	boolean	freeJueves;
	private	boolean	freeViernes;
	private	boolean	freeSabado;
	
	private boolean lunes;
	private boolean martes;
	private boolean miercoles;
	private boolean jueves;
	private boolean viernes;
	private boolean sabado;
	
	private	String 	nombreInicio;
	private	String 	nombreFin;
	private String	nombreHoras;
	
	public boolean isLunes() 								{return lunes;}
	public void setLunes(boolean lunes) 					{this.lunes = lunes;}
	
	public boolean isMartes() 								{return martes;}
	public void setMartes(boolean martes) 					{this.martes = martes;}
	
	public boolean isMiercoles() 							{return miercoles;}
	public void setMiercoles(boolean miercoles) 			{this.miercoles = miercoles;}
	
	public boolean isJueves() 								{return jueves;}
	public void setJueves(boolean jueves) 					{this.jueves = jueves;}
	
	public boolean isViernes() 								{return viernes;}
	public void setViernes(boolean viernes) 				{this.viernes = viernes;}
	
	public boolean isSabado() 								{return sabado;}
	public void setSabado(boolean sabado) 					{this.sabado = sabado;}
	
	public Long getInicio() 								{return inicio;}
	public void setInicio(Long inicio) 						{this.inicio = inicio;}
	
	public Long getFin() 									{return fin;}
	public void setFin(Long fin) 							{this.fin = fin;}
	
	public boolean isFreeLunes() 							{return freeLunes;}
	public void setFreeLunes(boolean freeLunes) 			{this.freeLunes = freeLunes;}
	
	public boolean isFreeMartes() 							{return freeMartes;}
	public void setFreeMartes(boolean freeMartes) 			{this.freeMartes = freeMartes;}
	
	public boolean isFreeMiercoles() 						{return freeMiercoles;}
	public void setFreeMiercoles(boolean freeMiercoles) 	{this.freeMiercoles = freeMiercoles;}
	
	public boolean isFreeJueves() 							{return freeJueves;}
	public void setFreeJueves(boolean freeJueves) 			{this.freeJueves = freeJueves;}
	
	public boolean isFreeViernes() 							{return freeViernes;}
	public void setFreeViernes(boolean freeViernes) 		{this.freeViernes = freeViernes;}
	
	public boolean isFreeSabado() 							{return freeSabado;}
	public void setFreeSabado(boolean freeSabado) 			{this.freeSabado = freeSabado;}
	
	public String getNombreInicio()							{return nombreHora(inicio);}
	public String getNombreFin() 							{return nombreHora(fin);}
	
	public void cleanDia()
	{
		lunes=false;
		martes=false;
		miercoles=false;
		jueves=false;
		viernes=false;
		sabado=false;
		
		freeLunes=true;
		freeMartes=true;
		freeMiercoles=true;
		freeJueves=true;
		freeViernes=true;
		freeSabado=true;
	}
	
	public void setFreeDia(Long codigo) 							
	{
		if(codigo.longValue()==1L)	{freeLunes=false;}
		if(codigo.longValue()==2L)	{freeMartes=false;}
		if(codigo.longValue()==3L)	{freeMiercoles=false;}
		if(codigo.longValue()==4L)	{freeJueves=false;}
		if(codigo.longValue()==5L)	{freeViernes=false;}
		if(codigo.longValue()==6L)	{freeSabado=false;}
	}
	public void setDia(Long codigo) 							
	{
		if(codigo.longValue()==1L)	{lunes=true;}
		if(codigo.longValue()==2L)	{martes=true;}
		if(codigo.longValue()==3L)	{miercoles=true;}
		if(codigo.longValue()==4L)	{jueves=true;}
		if(codigo.longValue()==5L)	{viernes=true;}
		if(codigo.longValue()==6L)	{sabado=true;}
	}
	
	public String nombreHora(Long codigo)
	{
		if(codigo.longValue()==1L)	return "Hora 01";
		if(codigo.longValue()==2L)	return "Hora 02";
		if(codigo.longValue()==3L)	return "Hora 03";
		if(codigo.longValue()==4L)	return "Hora 04";
		if(codigo.longValue()==5L)	return "Hora 05";
		if(codigo.longValue()==6L)	return "Hora 06";
		if(codigo.longValue()==7L)	return "Hora 07";		
		if(codigo.longValue()==8L)	return "Hora 08";		
		if(codigo.longValue()==9L)	return "Hora 09";
		if(codigo.longValue()==10L)	return "Hora 10";		
		if(codigo.longValue()==11L)	return "Hora 11";		
		if(codigo.longValue()==12L)	return "Hora 12";
		if(codigo.longValue()==13L)	return "Hora 13";
		if(codigo.longValue()==14L)	return "Hora 14";
		if(codigo.longValue()==15L)	return "Hora 15";
		return "";
	}
	
	public String getNombreHoras()
	{return nombreHora(inicio)+" - "+nombreHora(fin);}
}