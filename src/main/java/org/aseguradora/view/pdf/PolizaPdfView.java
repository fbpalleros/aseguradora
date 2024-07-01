package org.aseguradora.view.pdf;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.aseguradora.entity.Policy;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

public class PolizaPdfView  {

    private Policy policy;

    public PolizaPdfView(Policy policy) {
        this.policy = policy;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setPadding(1);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("Cliente", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Póliza", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Tipo de seguro", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Fecha", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Vencimiento", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table){
        table.addCell(policy.getCustomer().getName());
        table.addCell(String.valueOf(policy.getId()));
        table.addCell(String.valueOf(policy.getInsurance()));
        table.addCell(String.valueOf(policy.getStartDate()));
        table.addCell(String.valueOf(policy.getExpiration()));

    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(18);

        Paragraph p = new Paragraph("POLIZA", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();
    }


}
