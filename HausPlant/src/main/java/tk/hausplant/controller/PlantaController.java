/**
 * HausPlant
 *
 * 2017
 * Equipe desenvolvedora do HausPlant
 */
package tk.hausplant.controller;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import tk.hausplant.dao.PlantaDAO;
import tk.hausplant.model.Planta;

/**
 * Controller responsável pelas regras de negócio de Planta.
 */
public class PlantaController {

	private PlantaController() {
	}

	private static final String EXTENSAO_SERIALIZADO = ".dat";

	private static final Logger LOG = Logger.getLogger(PlantaController.class.getName());

	public static Planta carregar(final File arquivo) throws IOException {
		LOG.info("Carregando planta");
		return PlantaDAO.carregar(arquivo);
	}

	public static void salvar(final Planta planta, final File arquivo) {
		File arquivoComExtensao = arquivo;
		if (!arquivo.toPath().toString().endsWith(EXTENSAO_SERIALIZADO)) {
			arquivoComExtensao = new File(arquivo.toPath() + EXTENSAO_SERIALIZADO);
		}

		LOG.info("Salvando planta");
		PlantaDAO.salvar(planta, arquivoComExtensao);
	}
}
