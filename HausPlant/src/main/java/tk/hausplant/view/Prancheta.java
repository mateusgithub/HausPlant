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
import java.text.ParseException;

import tk.hausplant.controller.LojaController;
import tk.hausplant.controller.PlantaController;
import tk.hausplant.model.Planta;

/**
 * Janela onde serão exibidos controles para manipulação da Planta
 */
public class Prancheta extends javax.swing.JFrame {

	private static final String PLANTA_PADRAO = "/planta.dat";

	/**
	 * Quantidade de pixels por metro (para converter pixel para metro)
	 */
	public static final float PIXELS_POR_METRO = 25;

	private final Planta planta;

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
		jPanel2 = new javax.swing.JPanel();
		carregarPlantaPadrao = new javax.swing.JButton();
		carregarPlanta = new javax.swing.JButton();
		inserirMovel = new javax.swing.JButton();
		exibirVisualizacao3D = new javax.swing.JButton();
		salvarPlanta = new javax.swing.JButton();
		botaoFechar = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		container.setLayout(new java.awt.BorderLayout());

		jPanel2.setLayout(new java.awt.GridLayout(1, 0));

		carregarPlantaPadrao.setText("Carregar Planta Padrão");
		carregarPlantaPadrao.addActionListener(evt -> carregarPlantaPadraoActionPerformed(evt));
		jPanel2.add(carregarPlantaPadrao);

		carregarPlanta.setText("Carregar");
		carregarPlanta.addActionListener(evt -> carregarPlantaActionPerformed(evt));
		jPanel2.add(carregarPlanta);

		inserirMovel.setText("Inserir Móvel");
		inserirMovel.addActionListener(evt -> inserirMovelActionPerformed(evt));
		jPanel2.add(inserirMovel);

		exibirVisualizacao3D.setText("Visualização 3D");
		exibirVisualizacao3D.addActionListener(evt -> exibirVisualizacao3DActionPerformed(evt));
		jPanel2.add(exibirVisualizacao3D);

		salvarPlanta.setText("Salvar");
		salvarPlanta.addActionListener(evt -> salvarPlantaActionPerformed(evt));
		jPanel2.add(salvarPlanta);

		botaoFechar.setText("Fechar");
		botaoFechar.addActionListener(evt -> botaoFecharActionPerformed(evt));
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

	private void carregarPlantaPadraoActionPerformed(final java.awt.event.ActionEvent evt) {
		this.dispose();

		PlantaController plantaController = new PlantaController();
		Planta planta;
		try {
			File arquivo = new File(getClass().getResource(PLANTA_PADRAO).getFile());
			planta = PlantaController.carregar(arquivo);
		} catch (Exception ex) {
			TelasPopup.mostrarMensagem(ex.getMessage());
			ex.printStackTrace();
			return;
		}
		Prancheta prancheta = new Prancheta(planta, Color.white);
		prancheta.showWindow();
	}

	private void salvarPlantaActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_salvarPlantaActionPerformed
		File local = TelasPopup.obterArquivoParaSalvar();
		if (local == null) {
			return;
		}

		PlantaController.salvar(planta, local);
	}// GEN-LAST:event_salvarPlantaActionPerformed

	private void botaoFecharActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botaoFecharActionPerformed
		dispose();
	}// GEN-LAST:event_botaoFecharActionPerformed

	private void inserirMovelActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_inserirMovelActionPerformed
		try {
			LojaController.adicionarMovel(planta);
		} catch (IOException ex) {
			TelasPopup.mostrarMensagem("Falha ao carregar itens da loja");
		} catch (ParseException ex) {
			TelasPopup.mostrarMensagem("Falha ao interpretar itens da loja");
		}
	}// GEN-LAST:event_inserirMovelActionPerformed

	private void exibirVisualizacao3DActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_exibirVisualizacao3DActionPerformed
		Renderizador3DPlanta renderizador3D = new Renderizador3DPlanta(planta);

		renderizador3D.mostrarVisualizacao();
	}// GEN-LAST:event_exibirVisualizacao3DActionPerformed

	private void carregarPlantaActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_carregarPlantaActionPerformed
		File arquivo = TelasPopup.obterArquivoParaAbrir();
		if (arquivo == null) {
			return;
		}

		this.dispose();

		PlantaController plantaController = new PlantaController();
		Planta planta;
		try {
			planta = PlantaController.carregar(arquivo);
		} catch (Exception ex) {
			TelasPopup.mostrarMensagem(ex.getMessage());
			return;
		}
		Prancheta prancheta = new Prancheta(planta, Color.white);
		prancheta.showWindow();
	}// GEN-LAST:event_carregarPlantaActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton botaoFechar;
	private javax.swing.JButton carregarPlanta;
	private javax.swing.JButton carregarPlantaPadrao;
	private javax.swing.JPanel container;
	private javax.swing.JButton exibirVisualizacao3D;
	private javax.swing.JButton inserirMovel;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JButton salvarPlanta;
	// End of variables declaration//GEN-END:variables
}
