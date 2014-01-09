/*     */ package com.belogick.factory.util.dao.impl;
/*     */ 
/*     */ import com.belogick.factory.util.constant.Constante;
/*     */ import com.belogick.factory.util.dao.ListDao;
/*     */ import com.belogick.factory.util.helper.BeanHelper;
/*     */ import com.belogick.factory.util.support.DaoException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ import org.hibernate.Criteria;
/*     */ import org.hibernate.criterion.Disjunction;
/*     */ import org.hibernate.criterion.Order;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ 
/*     */ public class ListDaoJpa extends CrudDaoJpa
/*     */   implements ListDao
/*     */ {
/*     */   public List listByObject(Object object)
/*     */     throws DaoException
/*     */   {
/*     */     try
/*     */     {
/*  27 */       Criteria criteria = getCriteria(object);
/*  28 */       criteria.addOrder(Order.desc("id"));
/*  29 */       return criteria.list();
/*     */     } catch (Exception e) {
/*  31 */       e.printStackTrace();
/*  32 */     }return new ArrayList();
/*     */   }
/*     */ 
/*     */   public List listByObjectStatus(Object object, String statusList)
/*     */     throws DaoException
/*     */   {
/*     */     try
/*     */     {
/*  47 */       Criteria criteria = getCriteria(object);
/*  48 */       Disjunction disjunctionOrs = Restrictions.disjunction();
/*  49 */       String[] lista = statusList.split(",");
/*  50 */       for (int i = 0; i < lista.length; i++)
/*  51 */         disjunctionOrs.add(Restrictions.eq("estado", Long.valueOf(lista[i])));
/*  52 */       criteria.add(disjunctionOrs);
/*  53 */       criteria.addOrder(Order.desc("id"));
/*  54 */       lista = null;
/*  55 */       return criteria.list();
/*     */     } catch (Exception e) {
/*  57 */       e.printStackTrace();
/*  58 */     }return new ArrayList();
/*     */   }
/*     */ 
/*     */   public List listByObjectEnabled(Object object)
/*     */     throws DaoException
/*     */   {
/*     */     try
/*     */     {
/*  71 */       return listByObjectStatus(object, Constante.ROW_STATUS_ENABLED.toString()); } catch (Exception e) {
/*  72 */       e.printStackTrace();
/*  73 */     }return new ArrayList();
/*     */   }
/*     */ 
/*     */   public List listByObjectEnabledDisabled(Object object)
/*     */     throws DaoException
/*     */   {
/*     */     try
/*     */     {
/*  86 */       return listByObjectStatus(object, Constante.ROW_STATUS_ENABLED.toString() + "," + Constante.ROW_STATUS_DISABLED.toString()); } catch (Exception e) {
/*  87 */       e.printStackTrace();
/*  88 */     }return new ArrayList();
/*     */   }
/*     */ 
/*     */   public List listByObject_OrderBy(Object object, String fieldList, boolean order)
/*     */     throws DaoException
/*     */   {
/*     */     try
/*     */     {
/* 103 */       Criteria criteria = getCriteria(object);
/* 104 */       String[] lista = fieldList.split(",");
/* 105 */       if (order) for (int i = 0; i < lista.length; i++) criteria.addOrder(Order.desc(lista[i])); 
/*     */       else
/* 106 */         for (int i = 0; i < lista.length; i++) criteria.addOrder(Order.asc(lista[i]));
/* 107 */       lista = null;
/* 108 */       return criteria.list();
/*     */     } catch (Exception e) {
/* 110 */       e.printStackTrace();
/* 111 */     }return new ArrayList();
/*     */   }
/*     */ 
/*     */   public List listObject_OrderAscBy(Object object, String fieldList)
/*     */     throws DaoException
/*     */   {
/* 123 */     return listByObject_OrderBy(object, fieldList, false);
/*     */   }
/*     */ 
/*     */   public List listObject_OrderDscBy(Object object, String fieldList)
/*     */     throws DaoException
/*     */   {
/* 134 */     return listByObject_OrderBy(object, fieldList, true);
/*     */   }
/*     */ 
/*     */   public List listByNested(Object object, String fieldList)
/*     */     throws DaoException
/*     */   {
/*     */     try
/*     */     {
/* 147 */       Criteria criteria = getCriteria(BeanHelper.newInstance(object));
/* 148 */       String[] field = fieldList.split(",");
/*     */ 
/* 150 */       int posicion = 0;
/* 151 */       for (int i = 0; i < field.length; i++)
/*     */       {
/* 153 */         if (Long.valueOf(PropertyUtils.getProperty(object, field[i]).toString()).longValue() != 0L)
/*     */         {
/* 155 */           criteria.add(Restrictions.eq(field[i], PropertyUtils.getProperty(object, field[i])));
/* 156 */           posicion = i;
/*     */         }
/* 160 */         else if (i == 0)
/*     */         {
/* 162 */           criteria.add(Restrictions.ne(field[i], Long.valueOf(0L)));
/* 163 */           posicion = 99;
/*     */         }
/* 165 */         else if (i == posicion + 1) {
/* 166 */           criteria.add(Restrictions.ne(field[i], Long.valueOf(0L)));
/*     */         } else {
/* 168 */           criteria.add(Restrictions.eq(field[i], Long.valueOf(0L)));
/*     */         }
/*     */       }
/* 171 */       criteria.addOrder(Order.asc("descripcion"));
/* 172 */       return criteria.list();
/*     */     } catch (Exception e) {
/* 174 */       e.printStackTrace();
/* 175 */     }return new ArrayList();
/*     */   }
/*     */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.dao.impl.ListDaoJpa
 * JD-Core Version:    0.6.2
 */