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
@Table(name="marco.m_profesion_familia")
public class Familia implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private String descripcion;
	private Long sector;
	private Long estado;
	
	private String nombreSector;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_familia")
	public Long getId()									{return id;}
	public void setId(Long id) 							{this.id = id;}
	
	@Column(name="nombre")
	public String getNombre() 							{return nombre;}
	public void setNombre(String nombre) 				{this.nombre = nombre;}
	
	@Column(name="descripcion")
	public String getDescripcion() 						{return descripcion;}
	public void setDescripcion(String descripcion) 		{this.descripcion = descripcion;}
	
	@Column(name="sector")
	public Long getSector() 							{return sector;}
	public void setSector(Long sector) 					{this.sector = sector;}
	
	@Column(name="estado")
	public Long getEstado() 							{return estado;}
	public void setEstado(Long estado) 					{this.estado = estado;}
	
	@Transient
	public String getNombreSector() 					
	{
		if(this.sector==1L)	{return "SERVICIOS";}
		if(this.sector==2L)	{return "INDUSTRIA";}
		if(this.sector==3L)	{return "AGRICULTURA";}
		if(this.sector==4L)	{return "MINERIA";}
		return "";
	}
	public void setNombreSector(String nombreSector) 	{this.nombreSector = nombreSector;}
	
	
}