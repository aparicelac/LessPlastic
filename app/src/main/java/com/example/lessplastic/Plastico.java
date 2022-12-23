package com.example.lessplastic;

public class Plastico {

    private int id;
    private String tipo;
    private int cantidad;
    private String tamaño;
    private int peso;

    public Plastico(int id, String tipo, int cantidad, String tamaño, int peso) {
        this.id = id;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.tamaño = tamaño;
        this.peso = peso;
    }

    public int getId() {
        return id;
    }

    public void setId(int nuevoId) {
        this.id = nuevoId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String nuevoTipo) {
        this.tipo = nuevoTipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int nuevaCantidad) {
        this.cantidad = nuevaCantidad;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String nuevoTamaño) {
        this.tamaño = nuevoTamaño;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int nuevoPeso) {
        this.peso = nuevoPeso;
    }

    @Override
    public String toString() {
        return "Plastico{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", cantidad='" + cantidad + '\'' +
                ", tamaño='" + tamaño + '\'' +
                ", peso='" + peso + '\'' +
                '}';
    }
}