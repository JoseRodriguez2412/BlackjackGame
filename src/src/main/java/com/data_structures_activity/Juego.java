package com.data_structures_activity;

import java.util.*;

public class Juego {
    public static Stack<Carta> mazo = new Stack<>();

    public static void crearMazo(){
        String[] nombres = {"As", "Dos", "Tres", "Cuatro", "Cinco", "Seis", "Siete", "Ocho", "Nueve", "Diez", "Jota", "Reina", "Rey"};
        int[] valores = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        String[] palos = {"Corazon", "Diamante", "Trebol", "Pica"};

        for(String palo : palos){
            for(String nombre : nombres){
                mazo.push(new Carta(nombre, palo));
            }
        }
    }

    public static void mezclarMazo(Stack<Carta> mazo){
        List<Carta> listaCartas = new ArrayList<>(mazo);
        Collections.shuffle(listaCartas);
        mazo.clear();
        mazo.addAll(listaCartas);
    }

    public static Stack<Carta> repartirCartas(){
        Stack<Carta> manoJugador = new Stack<>();
        for (int i = 0; i < 2; i++) {
            manoJugador.push(mazo.peek());
            mazo.pop();
        }
        System.out.println(manoJugador);
        return manoJugador;
    }

    public static Carta pedirCarta(){
        Carta nuevaCarta = mazo.peek();
        mazo.pop();
        return nuevaCarta;
    }

    public static void aplicarReglas(){

    }
}
