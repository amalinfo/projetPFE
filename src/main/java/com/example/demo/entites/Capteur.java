package com.example.demo.entites;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Capteur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    @Column(name = "nom", unique = true)
    private String nom;
    @Column(name = "numero", unique = true)
    private Long numero;
    @Column(name = "modeleCapteur", nullable = false)
    private String modeleCapteur;
    @Column(name = "dateUtilisation", nullable = false)

    private Date dateUtilisation;
    @Column(name = "Etat", nullable = false)
     private Boolean etat;
    private Float valeurCapteur;
    @Column(name = "dateExpiration", nullable = false)

    private Date dateExpiration;
    @ManyToOne(fetch = FetchType.EAGER)
    private Champ champ;
    @OneToMany( fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<History> histories=new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    private List<Notification> notifications = new ArrayList<>();

}
