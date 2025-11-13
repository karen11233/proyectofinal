package com.mycompany.proyectofinal;

import java.util.*;

/**
 * Genera reportes estadísticos sobre los estudiantes y asignaturas.
 * 
 * Relaciones:
 *  - Asociación con: Estudiante, Asignatura, PagoMatricula.
 */
public class ReporteAcademico {

    /**
     * Calcula los mejores estudiantes (por promedio general).
     * 
     * @param estudiantes lista de estudiantes registrados
     * @return lista con los 3 mejores estudiantes (o menos si hay pocos)
     */
    public static List<Estudiante> mejoresEstudiantes(List<Estudiante> estudiantes) {
        if (estudiantes == null || estudiantes.isEmpty()) {
            System.out.println("⚠ No hay estudiantes registrados.");
            return new ArrayList<>();
        }

        // Crear una copia para evitar modificar la lista original
        List<Estudiante> copia = new ArrayList<>(estudiantes);

        // Ordenar por promedio descendente
        copia.sort((e1, e2) -> Double.compare(
                e2.calcularPromedioGeneral(),
                e1.calcularPromedioGeneral()
        ));

        // Retornar máximo 3 estudiantes
        return copia.subList(0, Math.min(3, copia.size()));
    }

    /**
     * Cuenta cuántos estudiantes están matriculados por curso/asignatura.
     * 
     * @param estudiantes lista de estudiantes
     * @return mapa con el nombre de la asignatura y la cantidad de estudiantes
     */
    public static Map<String, Integer> cantidadEstudiantesPorCurso(List<Estudiante> estudiantes) {
        Map<String, Integer> conteo = new HashMap<>();

        if (estudiantes == null || estudiantes.isEmpty()) {
            System.out.println("⚠ No hay estudiantes para analizar.");
            return conteo;
        }

        for (Estudiante e : estudiantes) {
            if (e.getAsignaturas() != null) {
                for (Asignatura a : e.getAsignaturas()) {
                    conteo.put(a.getNombre(), conteo.getOrDefault(a.getNombre(), 0) + 1);
                }
            }
        }

        return conteo;
    }

    /**
     * Genera un resumen de todos los pagos realizados por los estudiantes.
     * 
     * @param pagos lista de pagos de matrícula
     */
    public static void mostrarPagos(List<PagoMatricula> pagos) {
        System.out.println("\n=== REPORTE DE PAGOS DE MATRÍCULA ===");

        if (pagos == null || pagos.isEmpty()) {
            System.out.println("⚠ No hay pagos registrados.");
            return;
        }

        double total = 0;

        for (PagoMatricula p : pagos) {
            if (p.getEstudiante() != null) {
                System.out.printf(
                    "Estudiante: %-20s | Valor: $%.2f | Fecha: %s | Método: %s%n",
                    p.getEstudiante().getNombre(),
                    p.getValor(),
                    p.getFechaPago(),
                    p.getMetodoPago()
                );
                total += p.getValor();
            }
        }

        System.out.printf("\n💰 Total de ingresos por matrículas: $%.2f%n", total);
    }
}
