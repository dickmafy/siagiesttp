/*    */ package com.belogick.factory.util.service.impl;
/*    */ 
/*    */ import com.belogick.factory.util.dao.GetDao;
/*    */ import com.belogick.factory.util.service.GetService;
/*    */ import com.belogick.factory.util.support.ServiceException;
/*    */ 
/*    */ public class GetServiceImpl extends ListServiceImpl
/*    */   implements GetService
/*    */ {
/*    */   private GetDao getDao;
/*    */ 
/*    */   public void setGetDAO(GetDao GetDao)
/*    */   {
/*  9 */     this.getDao = GetDao; } 
/* 10 */   public GetDao getDAO() { return this.getDao; }
/*    */ 
/*    */   public Object findByObject(Object object) throws ServiceException
/*    */   {
/*    */     try
/*    */     {
/* 16 */       return getDAO().findByObject(object); } catch (Exception e) {
/* 17 */       throw new ServiceException(e);
/*    */     }
/*    */   }
/*    */   public Object findById(Object object) throws ServiceException {
/*    */     try { return getDAO().findById(object); } catch (Exception e) {
/* 22 */       throw new ServiceException(e);
/*    */     }
/*    */   }
/*    */   public Object findById(Class clazz, Long id) throws ServiceException {
/*    */     try { return getDAO().findById(clazz, id); } catch (Exception e) {
/* 27 */       throw new ServiceException(e);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void deleteByField(Object object, String field, String value) throws ServiceException {
/*    */     try { getDAO().deleteByField(object, field, value); } catch (Exception e) {
/* 33 */       throw new ServiceException(e);
/*    */     }
/*    */   }
/*    */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.service.impl.GetServiceImpl
 * JD-Core Version:    0.6.2
 */