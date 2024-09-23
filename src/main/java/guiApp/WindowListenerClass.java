package guiApp;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

class WindowListenerClass implements WindowListener
{
    private MainWindow referencia;

    WindowListenerClass(MainWindow referencia)
    {
        this.referencia = referencia;
    }

    @Override
    public void windowActivated(WindowEvent arg0)
    {
    }

    @Override
    public void windowClosed(WindowEvent arg0)
    {
    }

    @Override
    public void windowClosing(WindowEvent arg0)
    {
        JOptionPane.showMessageDialog(this.referencia, "Programa terminando...", SystemInfo.getVersionName(), JOptionPane.INFORMATION_MESSAGE);
        this.referencia.exitInterface();
    }

    @Override
    public void windowDeactivated(WindowEvent arg0)
    {
    }

    @Override
    public void windowDeiconified(WindowEvent arg0)
    {
    }

    @Override
    public void windowIconified(WindowEvent arg0)
    {
    }

    @Override
    public void windowOpened(WindowEvent arg0)
    {
    }

}
