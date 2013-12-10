package modules.horario.domain;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="horario.m_asistencia_docente")
public class AsistenciaDocente implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long    id;
	private Long seccion;
	private Date fecha;
	private	Long justificacion;
	private	Long estado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_distribucion")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	@Column(name="pk_seccion")
	public Long getSeccion() 								{return seccion;}
	public void setSeccion(Long seccion) 					{this.seccion = seccion;}
	
	@Column(name="pk_ambiente")
	public Date getFecha() 									{return fecha;}
	public void setFecha(Date fecha) 						{this.fecha = fecha;}
	
	@Column(name="justificacion")
	public Long getJustificacion()							{return justificacion;}
	public void setJustificacion(Long justificacion) 		{this.justificacion = justificacion;}
	
	@Column(name="estado")
	public Long getEstado() 								{return estado;}
	public void setEstado(Long estado) 						{this.estado = estado;}	
}