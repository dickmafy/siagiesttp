package modules.administracion.domain;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import modules.mantenimiento.domain.Especialidad;
import modules.mantenimiento.domain.Profession;

@Entity
@Table(name="administracion.m_historial_academico")
public class HistorialAcademico implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	private Long   	id;
	private Long 	personal;
	private Long	especialidad;
	private	String	titulo;
	private	Long	profesion;
	private	Long	duracion;
	private	String	centro;
	private	Long	situacion;
	private	Long	regnro;
	private	Long	regfol;
	private	Date	regfec;
	private	Long	estado;
	private String 	nombreSituacion;
	
	private Especialidad beanEspecialidad;
	private Profession beanProfesion;
	
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_academico")
	public Long getId()                                     {return id;}
	public void setId(Long id)                              {this.id = id;}
	
	@Column(name="pk_personal")
	public Long getPersonal() 								{return personal;}
	public void setPersonal(Long personal) 					{this.personal = personal;}
	
	@Column(name="especialidad")
	public Long getEspecialidad() 							{return especialidad;}
	public void setEspecialidad(Long especialidad) 			{this.especialidad = especialidad;}
	
	@Column(name="titulo")
	public String getTitulo() 								{return titulo;}
	public void setTitulo(String titulo) 					{this.titulo = titulo;}
	
	@Column(name="profesion")
	public Long getProfesion() 								{return profesion;}
	public void setProfesion(Long profesion) 				{this.profesion = profesion;}
	
	@Column(name="duracion")
	public Long getDuracion() 								{return duracion;}
	public void setDuracion(Long duracion) 					{this.duracion = duracion;}
	
	@Column(name="centro")
	public String getCentro() 								{return centro;}
	public void setCentro(String centro) 					{this.centro = centro;}
	
	@Column(name="situacion")
	public Long getSituacion() 								{return situacion;}
	public void setSituacion(Long situacion) 				{this.situacion = situacion;}
	
	@Column(name="registro_numero")
	public Long getRegnro() 								{return regnro;}
	public void setRegnro(Long regnro) 						{this.regnro = regnro;}
	
	@Column(name="registro_folio")
	public Long getRegfol() 								{return regfol;}
	public void setRegfol(Long regfol) 						{this.regfol = regfol;}
	
	@Column(name="registro_fecha")
	public Date getRegfec() 								{return regfec;}
	public void setRegfec(Date regfec) 						{this.regfec = regfec;}
	
	@Column(name="estado")
	public Long getEstado()                                 {return estado;}
	public void setEstado(Long estado)                      {this.estado = estado;}
	
	@Transient
	public String getNombreSituacion() 						
	{
		if(situacion.longValue()==1L)	{return "EN CURSO";}
		if(situacion.longValue()==1L)	{return "FINALIZADO";}
		return "";
	}
	public void setNombreSituacion(String nombreSituacion) 	{this.nombreSituacion = nombreSituacion;}
	
	@ManyToOne 
	@JoinColumn(name="especialidad",  insertable=false, updatable=false) 
	public Especialidad getBeanEspecialidad() 						{return beanEspecialidad;}
	public void setBeanEspecialidad(Especialidad beanEspecialidad) 	{this.beanEspecialidad = beanEspecialidad;}
	
	@ManyToOne 
	@JoinColumn(name="profesion",  insertable=false, updatable=false) 
	public Profession getBeanProfesion() 							{return beanProfesion;}
	public void setBeanProfesion(Profession beanProfesion) 			{this.beanProfesion = beanProfesion;}	
	
	
}