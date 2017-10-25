package tk.hausplant.view;

import java.awt.Color;
import spacedrawboard.resource.Material;
import spacedrawboard.resource.Mesh;
import spacedrawboard.resource.Model;
import spacedrawboard.resource.Triangle;
import spacedrawboard.resource.Vector3D;
import spacedrawboard.visualization.Drawboard;
import spacedrawboard.visualization.Visualization;
import tk.hausplant.model.Parede;
import tk.hausplant.model.Planta;
import tk.hausplant.model.Vetor2D;

public class Renderizador3DPlanta {

    /**
     * Distância padrão inicial da câmera em metros
     */
    private final int DISTANCIA_CAMERA_PADRAO = 110;

    /**
     * Planta a ser renderizada
     */
    private final Planta planta;

    /**
     * Objeto da biblioteca SpaceDrawboard, onde serão postos os objetos 3D
     */
    private final Drawboard drawboard = new Drawboard();

    /**
     * Flag para marcar se a planta já foi construída na drawboard
     */
    private boolean construido = false;

    public Renderizador3DPlanta(Planta planta) {
        this.planta = planta;
    }

    /**
     * Construir uma paredes 3D na drawboard usando triângulos
     *
     * @param parede Parede a ser construída
     */
    private void construirParede(Parede parede) {
        // Pixel por metro
        double pxMetro = Prancheta.PIXELS_POR_METRO;

        Material materialParede = new Material(Parede.COR_PADRAO);

        // Cantos da parede
        Vector3D A = new Vector3D(parede.getA().x, -parede.getA().y, 0);
        Vector3D B = new Vector3D(parede.getB().x, -parede.getB().y, 0);

        // Ajustar escalas dos cantos (converter de pixel para metro)
        A = A.timesScalar(1 / pxMetro);
        B = B.timesScalar(1 / pxMetro);

        // Cantos superiores da parede
        Vector3D Ah = A.clone();
        Vector3D Bh = B.clone();
        Ah.z = Bh.z = Parede.ALTURA_PADRAO;

        // Vetores para dar largura à parede
        Vetor2D paralelo = new Vetor2D(A.x, A.y).minus(new Vetor2D(B.x, B.y));
        Vector3D paral = new Vector3D(paralelo.x, paralelo.y, 0);
        paral.normalize();
        Vetor2D perpendicular = paralelo.getOrthogonal();
        perpendicular.normalize();
        Vector3D perp = new Vector3D(perpendicular.x, perpendicular.y, 0);
        perp = perp.timesScalar(Parede.LARGURA_PADRAO);
        Vector3D perpNeg = perp.clone().timesScalar(-1);

        // Formar os triângulos da parede
        Mesh formaParede = new Mesh();

        // Lados
        formaParede.addTriangle(new Triangle(A.plus(perp), Ah.plus(perp), Bh.plus(perp)));
        formaParede.addTriangle(new Triangle(Bh.plus(perp), B.plus(perp), A.plus(perp)));

        formaParede.addTriangle(new Triangle(Bh.plus(perpNeg), Ah.plus(perpNeg), A.plus(perpNeg)));
        formaParede.addTriangle(new Triangle(A.plus(perpNeg), B.plus(perpNeg), Bh.plus(perpNeg)));

        // Frentes
        formaParede.addTriangle(new Triangle(A.plus(perpNeg), Ah.plus(perpNeg), Ah.plus(perp)));
        formaParede.addTriangle(new Triangle(Ah.plus(perp), A.plus(perp), A.plus(perpNeg)));

        formaParede.addTriangle(new Triangle(B.plus(perp), Bh.plus(perp), Bh.plus(perpNeg)));
        formaParede.addTriangle(new Triangle(Bh.plus(perpNeg), B.plus(perpNeg), B.plus(perp)));

        // Calha
        Material materialCalha = new Material(new Color(50, 50, 50));
        Mesh formaCalha = new Mesh();

        Vector3D alturaCalha = new Vector3D(0, 0, 0.1);
        double c = 1;

        // Cantos inferiores da calha
        Vector3D c1 = Ah.plus(perpNeg);
        Vector3D c2 = Ah.plus(perp);
        Vector3D c3 = Bh.plus(perp);
        Vector3D c4 = Bh.plus(perpNeg);

        // Face inferior da calha
        formaCalha.addTriangle(new Triangle(c1, c2, c3));
        formaCalha.addTriangle(new Triangle(c3, c4, c1));

        Model modeloParede = new Model(formaParede, materialParede);
        Model modeloCalha = new Model(formaCalha, materialCalha);
        drawboard.addModel(modeloParede);
        drawboard.addModel(modeloCalha);
    }

    private void construirCasa() {
        if (construido) {
            return;
        }
        construido = true;

        // Construir paredes
        for (Parede parede : planta.getParedes()) {
            construirParede(parede);
        }
    }

    public void mostrarVisualizacao() {
        construirCasa();

        // Pixel por metro
        double pxMetro = Prancheta.PIXELS_POR_METRO;

        // Centralizar visualização
        double somaX = 0,
                somaY = 0;

        for (Parede parede : planta.getParedes()) {
            somaX += (parede.getA().x + parede.getB().x) / 2;
            somaY += (-parede.getA().y - parede.getB().y) / 2;
        }

        double centroX = 0, centroY = 0;

        int numeroParedes = planta.getParedes().size();

        if (numeroParedes > 0) {
            centroX = (somaX / numeroParedes) / pxMetro;
            centroY = (somaY / numeroParedes) / pxMetro;
        }

        // Mostrar visualização da cena
        Visualization visualizacao = new Visualization("Planta", 900, 600, drawboard);
        visualizacao.setFocusPoint(new Vector3D(
                centroX, centroY, Parede.ALTURA_PADRAO / 2
        ));
        visualizacao.setCameraDistance(DISTANCIA_CAMERA_PADRAO);
        visualizacao.setCameraLatitudeAngle(35);
        visualizacao.setCameraLongitudeAngle(35);
        visualizacao.setZoomSpeed(3);
        visualizacao.setDragSpeed(0.025);
        visualizacao.showWindow();
    }

}
