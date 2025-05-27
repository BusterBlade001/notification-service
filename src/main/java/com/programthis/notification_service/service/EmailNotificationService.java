package com.programthis.notification_service.service;

import com.programthis.notification_service.entity.NotificationLog;
import com.programthis.notification_service.repository.NotificationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

@Service
public class EmailNotificationService { 

    @Autowired
    private NotificationLogRepository notificationLogRepository;

    public static record NotificationRequest(String recipientEmail, String subject, String messageBody, String type) {}

    public void sendEmailNotification(NotificationRequest request) {
        NotificationLog log = new NotificationLog();
        log.setReceiver(request.recipientEmail());
        log.setType(request.type());
        log.setSubject(request.subject());
        log.setMessage(request.messageBody());
        log.setTimestamp(LocalDateTime.now());

        try {

            System.out.println("Simulando Email: " + request.recipientEmail());
            System.out.println("Sujeto: " + request.subject());
            System.out.println("cuerpo de texto: " + request.messageBody());

            log.setStatus("SENT");
        } catch (Exception e) {

            System.err.println("error de envio : " + e.getMessage());
            log.setStatus("Error");
        }
        notificationLogRepository.save(log);
    }
}