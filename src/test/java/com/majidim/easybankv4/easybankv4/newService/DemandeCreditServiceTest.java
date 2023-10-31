package com.majidim.easybankv4.easybankv4.newService;

import com.majidim.easybankv4.easybankv4.HibernateImps.AgenceImp;
import com.majidim.easybankv4.easybankv4.HibernateImps.ClientImpl;
import com.majidim.easybankv4.easybankv4.HibernateImps.DemandeCreditImpl;
import com.majidim.easybankv4.easybankv4.HibernateImps.EmployeImpl;
import com.majidim.easybankv4.easybankv4.dto.Agence;
import com.majidim.easybankv4.easybankv4.dto.Client;
import com.majidim.easybankv4.easybankv4.dto.DemendeCredit;
import com.majidim.easybankv4.easybankv4.dto.Employe;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DemandeCreditServiceTest {

    private static DemandeCreditService demandeCreditService;
    private static Employe employe;
    private static Agence agence;
    private static Client client;

    @BeforeAll
    public static void setUp(){
        demandeCreditService = new DemandeCreditService(new DemandeCreditImpl());
        EmployeImpl employeImp = new EmployeImpl();
        AgenceImp agenceImp = new AgenceImp();
        ClientImpl clientImp = new ClientImpl();
        Optional<Client> optionalClient = clientImp.findByID("client");
        Optional<Employe> employeOptional = employeImp.findByID("employer");
        Optional<Agence> agenceOptional = agenceImp.findByID("agence");
        optionalClient.ifPresent(value -> client = value);
        employeOptional.ifPresent(value -> employe = value);
        agenceOptional.ifPresent(value -> agence = value);

    }
    @Test
    void createTrue() {
        DemendeCredit demendeCreditTrue = new DemendeCredit();
        demendeCreditTrue.setNumero("xxx");
        demendeCreditTrue.setMontant(5000D);
        demendeCreditTrue.setRemarque("description");
        demendeCreditTrue.setDate(LocalDate.now());
        demendeCreditTrue.setDuree("14");
        demendeCreditTrue.setStatus("EnAttante");
        demendeCreditTrue.setSimulation(384.50585834838205D);
        demendeCreditTrue.setDate(LocalDate.now());
        demendeCreditTrue.setEmploye(employe);
        demendeCreditTrue.setAgence(agence);
        demendeCreditTrue.setClient(client);
        Optional<DemendeCredit> optionalDemendeCredit = demandeCreditService.create(demendeCreditTrue);
        assertTrue(optionalDemendeCredit.isPresent());
    }
    @Test
    void createFalse(){
        //create demende credit not ok
        DemendeCredit demendeCreditFalse = new DemendeCredit();
        demendeCreditFalse.setNumero("xxxx");
        demendeCreditFalse.setMontant(8000D);
        demendeCreditFalse.setRemarque("description");
        demendeCreditFalse.setDate(LocalDate.now());
        demendeCreditFalse.setDuree("14");
        demendeCreditFalse.setStatus("EnAttante");
        demendeCreditFalse.setSimulation(384.50585834838205D);
        demendeCreditFalse.setDate(LocalDate.now());
        demendeCreditFalse.setEmploye(employe);
        demendeCreditFalse.setAgence(agence);
        demendeCreditFalse.setClient(client);

        Optional<DemendeCredit> optionalDemendeCredit = demandeCreditService.create(demendeCreditFalse);
        assertFalse(optionalDemendeCredit.isPresent());
    }
    @AfterAll
    public static void tearDown (){
        demandeCreditService.delete("xxx");
        demandeCreditService.delete("xxxx");
    }
}
