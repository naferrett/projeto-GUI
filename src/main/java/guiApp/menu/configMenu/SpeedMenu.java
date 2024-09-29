/*
 * Cria um submenu de seleção de velocidades para alterar a velocidade da animação do funda da interface gráfica.
 */

package guiApp.menu.configMenu;

import javax.swing.*;

public class SpeedMenu {
    JMenu createSpeedMenu() {
        JMenu menuItemChooseSpeed = new JMenu("Alterar Velocidade");
        menuItemChooseSpeed.setMnemonic('D');

        menuItemChooseSpeed.add(create0_5xItem());
        menuItemChooseSpeed.add(create1xItem());
        menuItemChooseSpeed.add(create1_5xItem());
        menuItemChooseSpeed.add(create2xItem());

        return menuItemChooseSpeed;
    }

    private JMenuItem create0_5xItem() {
        JMenuItem menuItemSpeed0_5x = new JMenuItem("Velocidade 0.5x");
        menuItemSpeed0_5x.setActionCommand("Speed 0.5x");
        return menuItemSpeed0_5x;
    }

    private JMenuItem create1xItem() {
        JMenuItem menuItemSpeed1x = new JMenuItem("Velocidade 1x");
        menuItemSpeed1x.setActionCommand("Speed 1x");
        return menuItemSpeed1x;
    }

    private JMenuItem create1_5xItem() {
        JMenuItem menuItemSpeed1_5x = new JMenuItem("Velocidade 1.5x");
        menuItemSpeed1_5x.setActionCommand("Speed 1.5x");
        return menuItemSpeed1_5x;
    }

    private JMenuItem create2xItem() {
        JMenuItem menuItemSpeed2x = new JMenuItem("Velocidade 2x");
        menuItemSpeed2x.setActionCommand("Speed 2x");
        return menuItemSpeed2x;
    }
}
