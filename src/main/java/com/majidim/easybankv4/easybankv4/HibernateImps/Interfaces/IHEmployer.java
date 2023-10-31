package com.majidim.easybankv4.easybankv4.HibernateImps.Interfaces;

import com.majidim.easybankv4.easybankv4.dto.Employe;

import java.util.Optional;

public interface IHEmployer extends InterfaceData<Employe, String>{
    @Override
    Optional<Employe> findByID(String id, Class<Employe> employeClass);
}
