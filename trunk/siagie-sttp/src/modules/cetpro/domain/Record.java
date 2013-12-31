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
@Table(name="cetpro.m_record")
public class Record implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long 	pk_record;
	private Long 	pk_participante;
	private Long 	pk_persona;
	private Long 	pk_criterio;
	private Long 	procedimental;
	private Long 	actitudinal;
	private Long 	conceptual;
	private Long 	promedio;
	private Long 	recuperacion;
	private Date 	registro_fecha;
	private Long 	registro_usuario;
	private Long 	estado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_record")
	public Long getPk_record() 														{return pk_record;}
	public void setPk_record(Long pk_record) 										{this.pk_record = pk_record;}
	
	@Column(name="pk_participante")
	public Long getPk_participante() 												{return pk_participante;}
	public void setPk_participante(Long pk_participante) 							{this.pk_participante = pk_participante;}
	
	@Column(name="pk_persona")
	public Long getPk_persona() 													{return pk_persona;}
	public void setPk_persona(Long pk_persona) 										{this.pk_persona = pk_persona;}
	
	@Column(name="pk_criterio")
	public Long getPk_criterio() 													{return pk_criterio;}
	public void setPk_criterio(Long pk_criterio) 									{this.pk_criterio = pk_criterio;}
	
	@Column(name="procedimental")
	public Long getProcedimental() 													{return procedimental;}
	public void setProcedimental(Long procedimental) 								{this.procedimental = procedimental;}
	
	@Column(name="actitudinal")
	public Long getActitudinal() 													{return actitudinal;}
	public void setActitudinal(Long actitudinal) 									{this.actitudinal = actitudinal;}
	
	@Column(name="conceptual")
	public Long getConceptual() 													{return conceptual;}
	public void setConceptual(Long conceptual) 										{this.conceptual = conceptual;}
	
	@Column(name="promedio")
	public Long getPromedio() 														{return promedio;}
	public void setPromedio(Long promedio) 											{this.promedio = promedio;}
	
	@Column(name="recuperacion")
	public Long getRecuperacion() 													{return recuperacion;}
	public void setRecuperacion(Long recuperacion) 									{this.recuperacion = recuperacion;}
	
	@Column(name="registro_fecha")
	public Date getRegistro_fecha() 												{return registro_fecha;}
	public void setRegistro_fecha(Date registro_fecha) 								{this.registro_fecha = registro_fecha;}
	
	@Column(name="registro_usuario")
	public Long getRegistro_usuario() 												{return registro_usuario;}
	public void setRegistro_usuario(Long registro_usuario) 							{this.registro_usuario = registro_usuario;}
	
	@Column(name="estado")
	public Long getEstado() 														{return estado;}
	public void setEstado(Long estado) 												{this.estado = estado;}
	
}