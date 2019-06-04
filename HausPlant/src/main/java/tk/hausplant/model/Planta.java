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

	public Planta(final String descricao, final List<Parede> paredes, final List<Movel> moveis) {
		this.descricao = descricao;
		this.paredes = paredes;
		this.moveis = moveis;
	}

	public List<Parede> getParedes() {
		return paredes;
	}

	public void addParede(final Parede parede) {
		this.paredes.add(parede);
	}

	public List<Movel> getMoveis() {
		return moveis;
	}

	public void addMovel(final Movel movel) {
		this.moveis.add(movel);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

}
