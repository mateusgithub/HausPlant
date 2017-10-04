/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.hausplant.dao;

import java.util.List;
import java.io.IOException;
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
}
