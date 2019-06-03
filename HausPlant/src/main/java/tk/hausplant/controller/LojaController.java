/**
 * HausPlant
 *
 * 2017
 * Equipe desenvolvedora do HausPlant
 */
package tk.hausplant.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.text.ParseException;
import tk.hausplant.model.Planta;
import tk.hausplant.view.Loja;

public class LojaController {

	private static final String ARQUIVO_INDICE_LOJA = "/indiceMoveisLoja.csv";

	public static void adicionarMovel(final Planta planta) throws IOException, ParseException {
		try {
			Loja loja = new Loja(Paths.get(LojaController.class.getResource(ARQUIVO_INDICE_LOJA).toURI()), planta);
			loja.setVisible(true);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

}
