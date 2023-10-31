package com.majidim.easybankv4.easybankv4.dto;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name = "DemendeCredits")
public class DemendeCredit {
    @Id
    private String numero;
    @Column
    private LocalDate date;
    @Column
    private Double montant;
    @Column
    private String duree;
    @Column
    private String remarque;
    @Column
    private String status;
    @Column
    private Double simulation;

    @ManyToOne

    private Employe employe;

    @ManyToOne

    private Client client;

    @ManyToOne

    private Agence agence;


}
