package modules.intranet.controller; 
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
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import resouces.ConnPg;
import resouces.Fecha;

import com.belogick.factory.util.controller.GenericController;

import dataware.service.HorarioService;
import modules.administracion.domain.MetaInstitucional;
import modules.administracion.domain.Personal;
import modules.admision.domain.Proceso;
import modules.horario.domain.Seccion;
import modules.horario.domain.SilaboCronograma;
import modules.horario.domain.SilaboUnidadCt;
import modules.intranet.domain.silabo_docente;
import modules.marco.domain.ReferenteEducativo;
import modules.seguridad.domain.Usuario;

public class DocenteSilaboList extends GenericController   
{
	private List<SelectItem>    profesionList;
	private List<SelectItem>    procesoList;
	private List<SelectItem>    docenteList;
	
	List<MetaInstitucional> metas;
	private Long annio,proceso,profesion,turno;
	private Long institucion,docente;
	private HorarioService	myService;
	
	private List<ReferenteEducativo> listarCT;
	
	private Long meta,seccion,unidad;
	
	private String docente_nombre;
	private Long docente_id;
	
	private String urlRpt; // Codigo Ericson Huamani
	private String nombreUsuario; // Codigo Ericson Huamani
	
	public void init(Long anio, Long proc) throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Horarios";
		moduleName="Meta Detalle ";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();
		docente=usr.getPertenencia();
		
		annio = anio;
		proceso = proc;
		
		docente_nombre = usr.getNombres();
		//docente_id = usr.getId();
		
		if(annio>0L)
		{metas=myService.listarMetaInstitucional(institucion,annio,-1L);}
		
		Personal obj=new Personal();
		obj.setInstitucion(institucion);
		obj.setPuesto(6L);
		docenteList=getListSelectItem(myService.listByObjectEnabled(obj), "id", "apepat,apemat,nombres"," ",true);
		obj=null;
		fillProcesos();
		
		defaultList();
		page_new="";
		page_main="DocenteSilaboList";
		page_update="";
		forward(page_main);
		
