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
				+ "de dialogo social, concertación y negociación entre los actores y otros"
				+ "agentes vinculados a la formación profesional, que favorezcan el mejoramiento"
				+ "de su calidad, la adecuación ocupacional, la equidad en el acceso y la "
				+ "inserción al mercado laboral competitivo.");
		lineamientos.add(obj);
		obj=new Base();
		obj.setId(2L);
		obj.setNombre("Promover la formación profesional de calidad con valores, con una"
				+ "perspectiva competitiva, participativa y con equidad, desde los niveles"
				+ "básicos hasta el nivel superior, que desarrolle competencias laborales"
				+ "y capacidades emprendedoras, que responda a las caracteristicas y demandas"
				+ "locales, regionales en el marco de la descentralización y el"
				+ "mejoramiento de la calidad de vida de la población.");
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
			obj.setNombre("Valoración y reconocimiento social");
			variables.add(obj);
			obj=new Base();
			obj.setId(2L);
			obj.setNombre("Espacios de diálogo y concertación");
			variables.add(obj);
		}
		else
		{
			obj.setId(1L);
			obj.setNombre("Accesibilidad y Democratización");
			variables.add(obj);
			obj=new Base();
			obj.setId(2L);
			obj.setNombre("Oferta Formativa");
			variables.add(obj);
			obj=new Base();
			obj.setId(3L);
			obj.setNombre("Currículo actualizado y diversificado");
			variables.add(obj);
			obj=new Base();
			obj.setId(4L);
			obj.setNombre("Certificación de competencias laborales");
			variables.add(obj);
			obj=new Base();
			obj.setId(5L);
			obj.setNombre("Enfoque y Metodología");
			variables.add(obj);
			obj=new Base();
			obj.setId(6L);
			obj.setNombre("Actualización y Capacitación de Agentes");
			variables.add(obj);
			obj=new Base();
			obj.setId(7L);
			obj.setNombre("Evaluación de Desempeño de Agentes");
			variables.add(obj);
			obj=new Base();
			obj.setId(8L);
			obj.setNombre("Gestión Educativa Participativa");
			variables.add(obj);
		}
		return variables;
	}
	
	public String obtenerNombre(Long tipo, Long subtipo)
	{
		if(tipo.longValue()==1L && subtipo.longValue()==0L)	{return "Fomentar, institucionalizar y fortalecer los espacios"
				+ "de dialogo social, concertación y negociación entre los actores y otros"
				+ "agentes vinculados a la formación profesional, que favorezcan el mejoramiento"
				+ "de su calidad, la adecuación ocupacional, la equidad en el acceso y la "
				+ "inserción al mercado laboral competitivo.";}
		if(tipo.longValue()==2L && subtipo.longValue()==0L)	{return "Promover la formación profesional de calidad con valores, con una"
				+ "perspectiva competitiva, participativa y con equidad, desde los niveles"
				+ "básicos hasta el nivel superior, que desarrolle competencias laborales"
				+ "y capacidades emprendedoras, que responda a las caracteristicas y demandas"
				+ "locales, regionales en el marco de la descentralización y el"
				+ "mejoramiento de la calidad de vida de la población.";}
		if(tipo.longValue()==1L && subtipo.longValue()==1L)	{return "Valoración y reconocimiento social";}
		if(tipo.longValue()==1L && subtipo.longValue()==2L)	{return "Espacios de diálogo y concertación";}
		if(tipo.longValue()==2L && subtipo.longValue()==1L)	{return "Accesibilidad y Democratización";}
		if(tipo.longValue()==2L && subtipo.longValue()==2L)	{return "Oferta Formativa";}
		if(tipo.longValue()==2L && subtipo.longValue()==3L)	{return "Currículo actualizado y diversificado";}
		if(tipo.longValue()==2L && subtipo.longValue()==4L)	{return "Certificación de competencias laborales";}
		if(tipo.longValue()==2L && subtipo.longValue()==5L)	{return "Enfoque y Metodología";}
		if(tipo.longValue()==2L && subtipo.longValue()==6L)	{return "Actualización y Capacitación de Agentes";}
		if(tipo.longValue()==2L && subtipo.longValue()==7L)	{return "Evaluación de Desempeño de Agentes";}
		if(tipo.longValue()==2L && subtipo.longValue()==8L)	{return "Gestión Educativa Participativa";}
		return "";
	}
	
	
}
	