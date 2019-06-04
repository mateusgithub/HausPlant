/**
 * HausPlant
 *
 * 2017
 * Equipe desenvolvedora do HausPlant
 */
package tk.hausplant.view;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

import tk.hausplant.controller.LojaController;
import tk.hausplant.controller.PlantaController;
import tk.hausplant.model.Planta;

/**
 * Janela onde serão exibidos controles para manipulação da Planta
 */
public class Prancheta extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;

	private static final String PLANTA_PADRAO = "/planta.dat";

	/**
	 * Quantidade de pixels por metro (para converter pixel para metro)
	 */
	public static final float PIXELS_POR_METRO = 25;

	private Planta planta;

	public Prancheta(final Planta planta, final Color corFundo) {
		initComponents();

		this.planta = planta;

		Renderizador2DPlanta renderizador2D = new Renderizador2DPlanta(planta, corFundo);

		container.add(renderizador2D);

		setLocationRelativeTo(null);
	}

	public void showWindow() {
		setVisible(true);
	}

	public void hideWindow() {
		setVisible(false);
	}

	public void closeWindow() {
		dispose();
	}

	private void initComponents() {
		container = new javax.swing.JPanel();
		JPanel jPanel2 = new javax.swing.JPanel();
		JButton carregarPlantaPadrao = new javax.swing.JButton();
		JButton carregarPlanta = new javax.swing.JButton();
		JButton inserirMovel = new javax.swing.JButton();
		JButton exibirVisualizacao3D = new javax.swing.JButton();
		JButton salvarPlanta = new javax.swing.JButton();
		JButton botaoFechar = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		container.setLayout(new java.awt.BorderLayout());

		jPanel2.setLayout(new java.awt.GridLayout(1, 0));

		carregarPlantaPadrao.setText("Carregar Planta Padrão");
		carregarPlantaPadrao.addActionListener(evt -> carregarPlantaPadraoActionPerformed());
		jPanel2.add(carregarPlantaPadrao);

		carregarPlanta.setText("Carregar");
		carregarPlanta.addActionListener(evt -> carregarPlantaActionPerformed());
		jPanel2.add(carregarPlanta);

		inserirMovel.setText("Inserir Móvel");
		inserirMovel.addActionListener(evt -> inserirMovelActionPerformed());
		jPanel2.add(inserirMovel);

		exibirVisualizacao3D.setText("Visualização 3D");
		exibirVisualizacao3D.addActionListener(evt -> exibirVisualizacao3DActionPerformed());
		jPanel2.add(exibirVisualizacao3D);

		salvarPlanta.setText("Salvar");
		salvarPlanta.addActionListener(evt -> salvarPlantaActionPerformed());
		jPanel2.add(salvarPlanta);

		botaoFechar.setText("Fechar");
		botaoFechar.addActionListener(evt -> botaoFecharActionPerformed());
		jPanel2.add(botaoFechar);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)));

		pack();
	}

	private void carregarPlantaPadraoActionPerformed() {
		this.dispose();

		try {
			File arquivo = new File(getClass().getResource(PLANTA_PADRAO).getFile());
			planta = PlantaController.carregar(arquivo);
		} catch (IOException ex) {
			TelasPopup.mostrarMensagem(ex.getMessage());
			return;
		}
		Prancheta prancheta = new Prancheta(planta, Color.white);
		prancheta.showWindow();
	}

	private void salvarPlantaActionPerformed() {
		File local = TelasPopup.obterArquivoParaSalvar();
		if (local == null) {
			return;
		}

		PlantaController.salvar(planta, local);
	}

	private void botaoFecharActionPerformed() {
		dispose();
	}

	private void inserirMovelActionPerformed() {
		LojaController.adicionarMovel(planta);
	}

	private void exibirVisualizacao3DActionPerformed() {
		Renderizador3DPlanta renderizador3D = new Renderizador3DPlanta(planta);
		renderizador3D.mostrarVisualizacao();
	}

	private void carregarPlantaActionPerformed() {
		File arquivo = TelasPopup.obterArquivoParaAbrir();
		if (arquivo == null) {
			return;
		}

		this.dispose();

		try {
			planta = PlantaController.carregar(arquivo);
		} catch (Exception ex) {
			TelasPopup.mostrarMensagem(ex.getMessage());
			return;
		}
		Prancheta prancheta = new Prancheta(planta, Color.white);
		prancheta.showWindow();
	}

	private javax.swing.JPanel container;
}
