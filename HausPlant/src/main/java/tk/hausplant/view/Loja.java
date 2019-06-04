/**
 * HausPlant
 *
 * 2017
 * Equipe desenvolvedora do HausPlant
 */
package tk.hausplant.view;

import java.awt.Color;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import tk.hausplant.controller.LojaController;
import tk.hausplant.dao.MovelDAO;
import tk.hausplant.model.Movel;
import tk.hausplant.model.Planta;

/**
 * Onde estarão disponíveis os objetos que poderão ser colocados na planta
 */
public class Loja extends JFrame {

	private static final long serialVersionUID = 1L;
	private final Planta planta;

	/**
	 * Creates new form Loja
	 *
	 * @param indiceItens Caminho do arquivo contendo lista de móveis presentes na
	 *                    loja
	 * @param planta      Planta onde o móvel será inserido
	 * @throws IOException
	 * @throws ParseException
	 */
	public Loja(final Path indiceItens, final Planta planta) {
		initComponents();

		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

		setLocationRelativeTo(null);

		// Ajustar velocidade do scroll
		scroll.getVerticalScrollBar().setUnitIncrement(16);
		this.planta = planta;

		carregarItensDaLoja(indiceItens);
	}

	public void erroCarregarLoja() {
		TelasPopup.mostrarMensagem("Erro ao carregar itens da loja, " + "verifique a presença do arquivo de índice");
		dispose();
	}

	public void adicionarItem(final ItemLoja item) {
		container.add(item);
	}

	public void escolherMovel(final ItemLoja item) {
		Color cor = TelasPopup.obterCor();

		Movel movel;

		try {
			Path pathMovel = Paths.get(LojaController.class.getResource(item.getLocalModelo()).toURI());
			movel = MovelDAO.carregarMovelSTL(pathMovel, cor);
		} catch (ParseException | URISyntaxException ex) {
			TelasPopup.mostrarMensagem("Falha ao abrir o arquivo do móvel");
			return;
		}

		planta.addMovel(movel);

		dispose();
	}

	/**
	 * Método assíncrono para carregar itens da loja.
	 */
	private void carregarItensDaLoja(final Path indiceItens) {
		CarregadorDeItens carregador = new CarregadorDeItens(this, indiceItens);
		carregador.run();
	}

	private void initComponents() {
		new JSplitPane();
		JPanel jPanel1 = new JPanel();
		JToggleButton jToggleButton1 = new JToggleButton();
		JLabel jLabel1 = new JLabel();
		JLabel jLabel2 = new JLabel();
		scroll = new JScrollPane();
		container = new JPanel();

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 100, Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 100, Short.MAX_VALUE));

		jToggleButton1.setText("jToggleButton1");

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);

		jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
		jLabel1.setText("Loja");

		jLabel2.setText("Clique sobre um item para adicionar à planta");

		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		container.setBackground(new java.awt.Color(254, 254, 254));

		GroupLayout containerLayout = new GroupLayout(container);
		container.setLayout(containerLayout);
		containerLayout.setHorizontalGroup(
				containerLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 549, Short.MAX_VALUE));
		containerLayout.setVerticalGroup(
				containerLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 319, Short.MAX_VALUE));

		scroll.setViewportView(container);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(scroll)
						.addGroup(GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addGap(60, 60, 60).addComponent(jLabel1)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel2).addGap(29, 29, 29)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addComponent(jLabel1).addGap(0, 0, Short.MAX_VALUE))
						.addComponent(jLabel2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE)));

		pack();
	}

	private JPanel container;
	private JScrollPane scroll;
}
