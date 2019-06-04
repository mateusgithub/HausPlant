/**
 * HausPlant
 *
 * 2017
 * Equipe desenvolvedora do HausPlant
 */
package tk.hausplant.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import tk.hausplant.dao.ItemLojaDAO;

public class CarregadorDeItens implements Runnable {

	private final Loja loja;

	private final Path indiceItens;

	public CarregadorDeItens(final Loja loja, final Path indiceItens) {
		this.loja = loja;
		this.indiceItens = indiceItens;
	}

	@Override
	public void run() {
		List<ItemLoja> itens;
		try {
			itens = ItemLojaDAO.lerItensCSV(indiceItens);
		} catch (IOException ex) {
			loja.erroCarregarLoja();
			return;
		}

		for (ItemLoja item : itens) {
			// Adicionar evento ao item

			item.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(final MouseEvent me) {
					loja.escolherMovel(item);
				}
			});

			loja.adicionarItem(item);
		}
	}
}
