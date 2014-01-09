/*    */ package com.belogick.factory.util.service.impl;
/*    */ 
/*    */ import com.belogick.factory.util.dao.NativeDao;
/*    */ import com.belogick.factory.util.service.NativeService;
/*    */ import com.belogick.factory.util.support.ServiceException;
/*    */ import java.sql.Connection;
/*    */ import java.util.List;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.hibernate.Query;
/*    */ 
/*    */ public class NativeServiceImpl
/*    */   implements NativeService
/*    */ {
/* 14 */   protected Logger log = Logger.getLogger(getClass());
/*    */   private NativeDao nativeDao;
/*    */ 
/*    */   public void setNativeDAO(NativeDao NativeDao)
/*    */   {
/* 15 */     this.nativeDao = NativeDao; } 
/* 16 */   public NativeDao getDAO() { return this.nativeDao; }
/*    */ 
/*    */   public Connection getConnection() throws Exception
/*    */   {
/*    */     try
/*    */     {
/* 22 */       return getDAO().getConnection();
/*    */     }
/*    */     catch (Exception e) {
/* 25 */       e.printStackTrace();
/* 26 */       throw new ServiceException(e);
/*    */     }
/*    */   }
/*    */ 
/*    */   public Query createQuery(String queryName) throws ServiceException
/*    */   {
/*    */     try {
/* 33 */       return getDAO().createQuery(queryName); } catch (Exception e) {
/* 34 */       throw new ServiceException(e);
/*    */     }
/*    */   }
/*    */   public void executeQueryUpdate(String query) throws ServiceException {
/*    */     try { getDAO().executeQueryUpdate(query); } catch (Exception e) {
/* 39 */       throw new ServiceException(e);
/*    */     }
/*    */   }
/*    */   public List executeQueryList(String query) throws ServiceException {
/*    */     try { return getDAO().executeQueryList(query); } catch (Exception e) {
/* 44 */       throw new ServiceException(e);
/*    */     }
/*    */   }
/*    */   public List executeQueryHibernate(String query) throws ServiceException {
/*    */     try { return getDAO().executeQueryHibernate(query); } catch (Exception e) {
/* 49 */       throw new ServiceException(e);
/*    */     }
/*    */   }
/*    */   public List executeQueryNamed(String queryName) throws ServiceException {
/*    */     try { return getDAO().executeQueryNamed(queryName); } catch (Exception e) {
/* 54 */       throw new ServiceException(e);
/*    */     }
/*    */   }
/*    */   public Object executeQueryNamedUnique(String queryName, Object[] values) throws ServiceException {
/*    */     try { return getDAO().executeQueryNamedUnique(queryName, values); } catch (Exception e) {
/* 59 */       throw new ServiceException(e);
/*    */     }
/*    */   }
/*    */   public List executeQueryNamed(String queryName, Object[] values) throws ServiceException {
/*    */     try { return getDAO().executeQueryNamed(queryName, values); } catch (Exception e) {
/* 64 */       throw new ServiceException(e);
/*    */     }
/*    */   }
/*    */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.service.impl.NativeServiceImpl
 * JD-Core Version:    0.6.2
 */