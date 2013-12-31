package modules.marco.domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="marco.m_referente_educativo")
public class ReferenteEducativo implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long profesion;
	private Long tipo;
	private Long nivelA;
	private Long nivelB;
	private Long nivelC;
	private Long referente;
	private String titulo;
	private String descripcion;
	private Long estado;
	
	private boolean checkItem;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_referente")
	public Long getId() 							{return id;}
	public void setId(Long id) 						{this.id = id;}
	
	@Column(name="pk_profesion")	
	public Long getProfesion() 						{return profesion;}
	public void setProfesion(Long profesion) 		{this.profesion = profesion;}
	
	@Column(name="tipo")
	public Long getTipo() 							{return tipo;}
	public void setTipo(Long tipo) 					{this.tipo = tipo;}
	
	@Column(name="nivel_a")
	public Long getNivelA() 						{return nivelA;}
	public void setNivelA(Long nivelA) 				{this.nivelA = nivelA;}
	
	@Column(name="nivel_b")
	public Long getNivelB() 						{return nivelB;}
	public void setNivelB(Long nivelB) 				{this.nivelB = nivelB;}
	
	@Column(name="nivel_c")
	public Long getNivelC() 						{return nivelC;}
	public void setNivelC(Long nivelC) 				{this.nivelC = nivelC;}
	
	@Column(name="referente")
	public Long getReferente() 						{return referente;}
	public void setReferente(Long referente) 		{this.referente = referente;}
	
	@Column(name="titulo")
	public String getTitulo() 						{return titulo;}
	public void setTitulo(String titulo) 			{this.titulo = titulo;}
	
	@Column(name="descripcion")
	public String getDescripcion() 					{return descripcion;}
	public void setDescripcion(String descripcion) 	{this.descripcion = descripcion;}
	
	@Column(name="estado")
	public Long getEstado() 						{return estado;}
	public void setEstado(Long estado) 				{this.estado = estado;}
	
	@Transient
	public boolean isCheckItem() 					{return checkItem;}
	public void setCheckItem(boolean checkItem) 	{this.checkItem = checkItem;}
	
	@Transient
	public boolean getCheck() 						{return checkItem;}
	public void setCheck(boolean checkItem) 		{this.checkItem = checkItem;}
	
	
}