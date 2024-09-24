package guiApp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Random;

import javax.swing.JPanel;

class BackgroundPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private int minLines;
    private int maxLines;
    private int maxAtratores;
    private Color foregroundColor;
    private Color backgroundColor;
    private Point[] atratores;
    private Random rand;

    BackgroundPanel() {
        super();

        this.minLines = 12;
        this.maxLines = 48;
        this.maxAtratores = 4;
        this.foregroundColor = Color.white;
        this.backgroundColor = Color.lightGray;
        this.atratores = new Point[maxAtratores];
        this.rand = new Random();

        this.setForeground(foregroundColor);
        this.setBackground(backgroundColor);
    }

    @Override
    protected void paintComponent(Graphics originalCanvas) {
        super.paintComponent(originalCanvas);
        Graphics2D canvas = (Graphics2D) originalCanvas;

        canvas.setColor(foregroundColor);
        setBackground(backgroundColor);

        int maxX = this.getWidth();
        int maxY = this.getHeight();

        int limit = this.minLines + rand.nextInt(this.maxLines - this.minLines);

        for (int lineCount = 0; lineCount < limit; lineCount++) {
            for (Point atratorCorrente : atratores) {
                if (atratorCorrente != null) {
                    canvas.drawLine((int) atratorCorrente.getX(), (int) atratorCorrente.getY(), rand.nextInt(maxX), rand.nextInt(maxY));
                }
            }
        }
    }

    public void setForegroundColor(Color newColor) {
        this.foregroundColor = newColor;
        this.setForeground(newColor);
        repaint();
    }

    public void setBackgroundColor(Color newColor) {
        this.backgroundColor = newColor;
        setBackground(newColor);
        repaint();
    }

    public void setNewAttractor(Point newAttractor) {
        for (int index = 0; index < (atratores.length - 1); index++) {
            atratores[index] = atratores[index + 1];
        }
        atratores[atratores.length - 1] = newAttractor;
        repaint();
    }
}