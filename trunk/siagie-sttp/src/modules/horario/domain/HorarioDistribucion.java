package modules.horario.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="horario.m_distribucion")
public class HorarioDistribucion implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long seccion;
	private Long ambiente;
	private	Long dia;
	private	Long inicio;
	private	Long fin;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_seccion")
	public Long getSeccion() 								{return seccion;}
	public void setSeccion(Long seccion) 					{this.seccion = seccion;}
	
	@Column(name="pk_ambiente")
	public Long getAmbiente() 								{return ambiente;}
	public void setAmbiente(Long ambiente) 					{this.ambiente = ambiente;}
	
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