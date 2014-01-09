/*    */ package com.belogick.factory.util.service.impl;
/*    */ 
/*    */ import com.belogick.factory.util.dao.ListDao;
/*    */ import com.belogick.factory.util.service.ListService;
/*    */ import com.belogick.factory.util.support.ServiceException;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ListServiceImpl extends CrudServiceImpl
/*    */   implements ListService
/*    */ {
/*    */   private ListDao listDao;
/*    */ 
/*    */   public void setListDAO(ListDao ListDao)
/*    */   {
/* 10 */     this.listDao = ListDao; } 
/* 11 */   public ListDao getDAO() { return this.listDao; }
/*    */ 
/*    */   public List listByObject(Object object) throws ServiceException
/*    */   {
/*    */     try {
/* 16 */       return getDAO().listByObject(object); } catch (Exception e) {
/* 17 */       throw new ServiceException(e);
/*    */     }
/*    */   }
/*    */   public List listByObjectStatus(Object object, String statusList) throws ServiceException {
/*    */     try { return getDAO().listByObjectStatus(object, statusList); } catch (Exception e) {
/* 22 */       throw new ServiceException(e);
/*    */     }
/*    */   }
/*    */   public List listByObjectEnabled(Object object) throws ServiceException {
/*    */     try { return getDAO().listByObjectEnabled(object); } catch (Exception e) {
/* 27 */       throw new ServiceException(e);
/*    */     }
/*    */   }
/*    */   public List listByObjectEnabledDisabled(Object object) throws ServiceException {
/*    */     try { return getDAO().listByObjectEnabledDisabled(object); } catch (Exception e) {
/* 32 */       throw new ServiceException(e);
/*    */     }
/*    */   }
/*    */   public List listByObject_OrderBy(Object object, String fieldList, boolean order) throws ServiceException {
/*    */     try { return getDAO().listByObject_OrderBy(object, fieldList, order); } catch (Exception e) {
/* 37 */       throw new ServiceException(e);
/*    */     }
/*    */   }
/*    */   public List listObject_OrderAscBy(Object object, String fieldList) throws ServiceException {
/*    */     try { return getDAO().listObject_OrderAscBy(object, fieldList); } catch (Exception e) {
/* 42 */       throw new ServiceException(e);
/*    */     }
/*    */   }
/*    */   public List listObject_OrderDscBy(Object object, String fieldList) throws ServiceException {
/*    */     try { return getDAO().listObject_OrderDscBy(object, fieldList); } catch (Exception e) {
/* 47 */       throw new ServiceException(e);
/*    */     }
/*    */   }
/*    */   public List listByNested(Object object, String fieldList) throws ServiceException {
/*    */     try { return getDAO().listByNested(object, fieldList); } catch (Exception e) {
/* 52 */       throw new ServiceException(e);
/*    */     }
/*    */   }
/*    */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.service.impl.ListServiceImpl
 * JD-Core Version:    0.6.2
 */