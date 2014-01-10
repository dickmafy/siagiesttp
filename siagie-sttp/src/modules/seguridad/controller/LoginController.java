package modules.seguridad.controller;
import java.util.Calendar;

import org.apache.commons.beanutils.PropertyUtils;

import modules.admision.domain.Proceso;
import modules.seguridad.domain.MenuAcceso;
import modules.seguridad.domain.Usuario;
import modules.seguridad.domain.VariableAcceso;
import dataware.service.SeguridadService;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;
import com.belogick.factory.util.helper.DateHelper;
import com.belogick.factory.util.support.Encriptador;

public class LoginController extends GenericController 
{
	private SeguridadService	myService;	
	private int intento=0;
	
	@Override
	public void init() throws Exception
	{}
	
	public String login() throws Exception 
	{
		userName="Sistema";
		appName="Configuracion";
		moduleName="Login";
		String redirectTo 	= "login";
		Usuario usrLogin 	= (Usuario) getBeanSearch();
		
		VariableAcceso varLogin=myService.listarVariables();		
		String pasLogin = Encriptador.encryptBlowfish(usrLogin.getContrasena(), Constante.KEY);
		usrLogin.setContrasena(null);
		usrLogin=(Usuario)myService.obtenerUsuario(usrLogin.getUsuario());
		
		//No Existe
		if(usrLogin==null)
		{
			setBeanSearch(new Usuario());
			setMessageError(new Exception("El usuario ingresado no existe."));
			saveLog("Login Fallido - Usuario Inexistente");
		}
		else
		{
			userName=usrLogin.getUsuario();
			
			//Perfil
			if(usrLogin.getEstadoPerfil().longValue()==Constante.ROW_STATUS_DELETE.longValue() || 
					usrLogin.getEstadoPerfil().longValue()==Constante.ROW_STATUS_DISABLED.longValue())
			{
				setBeanSearch(new Usuario());
				setMessageError(new Exception("El perfil del usuario no se encuentra habilitado para su uso."));
				saveLog("Login Fallido - Perfil Deshabilitado/Eliminado");
			}
			
			//Eliminado
			else if(usrLogin.getEstado().longValue()==Constante.ROW_STATUS_DELETE.longValue())
			{
				setBeanSearch(new Usuario());
				setMessageError(new Exception("El usuario ingresado no existe."));
				saveLog("Login Fallido - Usuario Eliminado");
			}
			//Deshabilitado
			else if(usrLogin.getEstado().longValue()==Constante.ROW_STATUS_DISABLED.longValue())
			{
				setBeanSearch(new Usuario());
				setMessageError(new Exception("El usuario se encuentra temporalmente bloqueado."));
				saveLog("Login Fallido - Usuario Bloqueado");
			}
			//Bloqueado
			else if(usrLogin.getEstado().longValue()==Constante.ROW_STATUS_BLOCKED.longValue())
			{
			
				if(DateHelper.difDateMinute(DateHelper.getDate(), usrLogin.getModificacion())<=varLogin.getValorInteger(4L))
				{
					//Bloquear
					setBeanSearch(new Usuario());
					setMessageError(new Exception("El usuario se encuentra temporalmente bloqueado."));
					saveLog("Login Fallido - Usuario Bloqueado");
				}
				else
				{
					//Desbloquear
					setMessageError(new Exception("El usuario ha sido desbloqueado, intente acceder nuevamente."));
					usrLogin.setEstado(Constante.ROW_STATUS_ENABLED);
					usrLogin.setModificacion(DateHelper.getDate());
					getService().save(usrLogin);
					saveLog("Login en Proceso - Usuario Desbloqueado");					
				}			
			}
						
			//Habilitado
			else if(usrLogin.getEstado().longValue()==Constante.ROW_STATUS_ENABLED.longValue())
			{
				intento++;
				
				//Intentos?
				if(intento==varLogin.getValorInteger(3L))
				{
					//Bloquear
					usrLogin.setEstado(Constante.ROW_STATUS_BLOCKED);
					usrLogin.setModificacion(DateHelper.getDate());
					getService().save(usrLogin);
					
					intento=0;
					setBeanSearch(new Usuario());
					setMessageError(new Exception("El usuario ha superado los intentos de acceso y ha sido bloqueado."));
					saveLog("Login en Proceso - Usuario Bloqueado");
				}
				else
				{
					System.out.println("La clave es:"+pasLogin);
					//Clave Incorrecta
					if(!usrLogin.getContrasena().equals(pasLogin))
					{
						setBeanSearch(new Usuario());
						setMessageError(new Exception("La contrase�a ingresada es incorrecta."));
						saveLog("Login en Proceso - Contrase�a Incorrecta");
					}
					//Clave Correcta
					else
					{
						intento=0;
						saveLog("Login Exitoso");

						VariableAcceso 	varSesion = (VariableAcceso)getSpringBean("variableSesion");
						Usuario 		usrSesion = (Usuario)getSpringBean("usuarioSesion");
						MenuAcceso 		mnuSesion = (MenuAcceso)getSpringBean("menuSesion");
						MenuAcceso 		mnuLogin=myService.listarAccesos(usrLogin.getPerfil(),0L);						
						
						PropertyUtils.copyProperties(usrSesion, usrLogin);
						PropertyUtils.copyProperties(mnuSesion, mnuLogin);
						PropertyUtils.copyProperties(varSesion, varLogin);
						
						/*
						//Obteniendo Proceso Actual
						//=========================
						Proceso			proSesion = (Proceso)getSpringBean("procesoSesion");						
						Proceso			proLogin  = obtenerProceso(usrLogin.getInstitucion());
						
						if(proLogin==null)	
						{
							proLogin=new Proceso();
							proLogin.setId(-1L);
							proLogin.setAnnio(-1L);
							proLogin.setTipo(-1L);
							proLogin.setEstado(-1L);
						}	
						PropertyUtils.copyProperties(proSesion, proLogin);
						*/
						usrLogin.setAcceso(DateHelper.getDate());
						usrLogin=(Usuario)getService().save(usrLogin);
					
						usrSesion=null;
						varSesion=null;						
						mnuSesion=null;
						mnuLogin=null;
						
						OpcionController opcionController = (OpcionController)getSpringBean("opcionController");
						opcionController.init();
						opcionController=null;
						
						redirectTo = "start";
					}
				}
			}	
		}
		usrLogin=null;
		varLogin=null;
		pasLogin=null;
		return redirectTo;
	}

	public String logout()
	{
		getSession(false).setAttribute("usuarioSesion", null);
		getSession(false).setAttribute("menuSesion", null);
		getSession(false).setAttribute("variableSesion", null);
		getSession(false).invalidate();
		return "login";
	}	
	
	public Proceso obtenerProceso(Long institucion) throws Exception
	{
		Calendar ahoraCal = Calendar.getInstance();
		String annio=ahoraCal.get(Calendar.YEAR)+"";
		return (Proceso)myService.existeProceso(institucion, Long.valueOf(annio));
	}
	
	public SeguridadService getMyService() 						{return myService;}
	public void setMyService(SeguridadService myService) 		{this.myService = myService;}
}