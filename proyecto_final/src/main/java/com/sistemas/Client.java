package com.sistemas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    /* public static void main(String[] args) {
        String host = "localhost";
        int port = 8383;

        for (int attempt = 0; attempt < 5; attempt++) {
            byte status = connectToServer(host, port);

            if (status == 1) {
                System.out.println("Conexión exitosa");
                break;
            } else {
                System.err.println("Intento fallido. Reintentando en 1 segundo...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.err.println("Error durante la espera: " + e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
            if (attempt == 4) {
                System.err.println("No se pudo conectar al servidor tras 5 intentos.");
                return;
            }
        }
    } */

    public static void startClient(String host, int port, boolean isVerbose){
        for (int attempt = 0; attempt < 5; attempt++) {
            byte status = connectToServer(host, port);

            if (status == 1) {
                System.out.println("Conexión exitosa");
                break;
            } else {
                System.err.println("Intento fallido. Reintentando en 1 segundo...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.err.println("Error durante la espera: " + e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
            if (attempt == 4) {
                System.err.println("No se pudo conectar al servidor tras 5 intentos.");
                return;
            }
        }
    }

    public static byte connectToServer(String host, int port) {
        try (Socket socket = new Socket(host, port);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado al servidor " + host + ":" + port + "\n");
            System.out.println("Para terminar la conexión, envíe '0'.");

            // Iniciar el hilo para manejar logs del servidor
            Thread logThread = createLogThread(in);
            logThread.start();

            handleUserInput(console, out);

            logThread.interrupt();
            logThread.join();
            return 1;

        } catch (IOException | InterruptedException e) {
            System.err.println("Error durante la conexión: " + e.getMessage());
            return 4;
        }
    }

    private static Thread createLogThread(ObjectInputStream in) {
        return new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Object response = in.readObject();
                    if (response instanceof String log) {
                        System.out.println("Log del servidor: " + log);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("La conexión con el servidor se cerró.");
            }
        });
    }

    private static void handleUserInput(BufferedReader console, ObjectOutputStream out) throws IOException {
        byte option;
        do {
            System.out.print("Pulse '1' para enviar un proceso al servidor: ");
            try {
                option = Byte.parseByte(console.readLine());

                if (option == 1) {
                    Proceso proceso = leerProceso(console);
                    if (proceso != null) {
                        out.writeObject(proceso);
                        out.flush();
                    } else {
                        System.err.println("Proceso inválido.");
                    }
                } else if (option != 0) {
                    System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Entrada no válida. Intente de nuevo.");
                option = -1;
            }
        } while (option != 0);
        System.out.println("Terminando conexión...");
        out.writeObject(null); // Enviar señal de terminación
        out.flush();
    }

    public static Proceso leerProceso(BufferedReader console) {
        try {
            System.out.print("Ingrese el nombre del proceso: ");
            String nombre = console.readLine();

            System.out.print("Ingrese el número de páginas a usar: ");
            int paginas = Integer.parseInt(console.readLine());

            System.out.print("Ingrese las referencias separadas por espacios: ");
            String[] referenciasStr = console.readLine().split(" ");
            int[] referencias = new int[referenciasStr.length];

            for (int i = 0; i < referenciasStr.length; i++) {
                referencias[i] = Integer.parseInt(referenciasStr[i]);
            }

            return new Proceso(nombre, paginas, referencias);
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al crear el proceso. Intente nuevamente.");
            return null;
        }
    }
}
