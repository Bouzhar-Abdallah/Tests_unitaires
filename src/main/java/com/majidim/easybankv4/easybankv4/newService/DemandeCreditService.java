package com.majidim.easybankv4.easybankv4.newService;

import com.majidim.easybankv4.easybankv4.HibernateImps.DemandeCreditImpl;
import com.majidim.easybankv4.easybankv4.dto.DemendeCredit;
import com.majidim.easybankv4.easybankv4.exception.ParametreException;

import java.util.List;
import java.util.Optional;

public class DemandeCreditService {
    private double Taux = 0.12D;
    private double tolerance = 1e-2;
    private final DemandeCreditImpl creditImpl;

    public DemandeCreditService(DemandeCreditImpl creditImpl) {
        this.creditImpl = creditImpl;
    }

    public Optional<DemendeCredit> create(DemendeCredit demendeCredit) throws IllegalArgumentException {
        if (demendeCredit == null) throw new IllegalArgumentException("un code n'etait pas fournit");
        if (validate(demendeCredit)) return creditImpl.create(demendeCredit);
        else return Optional.empty();
    }

    public Optional<DemendeCredit> findByCode(String code) throws IllegalArgumentException {
        if (code.isEmpty()) throw new IllegalArgumentException("un code n'etait pas fournit");
        return creditImpl.findByID(code);
    }

    public List<DemendeCredit> ShowList() {
        return creditImpl.getAll();
    }

    public boolean delete(String code) throws IllegalArgumentException {
        if (code.isEmpty()) throw new IllegalArgumentException("un code n'etait pas fournit");
        return creditImpl.delete(code);
    }
    private boolean validate(DemendeCredit demendeCredit) {
        return (Math.abs(calculeMensualite(demendeCredit) - demendeCredit.getSimulation()) < tolerance);

    }
    private Double calculeMensualite(DemendeCredit demendeCredit){
        return (demendeCredit.getMontant()*Taux/12) /(1- Math.pow( 1+(Taux/12),- Integer.parseInt(demendeCredit.getDuree()) ));
    }
    public Optional<DemendeCredit> update(DemendeCredit demendeCredit) {

        Optional<DemendeCredit> demendeCreditBeforeOpt = creditImpl.findByID(demendeCredit.getNumero());
        DemendeCredit demendeCreditBefore = new DemendeCredit();
        if (demendeCreditBeforeOpt.isPresent()){
            demendeCreditBefore = demendeCreditBeforeOpt.get();
        }
        if (protectData(demendeCreditBefore,demendeCredit))
        {
            return creditImpl.update(demendeCredit);
        }else{
            return Optional.empty();
        }
    }
    private boolean protectData(DemendeCredit demendeCreditBefore,DemendeCredit demendeCreditNew){
        return demendeCreditBefore.getSimulation().equals(demendeCreditNew.getSimulation()) &&
                demendeCreditBefore.getNumero().equals(demendeCreditNew.getNumero()) &&
                demendeCreditBefore.getDuree().equals(demendeCreditNew.getDuree()) &&
                demendeCreditBefore.getMontant().equals(demendeCreditNew.getMontant());
    }









    /*

    public List<DemendeCredit> SearchByStatus(CreditStatus status) {
        return creditImpl.SearchByStatus(status);
    }

    public List<DemendeCredit> SearchByDate(LocalDate date) {
        return creditImpl.SearchBydate(date);
    }

    public List<DemendeCredit> SearchByCode(String numero) {
        return creditImpl.SearchByCode(numero);
    }

    */
}
