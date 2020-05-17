package org.antogautjean.view.tabs;

import java.io.IOException;

import javax.swing.JComponent;

public interface TabInterface {
    public JComponent getComponent() throws Exception;

    public String getTabTitle();

    public boolean isComponentRenderable();

    public void refreshFromFile() throws IOException;
}
