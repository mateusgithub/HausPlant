/**
 * HausPlant
 *
 * 2017
 * Equipe desenvolvedora do HausPlant
 */
package tk.hausplant.dao;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import spacedrawboard.resource.Mesh;
import spacedrawboard.resource.Triangle;
import spacedrawboard.resource.Vector3D;
import tk.hausplant.model.Movel;

/**
 * Responsável por manipular arquivos relacionados à Movel
 */
public class MovelDAO {

	private MovelDAO() {
	}

	public static Movel carregarMovelSTL(final Path caminho, final Color cor) throws ParseException {
		Mesh forma = new Mesh();

		try (BufferedReader source = Files.newBufferedReader(caminho, StandardCharsets.ISO_8859_1)) {
			String line = source.readLine();

			// Construir móvel a partir dos triângulos
			while (true) {
				while (line != null && !line.trim().startsWith("outer loop")) {
					line = source.readLine();
				}

				if (line == null) {
					break;
				}

				// valores do triangulo
				double[] v = new double[9];

				// TRIANGULO
				for (int i = 0; i < 3; i++) {
					line = source.readLine();

					if (line == null) {
						throw new ParseException("Fim de arquivo inesperado", -1);
					}
					line = line.trim().replaceFirst("vertex ", "");

					String[] verticeStrings = line.split("\\s+", 3);

					if (verticeStrings.length < 3) {
						// vertice inaválido
						throw new ParseException("Arquivo corrompido", -1);
					}
					v[3 * i + 0] = Double.valueOf(verticeStrings[0]);
					v[3 * i + 1] = Double.valueOf(verticeStrings[1]);
					v[3 * i + 2] = Double.valueOf(verticeStrings[2]);

				}

				Triangle novoTriangulo = new Triangle(new Vector3D(v[0], v[1], v[2]), new Vector3D(v[3], v[4], v[5]),
						new Vector3D(v[6], v[7], v[8]));
				forma.addTriangle(novoTriangulo);
			}
		} catch (IOException ex) {
			Logger.getLogger(PlantaDAO.class.getName()).log(Level.SEVERE, "Falha ao ler arquivo do móvel stl", ex);
		}

		return new Movel(forma, cor);
	}

}
