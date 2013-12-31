package modules.administracion.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="administracion.m_solicitud_movimiento")
public class SolicitudMovimiento implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long solicitud;
	private Date fecha;
	private Long operacion;
	private Long usuario;
	private String observacion;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_movimiento")
	public Long getId()                             {return id;}
	public void setId(Long id)                      {this.id = id;}
	
	@Column(name="pk_solicitud")
	public Long getSolicitud() 						{return solicitud;}
	public void setSolicitud(Long solicitud) 		{this.solicitud = solicitud;}
	
	@Column(name="fecha")
	public Date getFecha() 							{return fecha;}
	public void setFecha(Date fecha) 				{this.fecha = fecha;}
	
	@Column(name="operacion")
	public Long getOperacion() 						{return operacion;}
	public void setOperacion(Long operacion) 		{this.operacion = operacion;}
	
	@Column(name="usuario")
	public Long getUsuario() 						{return usuario;}
	public void setUsuario(Long usuario) 			{this.usuario = usuario;}
	
	@Column(name="observacion")
	public String getObservacion() 					{return observacion;}
	public void setObservacion(String observacion)	{this.observacion = observacion;}
}