package com.majidim.easybankv4.easybankv4.HibernateImps.Interfaces;

import com.majidim.easybankv4.easybankv4.dto.DemendeCredit;

import java.util.Optional;

public interface IHDemandeCredit extends InterfaceData<DemendeCredit, String> {
    @Override
    Optional<DemendeCredit> findByID(String id, Class<DemendeCredit> demendeCreditClass);

    @Override
    boolean delete(String id, Class<DemendeCredit> demendeCreditClass);
}
