package com.example.demo.dto;

import lombok.Data;

@Data
public class SendSmS{
    private String distinationSMSNumber;
    private String smsMessage;
    private Long idCapteur ;
}
