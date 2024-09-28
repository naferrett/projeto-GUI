package teste;

import javax.swing.*;
import java.awt.event.*;

public class MenuTest extends JFrame implements ActionListener {

    JMenu configMenu;
    JMenuItem menuItemPattern1, menuItemPattern2;

    public MenuTest() {
        // Configurar frame
        setTitle("Teste de Menu");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Criar o menu de configuração
        configMenu = new JMenu("Configurações");

        // Adicionar itens de menu
        menuItemPattern1 = new JMenuItem("Pattern 1");
        menuItemPattern2 = new JMenuItem("Pattern 2");
        configMenu.add(menuItemPattern1);
        configMenu.add(menuItemPattern2);

        // Adicionar listeners
        menuItemPattern1.addActionListener(this);
        menuItemPattern2.addActionListener(this);

        // Adicionar menu à barra de menus
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(configMenu);
        setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println("Comando disparado: " + command);
        if (command.equals("Pattern 1")) {
            System.out.println("Pattern 1 selecionado");
        } else if (command.equals("Pattern 2")) {
            System.out.println("Pattern 2 selecionado");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MenuTest().setVisible(true);
        });
    }
}
