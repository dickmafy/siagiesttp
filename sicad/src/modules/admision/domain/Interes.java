package modules.admision.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="admision.m_interes")
public class Interes implements Serializable
{
	private static final long serialVersionUID = -5880641907901053819L;
	private Long pk_interesado;
	private Long pk_profesion;
	private String nombreProfesion;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_interesado")
	public Long getPk_interesado() 							{return pk_interesado;}
	public void setPk_interesado(Long pk_interesado) 		{this.pk_interesado = pk_interesado;}
	
	@Column(name="pk_profesion")
	public Long getPk_profesion() 							{return pk_profesion;}
	public void setPk_profesion(Long pk_profesion) 			{this.pk_profesion = pk_profesion;}
	
	@Transient
	public String getNombreProfesion() 						{return nombreProfesion;}
	public void setNombreProfesion(String nombreProfesion) 	{this.nombreProfesion = nombreProfesion;}
}
