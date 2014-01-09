/*     */ package com.belogick.factory.util.controller;
/*     */ 
/*     */ import com.belogick.factory.util.constant.Constante;
/*     */ import com.belogick.factory.util.domain.LogBase;
/*     */ import com.belogick.factory.util.helper.DateHelper;
/*     */ import com.belogick.factory.util.helper.NetworkHelper;
/*     */ import com.belogick.factory.util.service.GenericService;
/*     */ import com.belogick.factory.util.support.ServiceException;
/*     */ import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.faces.event.ActionEvent;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class GenericTransaction extends GenericValidation
/*     */ {
/*  18 */   protected static Logger log = Logger.getLogger(GenericTransaction.class);
/*     */   private Object bean;
/*     */   private Object beanSave;
/*     */   private Object beanDelete;
/*     */   private Object beanSearch;
/*     */   private Object beanSelected;
/*     */   private List beanList;
/*     */   private List beanFilterList;
/*     */   private int sizeBeanList;
/*     */   private int sizeBeanFilterList;
/*     */   public String page_main;
/*     */   public String page_new;
/*     */   public String page_update;
/*     */   public String page_search;
/*     */ 
/*     */   public String getProperties(String property)
/*     */   {
/*  25 */     String propertiesFile = (String)getSession(true).getAttribute("language");
/*  26 */     ResourceBundle rb = ResourceBundle.getBundle(propertiesFile);
/*  27 */     return rb.getString(property);
/*     */   }
/*     */ 
/*     */   public void saveLog(String operation)
/*     */     throws Exception
/*     */   {
/*  35 */     LogBase newLog = new LogBase();
/*  36 */     newLog.setUsuario(this.userName);
/*  37 */     newLog.setIp_local(NetworkHelper.getIpLocal());
/*  38 */     newLog.setIp_publica(NetworkHelper.getIpPublica());
/*  39 */     newLog.setHost(NetworkHelper.getHost());
/*  40 */     newLog.setFecha(DateHelper.getDate());
/*  41 */     newLog.setModulo(this.moduleName);
/*  42 */     newLog.setOperacion(operation);
/*  43 */     newLog.setSistema(this.appName);
/*  44 */     getService().save(newLog);
/*  45 */     newLog = null;
/*     */   }
/*     */ 
/*     */   public Object getBean()
/*     */   {
/*  69 */     return this.bean; } 
/*  70 */   public Object getBeanSave() { return this.beanSave; } 
/*  71 */   public Object getBeanSearch() { return this.beanSearch; } 
/*  72 */   public Object getBeanDelete() { return this.beanDelete; } 
/*  73 */   public Object getBeanSelected() { return this.beanSelected; } 
/*     */   public void setBean(Object bean) {
/*  75 */     this.bean = bean; } 
/*  76 */   public void setBeanSave(Object beanSave) { this.beanSave = beanSave; } 
/*  77 */   public void setBeanSearch(Object beanSearch) { this.beanSearch = beanSearch; } 
/*  78 */   public void setBeanDelete(Object beanDelete) { this.beanDelete = beanDelete; } 
/*  79 */   public void setBeanSelected(Object beanSelected) { this.beanSelected = beanSelected; } 
/*     */   public List getBeanList() {
/*  81 */     return this.beanList; } 
/*  82 */   public void setBeanList(List beanList) { this.beanList = beanList; } 
/*  83 */   public List getBeanFilterList() { return this.beanFilterList; } 
/*  84 */   public void setBeanFilterList(List beanFilterList) { this.beanFilterList = beanFilterList; } 
/*     */   public int getSizeBeanList() {
/*  86 */     return this.beanList.size(); } 
/*  87 */   public void setSizeBeanList(int sizeBeanList) { this.sizeBeanList = sizeBeanList; } 
/*     */   public int getSizeBeanFilterList() {
/*  89 */     return this.beanFilterList.size(); } 
/*  90 */   public void setSizeBeanFilterList(int sizeBeanFilterList) { this.sizeBeanFilterList = sizeBeanFilterList; }
/*     */ 
/*     */ 
/*     */   public void infoBegin(String transaction)
/*     */     throws Exception
/*     */   {
/* 113 */     log.info("======================================================");
/* 114 */     log.info(transaction + " - " + this.appName.toUpperCase() + " - " + this.moduleName.toUpperCase());
/* 115 */     log.info("======================================================");
/* 116 */     log.info("Date \t:" + DateHelper.getDate());
/* 117 */     log.info("User \t:" + this.userName);
/* 118 */     log.info("======================================================");
/*     */   }
/*     */ 
/*     */   public void infoError(String e) throws Exception {
/* 122 */     log.info("Transaction Not Completed : " + e);
/* 123 */     log.info("======================================================");
/*     */   }
/*     */ 
/*     */   public void infoSucess() throws Exception {
/* 127 */     log.info("Successfully Completed Transaction");
/* 128 */     log.info("======================================================");
/*     */   }
/*     */ 
/*     */   public void update(ActionEvent event)
/*     */     throws Exception
/*     */   {
/* 140 */     infoBegin("TRANSACTION UPDATE");
/*     */     try
/*     */     {
/* 143 */       setBeanSave(getService().save(getBeanSave()));
/* 144 */       setMessageSuccess("El registro ha sido actualizado satisfactoriamente.");
/* 145 */       saveLog("Registro Actualizado");
/* 146 */       infoSucess();
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 150 */       infoError(ex.getMessage());
/* 151 */       log.error(ex.getStackTrace());
/* 152 */       setCatchError(ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void delete(ActionEvent event)
/*     */     throws Exception
/*     */   {
/* 165 */     infoBegin("TRANSACTION DELETE");
/*     */     try
/*     */     {
/* 168 */       getService().delete(getBeanDelete());
/* 169 */       setMessageSuccess("El registro ha sido eliminado.");
/* 170 */       saveLog("Registro Eliminado");
/* 171 */       infoSucess();
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 175 */       infoError(ex.getMessage());
/* 176 */       log.error(ex.getStackTrace());
/* 177 */       setCatchError(ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void save(ActionEvent event)
/*     */     throws Exception
/*     */   {
/* 190 */     infoBegin("TRANSACTION NEW");
/*     */     try
/*     */     {
/* 193 */       setBeanSave(getService().save(getBeanSave()));
/* 194 */       setMessageSuccess("El registro ha sido guardado satisfactoriamente.");
/* 195 */       saveLog("Registro Creado");
/* 196 */       infoSucess();
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 200 */       infoError(ex.getMessage());
/* 201 */       log.error(ex.getStackTrace());
/* 202 */       setCatchError(ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void status(Object object, Long status)
/*     */     throws Exception
/*     */   {
/* 214 */     infoBegin("TRANSACTION STATUS");
/*     */     try
/*     */     {
/* 217 */       getService().updateStatus(object, status);
/* 218 */       infoSucess();
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 222 */       infoError(ex.getMessage());
/* 223 */       log.error(ex.getStackTrace());
/* 224 */       setCatchError(ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected Object newInstance(Object object)
/*     */     throws ServiceException
/*     */   {
			  Object tmpObject;
/* 238 */     Class clazz = object.getClass();
/*     */     try {
/* 240 */       tmpObject = Class.forName(clazz.getName()).newInstance();
/*     */     }
/*     */     catch (InstantiationException e)
/*     */     {
/*     */       
/* 241 */       throw new ServiceException(e); } catch (IllegalAccessException e) {
/* 242 */       throw new ServiceException(e); } catch (ClassNotFoundException e) {
/* 243 */       throw new ServiceException(e);
/*     */     }
/*     */     
/* 244 */     return tmpObject;
/*     */   }
/*     */ 
/*     */   protected void setEnabled(Object object)
/*     */     throws Exception
/*     */   {
/* 255 */     PropertyUtils.setProperty(object, "estado", Constante.ROW_STATUS_ENABLED);
/* 256 */     setBean(object);
/*     */   }
/*     */ 
/*     */   public void beforeNew()
/*     */     throws Exception
/*     */   {
/* 263 */     log.info("Transaction BeforeNew"); } 
/* 264 */   public void beforeSave() throws Exception { log.info("Transaction BeforeSave"); } 
/* 265 */   public void beforeUpdate() throws Exception { log.info("Transaction BeforeUpdate"); } 
/* 266 */   public void beforeDelete() throws Exception { log.info("Transaction BeforeDelete"); } 
/* 267 */   public void beforeRemove() throws Exception { log.info("Transaction BeforeRemove"); } 
/* 268 */   public void beforeLoad() throws Exception { log.info("Transaction BeforeLoad"); } 
/*     */   public void afterNew() throws Exception {
/* 270 */     log.info("Transaction AfterNew"); } 
/* 271 */   public void afterSave() throws Exception { log.info("Transaction AfterSave"); } 
/* 272 */   public void afterUpdate() throws Exception { log.info("Transaction AfterUpdate"); } 
/* 273 */   public void afterDelete() throws Exception { log.info("Transaction AfterDelete"); } 
/* 274 */   public void afterRemove() throws Exception { log.info("Transaction AfterRemove"); } 
/* 275 */   public void afterLoad() throws Exception { log.info("Transaction AfterLoad"); } 
/*     */   public void finishSave() throws Exception {
/* 277 */     log.info("Transaction FinishSave"); } 
/* 278 */   public void finishUpdate() throws Exception { log.info("Transaction FinishUpdate"); } 
/* 279 */   public void finishDelete() throws Exception { log.info("Transaction FinishDelete"); } 
/* 280 */   public void finishRemove() throws Exception { log.info("Transaction FinishRemove"); }
/*     */ 
/*     */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.controller.GenericTransaction
 * JD-Core Version:    0.6.2
 */