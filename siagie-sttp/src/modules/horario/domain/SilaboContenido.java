package modules.horario.domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="horario.m_silabo_contenido")
public class SilaboContenido implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long    id;
	private Long	seccion;
	private	Long 	elemento;
	private	Long 	actividad;
	private String 	contenido;
	private String 	tareas;
	private	String 	descripcion;
	private	Long	tipo;
	private	Long	semana;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_contenido")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	@Column(name="pk_seccion")
	public Long getSeccion() 								{return seccion;}
	public void setSeccion(Long seccion) 					{this.seccion = seccion;}
	
	@Column(name="elemento")
	public Long getElemento() 								{return elemento;}
	public void setElemento(Long elemento) 					{this.elemento = elemento;}
	
	@Column(name="actividad")
	public Long getActividad() 								{return actividad;}
	public void setActividad(Long actividad) 				{this.actividad = actividad;}
	
	@Column(name="contenido")
	public String getContenido() 							{return contenido;}
	public void setContenido(String contenido) 				{this.contenido = contenido;}
	
	@Column(name="descripcion")
	public String getDescripcion() 							{return descripcion;}
	public void setDescripcion(String descripcion) 			{this.descripcion = descripcion;}
	
	@Column(name="tareas")
	public String getTareas() 								{return tareas;}
	public void setTareas(String tareas) 					{this.tareas = tareas;}
	
	@Column(name="tipo")
	public Long getTipo() 									{return tipo;}
	public void setTipo(Long tipo) 							{this.tipo = tipo;}
	
	@Column(name="semana")
	public Long getSemana() 								{return semana;}
	public void setSemana(Long semana) 						{this.semana = semana;}
}