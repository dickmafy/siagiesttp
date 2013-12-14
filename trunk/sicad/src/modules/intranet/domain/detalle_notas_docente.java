package modules.intranet.domain;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="intranet.m_detalle_notas_docente")
public class detalle_notas_docente implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date pk_silabo_cronograma;
	private Long pk_nota1;
	private	Long pk_nota2;
	private	Long pk_nota3;
	private	Long pk_alumno;
	private	Long estado;
	private Long aaaaaaaaaaaaaa;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_detalle_notas_docente")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	public Date getPk_silabo_cronograma() {
		return pk_silabo_cronograma;
	}
	public void setPk_silabo_cronograma(Date pk_silabo_cronograma) {
		this.pk_silabo_cronograma = pk_silabo_cronograma;
	}
	public Long getPk_nota1() {
		return pk_nota1;
	}
	public void setPk_nota1(Long pk_nota1) {
		this.pk_nota1 = pk_nota1;
	}
	public Long getPk_nota2() {
		return pk_nota2;
	}
	public void setPk_nota2(Long pk_nota2) {
		this.pk_nota2 = pk_nota2;
	}
	public Long getPk_nota3() {
		return pk_nota3;
	}
	public void setPk_nota3(Long pk_nota3) {
		this.pk_nota3 = pk_nota3;
	}
	public Long getPk_alumno() {
		return pk_alumno;
	}
	public void setPk_alumno(Long pk_alumno) {
		this.pk_alumno = pk_alumno;
	}
	public Long getEstado() {
		return estado;
	}
	public void setEstado(Long estado) {
		this.estado = estado;
	}
	public Long getAaaaaaaaaaaaaa() {
		return aaaaaaaaaaaaaa;
	}
	public void setAaaaaaaaaaaaaa(Long aaaaaaaaaaaaaa) {
		this.aaaaaaaaaaaaaa = aaaaaaaaaaaaaa;
	}
	
	
}
	