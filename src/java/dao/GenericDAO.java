package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

public class GenericDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Session s;

    public GenericDAO() {
     s = HibernateUtil.currentSession();    
    }
    
    

//    Utilizado por *DAO.java
    protected void saveOrUpdatePojo(Serializable pojo) {
    // s = HibernateUtil.getSession();
        try{
        //s.beginTransaction();
        s.saveOrUpdate(pojo);
       // s.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("entou no catch save " + e.getMessage());
           // s.getTransaction().rollback();
           e.printStackTrace();
           
        } finally {
            //s.close();
           // HibernateUtil.closeSession();
        }
        
    }
     protected void mergePojo(Serializable pojo) {      
       s.merge(pojo);
    }
     protected void evictPojo(Serializable pojo) {      
       s.evict(pojo);
     }
     
    @SuppressWarnings("unchecked")
//    Utilizado por *DAO.java
    protected <T extends Serializable> T getPojo(Class<T> classToSearch, Serializable key) {
         //s = HibernateUtil.getSession();
        try{
       
        Serializable toReturn;
        toReturn = (Serializable) s.get(classToSearch, key);
      
        return (T) toReturn;
        } catch (Exception e) {
            System.out.println("entou no catch getpojo " + e.getMessage());
           // s.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            //s.close();
            //HibernateUtil.closeSession();
        }
    }

//    No utilizado
    protected Serializable getPurePojo(String query, Object... params) {
      //s = HibernateUtil.getSession();
        try{
        //s.beginTransaction();
        Query qr = s.createQuery(query);
        for (int i = 0; i < params.length; i++) {
            qr.setParameter(i, params[i]);
        }
        Object toReturn = qr.uniqueResult();
       
        return (Serializable) toReturn;
        } catch (Exception e) {
            System.out.println("entou no catch purepojo serializable " + e.getMessage());
            //s.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
          
            //HibernateUtil.closeSession();
        }
    }
    
    protected <T extends Serializable> T getPurePojo(Class<T> classToCast , String query, Object... params) {
     // s = HibernateUtil.getSession();
        try{
       // s.beginTransaction();
        Query qr = s.createQuery(query);
        for (int i = 0; i < params.length; i++) {
            qr.setParameter(i, params[i]);
        }
        Object toReturn =   qr.uniqueResult();
       
        return (T)  toReturn;
        } catch (Exception e) {
            System.out.println("entou no catch pure pojo   " + e.getMessage());
     //       s.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
          
       //     HibernateUtil.closeSession();
        }
    }
    
    @SuppressWarnings("unchecked")
//    Utilizado por *DAO.java
    protected <T extends Serializable> List<T> getPureList(Class<T> classToCast, String query, Object... params) {
        List<T> toReturn ;
       // s = HibernateUtil.getSession();
        try {
           
            Query qr = s.createQuery(query);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    qr.setParameter(i, params[i]);
                }
            }
            toReturn = qr.list();
            return toReturn;
            
        } catch (Exception e) {
            System.out.println("entou no catch pure list " + e.getMessage());
           // s.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
           // s.close();
           // HibernateUtil.closeSession();
        }
       
    }

    @SuppressWarnings("unchecked")
//    Utilizado por EnderecoDAO.java
    protected <T extends Serializable> List<T> getPureListMaxResult(Class<T> classToCast, String query, Object... params) {
      //Session s = HibernateUtil.getSession();
        //s.beginTransaction();
        try{
        Query qr = s.createQuery(query);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                qr.setParameter(i, params[i]);
            }
            qr.setMaxResults(10);
        }
        List<T> toReturn = qr.list();
       
        
        return toReturn;
        } catch (Exception e) {
            System.out.println("entou no catch pure list Max Result" + e.getMessage());
           
            e.printStackTrace();
            return null;
        } finally {
           // s.close();
          //  HibernateUtil.closeSession();
        }
    }

}
