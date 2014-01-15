package modules.cetpro.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cetpro.m_nota")
public class CetproNota implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long 	id;
	private Long 	pk_cetpro_matricula_alumno;
	private Long 	pk_cetpro_ct;
	private Double 	nota;
	private Long 	estado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_nota")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPk_cetpro_matricula_alumno() {
		return pk_cetpro_matricula_alumno;
	}
	public void setPk_cetpro_matricula_alumno(Long pk_cetpro_matricula_alumno) {
		this.pk_cetpro_matricula_alumno = pk_cetpro_matricula_alumno;
	}
	public Long getPk_cetpro_ct() {
		return pk_cetpro_ct;
	}
	public void setPk_cetpro_ct(Long pk_cetpro_ct) {
		this.pk_cetpro_ct = pk_cetpro_ct;
	}
	
	public Long getEstado() {
		return estado;
	}
	public void setEstado(Long estado) {
		this.estado = estado;
	}
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}

	
	
	

}