package modules.marco.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="marco.m_requisito_ambiente")
public class RequisitoAmbiente implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long profesion;
	private Double valor;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_requisito")
	public Long getId() 							{return id;}
	public void setId(Long id) 						{this.id = id;}
	
	@Column(name="pk_profesion")	
	public Long getProfesion() 						{return profesion;}
	public void setProfesion(Long profesion) 		{this.profesion = profesion;}
	
	public Double getValor() 						{return valor;}
	public void setValor(Double valor) 				{this.valor = valor;}
}