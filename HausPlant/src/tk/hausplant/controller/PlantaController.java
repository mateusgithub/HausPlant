/**
 * HausPlant
 *
 * 2017
 * Equipe desenvolvedora do HausPlant
 */
package tk.hausplant.controller;

import java.util.logging.Logger;
import tk.hausplant.dao.PlantaDAO;
import tk.hausplant.model.Planta;

/**
 * Controller responsável pelas regras de negócio de Planta
 */
public class PlantaController {

    private static final Logger LOG = Logger.getLogger(PlantaController.class.getName());
    private PlantaDAO plantaDAO;

    public Planta carregar() {
        LOG.info("Carregando planta");
        plantaDAO = new PlantaDAO();
        return plantaDAO.carregar();
    }

    public void salvar(Planta planta) {
        LOG.info("Salvando planta");
        plantaDAO = new PlantaDAO();
        plantaDAO.salvar(planta);
    }
}
