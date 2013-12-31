package modules.cetpro.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cetpro.m_cronograma")
public class Calendario implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long 	pk_oferta;
	private Long 	pk_ambiente;
	private Long 	dia;
	private Long 	hora_inicio;
	private Long 	hora_fin;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_oferta")
	public Long getPk_oferta() 												{return pk_oferta;}
	public void setPk_oferta(Long pk_oferta) 								{this.pk_oferta = pk_oferta;}
	
	@Column(name="pk_ambiente")
	public Long getPk_ambiente() 											{return pk_ambiente;}
	public void setPk_ambiente(Long pk_ambiente) 							{this.pk_ambiente = pk_ambiente;}
	
	@Column(name="dia")
	public Long getDia() 													{return dia;}
	public void setDia(Long dia) 											{this.dia = dia;}
	
	@Column(name="hora_inicio")
	public Long getHora_inicio() 											{return hora_inicio;}
	public void setHora_inicio(Long hora_inicio) 							{this.hora_inicio = hora_inicio;}
	
	@Column(name="hora_fin")
	public Long getHora_fin() 												{return hora_fin;}
	public void setHora_fin(Long hora_fin) 									{this.hora_fin = hora_fin;}

}