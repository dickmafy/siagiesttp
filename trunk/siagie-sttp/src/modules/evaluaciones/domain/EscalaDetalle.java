package modules.evaluaciones.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="evaluaciones.m_escala_detalle")
public class EscalaDetalle implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long escala;
	private String nombre;
	private String letra;
	private Long valor;	
	private Long estado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_item")
	public Long getId()								 {return id;}
	public void setId(Long id) 						 {this.id = id;}
	
	@Column(name="pk_escala")
	public Long getEscala()							 {return escala;}
	public void setEscala(Long escala) 				 {this.escala = escala;}
	
	@Column(name="nombre")
	public String getNombre() 						 {return nombre;}
	public void setNombre(String nombre) 			 {this.nombre = nombre;}
	
	@Column(name="letra")
	public String getLetra() 						 {return letra;}
	public void setLetra(String letra) 				 {this.letra = letra;}
	
	@Column(name="valor")
	public Long getValor()							 {return valor;}
	public void setValor(Long valor)        		 {this.valor = valor;}
	
	@Column(name="estado")
	public Long getEstado() {return estado;}
	public void setEstado(Long estado)	    		 {this.estado = estado;}
	
	public static long getSerialversionuid() 		 {return serialVersionUID;}		
	
		
		
}