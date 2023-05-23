package com.example.demo.controllers;


import com.example.demo.entites.Champ;
import com.example.demo.services.ChampsService;
import com.example.demo.dto.ChampDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Champs")
@CrossOrigin
public class ChampController {
    @Autowired
    private ChampsService champsService;

    @GetMapping("/lister")
    public ResponseEntity<?>findAllChamps() {
        return champsService.findAllChamp();
        }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ChampDto champ) {
        return champsService.save(champ);
    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return champsService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id ) {
        return champsService.delete(id);
    }
   @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Champ champ){
        return  champsService.modifier(champ);}
    @GetMapping("/getByUser/{id}")
    public ResponseEntity<?> getByUser(@PathVariable("id")Long id ){
        return champsService.getByUser(id);
    }

}
