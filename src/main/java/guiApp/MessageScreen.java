/* Essa classe é uma janela de diálogo responsável por exibir uma mensagem
* com uma área de texto, um painel de imagem e um botão de saída. */

package guiApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

class MessageScreen extends JDialog implements ActionListener {
    private static final long    serialVersionUID = 1L;
    private final JPanel textPanel;
    private final JPanel buttonPanel;
    private final ImageDisplayPanel imagePanel;
    private final JTextArea textArea;


    MessageScreen(JFrame mainWindow, String title, String text) throws HeadlessException {
        super(mainWindow, title);
        setSize(800, 320);
        setResizable(false);
        setLocationRelativeTo(mainWindow);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);

        textArea = createTextArea(text);
        formatTextArea();

        JScrollPane scrollPane = createScrollPane(textArea);
        textPanel = createTextPanel(scrollPane);

        imagePanel = createImagePanel();

        JButton exitButton = new JButton();
        buttonPanel = createButtonPanel(exitButton);

        addComponents();
    }

    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        this.setVisible(false);
    }

    private JTextArea createTextArea(String text) {
        JTextArea textArea = new JTextArea();
        textArea.setText(text);
        return textArea;
    }

    private JScrollPane createScrollPane(JTextArea textArea) {
        return new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private JPanel createTextPanel(JScrollPane scrollPane) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setBorder(new TitledBorder(new LineBorder(Color.gray), SystemInfo.name));
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private ImageDisplayPanel createImagePanel() {
        ImageDisplayPanel panel = new ImageDisplayPanel();
        panel.setPreferredSize(new Dimension(200, 200));
        panel.setBorder(new TitledBorder(new LineBorder(Color.gray), SystemInfo.faculty));
        panel.setBackground(Color.white);
        return panel;
    }

    private JPanel createButtonPanel(JButton exitButton) {
        JPanel buttonPanel = new JPanel();
        exitButton = new JButton("Fechar");
        exitButton.addActionListener(this);
        buttonPanel.add(exitButton);
        return buttonPanel;
    }

    private void addComponents() {
        add(textPanel, BorderLayout.CENTER);
        add(imagePanel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.SOUTH);
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

