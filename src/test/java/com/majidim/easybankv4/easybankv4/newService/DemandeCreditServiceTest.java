package com.majidim.easybankv4.easybankv4.newService;


import com.majidim.easybankv4.easybankv4.HibernateImps.DemandeCreditImpl;
import com.majidim.easybankv4.easybankv4.dto.DemendeCredit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DemandeCreditServiceTest {

    DemandeCreditService demandeCreditService;
    @Mock
    DemandeCreditImpl impDemandeCredit;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        demandeCreditService = new DemandeCreditService(impDemandeCredit);
    }

    @Test
    void createTrue() {
        DemendeCredit demendeCreditTrue = createValidDemendeCredit();

        when(impDemandeCredit.create(demendeCreditTrue)).thenReturn(Optional.of(demendeCreditTrue));

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

        String throwExceptionId = "";

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> demandeCreditService.delete(throwExceptionId),
                "Delete should throw ParametreException");

        String NoExceptionId = "x";

        when(impDemandeCredit.delete(NoExceptionId)).thenReturn(true);

        boolean result = demandeCreditService.delete(NoExceptionId);
        verify(impDemandeCredit, Mockito.times(1)).delete(NoExceptionId);
        assertTrue(result);
    }

    @Test
    void testFindByCode() {

        String throwExceptionId = "";

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> demandeCreditService.findByCode(throwExceptionId),
                "Delete should throw ParametreException");

        String demandeCreditId = "x";
        DemendeCredit mockedDemendeCredit = Mockito.mock(DemendeCredit.class);
        when(impDemandeCredit.findByID(demandeCreditId)).thenReturn(Optional.of(mockedDemendeCredit));

        Optional<DemendeCredit> result = demandeCreditService.findByCode(demandeCreditId);
        verify(impDemandeCredit, Mockito.times(1)).findByID(demandeCreditId);
        assertTrue(result.isPresent());

    }

    @Test
    void testUpdate(){
        DemendeCredit demendeCredit = createValidDemendeCredit();
        when(impDemandeCredit.findByID(demendeCredit.getNumero())).thenReturn(Optional.of(demendeCredit));
        when(impDemandeCredit.update(demendeCredit)).thenReturn(Optional.of(demendeCredit));

        Optional<DemendeCredit> result = demandeCreditService.update(demendeCredit);

        verify(impDemandeCredit, Mockito.times(1)).findByID(demendeCredit.getNumero());
        verify(impDemandeCredit, Mockito.times(1)).update(demendeCredit);

        assertTrue(result.isPresent());
    }
    @Test
    void testUpdateError(){
        DemendeCredit demendeCredit = createValidDemendeCredit();
        when(impDemandeCredit.findByID(demendeCredit.getNumero())).thenReturn(Optional.of(createInvalidDemendeCredit()));
        when(impDemandeCredit.update(demendeCredit)).thenReturn(Optional.of(demendeCredit));

        Optional<DemendeCredit> result = demandeCreditService.update(demendeCredit);

        verify(impDemandeCredit, Mockito.times(1)).findByID(demendeCredit.getNumero());
        verify(impDemandeCredit, Mockito.times(0)).update(demendeCredit);
        assertFalse(result.isPresent());
    }
    private DemendeCredit createValidDemendeCredit() {
        DemendeCredit demendeCreditTrue = new DemendeCredit();
        demendeCreditTrue.setNumero("xxx");
        demendeCreditTrue.setMontant(5000D);
        demendeCreditTrue.setRemarque("description");
        demendeCreditTrue.setDate(LocalDate.now());
        demendeCreditTrue.setDuree("14");
        demendeCreditTrue.setStatus("EnAttante");
        demendeCreditTrue.setSimulation(384.50D);
        demendeCreditTrue.setDate(LocalDate.now());

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
        demendeCreditFalse.setSimulation(384.50D);
        demendeCreditFalse.setDate(LocalDate.now());
        return demendeCreditFalse;
    }

}
