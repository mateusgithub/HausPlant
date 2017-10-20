package tk.hausplant.dao;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;
import tk.hausplant.model.Planta;

/**
 * Classe responsável pela persistencia do objeto Planta
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
//    
//    public Planta load(){
//        if(Files.exists(arquivoSerializado)){
//            
//        }else{
//            
//        }
//    }
//    
    
}
