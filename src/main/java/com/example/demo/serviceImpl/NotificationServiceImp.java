package com.example.demo.serviceImpl;

import com.example.demo.dto.NotificationRequest;
import com.example.demo.dto.SendSmS;
import com.example.demo.entites.Notification;
import com.example.demo.repositories.CapteurRepository;
import com.example.demo.repositories.NotificationRepository;
import com.example.demo.services.NotificationService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationServiceImp implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final  SmsService service;
    private final EmailService emailService;

    private final CapteurRepository capteurRepository;


    public ResponseEntity<?> processSMS( SendSmS sendSmS){

        var n =  notificationRepository.save(
                Notification
                        .builder()
                        .capteur(
                                capteurRepository.findById(sendSmS.getIdCapteur()).orElseThrow()
                        ).build()
        );
        service.sendSMS(sendSmS.getDistinationSMSNumber(),sendSmS.getSmsMessage());
        return  ResponseEntity.ok(n);
    }

    public ResponseEntity<?> notfity( NotificationRequest request) throws MessagingException {
        var n= notificationRepository.save(
                Notification
                        .builder()
                        .capteur(
                                capteurRepository.findById(request.getIdCapteur()).orElse(null)
                        ).build()
        );
        emailService.sendMailwithtml(request.getTo(), request.getSubject() , request.getContent());
        service.sendSMS("55800479","testing both notifications");
        return ResponseEntity.ok(n);
    }
    @Override
    public ResponseEntity<?> save(Notification notification) {
        return ResponseEntity.ok(notificationRepository.save(notification));
    }

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(notificationRepository.findAll());
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Optional<Notification> notification = this.notificationRepository.findById(id);
        if(notification.isPresent()){
            this.notificationRepository.delete(notification.get());

            return ResponseEntity.ok("deleted");
        }
        return ResponseEntity.ok("notifcation not found ");
    }
}
