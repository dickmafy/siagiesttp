package modules.mantenimiento.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="mantenimiento.m_colegio")
public class Colegio implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private Long tipo;
	private String direccion;
	private String telefono;
	private Long ubigeo;
	private Long estado;
	private String nombreTipo;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_colegio")
	public Long getId() 								{return id;}
	public void setId(Long id) 							{this.id = id;}
	
	@Column(name="nombre")
	public String getNombre() 							{return nombre;}
	public void setNombre(String nombre) 				{this.nombre = nombre;}
	
	@Column(name="direccion")
	public String getDireccion() 						{return direccion;}
	public void setDireccion(String direccion) 			{this.direccion = direccion;}
	
	@Column(name="tipo")
	public Long getTipo() 								{return tipo;}
	public void setTipo(Long tipo) 						{this.tipo = tipo;}
	
	
	@Column(name="telefono")
	public String getTelefono() 						{return telefono;}
	public void setTelefono(String telefono) 			{this.telefono = telefono;}
	
	@Column(name="ubigeo")
	public Long getUbigeo() 							{return ubigeo;}
	public void setUbigeo(Long ubigeo) 					{this.ubigeo = ubigeo;}
	
	@Column(name="estado")
	public Long getEstado() 							{return estado;}
	public void setEstado(Long estado) 					{this.estado = estado;}
	
	@Transient
	public String getNombreTipo() {
		
		if(tipo==1L) return "PARTICULAR";
		if(tipo==2L) return "ESTATAL";
		return "";
	}
	public void setNombreTipo(String nombreTipo) 		{this.nombreTipo = nombreTipo;}
	
}