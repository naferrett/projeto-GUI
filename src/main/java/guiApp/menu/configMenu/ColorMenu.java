/*
 * Cria um submenu de seleção de cores para alterar o fundo da interface gráfica.
 */

package guiApp.menu.configMenu;

import javax.swing.*;

public class ColorMenu {
    JMenu createColorMenu() {
        JMenu menuItemChooseColor = new JMenu("Alterar Cor");
        menuItemChooseColor.setMnemonic('R');

        menuItemChooseColor.add(createPinkItem());
        menuItemChooseColor.add(createRedItem());
        menuItemChooseColor.add(createBlueItem());
        menuItemChooseColor.add(createGrayItem());

        return menuItemChooseColor;
    }

    private JMenuItem createPinkItem() {
        JMenuItem menuItemPink = new JMenuItem("Rosa");
        menuItemPink.setActionCommand("Pink Background");
        return menuItemPink;
    }

    private JMenuItem createRedItem() {
        JMenuItem menuItemRed = new JMenuItem("Vermelho");
        menuItemRed.setActionCommand("Red Background");
        return menuItemRed;
    }

    private JMenuItem createBlueItem() {
        JMenuItem menuItemBlue = new JMenuItem("Azul");
        menuItemBlue.setActionCommand("Blue Background");
        return menuItemBlue;
    }

    private JMenuItem createGrayItem() {
        JMenuItem menuItemGray = new JMenuItem("Cinza");
        menuItemGray.setActionCommand("Gray Background");
        return menuItemGray;
    }
}

