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
        //System.out.println("Opening file: " + file.getName());
        BufferedReader br = new BufferedReader(new FileReader(file));
        fileText.setText("");

        String line;
        while ((line = br.readLine()) != null) {
            //System.out.println("Read line: " + line);
            fileText.append(line + "\n");
        }
        br.close();
        //System.out.println("File opened and read successfully.");
    }

    public void closeFile() {
        //System.out.println("Closing file.");
        fileText.setText("");
    }
}

