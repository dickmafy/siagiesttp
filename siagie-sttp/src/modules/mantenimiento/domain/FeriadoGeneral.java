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
@Table(name="mantenimiento.m_feriado")
public class FeriadoGeneral implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long tipo;
	private String nombre;
	private Date fechaInicio;
	private Date fechaFin;
	private String observacion;
	private Long estado;
	
	private String nombreTipo;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_feriado")
	public Long getId() 							{return id;}
	public void setId(Long id) 						{this.id = id;}
	
	@Column(name="nombre")
	public String getNombre() 						{return nombre;}
	public void setNombre(String nombre) 			{this.nombre = nombre;}

	@Column(name="observacion")
	public String getObservacion() 					{return observacion;}
	public void setObservacion(String observacion) 	{this.observacion = observacion;}
	
	@Column(name="estado")
	public Long getEstado() 						{return estado;}
	public void setEstado(Long estado) 				{this.estado = estado;}
	
	@Column(name="tipo")
	public Long getTipo() 							{return tipo;}
	public void setTipo(Long tipo) 					{this.tipo = tipo;}
	
	@Column(name="fecha_inicio")
	public Date getFechaInicio() 					{return fechaInicio;}
	public void setFechaInicio(Date fechaInicio) 	{this.fechaInicio = fechaInicio;}
	
	@Column(name="fecha_fin")
	public Date getFechaFin() 						{return fechaFin;}
	public void setFechaFin(Date fechaFin) 			{this.fechaFin = fechaFin;}
	
	@Transient
	public String getNombreTipo() 
	{
		if(tipo.longValue()==1L)		{return "Feriado Nacional";}
		if(tipo.longValue()==2L)		{return "Feriado Gobierno";}
		return "Feriado Institucional";
	}
	public void setNombreTipo(String nombreTipo) 	{this.nombreTipo = nombreTipo;}
	
	
}