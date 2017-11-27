/**
 * HausPlant
 *
 * 2017
 * Equipe desenvolvedora do HausPlant
 */
package tk.hausplant.controller;

import java.io.File;
import java.util.logging.Logger;
import tk.hausplant.dao.PlantaDAO;
import tk.hausplant.model.Planta;
import tk.hausplant.view.Loja;

/**
 * Controller responsável pelas regras de negócio de Planta
 */
public class PlantaController {

    private static final String EXTENSAO_SERIALIZADO = ".dat";

    private static final String EXTENSAO_PADRAO = ".csv";

    private static final Logger LOG = Logger.getLogger(PlantaController.class.getName());

    public static Planta carregar(File arquivo) throws Exception {
        LOG.info("Carregando planta");
        return PlantaDAO.carregar(arquivo);
    }

    public static void salvar(Planta planta, File arquivo) {
        if (!arquivo.toPath().toString().endsWith(EXTENSAO_SERIALIZADO)) {
            arquivo = new File(arquivo.toPath() + EXTENSAO_SERIALIZADO);
        }
        
        LOG.info("Salvando planta");
        PlantaDAO.salvar(planta, arquivo);
    }
}
