package org.antogautjean.view.tabs;

import javax.swing.JComponent;

public interface TabInterface {
    JComponent getComponent(boolean refreshFromFile);

    String getTabTitle();

    void setIfRenderedCorrectly(boolean ifRendered);
    boolean getIfRenderedCorrectly();
}
