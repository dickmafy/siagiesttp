/*   */ package com.belogick.factory.util.service.impl;
/*   */ 
/*   */ import com.belogick.factory.util.dao.GenericDao;
/*   */ import com.belogick.factory.util.service.GenericService;
/*   */ 
/*   */ public class GenericServiceImpl extends GetServiceImpl
/*   */   implements GenericService
/*   */ {
/*   */   private GenericDao genericDao;
/*   */ 
/*   */   public void setGenericDAO(GenericDao GenericDao)
/*   */   {
/* 8 */     this.genericDao = GenericDao; } 
/* 9 */   public GenericDao getDAO() { return this.genericDao; }
/*   */ 
/*   */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.service.impl.GenericServiceImpl
 * JD-Core Version:    0.6.2
 */