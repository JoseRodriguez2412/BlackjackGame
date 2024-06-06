package com.data_structures_activity;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Juego juego = new Juego();
        Stack<Carta> jugador1 = new Stack<>();
        Stack<Carta> banca = new Stack<>();
        boolean plantar = false;
        boolean victoria = false;
        Scanner teclado = new Scanner(System.in);
        String respuesta;

        juego.crearMazo();
        juego.mezclarMazo(juego.mazo);
        jugador1 = juego.repartirCartas();
        banca = juego.repartirCartas();

        while(!plantar && !victoria){
            System.out.println("¿Deseas pedir una carta?");

            if(teclado.hasNextLine()){
                respuesta = teclado.nextLine();
                if(respuesta.equalsIgnoreCase("si")){
                    jugador1.push(juego.pedirCarta());
                    banca.push(juego.pedirCarta());
                } else if(respuesta.equalsIgnoreCase("no")){
                    plantar = true;
                } else{
                    System.out.println("Por favor, ingresa SI/NO");
                }
            } else{
                System.out.println("Por favor, ingresa SI/NO");
            }
            System.out.println(jugador1);
            System.out.println(banca);
            victoria = juego.aplicarReglas(jugador1, banca, plantar);
        }
    }
}