package modules.cetpro.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cetpro.m_participante")
public class Participante implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long 	pk_participante;
	private Long 	pk_proceso;
	private Long 	pk_persona;
	private Date 	pago_fecha;
	private Long 	pago_banco;
	private String 	pago_recibo;
	private Date 	registro_fecha;
	private Long 	registro_usuario;
	private Long 	estado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_participante")
	public Long getPk_participante() 											{return pk_participante;}
	public void setPk_participante(Long pk_participante) 						{this.pk_participante = pk_participante;}
	
	@Column(name="pk_proceso")
	public Long getPk_proceso() 												{return pk_proceso;}
	public void setPk_proceso(Long pk_proceso) 									{this.pk_proceso = pk_proceso;}
	
	@Column(name="pk_persona")
	public Long getPk_persona() 												{return pk_persona;}
	public void setPk_persona(Long pk_persona) 									{this.pk_persona = pk_persona;}
	
	@Column(name="pago_fecha")
	public Date getPago_fecha() 												{return pago_fecha;}
	public void setPago_fecha(Date pago_fecha) 									{this.pago_fecha = pago_fecha;}
	
	@Column(name="pago_banco")
	public Long getPago_banco() 												{return pago_banco;}
	public void setPago_banco(Long pago_banco) 									{this.pago_banco = pago_banco;}
	
	@Column(name="pago_recibo")
	public String getPago_recibo() 												{return pago_recibo;}
	public void setPago_recibo(String pago_recibo) 								{this.pago_recibo = pago_recibo;}
	
	@Column(name="registro_fecha")
	public Date getRegistro_fecha() 											{return registro_fecha;}
	public void setRegistro_fecha(Date registro_fecha) 							{this.registro_fecha = registro_fecha;}
	
	@Column(name="registro_usuario")
	public Long getRegistro_usuario() 											{return registro_usuario;}
	public void setRegistro_usuario(Long registro_usuario) 						{this.registro_usuario = registro_usuario;}
	
	@Column(name="estado")
	public Long getEstado() 													{return estado;}
	public void setEstado(Long estado) 											{this.estado = estado;}
	

	
}