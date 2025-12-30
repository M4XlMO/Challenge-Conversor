import java.io.IOException;
import java.util.Scanner;

public class Principal {
    static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        Consulta consulta = new Consulta();
        System.out.println("Escribe el numero de pelicula a consultar");
    try {
        var numeroPelicula = Integer.valueOf(lectura.nextLine());
        Pelicula pelicula = consulta.buscaPelicula(numeroPelicula);
        System.out.println(pelicula);
        GuardaConsulta guarda = new GuardaConsulta();
        guarda.guardaJson(pelicula);
    }
    catch (NumberFormatException e){
        System.out.println("Numero de pelicula no encontrado "+e.getMessage());
    }
    catch (RuntimeException | IOException e){
        System.out.println(e.getMessage());
        System.out.println("Finzalizando ejecución");
    }
    }
}
