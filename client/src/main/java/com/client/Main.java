package com.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Socket s = new Socket("localhost", 3000);

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());

        boolean loop = true;
        
        System.out.println("GIOCO DELL'INDOVINA NUMERO");
        
        do {

            System.out.println("Inserisci il numero da indovinare");

            Scanner num = new Scanner(System.in);
                do {
                    try {
                        int numero = Integer.parseInt(num.nextLine());
                        if (numero > 0 && numero < 101) {
                            String numeroStr = Integer.toString(numero);
                            out.writeBytes(numeroStr + "\n");
                            break;
                        } else {
                            System.out.println("inserisci un numero da 1 a 100");
                        }
                    } catch (Exception e) {
                        System.out.println("Inserisci un numero e non una parola");
                    }
                } while (true);


            String numeroRicevuto = in.readLine();
            

            switch (numeroRicevuto) {
                case "<":
                    System.out.println("Il numero inserito e' troppo piccolo");
                    System.out.println("Il server ha risposto con <");
                    break;
            
                case ">":
                    System.out.println("Il numero inserito e' troppo grande");
                    System.out.println("Il server ha risposto con >");
                    break;
                
                case "=":
                    String numTentativi = in.readLine();
                    System.out.println("HAI INDOVINATO IL NUMERO IN: " + numTentativi + " tentativi");
                    System.out.println("Vuoi giocare un'altra partita? Inserisci SI/NO");
                    Scanner scan = new Scanner(System.in);
                    if(scan.nextLine().equals("NO")){
                        out.writeBytes(scan.nextLine() + "\n");
                        loop = false;
                    }
                    break;
            }
        }while (loop);
    }
}