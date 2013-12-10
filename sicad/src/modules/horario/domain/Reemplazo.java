package modules.horario.domain;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="horario.m_reemplazo")
public class Reemplazo implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long seccion;
	private Long tipo;
	private	Long motivo;
	private	String descripcion;
	private Date fecha_inicio;
	private Date fecha_fin;
	private Long docente;
	private Long estado;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_reemplazo")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	@Column(name="pk_seccion")
	public Long getSeccion() 								{return seccion;}
	public void setSeccion(Long seccion) 					{this.seccion = seccion;}
	
	@Column(name="tipo")
	public Long getTipo() 									{return tipo;}
	public void setTipo(Long tipo) 							{this.tipo = tipo;}
	
	@Column(name="motivo")
	public Long getMotivo() 								{return motivo;}
	public void setMotivo(Long motivo) 						{this.motivo = motivo;}
	
	@Column(name="descripcion")
	public String getDescripcion() 							{return descripcion;}
	public void setDescripcion(String descripcion) 			{this.descripcion = descripcion;}
	
	@Column(name="fecha_inicio")
	public Date getFecha_inicio() 							{return fecha_inicio;}
	public void setFecha_inicio(Date fecha_inicio) 			{this.fecha_inicio = fecha_inicio;}
	
	@Column(name="fecha_fin")
	public Date getFecha_fin() 								{return fecha_fin;}
	public void setFecha_fin(Date fecha_fin) 				{this.fecha_fin = fecha_fin;}
	
	@Column(name="docente")
	public Long getDocente() 								{return docente;}
	public void setDocente(Long docente) 					{this.docente = docente;}
	
	@Column(name="estado")
	public Long getEstado() 								{return estado;}
	public void setEstado(Long estado) 						{this.estado = estado;}
}
	