package org.antogautjean.view.tabs;

import javax.swing.JComponent;

public interface TabInterface {
    public JComponent getComponent() throws Exception;

    public String getTabTitle();
}