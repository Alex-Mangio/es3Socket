package com.server;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws IOException {
    
        System.out.println("Il server e' partito...");

        ServerSocket ss = new ServerSocket(3000);

        while(true){
            ServerThread ts = new ServerThread(ss.accept());
            ts.start();
        }
    }
}