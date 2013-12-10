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
@Table(name="planificacion.m_objetivo")
public class Objetivo implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long    id;
	private	Long	institucion;
	private Long	lineamiento;
	private	Long	variable;
	private	String	descripcion;
	private	Long	estado;
	private String 	nombreLineamiento;
	private	String	codigoLineamiento;
	private String 	nombreVariable;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_objetivo")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	@Column(name="pk_institucion")
	public Long getInstitucion() 							{return institucion;}
	public void setInstitucion(Long institucion) 			{this.institucion = institucion;}
	
	@Column(name="lineamiento")
	public Long getLineamiento() 							{return lineamiento;}
	public void setLineamiento(Long lineamiento) 			{this.lineamiento = lineamiento;}
	
	@Column(name="variable")
	public Long getVariable() 								{return variable;}
	public void setVariable(Long variable) 					{this.variable = variable;}
	
	@Column(name="descripcion")
	public String getDescripcion() 							{return descripcion;}
	public void setDescripcion(String descripcion) 			{this.descripcion = descripcion;}
	
	@Column(name="estado")
	public Long getEstado() 								{return estado;}
	public void setEstado(Long estado) 						{this.estado = estado;}
	
	@Transient
	public String getNombreLineamiento() 
	{
		Politicas obj=new Politicas();
		return obj.obtenerNombre(lineamiento, 0L).toUpperCase();	
	}
	
	@Transient
	public String getCodigoLineamiento()		 
	{
		if(lineamiento == null) 		{return "";}
		if(lineamiento.longValue()==1L)	{return "I";}
		if(lineamiento.longValue()==2L)	{return "II";}
		return "";	
	}
	public void setCodigoLineamiento(String codigoLineamiento) {this.codigoLineamiento = codigoLineamiento;}
	
	@Transient
	public String getNombreVariable() 
	{
		Politicas obj=new Politicas();
		return obj.obtenerNombre(lineamiento, variable).toUpperCase();
	}

	
}
	