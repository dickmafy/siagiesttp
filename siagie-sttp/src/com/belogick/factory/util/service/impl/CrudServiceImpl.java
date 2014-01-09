/*    */ package com.belogick.factory.util.service.impl;
/*    */ 
/*    */ import com.belogick.factory.util.dao.CrudDao;
/*    */ import com.belogick.factory.util.service.CrudService;
/*    */ import com.belogick.factory.util.support.ServiceException;
/*    */ import java.util.Collection;
/*    */ 
/*    */ public class CrudServiceImpl extends NativeServiceImpl
/*    */   implements CrudService
/*    */ {
/*    */   private CrudDao crudDao;
/*    */ 
/*    */   public void setCrudDAO(CrudDao CrudDao)
/*    */   {
/* 10 */     this.crudDao = CrudDao; } 
/* 11 */   public CrudDao getDAO() { return this.crudDao; }
/*    */ 
/*    */   public Object save(Object object) throws ServiceException
/*    */   {
/*    */     try {
/* 16 */       return getDAO().save(object); } catch (Exception e) {
/* 17 */       throw new ServiceException(e);
/*    */     }
/*    */   }
/*    */   public void saveCollection(Collection collection) throws ServiceException {
/*    */     try { getDAO().saveCollection(collection); } catch (Exception e) {
/* 22 */       throw new ServiceException(e);
/*    */     }
/*    */   }
/*    */   public void delete(Object object) throws ServiceException {
/*    */     try { getDAO().delete(object); } catch (Exception e) {
/* 27 */       throw new ServiceException(e);
/*    */     }
/*    */   }
/*    */   public void deleteCollection(Collection collection) throws ServiceException {
/*    */     try { getDAO().deleteCollection(collection); } catch (Exception e) {
/* 32 */       throw new ServiceException(e);
/*    */     }
/*    */   }
/*    */   public void updateStatus(Object object, Long status) throws ServiceException {
/*    */     try { getDAO().updateStatus(object, status); } catch (Exception e) {
/* 37 */       throw new ServiceException(e);
/*    */     }
/*    */   }
/*    */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.service.impl.CrudServiceImpl
 * JD-Core Version:    0.6.2
 */