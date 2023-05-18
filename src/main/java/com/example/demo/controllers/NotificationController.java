package com.example.demo.controllers;

import com.example.demo.dto.NotificationRequest;
import com.example.demo.dto.SendSmS;
import com.example.demo.entites.Notification;
import com.example.demo.repositories.CapteurRepository;
import com.example.demo.serviceImpl.EmailService;
import com.example.demo.serviceImpl.NotificationServiceImp;
import com.example.demo.serviceImpl.SmsService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Notification")
@Slf4j
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationServiceImp notificationServiceImp;


    @PostMapping("/processSMS")
    public ResponseEntity<?> processSMS(@RequestBody SendSmS sendSmS){
        return notificationServiceImp.processSMS(sendSmS);
    }
    @PostMapping("/email")
    public ResponseEntity<?> notfity(@RequestBody NotificationRequest request) throws MessagingException {
       return notificationServiceImp.notfity(request);
    }


}
