package tk.hausplant.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;
import spacedrawboard.resource.Material;
import spacedrawboard.resource.Mesh;
import spacedrawboard.resource.Model;

/**
 * Objeto tridimensional que pode ser colocado na planta
 */
public class Movel extends Model implements Objeto3D {

    private Movel(Mesh forma, Material material) {
        super(forma, material);
    }

    public static Movel carregarMovel(Path caminho) throws IOException, ParseException {
        Movel movel;

        // Ler conteúdo do arquivo
        String source = new String(Files.readAllBytes(caminho));

        JSONObject objetoJson;
        try {
            objetoJson = new JSONObject(source);
        } catch (JSONException exception) {
            throw new ParseException("Arquivo de modelo 3D inválido", -1);
        }
        
        ////

        return movel;
    }

}
