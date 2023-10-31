package com.majidim.easybankv4.easybankv4.HibernateImps.Interfaces;

import com.majidim.easybankv4.easybankv4.dto.Client;
import com.majidim.easybankv4.easybankv4.dto.DemendeCredit;

import java.util.Optional;

public interface IHClient extends InterfaceData<Client, String>{
    @Override
    Optional<Client> findByID(String id, Class<Client> clientClass);
}
