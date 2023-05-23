package com.example.demo.controllers;
import com.example.demo.dto.CapteurDto;
import com.example.demo.entites.Capteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.serviceImpl.CapteurServiceImpl;

@RestController
@RequestMapping("/Capteur")
@CrossOrigin
public class CapteurController {
    @Autowired
    private CapteurServiceImpl capteurService;
    @GetMapping("/lister")
    public ResponseEntity<?> findAllCapteur() {
        return capteurService.findAllCapteur();
    }
    @GetMapping("/getByOwnerId/{id}")
    public ResponseEntity<?> getByOwnerId(@PathVariable("id") Long id ){
        return  this.capteurService.findAllByOwnerId(id);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CapteurDto capteur) {
        return capteurService.save(capteur);
    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return capteurService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id ) {
        return capteurService.delete(id);
    }
   @PutMapping("/update/{id}") public ResponseEntity<?> update (@PathVariable Long id ,@RequestBody Capteur capteur){
  // capteur.setId(id);
        return capteurService.modifier(capteur,id);}
    @GetMapping("/getByChamp/{id}")
    public ResponseEntity<?> getByChamp(@PathVariable("id")Long id ){
        return capteurService.getByChamp(id);
    }
}
