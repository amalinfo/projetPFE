package com.example.demo.controllers;

import com.example.demo.dto.HistoryDto;
import com.example.demo.services.HistoryService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/History")
@CrossOrigin
public class HistoryController {
    @Autowired
    private HistoryService service;
@PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody HistoryDto histroyDto) {
        return service.save(histroyDto);
    }
@DeleteMapping("/delete")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return service.delete(id);
    }
@GetMapping("/gett{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return service.findById(id);
    }
@GetMapping("/get")
    public ResponseEntity<?> getAll() {
        return service.getAll();
    }
    @GetMapping("/pdf")
    public ResponseEntity<?> generatepdf(Long capteurId) throws DocumentException {
    return service.generatedpdf();
    }

}
