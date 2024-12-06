package com.sistemas;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    /* public static void main(String[] args) {
        int port = 8383; // Puerto de escucha

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor escuchando en el puerto " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde: " + clientSocket.getInetAddress());

                // Crear un nuevo hilo para manejar el cliente
                new Thread(() -> handleClient(clientSocket, true)).start();
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    } */

    public static void startServer(String host, int port, boolean isVerbose){

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor escuchando en el puerto " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde: " + clientSocket.getInetAddress() + "\n");

                // Crear un nuevo hilo para manejar el cliente
                new Thread(() -> handleClient(clientSocket, isVerbose)).start();
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }

    private static void handleClient(Socket clientSocket, boolean isVerbose) {
        try (ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {

            System.out.println("Manejando cliente: " + clientSocket.getInetAddress());

            Object input;
            while ((input = in.readObject()) != null) {
                if (input instanceof Proceso proceso) {
                    System.out.println("Proceso recibido: " + proceso.getNombreDeProceso());
                    
                    // Procesar el objeto Proceso
                    String log = generarLog(proceso);
                    
                    // Enviar log al cliente
                    enviarLog(out, log);
                    if (isVerbose) {
                        imprimirLog(log);
                    }

                } else {
                    System.err.println("Objeto desconocido recibido: " + input);
                }
            }
        } catch (EOFException e) {
            System.out.println("Cliente desconectado.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
        } finally {
            try {
                if (clientSocket != null && !clientSocket.isClosed()) {
                    clientSocket.close();
                }
                System.out.println("Conexión con el cliente cerrada.");
            } catch (IOException e) {
                System.err.println("Error al cerrar el socket del cliente: " + e.getMessage());
            }
        }
    }

    private static String generarLog(Proceso proceso) {
        // Usar datos de prueba para Paginas (se pueden cambiar más adelante)
        Paginas pagina = new Paginas(32, 16, 8);
        int direccionFisica = 185;

        // Generar log del proceso
        return proceso.sendLog(pagina, direccionFisica);
    }

    private static void enviarLog(ObjectOutputStream out, String log) {
        try {
            out.writeObject(log);
            out.flush();
            System.out.println("Log enviado al cliente.");
        } catch (IOException e) {
            System.err.println("Error al enviar log al cliente: " + e.getMessage());
        }
    }

    private static void imprimirLog(String log) {
        System.out.println("Log enviado:");
        System.out.println(log);
    }
}
