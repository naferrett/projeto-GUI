package teste;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

    public class MinimalExample extends JFrame implements ActionListener {
        private static final long serialVersionUID = 1L;
        private JTextArea fileText;
        private JMenuItem menuItemOpenFile;
        private FileHandler fileHandler;
        private File currentFile;

        public MinimalExample() {
            super("Minimal Example");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(600, 400);
            setLocationRelativeTo(null);

            initUI();
        }

        private void initUI() {
            fileText = new JTextArea();
            fileText.setEditable(false);
            fileText.setLineWrap(true);
            fileText.setWrapStyleWord(true);

            fileHandler = new FileHandler(fileText);

            JScrollPane scrollPane = new JScrollPane(fileText);
            add(scrollPane, BorderLayout.CENTER);

            JMenuBar menuBar = new JMenuBar();
            JMenu fileMenu = new JMenu("File");
            menuItemOpenFile = new JMenuItem("Open File");
            menuItemOpenFile.addActionListener(this);
            fileMenu.add(menuItemOpenFile);
            menuBar.add(fileMenu);
            setJMenuBar(menuBar);

            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == menuItemOpenFile) {
                System.out.println("'Open File' menu item clicked.");
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select a File");

                int returnValue = fileChooser.showOpenDialog(this);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    currentFile = fileChooser.getSelectedFile();
                    System.out.println("Selected file: " + currentFile.getName());
                    try {
                        fileHandler.openFile(currentFile);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Error opening file.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                new MinimalExample();
            });
        }
    }

    class FileHandler {
        private JTextArea fileText;

        public FileHandler(JTextArea textArea) {
            this.fileText = textArea;
        }

        public void openFile(File file) throws IOException {
            System.out.println("Opening file: " + file.getName());
            BufferedReader br = new BufferedReader(new FileReader(file));
            fileText.setText("");

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Read line: " + line);
                fileText.append(line + "\n");
            }
            br.close();
            System.out.println("File opened and read successfully.");
        }

        public void closeFile() {
            System.out.println("Closing file.");
            fileText.setText("");
        }
    }

