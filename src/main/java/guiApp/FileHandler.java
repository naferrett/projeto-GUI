package guiApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

class FileHandler {
    private JTextArea fileText; // Para exibir o conteúdo do arquivo

    public FileHandler(JTextArea textArea) {
        this.fileText = textArea;
    }

    public void openFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        fileText.setText("");

        String line;
        while ((line = reader.readLine()) != null) {
            fileText.append(line + "\n");
        }
        reader.close();
    }

    public void closeFile() {
        fileText.setText("");
    }
}

