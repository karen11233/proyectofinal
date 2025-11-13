package com.mycompany.proyectofinal;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

public class ReportePDF {

    public static void generarReporteEstudiante(Estudiante estudiante, Map<String, Double> notasFinales) {
        try {
            String nombreArchivo = "Reporte_" + estudiante.getCodigo() + ".pdf";
            Document documento = new Document();
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();

            documento.add(new Paragraph("REPORTE ACADÉMICO DEL ESTUDIANTE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph("Nombre: " + estudiante.getNombre()));
            documento.add(new Paragraph("Código: " + estudiante.getCodigo()));
            documento.add(new Paragraph("Programa: " + estudiante.getPrograma()));
            documento.add(new Paragraph(" "));

            PdfPTable tabla = new PdfPTable(2);
            tabla.addCell("Asignatura");
            tabla.addCell("Nota Final");

            for (Map.Entry<String, Double> entry : notasFinales.entrySet()) {
                tabla.addCell(entry.getKey());
                tabla.addCell(String.format("%.2f", entry.getValue()));
            }

            documento.add(tabla);
            documento.close();

            System.out.println("📄 Reporte PDF generado correctamente: " + nombreArchivo);

        } catch (DocumentException | FileNotFoundException e) {
            System.out.println("❌ Error al generar el PDF: " + e.getMessage());
        }
    }
}

