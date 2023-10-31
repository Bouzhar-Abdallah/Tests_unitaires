package com.majidim.easybankv4.easybankv4.dto;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor


@Entity()
@Table(name = "Employes")
@Inheritance
public class Employe extends Personne {
    @Id
    @Column(name = "matricule")
    private String matricule;
    @Column(name = "daterecrutement")
    private LocalDate dateRecrutement;

    @OneToMany(mappedBy = "employe")
    private List<DemendeCredit> demendeCredits;
    
    public Employe(String nom, String prenom, LocalDate dateN, String tel, String adress, String emailAdresse,String matricule,LocalDate dateRecrutement) {
        super(nom, prenom, dateN, tel, adress, emailAdresse);
        this.matricule = matricule;
        this.dateRecrutement = dateRecrutement;
    }

}
