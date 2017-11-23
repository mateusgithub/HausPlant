/**
 * HausPlant
 *
 * 2017
 * Equipe desenvolvedora do HausPlant
 */
package tk.hausplant.dao;

import java.io.BufferedReader;
import java.util.List;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tk.hausplant.view.ItemLoja;

/**
 *
 * @author sergio
 */
public class ItemLojaDAO {

    /**
     * Ler lista de itens a partir de um arquivo com uma lista de itens no
     * formato JSON
     *
     * @param indiceItens
     * @return
     * @throws IOException
     * @throws ParseException
     */
    public static List<ItemLoja> lerItens(Path indiceItens)
            throws IOException, ParseException {
        List<ItemLoja> lista = new ArrayList<>();

        // Ler conteúdo do arquivo
        String source = new String(Files.readAllBytes(indiceItens));

        JSONArray arrayJson;

        try {
            JSONObject objetoJson = new JSONObject(source);
            arrayJson = objetoJson.getJSONArray("itens");
        } catch (JSONException exception) {
            throw new ParseException("Arquivo de índice de itens inválido", -1);
        }

        for (int i = 0; i < arrayJson.length(); i++) {
            try {
                JSONObject objeto = arrayJson.getJSONObject(i);

                String nomeItem = objeto.getString("nome");
                String localModelo = objeto.getString("localModelo");
                String localFoto = objeto.getString("localFoto");

                ItemLoja novoItem = new ItemLoja(nomeItem, localFoto, localModelo);
                lista.add(novoItem);
            } catch (JSONException exception) {
                // Ignorar modelo inválido
            }
        }

        return lista;
    }

    public static List<ItemLoja> lerItensCSV(Path caminho) throws ParseException, IOException {

        BufferedReader source = null;
        List<ItemLoja> lista = new ArrayList<>();

        source = Files.newBufferedReader(caminho,
                StandardCharsets.UTF_8);

        String line = source.readLine();

        while ((line = source.readLine()) != null) {

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] variaveis = line.trim().split("\\s*,\\s*", 3);
            if (variaveis.length < 3) {
                continue;
            }
            ItemLoja novoItem = new ItemLoja(variaveis[0], variaveis[1], variaveis[2]);
            lista.add(novoItem);

        }

        return lista;
    }

    public static void main(String[] args) throws ParseException, IOException {
        /*
        List<ItemLoja> lista=null;
        try {
            lista = ItemLojaDAO.lerItensDAO(Paths.get("teste.txt"));
        } catch (ParseException ex) {
            Logger.getLogger(ItemLojaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return;
        } catch (IOException ex) {
            Logger.getLogger(ItemLojaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        for(ItemLoja a : lista){
            System.out.println(a.getNome()+", " + a.getLocalFoto() + ", " +  a.getLocalModelo() );
        }
         */
    }
}
