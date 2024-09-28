/*
 * É responsável por criar o menu de configuração da aplicação, incluindo submenus
 * para alterar o padrão de fundo, cor de fundo, e velocidade.
 */

package guiApp.menu.configMenu;

import javax.swing.*;

public class ConfigurationMenu {
    public JMenu createConfigMenu() {
        JMenu configMenu = new JMenu("Configuração");
        configMenu.setMnemonic('C');

        configMenu.add((new PatternMenu()).createPatternMenu());
        configMenu.add((new ColorMenu()).createColorMenu());
        configMenu.add((new SpeedMenu()).createSpeedMenu());

        return configMenu;
    }
}
