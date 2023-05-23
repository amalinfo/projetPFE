package com.example.demo;


import com.example.demo.dto.NotificationRequest;
import com.example.demo.entites.Capteur;
import com.example.demo.entites.History;
import com.example.demo.entites.Role;
import com.example.demo.entites.User;
import com.example.demo.repositories.CapteurRepository;
import com.example.demo.repositories.HistoryRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.serviceImpl.NotificationServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Slf4j
@SpringBootApplication
public class ChampsApiApplication   {

	public static void main(String[] args) {
		SpringApplication.run(ChampsApiApplication.class, args);

	}
	@Bean
	public CommandLineRunner loadData(UserRepository repository , MqttClient mqttClient,
									  HistoryRepository historyRepository,
									  CapteurRepository capteurRepository,
									  NotificationServiceImp notificationServiceImp) throws MqttException {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	mqttClient.subscribe("capteur/notification/test",(s, mqttMessage) -> {
		log.info("notification {}",new String(mqttMessage.getPayload()) );
		notificationServiceImp.notfity(new NotificationRequest("nasramal76@gmail.com",
				"notification",
				new String(mqttMessage.getPayload()),
				1L
				));
		var idC = Long.valueOf(new String(mqttMessage.getPayload()));
		log.info("capteur id = {}", idC);
		Optional<Capteur> c = capteurRepository.findById(idC);
		if (c.isPresent()){
			Capteur capteur = c.get();
			capteur.setEtat(false);
			capteurRepository.save(capteur);
			History h = new History();
			h.setCapteur(capteur);
			h.setDate(new Date());
			historyRepository.save(h);
		}else {
			log.error("capteur me fammesh ");
		}
	});
		return (args) -> {

			// save a couple of data
			User user = repository.save(new User(1L,
					"admin@admin.com",
					encoder.encode("admin"),
					"admin",
					"admin",
					22,
					"+21655800479",
					"darhom",
					null,
					Role.ADMIN,
					null
			));
		};

	}

}
