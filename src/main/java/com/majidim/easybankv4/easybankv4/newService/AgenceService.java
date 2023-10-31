package com.majidim.easybankv4.easybankv4.newService;

import com.majidim.easybankv4.easybankv4.HibernateImps.AgenceImp;
import com.majidim.easybankv4.easybankv4.dto.Agence;
import com.majidim.easybankv4.easybankv4.dto.Employe;

import java.util.Optional;


public class AgenceService {
    private final AgenceImp emp;

    public AgenceService(AgenceImp emp) {
        this.emp = emp;
    }
    public Optional<Agence> create(Agence agence) {
        return emp.create(agence);
    }
    public Optional<Agence> findByCode(String code) {
        return emp.findByID(code);
    }
    public Optional<Agence> Update(Agence agence){
        return emp.update(agence);
    }
}
