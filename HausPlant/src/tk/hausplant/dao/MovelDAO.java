/**
 * HausPlant
 *
 * 2017
 * Equipe desenvolvedora do HausPlant
 */
package tk.hausplant.dao;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import spacedrawboard.resource.Material;
import spacedrawboard.resource.Mesh;
import spacedrawboard.resource.Triangle;
import spacedrawboard.resource.Vector3D;
import spacedrawboard.visualization.Drawboard;
import spacedrawboard.visualization.Visualization;
import tk.hausplant.model.Movel;
import tk.hausplant.view.TelasPopup;

/**
 * Responsável por manipular arquivos relacionados à Movel
 */
public class MovelDAO {

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
            material = new Material(Movel.COR_PADRAO);
        }

        return new Movel(forma, material);
    }

    public static Movel carregarMovelSTL(Path caminho) throws IOException, ParseException {
        Mesh forma = new Mesh();
        BufferedReader source;
        try {
            source = Files.newBufferedReader(caminho,
                    StandardCharsets.ISO_8859_1);
            String line = source.readLine();

            // Construir móvel a partir dos triângulos
            while (true) {

                while (line != null && !line.trim().startsWith("outer loop")) {
                    line = source.readLine();

                }

                if (line == null) {
                    break;
                }

                // valores do triangulo
                double v[] = new double[9];

                // TRIANGULO
                for (int i = 0; i < 3; i++) {
                    line = source.readLine();

                    if (line == null) {
                        throw new ParseException("Fim de arquivo inesperado", -1);
                    }
                    line = line.trim().replaceFirst("vertex ", "");

                    String[] verticeStrings = line.split("\\s+", 3);

                    if (verticeStrings.length < 3) {
                        //vertice inaválido
                        throw new ParseException("Arquivo corrompido", -1);
                    }
                    v[3 * i + 0] = Double.valueOf(verticeStrings[0]);
                    v[3 * i + 1] = Double.valueOf(verticeStrings[1]);
                    v[3 * i + 2] = Double.valueOf(verticeStrings[2]);

                }

                Triangle novoTriangulo = new Triangle(
                        new Vector3D(v[0], v[1], v[2]),
                        new Vector3D(v[3], v[4], v[5]),
                        new Vector3D(v[6], v[7], v[8])
                );
                forma.addTriangle(novoTriangulo);

            }
        } catch (IOException ex) {
            Logger.getLogger(PlantaDAO.class.getName()).
                    log(Level.SEVERE, "Falha ao ler arquivo do móvel stl", ex);
        }

        Material material;

        int r = 191,
                g = 124,
                b = 57;

        material = new Material(new Color(r, g, b));

        return new Movel(forma, material);
    }

    public static void main(String args[]) {
        
        try {
            File dir = new File("modelos");
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    Drawboard drawboard = new Drawboard();

                    Movel movel = MovelDAO.carregarMovelSTL(child.toPath());
                    
                    drawboard.addModel(movel.getModelo());

                    Visualization v = new Visualization(child.getName(), 750, 450, drawboard);
                    v.showWindow();
                }
            } else {

            }
        } catch (IOException ex) {
            Logger.getLogger(MovelDAO.class.getName()).log(Level.SEVERE, null, ex);
            return;
        } catch (ParseException ex) {
            Logger.getLogger(MovelDAO.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

    }

}
