package modules.planificacion.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="planificacion.m_estrategia")
public class Estrategia implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long    id;
	private	Long	objetivo;
	private Long	codigo;
	private	Long 	ambito;
	private	String	descripcion;
	private	Long	estado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_estrategia")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	@Column(name="pk_objetivo")
	public Long getObjetivo() 								{return objetivo;}
	public void setObjetivo(Long objetivo) 					{this.objetivo = objetivo;}
	
	@Column(name="pk_codigo")
	public Long getCodigo() 								{return codigo;}
	public void setCodigo(Long codigo) 						{this.codigo = codigo;}
	
	@Column(name="ambito")
	public Long getAmbito() 								{return ambito;}
	public void setAmbito(Long ambito) 						{this.ambito = ambito;}
	
	@Column(name="descripcion")
	public String getDescripcion() 							{return descripcion;}
	public void setDescripcion(String descripcion) 			{this.descripcion = descripcion;}
	
	@Column(name="estado")
	public Long getEstado() 								{return estado;}
	public void setEstado(Long estado) 						{this.estado = estado;}
	}
	