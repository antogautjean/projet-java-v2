package org.antogautjean.view.Tabs;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FactoryTab implements TabInterface {

    @Override
    public JComponent getComponent() {
        JPanel factoryPanel = new JPanel();
        factoryPanel.add(new JLabel(getTabTitle() + " en construction")); // TODO: à remplacer par le vrai truc
        return factoryPanel;
    }

    @Override
    public String getTabTitle() {
        return "Gestion de l'usine";
    }
}
