package modules.horario.domain;
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
@Table(name="horario.m_asistencia_alumno")
public class AsistenciaAlumno implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long 	id;
	private Date 	fecha;
	private Long 	persona;
	private	Long 	justificacion;
	private	Long	ambiente;
	private	Long 	estado;
	
	private String	nombres;
	private	String	apellidos;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_seccion")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	@Column(name="pk_fecha")
	public Date getFecha() 									{return fecha;}
	public void setFecha(Date fecha) 						{this.fecha = fecha;}
	
	@Column(name="pk_ambiente")
	public Long getAmbiente() 								{return ambiente;}
	public void setAmbiente(Long ambiente) 					{this.ambiente = ambiente;}
	
	@Column(name="pk_persona")
	public Long getPersona() 								{return persona;}
	public void setPersona(Long persona) 					{this.persona = persona;}
	
	@Column(name="justificacion")
	public Long getJustificacion() 							{return justificacion;}
	public void setJustificacion(Long justificacion) 		{this.justificacion = justificacion;}
	
	@Column(name="estado")
	public Long getEstado() 								{return estado;}
	public void setEstado(Long estado) 						{this.estado = estado;}
	
	@Transient
	public String getNombres() 								{return nombres;}
	public void setNombres(String nombres) 					{this.nombres = nombres;}
	
	@Transient
	public String getApellidos() 							{return apellidos;}
	public void setApellidos(String apellidos) 				{this.apellidos = apellidos;}
	
}
	