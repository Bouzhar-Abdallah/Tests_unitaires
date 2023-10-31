package com.majidim.easybankv4.easybankv4.HibernateImps.Interfaces;

import com.majidim.easybankv4.easybankv4.HibernateImps.Interfaces.InterfaceData;
import com.majidim.easybankv4.easybankv4.dto.Agence;

import java.util.Optional;

public interface IHAgence extends InterfaceData<Agence, String> {

    @Override
    Optional<Agence> findByID(String id, Class<Agence> agenceClass);
}
