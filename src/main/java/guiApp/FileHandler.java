/* Essa classe é responsável por executar a abertura e leitura de um arquivo
* para que ele seja mostrado na interface. Também limpa a tela quando um
* arquivo é fechado. */

package guiApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

class FileHandler {
    private JTextArea fileText;

    public FileHandler(JTextArea textArea) {
        this.fileText = textArea;
    }

    public void openFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        fileText.setText("");

        String line;
        while ((line = br.readLine()) != null) {
            fileText.append(line + "\n");
        }
        br.close();
    }

    public void closeFile() {
        fileText.setText("");
    }
}

