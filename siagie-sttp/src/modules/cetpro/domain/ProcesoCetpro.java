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
@Table(name="cetpro.m_proceso")
public class ProcesoCetpro implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long 	pk_proceso;
	private Long 	pk_oferta;
	private Long 	annio;
	private Long 	turno;
	private Date 	fecha_inicio;
	private Date 	fecha_termino;
	private Long 	registro_usuario;
	private Date 	registro_fecha;
	private Long 	estado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_proceso")
	public Long getPk_proceso() 										{return pk_proceso;}
	public void setPk_proceso(Long pk_proceso) 							{this.pk_proceso = pk_proceso;}
	
	@Column(name="pk_oferta")
	public Long getPk_oferta() 											{return pk_oferta;}
	public void setPk_oferta(Long pk_oferta) 							{this.pk_oferta = pk_oferta;}
	
	@Column(name="annio")
	public Long getAnnio() 												{return annio;}
	public void setAnnio(Long annio) 									{this.annio = annio;}
	
	@Column(name="turno")
	public Long getTurno() 												{return turno;}
	public void setTurno(Long turno) 									{this.turno = turno;}
	
	@Column(name="fecha_inicio")
	public Date getFecha_inicio() 										{return fecha_inicio;}
	public void setFecha_inicio(Date fecha_inicio) 						{this.fecha_inicio = fecha_inicio;}
	
	@Column(name="fecha_termino")
	public Date getFecha_termino() 										{return fecha_termino;}
	public void setFecha_termino(Date fecha_termino) 					{this.fecha_termino = fecha_termino;}
	
	@Column(name="registro_usuario")
	public Long getRegistro_usuario() 									{return registro_usuario;}
	public void setRegistro_usuario(Long registro_usuario) 				{this.registro_usuario = registro_usuario;}
	
	@Column(name="registro_fecha")
	public Date getRegistro_fecha() 									{return registro_fecha;}
	public void setRegistro_fecha(Date registro_fecha) 					{this.registro_fecha = registro_fecha;}
	
	@Column(name="estado")
	public Long getEstado() 											{return estado;}
	public void setEstado(Long estado) 									{this.estado = estado;}
	

	

}