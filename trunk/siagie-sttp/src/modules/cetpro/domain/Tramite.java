package modules.cetpro.domain;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cetpro.m_tramite")
public class Tramite implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long 	pk_tramite;
	private Long 	pk_oferta;
	private Long 	usuario;
	private Date 	fecha;
	private Long 	motivo;
	private String 	descripcion;
	private Long 	estado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_tramite")
	public Long getPk_tramite() 										{return pk_tramite;}
	public void setPk_tramite(Long pk_tramite) 							{this.pk_tramite = pk_tramite;}
	
	@Column(name="pk_oferta")
	public Long getPk_oferta() 											{return pk_oferta;}
	public void setPk_oferta(Long pk_oferta) 							{this.pk_oferta = pk_oferta;}
	
	@Column(name="usuario")
	public Long getUsuario() 											{return usuario;}
	public void setUsuario(Long usuario) 								{this.usuario = usuario;}
	
	@Column(name="fecha")
	public Date getFecha() 												{return fecha;}
	public void setFecha(Date fecha) 									{this.fecha = fecha;}
	
	@Column(name="motivo")
	public Long getMotivo() 											{return motivo;}
	public void setMotivo(Long motivo) 									{this.motivo = motivo;}
	
	@Column(name="descripcion")
	public String getDescripcion() 										{return descripcion;}
	public void setDescripcion(String descripcion) 						{this.descripcion = descripcion;}
	
	@Column(name="estado")
	public Long getEstado() 											{return estado;}
	public void setEstado(Long estado) 									{this.estado = estado;}
	

	

}