package com.majidim.easybankv4.easybankv4.HibernateImps;

import com.majidim.easybankv4.easybankv4.dto.Agence;
import com.majidim.easybankv4.easybankv4.HibernateImps.Interfaces.IHAgence;
import com.majidim.easybankv4.easybankv4.dto.DemendeCredit;

import java.util.Optional;

public class AgenceImp extends HibernateImplementation<Agence,String> implements IHAgence {
    public Optional<Agence> findByID(String id) {
        return super.findByID(id, Agence.class);
    }
    public boolean delete(String code){
        return delete(code,Agence.class);
    }
}
