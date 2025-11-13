package com.mycompany.proyectofinal;

public class Evaluacion {
    private String nombre;
    private double nota;
    private double porcentaje;

    public Evaluacion(String nombre, double nota, double porcentaje) {
        this.nombre = nombre;
        this.nota = nota;
        this.porcentaje = porcentaje;
    }

    public String getNombre() { return nombre; }
    public double getNota() { return nota; }
    public double getPorcentaje() { return porcentaje; }
}
