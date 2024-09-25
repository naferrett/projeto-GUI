package guiApp;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

class WindowListenerClass implements WindowListener
{
    private MainWindow reference;

    WindowListenerClass(MainWindow reference)
    {
        this.reference = reference;
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
        JOptionPane.showMessageDialog(this.reference, "Programa fechando...", SystemInfo.getVersionName(), JOptionPane.INFORMATION_MESSAGE);
        this.reference.exitInterface();
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
