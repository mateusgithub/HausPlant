package tk.hausplant.view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Responsável por gerenciar os itens disponíveis na loja de objetos 3D
 */
public class ItemLoja extends JPanel {

    /**
     * Nome do item
     */
    private final String nome;

    /**
     * Local do arquivo contendo a foto do item
     */
    private final String localFoto;

    /**
     * Local do arquivo contendo o modelo 3d do item
     */
    private final String localModelo;

    public ItemLoja(String nome, String localFoto, String localModelo) {
        this.nome = nome;
        this.localFoto = localFoto;
        this.localModelo = localModelo;
    }

    public String getLocalModelo() {
        return localModelo;
    }

    public String getLocalFoto() {
        return localFoto;
    }

    public String getNome() {
        return nome;
    }

    /**
     * Ler lista de itens a partir de um arquivo com uma lista de itens no
     * formato JSON
     *
     * @param indiceItens
     * @return
     * @throws IOException
     * @throws ParseException
     */
    public static ArrayList<ItemLoja> lerItens(Path indiceItens)
            throws IOException, ParseException {
        ArrayList lista = new ArrayList<>();

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

}
