package modules.marco.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="marco.m_profesion_capacidad")
public class Capacidad implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long profesion;
	private String nombre;
	private String descripcion;
	private Long estado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_capacidad")
	public Long getId() 							{return id;}
	public void setId(Long id) 						{this.id = id;}
	
	@Column(name="pk_profesion")
	public Long getProfesion() 						{return profesion;}
	public void setProfesion(Long profesion) 		{this.profesion = profesion;}
	
	@Column(name="nombre")
	public String getNombre() 						{return nombre;}
	public void setNombre(String nombre) 			{this.nombre = nombre;}
	
	@Column(name="descripcion")
	public String getDescripcion() 					{return descripcion;}
	public void setDescripcion(String descripcion) 	{this.descripcion = descripcion;}
	
	@Column(name="estado")
	public Long getEstado() 						{return estado;}
	public void setEstado(Long estado) 				{this.estado = estado;}
}