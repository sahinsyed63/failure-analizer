package com.example.failure_analizer.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


import org.springframework.stereotype.Component;

import com.example.failure_analizer.entity.Failure;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import java.util.List;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component
public class ReportGenerator {
	
	public ByteArrayInputStream generatePdf(List<Failure> failure) {
		
		Document document = new Document(); 
		ByteArrayOutputStream out = new  ByteArrayOutputStream();
		
		PdfWriter.getInstance(document,out);
		document.open();
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
		
		Paragraph para = new Paragraph("Failure Report", font);
        para.setAlignment(Element.ALIGN_CENTER);
        document.add(para);
        document.add(Chunk.NEWLINE);
        
        
        PdfPTable table = new PdfPTable(6); // number of columns
        table.setWidthPercentage(100);
        table.setWidths(new int[]{2, 3, 3, 3, 3, 2});

        // Add Table Headers
        table.addCell("Title");
        table.addCell("Description");
        table.addCell("Root Cause");
        table.addCell("Findings");
        table.addCell("Lesson Learned");
        table.addCell("Date Reported");

        for (Failure failures : failure) {
            table.addCell(failures.getTitle());
            table.addCell(failures.getDescription());
            table.addCell(failures.getRootCause());
            table.addCell(failures.getFindings());
            table.addCell(failures.getLessonLearned());
            table.addCell(failures.getDateReported().toString());
        }

        document.add(table);
        
        
        document.close();
		
        return new ByteArrayInputStream(out.toByteArray());
		
		
	}

}
