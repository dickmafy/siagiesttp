package modules.admision.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
public class MatriculaSeccion implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long 	id;
	private	Long	modulo;
	private	Long	tipo;
	private	Long	unidad;
	private	Long	seccion;
	private	String	nombreUnidad;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_matricula")
	public Long getId()         				{return id;}
	public void setId(Long id)                  {this.id = id;}
	
	@Column(name="pk_seccion")
	public Long getSeccion() 					{return seccion;}
	public void setSeccion(Long seccion) 		{this.seccion = seccion;}
	
	@Transient
	public Long getModulo() 					{return modulo;}
	public void setModulo(Long modulo) 			{this.modulo = modulo;}
	
	@Transient
	public Long getTipo() 						{return tipo;}
	public void setTipo(Long tipo) 				{this.tipo = tipo;}
	
	@Transient
	public Long getUnidad() 					{return unidad;}
	public void setUnidad(Long unidad) 			{this.unidad = unidad;}
	
	@Transient
	public String getNombreUnidad() 					{return nombreUnidad;}
	public void setNombreUnidad(String nombreUnidad) 	{this.nombreUnidad = nombreUnidad;}
}