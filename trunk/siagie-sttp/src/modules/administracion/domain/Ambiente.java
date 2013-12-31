package modules.administracion.domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="administracion.m_ambiente")
public class Ambiente implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long local;
	private Long tipo;
	private String codigo;
    private String nombre;
    private Long area;
    private String observacion; 
    private Long capacidad;
    private Long estado;
    private String capacidadAutorizada;
    private Long resolucion;
    private Long minimo;
	private Long solicitud;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_ambiente")
	public Long getId()                          	{return id;}
	public void setId(Long id)                   	{this.id = id;}

	@Column(name="pk_local")
	public Long getLocal() 							{return local;}
	public void setLocal(Long local) 				{this.local = local;}
	
	@Column(name="codigo")
	public String getCodigo() 						{return codigo;}
	public void setCodigo(String codigo)			{this.codigo = codigo;}
	
	@Column(name="nombre")
	public String getNombre()                    	{return nombre;}
	public void setNombre(String nombre)         	{this.nombre = nombre;}
	
	@Column(name="tipo")
	public Long getTipo()                        	{return tipo;}
	public void setTipo(Long tipo)              	{this.tipo = tipo;}
	
	@Column(name="area")
	public Long getArea()                          	{return area;}
	public void setArea(Long area)                  {this.area = area;}
	
	@Column(name="observacion")
	public String getObservacion()                  {return observacion;}
	public void setObservacion(String observacion)  {this.observacion = observacion;}
	
	@Column(name="capacidad")
	public Long getCapacidad() 						{return capacidad;}
	public void setCapacidad(Long capacidad) 		{this.capacidad = capacidad;}
	
	@Column(name="estado")
	public Long getEstado()                        	{return estado;}
	public void setEstado(Long estado)            	{this.estado = estado;}
	
	@Column(name="resolucion")
	public Long getResolucion() 					{return resolucion;}
	public void setResolucion(Long resolucion) 		{this.resolucion = resolucion;}
	
	@Column(name="solicitud")
	public Long getSolicitud() 						{return solicitud;}
	public void setSolicitud(Long solicitud) 		{this.solicitud = solicitud;}
	
	@Column(name="minimo")
	public Long getMinimo() 						{return minimo;}
	public void setMinimo(Long minimo) 				{this.minimo = minimo;}
	
	@Transient
	public Double getCapacidadAutorizada() 					
	{return Math.floor(area/1.2);}
}