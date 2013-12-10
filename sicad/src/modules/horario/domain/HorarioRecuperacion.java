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
@Table(name="horario.m_recuperacion")
public class HorarioRecuperacion implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long    id;
	private Long 	seccion;
	private Long 	sesion;
	private	Date	fecha;
	private	Long	ambiente;
	private	Long	motivo;
	private	String	observacion;
	private	Long 	estado;
	private	String	nombreMotivo;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_recuperacion")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	@Column(name="pk_seccion")
	public Long getSeccion() 								{return seccion;}
	public void setSeccion(Long seccion) 					{this.seccion = seccion;}
	
	@Column(name="pk_sesion")
	public Long getSesion() 								{return sesion;}
	public void setSesion(Long sesion) 						{this.sesion = sesion;}
	
	@Column(name="fecha")
	public Date getFecha() 									{return fecha;}
	public void setFecha(Date fecha) 						{this.fecha = fecha;}
	
	@Column(name="ambiente")
	public Long getAmbiente() 								{return ambiente;}
	public void setAmbiente(Long ambiente) 					{this.ambiente = ambiente;}
	
	@Column(name="motivo")
	public Long getMotivo() 								{return motivo;}
	public void setMotivo(Long motivo) 						{this.motivo = motivo;}
	
	@Column(name="observacion")
	public String getObservacion() 							{return observacion;}
	public void setObservacion(String observacion) 			{this.observacion = observacion;}
	
	@Column(name="estado")
	public Long getEstado() 								{return estado;}	
	public void setEstado(Long estado) 						{this.estado = estado;}
	
	@Transient
	public String getNombreMotivo() 						{return nombreMotivo;}
	public void setNombreMotivo(String nombreMotivo) 		{this.nombreMotivo = nombreMotivo;}
}