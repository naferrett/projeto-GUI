/*
 * Cria um submenu de seleção de velocidades para alterar a velocidade da animação do funda da interface gráfica.
 */

package guiApp.menu.configMenu;

import javax.swing.*;

public class SpeedMenu {
    JMenu createSpeedMenu() {
        JMenu menuItemChooseSpeed = new JMenu("Alterar Velocidade");
        menuItemChooseSpeed.setMnemonic('D');

        menuItemChooseSpeed.add(create05xItem());
        menuItemChooseSpeed.add(create1xItem());
        menuItemChooseSpeed.add(create2xItem());

        return menuItemChooseSpeed;
    }

    private JMenuItem create05xItem() {
        JMenuItem menuItemSpeed05x = new JMenuItem("Velocidade 0.5x");
        menuItemSpeed05x.setActionCommand("Speed 0.5x");
        return menuItemSpeed05x;
    }

    private JMenuItem create1xItem() {
        JMenuItem menuItemSpeed1x = new JMenuItem("Velocidade 1x");
        menuItemSpeed1x.setActionCommand("Speed 1x");
        return menuItemSpeed1x;
    }

    private JMenuItem create2xItem() {
        JMenuItem menuItemSpeed2x = new JMenuItem("Velocidade 2x");
        menuItemSpeed2x.setActionCommand("Speed 2x");
        return menuItemSpeed2x;
    }
}
