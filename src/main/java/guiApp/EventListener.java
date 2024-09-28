package guiApp;

import guiApp.file.FileHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class EventListener implements ActionListener {
    private FileHandler fileHandler;
    private MainWindow mainWindow;
    private File currentFile;

    EventListener(FileHandler filehandler, MainWindow mainwindow) {
        this.fileHandler = filehandler;
        this.mainWindow = mainwindow;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();

        switch (actionCommand) {
            case "Open File":
                mainWindow.setStatusMessage("Opção 'Abrir Arquivo' selecionada!");
                fileHandler.processFile();
                break;

            case "Close File":
                mainWindow.setStatusMessage("Opção 'Fechar Arquivo' selecionada!");
                fileHandler.closeFile();
                break;

            case "Exit":
                mainWindow.setStatusMessage("Opção 'Saída' selecionada!");
                mainWindow.exitInterface();
                break;

            case "Rectangle Pattern":
                mainWindow.setStatusMessage("Padrão dinâmico alterado para 'Retângulo'!");
                mainWindow.backgroundPanel.setPatternRectangle();
                break;

            case "Triangle Pattern":
                mainWindow.setStatusMessage("Padrão dinâmico alterado para 'Triângulo'!");
                mainWindow.backgroundPanel.setPatternTriangle();
                break;

            case "Star Pattern":
                mainWindow.setStatusMessage("Padrão dinâmico alterado para 'Estrela'!");
                mainWindow.backgroundPanel.setPatternStar();
                break;

            case "No Pattern":
                mainWindow.setStatusMessage("Padrão dinâmico removido da tela!");
                mainWindow.backgroundPanel.setNoPattern();
                break;

            case "Red Background":
                mainWindow.setStatusMessage("Cor de fundo alterada para 'Vermelho'!");
                mainWindow.backgroundPanel.setBackgroundColor(new Color(255,99,71));
                break;

            case "Pink Background":
                mainWindow.setStatusMessage("Cor de fundo alterada para 'Rosa'!");
                mainWindow.backgroundPanel.setBackgroundColor(new Color(255,192,203));
                break;

            case "Blue Background":
                mainWindow.setStatusMessage("Cor de fundo alterada para 'Azul'!");
                mainWindow.backgroundPanel.setBackgroundColor(new Color(135,206,250));
                break;

            case "Gray Background":
                mainWindow.setStatusMessage("Cor de fundo alterada para 'Cinza'!");
                mainWindow.backgroundPanel.setBackgroundColor(Color.lightGray);
                break;

            case "Speed 0.5x":
                mainWindow.setStatusMessage("Velocidade do fundo dinâmico alterada para 0.5x!");
                break;

            case "Speed 1x":
                mainWindow.setStatusMessage("Velocidade do fundo dinâmico alterada para 1x");
                break;

            case "Speed 2x":
                mainWindow.setStatusMessage("Velocidade do fundo dinâmico alterada para 2x!");
                break;

            case "Help Message":
                showHelpMessage();
                break;

            case "About Message":
                showAboutMessage();
                break;
        }
    }

    private void showHelpMessage() {
        (new MessageScreen(mainWindow, "Ajuda - " + SystemInfo.getVersionName(), SystemInfo.getHelp())).setVisible(true);
    }

    private void showAboutMessage() {
        (new MessageScreen(mainWindow, "Sobre - " + SystemInfo.getVersionName(), SystemInfo.getAbout())).setVisible(true);
    }

}
