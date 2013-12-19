package modules.horario.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="horario.m_seccion")
public class Seccion implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long    id;
	private	Long	detalle;
	private String 	nombre;
	private Long 	docente;
	private	String	metodologia;
	private	String	evaluacion;
	private	String	recursos;
	private Long 	estado;

	private String	nombreEstado;
	private String 	nombreUnidad;
	private	String 	nombreModulo;
	private	String	nombreHorario;
	private	String	nombreAmbiente;
	private	Long	valorHoras;
	private	Long	valorCapacidad;
	private	Long	valorModulo;
	private Long	valorTipoModulo;
	private Long	valorUnidad;
	private Long	meta;
	private Long 	estadoSilabo;
	private Long 	alumno;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_seccion")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	@Column(name="pk_detalle")
	public Long getDetalle() 								{return detalle;}
	public void setDetalle(Long detalle) 					{this.detalle = detalle;}
	
	@Column(name="nombre")
	public String getNombre() 								{return nombre;}
	public void setNombre(String nombre) 					{this.nombre = nombre;}
	
	@Column(name="docente")
	public Long getDocente() 								{return docente;}
	public void setDocente(Long docente) 					{this.docente = docente;}
	
	@Column(name="metodologia")
	public String getMetodologia() 							{return metodologia;}
	public void setMetodologia(String metodologia) 			{this.metodologia = metodologia;}
	
	@Column(name="evaluacion")
	public String getEvaluacion() 							{return evaluacion;}
	public void setEvaluacion(String evaluacion) 			{this.evaluacion = evaluacion;}
	
	@Column(name="recursos")
	public String getRecursos() 							{return recursos;}
	public void setRecursos(String recursos) 				{this.recursos = recursos;}
	
	@Column(name="estado")
	public Long getEstado() 								{return estado;}
	public void setEstado(Long estado) 						{this.estado = estado;}	
	
	@Transient
	public String getNombreUnidad() 						{return nombreUnidad;}
	public void setNombreUnidad(String nombreUnidad) 		{this.nombreUnidad = nombreUnidad;}
	
	@Transient
	public String getNombreModulo() 						{return nombreModulo;}
	public void setNombreModulo(String nombreModulo) 		{this.nombreModulo = nombreModulo;}
	
	@Transient
	public String getNombreHorario() 						{return nombreHorario;}
	public void setNombreHorario(String nombreHorario) 		{this.nombreHorario = nombreHorario;}
	
	@Transient
	public String getNombreAmbiente() 						{return nombreAmbiente;}
	public void setNombreAmbiente(String nombreAmbiente) 	{this.nombreAmbiente = nombreAmbiente;}
	
	@Transient
	public Long getValorCapacidad() 						{return valorCapacidad;}
	public void setValorCapacidad(Long valorCapacidad) 		{this.valorCapacidad = valorCapacidad;}
	
	@Transient
	public Long getValorHoras() 							{return valorHoras;}
	public void setValorHoras(Long valorHoras) 				{this.valorHoras = valorHoras;}
	
	@Transient
	public Long getValorModulo() 							{return valorModulo;}
	public void setValorModulo(Long valorModulo) 			{this.valorModulo = valorModulo;}
	
	@Transient
	public Long getValorTipoModulo() 						{return valorTipoModulo;}
	public void setValorTipoModulo(Long valorTipoModulo) 	{this.valorTipoModulo = valorTipoModulo;}
	
	@Transient
	public String getNombreEstado() 					
	{
		if(estado==null)				{return "";}
		if(estado.longValue()<0L)		{return "DESHABILITADO";}
		if(estado.longValue()==2L)		{return "PUBLICADO";}
		return "";
	}
	
	@Transient
	public Long getMeta() {
		return meta;
	}
	public void setMeta(Long meta) {
		this.meta = meta;
	}
	
	@Transient
	public Long getValorUnidad() {
		return valorUnidad;
	}
	public void setValorUnidad(Long valorUnidad) {
		this.valorUnidad = valorUnidad;
	}
	
	@Transient
	public Long getEstadoSilabo() {
		return estadoSilabo;
	}
	public void setEstadoSilabo(Long estadoSilabo) {
		this.estadoSilabo = estadoSilabo;
	}
	
	@Transient
	public Long getAlumno() {
		return alumno;
	}
	public void setAlumno(Long alumno) {
		this.alumno = alumno;
	}
	
	
}