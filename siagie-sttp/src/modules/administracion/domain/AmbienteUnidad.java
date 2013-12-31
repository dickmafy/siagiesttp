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
@Table(name="administracion.m_ambiente_unidad")
public class AmbienteUnidad implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long institucion;
	private Long unidad;
	private Long tipo;
	private Long horas;
	
	private Long valorAmbiente;
	private Long valorCapacidad;
	private String nombreHorario;
	private String nombreTipo;
	private	String nombreAmbiente;
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_transitoria")
	public Long getId()                          	{return id;}
	public void setId(Long id)                   	{this.id = id;}
	
	@Column(name="pk_institucion")
	public Long getInstitucion() 					{return institucion;}
	public void setInstitucion(Long institucion) 	{this.institucion = institucion;}
	
	@Column(name="pk_unidad")
	public Long getUnidad() 						{return unidad;}
	public void setUnidad(Long unidad) 				{this.unidad = unidad;}
	
	@Column(name="pk_tipo")
	public Long getTipo() 							{return tipo;}
	public void setTipo(Long tipo) 					{this.tipo = tipo;}
	
	@Column(name="horas")
	public Long getHoras() 							{return horas;}
	public void setHoras(Long horas) 				{this.horas = horas;}
	
	@Transient
	public Long getValorAmbiente() 							{return valorAmbiente;}
	public void setValorAmbiente(Long valorAmbiente) 		{this.valorAmbiente = valorAmbiente;}
	
	@Transient
	public Long getValorCapacidad() 						{return valorCapacidad;}
	public void setValorCapacidad(Long valorCapacidad) 		{this.valorCapacidad = valorCapacidad;}
	
	@Transient
	public String getNombreHorario() 						{return nombreHorario;}
	public void setNombreHorario(String nombreHorario)	 	{this.nombreHorario = nombreHorario;}
	
	@Transient
	public String getNombreAmbiente() 						{return nombreAmbiente;}
	public void setNombreAmbiente(String nombreAmbiente) 	{this.nombreAmbiente = nombreAmbiente;}
	
	@Transient
	public String getNombreTipo() 							{return nombreTipo;}
	public void setNombreTipo(String nombreTipo) 			{this.nombreTipo = nombreTipo;}
	
}