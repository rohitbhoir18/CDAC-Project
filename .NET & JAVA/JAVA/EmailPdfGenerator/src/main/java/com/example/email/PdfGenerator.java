package com.example.email;

import com.example.email.model.InvoiceWrapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class PdfGenerator {

    public static byte[] generateInvoicePdf(InvoiceWrapper invoiceWrapper) throws Exception {
        if (invoiceWrapper == null || invoiceWrapper.getInvoiceHeader() == null) {
            throw new IllegalArgumentException("InvoiceWrapper or InvoiceHeader is null");
        }

        InvoiceWrapper.InvoiceHeader header = invoiceWrapper.getInvoiceHeader();
        List<InvoiceWrapper.InvoiceDetail> details = invoiceWrapper.getInvoiceDetails();

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);

        document.open();

        document.add(new Paragraph("Invoice", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
        document.add(new Paragraph("Customer: " + header.getCustDetails()));
        document.add(new Paragraph("Date: " + header.getInvDate()));
        document.add(new Paragraph("Quantity: " + header.getQuantity()));
        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(2);
        table.addCell("Component");
        table.addCell("Type");

        if (details != null) {
            for (InvoiceWrapper.InvoiceDetail item : details) {
                String compName = (item.getComponent() != null) ? item.getComponent().getCompName() : "Unknown Component";
                table.addCell(compName);
                table.addCell("Y".equalsIgnoreCase(item.getIsAlternate()) ? "Alternate" : "Default");
            }
        }

        document.add(table);
        document.add(Chunk.NEWLINE);
        document.add(new Paragraph("Subtotal: ₹" + header.getFinalAmount()));
        document.add(new Paragraph("GST: ₹" + header.getTax()));
        document.add(new Paragraph("Total: ₹" + header.getTotalAmount()));

        document.close();
        return out.toByteArray();
    }
}
