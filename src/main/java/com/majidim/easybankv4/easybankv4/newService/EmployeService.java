package com.majidim.easybankv4.easybankv4.newService;

import com.majidim.easybankv4.easybankv4.dto.Employe;
import com.majidim.easybankv4.easybankv4.dto.Personne;
import com.majidim.easybankv4.easybankv4.HibernateImps.EmployeImpl;

import java.util.List;
import java.util.Optional;

public class EmployeService {
    private final EmployeImpl employeImpl;

    public EmployeService(EmployeImpl employeImpl) {
        this.employeImpl = employeImpl;
    }
    public Optional<Employe> create(Employe employe) {
        return employeImpl.create(employe);
    }
    public Optional<Employe> findByMatricule(String matricule){
        return employeImpl.findByID(matricule);
    }
    public List<Employe> getAll(){
        return employeImpl.getAll();
    }
    public List<Employe> ShowList(){
        return employeImpl.getAll();
    }
    public Optional<Employe> Update(Employe employe){
        return employeImpl.update(employe);
    }
    public boolean delete(String code) {
        return employeImpl.delete(code);
    }
}
