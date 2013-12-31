package modules.administracion.domain;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="administracion.m_cronograma")
public class Cronograma implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long institucion;
	private Long periodo;
	private Date vigenciaInicio;
	private Date vigenciaFin;
	private String nombrePeriodo;
	private Long tipo;
	private Long ejecucion;
	private Long resolucion;
	private Long solicitud;
	
	private String nombreEjecucion;
	private String nombreTipo;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_cronograma")
	public Long getId()                             	{return id;}
	public void setId(Long id)                      	{this.id = id;}
	
	@Column(name="pk_institucion")
	public Long getInstitucion()						{return institucion;}
	public void setInstitucion(Long institucion) 		{this.institucion = institucion;}
	
	@Column(name="tipo")
	public Long getTipo() 								{return tipo;	}
	public void setTipo(Long tipo) 						{this.tipo = tipo;	}
	
	@Column(name="periodo")
	public Long getPeriodo() 							{return periodo;}
	public void setPeriodo(Long periodo) 				{this.periodo = periodo;}
	
	@Column(name="ejecucion")
	public Long getEjecucion() 							{return ejecucion;	}
	public void setEjecucion(Long ejecucion) 			{this.ejecucion = ejecucion;	}
	
	@Column(name="vigencia_inicio")
	public Date getVigenciaInicio() 					{return vigenciaInicio;}
	public void setVigenciaInicio(Date vigenciaInicio) 	{this.vigenciaInicio = vigenciaInicio;}
	
	@Column(name="vigencia_fin")
	public Date getVigenciaFin() 						{return vigenciaFin;}
	public void setVigenciaFin(Date vigenciaFin) 		{this.vigenciaFin = vigenciaFin;}
	
	@Column(name="resolucion")
	public Long getResolucion() 				{return resolucion;	}
	public void setResolucion(Long resolucion) 	{this.resolucion = resolucion;	}
	
	@Column(name="solicitud")
	public Long getSolicitud() 					{return solicitud;	}
	public void setSolicitud(Long solicitud) 	{this.solicitud = solicitud;	}
	
	
	
	@Transient
	public String getNombrePeriodo() 					
	{
		if(periodo==null)				{return "";}
		if(periodo.longValue()==1L)		{return "ENERO";}
		if(periodo.longValue()==2L)		{return "FEBRERO";}
		if(periodo.longValue()==3L)		{return "MARZO";}
		if(periodo.longValue()==4L)		{return "ABRIL";}
		if(periodo.longValue()==5L)		{return "MAYO";}
		if(periodo.longValue()==6L)		{return "JUNIO";}
		if(periodo.longValue()==7L)		{return "JULIO";}
		if(periodo.longValue()==8L)		{return "AGOSTO";}
		if(periodo.longValue()==9L)		{return "SEPTIEMBRE";}
		if(periodo.longValue()==10L)	{return "OCTUBRE";}
		if(periodo.longValue()==11L)	{return "NOVIEMBRE";}
		if(periodo.longValue()==12L)	{return "DICIEMBRE";}
		return "";
	}
	public void setNombrePeriodo(String nombrePeriodo) 	{this.nombrePeriodo = nombrePeriodo;}

	@Transient
	public String getNombreEjecucion() 					
	{
		if(ejecucion==null)					{return "";}
		if(ejecucion.longValue()==0L)		{return "-";}
		if(ejecucion.longValue()==1L)		{return "ENERO";}
		if(ejecucion.longValue()==2L)		{return "FEBRERO";}
		if(ejecucion.longValue()==3L)		{return "MARZO";}
		if(ejecucion.longValue()==4L)		{return "ABRIL";}
		if(ejecucion.longValue()==5L)		{return "MAYO";}
		if(ejecucion.longValue()==6L)		{return "JUNIO";}
		if(ejecucion.longValue()==7L)		{return "JULIO";}
		if(ejecucion.longValue()==8L)		{return "AGOSTO";}
		if(ejecucion.longValue()==9L)		{return "SEPTIEMBRE";}
		if(ejecucion.longValue()==10L)		{return "OCTUBRE";}
		if(ejecucion.longValue()==11L)		{return "NOVIEMBRE";}
		if(ejecucion.longValue()==12L)		{return "DICIEMBRE";}
		return "";
	}
	public void setNombreEjecucion(String nombreEjecucion) {		this.nombreEjecucion = nombreEjecucion;	}
	
	@Transient
	public String getNombreTipo() 					
	{
		if(tipo==null)				{return "";}
		if(tipo.longValue()==1L)		{return "NORMAL";}
		if(tipo.longValue()==2L)		{return "EXTENSIÓN";}
		return "";
	}
	
	public void setNombreTipo(String nombreTipo) {		this.nombreTipo = nombreTipo;	}
	
	
	
	
}