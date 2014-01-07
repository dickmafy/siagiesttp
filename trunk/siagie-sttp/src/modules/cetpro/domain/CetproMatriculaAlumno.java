package modules.cetpro.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cetpro.m_cetpro_matricula_alumno")
public class CetproMatriculaAlumno implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long 	pk_cetpro_matricula_alumno;
	private Long 	pk_cetpro_matricula;
	private Long 	pk_persona;
	private Long 	estado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_cetpro_matricula_alumno")
	public Long getPk_cetpro_matricula_alumno() {
		return pk_cetpro_matricula_alumno;
	}
	public void setPk_cetpro_matricula_alumno(Long pk_cetpro_matricula_alumno) {
		this.pk_cetpro_matricula_alumno = pk_cetpro_matricula_alumno;
	}
	public Long getPk_cetpro_matricula() {
		return pk_cetpro_matricula;
	}
	public void setPk_cetpro_matricula(Long pk_cetpro_matricula) {
		this.pk_cetpro_matricula = pk_cetpro_matricula;
	}
	public Long getPk_persona() {
		return pk_persona;
	}
	public void setPk_persona(Long pk_persona) {
		this.pk_persona = pk_persona;
	}
	public Long getEstado() {
		return estado;
	}
	public void setEstado(Long estado) {
		this.estado = estado;
	}
	
	
	

}