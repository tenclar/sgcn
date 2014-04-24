package dao.mci;

import dao.GenericDAO;
import entity.mci.BeneficioSocial;
import java.util.List;

public class BeneficioSocialDAO extends GenericDAO {

    public BeneficioSocialDAO() {
    }

    public long saveBeneficioSocial(BeneficioSocial beneficiosocial) {
        saveOrUpdatePojo(beneficiosocial);
        return beneficiosocial.getId();
    }

    public void updateBeneficioSocial(BeneficioSocial beneficiosocial) {
        saveOrUpdatePojo(beneficiosocial);
    }

    public BeneficioSocial getBeneficioSocial(int beneficiosocialId) {
        BeneficioSocial BeneficioSocial = getPojo(BeneficioSocial.class, beneficiosocialId);
        return BeneficioSocial;
    }

    public List<BeneficioSocial> getBeneficioSocials() {
        return getPureList(BeneficioSocial.class, "from BeneficioSocial beneficiosocial");
    }

//    public BeneficioSocialDAO(Session ses) {;;;
//        super(ses);
//    }
//    public void removeBeneficioSocial(BeneficioSocial beneficiosocial) {
//        removePojo(beneficiosocial);
//    }
}
