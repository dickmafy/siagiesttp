/*    */ package com.belogick.factory.util.dao.impl;
/*    */ 
/*    */ import com.belogick.factory.util.dao.CrudDao;
/*    */ import com.belogick.factory.util.helper.BeanHelper;
/*    */ import com.belogick.factory.util.support.DaoException;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import javax.persistence.EntityManager;
/*    */ import javax.persistence.EntityManagerFactory;
/*    */ import org.apache.commons.beanutils.PropertyUtils;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.criterion.Example;
/*    */ import org.springframework.orm.jpa.JpaTemplate;
/*    */ 
/*    */ public class CrudDaoJpa extends NativeDaoJpa
/*    */   implements CrudDao
/*    */ {
/*    */   public Object save(Object object)
/*    */     throws DaoException
/*    */   {
/*    */     try
/*    */     {
/* 22 */       BeanHelper.printAll(object); } catch (Exception e) { e.printStackTrace(); }
/* 23 */     Object result = null;
/*    */     try {
/* 25 */       result = getJpaTemplate().merge(object);
/*    */     }
/*    */     catch (Exception re) {
/* 28 */       this.log.error("save failed", re);
/* 29 */       throw new DaoException(re);
/*    */     }
/* 31 */     return result;
/*    */   }
/*    */ 
/*    */   public void saveCollection(Collection collection)
/*    */     throws DaoException
/*    */   {
/* 40 */     for (Iterator it = collection.iterator(); it.hasNext(); save(it.next()));
/*    */   }
/*    */ 
/*    */   public void delete(Object object)
/*    */     throws DaoException
/*    */   {
/*    */     try
/*    */     {
/* 51 */       Session hibernateSession = (Session)getJpaTemplate().getEntityManagerFactory().createEntityManager().getDelegate();
/* 52 */       hibernateSession.createQuery("DELETE FROM " + object.getClass().getName() + " WHERE id='" + PropertyUtils.getProperty(object, "id") + "'");
/*    */     }
/*    */     catch (Exception re)
/*    */     {
/* 56 */       this.log.error("delete failed", re);
/* 57 */       throw new DaoException(re);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void deleteCollection(Collection collection)
/*    */     throws DaoException
/*    */   {
/* 67 */     for (Iterator it = collection.iterator(); it.hasNext(); delete(it.next()));
/*    */   }
/*    */ 
/*    */   public void updateStatus(Object object, Long status)
/*    */     throws DaoException
/*    */   {
/*    */     try
/*    */     {
/* 79 */       Session hibernateSession = (Session)getJpaTemplate().getEntityManagerFactory().createEntityManager().getDelegate();
/* 80 */       hibernateSession.createQuery("UPDATE " + object.getClass().getName() + " SET estado='" + status + "' WHERE id='" + PropertyUtils.getProperty(object, "id") + "'").executeUpdate();
/*    */     }
/*    */     catch (Exception re)
/*    */     {
/* 84 */       this.log.error("delete failed", re);
/* 85 */       throw new DaoException(re);
/*    */     }
/*    */   }
/*    */ 
/*    */   protected Criteria getCriteria(Object object)
/*    */   {
/* 91 */     Session hibernateSession = (Session)getJpaTemplate().getEntityManagerFactory().createEntityManager().getDelegate();
/* 92 */     Example example = Example.create(object)
/* 93 */       .ignoreCase()
/* 94 */       .enableLike();
/* 95 */     return hibernateSession.createCriteria(object.getClass()).add(example);
/*    */   }
/*    */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.dao.impl.CrudDaoJpa
 * JD-Core Version:    0.6.2
 */