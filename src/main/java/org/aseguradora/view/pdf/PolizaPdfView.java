package org.aseguradora.view.pdf;

import java.awt.Color;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.aseguradora.entity.Policy;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PolizaPdfView {

    private Policy policy;

    public PolizaPdfView(Policy policy) {
        this.policy = policy;
    }

    private void writeTableHeader(PdfPTable table) {
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

    private void writeTableData(PdfPTable table) {
        table.addCell(policy.getCustomer().getName());
        table.addCell(String.valueOf(policy.getId()));
        table.addCell(String.valueOf(policy.getInsurance()));
        table.addCell(formatDate(policy.getStartDate()));
        table.addCell(formatDate(policy.getExpiration()));

    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Image img = Image.getInstance("src\\main\\webapp\\WEB-INF\\images\\vivirlogo.png");
        img.scaleAbsolute(100f, 100f);  // Adjust height and width as needed
        document.add(img);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(18);

        Paragraph p = new Paragraph("Poliza", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        Paragraph terms = new Paragraph("Consulte el documento de política adjunto para conocer los términos y condiciones completos.");
        document.add(terms);

        PdfPTable signatureTable = new PdfPTable(2);
        signatureTable.setSpacingBefore(20);
        signatureTable.setWidthPercentage(50f);
        PdfPCell insuredCell = new PdfPCell(new Phrase("Asegurado"));
        signatureTable.addCell(insuredCell);
        PdfPCell agentCell = new PdfPCell(new Phrase("Agente"));
        signatureTable.addCell(agentCell);
        document.add(signatureTable);

        PdfPTable signatureTable2 = new PdfPTable(2);
        signatureTable2.setSpacingAfter(10);
        signatureTable2.setWidthPercentage(50f);
        PdfPCell insuredCell2 = new PdfPCell(new Phrase(this.policy.getCustomer().getName()));
        signatureTable2.addCell(insuredCell2);
        PdfPCell agentCell2 = new PdfPCell(new Phrase("Vivir Seguros"));
        signatureTable2.addCell(agentCell2);
        document.add(signatureTable2);

        Paragraph termsTitle = new Paragraph("Términos y Condiciones Generales", FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD));
        termsTitle.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(termsTitle);

        Paragraph termsText = new Paragraph(getTermsAndConditions());
        termsText.setSpacingBefore(10f);
        termsText.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(termsText);

        document.close();
    }

    private String getTermsAndConditions() {
        return "Vivir Seguros\n" +
                "\n" +
                "1. Objeto\n" +
                "\n" +
                "El presente contrato de seguro (en adelante, la \"Póliza\") tiene por objeto regular las condiciones generales de las operaciones de seguro que Vivir Seguros S.A. (en adelante, la \"Aseguradora\") contrata con los tomadores de seguro (en adelante, los \"Tomados\").\n" +
                "\n" +
                "2. Vigencia\n" +
                "\n" +
                "La Póliza entrará en vigor en la fecha de su emisión y tendrá una vigencia de [especificar la duración de la póliza], pudiendo ser renovada por acuerdo expreso entre las partes.\n" +
                "\n" +
                "3. Prima\n" +
                "\n" +
                "El Tomador deberá abonar a la Aseguradora la prima correspondiente a la cobertura contratada, en la forma y plazos convenidos en la Póliza. El pago de la prima es condición indispensable para la vigencia de la cobertura.\n" +
                "\n" +
                "4. Siniestro\n" +
                "\n" +
                "Se entiende por siniestro el hecho generador del riesgo amparado por la Póliza. El Asegurado deberá comunicar a la Aseguradora el acaecimiento del siniestro dentro de los [especificar plazo] días de haberlo conocido. La Aseguradora procederá a la investigación del siniestro y, en caso de corresponder, al pago de la indemnización correspondiente.\n" +
                "\n" +
                "5. Excepciones a la cobertura\n" +
                "\n" +
                "La Póliza no ampara los siguientes riesgos:\n" +
                "\n" +
                "Exclusiones Generales:\n" +
                "\n" +
                "Guerra y terrorismo: Pérdidas o daños causados por guerra, guerra civil, actos de terrorismo o insurrección.\n" +
                "\n" +
                "Materiales nucleares, biológicos, químicos y peligrosos (NBQ): Pérdidas o daños causados por la liberación, dispersión o uso de NBQ.\n" +
                "\n" +
                "Fraude y tergiversación: Pérdidas o daños que resulten del fraude o tergiversación por parte del asegurado o un tercero.\n" +
                "\n" +
                "Actos intencionales: Pérdidas o daños causados intencionalmente por el asegurado.\n" +
                "\n" +
                "Desgaste y rotura: Pérdidas o daños que resulten del desgaste normal de un artículo asegurado.\n" +
                "\n" +
                "Avería mecánica: Pérdidas o daños que resulten de la avería o fallo mecánico de un artículo asegurado.\n" +
                "\n" +
                "Exclusiones Específicas por Tipo de Seguro:\n" +
                "\n" +
                "Seguro de Propiedad:\n" +
                "\n" +
                "Inundaciones: Pérdidas o daños causados por inundaciones.\n" +
                "\n" +
                "Terremotos: Pérdidas o daños causados por terremotos.\n" +
                "\n" +
                "Tormentas de viento: Pérdidas o daños causados por tormentas de viento.\n" +
                "\n" +
                "Robo: Pérdidas o daños causados por el robo de un artículo asegurado.\n" +
                "\n" +
                "Vandalismo: Pérdidas o daños causados por vandalismo.\n" +
                "\n" +
                "Seguro de Salud:\n" +
                "\n" +
                "Condiciones preexistentes: Condiciones médicas preexistentes que eran conocidas por el asegurado al momento de la emisión de la póliza.\n" +
                "\n" +
                "Procedimientos electivos: Procedimientos médicos electivos que no son médicamente necesarios.\n" +
                "\n" +
                "Tratamiento de salud mental: Tratamiento de salud mental, como terapia y asesoramiento.\n" +
                "\n" +
                "Cuidado dental: Cuidado dental, como limpiezas, empastes y coronas.\n" +
                "\n" +
                "Cuidado de la vista: Cuidado de la vista, como anteojos y lentes de contacto.\n" +
                "\n" +
                "Seguro de Vida:\n" +
                "\n" +
                "Suicidio: Muerte por suicidio dentro de un cierto período después de la emisión de la póliza.\n" +
                "\n" +
                "Actividades bélicas: Muerte como resultado de la participación en actividades bélicas.\n" +
                "\n" +
                "Actividades peligrosas: Muerte como resultado de la participación en actividades peligrosas, como paracaidismo o escalada en roca.\n" +
                "\n" +
                "Actos criminales: Muerte como resultado de la comisión de un delito.\n" +
                "\n" +
                "Consumo de alcohol o drogas: Muerte como resultado del consumo de alcohol o drogas.\n" +
                "\n" +
                "IMPORTANTE: Esta lista no es exhaustiva y puede haber otras exclusiones aplicables a su póliza específica. Lea cuidadosamente su póliza para comprender completamente las exclusiones de cobertura.\n" +
                "6. Cancelación de la Póliza\n" +
                "\n" +
                "La Aseguradora podrá cancelar la Póliza en caso de incumplimiento por parte del Tomador de sus obligaciones, o en caso de fraude o falsedad en la información proporcionada por el mismo.\n" +
                "\n" +
                "7. Jurisdicción\n" +
                "\n" +
                "Para cualquier controversia que surja de la presente Póliza, las partes se someten a la jurisdicción de los tribunales ordinarios de la Ciudad Autónoma de Buenos Aires.\n" +
                "\n" +
                "8. Normas aplicables\n" +
                "\n" +
                "La presente Póliza se rige por las disposiciones de la Ley de Seguros (Ley 17.418) y sus reglamentaciones.\n" +
                "\n" +
                "Anexos\n" +
                "\n" +
                "La Póliza podrá incluir anexos que complementen o modifiquen las condiciones generales establecidas en el presente documento.\n" +
                "\n" +
                "IMPORTANTE\n" +
                "\n" +
                "Los presentes términos y condiciones generales son un resumen de las condiciones de la Póliza. Se recomienda al Tomador leer cuidadosamente la Póliza completa antes de su contratación.\n" +
                "\n" +
                "Vivir Seguros S.A.";
    }
    private String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

}
