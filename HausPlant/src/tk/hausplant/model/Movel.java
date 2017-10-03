package tk.hausplant.model;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import spacedrawboard.resource.Material;
import spacedrawboard.resource.Mesh;
import spacedrawboard.resource.Model;
import spacedrawboard.resource.Triangle;
import spacedrawboard.resource.Vector3D;

/**
 * Objeto tridimensional que pode ser colocado na planta
 */
public class Movel extends Model implements Objeto3D {

    private static final Color COR_PADRAO = Color.gray;

    private Movel(Mesh forma, Material material) {
        super(forma, material);
    }

    public static Movel carregarMovel(Path caminho) throws IOException, ParseException {

        // Ler conteúdo do arquivo
        String source = new String(Files.readAllBytes(caminho));

        JSONArray triangulosJson;

        JSONObject cor;

        try {
            JSONObject objetoJson = new JSONObject(source);
            triangulosJson = objetoJson.getJSONArray("triangulos");
            cor = objetoJson.getJSONObject("cor");
        } catch (JSONException exception) {
            throw new ParseException("Arquivo de modelo 3D inválido", -1);
        }

        Mesh forma = new Mesh();

        // Construir móvel a partir dos triângulos
        for (int i = 0; i < triangulosJson.length(); i++) {
            try {
                JSONArray t = triangulosJson.getJSONArray(i);

                if (t.length() < 9) {
                    // Ignorar triangulo inválido
                    continue;
                }

                double[] v = new double[9];
                for (int j = 0; j < 9; j++) {
                    v[j] = t.getDouble(j);
                }

                Triangle novoTriangulo = new Triangle(
                        new Vector3D(v[0], v[1], v[2]),
                        new Vector3D(v[3], v[4], v[5]),
                        new Vector3D(v[6], v[7], v[8])
                );
                forma.addTriangle(novoTriangulo);
            } catch (JSONException exception) {
                // Ignorar triangulo inválido
            }
        }

        Material material;

        try {
            int r = cor.getInt("r"),
                    g = cor.getInt("g"),
                    b = cor.getInt("b");

            material = new Material(new Color(r, g, b));

        } catch (JSONException exception) {
            material = new Material(COR_PADRAO);
        }

        return new Movel(forma, material);
    }

}
