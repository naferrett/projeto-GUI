package guiApp;

import guiApp.file.FileHandler;
import guiApp.menu.MenuHandler;
import lombok.extern.log4j.Log4j2;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serial;
import javax.imageio.ImageIO;
import javax.swing.*;

@Log4j2
public class MainWindow extends JFrame implements Runnable {

    @Serial
    private static final long serialVersionUID = 1L;
    private boolean threadRunning;
    private JLabel labelStatus;
    private JTextArea fileText;
    private FileHandler fileHandler;
    private JScrollPane scrollPane;
    //private MouseListenerClass mouseEventListener;
    BackgroundPanel backgroundPanel;

    MainWindow() throws HeadlessException {
        super(SystemInfo.getVersionName());

        this.threadRunning = false;

        windowConfig();
        initAddComponents();

        this.fileHandler = new FileHandler(this, fileText);
        EventListener eventListener = new EventListener(fileHandler, this);

        MenuHandler menuHandler = new MenuHandler(this);
        menuHandler.createAddToMenu();

        this.addMenuListeners(eventListener);
    }

    private void windowConfig() {
        this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.3), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.3));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setIcon();
    }

    private void setIcon() {
        try {
            this.setIconImage(ImageIO.read(this.getClass().getResource(SystemInfo.iconImage)));
        } catch (IOException | NullPointerException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar o ícone.", "Erro", JOptionPane.ERROR_MESSAGE);
            log.error(ex);
        }
    }

    private void initAddComponents() {
        initBackgroundPanel();
        initStatusPanel();
        initTextArea();
        initScrollPane();
        initListeners();

        setVisible(true);
    }

    private void initBackgroundPanel() {
        this.backgroundPanel = new BackgroundPanel();
        this.backgroundPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel label = new JLabel("Arquivo de texto:");
        label.setLabelFor(fileText);

        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 0; // 'gridx' e 'gridy' posicionam o elemento em relação aos eixos posicionados ao centro
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 10, 20, 10); // Padding interno do elemento em relação ao panel
        //gbc.weightx = 1.0;
        this.backgroundPanel.add(label, gbc);
    }

    private void initStatusPanel() {
        JPanel statusPanel = new JPanel();
        this.labelStatus = new JLabel();
        statusPanel.add(labelStatus);
        statusPanel.setBackground(Color.gray);
        statusPanel.setBorder(BorderFactory.createEtchedBorder());
        this.add(statusPanel, BorderLayout.SOUTH);
    }

    private void initScrollPane() {
        this.scrollPane = new JScrollPane(backgroundPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void initTextArea() {
        fileText = new JTextArea();
        fileText.setEditable(false);
        fileText.setLineWrap(true);
        fileText.setWrapStyleWord(true);
        //fileText.setColumns(35);
        fileHandler = new FileHandler(this, fileText);
        this.scrollPane = new JScrollPane(fileText);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0; // Aumenta a caixa de texto na horizontal
        gbc.weighty = 1.0; // Aumenta a caixa de texto na vertical
        // gbc.gridx = 0; // Column 0
        gbc.gridy = 1; // Linha 1
        gbc.fill = GridBagConstraints.BOTH; //Aumenta a caixa em tamanho e largura
        gbc.insets = new Insets(0, 70, 50, 70);

        this.backgroundPanel.add(new JScrollPane(fileText), gbc);
    }

    private void initListeners() {
        WindowListenerClass windowEventListener = new WindowListenerClass(this);
        this.addWindowListener(windowEventListener);

//        mouseEventListener = new MouseListenerClass(this.backgroundPanel);
//        backgroundPanel.addMouseListener(mouseEventListener);
    }

    public void setStatusMessage(String message)
    {
        this.labelStatus.setText(message);
    }

    void initInterface() {
        this.setStatusMessage(SystemInfo.university);
        this.setVisible(true);

        this.threadRunning = true;
        Thread serverDispatcher = new Thread(this);
        serverDispatcher.start();
    }

    void addMenuItemListener(ActionListener listener, JMenu mainMenu) {
        for(Component target : mainMenu.getMenuComponents()) {
            if (target instanceof JMenuItem) {
                ((JMenuItem) target).addActionListener(listener);
            }

            if (target instanceof JMenu) {
                addMenuItemListener(listener, (JMenu) target);
            }
        }
    }

    void addMenuListeners(ActionListener listener) {
        for (Component menu : this.getJMenuBar().getComponents()) {
            if (menu instanceof JMenu) {
                addMenuItemListener(listener, (JMenu) menu);
            }
        }
    }

    void exitInterface() {
        this.threadRunning = false;
        System.exit(NORMAL);
    }

    @Override
    public void run() {
        while (this.threadRunning) {
            backgroundPanel.repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                log.error("Erro durante execução de Threads" + ex);
            }
            backgroundPanel.nextLine();
        }
    }

}
