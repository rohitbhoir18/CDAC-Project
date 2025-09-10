package com.example.email;

import com.example.email.model.InvoiceWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins = "http://localhost:5173")  // Adjust this to your frontend origin
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendInvoice(@RequestBody InvoiceWrapper invoice) {
        try {
            emailService.sendInvoiceEmail(invoice);
            return "Email sent successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to send email: " + e.getMessage();
        }
    }
}
