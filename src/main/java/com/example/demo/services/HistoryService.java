package com.example.demo.services;

import com.example.demo.dto.CapteurDto;
import com.example.demo.dto.HistoryDto;
import com.example.demo.exceptions.CapteurNotFoundExcep;
import com.lowagie.text.DocumentException;
import org.springframework.http.ResponseEntity;

public interface HistoryService {
    ResponseEntity<?> save(HistoryDto histroyDto);
    ResponseEntity<?> delete(Long id) ;
    ResponseEntity<?> findById(Long id);
    ResponseEntity<?> getAll();
    ResponseEntity<?> generatedpdf() throws DocumentException;
}
