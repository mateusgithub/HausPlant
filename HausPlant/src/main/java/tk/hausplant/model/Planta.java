/**
 * HausPlant
 *
 * 2017
 * Equipe desenvolvedora do HausPlant
 */
package tk.hausplant.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Planta da casa que poderá conter paredes, móveis e telhados
 */
public class Planta implements Serializable {

    private String descricao;

    private final List<Parede> paredes;

    private final List<Movel> moveis;

    public Planta() {
        paredes = new ArrayList<>();
        moveis = new ArrayList<>();
    }

    public Planta(String descricao, List<Parede> paredes, List<Movel> moveis) {
        this.descricao = descricao;
        this.paredes = paredes;
        this.moveis = moveis;
    }

    public List<Parede> getParedes() {
        return paredes;
    }

    public void addParede(Parede parede) {
        this.paredes.add(parede);
    }

    public List<Movel> getMoveis() {
        return moveis;
    }

    public void addMovel(Movel movel) {
        this.moveis.add(movel);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
