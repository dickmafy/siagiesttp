package modules.seguridad.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="seguridad.acceso")
public class Acceso implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long perfil;
	private Long menu;
	private Long permiso;
	
	@Id
	@Column(name="pk_perfil")
	public Long getPerfil() 				{return perfil;}
	public void setPerfil(Long perfil) 		{this.perfil = perfil;}

	@Column(name="pk_menu")
	public Long getMenu() 					{return menu;}
	public void setMenu(Long menu) 			{this.menu = menu;}

	@Column(name="permiso")
	public Long getPermiso() 				{return permiso;}
	public void setPermiso(Long permiso) 	{this.permiso = permiso;}	
}