package org.antogautjean.view.Tabs;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SettingsTab implements TabInterface {
    @Override
    public JComponent getComponent() {
        JPanel settingsPanel = new JPanel();
        settingsPanel.add(new JLabel(getTabTitle() + " en construction")); // TODO: à remplacer par le vrai truc
        return settingsPanel;
    }

    @Override
    public String getTabTitle() {
        return "Paramètres";
    }
}
