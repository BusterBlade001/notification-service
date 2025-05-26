package com.programthis.notification_service.controller;
import com.programthis.notification_service.service.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    @Autowired
    private EmailNotificationService emailNotificationService;

    @PostMapping("/email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailNotificationService.NotificationRequest notificationRequest) {
        try {
            emailNotificationService.sendEmailNotification(notificationRequest);
            return ResponseEntity.ok("Notification request processed.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error processing notification: " + e.getMessage());
        }
    }
}
