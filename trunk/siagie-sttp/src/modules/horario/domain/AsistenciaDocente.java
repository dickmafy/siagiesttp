package modules.horario.domain;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="horario.m_asistencia_docente")
public class AsistenciaDocente implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long seccion;
	private Long ambiente;
	private Date fecha;
	private	Long motivo;
	private	String descripcion;
	private Date registroFecha;
	private Long registroUsuario;
	private	Long estado;
	
	@Id
	@Column(name="pk_ambiente")
	public Long getAmbiente() 								{return ambiente;}
	public void setAmbiente(Long ambiente) 					{this.ambiente = ambiente;}
	
	@Id
	@Column(name="pk_seccion")
	public Long getSeccion() 								{return seccion;}	
	public void setSeccion(Long seccion) 					{this.seccion = seccion;}
	
	@Id
	@Column(name="pk_fecha")
	public Date getFecha() 									{return fecha;}
	public void setFecha(Date fecha) 						{this.fecha = fecha;}
	
	@Column(name="motivo")	
	public Long getMotivo() 								{return motivo;}
	public void setMotivo(Long motivo) 						{this.motivo = motivo;}
	
	@Column(name="descripcion")
	public String getDescripcion() 							{return descripcion;}
	public void setDescripcion(String descripcion) 			{this.descripcion = descripcion;}
	
	@Column(name="registro_fecha")
	public Date getRegistroFecha() 							{return registroFecha;}
	public void setRegistroFecha(Date registroFecha) 		{this.registroFecha = registroFecha;}
	
	@Column(name="registro_usuario")
	public Long getRegistroUsuario() 						{return registroUsuario;}
	public void setRegistroUsuario(Long registroUsuario) 	{this.registroUsuario = registroUsuario;}
	
	@Column(name="estado")
	public Long getEstado() 								{return estado;}
	public void setEstado(Long estado) 						{this.estado = estado;}	
}