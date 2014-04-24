package util;

import java.io.IOException;
import javax.servlet.*;
import org.apache.log4j.Logger;


public class HibernateSessionFilter implements Filter {

    private static Logger log = Logger.getLogger(HibernateSessionFilter.class);
    //private SessionFactory sf;   
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
         
        try {   
          //  log.error("Abre session");
            HibernateUtil.getSession();
          //   log.error("inicia Transação");
            HibernateUtil.currentSession().beginTransaction();
            chain.doFilter(request, response);
         //   log.error("commita Transação");
            HibernateUtil.currentSession().getTransaction().commit();
        } catch (ServletException e) {
            HibernateUtil.currentSession().getTransaction().rollback();
          //  log.error(e.getMessage());
        } finally {
          //  log.error("Fecha session");
            HibernateUtil.closeSession();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
