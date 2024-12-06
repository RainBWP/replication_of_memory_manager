package com.sistemas;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) {
        boolean isVerbose = true;
        boolean ejecutar = false;
        boolean jsonExist = false;
        JsonImport jsonImport = JsonImportManager();
        int port = 8383;

        if (jsonImport != null) {
            jsonExist = true;
        }

        if (args.length != 0) {
            if (args.length <= 2) {
                if (!args[1].matches("\\b(?:(?:2[0-4]\\d|25[0-5]|1\\d{2}|[1-9]?\\d)\\.){3}(?:2[0-4]\\d|25[0-5]|1\\d{2}|[1-9]?\\d)\\b")) {
                    System.out.println("Error: La dirección IP no es válida");
                } else {
                    ejecutar = true;
                }

                if ((args[0].equals("-c") || args[0].equals("--client")) && ejecutar) {
                    Client.startClient(args[1], port, isVerbose);
                } else if ((args[0].equals("-s") || args[0].equals("--server")) && (ejecutar && jsonExist)) {
                    Server.startServer(args[1], port, isVerbose);
                } else {
                    System.out.println("Error: Argumento no válido\n");
                    printJsonExample();
                    ejecutar = false;
                }
            } else {
                System.out.println("Error: Debe ingresar 2 argumentos");
            }
        }
        if (!ejecutar) {
            printExecutionExample();
        }
    }

    private static void printExecutionExample(){
        System.out.println("Para ejecutar el programa se deben pasar dos argumentos:");
        System.out.println("--client    -c\t Para Ejecutar el Cliente el cual se conectara al servidor");
        System.out.println("--server    -s\t Para Ejecutar el Servidor el cual se encargara de recibir las conexiones de los clientes\n");
        System.out.println("\t0.0.0.0\t Una direccion IP para conectarse al servidor");
        System.out.println("\t0.0.0.0\t Una direccion IP para levantar el servidor\n\n");
        System.out.println("Ejemplo: \t java main -s 192.168.1.1");
            
    }

    private static void printJsonExample(){
        System.out.println("\nSe Recomienda para el uso del servidor Crear un archivo json Llamado 'execution.json'");
        System.out.println("\tEste debe Contar con el siguiente formato:");
        System.out.println("{\n" +
                "        \"tamano_de_pagina\": 4096,\n" +
                "        \"nodos\": [\n" +
                "            {\n" +
                "                \"id\": 1,\n" +
                "                \"memoria_fisica\": 16384,\n" +
                "                \"memoria_virtual\": 32768\n..." +
                "            },\n" +
                "        ]\n" +
                "}");
    }

    private static JsonImport JsonImportManager(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Leer JSON desde un archivo
            File jsonFile = new File("execution.json"); // Asegúrate de que este archivo exista
            JsonImport Json = mapper.readValue(jsonFile, JsonImport.class);
            
            /* System.out.println("Debug:\tTamano de Pagina: " + Json.getTamanoDePagina());
            System.out.println("\tNodosSize: "+ Json.sizeNodos()); */
            return Json;
        } catch (IOException e) {
            System.err.println("================================");
            System.err.println(e.getMessage());
            System.err.println("================================");
            return null;
        }
    }
}
