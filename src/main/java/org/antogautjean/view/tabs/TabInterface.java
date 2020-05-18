package org.antogautjean.view.tabs;

import javax.swing.JComponent;

public interface TabInterface {
    JComponent getComponent();

    String getTabTitle();

    void setIfRenderedCorrectly(boolean ifRendered);
    boolean getIfRenderedCorrectly();
}
