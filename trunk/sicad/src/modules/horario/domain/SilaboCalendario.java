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
@Table(name="horario.m_silabo_calendario")
public class SilaboCalendario implements Serializable
{
	//limpio
	

//	pk_silabo_calendario serial NOT NULL,
//	  pk_silabo_cronograma integer NOT NULL,
//	  fecha date,
//	  estado integer,

	
	private static final long serialVersionUID = 1L;
	private Long    id;
	private Long	pk_silabo_cronograma;
	private	Date 	fecha;
	private Long 	estado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_silabo_calendario")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	public Long getPk_silabo_cronograma() {
		return pk_silabo_cronograma;
	}
	public void setPk_silabo_cronograma(Long pk_silabo_cronograma) {
		this.pk_silabo_cronograma = pk_silabo_cronograma;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Long getEstado() {
		return estado;
	}
	public void setEstado(Long estado) {
		this.estado = estado;
	}
	
	
	
	
}