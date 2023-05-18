package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationRequest {
    private String to ;

    private String subject;
    private String content;

    private Long idCapteur;
}
