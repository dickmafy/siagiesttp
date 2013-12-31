package modules.evaluaciones.domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="evaluaciones.m_balotario")
public class Balotario implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long instrumento;
	private String nombre;
	private String descripcion;
	private Long estado;
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	@Column(name="pk_balotario")
	public Long getId()					   	            {return id;}
	public void setId(Long id)				            {this.id = id;}
	
	@Column(name="pk_instrumento")
	public Long getInstrumento() 					{return instrumento;}
	public void setInstrumento(Long instrumento) 	{this.instrumento = instrumento;}
	
	@Column(name="nombre")
	public String getNombre() 			 				{return nombre;}
	public void setNombre(String nombre) 	 			{this.nombre = nombre;}
	
	@Column(name="descripcion")
	public String getDescripcion() 		 	 			{return descripcion;}
	public void setDescripcion(String descripcion)  	{this.descripcion = descripcion;}
	
	@Column(name="estado")
	public Long getEstado() 		         			{return estado;}
	public void setEstado(Long estado)       			{this.estado = estado;	}
	
	public static long getSerialversionuid() 			{return serialVersionUID;}
	
}