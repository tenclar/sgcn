package util;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;

public class GeraBanco {

    public static void main(String[] args) {
        AnnotationConfiguration configuration = new AnnotationConfiguration();
        configuration.configure();
//        CRIA BANCO
       // SchemaExport se = new SchemaExport(configuration);
        //se.create(true, true);
//        ATUALIZA BANCO
        
        //SchemaUpdate su = new SchemaUpdate(configuration);
        //su.execute(true, true);
     //SchemaUpdate su = new SchemaUpdate(configuration);
       // su.execute(true, true);
    }
}
