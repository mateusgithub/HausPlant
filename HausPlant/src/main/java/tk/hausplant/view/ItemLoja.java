/**
 * HausPlant
 *
 * 2017
 * Equipe desenvolvedora do HausPlant
 */
package tk.hausplant.view;

import javax.swing.JLabel;

/**
 * Responsável por gerenciar os itens disponíveis na loja de objetos 3D
 */
public class ItemLoja extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Nome do item
	 */
	private final String nome;

	/**
	 * Local do arquivo contendo a foto do item
	 */
	private final String localFoto;

	/**
	 * Local do arquivo contendo o modelo 3d do item
	 */
	private final String localModelo;

	public ItemLoja(final String nome, final String localModelo, final String localFoto) {

		initComponents();

		this.nome = nome;
		this.localFoto = localFoto;
		this.localModelo = localModelo;

		nomeLabel.setText(nome);
	}

	public String getLocalModelo() {
		return localModelo;
	}

	public String getLocalFoto() {
		return localFoto;
	}

	public String getNome() {
		return nome;
	}

	private void initComponents() {
		nomeLabel = new javax.swing.JLabel();
		JLabel imagemComponente = new javax.swing.JLabel();

		nomeLabel.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
		nomeLabel.setText("nome");

		imagemComponente.setBackground(new java.awt.Color(58, 58, 58));
		imagemComponente.setForeground(new java.awt.Color(254, 254, 254));
		imagemComponente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		imagemComponente.setText("Imagem");
		imagemComponente.setOpaque(true);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(imagemComponente, javax.swing.GroupLayout.PREFERRED_SIZE, 101,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(nomeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(imagemComponente, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(nomeLabel, javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE));
	}

	private javax.swing.JLabel nomeLabel;
}
