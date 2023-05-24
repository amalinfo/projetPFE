package com.example.demo.utils;

import com.example.demo.dto.NotificationRequest;
import com.example.demo.entites.Capteur;
import com.example.demo.entites.History;
import com.example.demo.entites.User;
import com.example.demo.repositories.CapteurRepository;
import com.example.demo.repositories.HistoryRepository;
import com.example.demo.serviceImpl.NotificationServiceImp;
import com.example.demo.serviceImpl.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class MqttListner implements  Runnable{

    private final MqttClient mqttClient;
    private final SmsService service;
    private final HistoryRepository historyRepository;
    private final CapteurRepository capteurRepository;
    private final NotificationServiceImp notificationServiceImp ;
    @Override
    public void run() {
        while (true){
        try {
            mqttClient.subscribe("capteur/notification/test",(s, mqttMessage) -> {
                var id = Long.valueOf(new String(mqttMessage.getPayload()));
                Optional<Capteur> capteurI = capteurRepository.findById(id) ;
                if (capteurI.isPresent()){
                    Capteur capteur = capteurI.get();
                        capteur.setEtat(false);
                        capteurRepository.save(capteur);
                        History h = new History();
                        h.setCapteur(capteur);
                        h.setDate(new Date());
                        historyRepository.save(h);
                    notificationServiceImp.notfity(new NotificationRequest(capteurI.get().getChamp().getUserEmail(),
                            "alert "+capteurI.get().getChamp() + " le capteur "+capteurI.get().getNom(),
                            new String(mqttMessage.getPayload()),
                            id
                    ));
                  //  service.sendSMS(capteurI.get().getChamp().getUserPhone(),"alert captuer "+capteurI.get().getNom());

                }


            });
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }
    }
}
