package modules.cetpro.domain;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cetpro.m_horario")
public class Programacion implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long 	pk_ambiente;
	private Long 	pk_fecha;
	private Long 	pk_proceso;
	private Long 	docente;
	private Long 	justificacion;
	private Long 	estado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_ambiente")
	public Long getPk_ambiente() 								{return pk_ambiente;}
	public void setPk_ambiente(Long pk_ambiente) 				{this.pk_ambiente = pk_ambiente;}
	
	@Column(name="pk_fecha")
	public Long getPk_fecha() 									{return pk_fecha;}
	public void setPk_fecha(Long pk_fecha) 						{this.pk_fecha = pk_fecha;}
	
	@Column(name="pk_proceso")
	public Long getPk_proceso() 								{return pk_proceso;}
	public void setPk_proceso(Long pk_proceso) 					{this.pk_proceso = pk_proceso;}
	
	@Column(name="docente")
	public Long getDocente() 									{return docente;}
	public void setDocente(Long docente) 						{this.docente = docente;}
	
	@Column(name="justificacion")
	public Long getJustificacion() 								{return justificacion;}
	public void setJustificacion(Long justificacion) 			{this.justificacion = justificacion;}
	
	@Column(name="estado")
	public Long getEstado() 									{return estado;}
	public void setEstado(Long estado) 							{this.estado = estado;}
	

	
}