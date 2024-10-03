/* Essa classe é responsável por definir a aparência do fundo da interface
* gráfica, definindo os desenhos realizados pelas threads em um fundo
* dinâmico com base nas dimensões da janela. */

package guiApp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serial;

import javax.swing.JPanel;

class BackgroundPanel extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Color foregroundColor;
    private Color backgroundColor;
    private Point[] attractors;
    private int currentLine = 0;

    BackgroundPanel() {
        super();
        this.foregroundColor = Color.white;
        this.backgroundColor = Color.lightGray;

        this.setForeground(foregroundColor);
        this.setBackground(backgroundColor);
    }

    @Override
    protected void paintComponent(Graphics originalCanvas) {
        super.paintComponent(originalCanvas);
        Graphics2D canvas = (Graphics2D) originalCanvas;
        canvas.setColor(foregroundColor);
        setBackground(backgroundColor);

        for (int i = 0; i < currentLine; i++) {
            int nextIndex = (i + 1) % attractors.length; // 'nextIndex' volta a ser 0 quando estiver no último ponto do desenho.
            originalCanvas.drawLine(attractors[i].x, attractors[i].y, attractors[nextIndex].x, attractors[nextIndex].y);
        }
    }

    public void setBackgroundColor(Color newColor) {
        this.backgroundColor = newColor;
        setBackground(newColor);
        repaint();
    }

    public void setNoPattern() {
        this.attractors = null;
        currentLine = 0;
    }

    public void setPatternRectangle() {
        this.attractors = new Point[4];

        int maxY = this.getHeight();
        int maxX = this.getWidth();

        int marginX = (int)(0.05 * this.getWidth());
        int marginY = (int)(0.075 * this.getHeight());

        attractors[0] = new Point(marginX, marginY);
        attractors[1] = new Point(maxX - marginX, marginY);
        attractors[2] = new Point(maxX - marginX, maxY - marginY);
        attractors[3] = new Point(marginX, maxY - marginY);

        currentLine = 0;
    }

    public void setPatternTriangle() {
        this.attractors = new Point[3];
        int maxY = this.getHeight();
        int maxX = this.getWidth();

        int marginX = (int)(0.025 * maxX);
        int marginY = (int)(0.05 * maxY);

        attractors[0] = new Point(maxX / 2, marginY);
        attractors[1] = new Point(maxX - marginX, maxY - marginY);
        attractors[2] = new Point(marginX, maxY - marginY);

        currentLine = 0;
    }

    public void setPatternStar() {
        this.attractors = new Point[10];

        int maxY = this.getHeight();
        int maxX = this.getWidth();

        int marginX = (int)(0.025 * maxX);
        int marginY = (int)(0.05 * maxY);

        attractors[0] = new Point(maxX / 2, marginY);
        attractors[1] = new Point((int)(0.6 * maxX), (int)(0.3 * maxY));
        attractors[2] = new Point(maxX - marginX, (int)(0.3 * maxY));
        attractors[3] = new Point((int)(0.7 * maxX), (int)(0.6 * maxY));
        attractors[4] = new Point((int)(0.8 * maxX), maxY - marginY);
        attractors[5] = new Point(maxX / 2, (int)(0.75 * maxY));
        attractors[6] = new Point((int)(0.2 * maxX), maxY - marginY);
        attractors[7] = new Point((int)(0.3 * maxX), (int)(0.6 * maxY));
        attractors[8] = new Point(marginX, (int)(0.3 * maxY));
        attractors[9] = new Point((int)(0.4 * maxX), (int)(0.3 * maxY));

        currentLine = 0;
    }

    public void nextLine() {
        if (this.attractors == null) {
            return;
        }

        currentLine++;
        if (currentLine > attractors.length) {
            currentLine = 0; // Quando o desenho acabar, 'currentLine' é incrementado mais uma vez, fica maior que 'attractors.lenght', tem seu valor atribuído a 0 e o desenho recomeça.
        }
    }
}