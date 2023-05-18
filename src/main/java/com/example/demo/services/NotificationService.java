package com.example.demo.services;

import com.example.demo.entites.Notification;
import org.springframework.http.ResponseEntity;

public interface NotificationService {
    ResponseEntity<?> save(Notification notification);
    ResponseEntity<?> getAll();
    ResponseEntity<?>delete(Long id);
}
