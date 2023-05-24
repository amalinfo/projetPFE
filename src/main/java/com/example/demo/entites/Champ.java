package com.example.demo.entites;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonDeserialize(as =Champ.class)
public class Champ {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nom", unique = true)
    private String nom;
    @Column(name = "numero", unique = true)
    private  Long numero;
    @Column(name = "adresse", nullable = false)
    private String adresse;
    @Column(name = "date_ajout", nullable = false)

    private Date dateAjout;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @JsonIgnore
    @OneToMany(mappedBy = "champ",  fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<Capteur> capteur;

    public String getUserEmail(){
        return this.user.getEmail();

    }
    public String getUserPhone(){
        return this.user.getNumTel();
    }
}
