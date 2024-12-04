package com.sistemas;

public class Main {
    public static void main(String[] args) {
        // System.out.println(java.util.Arrays.toString(args));
        boolean ejecutar = false;

        if (args.length != 0) {
            if (args.length == 2) {
                if (!args[1].matches("\\b(?:(?:2[0-4]\\d|25[0-5]|1\\d{2}|[1-9]?\\d)\\.){3}(?:2[0-4]\\d|25[0-5]|1\\d{2}|[1-9]?\\d)\\b")) {
                    System.out.println("Error: La dirección IP no es válida");
                    ejecutar = false;
                }

                if ((args[0].equals("-c") | args[0].equals("--client")) && ejecutar) {
                    ejecutar = true;
                    Cliente cliente = new Cliente(args[1]);
                    // cliente.ejecutar();
                } else if (((args[0].equals("-s")) | args[0].equals("--server")) && ejecutar) {
                    ejecutar = true;
                    Servidor servidor = new Servidor(args[1]);
                    // servidor.ejecutar();
                } else {
                    ejecutar = false;
                    System.out.println("Error: Argumento no valido");
                }
                
            } else {
                System.out.println("Error: Debe ingresar 2 argumentos");
            }

            
        }
        if (!ejecutar) {
            System.out.println("Para ejecutar el programa se deben pasar dos argumentos:");
            System.out.println("--client    -c\t Para Ejecutar el Cliente el cual se conectara al servidor");
            System.out.println("--server    -s\t Para Ejecutar el Servidor el cual se encargara de recibir las conexiones de los clientes\n");
            System.out.println("\t0.0.0.0\t Una direccion IP para conectarse al servidor");
            System.out.println("\t0.0.0.0\t Una direccion IP para levantar el servidor\n\n");
            System.out.println("Ejemplo: \t java main -s 192.168.1.1");
        }

        
    }
}