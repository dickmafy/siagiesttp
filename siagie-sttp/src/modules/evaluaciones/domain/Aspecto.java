package modules.evaluaciones.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="evaluaciones.m_aspecto")
public class Aspecto implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private String descripcion;
	private String sigla;
	private Long estado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_aspecto")
	public Long getId() 				 			{return id;}
	public void setId(Long id) 			 	 		{this.id = id;}
	
	@Column(name="nombre")
	public String getNombre() 			 			{return nombre;}
	public void setNombre(String nombre) 	 		{this.nombre = nombre;}
	
	@Column(name="descripcion")
	public String getDescripcion() 		 	 		{return descripcion;}
	public void setDescripcion(String descripcion)  {this.descripcion = descripcion;}
	
	@Column(name="sigla")
	public String getSigla()		      			{return sigla;}
	public void setSigla(String sigla) 	 	 		{this.sigla = sigla;}
	
	@Column(name="estado")
	public Long getEstado() 		         		{return estado;}
	public void setEstado(Long estado)       		{this.estado = estado;	}
	
	public static long getSerialversionuid() 		{return serialVersionUID;}
			
		
}