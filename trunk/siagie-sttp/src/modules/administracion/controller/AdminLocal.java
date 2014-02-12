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
import resouces.ConnPg;
import resouces.Fecha;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;

import dataware.service.SeguridadService;
import modules.administracion.domain.Institucion;
import modules.administracion.domain.Local;
import modules.administracion.domain.Personal;
import modules.administracion.domain.Solicitud;
import modules.mantenimiento.domain.Resolucion;
import modules.mantenimiento.domain.Ubigeo;
import modules.seguridad.domain.Usuario;

public class AdminLocal extends GenericController {
	private Long institucion;
	private String nombreInstitucion;
	private List<SelectItem> personalList;
	private List<SelectItem> institucionList;
	private List<SelectItem> resolucionList;
	private List<SelectItem> solicitudList;

	private List<SelectItem> depList;
	private List<SelectItem> proList;
	private List<SelectItem> disList;
	private Long departamento, provincia;
	private SeguridadService myService;
	private List<Institucion> instituciones = null;

	private String urlRpt; // Codigo Ericson Huamani
	private String nombreUsuario; // Codigo Ericson Huamani

	@SuppressWarnings("unchecked")
	public void initBase(Long codigo, String nombre) throws Exception {
		Usuario usr = (Usuario) getSpringBean("usuarioSesion");
		appName = "Local";
		moduleName = "Instituciones - Local ";
		userName = usr.getUsuario();
		institucion = codigo;
		nombreInstitucion = nombre;
		defaultList();

		instituciones = myService.listByObjectEnabledDisabled(new Institucion());
		institucionList = getListSelectItem(instituciones, "id", "nombre", true);

		Personal obj = new Personal();
		obj.setInstitucion(institucion);
		personalList = getListSelectItem(obj, "id", "nombres,apepat,apemat",
				" ", true);
		obj = null;

		page_new = "adm_loc_new";
		page_main = "adm_loc_lst";
		page_update = "adm_loc_upd";
		forward(page_main);

		setUrlRpt("/modulos/reportes/pdf/NOMINA.pdf"); // Codigo Ericson Huamaní
		nombreUsuario = usr.getNombres(); // Codigo Ericson Huamaní
	}

	@Override
	public void init() throws Exception {
		initBase(-1L, "");
	}

	public void init(Long codigo, String nombre) throws Exception {
		initBase(codigo, nombre);
	}

	public void setNombreInstitucion() {
		if (instituciones != null) {
			for (int i = 0; i < instituciones.size(); i++) {
				if (instituciones.get(i).getId().longValue() == institucion
						.longValue()) {
					nombreInstitucion = instituciones.get(i)
							.getNombreCompleto();
				}
			}
		}
	}

	@Override
	public void afterNew() throws Exception {
		Personal obj = new Personal();
		obj.setInstitucion(institucion);
		personalList = getListSelectItem(obj, "id", "nombres,apepat,apemat"," ", true);
		obj = null;

		Local bean = new Local();
		bean.setEstado(Constante.ROW_STATUS_ENABLED);
		bean.setInstitucion(institucion);
		bean.setPrincipal(false);
		setBean(bean);
		bean = null;

		resolucionList = getListSelectItem(
				myService.listByObjectEnabled(new Resolucion()), "id",
				"nombreSiglas,nombre", " ", false);

		Solicitud sol = new Solicitud();
		sol.setInstitucion(institucion);
		sol.setTipo(9L);
		sol.setEstado(3L);
		solicitudList = getListSelectItem(myService.listByObject(sol), "id",
				"id,fecha,nombreTipo", " ", true);
		sol = null;

		fillDep();
	}

	@Override
	public void afterLoad() throws Exception {
		Local bean = (Local) getBeanSelected();
		depList = getListSelectItem(myService.listarUbigeos(0L, 0L),
				"departamento", "descripcion", true);
		if (bean.getUbigeo() != null) {
			Ubigeo ubi = (Ubigeo) myService.findById(Ubigeo.class,
					bean.getUbigeo());
			departamento = ubi.getDepartamento();
			provincia = ubi.getProvincia();
			depList = getListSelectItem(myService.listarUbigeos(0L, 0L),
					"departamento", "descripcion", true);
			proList = getListSelectItem(
					myService.listarUbigeos(departamento, 0L), "provincia",
					"descripcion", true);
			disList = getListSelectItem(
					myService.listarUbigeos(departamento, provincia), "id",
					"descripcion", true);
		} else {
			departamento = -1L;
			provincia = -1L;
		}

		resolucionList = getListSelectItem(
				myService.listByObjectEnabled(new Resolucion()), "id",
				"nombreSiglas,nombre", " ", false);

		Solicitud sol = new Solicitud();
		sol.setInstitucion(institucion);
		sol.setTipo(9L);
		sol.setEstado(3L);
		solicitudList = getListSelectItem(myService.listByObject(sol), "id",
				"id,fecha,nombreTipo", " ", true);
		sol = null;
		bean = null;
	}

