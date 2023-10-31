package com.majidim.easybankv4.easybankv4.dto;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor

@MappedSuperclass
@Table(name = "persons")
public abstract class Personne {
    @Column(name = "nom")
    protected String nom;
    @Column(name = "prenom")
    protected String prenom;
    @Column(name = "daten")
    protected LocalDate dateN;
    @Column(name = "numerotel")
    protected String tel;
    @Column(name = "adress")
    protected String adress;
    @Column(name = "emailadresse")
    protected String emailAdresse;

}

