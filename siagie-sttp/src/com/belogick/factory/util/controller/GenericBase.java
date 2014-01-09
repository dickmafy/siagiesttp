/*     */ package com.belogick.factory.util.controller;
/*     */ 
/*     */ import com.belogick.factory.util.service.GenericService;
/*     */ import java.util.Map;
/*     */ import javax.faces.application.Application;
/*     */ import javax.faces.application.NavigationHandler;
/*     */ import javax.faces.context.ExternalContext;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.servlet.ServletContext;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.springframework.util.ClassUtils;
/*     */ import org.springframework.web.context.WebApplicationContext;
/*     */ import org.springframework.web.context.support.WebApplicationContextUtils;
/*     */ 
/*     */ public class GenericBase
/*     */ {
/*     */   public String userName;
/*     */   public String moduleName;
/*     */   public String appName;
/*  21 */   public boolean uppercase = true;
/*     */   private GenericService service;
/*     */ 
/*     */   public void init()
/*     */     throws Exception
/*     */   {
/*     */   }
/*     */ 
/*     */   public GenericService getService()
/*     */   {
/*  25 */     return this.service; } 
/*  26 */   public void setService(GenericService service) { this.service = service; }
/*     */ 
/*     */ 
/*     */   protected HttpSession getSession(boolean create)
/*     */   {
/*  33 */     return getRequest().getSession(create);
/*     */   }
/*     */ 
/*     */   protected HttpServletRequest getRequest()
/*     */   {
/*  39 */     return (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
/*     */   }
/*     */ 
/*     */   protected HttpServletResponse getResponse()
/*     */   {
/*  45 */     return (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
/*     */   }
/*     */ 
/*     */   protected String getRequestParameter(String name)
/*     */   {
/*  53 */     return (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
/*     */   }
/*     */ 
/*     */   public Object getSpringBean(String name)
/*     */   {
/*  61 */     return getWebContext().getBean(name);
/*     */   }
/*     */ 
/*     */   public Object getSpringBean(Class<? extends Object> name)
/*     */   {
/*  69 */     return getSpringBean(ClassUtils.getShortName(name));
/*     */   }
/*     */ 
/*     */   public Object getSpringBean(Object name)
/*     */   {
/*  77 */     return getSpringBean(name.getClass());
/*     */   }
/*     */ 
/*     */   public WebApplicationContext getWebContext()
/*     */   {
/*  84 */     return WebApplicationContextUtils.getWebApplicationContext(getServletContext());
/*     */   }
/*     */ 
/*     */   public ServletContext getServletContext()
/*     */   {
/*  91 */     return (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
/*     */   }
/*     */ 
/*     */   public void forward(String rule)
/*     */   {
/*  99 */     FacesContext fc = FacesContext.getCurrentInstance();
/* 100 */     fc.getApplication().getNavigationHandler().handleNavigation(fc, null, rule);
/*     */   }
/*     */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.controller.GenericBase
 * JD-Core Version:    0.6.2
 */