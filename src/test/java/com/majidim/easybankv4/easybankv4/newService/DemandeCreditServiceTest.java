package com.majidim.easybankv4.easybankv4.newService;

import com.majidim.easybankv4.easybankv4.HibernateImps.AgenceImp;
import com.majidim.easybankv4.easybankv4.HibernateImps.ClientImpl;
import com.majidim.easybankv4.easybankv4.HibernateImps.DemandeCreditImpl;
import com.majidim.easybankv4.easybankv4.HibernateImps.EmployeImpl;
import com.majidim.easybankv4.easybankv4.dto.Agence;
import com.majidim.easybankv4.easybankv4.dto.Client;
import com.majidim.easybankv4.easybankv4.dto.DemendeCredit;
import com.majidim.easybankv4.easybankv4.dto.Employe;
import com.majidim.easybankv4.easybankv4.exception.ParametreException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DemandeCreditServiceTest {

    DemandeCreditService demandeCreditService;
    @Mock
    DemandeCreditImpl impDemandeCredit;
    Employe employe;
    Agence agence;
    Client client;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        demandeCreditService = new DemandeCreditService(impDemandeCredit);
    }

    @Test
    void createTrue() {
        DemendeCredit demendeCreditTrue = createValidDemendeCredit();

        // Mock the service
        when(impDemandeCredit.create(demendeCreditTrue)).thenReturn(Optional.of(demendeCreditTrue));

        // Act
        Optional<DemendeCredit> result = demandeCreditService.create(demendeCreditTrue);

        // Assert that the result is present and contains the expected data
        assertTrue(result.isPresent(), "The creation should be successful");
        assertEquals(demendeCreditTrue, result.get(), "The created object should match the expected object");
        verify(impDemandeCredit, Mockito.times(1)).create(demendeCreditTrue);
    }

    @Test
    void createFalse() {
        DemendeCredit demendeCreditFalse = createInvalidDemendeCredit();

        Optional<DemendeCredit> result = demandeCreditService.create(demendeCreditFalse);

        assertTrue(result.isEmpty());
        verify(impDemandeCredit, Mockito.times(0)).create(demendeCreditFalse);

    }

    @Test
    void createFalseException() {

        assertThrows(IllegalArgumentException.class, () -> {
            demandeCreditService.create(null);
        });
    }

    @Test
    void testDeleteWithException() {

        String demandeCreditId = "";

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> demandeCreditService.delete(demandeCreditId),
                "Delete should throw ParametreException");
    }
    @Test
    void testDeleteNoException() {

        String demandeCreditId = "x";

        when(impDemandeCredit.delete(demandeCreditId)).thenReturn(true);

        boolean result = demandeCreditService.delete(demandeCreditId);
        verify(impDemandeCredit, Mockito.times(1)).delete(demandeCreditId);
        assertTrue(result);

    }

    private DemendeCredit createValidDemendeCredit() {
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
        return demendeCreditTrue;
    }

    private DemendeCredit createInvalidDemendeCredit() {
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
        return demendeCreditFalse;
    }

}
