package modules.admision.domain;
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
@Table(name="admision.m_proceso")
public class Proceso implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private	Long institucion;
	private Long annio;
	private Long proceso;
	private Date fecha;
	private Long registrante;
	private Double costo_carpeta;
	private Double costo_inscripcion;
	private Double costo_matreg;
	private Double costo_matext;
	private Long estado;
	
	private Long tipo;
	private Long ejecucion;
	private String nombreTipo;
	private String nombrePeriodo;
	private String nombreEjecucion;
	private String nombreEstado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_proceso")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	@Column(name="pk_institucion")
	public Long getInstitucion() 							{return institucion;}
	public void setInstitucion(Long institucion) 			{this.institucion = institucion;}
	
	@Column(name="annio")
	public Long getAnnio() 									{return annio;}
	public void setAnnio(Long annio) 						{this.annio = annio;}
	
	@Column(name="proceso")	
	public Long getProceso() 								{return proceso;}
	public void setProceso(Long proceso) 					{this.proceso = proceso;}
	
	@Column(name="registro_fecha")
	public Date getFecha() 									{return fecha;}
	public void setFecha(Date fecha) 						{this.fecha = fecha;}
	
	@Column(name="registro_usuario")
	public Long getRegistrante() 							{return registrante;}
	public void setRegistrante(Long registrante) 			{this.registrante = registrante;}
	
	@Column(name="costo_carpeta")
	public Double getCosto_carpeta() 						{return costo_carpeta;}
	public void setCosto_carpeta(Double costo_carpeta)	 	{this.costo_carpeta = costo_carpeta;}
	
	@Column(name="costo_inscripcion")
	public Double getCosto_inscripcion() 						{return costo_inscripcion;}
	public void setCosto_inscripcion(Double costo_inscripcion) 	{this.costo_inscripcion = costo_inscripcion;}
	
	@Column(name="costo_matricula_regular")
	public Double getCosto_matreg()							{return costo_matreg;}
	public void setCosto_matreg(Double costo_matreg) 		{this.costo_matreg = costo_matreg;}
	
	@Column(name="costo_matricula_extemporanea")
	public Double getCosto_matext() 						{return costo_matext;}
	public void setCosto_matext(Double costo_matext) 		{this.costo_matext = costo_matext;}

	@Column(name="estado")
	public Long getEstado() 								{return estado;}	
	public void setEstado(Long estado)	    				{this.estado = estado;}
	
	@Transient
	public String getNombreEstado() 
	{
		if(estado.longValue()==0L)		{return "ANULADO";}
		if(estado.longValue()==1L)		{return "PENDIENTE";}
		if(estado.longValue()==2L)		{return "PRE-PUBLICADO";}
		if(estado.longValue()==3L)		{return "PUBLICADO";}
		if(estado.longValue()==4L)		{return "ADMISIÓN";}
		if(estado.longValue()==5L)		{return "MATRICULA";}
		if(estado.longValue()==6L)		{return "ACADEMICA";}
		if(estado.longValue()==7L)		{return "FINALIZADO";}		
		return "";
	}
	public void setNombreEstado(String nombreEstado) 
	{this.nombreEstado = nombreEstado;}
	
	@Transient
	public Long getTipo() 									{return tipo;}
	public void setTipo(Long tipo) 							{this.tipo = tipo;}
	
	@Transient
	public Long getEjecucion() 								{return ejecucion;}
	public void setEjecucion(Long ejecucion) 				{this.ejecucion = ejecucion;}
	
	@Transient
	public String getNombreTipo() 
	{
		if(tipo.longValue()==1L)		{return "Normal";}
		if(tipo.longValue()==2L)		{return "Extensión";}
		return "";
	}
	
	@Transient
	public String getNombrePeriodo() 
	{
		if(proceso.longValue()==1L)		{return "Enero";}
		if(proceso.longValue()==2L)		{return "Febrero";}
		if(proceso.longValue()==3L)		{return "Marzo";}
		if(proceso.longValue()==4L)		{return "Abril";}
		if(proceso.longValue()==5L)		{return "Mayo";}
		if(proceso.longValue()==6L)		{return "Junio";}
		if(proceso.longValue()==7L)		{return "Julio";}
		if(proceso.longValue()==8L)		{return "Agosto";}
		if(proceso.longValue()==9L)		{return "Setiembre";}
		if(proceso.longValue()==10L)	{return "Octubre";}
		if(proceso.longValue()==11L)	{return "Noviembre";}
		if(proceso.longValue()==12L)	{return "Diciembre";}
		return "";
	}
	@Transient
	public String getNombreEjecucion() 
	{
		if(ejecucion.longValue()==1L)		{return "Enero";}
		if(ejecucion.longValue()==2L)		{return "Febrero";}
		if(ejecucion.longValue()==3L)		{return "Marzo";}
		if(ejecucion.longValue()==4L)		{return "Abril";}
		if(ejecucion.longValue()==5L)		{return "Mayo";}
		if(ejecucion.longValue()==6L)		{return "Junio";}
		if(ejecucion.longValue()==7L)		{return "Julio";}
		if(ejecucion.longValue()==8L)		{return "Agosto";}
		if(ejecucion.longValue()==9L)		{return "Setiembre";}
		if(ejecucion.longValue()==10L)		{return "Octubre";}
		if(ejecucion.longValue()==11L)		{return "Noviembre";}
		if(ejecucion.longValue()==12L)		{return "Diciembre";}
		return "";
	}
}