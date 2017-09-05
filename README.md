# HausPlant
*Projeto em Java para a disciplina Programação Orientada a Objetos 2 (SI400)*

Sistema para projeto e design de plantas arquitetônicas.

Projeto utilizará a arquitetura MVC (model, view, controller). Persistência de dados em arquivos.

## Membros da equipe
* Marcos Ramon Ramalho - RA: 183414
* Mateus Hikari Tanaka - RA: 184082
* Sérgio Ferreira Batista Filho - RA: 187064

## Classes do sistema
### Camada Model
#### Pacote model
* **Planta**
  Planta da casa que poderá conter paredes, móveis e telhados (Objetos 3D)
* **Objeto3D**
  Objeto tridimensional genérico
* **Parede**
  Descrição das características de uma parede, podendo conter portas e janelas (extende Objeto3D)
* **Porta**
  Descrição das características de uma porta, a qual está ligada à uma parede (extende Objeto3D)
* **Janela**
  Descrição das características de uma janela, a qual está ligada à uma parede (extende Objeto3D)
* **Telhado**
  Armazena informações sobre as características do telhado
* **Movel**
  Objeto tridimensional que pode ser colocado na planta (extende Objeto3D)
* **PlantaDAO**
  Objeto DAO responsável por salvar e carregar plantas de casas a partir de arquivos
* **MovelDAO**
  Objeto DAO responsável por carregar móveis a partir de arquivos

### Camada View
#### Pacote prancheta
* **Prancheta**
  Responsável por gerar uma visualização 2D de uma planta (Extende java.awt.Canvas)
* **ManipuladorMouse**
  Receberá eventos ocorridos sobre a Prancheta e fará modificações na planta conforme solicitado pelo usuário
* **JanelaPrancheta**
  Janela onde serão exibidos controles e o componente Prancheta em si para manipulação da Planta
* **Vetor2D**
  Efetua operações aritiméticas sobre um vetor 2D.
* **Visualizacao3D**
  Onde será construida e apresentada a visualização 3D de uma planta.
#### Pacote loja
* **Loja**
  Onde estarão disponíveis os objetos que poderão ser colocados na planta

### Camada Controller
* **HausPlant**
  Ponto de partida e controle principal do fluxo do programa
* **Projeto**
  Controle sobre o projeto sendo trabalhado no momento
* **Biblioteca**
  Controle sobre objetos que serão importados pela loja. Irá carregar objetos à partir de arquivos

## Bibliotecas a serem utilizadas
* **SpaceDrawboard Library** - Para visualização de cenas tridimensionais
* **JSON Parser** - Para interpretação de arquivos