		setUrlRpt("/modulos/reportes/pdf/NOMINA.pdf"); // Codigo Ericson Huamaní
		nombreUsuario=usr.getNombres(); // Codigo Ericson Huamaní
	}
	
	public void init() throws Exception
	{init(-1L,-1L);}
	
	public void selectAnnio() throws Exception
	{
		metas=myService.listarMetaInstitucional(institucion,annio,-1L);
		fillProcesos();
		proceso=-1L;
		profesionList=new ArrayList<SelectItem>();
		profesion=-1L;
		turno=-1L;
		defaultList();
	}
		
	public void fillProcesos() throws Exception
	{
		procesoList=new ArrayList<SelectItem>();
		List<Integer> procesos=new ArrayList<Integer>();
		
		if(metas!=null)
		{
			for(int i=0; i<metas.size(); i++)
			{
				if(metas.get(i).getAnnio().longValue()==annio)
				{procesos.add(Integer.parseInt(metas.get(i).getProceso().toString()));}
			}
		}
		HashSet<Integer> hashSet = new HashSet<Integer>(procesos);
		procesos.clear();
		procesos.addAll(hashSet);
		
		for(int j=0; j<procesos.size(); j++)
		{
			if(procesos.get(j)==1)	{procesoList.add(new SelectItem(1,"Enero"));}
			if(procesos.get(j)==2)	{procesoList.add(new SelectItem(2,"Febrero"));}
			if(procesos.get(j)==3)	{procesoList.add(new SelectItem(3,"Marzo"));}
			if(procesos.get(j)==4)	{procesoList.add(new SelectItem(4,"Abril"));}
			if(procesos.get(j)==5)	{procesoList.add(new SelectItem(5,"Mayo"));}
			if(procesos.get(j)==6)	{procesoList.add(new SelectItem(6,"Junio"));}
			if(procesos.get(j)==7)	{procesoList.add(new SelectItem(7,"Julio"));}
			if(procesos.get(j)==8)	{procesoList.add(new SelectItem(8,"Agosto"));}
			if(procesos.get(j)==9)	{procesoList.add(new SelectItem(9,"Septiembre"));}
			if(procesos.get(j)==10)	{procesoList.add(new SelectItem(10,"Octubre"));}
			if(procesos.get(j)==11)	{procesoList.add(new SelectItem(11,"Noviembre"));}
			if(procesos.get(j)==12)	{procesoList.add(new SelectItem(12,"Diciembre"));}
		}
		procesos=null;
		hashSet=null;
	}
	
	@Override
	public void defaultList() throws Exception
	{
		if(institucion.longValue()>0L && annio.longValue()>0L && proceso.longValue()>0L)
		{setBeanList(myService.listarSeccionesDocente(institucion, annio, proceso,docente));}
		else
		{setBeanList(new ArrayList<Seccion>());}
	}
	
	public void goCrearFechas() throws Exception
	{
//		SilaboCronograma silaboCronograma = obtenerSilaboCronograma();    	
//		silaboCronograma = (SilaboCronograma) myService.findByObject(silaboCronograma);
		
		DocenteSilaboFecha go = (DocenteSilaboFecha)getSpringBean("docenteSilaboFecha");
		//go.init(annio, proceso, ((MetaInstitucional)getBeanSelected()).getProfesion(), ((MetaInstitucional)getBeanSelected()).getTurno());
		go.init((Seccion)getBeanSelected());
	}
	
	public void goNotas()throws Exception{
		
		
		
		SilaboCronograma silaboCronograma = obtenerSilaboCronograma();    	
		silaboCronograma = (SilaboCronograma) myService.findByObject(silaboCronograma);
		
		DocenteSilaboNota go = (DocenteSilaboNota)getSpringBean("docenteSilaboNota");
		Proceso proceso = new Proceso();
		proceso.setAnnio(annio);
		proceso.setProceso(this.proceso);
		proceso = (Proceso) myService.findByObject(proceso);
		go.init((Seccion)getBeanSelected(),proceso,silaboCronograma);
	}
	
	
	public void goAsistencia()throws Exception{
		SilaboCronograma silaboCronograma = obtenerSilaboCronograma();    	
		silaboCronograma = (SilaboCronograma) myService.findByObject(silaboCronograma);
    	
		DocenteSilaboAsistenciaListFecha  go = (DocenteSilaboAsistenciaListFecha)getSpringBean("docenteSilaboAsistenciaListFecha");
		Proceso proceso = new Proceso();
		proceso.setAnnio(annio);
		proceso.setProceso(this.proceso);
		proceso = (Proceso) myService.findByObject(proceso);
		go.init((Seccion)getBeanSelected(),proceso,silaboCronograma);
	}


	private SilaboCronograma obtenerSilaboCronograma() {
		Seccion bean = (Seccion)getBeanSelected();
		SilaboCronograma silaboCronograma =new SilaboCronograma();
		
		silaboCronograma.setPk_meta(bean.getMeta());
    	silaboCronograma.setContenido("-");
    	silaboCronograma.setPk_unidad(bean.getValorUnidad());
    	silaboCronograma.setPk_seccion(bean.getId());
    	silaboCronograma.setPk_docente(bean.getDocente());
    	silaboCronograma.setEstado(bean.getEstadoSilabo());
		return silaboCronograma;
	}
	
	public void goCt()throws Exception{
		
//		seccion=pseccion.getId();
//		docente=pseccion.getDocente();
//		meta=pseccion.getMeta();
//		unidad=pseccion.getValorUnidad();
//		
		//en la seccion esta el silabocronograma id
    	SilaboCronograma silaboCronograma =new SilaboCronograma();
		silaboCronograma.setPk_meta(meta);
    	silaboCronograma.setContenido("-");
    	silaboCronograma.setPk_unidad(unidad);
    	silaboCronograma.setPk_seccion(seccion);
    	silaboCronograma.setPk_docente(docente);
    	silaboCronograma.setEstado(1L);
    	
    	SilaboCronograma obtenerSilaboCronograma = (SilaboCronograma) myService.findByObject(silaboCronograma);
    	
    	
		DocenteSilaboCT go = (DocenteSilaboCT)getSpringBean("docenteSilaboCT");
		Proceso proceso = new Proceso();
		proceso.setAnnio(annio);
		proceso.setProceso(this.proceso);
		proceso = (Proceso) myService.findByObject(proceso);
		go.init((Seccion)getBeanSelected(),proceso,obtenerSilaboCronograma);
	}
	
	public HorarioService getMyService() 							{return myService;}
	public void setMyService(HorarioService myService) 				{this.myService = myService;}

	public Long getAnnio() 											{return annio;}
	public void setAnnio(Long annio) 								{this.annio = annio;}

	public Long getProceso() 										{return proceso;}
	public void setProceso(Long proceso) 							{this.proceso = proceso;}

	public Long getProfesion() 										{return profesion;}
	public void setProfesion(Long profesion) 						{this.profesion = profesion;}

	public Long getTurno() 											{return turno;}
	public void setTurno(Long turno) 								{this.turno = turno;}
	
	public List<SelectItem> getProfesionList() 						{return profesionList;}
	public void setProfesionList(List<SelectItem> profesionList) 	{this.profesionList = profesionList;}
	
	public List<SelectItem> getProcesoList() 						{return procesoList;}
	public void setProcesoList(List<SelectItem> procesoList) 		{this.procesoList = procesoList;}

	public List<SelectItem> getDocenteList() 						{return docenteList;}
	public void setDocenteList(List<SelectItem> docenteList) 		{this.docenteList = docenteList;}


	public List<ReferenteEducativo> getListarCT() {
		return listarCT;
	}


	public void setListarCT(List<ReferenteEducativo> listarCT) {
		this.listarCT = listarCT;
	}


	public String getDocente_nombre() {
		return docente_nombre;
	}


	public void setDocente_nombre(String docente_nombre) {
		this.docente_nombre = docente_nombre;
	}


	public Long getDocente_id() {
		return docente_id;
	}


	public void setDocente_id(Long docente_id) {
		this.docente_id = docente_id;
	}

	// Inicio Codigo Ericson Huamaní 19-12-2013 11:00
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
					parametro.put("PK_PERSONAL",docente);
					parametro.put("USUARIO", nombreUsuario);
					parametro.put("RUTA_IMAGEN",rutaAplicacion+"/recursos/imagenes/sicad_1_rpt.jpg");
					
					File fp = new File(rutaAplicacion
							+ "/modulos/reportes/jasper/rpt_cursos_docente.jasper");
					InputStream reportSt = new BufferedInputStream(
							new FileInputStream(fp));
					print = JasperFillManager.fillReport(reportSt, parametro,
							ConnPg.getConexion());
					OutputStream output = new FileOutputStream(new File(
							rutaAplicacion + "/modulos/reportes/pdf/"
									+ nombreArchivoPdf));
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

		public String getUrlRpt() {
			return urlRpt;
		}

		public void setUrlRpt(String urlRpt) {
			this.urlRpt = urlRpt;
		}

} 
