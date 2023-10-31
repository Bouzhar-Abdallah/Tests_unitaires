package com.majidim.easybankv4.easybankv4.HibernateImps;

import com.majidim.easybankv4.easybankv4.HibernateImps.Interfaces.IHEmployer;
import com.majidim.easybankv4.easybankv4.dto.Client;
import com.majidim.easybankv4.easybankv4.dto.DemendeCredit;
import com.majidim.easybankv4.easybankv4.dto.Employe;

import java.util.List;
import java.util.Optional;

public class EmployeImpl extends HibernateImplementation<Employe, String> implements IHEmployer {
    public Optional<Employe> findByID(String id) {

        return super.findByID(id, Employe.class);
    }
    public List<Employe> getAll(){

        return getAll(Employe.class);
    }
    public boolean delete(String code){
        return delete(code, Employe.class);
    }
}
