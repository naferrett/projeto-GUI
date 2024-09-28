/*
 * Cria um submenu de seleção de padrões para alterar o fundo da interface gráfica.
 */

package guiApp.menu.configMenu;

import javax.swing.*;

public class PatternMenu {

    JMenu createPatternMenu() {
        JMenu menuItemChoosePattern = new JMenu("Alterar Padrão");
        menuItemChoosePattern.setMnemonic('P');

        menuItemChoosePattern.add(createRectangleItem());
        menuItemChoosePattern.add(createTriangleItem());
        menuItemChoosePattern.add(createStarItem());
        menuItemChoosePattern.add(createNoPatternItem());

        return menuItemChoosePattern;
    }

    private JMenuItem createRectangleItem() {
        JMenuItem menuItemRectanglePattern = new JMenuItem("Retângulo");
        menuItemRectanglePattern.setActionCommand("Rectangle Pattern");
        return menuItemRectanglePattern;
    }

    private JMenuItem createTriangleItem() {
        JMenuItem menuItemTrianglePattern = new JMenuItem("Triângulo");
        menuItemTrianglePattern.setActionCommand("Triangle Pattern");
        return menuItemTrianglePattern;
    }

    private JMenuItem createStarItem() {
        JMenuItem menuItemStarPattern = new JMenuItem("Estrela");
        menuItemStarPattern.setActionCommand("Star Pattern");
        return menuItemStarPattern;
    }

    private JMenuItem createNoPatternItem() {
        JMenuItem menuItemNoPattern = new JMenuItem("Limpar");
        menuItemNoPattern.setActionCommand("No Pattern");
        return menuItemNoPattern;
    }
}
