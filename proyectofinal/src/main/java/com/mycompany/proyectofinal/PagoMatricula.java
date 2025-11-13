package com.mycompany.proyectofinal;

import java.time.LocalDate;

/**
 * Representa el pago de matrícula de un estudiante.
 * Cada estudiante puede tener uno o varios pagos registrados.
 */
public class PagoMatricula {
    private String codigoPago;
    private double valor;
    private LocalDate fechaPago;
    private String metodoPago; // Efectivo, tarjeta, transferencia
    private Estudiante estudiante; // Asociación con estudiante

    public PagoMatricula(String codigoPago, double valor, LocalDate fechaPago, String metodoPago, Estudiante estudiante) {
        this.codigoPago = codigoPago;
        this.valor = valor;
        this.fechaPago = fechaPago;
        this.metodoPago = metodoPago;
        this.estudiante = estudiante;
    }

    public String getCodigoPago() {
        return codigoPago;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }
}
