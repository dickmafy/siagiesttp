package modules.mantenimiento.domain;
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
@Table(name="mantenimiento.m_resolucion")
public class Resolucion implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private Long tipo;
    private Date fecha;
    private Long estado;
    private String nombreTipo;
    private String nombreSiglas;
    private Boolean existFile;
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_resolucion")
	public Long getId()                            		{return id;}
	public void setId(Long id)                     		{this.id = id;}
		
	@Column(name="tipo")
	public Long getTipo()								{return tipo;}
	public void setTipo(Long tipo) 						{this.tipo = tipo;}
	
	@Column(name="fecha")
	public Date getFecha() 								{return fecha;}
	public void setFecha(Date fecha) 					{this.fecha = fecha;}
  
	@Column(name="estado")
	public Long getEstado()                        		{return estado;}
	public void setEstado(Long estado)             		{this.estado = estado;}	
	
	@Column(name="nombre")
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	
	@Transient
	public String getNombreTipo() 
	{
		if(tipo==1L) return "Resolución Ministerial";
		if(tipo==2L) return "Resolución Directorial";
		return "";
	}
	public void setNombreTipo(String nombreTipo) 		{this.nombreTipo = nombreTipo;}
	
	@Transient
	public String getNombreSiglas() 					
	{
		if(tipo==1L) return "RM";
		if(tipo==2L) return "RD";
		return "";
	}
	public void setNombreSiglas(String nombreSiglas) 	{this.nombreSiglas = nombreSiglas;}
	
	@Transient
	public Boolean getExistFile() 						{return existFile;}
	public void setExistFile(Boolean existFile) 		{this.existFile = existFile;}
}