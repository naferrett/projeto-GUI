/* Essa classe é responsável por carreagr e exibir uma imagem. */

package guiApp;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.io.Serial;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

class ImageDisplayPanel extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;
    private Image currentImage;
    private Graphics2D auxGraphics;

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        auxGraphics = (Graphics2D) g;

        try {
            URL auxURL = this.getClass().getResource(SystemInfo.systemImage);
            currentImage = ImageIO.read(auxURL);
            auxGraphics.drawImage(currentImage, 20, 50, 180, 190, 0, 0, currentImage.getWidth(null), currentImage.getHeight(null), null);
        } catch (IOException e) {
            System.out.println("Arquivo de imagem não encontrado. " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao carregar arquivo de imagem. " + e.getMessage());
        }
    }

}
