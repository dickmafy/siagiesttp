package modules.horario.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="horario.m_horario_docente")
public class HorarioDocente implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long    id;
	private Long personal;
	private Long carga;
	private	Long dia;
	private	Long inicio;
	private	Long fin;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_horario")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	@Column(name="pk_personal")
	public Long getPersonal() 								{return personal;}
	public void setPersonal(Long personal) 					{this.personal = personal;}
	
	@Column(name="carga")
	public Long getCarga() 									{return carga;}
	public void setCarga(Long carga) 						{this.carga = carga;}
	
	@Column(name="dia")
	public Long getDia() 									{return dia;}
	public void setDia(Long dia) 							{this.dia = dia;}
	
	@Column(name="hora_inicio")
	public Long getInicio() 								{return inicio;}
	public void setInicio(Long inicio) 						{this.inicio = inicio;}
	
	@Column(name="hora_fin")
	public Long getFin() 									{return fin;}
	public void setFin(Long fin) 							{this.fin = fin;}	
}