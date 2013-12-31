package modules.cetpro.domain;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cetpro.m_contextualizacion")
public class Contextualizacion implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long 	pk_contextualizacion;
	private Long 	pk_oferta;
	private Long 	nivel;
	private Long 	subnivel;
	private String 	titulo;
	private String 	descripcion;
	private Long 	duracion;
	private Long 	tipo;
	private String 	contenido;
	private Long 	estado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_contextualizacion")
	public Long getPk_contextualizacion() 										{return pk_contextualizacion;}
	public void setPk_contextualizacion(Long pk_contextualizacion) 				{this.pk_contextualizacion = pk_contextualizacion;}
	
	@Column(name="pk_oferta")
	public Long getPk_oferta() 													{return pk_oferta;}
	public void setPk_oferta(Long pk_oferta) 									{this.pk_oferta = pk_oferta;}
	
	@Column(name="nivel")
	public Long getNivel() 														{return nivel;}
	public void setNivel(Long nivel) 											{this.nivel = nivel;}
	
	@Column(name="subnivel")
	public Long getSubnivel() 													{return subnivel;}
	public void setSubnivel(Long subnivel) 										{this.subnivel = subnivel;}
	
	@Column(name="titulo")
	public String getTitulo() 													{return titulo;}
	public void setTitulo(String titulo) 										{this.titulo = titulo;}
	
	@Column(name="descripcion")
	public String getDescripcion() 												{return descripcion;}
	public void setDescripcion(String descripcion) 								{this.descripcion = descripcion;}
	
	@Column(name="duracion")
	public Long getDuracion() 													{return duracion;}
	public void setDuracion(Long duracion) 										{this.duracion = duracion;}
	
	@Column(name="tipo")
	public Long getTipo() 														{return tipo;}
	public void setTipo(Long tipo) 												{this.tipo = tipo;}
	
	@Column(name="contenido")
	public String getContenido() 												{return contenido;}
	public void setContenido(String contenido) 									{this.contenido = contenido;}
	
	@Column(name="estado")
	public Long getEstado() 													{return estado;}
	public void setEstado(Long estado) 											{this.estado = estado;}
	

}