package modules.evaluaciones.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="evaluaciones.m_rango")
public class Rango implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long indicador;
	private String nombre;
	private Long valorMin;
	private Long valorMax;
	private Long estado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_rango")
	public Long getId() 									{return id;}
	public void setId(Long id)								{this.id = id;}
	
	@Column(name="pk_indicador")
	public Long getIndicador() 								{return indicador;}
	public void setIndicador(Long indicador) 				{this.indicador = indicador;}
	
	@Column(name="nombre")
	public String getNombre()								{return nombre;}
	public void setNombre(String nombre)				 	{this.nombre = nombre;}
	
	@Column(name="valormin")
	public Long getValorMin() 								{return valorMin;}
	public void setValorMin(Long valorMin)					{this.valorMin = valorMin;}
	
	@Column(name="valormax")
	public Long getValorMax()								{return valorMax;}
	public void setValorMax(Long valorMax)					{this.valorMax = valorMax;}
	
	@Column(name="estado")
	public Long getEstado() 								{return estado;}
	public void setEstado(Long estado) 						{this.estado = estado;}
	
	public static long getSerialversionuid()				{return serialVersionUID;}
	
	
}