package com.mycompany.proyectofinal;

public class Profesor {
    private String nombre;
    private String codigo;

    public Profesor(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public String getNombre() { return nombre; }
    public String getCodigo() { return codigo; }
}
