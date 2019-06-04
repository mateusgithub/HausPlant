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
import java.util.ArrayList;
import tk.hausplant.view.ItemLoja;

public class ItemLojaDAO {

	private ItemLojaDAO() {
	}

	public static List<ItemLoja> lerItensCSV(final Path caminho) throws IOException {

		List<ItemLoja> lista = new ArrayList<>();

		try (BufferedReader source = Files.newBufferedReader(caminho, StandardCharsets.UTF_8)) {
			source.readLine();

			String line;
			while ((line = source.readLine()) != null) {
				if (!line.trim().isEmpty()) {
					String[] variaveis = line.trim().split("\\s*,\\s*", 3);
					if (variaveis.length >= 3) {
						ItemLoja novoItem = new ItemLoja(variaveis[0], variaveis[1], variaveis[2]);
						lista.add(novoItem);
					}
				}
			}
		}

		return lista;
	}
}
