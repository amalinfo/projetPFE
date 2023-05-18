package com.example.demo.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Utilisateur")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    @Email
    private String email;
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "nom", unique = true)
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Name must contain only letters and spaces")
    private String nom;
    @Column(name = "prenom",nullable = false)
    private  String prenom;
    @Min(value = 20,message = "l'age doit etre sup a 20 ans")
    @Max(value = 62,message = "l'age doit etre inf a 62 ans")
    @Column(name = "age", nullable = false)
    private Integer age ;
    //@Column(name = "numeroCin", nullable = false)
   // private String  numCin;

    @Column(name = "telephone", nullable = false)
    @Pattern(regexp="^\\+216[2-9]\\d{7}$", message="Invalid phone number format")
    @Size(min=12, max=12, message="Phone number must be exactly 13 digits")
    private String numTel;
    @Column(name = "adress", nullable = false)
    private String adress;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade =CascadeType.PERSIST)
    @JsonIgnore
    private List<Champ> champ;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Token> tokens;
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
     return List.of(new SimpleGrantedAuthority(role.name()));}


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
