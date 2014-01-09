/*     */ package com.belogick.factory.util.dao.impl;
/*     */ 
/*     */ import com.belogick.factory.util.dao.NativeDao;
/*     */ import com.belogick.factory.util.support.DaoException;
/*     */ import java.sql.Connection;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.persistence.EntityManager;
/*     */ import javax.persistence.EntityManagerFactory;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.SQLQuery;
/*     */ import org.hibernate.Session;
/*     */ import org.hibernate.SessionFactory;
/*     */ import org.hibernate.StatelessSession;
/*     */ import org.springframework.orm.jpa.JpaTemplate;
/*     */ import org.springframework.orm.jpa.support.JpaDaoSupport;
/*     */ 
/*     */ public class NativeDaoJpa extends JpaDaoSupport
/*     */   implements NativeDao
/*     */ {
/*  14 */   protected final Logger log = Logger.getLogger(getClass());
/*     */ 
/*     */   public Connection getConnection()
/*     */     throws DaoException
/*     */   {
/*  19 */     Connection cnx = null;
/*  20 */     Session hibernateSession = null;
/*     */     try
/*     */     {
/*  23 */       hibernateSession = (Session)getJpaTemplate().getEntityManagerFactory().createEntityManager().getDelegate();
/*  24 */       cnx = hibernateSession.getSessionFactory().openStatelessSession().connection();
/*     */     } catch (Exception e) {
/*  26 */       throw new DaoException(e);
/*  27 */     }return cnx;
/*     */   }
/*     */ 
/*     */   public Query createQuery(String queryName)
/*     */     throws DaoException
/*     */   {
/*  38 */     Session hibernateSession = (Session)getJpaTemplate().getEntityManagerFactory().createEntityManager().getDelegate();
/*  39 */     return hibernateSession.createSQLQuery(queryName);
/*     */   }
/*     */ 
/*     */   public void executeQueryUpdate(String query)
/*     */     throws DaoException
/*     */   {
/*     */     try
/*     */     {
/*  51 */       Session hibernateSession = (Session)getJpaTemplate().getEntityManagerFactory().createEntityManager().getDelegate();
/*  52 */       hibernateSession.createSQLQuery(query).executeUpdate();
/*  53 */       hibernateSession = null;
/*     */     } catch (Exception e) {
/*  55 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public List executeQueryList(String query)
/*     */     throws DaoException
/*     */   {
/*     */     try
/*     */     {
/*  68 */       Session hibernateSession = (Session)getJpaTemplate().getEntityManagerFactory().createEntityManager().getDelegate();
/*  69 */       return hibernateSession.createSQLQuery(query).list();
/*     */     } catch (Exception e) {
/*  71 */       e.printStackTrace();
/*  72 */     }return new ArrayList();
/*     */   }
/*     */ 
/*     */   public List executeQueryHibernate(String query)
/*     */     throws DaoException
/*     */   {
/*  82 */     return getJpaTemplate().find(query);
/*     */   }
/*     */ 
/*     */   public List executeQueryNamed(String queryName)
/*     */     throws DaoException
/*     */   {
/*  92 */     return getJpaTemplate().findByNamedQuery(queryName);
/*     */   }
/*     */ 
/*     */   public Object executeQueryNamedUnique(String queryName, Object[] values)
/*     */     throws DaoException
/*     */   {
/* 103 */     Object result = null;
/* 104 */     List tempList = getJpaTemplate().findByNamedQuery(queryName, values);
/* 105 */     if (!tempList.isEmpty()) result = tempList.get(0);
/* 106 */     return result;
/*     */   }
/*     */ 
/*     */   public List executeQueryNamed(String queryName, Object[] values)
/*     */     throws DaoException
/*     */   {
/* 117 */     return getJpaTemplate().findByNamedQuery(queryName, values);
/*     */   }
/*     */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.dao.impl.NativeDaoJpa
 * JD-Core Version:    0.6.2
 */