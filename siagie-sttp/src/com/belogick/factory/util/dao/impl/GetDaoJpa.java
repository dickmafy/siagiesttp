/*    */ package com.belogick.factory.util.dao.impl;
/*    */ 
/*    */ import com.belogick.factory.util.dao.GetDao;
/*    */ import com.belogick.factory.util.helper.BeanHelper;
/*    */ import com.belogick.factory.util.support.DaoException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.apache.commons.beanutils.PropertyUtils;
/*    */ import org.hibernate.Criteria;
/*    */ import org.springframework.orm.jpa.JpaTemplate;
/*    */ 
/*    */ public class GetDaoJpa extends ListDaoJpa
/*    */   implements GetDao
/*    */ {
/*    */   public Object findByObject(Object object)
/*    */     throws DaoException
/*    */   {
/*    */     try
/*    */     {
/* 21 */       Criteria criteria = getCriteria(object);
/* 22 */       return criteria.list().get(0);
/*    */     } catch (Exception e) {
/* 24 */       e.printStackTrace();
/* 25 */     }return new ArrayList();
/*    */   }
/*    */ 
/*    */   public Object findById(Object object)
/*    */     throws DaoException
/*    */   {
/*    */     try
/*    */     {
/* 37 */       return getJpaTemplate().find(object.getClass(), PropertyUtils.getProperty(object, "id"));
/*    */     }
/*    */     catch (Exception e) {
/* 40 */       e.printStackTrace();
/* 41 */       throw new DaoException(e);
/*    */     }
/*    */   }
/*    */ 
/*    */   public Object findById(Class clazz, Long id)
/*    */     throws DaoException
/*    */   {
/*    */     try
/*    */     {
/* 55 */       return getJpaTemplate().find(clazz, id);
/*    */     }
/*    */     catch (Exception e) {
/* 58 */       e.printStackTrace();
/* 59 */       throw new DaoException(e);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void deleteByField(Object object, String field, String value) throws DaoException
/*    */   {
/*    */     try {
/* 66 */       executeQueryUpdate("DELETE FROM " + BeanHelper.getTableName(object) + " WHERE " + field + "='" + value + "'; ");
/*    */     }
/*    */     catch (Exception e) {
/* 69 */       e.printStackTrace();
/* 70 */       throw new DaoException(e);
/*    */     }
/*    */   }
/*    */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.dao.impl.GetDaoJpa
 * JD-Core Version:    0.6.2
 */