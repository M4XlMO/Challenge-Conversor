import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Consulta {

    public Pelicula buscaPelicula(int numeroPelicula) {
        URI direccion = URI.create("https://swapi.py4e.com/api/films/" + numeroPelicula + "/");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 1) Si no es 200 OK, cortamos acá con un mensaje claro
            if (response.statusCode() != 200) {
                throw new RuntimeException("Película no encontrada (HTTP " + response.statusCode() + ")");
            }

            String body = response.body();

            // 2) Por si la API te devuelve literalmente "null"
            if (body == null || body.isBlank() || body.equals("null")) {
                throw new RuntimeException("Película no encontrada (respuesta nula)");
            }

            Pelicula pelicula = new Gson().fromJson(body, Pelicula.class);

            // 3) Validación extra: si faltan campos clave, lo tratamos como no encontrada
            if (pelicula == null || pelicula.title() == null || pelicula.title().isBlank()) {
                throw new RuntimeException("Película no encontrada (JSON sin datos de película)");
            }

            return pelicula;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error consultando la API: " + e.getMessage(), e);
        }
    }
}
