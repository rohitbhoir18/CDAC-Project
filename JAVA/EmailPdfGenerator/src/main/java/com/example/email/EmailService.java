package com.example.email;

import com.example.email.model.InvoiceWrapper;
import com.example.email.model.InvoiceWrapper.InvoiceHeader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendInvoiceEmail(InvoiceWrapper invoiceWrapper) throws Exception {
        if (invoiceWrapper == null || invoiceWrapper.getInvoiceHeader() == null) {
            throw new IllegalArgumentException("InvoiceWrapper or InvoiceHeader is null");
        }

        InvoiceHeader header = invoiceWrapper.getInvoiceHeader();

        // Generate PDF bytes for the invoice
        byte[] pdfBytes = PdfGenerator.generateInvoicePdf(invoiceWrapper);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true); // true = multipart

        // Extract email from customer details string
        String recipientEmail = extractEmailFromCustDetails(header.getCustDetails());

        if (recipientEmail == null) {
            throw new IllegalArgumentException("Email not found in customer details");
        }

        helper.setTo(recipientEmail);
        helper.setFrom("omdeepakgarg10@gmail.com"); // Your verified sender email
        helper.setSubject("Your Car Invoice - #" + header.getInvId());
        helper.setText("Dear Customer,\n\nPlease find attached your invoice.\n\nThank you!");

        // Attach the PDF invoice
        helper.addAttachment("Invoice_" + header.getInvId() + ".pdf", new ByteArrayResource(pdfBytes));

        // Send the email
        mailSender.send(message);
    }

    private String extractEmailFromCustDetails(String custDetails) {
        if (custDetails == null) {
            throw new IllegalArgumentException("Customer details are null");
        }
        
        // Simple regex pattern to find email in string
        Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}");
        Matcher matcher = emailPattern.matcher(custDetails);
        if (matcher.find()) {
            return matcher.group();
        }
        
        return null;
    }
}
