/*
 * A classe FileHandler gerencia a abertura, leitura e fechamento de arquivos de texto em uma interface gráfica, usando
 * um JFileChooser para selecionar arquivos e exibir o conteúdo do arquivo em um JTextArea.
 */

package guiApp.file;

import guiApp.MainWindow;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

@Log4j2
public class FileHandler {
    final JTextArea fileText;
    private final MainWindow mainWindow;
    private File currentFile;

    public FileHandler(MainWindow mainWindow, JTextArea textArea) {
        this.fileText = textArea;
        this.mainWindow = mainWindow;
    }

    public void removeFileText() {
        fileText.setText("");
    }

    public void processFile() {
        JFileChooser fileChooser = createFileChooser();
        int returnValue = fileChooser.showOpenDialog(mainWindow);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            this.currentFile = fileChooser.getSelectedFile();
            openFile(this.currentFile);
        }
    }

    private JFileChooser createFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Procurar Arquivo");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo de texto", "txt");
        fileChooser.setFileFilter(filter);

        return fileChooser;
    }

    private void openFile(File file) {
        try {
            (new FileReaderService(this)).readFile(file);
            mainWindow.setStatusMessage("Arquivo aberto: " + file.getName());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(mainWindow, "Erro ao abrir o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
            log.error(ex);
        }
    }

    public void closeFile() {
        try {
            this.removeFileText();
            mainWindow.setStatusMessage("Arquivo fechado: " + this.currentFile.getName());
            this.currentFile = null;
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(mainWindow, "Nenhum arquivo aberto foi encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            log.error(ex);
        }
    }
}

