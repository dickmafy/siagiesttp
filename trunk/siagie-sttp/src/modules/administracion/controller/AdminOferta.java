package modules.administracion.controller; 
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import resouces.ConnPg;
import resouces.Fecha;
import modules.administracion.domain.Institucion;
import modules.administracion.domain.Oferta;
import modules.administracion.domain.Solicitud;
import modules.mantenimiento.domain.Resolucion;
import modules.marco.domain.Profesion;
import modules.seguridad.domain.Usuario;

import com.belogick.factory.util.controller.GenericController;
import com.belogick.factory.util.helper.DateHelper;

import dataware.service.SeguridadService;

public class AdminOferta extends GenericController   
{
	private StreamedContent file;
	private Long institucion, formacion;
	private String nombreInstitucion;
	
	private List<SelectItem>    profesionList;
	private List<SelectItem>    institucionList;
	private List<SelectItem>    resolucionList;
	private List<SelectItem>    solicitudList;
	
	private SeguridadService	myService;	
	private List<Institucion> instituciones=null;
	
	private String urlRpt; // Codigo Ericson Huamani
	private String nombreUsuario; // Codigo Ericson Huamani
	
	public void initBase(Long codigo, String nombre, Long forma) throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Local";
		moduleName="Instituciones - Oferta Educativa ";
		userName=usr.getUsuario();
		institucion=codigo;
		nombreInstitucion=nombre;
		formacion=forma;
		defaultList();
		
		instituciones=myService.listByObjectEnabledDisabled(new Institucion());
		institucionList=getListSelectItem(instituciones, "id", "nombre", true);
		
		page_new="adm_ofer_new";
		page_main="adm_ofer_lst";
		page_update="adm_ofer_upd";
		forward(page_main);
		
