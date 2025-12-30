import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class GuardaConsulta {
    public void guardaJson(Pelicula pelicula) throws IOException {
        if (pelicula == null || pelicula.title() == null || pelicula.title().isBlank()) {
            throw new IllegalArgumentException("No se puede guardar: película inválida o sin título");
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter escritura = new FileWriter(pelicula.title() + ".json")) {
            escritura.write(gson.toJson(pelicula));
        }
    }
}