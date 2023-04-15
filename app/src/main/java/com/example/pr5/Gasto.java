package com.example.pr5;

public class Gasto {
    private String fecha;
    private double valor;
    private String tipo;

    public Gasto(String fecha, double valor, String tipo) {
        this.fecha = fecha;
        this.valor = valor;
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
