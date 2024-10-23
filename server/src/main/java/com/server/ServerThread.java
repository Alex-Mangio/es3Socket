package com.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class ServerThread extends Thread {

    Socket s;

    public ServerThread(Socket s) {
        this.s = s;
    }

    public void run() {

        try {

            boolean loopPartita = true;

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            System.out.println("Un client si e' connesso");

            do {

                System.out.println("Una nuova partita e' iniziata");

                Random rand = new Random();
                int numRand = rand.nextInt(100);

                int tentativi = 1;

                do {

                    String numeroRicevuto = in.readLine();

                    if (numeroRicevuto.equals("NO")) {

                        s.close();
                        System.out.println("Il client si e' disconnesso");
                        loopPartita = false;
                        break;

                    } else {
                        int numeroRicevutoInt = Integer.parseInt(numeroRicevuto);

                        if (numeroRicevutoInt == numRand) {
                            out.writeBytes("=" + "\n");
                            String tentativiStr = Integer.toString(tentativi);
                            out.writeBytes(tentativiStr + "\n");

                        } else if (numeroRicevutoInt < numRand) {
                            out.writeBytes("<" + "\n");
                            tentativi++;

                        } else if (numeroRicevutoInt > numRand) {
                            out.writeBytes(">" + "\n");
                            tentativi++;
                        }
                    }

                } while (true);
            } while (loopPartita);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
