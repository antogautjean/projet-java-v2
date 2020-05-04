package org.antogautjean.view.Tabs;

import javax.swing.JComponent;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface TabInterface {
    public JComponent getComponent() throws Exception;

    public String getTabTitle();
}