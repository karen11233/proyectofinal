package com.mycompany.proyectofinal;

import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    private String nombre;
    private String codigo;
    private String programa;
    private List<Asignatura> asignaturas = new ArrayList<>();

    public Estudiante(String nombre, String codigo, String programa) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.programa = programa;
    }

    public String getNombre() { return nombre; }
    public String getCodigo() { return codigo; }
    public String getPrograma() { return programa; }

    public void agregarAsignatura(Asignatura asignatura) {
        if (asignatura != null) {
            asignaturas.add(asignatura);
        }
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public double calcularPromedioGeneral() {
        if (asignaturas.isEmpty()) return 0;
        double total = 0;
        for (Asignatura a : asignaturas) {
            total += a.calcularNotaFinal();
        }
        return total / asignaturas.size();
    }
}
