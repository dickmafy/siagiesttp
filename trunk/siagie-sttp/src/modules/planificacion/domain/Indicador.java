package modules.planificacion.domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="planificacion.m_indicador")
public class Indicador implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long    id;
	private	Long	objetivo;
	private Long	tipo;
	private	String	descripcion;
	private	Long	estado;
	private String nombreTipo;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_indicador")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	@Column(name="pk_objetivo")
	public Long getObjetivo() 								{return objetivo;}
	public void setObjetivo(Long objetivo) 					{this.objetivo = objetivo;}
	
	@Column(name="tipo")
	public Long getTipo() 									{return tipo;}
	public void setTipo(Long tipo) 							{this.tipo = tipo;}
	
	@Column(name="descripcion")
	public String getDescripcion() 							{return descripcion;}
	public void setDescripcion(String descripcion) 			{this.descripcion = descripcion;}
	
	@Column(name="estado")
	public Long getEstado() 								{return estado;}
	public void setEstado(Long estado) 						{this.estado = estado;}
	
	@Transient
	public String getNombreTipo() 
	{
		if(tipo == null) 			{return "";}
		if(tipo.longValue()==1L)	{return "indicador 1";}
		if(tipo.longValue()==2L)	{return "indicador 2";}
		return "";	
	}
	
}
	