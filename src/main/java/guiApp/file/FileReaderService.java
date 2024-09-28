/* Essa classe é responsável por executar a abertura e leitura do arquivo qu será mostrado na interface gráfica. */

package guiApp.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class FileReaderService {
    private final FileHandler fileHandler;

    FileReaderService(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public void readFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.FileReader(file));
        fileHandler.fileText.setText("");

        String line;
        while ((line = br.readLine()) != null) {
            fileHandler.fileText.append(line + "\n");
        }
        br.close();
    }
}
