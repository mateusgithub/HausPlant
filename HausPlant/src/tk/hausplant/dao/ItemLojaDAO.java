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
import tk.hausplant.view.ItemLoja;

/**
 *
 * @author sergio
 */
public class ItemLojaDAO {

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
