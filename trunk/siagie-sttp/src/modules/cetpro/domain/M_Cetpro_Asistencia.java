package modules.cetpro.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cetpro.m_cetpro_asistencia")
public class M_Cetpro_Asistencia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pk_cetpro_asistencia;
	private Long pk_cetpro_matricula_alumno;
	private Long pk_cetpro_matricula_fecha;
	private Long asistencia;
	private Long estado;
	
	public M_Cetpro_Asistencia() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_cetpro_asistencia")
	public Long getPk_cetpro_asistencia() 										{return pk_cetpro_asistencia;}
	public void setPk_cetpro_asistencia(Long pk_cetpro_asistencia) 				{this.pk_cetpro_asistencia = pk_cetpro_asistencia;}

	@Column(name="pk_cetpro_matricula_alumno")
	public Long getPk_cetpro_matricula_alumno() 								{return pk_cetpro_matricula_alumno;}
	public void setPk_cetpro_matricula_alumno(Long pk_cetpro_matricula_alumno) 	{this.pk_cetpro_matricula_alumno = pk_cetpro_matricula_alumno;}

	@Column(name="pk_cetpro_matricula_fecha")
	public Long getPk_cetpro_matricula_fecha() 									{return pk_cetpro_matricula_fecha;}
	public void setPk_cetpro_matricula_fecha(Long pk_cetpro_matricula_fecha) 	{this.pk_cetpro_matricula_fecha = pk_cetpro_matricula_fecha;}

	@Column(name="asistencia")
	public Long getAsistencia() 												{return asistencia;}
	public void setAsistencia(Long asistencia) 									{this.asistencia = asistencia;}

	@Column(name="estado")
	public Long getEstado() 													{return estado;}
	public void setEstado(Long estado) 											{this.estado = estado;}
	
}
