package com.programthis.notification_service.service;

import com.programthis.notification_service.entity.NotificationLog;
import com.programthis.notification_service.repository.NotificationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// Para enviar emails, necesitarías JavaMailSender. Por ahora, solo simularemos y registraremos.
// import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.mail.SimpleMailMessage;

import java.time.LocalDateTime;

@Service
public class EmailNotificationService { // Podrías tener diferentes servicios para diferentes tipos de notificación (SMS, Push, etc.)

    @Autowired
    private NotificationLogRepository notificationLogRepository;

    // @Autowired
    // private JavaMailSender mailSender; // Descomentar si configuras spring-boot-starter-mail

    // Simulación de un DTO para la solicitud de notificación
    public static record NotificationRequest(String recipientEmail, String subject, String messageBody, String type) {}

    public void sendEmailNotification(NotificationRequest request) {
        NotificationLog log = new NotificationLog();
        log.setReceiver(request.recipientEmail());
        log.setType(request.type());
        log.setSubject(request.subject());
        log.setMessage(request.messageBody());
        log.setTimestamp(LocalDateTime.now());

        try {
            // Lógica para enviar el email usando JavaMailSender
            // SimpleMailMessage mailMessage = new SimpleMailMessage();
            // mailMessage.setTo(request.recipientEmail());
            // mailMessage.setSubject(request.subject());
            // mailMessage.setText(request.messageBody());
            // mailMessage.setFrom("noreply@ecomarket.com"); // Configurar remitente
            // mailSender.send(mailMessage);

            System.out.println("Simulating email sending to: " + request.recipientEmail());
            System.out.println("Subject: " + request.subject());
            System.out.println("Body: " + request.messageBody());

            log.setStatus("SENT");
        } catch (Exception e) {
            // Loggear el error específico
            System.err.println("Failed to send email: " + e.getMessage());
            log.setStatus("FAILED");
        }
        notificationLogRepository.save(log);
    }
}