package com.majidim.easybankv4.easybankv4.dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity(name="Agences")
public class Agence {
    public Agence(String code, String nom, String adresse, String tel) {
        this.code = code;
        this.nom = nom;
        this.adresse = adresse;
        this.tel = tel;
    }

    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "nom")
    private String nom;
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "tel")
    private String tel;
    @OneToMany
    private List<DemendeCredit> demendeCredits;

    /*@OneToMany(mappedBy = "agence")
    private List<Compte> comptes;
    @OneToMany(mappedBy = "agence")
    private List<EmployeAgency> employeAgencies;*/


}