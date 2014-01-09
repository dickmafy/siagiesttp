/*   */ package com.belogick.factory.util.support;
/*   */ 
/*   */ public class ServiceException extends Exception
/*   */ {
/*   */   private static final long serialVersionUID = 209515474490940953L;
/*   */ 
/*   */   public ServiceException(String message, Exception e)
/*   */   {
/* 5 */     super(message, e); } 
/* 6 */   public ServiceException(Exception e) { super(e); }
/*   */ 
/*   */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.support.ServiceException
 * JD-Core Version:    0.6.2
 */