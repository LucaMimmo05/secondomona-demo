package com.secondomona.service;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.secondomona.dto.PersonaDTO;

import jakarta.enterprise.context.ApplicationScoped;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ApplicationScoped
public class PdfService {

    /**
     * Genera un PDF con l'elenco delle persone presenti in azienda
     *
     * @param personePresenti Lista delle persone attualmente presenti in azienda
     * @return Array di byte contenente il PDF generato
     * @throws IOException Se si verifica un errore durante la generazione del PDF
     */
    public byte[] generaElencoPresenzePdf(List<PersonaDTO> personePresenti) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);

        // Imposta il font
        PdfFont font = PdfFontFactory.createFont();
        document.setFont(font);

        // Aggiungi titolo
        Paragraph titolo = new Paragraph("ELENCO PERSONE PRESENTI IN AZIENDA")
                .setFontSize(16)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20);
        document.add(titolo);

        // Aggiungi data e ora di generazione
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        Paragraph dataGenerazione = new Paragraph("Generato il: " + now.format(formatter))
                .setFontSize(10)
                .setTextAlignment(TextAlignment.RIGHT)
                .setMarginBottom(20);
        document.add(dataGenerazione);

        // Aggiungi nota per evacuazione
        Paragraph nota = new Paragraph("DOCUMENTO PER EVACUAZIONE DI EMERGENZA")
                .setFontSize(12)
                .setBold()
                .setFontColor(ColorConstants.RED)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20);
        document.add(nota);

        // Crea tabella
        Table table = new Table(UnitValue.createPercentArray(new float[]{5, 20, 20, 30, 25}))
                .setWidth(UnitValue.createPercentValue(100))
                .setMarginBottom(20);

        // Intestazione tabella
        addHeaderCell(table, "N°");
        addHeaderCell(table, "Nome");
        addHeaderCell(table, "Cognome");
        addHeaderCell(table, "Email");
        addHeaderCell(table, "Note");

        // Aggiungi righe con i dati delle persone
        int rowNum = 1;
        for (PersonaDTO persona : personePresenti) {
            addCell(table, String.valueOf(rowNum++));
            addCell(table, persona.getNome());
            addCell(table, persona.getCognome());
            addCell(table, persona.getMail());
            addCell(table, ""); // Colonna Note vuota, utilizzabile per eventuali annotazioni manuali
        }

        document.add(table);

        // Aggiungi piè di pagina
        Paragraph footer = new Paragraph("DOCUMENTO RISERVATO - Da utilizzare solo in caso di evacuazione")
                .setFontSize(8)
                .setTextAlignment(TextAlignment.CENTER);
        document.add(footer);

        document.close();
        return outputStream.toByteArray();
    }

    private void addHeaderCell(Table table, String text) {
        Cell cell = new Cell()
                .add(new Paragraph(text).setBold())
                .setBackgroundColor(new DeviceRgb(220, 220, 220))
                .setBorder(new SolidBorder(ColorConstants.BLACK, 1))
                .setTextAlignment(TextAlignment.CENTER)
                .setPadding(5);
        table.addHeaderCell(cell);
    }

    private void addCell(Table table, String text) {
        Cell cell = new Cell()
                .add(new Paragraph(text))
                .setBorder(new SolidBorder(ColorConstants.BLACK, 1))
                .setPadding(5);
        table.addCell(cell);
    }
}
