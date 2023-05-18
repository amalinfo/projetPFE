package com.example.demo.serviceImpl;

import com.example.demo.entites.Capteur;
import com.example.demo.entites.Champ;
import com.example.demo.entites.User;
import com.example.demo.repositories.CapteurRepository;
import com.example.demo.repositories.ChampRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.CapteurService;
import com.example.demo.dto.CapteurDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@RequiredArgsConstructor
@Slf4j
public class CapteurServiceImpl implements CapteurService {


    private final CapteurRepository capteurRepository;
    private final ChampRepository champRepository;
    private final EmailService emailService;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<?> save(CapteurDto capteurDto) {
        Capteur capteur=new Capteur(null,capteurDto.getNom(),capteurDto.getNumero(),capteurDto.getModelecapteur(),
                capteurDto.getDateUtilisation(),capteurDto.getEtat(),capteurDto.getVal(),
                capteurDto.getDateExpiration(),champRepository.findById(capteurDto.getIdchamp()).get(),null,null);
        return  ResponseEntity.ok(capteurRepository.save(capteur));
    }
@Transactional
    @Override
    public ResponseEntity<?> delete(Long id){
        Capteur C= capteurRepository.findById(id).get();
    Champ champp= champRepository.findById(C.getChamp().getId()).get();
    champp.getCapteur().remove(C);
    champRepository.save(champp); 
       capteurRepository.delete(C);
        return ResponseEntity.ok("delete");
    }

    @Override
    public ResponseEntity<?> findById(Long id){
        Optional<Capteur> capteur=capteurRepository.findById(id);
        if (capteur.isPresent()){
            return ResponseEntity.ok(capteur.get());
        }
        return ResponseEntity.ok("capteur Not Found");
    }

    @Override
    public ResponseEntity<?> findAllCapteur() {
        return ResponseEntity.ok(capteurRepository.findAll());
    }
    @Override
    public ResponseEntity<?> modifier(Capteur capteur, Long id) {
  Capteur capteur1 = capteurRepository.findById(id).get();
        if (capteur1!=null) {
      /* if(!capteur.getEtat()){
           //log.info("capteur {}",capteur1.getChamp().getUser().getEmail());
           emailService.sendMail(capteur1.getChamp().getUser().getEmail(),"l'état de capteur rouge","capteur "+capteur.getNom()+" etat rouge");
            }*/
           capteur1.setNom(capteur.getNom());
           capteur1.setModeleCapteur(capteur.getModeleCapteur());
           capteur1.setEtat(capteur.getEtat());
           capteur1.setDateExpiration(capteur.getDateExpiration());
           capteur1.setDateUtilisation(capteur.getDateUtilisation());
           capteur1.setNumero(capteur.getNumero());
            capteurRepository.save(capteur1);
            return ResponseEntity.ok(capteur);
        } else {
            return ResponseEntity.ok("capteur not found");
        }
    }

    @Override
    public ResponseEntity<?> getByChamp(Long id) {
        Optional<Champ> maken = champRepository.findById(id);
        if (maken.isPresent()){
            return ResponseEntity.ok(capteurRepository.findAllByChamp(maken.get()));

        }else {
            return ResponseEntity.status(400).body("no champ matching this call");
        }
    }




}

