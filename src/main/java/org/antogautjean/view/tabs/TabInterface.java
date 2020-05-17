package org.antogautjean.view.tabs;

import javax.swing.JComponent;

public interface TabInterface {
    public JComponent getComponent();

    public String getTabTitle();

    public void setIfRenderedCorrectly(boolean ifRendered);
    public boolean getIfRenderedCorrectly();
}
