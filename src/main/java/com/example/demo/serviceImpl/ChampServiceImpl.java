package com.example.demo.serviceImpl;

import com.example.demo.entites.Champ;
import com.example.demo.entites.User;
import com.example.demo.repositories.ChampRepository;
import com.example.demo.repositories.HistoryRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.ChampsService;
import com.example.demo.dto.ChampDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChampServiceImpl implements ChampsService {

    private  final ChampRepository champRepository;
    private final UserRepository userRepository;
    private final HistoryRepository historyRepository;

    @Override
    public ResponseEntity<?> save(ChampDto champDto) {
        Champ champ=new Champ(null,champDto.getNom(),champDto.getNumero(),champDto.getAdresse(),
                champDto.getDate_ajout(),userRepository.findByEmail(champDto.getUserEmail()).get(),null );
        return ResponseEntity.ok(champRepository.save(champ));
    }
    @Override
    public ResponseEntity<?> delete(Long id) {
        Optional<Champ> cham =champRepository.findById(id);

        if (cham.isPresent()){
            User u=userRepository.findById(cham.get().getUser().getId()).get();
            u.getChamp().remove(cham.get());
            userRepository.save(u);
            cham.get().setUser(null);
            champRepository.delete(cham.get());
            return ResponseEntity.ok("delete");
        }else return ResponseEntity.ok("no champs with this id");

    }
    @Override
    public ResponseEntity<?> findById(Long id) {
        Optional<Champ> champ=champRepository.findById(id);
        if (champ.isPresent()){
            return ResponseEntity.ok(champ.get());
        }
        return ResponseEntity.ok("champ Not Found");
    }
    @Override
    public ResponseEntity<?> findAllChamp() {
        return ResponseEntity.ok(champRepository.findAll());
    }

   @Override
    public ResponseEntity<?> modifier(Champ champ) {
        Optional<Champ> champ1= champRepository.findById(champ.getId());
        if(champ1.isPresent()){
            champRepository.save(champ);
            return ResponseEntity.ok(champ);
        }
        else {
            return ResponseEntity.ok("champ not found");
        }
    }

    @Override
    public ResponseEntity<?> getByUser(Long id) {
    Optional<User> mouwaten = userRepository.findById(id);
    if (mouwaten.isPresent()){
        return ResponseEntity.ok(champRepository.findAllByUser(mouwaten.get()));
    }else {
        return ResponseEntity.status(400).body("no user matching this call");
    }
    }

    @Override
    public ResponseEntity<?> getByUserEmail(String email) {
        Optional<User> user = this.userRepository.findByEmail(email);
        if (user.isPresent()){
            return ResponseEntity.ok(champRepository.findAllByUser(user.get()));
        }
        return ResponseEntity.ok("no user matching this call");
    }

}
