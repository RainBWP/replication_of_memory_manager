package BalanceCarga;
import java.util.Scanner;
//import com.sistemas.Proceso;

public class Input {

    public Proceso leerTask(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre del proceso: ");
        String proces = scanner.next();
        System.out.print("Numero de paginas: ");
        int NumPags = scanner.nextInt();
        System.out.print("Lista de referencias: ");
        //String LisRefs = scanner.next();
        int []LisRefs = scanner.nextInt();
        Proceso task = new Proceso(proces, NumPags, LisRefs);
        return task; 
    }
    
}
