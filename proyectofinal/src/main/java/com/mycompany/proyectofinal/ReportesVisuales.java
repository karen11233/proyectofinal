package com.mycompany.proyectofinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Clase ReportesVisuales
 * 
 * Esta clase tiene la responsabilidad de generar reportes visuales del desempeño académico.
 * 
 * Permite:
 *  - Mostrar en consola un reporte con las notas finales.
 *  - Generar un gráfico textual tipo barra.
 *  - Guardar el reporte completo en un archivo de texto (.txt).
 * 
 * Relaciones UML:
 *  - ASOCIACIÓN: con las clases Estudiante y Profesor (usa sus objetos).
 *  - AGREGACIÓN: con Map<String, Double> (usa las notas finales de asignaturas).
 *  - COMPOSICIÓN: no aplica aquí directamente; la composición se da dentro de Asignatura → Evaluacion.
 * 
 * Autor: Karen Torres
 * Fecha: 05/11/2025
 */
public class ReportesVisuales {

    /**
     * CONSTRUCTOR por defecto.
     * 
     * No recibe parámetros, ya que esta clase actúa como una herramienta de generación
     * de reportes (sin mantener estado interno).
     */
    public ReportesVisuales() { // ← CONSTRUCTOR
        // Constructor vacío. Puede ampliarse si se desea configurar opciones de salida.
    }

    /**
     * Método para imprimir el reporte visual en consola.
     * 
     * Muestra:
     *  - Datos del estudiante y profesor.
     *  - Tabla de asignaturas y notas finales.
     *  - Gráfico textual tipo barra ("█").
     * 
     * Relación:
     *  - ASOCIACIÓN con Estudiante y Profesor.
     *  - AGREGACIÓN con Map<String, Double> (notas finales).
     * 
     * @param estudiante   objeto de la clase Estudiante
     * @param profesor     objeto de la clase Profesor
     * @param notasFinales mapa con nombre de asignatura y nota final
     */
    public void imprimirReporte(Estudiante estudiante, Profesor profesor, Map<String, Double> notasFinales) {
        System.out.println("\n==========================================");
        System.out.println("          REPORTE ACADÉMICO VISUAL        ");
        System.out.println("==========================================");
        System.out.println("Estudiante: " + estudiante.getNombre() + "  |  Código: " + estudiante.getCodigo());
        System.out.println("Programa  : " + estudiante.getPrograma());
        System.out.println("Profesor  : " + (profesor != null ? profesor.getNombre() : "Sin profesor"));
        System.out.println("------------------------------------------");
        System.out.printf("%-4s %-25s %-10s%n", "No.", "Asignatura", "Nota final");
        System.out.println("------------------------------------------");

        int contador = 1;
        for (Map.Entry<String, Double> entry : notasFinales.entrySet()) {
            System.out.printf("%-4d %-25s %-10.2f%n", contador++, entry.getKey(), entry.getValue());
        }

        System.out.println("\n--- GRÁFICO TEXTUAL (cada '█' ≈ 0.1 punto) ---");
        imprimirGraficoTextual(notasFinales);

        System.out.println("\n==========================================\n");
    }

    /**
     * Método auxiliar para imprimir un gráfico textual en consola.
     * 
     * Cada bloque “█” representa aproximadamente 0.1 puntos en la nota.
     * 
     * @param notasFinales mapa con asignatura y nota final
     */
    private void imprimirGraficoTextual(Map<String, Double> notasFinales) {
        for (Map.Entry<String, Double> entry : notasFinales.entrySet()) {
            int cantidadBarras = (int) (entry.getValue() * 10);
            String barras = "█".repeat(cantidadBarras);
            System.out.printf("%-25s | %s (%.2f)%n", entry.getKey(), barras, entry.getValue());
        }
    }

    /**
     * Genera una cadena con el contenido completo del reporte,
     * útil para guardar en un archivo.
     * 
     * @param estudiante   objeto de la clase Estudiante
     * @param profesor     objeto de la clase Profesor
     * @param notasFinales mapa con asignaturas y notas finales
     * @return contenido completo del reporte como texto
     */
    public String generarContenidoReporte(Estudiante estudiante, Profesor profesor, Map<String, Double> notasFinales) {
        StringBuilder sb = new StringBuilder();

        sb.append("==========================================\n");
        sb.append("          REPORTE ACADÉMICO VISUAL        \n");
        sb.append("==========================================\n");
        sb.append("Estudiante: ").append(estudiante.getNombre()).append("  |  Código: ").append(estudiante.getCodigo()).append("\n");
        sb.append("Programa  : ").append(estudiante.getPrograma()).append("\n");
        sb.append("Profesor  : ").append(profesor != null ? profesor.getNombre() : "Sin profesor").append("\n");
        sb.append("------------------------------------------\n");
        sb.append(String.format("%-4s %-25s %-10s%n", "No.", "Asignatura", "Nota final"));
        sb.append("------------------------------------------\n");

        int contador = 1;
        for (Map.Entry<String, Double> entry : notasFinales.entrySet()) {
            sb.append(String.format("%-4d %-25s %-10.2f%n", contador++, entry.getKey(), entry.getValue()));
        }

        sb.append("\n--- GRÁFICO TEXTUAL (cada '█' ≈ 0.1 punto) ---\n");
        for (Map.Entry<String, Double> entry : notasFinales.entrySet()) {
            int cantidadBarras = (int) (entry.getValue() * 10);
            String barras = "█".repeat(cantidadBarras);
            sb.append(String.format("%-25s | %s (%.2f)%n", entry.getKey(), barras, entry.getValue()));
        }

        sb.append("\n==========================================\n");
        return sb.toString();
    }

    /**
     * Guarda el reporte generado en un archivo de texto (.txt).
     * 
     * Relación:
     *  - ASOCIACIÓN: usa Estudiante y Profesor.
     *  - AGREGACIÓN: usa los datos del mapa (notas finales).
     * 
     * @param rutaArchivo  nombre o ruta del archivo destino
     * @param estudiante   objeto de la clase Estudiante
     * @param profesor     objeto de la clase Profesor
     * @param notasFinales mapa con nombre de asignatura y nota final
     * @return true si el archivo se guarda correctamente, false si ocurre un error
     */
    public boolean guardarReporteTxt(String rutaArchivo, Estudiante estudiante, Profesor profesor, Map<String, Double> notasFinales) {
        String contenido = generarContenidoReporte(estudiante, profesor, notasFinales);
        File archivo = new File(rutaArchivo);

        try (PrintWriter writer = new PrintWriter(archivo)) {
            writer.print(contenido);
            return true;
        } catch (FileNotFoundException e) {
            System.err.println("❌ Error al guardar el archivo: " + e.getMessage());
            return false;
        }
    }
}


