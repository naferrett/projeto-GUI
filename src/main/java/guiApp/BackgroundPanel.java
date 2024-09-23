package guiApp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Random;

import javax.swing.JPanel;

class BackgroundPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private int               minLinhas;
    private int               maxLinhas;
    private int               maxAtratores;
    private Color             corFrente;
    private Color             corFundo;
    private Point             atratores[];
    private Random            rand;

    BackgroundPanel() {
        super();

        this.minLinhas = 12;
        this.maxLinhas = 48;
        this.maxAtratores = 4;
        this.corFrente = Color.white;
        this.corFundo = Color.lightGray;
        this.atratores = new Point[maxAtratores];
        this.rand = new Random();

        this.setForeground(corFrente);
        this.setBackground(corFundo);
    }

    @Override
    public void paint(Graphics canvasOriginal) {
        super.paint(canvasOriginal);
        Graphics2D canvas = (Graphics2D) canvasOriginal;

        setForeground(corFrente);
        setBackground(corFundo);

        int maxX = this.getWidth();
        int maxY = this.getHeight();

        int limite = this.minLinhas + rand.nextInt(this.maxLinhas - this.minLinhas);

        for (int qtasLinhas = 0; qtasLinhas < limite; qtasLinhas++)
        {
            for (Point atratorCorrente : atratores)
            {
                if (atratorCorrente != null)
                {
                    canvas.drawLine((int) atratorCorrente.getX(), (int) atratorCorrente.getY(), rand.nextInt(maxX), rand.nextInt(maxY));
                }
            }
        }
    }

    void setCorFrente(Color novaCor)
    {
        this.corFrente = novaCor;
    }

    void setCorFundo(Color novaCor)
    {
        this.corFundo = novaCor;
    }

    public void setNewAttractor(Point novoAtrator)
    {
        for (int indice = 0; indice < (atratores.length - 1); indice++)
        {
            atratores[indice] = atratores[indice + 1];
        }
        atratores[atratores.length - 1] = novoAtrator;
    }
}
