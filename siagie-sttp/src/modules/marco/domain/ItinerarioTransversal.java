package modules.marco.domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="marco.m_transversal_itinerario")
public class ItinerarioTransversal implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long tipo;
	private Long modulo;
	private String titulo;
	private String descripcion;
	private Long semestre;
	private Long horasSemestre;
	private Long horasTotal;
	private Double creditos;
	private Long estado;
	
	private String nombreModulo;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_unidad")
	public Long getId() 								{return id;}
	public void setId(Long id) 							{this.id = id;}
	
	@Column(name="tipo")
	public Long getTipo() 								{return tipo;}
	public void setTipo(Long tipo) 						{this.tipo = tipo;}
	
	@Column(name="modulo")
	public Long getModulo() 							{return modulo;}
	public void setModulo(Long modulo) 					{this.modulo = modulo;}	
	
	@Column(name="semestre")
	public Long getSemestre() 							{return semestre;}
	public void setSemestre(Long semestre) 				{this.semestre = semestre;}
	
	@Column(name="horas_semestre")
	public Long getHorasSemestre() 						{return horasSemestre;}
	public void setHorasSemestre(Long horasSemestre) 	{this.horasSemestre = horasSemestre;}
	
	@Column(name="horas_total")
	public Long getHorasTotal() 						{return horasTotal;}
	public void setHorasTotal(Long horasTotal) 			{this.horasTotal = horasTotal;}
	
	@Column(name="creditos")
	public Double getCreditos() 						{return creditos;}
	public void setCreditos(Double creditos) 			{this.creditos = creditos;}
	
	@Column(name="titulo")
	public String getTitulo() 							{return titulo;}
	public void setTitulo(String titulo) 				{this.titulo = titulo;}
	
	@Column(name="descripcion")
	public String getDescripcion() 						{return descripcion;}
	public void setDescripcion(String descripcion) 		{this.descripcion = descripcion;}
	
	@Column(name="estado")
	public Long getEstado() 							{return estado;}
	public void setEstado(Long estado) 					{this.estado = estado;}
	
	@Transient
	public String getNombreModulo() 					{return nombreModulo;}
	public void setNombreModulo(String nombreModulo) 	{this.nombreModulo = nombreModulo;}
	
	
}