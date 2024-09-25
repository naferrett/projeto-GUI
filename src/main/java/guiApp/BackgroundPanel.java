package guiApp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Random;

import javax.swing.JPanel;

class BackgroundPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private Color foregroundColor;
    private Color backgroundColor;
    private Point[] attractors;
    private int currentLine = 0;

    BackgroundPanel() {
        super();
        this.foregroundColor = Color.white;
        this.backgroundColor = Color.lightGray;
//        this.attractors = new Point[maxAtratores];
//        this.rand = new Random();

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
            int nextIndex = (i + 1) % attractors.length;
            originalCanvas.drawLine(attractors[i].x, attractors[i].y, attractors[nextIndex].x, attractors[nextIndex].y);
        }
    }

//    @Override
//    protected void paintComponent(Graphics originalCanvas) {
//        super.paintComponent(originalCanvas);
//        Graphics2D canvas = (Graphics2D) originalCanvas;
//
//        canvas.setColor(foregroundColor);
//        setBackground(backgroundColor);
//
//        int maxX = this.getWidth();
//        int maxY = this.getHeight();
//
//        int limit = 1;
//
//        for (int lineCount = 0; lineCount < limit; lineCount++) {
//            for (Point currentAttractor : attractors) {
//                if (currentAttractor != null) {
//                    canvas.drawLine((int) currentAttractor.getX(), (int) currentAttractor.getY(), rand.nextInt(maxX), rand.nextInt(maxY));
//                }
//            }
//        }
//
//    }

//    public void setForegroundColor(Color newColor) {
//        this.foregroundColor = newColor;
//        this.setForeground(newColor);
//        repaint();
//    }

    public void setBackgroundColor(Color newColor) {
        this.backgroundColor = newColor;
        setBackground(newColor);
        repaint();
    }

    public void setNoPattern() {
        this.attractors = null;
        currentLine = 0;
        repaint();
    }

    public void setPatternRectangle() {
        this.attractors = new Point[4];

        int maxY = this.getHeight();
        int maxX = this.getWidth();

        int marginX = (int)(0.025 * this.getWidth());
        int marginY = (int)(0.05 * this.getHeight());

        attractors[0] = new Point(marginX, marginY);
        attractors[1] = new Point(maxX - marginX, marginY);
        attractors[2] = new Point(maxX - marginX, maxY - marginY);
        attractors[3] = new Point(marginX, maxY - marginY);

        currentLine = 0;
        repaint();
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
    }

    public void setPatternStar() {
        this.attractors = new Point[10];

        int maxY = this.getHeight();
        int maxX = this.getWidth();

        int marginX = (int)(0.025 * maxX);
        int marginY = (int)(0.05 * maxY);

        attractors[0] = new Point(maxX / 2, marginY);                        // Topo
        attractors[1] = new Point((int)(0.6 * maxX), (int)(0.3 * maxY));     // Parte inferior direita
        attractors[2] = new Point(maxX - marginX, (int)(0.3 * maxY));        // Extremo direito
        attractors[3] = new Point((int)(0.7 * maxX), (int)(0.6 * maxY));     // Extremo direito inferior
        attractors[4] = new Point((int)(0.8 * maxX), maxY - marginY);        // Ponta inferior direita
        attractors[5] = new Point(maxX / 2, (int)(0.75 * maxY));             // Ponta inferior
        attractors[6] = new Point((int)(0.2 * maxX), maxY - marginY);        // Ponta inferior esquerda
        attractors[7] = new Point((int)(0.3 * maxX), (int)(0.6 * maxY));     // Extremo esquerdo inferior
        attractors[8] = new Point(marginX, (int)(0.3 * maxY));               // Extremo esquerdo superior
        attractors[9] = new Point((int)(0.4 * maxX), (int)(0.3 * maxY));     // Parte superior esquerda

        currentLine = 0;
        repaint();
    }


    public void nextLine() {
        if (this.attractors == null || this.attractors.length == 0) {
            return;
        }

        currentLine++;
        if (currentLine > attractors.length) {
            currentLine = 0;
        }
        repaint();
    }

//    public void setNewAttractor(Point newAttractor) {
//        // Movendo atratores para a esquerda e adicionando o novo atrator no final
//        for (int index = 0; index < (attractors.length - 1); index++) {
//            attractors[index] = attractors[index + 1];
//        }
//        attractors[attractors.length - 1] = newAttractor;
//        repaint();
//    }
}