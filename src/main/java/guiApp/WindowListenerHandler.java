/* Esta classe implementa a interface WindowListener para gerenciar eventos relacionados
 * à janela principal da aplicação. Ela escuta eventos de ciclo de vida da janela, como
 * abertura, fechamento, minimização e ativação. Quando a janela está prestes a ser fechada,
 * exibe uma mensagem ao usuário e executa o método de encerramento da interface principal.*/

package guiApp;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

class WindowListenerHandler implements WindowListener {
    private final MainWindow reference;

    WindowListenerHandler(MainWindow reference)
    {
        this.reference = reference;
    }

    @Override
    public void windowActivated(WindowEvent arg0) {}

    @Override
    public void windowClosed(WindowEvent arg0) {}

    @Override
    public void windowClosing(WindowEvent arg0) {
        JOptionPane.showMessageDialog(this.reference, "Fechando programa...", SystemInfo.getVersionName(), JOptionPane.INFORMATION_MESSAGE);
        this.reference.exitInterface();
    }

    @Override
    public void windowDeactivated(WindowEvent arg0) {}

    @Override
    public void windowDeiconified(WindowEvent arg0) {}

    @Override
    public void windowIconified(WindowEvent arg0) {}

    @Override
    public void windowOpened(WindowEvent arg0) {}

}
