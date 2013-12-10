package modules.administracion.domain;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="administracion.m_feriado")
public class Feriado implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long institucion;
    private String nombre;
    private Date fecha_inicio;
    private Date fecha_fin;
    private String observacion;
    private Long estado;
    

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_feriado")
	public Long getId()                            		{return id;}
	public void setId(Long id)                     		{this.id = id;}
	
	@Column(name="nombre")
	public String getNombre()                      		{return nombre;}
	public void setNombre(String nombre)           		{this.nombre = nombre;}
	
	@Column(name="observacion")
	public String getObservacion()                 		{return observacion;}
	public void setObservacion(String observacion) 		{this.observacion = observacion;}
	
	@Column(name="pk_institucion")
	public Long getInstitucion()						{return institucion;}
	public void setInstitucion(Long institucion) 		{this.institucion = institucion;}
	
	@Column(name="fecha_inicio")
	public Date getFecha_inicio() 						{return fecha_inicio;}
	public void setFecha_inicio(Date fecha_inicio) 		{this.fecha_inicio = fecha_inicio;}

	@Column(name="fecha_fin")
	public Date getFecha_fin() 							{return fecha_fin;}
	public void setFecha_fin(Date fecha_fin) 			{this.fecha_fin = fecha_fin;	}
  
	@Column(name="estado")
	public Long getEstado()                        		{return estado;}
	public void setEstado(Long estado)             		{this.estado = estado;}

}