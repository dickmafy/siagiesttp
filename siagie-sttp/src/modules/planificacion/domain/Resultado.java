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
@Table(name="planificacion.m_resultado")
public class Resultado implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long    id;
	private	Long	objetivo;
	private Long	annio;
	private	Long	semestre;
	private	Long	tipo;
	private	Long	cuantificacion;
	private String  descripcion;
	private	Long	estado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_resultado")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	@Column(name="pk_objetivo")
	public Long getObjetivo() 								{return objetivo;}
	public void setObjetivo(Long objetivo) 					{this.objetivo = objetivo;}
	
	@Column(name="annio")
	public Long getAnnio() 									{return annio;}
	public void setAnnio(Long annio) 						{this.annio = annio;}
	
	@Column(name="semestre")
	public Long getSemestre() 								{return semestre;}
	public void setSemestre(Long semestre) 					{this.semestre = semestre;}
	
	@Column(name="tipo")
	public Long getTipo() 									{return tipo;}
	public void setTipo(Long tipo) 							{this.tipo = tipo;}
	
	@Column(name="cuantificacion")
	public Long getCuantificacion() 						{return cuantificacion;}
	public void setCuantificacion(Long cuantificacion) 		{this.cuantificacion = cuantificacion;}
	
	@Column(name="descripcion")
	public String getDescripcion() 							{return descripcion;}
	public void setDescripcion(String descripcion) 			{this.descripcion = descripcion;}

	public Long getEstado() {		return estado;	}
	public void setEstado(Long estado) {		this.estado = estado;	}

	
	@Transient
	public String getNombreSemestre() 
	{
		if(semestre.longValue()==1L)	{return "semestre1";}
		if(semestre.longValue()==2L)	{return "semestre2";}
		if(semestre.longValue()==3L)	{return "semestre3";}
		return "";
	}
	
	@Transient
	public String getNombreTipo() 
	{
		if(tipo == null) 			{return "";}
		if(tipo.longValue()==1L)	{return "tipo1";}
		if(tipo.longValue()==2L)	{return "tipo2";}
		return "";	
	}
	
	
	
}
	