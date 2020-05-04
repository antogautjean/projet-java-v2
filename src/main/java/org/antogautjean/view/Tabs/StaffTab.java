package org.antogautjean.view.Tabs;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StaffTab implements TabInterface {

    @Override
    public JComponent getComponent() {
        JPanel staffPanel = new JPanel();
        staffPanel.add(new JLabel(getTabTitle() + " en construction")); // TODO: à remplacer par le vrai truc
        return staffPanel;
    }

    @Override
    public String getTabTitle() {
        return "Gestion du personnel";
    }
}
