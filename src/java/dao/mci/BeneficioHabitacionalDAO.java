package dao.mci;

import dao.GenericDAO;
import entity.mci.BeneficioHabitacional;
import java.util.List;

public class BeneficioHabitacionalDAO extends GenericDAO {

    private static final long serialVersionUID = 1L;

    public BeneficioHabitacionalDAO() {
    }

    public long saveBeneficioHabitacional(BeneficioHabitacional beneficiohabitacional) {
        saveOrUpdatePojo(beneficiohabitacional);
        return beneficiohabitacional.getId();
    }

    public void updateBeneficioHabitacional(BeneficioHabitacional beneficiohabitacional) {
        saveOrUpdatePojo(beneficiohabitacional);
    }

    public BeneficioHabitacional getBeneficioHabitacional(int beneficiohabitacionalId) {
        BeneficioHabitacional BeneficioHabitacional = getPojo(BeneficioHabitacional.class, beneficiohabitacionalId);
        return BeneficioHabitacional;
    }

    public List<BeneficioHabitacional> getBeneficioHabitacionais() {
        return getPureList(BeneficioHabitacional.class, "from BeneficioHabitacional beneficiohabitacional");
    }
//    public BeneficioHabitacionalDAO(Session ses) {
//        super(ses);
//    }
//    public void removeBeneficioHabitacional(BeneficioHabitacional beneficiohabitacional) {
//        removePojo(beneficiohabitacional);
//    }
}
