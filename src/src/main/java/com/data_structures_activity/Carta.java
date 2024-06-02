package com.data_structures_activity;

public class Carta {
    private String nombre;
    private int valor;
    private String palo;

    public Carta(String nombre, int valor, String palo){
        this.nombre = nombre;
        this.valor = valor;
        this.palo = palo;
    }

    public Carta(String nombre, String palo){
        this.nombre = nombre;
        this.palo = palo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getPalo() {
        return palo;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }

    @Override
    public String toString() {
        return "Carta{" +
                "nombre='" + nombre + '\'' +
                ", valor=" + valor +
                ", palo='" + palo + '\'' +
                '}';
    }
}
