/**
 * HausPlant
 *
 * 2017
 * Equipe desenvolvedora do HausPlant
 */
package tk.hausplant.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import tk.hausplant.model.Parede;
import tk.hausplant.model.Planta;

/**
 * Classe responsável pela leitura e escrita do objeto Planta em arquivo
 *
 * @author mateus
 */
public class PlantaDAO {

	private PlantaDAO() {
	}

	private static final Logger LOG = Logger.getLogger(PlantaDAO.class.getName());

	/**
	 * Salva os dados da Planta em formato serializado.
	 *
	 * @param planta             Referência para objeto com dados pré-carregados
	 * @param arquivoSerializado Local para salvar o arquivo
	 */
	public static void salvar(final Planta planta, final File arquivoSerializado) {
		try (ObjectOutputStream os = new ObjectOutputStream(Files.newOutputStream(arquivoSerializado.toPath()))) {
			os.writeObject(planta);
		} catch (IOException ex) {
			LOG.log(Level.SEVERE, "Erro ao salvar", ex);
		}
	}

	/**
	 * Metodo que verifica existencia do arquivo e decide se deve ler serializado ou
	 * do arquivo .csv
	 *
	 * @param arquivo
	 * @return Planta com descricao e paredes
	 */
	public static Planta carregar(final File arquivo) throws IOException {
		if (arquivo.exists()) {
			if (arquivo.getAbsolutePath().endsWith(".csv")) {
				return PlantaDAO.carregarPlantaCSV(arquivo);
			} else {
				return PlantaDAO.carregarPlantaSerializada(arquivo);
			}
		} else {
			throw new IOException("Arquivo não encontrando");
		}
	}

	/**
	 * Carrega arquivo Planta serializado.
	 */
	private static Planta carregarPlantaSerializada(final File arquivoSerializado) {
		Planta dados = null;
		try (ObjectInputStream is = new ObjectInputStream(Files.newInputStream(arquivoSerializado.toPath()))) {
			dados = (Planta) is.readObject();
		} catch (ClassNotFoundException | IOException ex) {
			LOG.log(Level.SEVERE, "loadSerialized", ex);
		}
		return dados;
	}

	/**
	 * Carregar uma planta a partir e um arquivo. Carrega as cordenadas e monta as
	 * paredes da Planta.
	 */
	private static Planta carregarPlantaCSV(final File arquivo) {
		Planta planta = new Planta();

		try (BufferedReader source = Files.newBufferedReader(arquivo.toPath(), StandardCharsets.ISO_8859_1)) {
			String line;
			while ((line = source.readLine()) != null) {
				String[] cord = line.split(",");
				final Parede parede = new Parede(Integer.valueOf(cord[0]), Integer.valueOf(cord[1]),
						Integer.valueOf(cord[2]), Integer.valueOf(cord[3]));
				planta.addParede(parede);
			}
		} catch (IOException ex) {
			Logger.getLogger(PlantaDAO.class.getName()).log(Level.SEVERE, "Falha ao ler arquivo planta csv", ex);
		}

		return planta;
	}
}
