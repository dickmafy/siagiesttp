package modules.mantenimiento.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mantenimiento.m_ubigeo")
public class Ubigeo implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long departamento;
	private Long provincia;
	private Long distrito;	
	private String descripcion;
	private String postal;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_ubigeo")
	public Long getId()                         	{return id;}
	public void setId(Long id)                  	{this.id=id;}
	
	@Column(name="departamento")
	public Long getDepartamento() 					{return departamento;}
	public void setDepartamento(Long departamento) 	{this.departamento = departamento;}
	
	@Column(name="provincia")
	public Long getProvincia() 						{return provincia;}
	public void setProvincia(Long provincia) 		{this.provincia = provincia;}
	
	@Column(name="distrito")
	public Long getDistrito() 						{return distrito;}
	public void setDistrito(Long distrito) 			{this.distrito = distrito;}
	
	@Column(name="descripcion")
	public String getDescripcion() 					{return descripcion;}
	public void setDescripcion(String descripcion) 	{this.descripcion = descripcion;}
	
	@Column(name="postal")
	public String getPostal() 						{return postal;}
	public void setPostal(String postal) 			{this.postal = postal;}
}