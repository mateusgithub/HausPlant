/**
 * HausPlant
 *
 * 2017
 * Equipe desenvolvedora do HausPlant
 */
package tk.hausplant.controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import tk.hausplant.model.Planta;
import tk.hausplant.view.Loja;

public class LojaController {

    private static final String ARQUIVO_INDICE_LOJA = "indiceMoveisLoja.csv";

    public static void adicionarMovel(Planta planta) 
            throws IOException, ParseException {
        Loja loja = new Loja(Paths.get(ARQUIVO_INDICE_LOJA), planta);
        
        loja.setVisible(true);
    }
    
}
