package modules.marco.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="marco.m_profesion")
public class Profesion implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long familia;
	private Long formacion;
	private String nombre;
	private String descripcion;
	private String mencion;
	private String aptitudes;
	private String actitudes;
	private String ambiente;
	private String equipo;
	private String puestos;
	private Long dependencia;
	private Boolean referente;
	private Long duracion;
	private Long estado;
	
	
	private String nombreFamilia;
	private String nombreFormacion;
	private String nombreEstado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_profesion")
	public Long getId() 								{return id;}
	public void setId(Long id) 							{this.id = id;}
	
	@Column(name="pk_familia")
	public Long getFamilia() 							{return familia;}
	public void setFamilia(Long familia) 				{this.familia = familia;}
	
	@Column(name="nombre")	
	public String getNombre() 							{return nombre;}
	public void setNombre(String nombre) 				{this.nombre = nombre;}
	
	@Column(name="descripcion")
	public String getDescripcion() 						{return descripcion;}
	public void setDescripcion(String descripcion)	 	{this.descripcion = descripcion;}
	
	@Column(name="formacion")
	public Long getFormacion() 							{return formacion;}
	public void setFormacion(Long formacion) 			{this.formacion = formacion;}
	
	@Column(name="mencion")
	public String getMencion() 							{return mencion;}
	public void setMencion(String mencion) 				{this.mencion = mencion;}
	
	@Column(name="aptitudes")
	public String getAptitudes() 						{return aptitudes;}
	public void setAptitudes(String aptitudes) 			{this.aptitudes = aptitudes;}
	
	@Column(name="actitudes")
	public String getActitudes() 						{return actitudes;}
	public void setActitudes(String actitudes) 			{this.actitudes = actitudes;}
	
	@Column(name="ambiente")
	public String getAmbiente() 						{return ambiente;}
	public void setAmbiente(String ambiente) 			{this.ambiente = ambiente;}
	
	@Column(name="duracion")
	public Long getDuracion() 							{return duracion;}
	public void setDuracion(Long duracion) 				{this.duracion = duracion;}
	
	@Column(name="dependencia")
	public Long getDependencia() 						{return dependencia;}
	public void setDependencia(Long dependencia) 		{this.dependencia = dependencia;}
	
	@Column(name="equipo")
	public String getEquipo() 							{return equipo;}
	public void setEquipo(String equipo) 				{this.equipo = equipo;}
	
	@Column(name="puestos")
	public String getPuestos() 							{return puestos;}
	public void setPuestos(String puestos) 				{this.puestos = puestos;}
	
	@Column(name="estado")
	public Long getEstado() 							{return estado;}	
	public void setEstado(Long estado) 					{this.estado = estado;}
	
	@Transient
	public Boolean getReferente() 						{return referente;}
	public void setReferente(Boolean referente) 		{this.referente = referente;}
	
	@Transient
	public String getNombreFamilia() 					{return nombreFamilia;}
	public void setNombreFamilia(String nombreFamilia) 	{this.nombreFamilia = nombreFamilia;}
	
	@Transient
	public String getNombreFormacion() 						
	{
		if(formacion.longValue()==1L)	return "IST";
		if(formacion.longValue()==2L)	return "CETPRO BASICO";
		if(formacion.longValue()==3L)	return "CETPRO INTERMEDIO";
		return "";
	}
	public void setNombreFormacion(String nombreFormacion) 	{this.nombreFormacion = nombreFormacion;}
	
	@Transient
	public String getNombreEstado() 
	{
		if(estado.longValue()==-1L)	return "RECHAZADO";
		if(estado.longValue()==0L)	return "PENDIENTE";
		if(estado.longValue()==1L)	return "REVISADO";
		if(estado.longValue()==2L)	return "APROBADO";
		return "";
	}
	public void setNombreEstado(String nombreEstado)		{this.nombreEstado = nombreEstado;}
	
	
}