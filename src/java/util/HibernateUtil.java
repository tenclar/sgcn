/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;




import java.sql.Connection;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author tenclar
 */
public class HibernateUtil{
    
    private static  Logger log =Logger.getLogger(HibernateUtil.class);
    private static  SessionFactory sessionFactory;
    private static  ThreadLocal<Session> sessions = new ThreadLocal<Session>();
    private static Connection conn = null;
    
    static{
          sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
       }
    
    public static Session getSession(){
     //  log.error("Session Hibernate");
        if(sessions.get() != null){  
              log.error("Alguma Session nao fechada");
        }
            sessions.set(sessionFactory.openSession());                          
        return sessions.get();
//        Session session = (Session) sessions.get();
//       // System.out.println("Sessao: "+session);
//        if (session == null){            
//                session = sessionFactory.openSession();
//            }                  
//        
//        sessions.set(session);
//        return sessions.get();
    }
    
    public static void closeSession(){
       sessions.get().close();
       sessions.set(null);
        
    }
    public static Session currentSession(){
        return sessions.get();
    }
   
   
      public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
      
      
    public static Connection getConnection(){
        conn = getSessionFactory().getCurrentSession().connection();
        return conn;
    }
   
}
