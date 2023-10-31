package com.majidim.easybankv4.easybankv4.dto;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor


@Entity()
@Table(name = "Clients")
@Inheritance
public class Client extends Personne {
    @Id
    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "client")
    private List<DemendeCredit> demendeCredits;

    public Client(String code, String nom, String prenom, LocalDate dateN, String tel, String adress, String emailAdresse) {
        super(nom, prenom, dateN, tel, adress, emailAdresse);
        this.code = code;
    }
}
