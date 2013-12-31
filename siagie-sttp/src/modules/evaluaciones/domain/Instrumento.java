package modules.evaluaciones.domain;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.bytecode.javassist.FieldHandler;

@Entity
@Table(name="evaluaciones.m_instrumento")
public class Instrumento implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long escala;
	private String nombre;
	private String objetivos;
	private String instrucciones;
	private Date fecha;
	private Boolean orden;
	private Boolean aspectos;
	private Boolean alineado;
	private Boolean observados;
	private Boolean autoevaluacion;	
	private Long estado;
	private Escala beanEscala;
	private FieldHandler fieldHandler;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_instrumento")
	public Long getId()					   	            {return id;}
	public void setId(Long id)				            {this.id = id;}
	
	@Column(name="pk_escala")
	public Long getEscala()				                {return escala;}
	public void setEscala(Long escala)                  {this.escala = escala;}
	
	@Column(name="nombre")
	public String getNombre() 				            {return nombre;}
	public void setNombre(String nombre) 	            {this.nombre = nombre;}
	
	@Column(name="objetivos")
	public String getObjetivos()			            {return objetivos;}
	public void setObjetivos(String objetivos)          {this.objetivos = objetivos;	}
	
	@Column(name="instrucciones")
	public String getInstrucciones() 		            {return instrucciones;}
	public void setInstrucciones(String instrucciones)  {this.instrucciones = instrucciones;}
	
	@Column(name="fecha")
	public Date getFecha() 								{return fecha;}
	public void setFecha(Date fecha) 					{this.fecha = fecha;}
	
	@Column(name="orden")
	public Boolean getOrden()							{return orden;}
	public void setOrden(Boolean orden)				    {this.orden = orden;}
	
	@Column(name="aspectos")
	public Boolean getAspectos() 						{return aspectos;}
	public void setAspectos(Boolean aspectos)			{this.aspectos = aspectos;}
	
	@Column(name="alineado")
	public Boolean getAlineado()						{return alineado;}
	public void setAlineado(Boolean alineado)			{this.alineado = alineado;}
	
	@Column(name="observados")
	public Boolean getObservados()						{return observados;}
	public void setObservados(Boolean observados) 		{this.observados = observados;}
	
	@Column(name="autoevaluacion")
	public Boolean getAutoevaluacion() 					{return autoevaluacion;}
	public void setAutoevaluacion(Boolean autoevaluacion) {this.autoevaluacion = autoevaluacion;}
	
	@Column(name="estado")
	public Long getEstado() 							{return estado;}
	public void setEstado(Long estado) 					{this.estado = estado;}
	
	public static long getSerialversionuid()			{return serialVersionUID;}
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="pk_escala",  insertable=false, updatable=false)
	@LazyToOne(LazyToOneOption.PROXY)
	public Escala getBeanEscala() 
	{if (fieldHandler != null) 	{return (Escala) fieldHandler.readObject(this, "beanEscala", beanEscala);} 	return beanEscala;}
	public void setBeanEscala(Escala beanEscala)
	{if (fieldHandler != null)	{this.beanEscala = (Escala) fieldHandler.writeObject(this, "beanEscala", this.beanEscala, beanEscala);  return;}	this.beanEscala = beanEscala;}

	
	
		
}