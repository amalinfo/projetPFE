package com.example.demo.serviceImpl;

import com.example.demo.dto.HistoryDto;
import com.example.demo.entites.Capteur;
import com.example.demo.entites.History;
import com.example.demo.repositories.CapteurRepository;
import com.example.demo.repositories.HistoryRepository;
import com.example.demo.services.HistoryService;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository histpryRepository;
    private final CapteurRepository capteurRepository;

    @Override
    public ResponseEntity<?> save(HistoryDto histroyDto) {
        var Capteur=capteurRepository.findById(histroyDto.getIdCapteur()).orElse(null);
        if (Capteur!=null){
            History history=History.builder().capteur(Capteur)
            .build();
         return ResponseEntity.ok(histpryRepository.save(history));
        }
        return ResponseEntity.badRequest().body("capteur not found");
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        histpryRepository.deleteById(id);
        return ResponseEntity.ok("delete");
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        Optional<History> history=histpryRepository.findById(id);
        if (history.isPresent()){
            return ResponseEntity.ok(history.get());
        }
        return ResponseEntity.ok("champ Not Found");
    }

    @Override
    public ResponseEntity<?> getAll() {
      return   ResponseEntity.ok(
        histpryRepository.findAll());
    }

    @Override
    public ResponseEntity<?> generatedpdf(Long id) throws DocumentException {
        Optional<Capteur> capteur = capteurRepository.findById(id);
        if (capteur.isPresent()) {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            Document doc = new Document();
            PdfWriter.getInstance(doc, output);
            doc.open();
            Paragraph par = new Paragraph();
            List<History> historyList = histpryRepository.findAllByCapteur(capteur.get());
            if (historyList.size()!=0){

            historyList.forEach(e -> {
                par.add("nom capteur : "+ e.getCapteur().getNom()+ " ");
                par.add("date de notification  : "+e.getDate().toString() + " ");
                par.add("etat : "+e.getCapteur().getEtat()+ " " );
                par.add("user : "+e.getCapteur().getChamp().getUserEmail()+ " " );
                par.add("\n");
            });
            doc.add(par);
            doc.close();
            HttpHeaders http = new HttpHeaders();
            http.setContentType(MediaType.APPLICATION_PDF);
            return new ResponseEntity<>(output.toByteArray(), http, HttpStatus.OK);
        }
            else {
                par.add(capteur.get().getNom() + " have no history");
                doc.add(par);
                doc.close();
                HttpHeaders http = new HttpHeaders();
                http.setContentType(MediaType.APPLICATION_PDF);
                return new ResponseEntity<>(output.toByteArray(), http, HttpStatus.OK);
            }
        }else {
            return ResponseEntity.ok("no capteur match this call");
        }
        }
}
