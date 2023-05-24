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
import com.example.demo.utils.MqttListner;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Slf4j
@SpringBootApplication
public class ChampsApiApplication   {
	@Autowired
	MqttListner mqttListner;

	public static void main(String[] args) {
		SpringApplication.run(ChampsApiApplication.class, args);

	}
	@Bean
	public CommandLineRunner schedulingRunner(TaskExecutor executor) {
		return new CommandLineRunner() {
			public void run(String... args) throws Exception {
				executor.execute(mqttListner);
			}
		};
	}
	@Bean
	public CommandLineRunner loadData(UserRepository repository ) throws MqttException {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
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
