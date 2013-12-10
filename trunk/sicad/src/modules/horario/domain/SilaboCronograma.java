package modules.horario.domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="horario.m_silabo_cronograma")
public class SilaboCronograma implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long    id;
	private Long	seccion;
	private	Long 	sesion;
	private Long 	contenido;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_conograma")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	@Column(name="pk_seccion")
	public Long getSeccion() 								{return seccion;}
	public void setSeccion(Long seccion) 					{this.seccion = seccion;}
	
	@Column(name="pk_sesion")
	public Long getSesion() 								{return sesion;}
	public void setSesion(Long sesion) 						{this.sesion = sesion;}
	
	@Column(name="pk_contenido")
	public Long getContenido() 								{return contenido;}
	public void setContenido(Long contenido) 				{this.contenido = contenido;}
	
	
}