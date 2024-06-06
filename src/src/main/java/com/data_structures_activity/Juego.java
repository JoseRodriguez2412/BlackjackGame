package com.data_structures_activity;

import java.util.*;

public class Juego {
    public static Stack<Carta> mazo = new Stack<>();
    public static Scanner teclado = new Scanner(System.in);

    public static void crearMazo(){
        String[] nombres = {"As", "Dos", "Tres", "Cuatro", "Cinco", "Seis", "Siete", "Ocho", "Nueve", "Diez", "Jota", "Reina", "Rey"};
        int[] valores = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] palos = {"Corazon", "Diamante", "Trebol", "Pica"};
        boolean esCartaValor10 = false;

        for(String palo : palos){
            for(int i = 0; i < nombres.length; i++){
                esCartaValor10 = nombres[i].equals("Diez") || nombres[i].equals("Jota") ||
                        nombres[i].equals("Reina") || nombres[i].equals("Rey");
                if(!esCartaValor10){
                    mazo.push(new Carta(nombres[i], valores[i], palo));
                } else{
                    mazo.push(new Carta(nombres[i], valores[valores.length -1], palo));
                }
            }
        }
    }

    public static void mezclarMazo(Stack<Carta> mazo){
        List<Carta> listaCartas = new ArrayList<>(mazo);
        Collections.shuffle(listaCartas);
        mazo.clear();
        mazo.addAll(listaCartas);
        /*for(Carta carta : mazo){
            System.out.println(carta);
        }*/
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

    public static boolean aplicarReglas(Stack<Carta> mano1, Stack<Carta> manoBanca, boolean jugadorSePlanto){
        //Obtener victoria
        boolean victoria = false;

        //Verificar si ha habido victoria por Blackjack
        boolean esBlackjack = victoriaPorBlacjack(mano1, manoBanca);

        //Si no hubo Blackjack, verificar quién ganó por puntos
        if(!esBlackjack){
            victoria = victoriaPorPuntos(mano1, manoBanca, jugadorSePlanto);
            return victoria;
        }else{
            return true;
        }
    }

    public static boolean victoriaPorBlacjack(Stack<Carta> mano1, Stack<Carta> manoBanca){
        boolean esBlackjack = false;
        boolean esEmpate = false;

        //Victoria directa para jugador (Blackjack)
        for (int i = 0; i < mano1.size()-1; i++) {
            esBlackjack = (mano1.get(i).getNombre().equals("As") && mano1.get(i+1).getValor() == 10)
                    || (mano1.get(i).getValor() == 10 && mano1.get(i+1).getNombre().equals("As"));
            if(esBlackjack){
                System.out.println("!BLACKJACK¡¡Haz ganado la partida!");
            }
        }

        //Derrota directa (Blackjack de la banca)
        if(!esBlackjack){
            for (int i = 0; i < manoBanca.size()-1; i++) {
                esBlackjack = (manoBanca.get(i).getNombre().equals("As") && manoBanca.get(i+1).getValor() == 10)
                        || (manoBanca.get(i).getValor() == 10 && manoBanca.get(i+1).getNombre().equals("As"));
                if(esBlackjack){
                    System.out.println("!BLACKJACK¡¡La banca ha ganado la partida!");
                }
            }
        } else{ //Empate de Blackjack con la banca
            for (int i = 0; i < manoBanca.size()-1; i++) {
                esEmpate = (manoBanca.get(i).getNombre().equals("As") && manoBanca.get(i+1).getValor() == 10)
                        || (manoBanca.get(i).getValor() == 10 && manoBanca.get(i+1).getNombre().equals("As"));
                if(esEmpate){
                    System.out.println("!BLACKJACK¡¡Empate!");
                }
            }
        }

        //Devolver resultados
        return esBlackjack;
    }

    public static boolean victoriaPorPuntos(Stack<Carta> mano1, Stack<Carta> manoBanca, boolean jugadorSePlanto){
        int puntuacionJugador = 0;
        int puntuacionBanca = 0;
        boolean victoriaJugador = false;
        boolean victoriaBanca = false;

        //Sumar puntos de la mano del jugador
        for(Carta carta : mano1){
            //Verificar si en la mano del jugador se encuentra un As
            if(carta.getNombre().equals("As")){
                System.out.println("Elija el valor para el As que más le convenga (1/11):");
                carta.setValor(teclado.nextInt());
            }
            puntuacionJugador += carta.getValor();
        }

        //Sumar puntos de la mano de la banca
        for(Carta carta : manoBanca){
            if(carta.getNombre().equals("As")){
                System.out.println("Elija el valor para el As que más le convenga (1/11):");
                carta.setValor(teclado.nextInt());
            }
            puntuacionBanca += carta.getValor();
        }

        victoriaJugador = (puntuacionJugador > puntuacionBanca && puntuacionJugador <= 21)
                || (puntuacionBanca > 21 && puntuacionJugador <= 21);

        victoriaBanca = (puntuacionBanca > puntuacionJugador && puntuacionBanca <= 21)
                || (puntuacionJugador > 21 && puntuacionBanca <= 21);
        //Victoria por puntos
        if(victoriaJugador){
            System.out.println("Puntos del jugador: "+puntuacionJugador);
            System.out.println("Puntos de la banca: "+puntuacionBanca);
            System.out.println("¡Haz ganado la partida!");
            if(jugadorSePlanto){
                return true;
            } else{
                return false;
            }
        } else if(victoriaBanca){
            //Derrota por puntos
            System.out.println("Puntos del jugador: "+puntuacionJugador);
            System.out.println("Puntos de la banca: "+puntuacionBanca);
            System.out.println("¡Haz perdido la partida! :(");
            return true;
        } else if(puntuacionJugador == puntuacionBanca && puntuacionJugador <= 21){
            //Empate con banca
            System.out.println("Puntos del jugador: "+puntuacionJugador);
            System.out.println("Puntos de la banca: "+puntuacionBanca);
            System.out.println("¡Empate!");
            return true;
        } else{
            return false;
        }
    }
}
