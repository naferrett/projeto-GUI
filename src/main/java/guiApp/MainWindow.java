package guiApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.Toolkit;
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
    private BackgroundPanel backgroundPannel;
    private JPanel statusPannel;

    MainWindow() throws HeadlessException {
        super(SystemInfo.getVersionName());

        this.threadRunning = false;

        windowConfig();
        createAddToMenu();
        adicionaOuvinteMenus(this);
        initAddComponents();
    }

    private void windowConfig() {
        this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.3), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.3));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(5, 5)); // ir trocando pra ver oq muda

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
        initStatusPanel();
        initScrollPane();
        initListeners();
    }

    private void initStatusPanel() {
        this.statusPannel = new JPanel();
        this.labelStatus = new JLabel();
        this.statusPannel.add(labelStatus);
        this.statusPannel.setBackground(Color.gray);
        this.statusPannel.setBorder(BorderFactory.createEtchedBorder());
        this.add(statusPannel, BorderLayout.SOUTH);
    }

    private void initScrollPane() {
        this.backgroundPannel = new BackgroundPanel();
        this.scrollPane = new JScrollPane(backgroundPannel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void initTextArea() {
        fileText = new JTextArea();
        fileText.setEditable(false); // Impede a edição se for necessário
        this.add(new JScrollPane(fileText), BorderLayout.CENTER); // Adiciona a área de texto na interface
    }

    private void initListeners() {
        windowEventListener = new WindowListenerClass(this);
        this.addWindowListener(windowEventListener);

        mouseEventListener = new MouseListenerClass(this.backgroundPannel);
        backgroundPannel.addMouseListener(mouseEventListener);
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

    // Fazer uma classe
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
        menuItemChoosePattern.add(menuItemPattern1);

        menuItemPattern2 = new JMenuItem("Padrão 2");
        menuItemChoosePattern.add(menuItemPattern2);

        configMenu.add(menuItemChoosePattern);
    }

    private void addColorMenu() {
        // Criando submenu para escolher a cor
        menuItemChooseColor = new JMenu("Alterar Cor");
        menuItemChooseColor.setMnemonic('R');

        // Itens do submenu
        menuItemPink = new JMenuItem("Rosa");
        menuItemChooseColor.add(menuItemPink);

        menuItemRed = new JMenuItem("Vermelho");
        menuItemChooseColor.add(menuItemRed);

        menuItemBlue = new JMenuItem("Azul");
        menuItemChooseColor.add(menuItemBlue);

        configMenu.add(menuItemChooseColor);
    }

    private void addSpeedMenu() {
        // Criando submenu para escolher a velocidade
        menuItemChooseSpeed = new JMenu("Alterar Velocidade");
        menuItemChooseSpeed.setMnemonic('D');

        // Itens do submenu
        menuItemSpeed1x = new JMenuItem("Velocidade 1x");
        menuItemChooseSpeed.add(menuItemSpeed1x);

        menuItemSpeed2x = new JMenuItem("Velocidade 2x");
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
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == menuItemOpenFile) {
            fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Procurar Arquivo");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo de texto", "txt", "pdf"); // Filtro pra só abrir pdf

            fileChooser.setFileFilter(filter);
            int returnValue = fileChooser.showOpenDialog(this);

            if(returnValue == JFileChooser.APPROVE_OPTION) {
                currentFile = fileChooser.getSelectedFile();
                initTextArea(); // por que não está iniciando
                try {
                    new FileHandler(fileText).openFile(currentFile);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao abrir o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }

            this.setStatusMessage("Opção 'Abrir Arquivo' selecionada!");
        }

        if (event.getSource() == menuItemCloseFile) {
            fileHandler.closeFile(); // Fecha o arquivo limpando a área de texto
            initAddComponents();
            currentFile = null;
            this.setStatusMessage("Opção 'Fechar Arquivo' selecionada!");
        }

        if (event.getSource() == menuItemExit) {
            exitInterface();
        }

        if (event.getSource() == menuItemHelp) {
            (new MessageScreen(this, "Ajuda - " + SystemInfo.getVersionName(), SystemInfo.getHelp())).setVisible(true);
            this.setStatusMessage("Opção 'Ajuda' selecionada!");
        }

        if (event.getSource() == menuItemAbout) {
            (new MessageScreen(this, "Sobre - " + SystemInfo.getVersionName(), SystemInfo.getAbout())).setVisible(true);
            this.setStatusMessage("Opção 'Sobre' selecionada!");
        }

//      if (event.getSource() == menuItemRed)
//         {
//         backgroundPannel.setCorFundo(Color.red);
//         }
//
//      if (event.getSource() == menuItemPink)
//         {
//         backgroundPannel.setCorFundo(new Color(32, 107, 57));
//         }
//
//      if (event.getSource() == menuItemBlue)
//         {
//         backgroundPannel.setCorFundo(Color.blue);
//         }
    }

    void exitInterface() {
        this.threadRunning = false;
        System.exit(NORMAL);
    }

    @Override
    public void run() {
        while (this.threadRunning) {
            // Aqui acontece a atualizacao da tela
            backgroundPannel.repaint();
            try {
                // Pausa de 1 seg (1.000 milisegundos) para evitar efeitos indesej�veis na composicao da tela
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
