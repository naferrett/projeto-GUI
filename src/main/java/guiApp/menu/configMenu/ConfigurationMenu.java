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
