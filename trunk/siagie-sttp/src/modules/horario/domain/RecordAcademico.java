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
@Table(name="horario.m_record_academico")
public class RecordAcademico implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long persona;
	private Long nota;
	private	Long promedio;
	private	Long recuperacion;
	private Date registro;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_record")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	@Column(name="pk_persona")
	public Long getPersona() 								{return persona;}
	public void setPersona(Long persona) 					{this.persona = persona;}
	
	@Column(name="pk_nota")
	public Long getNota() 									{return nota;}
	public void setNota(Long nota) 							{this.nota = nota;}
	
	@Column(name="promedio")
	public Long getPromedio() 								{return promedio;}
	public void setPromedio(Long promedio) 					{this.promedio = promedio;}
	
	@Column(name="recuperacion")
	public Long getRecuperacion() 							{return recuperacion;}
	public void setRecuperacion(Long recuperacion) 			{this.recuperacion = recuperacion;}
	
	@Column(name="registro")
	public Date getRegistro() 								{return registro;}
	public void setRegistro(Date registro) 					{this.registro = registro;}
}
	