/*   */ package com.belogick.factory.util.support;
/*   */ 
/*   */ public class DaoException extends Exception
/*   */ {
/*   */   private static final long serialVersionUID = -926827715095030219L;
/*   */ 
/*   */   public DaoException()
/*   */   {
/*   */   }
/*   */ 
/*   */   public DaoException(Exception e)
/*   */   {
/* 6 */     super(e); } 
/* 7 */   public DaoException(String message) { super(message); }
/*   */ 
/*   */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.support.DaoException
 * JD-Core Version:    0.6.2
 */