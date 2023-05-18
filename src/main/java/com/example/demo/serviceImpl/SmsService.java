package com.example.demo.serviceImpl;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsService {
final static String TWILIO_ACCOUNT_SID="AC57b455528517dd778037ca674ad04688";
final static String TWILIO_TOKEN="4c6569fdaf32dd1196370fc2616e55a4";

    public String sendSMS(String smsNumber, String smsMessage){
        Twilio.init(TWILIO_ACCOUNT_SID,TWILIO_TOKEN);
        Message.creator(new PhoneNumber("+21655800479"),new PhoneNumber("+15856011579"),"test").create();
    return "smsValide";
    }
}
