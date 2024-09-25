package guiApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

class MainWindow extends JFrame implements ActionListener, Runnable {
    private static final long serialVersionUID = 1L;
    private boolean threadRunning;
    private JLabel labelStatus;
    private JMenu fileMenu;
    private JMenu configMenu;
    private JMenu helpMenu;
    private JMenuBar menuBar;
    private JMenuItem menuItemOpenFile;
    private JMenuItem menuItemCloseFile;
    private JMenuItem menuItemExit;
    private JFileChooser fileChooser;
    private JTextArea fileText;
    private FileHandler fileHandler;
    private File currentFile;
    private JMenu menuItemChoosePattern;
    private JMenuItem menuItemPattern1;
    private JMenuItem menuItemPattern2;
    private JMenu menuItemChooseColor;
    private JMenuItem menuItemBlue;
    private JMenuItem menuItemPink;
    private JMenuItem menuItemRed;
    private JMenu menuItemChooseSpeed;
    private JMenuItem menuItemSpeed1x;
    private JMenuItem menuItemSpeed2x;
    private JMenuItem menuItemHelp;
    private JMenuItem menuItemAbout;
    private JScrollPane scrollPane;
    private WindowListenerClass windowEventListener;
    private MouseListenerClass mouseEventListener;
    private BackgroundPanel backgroundPanel;
    private JPanel statusPanel;

    MainWindow() throws HeadlessException {
        super(SystemInfo.getVersionName());

        this.threadRunning = false;

        windowConfig();
        initAddComponents();
        createAddToMenu();
        adicionaOuvinteMenus(this);
    }

