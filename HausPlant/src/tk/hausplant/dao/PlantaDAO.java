/**
 * HausPlant
 *
 * 2017
 * Equipe desenvolvedora do HausPlant
 */
package tk.hausplant.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
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
    private static final String PLANTA_DEFAULT = "Planta.csv";
    private static final String SERIAL_FILENAME = "planta.dat";
    private final Path arquivoCsv;
    private final Path arquivoSerializado;

    /**
     * Construtor inicializa as informações dos arquivos
     */
    public PlantaDAO() {
        this.arquivoCsv = FileSystems.getDefault().getPath(PLANTA_DEFAULT);
        this.arquivoSerializado = FileSystems.getDefault().getPath(SERIAL_FILENAME);
    }

    /**
     * Salva os dados da Planta em formato serializado.
     *
     * @param planta Referência para objeto com dados pré-carregados
     */
    public void salvar(Planta planta) {
        try {
            ObjectOutputStream os = new ObjectOutputStream(
                    Files.newOutputStream(arquivoSerializado));
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
    public Planta carregar() {
        if (Files.exists(arquivoSerializado)) {
            LOG.info("Arquivo planta serializada");
            return carregarSerializado();
        } else {
            LOG.info("Arquivo planta.csv");
            return new Planta(carregarDescricaoPlanta(), carregarParedesPlanta());
        }
    }

    /**
     * Carrega arquivo Planta serializado
     *
     * @return Planta
     */
    private Planta carregarSerializado() {
        ObjectInputStream is;
        Planta dados = null;
        try {
            is = new ObjectInputStream(
                    Files.newInputStream(arquivoSerializado));
            dados = (Planta) is.readObject();
        } catch (ClassNotFoundException | IOException ex) {
            LOG.log(Level.SEVERE, "loadSerialized", ex);
        }
        return dados;
    }

    /**
     * Carrega a descricao da planta
     *
     * @return Descrição da planta
     */
    private String carregarDescricaoPlanta() {
        try {
            BufferedReader source = Files.newBufferedReader(arquivoCsv,
                    StandardCharsets.ISO_8859_1);
            String descricao = source.readLine();
            return descricao;
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Carregar as cordenadas e monta as paredes da Planta
     *
     * @return Lista de paredes da planta
     */
    private List<Parede> carregarParedesPlanta() {
        List<Parede> paredes = new ArrayList();

        BufferedReader source;
        try {
            source = Files.newBufferedReader(arquivoCsv,
                    StandardCharsets.ISO_8859_1);
            String descricao = source.readLine();
            String cabecalho = source.readLine();
            String line;
            while ((line = source.readLine()) != null) {
                String[] cord = line.split(",");
                final Parede parede = new Parede(Integer.valueOf(cord[0]), Integer.valueOf(cord[1]), Integer.valueOf(cord[2]), Integer.valueOf(cord[3]));
                paredes.add(parede);
            }
        } catch (IOException ex) {
            Logger.getLogger(PlantaDAO.class.getName()).
                    log(Level.SEVERE, "Falha ao ler arquivo planta csv", ex);
        }
        return paredes;
    }
}
