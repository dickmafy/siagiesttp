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
@Table(name="admision.m_proceso_oferta")
public class ProcesoOferta implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long proceso;
	private Long profesion;
	private Long turno;
	
	private String nombreProfesion;
	private String nombreTurno;
	private boolean check;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_oferta")
	public Long getId() 							{return id;}
	public void setId(Long id) 						{this.id = id;}
	
	@Column(name="pk_proceso")
	public Long getProceso() 						{return proceso;}
	public void setProceso(Long proceso)		 	{this.proceso = proceso;}
	
	@Column(name="pk_profesion")
	public Long getProfesion() 						{return profesion;}
	public void setProfesion(Long profesion) 		{this.profesion = profesion;}
	
	@Column(name="pk_turno")
	public Long getTurno() 							{return turno;}
	public void setTurno(Long turno)			 	{this.turno = turno;}
	
	@Transient
	public String getNombreProfesion() 				{return nombreProfesion;}
	public void setNombreProfesion(String nombreProfesion) {this.nombreProfesion = nombreProfesion;}
	
	@Transient
	public String getNombreTurno() 									
	{
		if(turno.longValue()==1L)	{return "Mañana";}
		if(turno.longValue()==2L)	{return "Tarde";}
		if(turno.longValue()==3L)	{return "Noche";}
		return "";
	}
	public void setNombreTurno(String nombreTurno) 			{this.nombreTurno = nombreTurno;}
	
	@Transient
	public boolean isCheck() 										{return check;}
	public void setCheck(boolean check) 							{this.check = check;}
	
	
}