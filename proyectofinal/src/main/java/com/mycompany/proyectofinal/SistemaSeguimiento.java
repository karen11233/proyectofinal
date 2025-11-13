package com.mycompany.proyectofinal;

import java.util.ArrayList;
import java.util.List;

public class Asignatura {
    private String nombre;
    private String codigo;
    private int creditos;
    private Profesor profesor;
    private List<Evaluacion> evaluaciones = new ArrayList<>();

    public Asignatura(String nombre, String codigo, int creditos, Profesor profesor) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.creditos = creditos;
        this.profesor = profesor;
    }

    public String getNombre() { return nombre; }

    public void agregarEvaluacion(Evaluacion eval) {
        if (eval != null) {
            evaluaciones.add(eval);
        }
    }

    public double calcularNotaFinal() {
        double suma = 0;
        double totalPorcentaje = 0;

        for (Evaluacion e : evaluaciones) {
            suma += e.getNota() * (e.getPorcentaje() / 100.0);
            totalPorcentaje += e.getPorcentaje();
        }

        // Ajuste si los porcentajes no suman 100
        if (totalPorcentaje > 0 && totalPorcentaje != 100) {
            suma = suma * (100 / totalPorcentaje);
        }

        return suma;
    }
}
