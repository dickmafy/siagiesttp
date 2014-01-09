/*    */ package com.belogick.factory.util.support;
/*    */ 
/*    */ import org.springframework.beans.BeansException;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ import org.springframework.context.ApplicationContextAware;
/*    */ 
/*    */ public class ApplicationContextProvider
/*    */   implements ApplicationContextAware
/*    */ {
/*  7 */   private static ApplicationContext applicationContext = null;
/*    */ 
/*  9 */   public static ApplicationContext getApplicationContext() { return applicationContext; } 
/*    */   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
/* 11 */     applicationContext = applicationContext;
/*    */   }
/*    */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.support.ApplicationContextProvider
 * JD-Core Version:    0.6.2
 */