		setUrlRpt("/modulos/reportes/pdf/NOMINA.pdf"); // Codigo Ericson Huaman�
		nombreUsuario = usr.getNombres(); // Codigo Ericson Huaman�
	}
	
	@Override
	public void init() throws Exception 
	{initBase(-1L,"", -1L);}
	
	public void init(Long codigo, String nombre, Long forma) throws Exception
	{initBase(codigo,nombre,forma);}
	
	
	@Override
	public void afterNew() throws Exception
	{
		Oferta bean=new Oferta();
		bean.setInstitucion(institucion);
		setBean(bean);
		bean=null;
		
		Profesion obj=new Profesion();
		obj.setFormacion(formacion);
		
		//funcion de eliminacion de duplicados
		profesionList=getProfesion(obj);
		obj=null;
		
		resolucionList=getListSelectItem(myService.listByObjectEnabled(new Resolucion()), "id","nombreSiglas,nombre"," ",false);
	
		Solicitud sol=new Solicitud();
		sol.setInstitucion(institucion);
		sol.setTipo(4L);
		sol.setEstado(3L);
		solicitudList=getListSelectItem(myService.listByObject(sol),"id","id,fecha,nombreTipo"," ",true);
		sol=null;
	}
	
	@Override
	public void afterLoad() throws Exception
	{
		Profesion obj=new Profesion();
		obj.setFormacion(formacion);
		
		//funcion de eliminacion de duplicados
		profesionList=getProfesion(obj);
		obj=null;
		
		resolucionList=getListSelectItem(myService.listByObjectEnabled(new Resolucion()), "id","nombreSiglas,nombre"," ",false);
		
		Solicitud sol=new Solicitud();
		sol.setInstitucion(institucion);
		sol.setTipo(4L);
		sol.setEstado(3L);
		solicitudList=getListSelectItem(myService.listByObject(sol),"id","id,fecha,nombreTipo"," ",true);
		sol=null;
	}
		
	@Override
	public void defaultList() throws Exception
	{
		setBeanList(null);
		if(institucion.longValue()>0L)
		{
			setBeanList(myService.listarOferta(institucion, DateHelper.getDate(),0L));
			setNombreInstitucion();
		}
	}

	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Oferta object = (Oferta)getBean();
		if(!validateSelect(object.getProfesion()))
		{
			setMessageError("Debe seleccionar una Profesi�n");			
			success = false;
		}
		else if(!validateEmpty(object.getVigenciaInicio()))
		{
			setMessageError("Debe ingresar la Fecha de Inicio de la Vigencia de la Oferta Educativa");			
			success = false;
		}
		else if(!validateEmpty(object.getVigenciaFin()))
		{
			setMessageError("Debe ingresar la Fecha de Fin de la Vigencia de la Oferta Educativa");			
			success = false;
		}
		else if(!validateSelect(object.getResolucion()))
		{
			setMessageError("Debe seleccionar la Resoluci�n de ejecuci�n");			
			success = false;
		}
		object=null;
		return success;
	}
	
	public void setNombreInstitucion()
	{
		if(instituciones!=null)
		{
			for(int i=0; i<instituciones.size(); i++)
			{
				if(instituciones.get(i).getId().longValue()==institucion.longValue())
				{
					nombreInstitucion=instituciones.get(i).getNombreCompleto();
					formacion=instituciones.get(i).getFormacion();
				}
			}
		}
	}
	
	public StreamedContent getFile() 							
	{
		Oferta object = (Oferta)getBeanSelected();
		try 
		{
	        InputStream is = new BufferedInputStream(new FileInputStream(getServletContext().getRealPath("/recursos/documentos/resolucion/"+object.getResolucion()+".pdf")));
	        return new DefaultStreamedContent(is, "application/pdf", object.getId()+".pdf");
	    } 
		catch (FileNotFoundException e) 
	    {return null;}
	}
	
	public SeguridadService getMyService() 								{return myService;}	
	public void setMyService(SeguridadService myService)				{this.myService = myService;}
	
	public String getNombreInstitucion() 								{return nombreInstitucion;}
	public void setNombreInstitucion(String nombreInstitucion) 			{this.nombreInstitucion = nombreInstitucion;}

	public List<SelectItem> getProfesionList() 							{return profesionList;}
	public void setProfesionList(List<SelectItem> profesionList) 		{this.profesionList = profesionList;}

	public List<SelectItem> getInstitucionList() 						{return institucionList;	}
	public void setInstitucionList(List<SelectItem> institucionList)	{this.institucionList = institucionList;	}
	
	public Long getInstitucion() 										{return institucion;}
	public void setInstitucion(Long institucion) 						{this.institucion = institucion;}

	public List<SelectItem> getResolucionList() 						{return resolucionList;}
	public void setResolucionList(List<SelectItem> resolucionList) 		{this.resolucionList = resolucionList;}

	public List<SelectItem> getSolicitudList() 							{return solicitudList;}
	public void setSolicitudList(List<SelectItem> solicitudList) 		{this.solicitudList = solicitudList;}

	private List<SelectItem> getProfesion(Profesion obj){
		
		List<SelectItem> list = getListSelectItem(obj, "id", "nombre", true);
		List<SelectItem> list2 = new ArrayList<SelectItem>();
		for (int i = 0; i < list.size(); i+=2) {
			list2.add(list.get(i));
		}
		return list2;
	} 
	
	public String getUrlRpt() {
		return urlRpt;
	}

	public void setUrlRpt(String urlRpt) {
		this.urlRpt = urlRpt;
	}
	
	// Inicio Codigo Ericson Huaman� 19-12-2013 11:00
		@SuppressWarnings("unchecked")
		public void generarReporte(ActionEvent evt) {
			try {
				String rutaAplicacion = FacesContext.getCurrentInstance()
						.getExternalContext().getRealPath("/");
				String nombreArchivoPdf = new Fecha().getFecha(new Date(),
						Fecha.PATTERN_DDMMYYYYHHMMS, Fecha.LOCALE_ES) + ".pdf";
				JasperPrint print = null;

				@SuppressWarnings("rawtypes")
				Map parametro = new HashMap();
				parametro.put("PK_INSTITUTO", institucion);
				parametro.put("NOMBRE_INSTITUTO", nombreInstitucion);
				parametro.put("USUARIO", nombreUsuario);
				parametro.put("RUTA_IMAGEN", rutaAplicacion
						+ "/recursos/imagenes/sicad_1_rpt.jpg");

				File fp = new File(rutaAplicacion
						+ "/modulos/reportes/jasper/rpt_oferta_educativa.jasper");
				InputStream reportSt = new BufferedInputStream(new FileInputStream(
						fp));
				print = JasperFillManager.fillReport(reportSt, parametro,
						ConnPg.getConexion());
				OutputStream output = new FileOutputStream(new File(rutaAplicacion
						+ "/modulos/reportes/pdf/" + nombreArchivoPdf));
				JasperExportManager.exportReportToPdfStream(print, output);
				urlRpt = "/modulos/reportes/pdf/" + nombreArchivoPdf;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Fin codigo Ericson Huaman�
} 
