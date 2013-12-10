package modules.planificacion.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Politicas implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public List<Base> obtenerLineamientos()
	{
		List<Base> lineamientos=new ArrayList<Base>();
		Base obj=new Base();
		
		obj.setId(1L);
		obj.setNombre("Fomentar, institucionalizar y fortalecer los espacios"
				+ "de dialogo social, concertaci�n y negociaci�n entre los actores y otros"
				+ "agentes vinculados a la formaci�n profesional, que favorezcan el mejoramiento"
				+ "de su calidad, la adecuaci�n ocupacional, la equidad en el acceso y la "
				+ "inserci�n al mercado laboral competitivo.");
		lineamientos.add(obj);
		obj=new Base();
		obj.setId(2L);
		obj.setNombre("Promover la formaci�n profesional de calidad con valores, con una"
				+ "perspectiva competitiva, participativa y con equidad, desde los niveles"
				+ "b�sicos hasta el nivel superior, que desarrolle competencias laborales"
				+ "y capacidades emprendedoras, que responda a las caracteristicas y demandas"
				+ "locales, regionales en el marco de la descentralizaci�n y el"
				+ "mejoramiento de la calidad de vida de la poblaci�n.");
		lineamientos.add(obj);
		return lineamientos;
	}
	
	public List<Base> obtenerVariables(Long tipo)
	{
		List<Base> variables=new ArrayList<Base>();
		Base obj=new Base();
		
		if(tipo.longValue()==1L)
		{
			obj.setId(1L);
			obj.setNombre("Valoraci�n y reconocimiento social");
			variables.add(obj);
			obj=new Base();
			obj.setId(2L);
			obj.setNombre("Espacios de di�logo y concertaci�n");
			variables.add(obj);
		}
		else
		{
			obj.setId(1L);
			obj.setNombre("Accesibilidad y Democratizaci�n");
			variables.add(obj);
			obj=new Base();
			obj.setId(2L);
			obj.setNombre("Oferta Formativa");
			variables.add(obj);
			obj=new Base();
			obj.setId(3L);
			obj.setNombre("Curr�culo actualizado y diversificado");
			variables.add(obj);
			obj=new Base();
			obj.setId(4L);
			obj.setNombre("Certificaci�n de competencias laborales");
			variables.add(obj);
			obj=new Base();
			obj.setId(5L);
			obj.setNombre("Enfoque y Metodolog�a");
			variables.add(obj);
			obj=new Base();
			obj.setId(6L);
			obj.setNombre("Actualizaci�n y Capacitaci�n de Agentes");
			variables.add(obj);
			obj=new Base();
			obj.setId(7L);
			obj.setNombre("Evaluaci�n de Desempe�o de Agentes");
			variables.add(obj);
			obj=new Base();
			obj.setId(8L);
			obj.setNombre("Gesti�n Educativa Participativa");
			variables.add(obj);
		}
		return variables;
	}
	
	public String obtenerNombre(Long tipo, Long subtipo)
	{
		if(tipo.longValue()==1L && subtipo.longValue()==0L)	{return "Fomentar, institucionalizar y fortalecer los espacios"
				+ "de dialogo social, concertaci�n y negociaci�n entre los actores y otros"
				+ "agentes vinculados a la formaci�n profesional, que favorezcan el mejoramiento"
				+ "de su calidad, la adecuaci�n ocupacional, la equidad en el acceso y la "
				+ "inserci�n al mercado laboral competitivo.";}
		if(tipo.longValue()==2L && subtipo.longValue()==0L)	{return "Promover la formaci�n profesional de calidad con valores, con una"
				+ "perspectiva competitiva, participativa y con equidad, desde los niveles"
				+ "b�sicos hasta el nivel superior, que desarrolle competencias laborales"
				+ "y capacidades emprendedoras, que responda a las caracteristicas y demandas"
				+ "locales, regionales en el marco de la descentralizaci�n y el"
				+ "mejoramiento de la calidad de vida de la poblaci�n.";}
		if(tipo.longValue()==1L && subtipo.longValue()==1L)	{return "Valoraci�n y reconocimiento social";}
		if(tipo.longValue()==1L && subtipo.longValue()==2L)	{return "Espacios de di�logo y concertaci�n";}
		if(tipo.longValue()==2L && subtipo.longValue()==1L)	{return "Accesibilidad y Democratizaci�n";}
		if(tipo.longValue()==2L && subtipo.longValue()==2L)	{return "Oferta Formativa";}
		if(tipo.longValue()==2L && subtipo.longValue()==3L)	{return "Curr�culo actualizado y diversificado";}
		if(tipo.longValue()==2L && subtipo.longValue()==4L)	{return "Certificaci�n de competencias laborales";}
		if(tipo.longValue()==2L && subtipo.longValue()==5L)	{return "Enfoque y Metodolog�a";}
		if(tipo.longValue()==2L && subtipo.longValue()==6L)	{return "Actualizaci�n y Capacitaci�n de Agentes";}
		if(tipo.longValue()==2L && subtipo.longValue()==7L)	{return "Evaluaci�n de Desempe�o de Agentes";}
		if(tipo.longValue()==2L && subtipo.longValue()==8L)	{return "Gesti�n Educativa Participativa";}
		return "";
	}
	
	
}
	