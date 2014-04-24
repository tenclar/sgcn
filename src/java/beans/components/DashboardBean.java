package beans.components;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;


@ManagedBean
@ViewScoped
public class DashboardBean {

    private DashboardModel model;

    public DashboardBean() {
        model = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();
        DashboardColumn column3 = new DefaultDashboardColumn();
        column1.addWidget("geralgrafico");
        column1.addWidget("geral");        
        
        column2.addWidget("graficovlconcedente");
        column2.addWidget("vlconcedente");
        
        column3.addWidget("graficovlproponente");
        column3.addWidget("vlproponente");
        
        
        
        model.addColumn(column1);
        model.addColumn(column2);
        model.addColumn(column3);
        
    }

    public DashboardModel getModel() {
        return model;
    }

    public void setModel(DashboardModel model) {
        this.model = model;
    }
}
