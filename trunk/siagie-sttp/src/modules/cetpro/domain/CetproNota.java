package modules.cetpro.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cetpro.m_matricula_cetpro")
public class CetproNota implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long 	id;
	private Long 	pk_silabo_oferta;
	private Long 	pk_persona;
	private Long 	estado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_silabo_matricula")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getPk_silabo_oferta() {
		return pk_silabo_oferta;
	}
	public void setPk_silabo_oferta(Long pk_silabo_oferta) {
		this.pk_silabo_oferta = pk_silabo_oferta;
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