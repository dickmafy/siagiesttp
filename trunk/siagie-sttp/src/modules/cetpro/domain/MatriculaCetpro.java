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
public class MatriculaCetpro implements Serializable
{
	private static final long serialVersionUID = 1L;
	
//	 pk_matricula integer NOT NULL,
//	  pk_persona integer,
//	  pk_fecha date,
//	  tipo integer,
//	  estado integer,
//	  
	private Long 	pk_matricula;
	private Long 	pk_persona;
	private Long 	pk_ambiente;
	private Long 	pk_fecha;
	private Long 	tipo;
	private Long 	estado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_matricula")
	public Long getPk_matricula() {
		return pk_matricula;
	}
	public void setPk_matricula(Long pk_matricula) {
		this.pk_matricula = pk_matricula;
	}
	public Long getPk_persona() {
		return pk_persona;
	}
	public void setPk_persona(Long pk_persona) {
		this.pk_persona = pk_persona;
	}
	public Long getPk_ambiente() {
		return pk_ambiente;
	}
	public void setPk_ambiente(Long pk_ambiente) {
		this.pk_ambiente = pk_ambiente;
	}
	public Long getPk_fecha() {
		return pk_fecha;
	}
	public void setPk_fecha(Long pk_fecha) {
		this.pk_fecha = pk_fecha;
	}
	public Long getTipo() {
		return tipo;
	}
	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}
	public Long getEstado() {
		return estado;
	}
	public void setEstado(Long estado) {
		this.estado = estado;
	}
	


}