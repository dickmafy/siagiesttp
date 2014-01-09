/*    */ package com.belogick.factory.util.controller;
/*    */ 
/*    */ import com.belogick.factory.util.helper.DatabaseHelper;
/*    */ import javax.faces.application.FacesMessage;
/*    */ import javax.faces.context.FacesContext;
/*    */ 
/*    */ public class GenericMessage extends GenericBase
/*    */ {
/*    */   protected void setMessageError(String spc, String error)
/*    */   {
/* 18 */     FacesContext.getCurrentInstance().addMessage(spc, new FacesMessage(FacesMessage.SEVERITY_FATAL, error, error));
/*    */   }
/*    */ 
/*    */   protected void setMessageError(Exception e)
/*    */   {
/* 25 */     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getLocalizedMessage()));
/*    */   }
/*    */ 
/*    */   protected void setMessageError(String error)
/*    */   {
/* 32 */     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error));
/*    */   }
/*    */ 
/*    */   protected void setMessageSuccess(String msg)
/*    */   {
/* 39 */     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
/*    */   }
/*    */ 
/*    */   protected void setMessageSuccess(String spc, String msg)
/*    */   {
/* 47 */     FacesContext.getCurrentInstance().addMessage(spc, new FacesMessage(msg));
/*    */   }
/*    */ 
/*    */   public void setCatchError(Exception ex)
/*    */   {
/* 54 */     setMessageError(DatabaseHelper.getPostgresqlError(ex));
/*    */   }
/*    */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.controller.GenericMessage
 * JD-Core Version:    0.6.2
 */