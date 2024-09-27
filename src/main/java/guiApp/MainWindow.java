package guiApp;

import lombok.extern.log4j.Log4j2;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serial;
import javax.imageio.ImageIO;
import javax.swing.*;

@Log4j2
class MainWindow extends JFrame implements Runnable {
    @Serial
    private static final long serialVersionUID = 1L;
    private boolean threadRunning;
    private JLabel labelStatus;
    private JMenu fileMenu;
    private JMenu configMenu;
    private JMenu helpMenu;
    private JMenuItem menuItemOpenFile;
    private JMenuItem menuItemCloseFile;
    private JMenuItem menuItemExit;
    private JTextArea fileText;
    private FileHandler fileHandler;
    private JMenuItem menuItemRectanglePattern;
    private JMenuItem menuItemTrianglePattern;
    private JMenuItem menuItemStarPattern;
    private JMenuItem menuItemNoPattern;
    private JMenuItem menuItemBlue;
    private JMenuItem menuItemPink;
    private JMenuItem menuItemRed;
    private JMenuItem menuItemGray;
    private JMenu menuItemChooseSpeed;
    private JMenuItem menuItemSpeed05x;
    private JMenuItem menuItemSpeed1x;
    private JMenuItem menuItemSpeed2x;
    private JMenuItem menuItemHelp;
    private JMenuItem menuItemAbout;
    private JScrollPane scrollPane;
    private final EventHandler eventHandler;
    //private MouseListenerClass mouseEventListener;
    BackgroundPanel backgroundPanel;

    MainWindow() throws HeadlessException {
        super(SystemInfo.getVersionName());

        this.threadRunning = false;

        windowConfig();
        initAddComponents();
        createAddToMenu();

        this.fileHandler = new FileHandler(fileText);
        this.eventHandler = new EventHandler(fileHandler, this);
        addMenuListeners(eventHandler);
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
        fileHandler = new FileHandler(fileText);
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

    void setStatusMessage(String message)
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

    private void createAddToMenu() {
        createFileMenu();
        createConfigMenu();
        createHelpMenu();
        setupMenuBar();
    }

    private void createFileMenu() {
        fileMenu = new JMenu("Arquivo");
        fileMenu.setMnemonic('A');

        menuItemOpenFile = new JMenuItem("Abrir Arquivo");
        menuItemOpenFile.setActionCommand("Open File");
        menuItemOpenFile.setMnemonic('B');
        fileMenu.add(menuItemOpenFile);

        menuItemCloseFile = new JMenuItem("Fechar Arquivo");
        menuItemCloseFile.setActionCommand("Close File");
        menuItemCloseFile.setMnemonic('F');
        fileMenu.add(menuItemCloseFile);

        fileMenu.addSeparator();

        menuItemExit = new JMenuItem("Saida");
        menuItemExit.setActionCommand("Exit");
        menuItemExit.setMnemonic('S');
        fileMenu.add(menuItemExit);
    }

    private void createConfigMenu() {
        configMenu = new JMenu("Configuração");
        configMenu.setMnemonic('C');

        addPatternMenu();
        addColorMenu();
        addSpeedMenu();
    }

    private void addPatternMenu() {
        // Criando submenu para escolher o padrão
        JMenu menuItemChoosePattern = new JMenu("Alterar Padrão");
        menuItemChoosePattern.setMnemonic('P');

        // Itens do submenu
        menuItemRectanglePattern = new JMenuItem("Retângulo");
        menuItemRectanglePattern.addActionListener(eventHandler);
        menuItemRectanglePattern.setActionCommand("Rectangle Pattern");
        menuItemChoosePattern.add(menuItemRectanglePattern);

        menuItemTrianglePattern = new JMenuItem("Triângulo");
        menuItemTrianglePattern.addActionListener(eventHandler);
        menuItemTrianglePattern.setActionCommand("Triangle Pattern");
        menuItemChoosePattern.add(menuItemTrianglePattern);

        menuItemStarPattern = new JMenuItem("Estrela");
        menuItemStarPattern.addActionListener(eventHandler);
        menuItemStarPattern.setActionCommand("Star Pattern");
        menuItemChoosePattern.add(menuItemStarPattern);

        menuItemNoPattern = new JMenuItem("Limpar");
        menuItemNoPattern.addActionListener(eventHandler);
        menuItemNoPattern.setActionCommand("No Pattern");
        menuItemChoosePattern.add(menuItemNoPattern);

        configMenu.add(menuItemChoosePattern);
    }

    private void addColorMenu() {
        // Criando submenu para escolher a cor
        JMenu menuItemChooseColor = new JMenu("Alterar Cor");
        menuItemChooseColor.setMnemonic('R');

        // Itens do submenu
        menuItemPink = new JMenuItem("Rosa");
        menuItemPink.setActionCommand("Pink Background");
        menuItemChooseColor.add(menuItemPink);

        menuItemRed = new JMenuItem("Vermelho");
        menuItemRed.setActionCommand("Red Background");
        menuItemChooseColor.add(menuItemRed);

        menuItemBlue = new JMenuItem("Azul");
        menuItemBlue.setActionCommand("Blue Background");
        menuItemChooseColor.add(menuItemBlue);

        menuItemGray = new JMenuItem("Cinza");
        menuItemGray.setActionCommand("Gray Background");
        menuItemChooseColor.add(menuItemGray);

        configMenu.add(menuItemChooseColor);
    }

    private void addSpeedMenu() {
        // Criando submenu para escolher a velocidade
        menuItemChooseSpeed = new JMenu("Alterar Velocidade");
        menuItemChooseSpeed.setMnemonic('D');

        // Itens do submenu
        menuItemSpeed05x = new JMenuItem("Velocidade 0.5x");
        menuItemSpeed05x.addActionListener(eventHandler);
        menuItemSpeed05x.setActionCommand("Speed 0.5x");
        menuItemChooseSpeed.add(menuItemSpeed05x);

        menuItemSpeed1x = new JMenuItem("Velocidade 1x");
        menuItemSpeed1x.addActionListener(eventHandler);
        menuItemSpeed1x.setActionCommand("Speed 1x");
        menuItemChooseSpeed.add(menuItemSpeed1x);

        menuItemSpeed2x = new JMenuItem("Velocidade 2x");
        menuItemSpeed2x.addActionListener(eventHandler);
        menuItemSpeed2x.setActionCommand("Speed 2x");
        menuItemChooseSpeed.add(menuItemSpeed2x);

        configMenu.add(menuItemChooseSpeed);
    }

    private void createHelpMenu() {
        helpMenu = new JMenu("Ajuda");
        helpMenu.setMnemonic('J');

        menuItemHelp = new JMenuItem("Ajuda");
        menuItemHelp.setMnemonic('U');
        menuItemHelp.setActionCommand("Help Message");
        helpMenu.add(menuItemHelp);

        menuItemAbout = new JMenuItem("Sobre");
        menuItemAbout.setMnemonic('O');
        menuItemAbout.setActionCommand("About Message");
        helpMenu.add(menuItemAbout);
    }

    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(configMenu);
        menuBar.add(helpMenu);
        this.setJMenuBar(menuBar);
    }

    private void addMenuItemListener(ActionListener listener, JMenu mainMenu) {
        for (Component target : mainMenu.getMenuComponents()) {
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
