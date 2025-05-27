package com.programthis.notification_service.controller; // O tu paquete real

import com.programthis.notification_service.entity.NotificationLog;
import com.programthis.notification_service.service.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications") // Ruta base que ya tenías
public class NotificationController {

    @Autowired
    private EmailNotificationService emailNotificationService;

    // Tu endpoint POST para enviar notificaciones (este ya te funciona)
    @PostMapping("/email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailNotificationService.NotificationRequest notificationRequest) {
        try {
            emailNotificationService.sendEmailNotification(notificationRequest);
            return ResponseEntity.ok("Notification request processed.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error processing notification: " + e.getMessage());
        }
    }

    @GetMapping("/logs/recipient/{email}") 
    public ResponseEntity<List<NotificationLog>> getLogsByEmail(@PathVariable String email) {
        List<NotificationLog> logs = emailNotificationService.getLogsByReceiver(email); // Llama al método del servicio
        if (logs.isEmpty()) {
            return ResponseEntity.notFound().build(); // Opcional: devolver 404 si no hay logs
        }
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/logs/type/{type}") 
    public ResponseEntity<List<NotificationLog>> getLogsByType(@PathVariable String type) {
        List<NotificationLog> logs = emailNotificationService.getLogsByType(type); // Llama al método del servicio
        if (logs.isEmpty()) {
            return ResponseEntity.notFound().build(); // Opcional
        }
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/logs") 
    public ResponseEntity<List<NotificationLog>> getAllLogs() {
        List<NotificationLog> logs = emailNotificationService.getAllLogs();
        return ResponseEntity.ok(logs);
    }
   
}