/*
 * A classe MenuHandler é responsável por criar e adicionar os menus à barra de menus da janela principal,
 * inserindo os menus de Arquivo, Configuração e Ajuda.
 */

package guiApp.menu;

import guiApp.MainWindow;
import guiApp.menu.configMenu.ConfigurationMenu;

import javax.swing.*;

public class MenuHandler {
    private final MainWindow mainWindow;

    public MenuHandler(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void createAddToMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add((new FileMenu()).createFileMenu());
        menuBar.add(new ConfigurationMenu().createConfigMenu());
        menuBar.add(new HelpMenu().createHelpMenu());
        mainWindow.setJMenuBar(menuBar);
    }
}
