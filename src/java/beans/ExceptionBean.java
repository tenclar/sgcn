/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author tenclar
 */

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ExceptionBean {

    public void throwRuntimeException() {
        throw new RuntimeException("peek-a-boo");
    }

    public void throwSQLException() throws SQLException {
        throw new SQLException("DB fail");
    }

}
