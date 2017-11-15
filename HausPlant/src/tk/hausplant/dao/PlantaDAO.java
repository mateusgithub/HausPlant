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

    private static final Logger LOG = Logger.getLogger(PlantaDAO.class.getName());

    /**
     * Construtor inicializa as informações dos arquivos
     */
    public PlantaDAO() {
    }

    /**
     * Salva os dados da Planta em formato serializado.
     *
     * @param planta Referência para objeto com dados pré-carregados
     * @param arquivoSerializado Local para salvar o arquivo
     */
    public static void salvar(Planta planta, File arquivoSerializado) {
        try {
            ObjectOutputStream os = new ObjectOutputStream(
                    Files.newOutputStream(arquivoSerializado.toPath()));
            os.writeObject(planta);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Erro ao salvar", ex);
        }
    }

    /**
     * Metodo que verifica existencia do arquivo e decide se deve ler
     * serializado ou do arquivo .csv
     *
     * @return Planta com descricao e paredes
     */
    public static Planta carregar(File arquivoSerializado) {
        if (arquivoSerializado.exists()) {
            LOG.info("Arquivo planta serializada");
            return carregarSerializado(arquivoSerializado);
        } else {
            LOG.info("Arquivo planta.csv");
            return carregarPlanta(arquivoSerializado);
        }
    }

    /**
     * Carrega arquivo Planta serializado
     *
     * @return Planta
     */
    private static Planta carregarSerializado(File arquivoSerializado) {
        ObjectInputStream is;
        Planta dados = null;
        try {
            is = new ObjectInputStream(
                    Files.newInputStream(arquivoSerializado.toPath()));
            dados = (Planta) is.readObject();
        } catch (ClassNotFoundException | IOException ex) {
            LOG.log(Level.SEVERE, "loadSerialized", ex);
        }
        return dados;
    }

    /**
     * Carregar uma planta a partir e um arquivo. Carrega as cordenadas e monta
     * as paredes da Planta
     */
    private static Planta carregarPlanta(File arquivo) {
        Planta planta = new Planta();

        BufferedReader source;
        try {
            source = Files.newBufferedReader(arquivo.toPath(),
                    StandardCharsets.ISO_8859_1);
            String cabecalho = source.readLine();
            String line;
            while ((line = source.readLine()) != null) {
                String[] cord = line.split(",");
                final Parede parede = new Parede(Integer.valueOf(cord[0]), Integer.valueOf(cord[1]), Integer.valueOf(cord[2]), Integer.valueOf(cord[3]));
                planta.addParede(parede);
            }
        } catch (IOException ex) {
            Logger.getLogger(PlantaDAO.class.getName()).
                    log(Level.SEVERE, "Falha ao ler arquivo planta csv", ex);
        }
        
        return planta;
    }
}