	@Override
	public void defaultList() throws Exception {
		Local bean = new Local();
		bean.setInstitucion(institucion);
		setBeanList(getService().listByObjectEnabledDisabled(bean));
		bean = null;
		setNombreInstitucion();
	}

	@Override
	public boolean validation() throws Exception {
		boolean success = true;
		Local object = (Local) getBean();

		if (!validateEmpty(object.getNombre())) {
			setMessageError("Debe ingresar el nombre del Local.");
			success = false;
		} else if (!validateSelect(provincia)) {
			setMessageError("Debe seleccionar la Provincia del Local.");
			success = false;
		} else if (!validateSelect(departamento)) {
			setMessageError("Debe seleccionar el Departamento del Local.");
			success = false;
		} else if (!validateSelect(object.getEncargado())) {
			setMessageError("Debe seleccionar el Encargado del Local.");
			success = false;
		} else if (!validateSelect(object.getUbigeo())) {
			setMessageError("Debe seleccionar el Distrito del Local.");
			success = false;
		} else if (!validateEmpty(object.getDireccion())) {
			setMessageError("Debe ingresar la dirección del Local.");
			success = false;
		} else if (!validateEmpty(object.getTelefonos())) {
			setMessageError("Debe ingresar los números telefonicos asociados al Local.");
			success = false;
		} else if (!validateSelect(object.getResolucion())) {
			setMessageError("Debe seleccionar la Resolución de ejecución");
			success = false;
		}
		object = null;
		return success;
	}

	public void fillDep() throws Exception {
		depList = getListSelectItem(myService.listarUbigeos(0L, 0L),
				"departamento", "descripcion", true);
		proList = new ArrayList<SelectItem>();
		disList = new ArrayList<SelectItem>();
		Local bean = (Local) getBean();
		bean.setUbigeo(-1L);
		setBean(bean);
		departamento = -1L;
		provincia = -1L;
	}

	public void fillPro() throws Exception {
		proList = getListSelectItem(myService.listarUbigeos(departamento, 0L),
				"provincia", "descripcion", true);
		disList = new ArrayList<SelectItem>();

		Local bean = (Local) getBean();
		bean.setUbigeo(-1L);
		setBean(bean);
		provincia = -1L;
	}

	public void fillDis() throws Exception {
		disList = getListSelectItem(
				myService.listarUbigeos(departamento, provincia), "id",
				"descripcion", true);
		Local bean = (Local) getBean();
		bean.setUbigeo(-1L);
		setBean(bean);
	}

	public List<SelectItem> getDepList() {
		return depList;
	}

	public void setDepList(List<SelectItem> depList) {
		this.depList = depList;
	}

	public List<SelectItem> getProList() {
		return proList;
	}

	public void setProList(List<SelectItem> proList) {
		this.proList = proList;
	}

	public List<SelectItem> getDisList() {
		return disList;
	}

	public void setDisList(List<SelectItem> disList) {
		this.disList = disList;
	}

	public Long getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Long departamento) {
		this.departamento = departamento;
	}

	public Long getProvincia() {
		return provincia;
	}

	public void setProvincia(Long provincia) {
		this.provincia = provincia;
	}

	public SeguridadService getMyService() {
		return myService;
	}

	public void setMyService(SeguridadService myService) {
		this.myService = myService;
	}

	public String getNombreInstitucion() {
		return nombreInstitucion;
	}

	public void setNombreInstitucion(String nombreInstitucion) {
		this.nombreInstitucion = nombreInstitucion;
	}

	public List<SelectItem> getPersonalList() {
		return personalList;
	}

	public void setPersonalList(List<SelectItem> personalList) {
		this.personalList = personalList;
	}

	public List<SelectItem> getInstitucionList() {
		return institucionList;
	}

	public void setInstitucionList(List<SelectItem> institucionList) {
		this.institucionList = institucionList;
	}

	public Long getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Long institucion) {
		this.institucion = institucion;
	}

	public List<SelectItem> getResolucionList() {
		return resolucionList;
	}

	public void setResolucionList(List<SelectItem> resolucionList) {
		this.resolucionList = resolucionList;
	}

	public List<SelectItem> getSolicitudList() {
		return solicitudList;
	}

	public void setSolicitudList(List<SelectItem> solicitudList) {
		this.solicitudList = solicitudList;
	}

	public String getUrlRpt() {
		return urlRpt;
	}

	public void setUrlRpt(String urlRpt) {
		this.urlRpt = urlRpt;
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
			parametro.put("PK_INSTITUTO", institucion);
			parametro.put("NOMBRE_INSTITUTO", nombreInstitucion);
			parametro.put("USUARIO", nombreUsuario);
			parametro.put("RUTA_IMAGEN", rutaAplicacion
					+ "/recursos/imagenes/sicad_1_rpt.jpg");

			File fp = new File(rutaAplicacion
					+ "/modulos/reportes/jasper/rpt_locales.jasper");
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

	// Fin codigo Ericson Huamaní
}
