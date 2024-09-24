package teste;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class ColorChangingGUIExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
    }
}

class MainWindow extends JFrame {
    private BackgroundPanel backgroundPanel;

    MainWindow() {
        setTitle("Color Changing GUI Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        backgroundPanel = new BackgroundPanel();
        add(backgroundPanel);

        JButton changeColorButton = new JButton("Change Background Color");
        changeColorButton.addActionListener(e -> changeBackgroundColor());

        add(changeColorButton, BorderLayout.SOUTH);
    }

    private void changeBackgroundColor() {
        Color newColor = JColorChooser.showDialog(this, "Choose Background Color", backgroundPanel.getBackground());
        if (newColor != null) {
            backgroundPanel.setBackgroundColor(newColor);
        }
    }
}

class BackgroundPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private int minLines = 12;
    private int maxLines = 48;
    private int maxAtratores = 4;
    private Color foregroundColor = Color.WHITE;
    private Color backgroundColor = Color.LIGHT_GRAY;
    private Point[] atratores = new Point[maxAtratores];
    private Random rand = new Random();

    BackgroundPanel() {
        setBackground(backgroundColor);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(foregroundColor);

        int maxX = getWidth();
        int maxY = getHeight();

        int limit = minLines + rand.nextInt(maxLines - minLines);

        for (int lineCount = 0; lineCount < limit; lineCount++) {
            for (Point atratorCorrente : atratores) {
                if (atratorCorrente != null) {
                    g2d.drawLine((int) atratorCorrente.getX(), (int) atratorCorrente.getY(),
                            rand.nextInt(maxX), rand.nextInt(maxY));
                }
            }
        }
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        setBackground(color);
        repaint();
    }

    public void setForegroundColor(Color color) {
        this.foregroundColor = color;
        repaint();
    }

    public void setNewAttractor(Point newAttractor) {
        System.arraycopy(atratores, 1, atratores, 0, atratores.length - 1);
        atratores[atratores.length - 1] = newAttractor;
        repaint();
    }
}