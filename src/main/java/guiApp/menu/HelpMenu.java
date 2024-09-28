/*
 * É responsável por criar o menu "Ajuda" da aplicação, incluindo opções para exibir mensagens de ajuda e informações sobre a aplicação.
 */

package guiApp.menu;

import javax.swing.*;

public class HelpMenu {
    JMenu createHelpMenu() {
        JMenu helpMenu = new JMenu("Ajuda");
        helpMenu.setMnemonic('J');

        helpMenu.add(createHelpItem());
        helpMenu.add(createAboutItem());

        return helpMenu;
    }

    private JMenuItem createHelpItem() {
        JMenuItem menuItemHelp = new JMenuItem("Ajuda");
        menuItemHelp.setMnemonic('H');
        menuItemHelp.setActionCommand("Help Message");
        return menuItemHelp;
    }

    private JMenuItem createAboutItem() {
        JMenuItem menuItemAbout = new JMenuItem("Sobre");
        menuItemAbout.setMnemonic('A');
        menuItemAbout.setActionCommand("About Message");
        return menuItemAbout;
    }
}
