package guiApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuHandler {
    private MainWindow mainWindow;


    MenuHandler(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    void createAddToMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createConfigMenu());
        menuBar.add(createHelpMenu());
        mainWindow.setJMenuBar(menuBar);
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("Arquivo");
        fileMenu.setMnemonic('A');

        JMenuItem menuItemOpenFile = new JMenuItem("Abrir Arquivo");
        menuItemOpenFile.setActionCommand("Open File");
        menuItemOpenFile.setMnemonic('B');
        fileMenu.add(menuItemOpenFile);

        JMenuItem menuItemCloseFile = new JMenuItem("Fechar Arquivo");
        menuItemCloseFile.setActionCommand("Close File");
        menuItemCloseFile.setMnemonic('F');
        fileMenu.add(menuItemCloseFile);

        fileMenu.addSeparator();

        JMenuItem menuItemExit = new JMenuItem("Saída");
        menuItemExit.setActionCommand("Exit");
        menuItemExit.setMnemonic('S');
        fileMenu.add(menuItemExit);

        return fileMenu;
    }

    private JMenu createConfigMenu() {
        JMenu configMenu = new JMenu("Configuração");
        configMenu.setMnemonic('C');

        configMenu.add(createPatternMenu());
        configMenu.add(createColorMenu());
        configMenu.add(createSpeedMenu());

        return configMenu;
    }

    private JMenu createPatternMenu() {
        JMenu menuItemChoosePattern = new JMenu("Alterar Padrão");
        menuItemChoosePattern.setMnemonic('P');

        JMenuItem menuItemRectanglePattern = new JMenuItem("Retângulo");
        menuItemRectanglePattern.setActionCommand("Rectangle Pattern");
        menuItemChoosePattern.add(menuItemRectanglePattern);

        JMenuItem menuItemTrianglePattern = new JMenuItem("Triângulo");
        menuItemTrianglePattern.setActionCommand("Triangle Pattern");
        menuItemChoosePattern.add(menuItemTrianglePattern);

        JMenuItem menuItemStarPattern = new JMenuItem("Estrela");
        menuItemStarPattern.setActionCommand("Star Pattern");
        menuItemChoosePattern.add(menuItemStarPattern);

        JMenuItem menuItemNoPattern = new JMenuItem("Limpar");
        menuItemNoPattern.setActionCommand("No Pattern");
        menuItemChoosePattern.add(menuItemNoPattern);

        return menuItemChoosePattern;
    }

    private JMenu createColorMenu() {
        JMenu menuItemChooseColor = new JMenu("Alterar Cor");
        menuItemChooseColor.setMnemonic('R');

        JMenuItem menuItemPink = new JMenuItem("Rosa");
        menuItemPink.setActionCommand("Pink Background");
        menuItemChooseColor.add(menuItemPink);

        JMenuItem menuItemRed = new JMenuItem("Vermelho");
        menuItemRed.setActionCommand("Red Background");
        menuItemChooseColor.add(menuItemRed);

        JMenuItem menuItemBlue = new JMenuItem("Azul");
        menuItemBlue.setActionCommand("Blue Background");
        menuItemChooseColor.add(menuItemBlue);

        JMenuItem menuItemGray = new JMenuItem("Cinza");
        menuItemGray.setActionCommand("Gray Background");
        menuItemChooseColor.add(menuItemGray);

        return menuItemChooseColor;
    }

    private JMenu createSpeedMenu() {
        JMenu menuItemChooseSpeed = new JMenu("Alterar Velocidade");
        menuItemChooseSpeed.setMnemonic('D');

        JMenuItem menuItemSpeed05x = new JMenuItem("Velocidade 0.5x");
        menuItemSpeed05x.setActionCommand("Speed 0.5x");
        menuItemChooseSpeed.add(menuItemSpeed05x);

        JMenuItem menuItemSpeed1x = new JMenuItem("Velocidade 1x");
        menuItemSpeed1x.setActionCommand("Speed 1x");
        menuItemChooseSpeed.add(menuItemSpeed1x);

        JMenuItem menuItemSpeed2x = new JMenuItem("Velocidade 2x");
        menuItemSpeed2x.setActionCommand("Speed 2x");
        menuItemChooseSpeed.add(menuItemSpeed2x);

        return menuItemChooseSpeed;
    }

    private JMenu createHelpMenu() {
        JMenu helpMenu = new JMenu("Ajuda");
        helpMenu.setMnemonic('J');

        JMenuItem menuItemHelp = new JMenuItem("Ajuda");
        menuItemHelp.setActionCommand("Help Message");
        helpMenu.add(menuItemHelp);

        JMenuItem menuItemAbout = new JMenuItem("Sobre");
        menuItemAbout.setActionCommand("About Message");
        helpMenu.add(menuItemAbout);

        return helpMenu;
    }
}
