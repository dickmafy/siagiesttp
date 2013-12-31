package modules.cetpro.domain;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cetpro.m_itinerario")
public class ItinerarioCetpro implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long 	pk_itinerario;
	private Long 	pk_oferta;
	private String 	titulo;
	private String 	descripcion;
	private Long 	horas;
	private Long 	sesiones;
	private Long 	estado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_itinerario")
	public Long getPk_itinerario() 										{return pk_itinerario;}
	public void setPk_itinerario(Long pk_itinerario) 					{this.pk_itinerario = pk_itinerario;}
	
	@Column(name="pk_oferta")
	public Long getPk_oferta() 											{return pk_oferta;}
	public void setPk_oferta(Long pk_oferta) 							{this.pk_oferta = pk_oferta;}
	
	@Column(name="titulo")
	public String getTitulo() 											{return titulo;}
	public void setTitulo(String titulo) 								{this.titulo = titulo;}
	
	@Column(name="descripcion")
	public String getDescripcion() 										{return descripcion;}
	public void setDescripcion(String descripcion) 						{this.descripcion = descripcion;}
	
	@Column(name="horas")
	public Long getHoras() 												{return horas;}
	public void setHoras(Long horas) 									{this.horas = horas;}
	
	@Column(name="sesiones")
	public Long getSesiones() 											{return sesiones;}
	public void setSesiones(Long sesiones) 								{this.sesiones = sesiones;}
	
	@Column(name="estado")
	public Long getEstado() 											{return estado;}
	public void setEstado(Long estado) 									{this.estado = estado;}
	

}