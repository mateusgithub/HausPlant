package tk.hausplant.model;

import java.io.Serializable;
import java.util.List;

/**
 * Planta da casa que poderá conter paredes, móveis e telhados
 */
public class Planta implements Serializable {

    private String descricao;
    
    private final List<Parede> walls;

    public Planta(String descricao, List<Parede> paredes) {
        this.descricao = descricao;
        this.walls = paredes;
    }

    public List<Parede> getWalls() {
        return walls;
    }

    public void addWall(Parede wall) {
        this.walls.add(wall);
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
  
}
