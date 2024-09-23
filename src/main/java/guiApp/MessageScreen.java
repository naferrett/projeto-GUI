package guiApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

class MessageScreen extends JDialog implements ActionListener {
    private static final long    serialVersionUID = 1L;
    private final JButton exitButton;
    private final JPanel textPannel;
    private final JPanel buttonPannel;
    private final ImageDisplayPanel imagePannel;
    private final JTextArea textArea;
    private final JScrollPane scrollPane;


    MessageScreen(JFrame mainWindow, String title, String text) throws HeadlessException {
        super(mainWindow, title);
        setSize(800, 320);
        setResizable(false);
        setLocationRelativeTo(mainWindow);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        textArea = new JTextArea();
        textArea.setText(text);
        formatTextArea();

        // Criar o JScrollPane e adicionar o JTextArea
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Criar o painel de texto e adicionar o JScrollPane
        textPannel = new JPanel();
        textPannel.setBackground(Color.white);
        textPannel.setBorder(new TitledBorder(new LineBorder(Color.gray), SystemInfo.name));
        textPannel.setLayout(new BorderLayout());
        textPannel.add(scrollPane, BorderLayout.CENTER); // Adicionar o scrollPane ao painel de texto
        add(textPannel, BorderLayout.CENTER);

        // Configurar o painel de imagem
        imagePannel = new ImageDisplayPanel();
        imagePannel.setPreferredSize(new Dimension(200, 200));
        imagePannel.setBorder(new TitledBorder(new LineBorder(Color.gray), SystemInfo.faculty));
        imagePannel.setBackground(Color.white);
        add(imagePannel, BorderLayout.WEST);

        // Configurar o painel de botões
        buttonPannel = new JPanel();
        exitButton = new JButton("Fechar");
        exitButton.addActionListener(this);
        buttonPannel.add(exitButton);
        add(buttonPannel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        this.setVisible(false);
    }

    private void formatTextArea() {
        textArea.setForeground(Color.black);
        textArea.setBackground(Color.white);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.BOLD, 12));
        textArea.setMargin(new Insets(10, 10, 10, 10));
    }
}

