package modules.evaluaciones.domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import modules.seguridad.domain.Perfil;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.bytecode.javassist.FieldHandled;
import org.hibernate.bytecode.javassist.FieldHandler;

@Entity
@Table(name="evaluaciones.m_encuesta")
public class Encuesta implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long pk_instrumento;
	private Long pk_pregunta;
	private Long estado;
	private Pregunta beanPregunta;
	private FieldHandler fieldHandler;
	private Long alternativa;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_encuesta")	
	public Long getId()				               		{return id;}	
	public void setId(Long id) 		               		{this.id = id;}
	
	
	@Column(name="pk_instrumento")
	public Long getPk_instrumento() 					{return pk_instrumento;	}
	public void setPk_instrumento(Long pk_instrumento) 	{this.pk_instrumento = pk_instrumento;}
	
	
	@Column(name="pk_pregunta")
	public Long getPk_pregunta()						{return pk_pregunta;}
	public void setPk_pregunta(Long pk_pregunta) 		{this.pk_pregunta = pk_pregunta;	}
	
	@Column(name="alternativa")
	public Long getAlternativa() 						{return alternativa;}
	public void setAlternativa(Long alternativa) 		{this.alternativa = alternativa;}
	
	@Column(name="estado")
	public Long getEstado()                             {return estado;}
	public void setEstado(Long estado)                  {this.estado=estado;}
	
	public static long getSerialversionuid() 			{return serialVersionUID;}


	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="pk_pregunta",  insertable=false, updatable=false)
	@LazyToOne(LazyToOneOption.PROXY)
	public Pregunta getBeanPregunta()									 
	{if (fieldHandler != null) 	{return (Pregunta) fieldHandler.readObject(this, "beanPregunta", beanPregunta);} 	return beanPregunta;}
	 
	public void setBeanPregunta(Pregunta beanPregunta) 
	{if (fieldHandler != null)	{this.beanPregunta = (Pregunta) fieldHandler.writeObject(this, "beanPregunta", this.beanPregunta, beanPregunta);  return;}	this.beanPregunta = beanPregunta;}
	
	@Transient
	public FieldHandler getFieldHandler() 									{return fieldHandler;}
	public void setFieldHandler(FieldHandler fieldHandler)					{this.fieldHandler = fieldHandler;}
	
	
	
	
}