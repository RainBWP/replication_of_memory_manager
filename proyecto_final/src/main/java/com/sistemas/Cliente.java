package com.sistemas;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost"; // Dirección del servidor
        int port = 12345; // Puerto del servidor

        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado al servidor " + host + " en el puerto " + port);

            String userInput;
            while ((userInput = console.readLine()) != null) {
                out.println(userInput); // Enviar al servidor
                System.out.println("Respuesta del servidor: " + in.readLine()); // Leer respuesta
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

