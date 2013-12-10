package modules.marco.domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="marco.m_transversal")
public class Transversal implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long modulo;
	private Long tipo;
	private Long nivel;
	private Long subnivel;
	private String titulo;
	private String descripcion;
	private Long duracion;
	private Long estado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_transversal")
	public Long getId() 							{return id;}
	public void setId(Long id) 						{this.id = id;}
	
	@Column(name="tipo")
	public Long getTipo() 							{return tipo;}
	public void setTipo(Long tipo) 					{this.tipo = tipo;}
	
	@Column(name="modulo")
	public Long getModulo() 						{return modulo;}
	public void setModulo(Long modulo) 				{this.modulo = modulo;}
	
	@Column(name="nivel")
	public Long getNivel() 							{return nivel;}
	public void setNivel(Long nivel) 				{this.nivel = nivel;}
	
	@Column(name="subnivel")
	public Long getSubnivel() 						{return subnivel;}
	public void setSubnivel(Long subnivel) 			{this.subnivel = subnivel;}
	
	@Column(name="duracion")
	public Long getDuracion() 						{return duracion;}
	public void setDuracion(Long duracion) 			{this.duracion = duracion;}
	
	@Column(name="titulo")
	public String getTitulo()						{return titulo;}
	public void setTitulo(String titulo) 			{this.titulo = titulo;}
	
	@Column(name="descripcion")
	public String getDescripcion() 					{return descripcion;}
	public void setDescripcion(String descripcion) 	{this.descripcion = descripcion;}
	
	@Column(name="estado")
	public Long getEstado() 						{return estado;}
	public void setEstado(Long estado) 				{this.estado = estado;}
}