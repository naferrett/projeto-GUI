package guiApp.menu;

import javax.swing.*;

public class FileMenu {

    JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("Arquivo");
        fileMenu.setMnemonic('A');

        fileMenu.add(createOpenFileItem());
        fileMenu.add(createCloseFileItem());
        fileMenu.addSeparator();
        fileMenu.add(createExitItem());

        return fileMenu;
    }

    private JMenuItem createOpenFileItem() {
        JMenuItem menuItemOpenFile = new JMenuItem("Abrir Arquivo");
        menuItemOpenFile.setActionCommand("Open File");
        menuItemOpenFile.setMnemonic('B');
        return menuItemOpenFile;
    }

    private JMenuItem createCloseFileItem() {
        JMenuItem menuItemCloseFile = new JMenuItem("Fechar Arquivo");
        menuItemCloseFile.setActionCommand("Close File");
        menuItemCloseFile.setMnemonic('F');
        return menuItemCloseFile;
    }

    private JMenuItem createExitItem() {
        JMenuItem menuItemExit = new JMenuItem("Saída");
        menuItemExit.setActionCommand("Exit");
        menuItemExit.setMnemonic('S');
        return menuItemExit;
    }

}
