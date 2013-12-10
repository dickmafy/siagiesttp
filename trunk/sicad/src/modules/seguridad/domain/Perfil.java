package modules.seguridad.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="seguridad.perfil")
public class Perfil implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long tipo;
	private String nombre;
	private String descripcion;
	private Long estado;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_perfil")
	public Long getId()                                                 	{return id;}
	public void setId(Long id)                                          	{this.id=id;}

	@Column(name="tipo")
	public Long getTipo() 													{return tipo;}
	public void setTipo(Long tipo) 											{this.tipo = tipo;}
	
	@Column(name="nombre")
	public String getNombre()                                           	{return nombre;}
	public void setNombre(String nombre)                                	{this.nombre=nombre;}

	@Column(name="descripcion")
	public String getDescripcion()                                      	{return descripcion;}
	public void setDescripcion(String descripcion)                      	{this.descripcion=descripcion;}

	@Column(name="estado")
	public Long getEstado()                                             	{return estado;}
	public void setEstado(Long estado)                                  	{this.estado=estado;}	
}