    private void windowConfig() {
        this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.3), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.3));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLayout(new BorderLayout(5, 5)); // ir trocando pra ver oq muda

        this.setIcon();
    }

    private void setIcon() {
        try {
            this.setIconImage(ImageIO.read(this.getClass().getResource(SystemInfo.iconImage)));
        } catch (IOException exceptionLaunched) {
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
        JLabel label = new JLabel("Arquivo carregado:");
        label.setLabelFor(fileText);
        this.backgroundPanel.add(label, BorderLayout.NORTH);
    }

    private void initStatusPanel() {
        this.statusPanel = new JPanel();
        this.labelStatus = new JLabel();
        this.statusPanel.add(labelStatus);
        this.statusPanel.setBackground(Color.gray);
        this.statusPanel.setBorder(BorderFactory.createEtchedBorder());
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
        fileText.setColumns(35);
        fileHandler = new FileHandler(fileText);
        this.scrollPane = new JScrollPane(fileText);

        this.backgroundPanel.add(new JScrollPane(fileText), BorderLayout.CENTER);
    }

    private void initListeners() {
        windowEventListener = new WindowListenerClass(this);
        this.addWindowListener(windowEventListener);

        mouseEventListener = new MouseListenerClass(this.backgroundPanel);
        backgroundPanel.addMouseListener(mouseEventListener);
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

    // Fazer uma classe?
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
        menuItemOpenFile.setMnemonic('B');
        fileMenu.add(menuItemOpenFile);

        menuItemCloseFile = new JMenuItem("Fechar Arquivo");
        menuItemCloseFile.setMnemonic('F');
        fileMenu.add(menuItemCloseFile);

        fileMenu.addSeparator();

        menuItemExit = new JMenuItem("Saida");
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
        menuItemChoosePattern = new JMenu("Alterar Padrão");
        menuItemChoosePattern.setMnemonic('P');

        // Itens do submenu
        menuItemPattern1 = new JMenuItem("Padrão 1");
        menuItemPattern1.addActionListener(this);
        menuItemChoosePattern.add(menuItemPattern1);

        menuItemPattern2 = new JMenuItem("Padrão 2");
        menuItemPattern2.addActionListener(this);
        menuItemChoosePattern.add(menuItemPattern2);

        configMenu.add(menuItemChoosePattern);
    }

    private void addColorMenu() {
        // Criando submenu para escolher a cor
        menuItemChooseColor = new JMenu("Alterar Cor");
        menuItemChooseColor.setMnemonic('R');

        // Itens do submenu
        menuItemPink = new JMenuItem("Rosa");
        menuItemPink.addActionListener(this);
        menuItemChooseColor.add(menuItemPink);

        menuItemRed = new JMenuItem("Vermelho");
        menuItemRed.addActionListener(this);
        menuItemChooseColor.add(menuItemRed);

        menuItemBlue = new JMenuItem("Azul");
        menuItemBlue.addActionListener(this);
        menuItemChooseColor.add(menuItemBlue);

        configMenu.add(menuItemChooseColor);
    }

    private void addSpeedMenu() {
        // Criando submenu para escolher a velocidade
        menuItemChooseSpeed = new JMenu("Alterar Velocidade");
        menuItemChooseSpeed.setMnemonic('D');

        // Itens do submenu
        menuItemSpeed1x = new JMenuItem("Velocidade 1x");
        menuItemSpeed1x.addActionListener(this);
        menuItemChooseSpeed.add(menuItemSpeed1x);

        menuItemSpeed2x = new JMenuItem("Velocidade 2x");
        menuItemSpeed2x.addActionListener(this);
        menuItemChooseSpeed.add(menuItemSpeed2x);

        configMenu.add(menuItemChooseSpeed);
    }


    private void createHelpMenu() {
        helpMenu = new JMenu("Ajuda");
        helpMenu.setMnemonic('J');

        menuItemHelp = new JMenuItem("Ajuda");
        menuItemHelp.setMnemonic('U');
        helpMenu.add(menuItemHelp);

        menuItemAbout = new JMenuItem("Sobre");
        menuItemAbout.setMnemonic('O');
        helpMenu.add(menuItemAbout);
    }

    private void setupMenuBar() {
        menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(configMenu);
        menuBar.add(helpMenu);
        this.setJMenuBar(menuBar);
    }

    void adicionaOuvinteMenus(ActionListener ouvinte) {
        for (Component menuPrincipal : this.getJMenuBar().getComponents()) {
            if (menuPrincipal instanceof JMenu) {
                adicionaOuvinteItemMenu(ouvinte, (JMenu) menuPrincipal);
            }
        }
    }

    private void adicionaOuvinteItemMenu(ActionListener ouvinte, JMenu menuPrincipal) {
        for (Component alvo : menuPrincipal.getMenuComponents()) {
            if (alvo instanceof JMenuItem) {
                ((JMenuItem) alvo).addActionListener(ouvinte);
                //System.out.println("Ouvinte adicionado para: " + ((JMenuItem) alvo).getText());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == menuItemOpenFile) {
            //System.out.println("'Open File' menu item clicked.");
            this.setStatusMessage("Opção 'Abrir Arquivo' selecionada!");

            fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Procurar Arquivo");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo de texto", "txt", "pdf");
            fileChooser.setFileFilter(filter);

            int returnValue = fileChooser.showOpenDialog(this);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                currentFile = fileChooser.getSelectedFile();
                //System.out.println("Selected file: " + currentFile.getName());
                try {
                    fileHandler.openFile(currentFile);  // Exibe o conteúdo no JTextArea
                    this.setStatusMessage("Arquivo aberto: " + currentFile.getName());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao abrir o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (event.getSource() == menuItemCloseFile) {
            this.setStatusMessage("Opção 'Fechar Arquivo' selecionada!");

            try {
                fileText.setText("");
                this.setStatusMessage("Arquivo fechado: " + currentFile.getName());
                currentFile = null;
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao fechar o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (event.getSource() == menuItemExit) {
            exitInterface();
        }

        if (event.getSource() == menuItemHelp) {
            this.setStatusMessage("Opção 'Ajuda' selecionada!");
            (new MessageScreen(this, "Ajuda - " + SystemInfo.getVersionName(), SystemInfo.getHelp())).setVisible(true);
        }

        if (event.getSource() == menuItemAbout) {
            this.setStatusMessage("Opção 'Sobre' selecionada!");
            (new MessageScreen(this, "Sobre - " + SystemInfo.getVersionName(), SystemInfo.getAbout())).setVisible(true);
        }

        if (event.getSource() == menuItemRed) {
            this.setStatusMessage("Cor de fundo alterada para 'Vermelho'!");
            backgroundPanel.setBackgroundColor(new Color(255,99,71));
        }

        if (event.getSource() == menuItemPink) {
            this.setStatusMessage("Cor de fundo alterada para 'Rosa'!");
            backgroundPanel.setBackgroundColor(new Color(255,192,203));
        }

        if (event.getSource() == menuItemBlue) {
            this.setStatusMessage("Cor de fundo alterada para 'Azul'!");
            backgroundPanel.setBackgroundColor(new Color(135,206,250));
        }
    }

    void exitInterface() {
        this.threadRunning = false;
        System.exit(NORMAL);
    }

    @Override
    public void run() {
        while (this.threadRunning) {
            // Aqui acontece a atualizacao da tela
            backgroundPanel.repaint();
            try {
                // Pausa de 1 seg (1.000 milisegundos) para evitar efeitos indesej�veis na composicao da tela
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
