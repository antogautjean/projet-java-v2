package org.antogautjean.view.tabs;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.antogautjean.controller.ControllerFromFileInterface;

import java.awt.FlowLayout;
import java.io.IOException;

public class DefaultTab {
    protected boolean ifRenderedCorrectly = false;
    protected ControllerFromFileInterface[] ctrls;

    public void setIfRenderedCorrectly(boolean ifRendered) {
        this.ifRenderedCorrectly = ifRendered;
    }

    public boolean getIfRenderedCorrectly() {
        return !this.ifRenderedCorrectly;
    }

    protected JComponent notFoundDesign() {
        JPanel output = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel text = new JLabel("<html>Le fichier utilisé pour cet onglet n'est pas bon<br/>"
                + "<br/>¯\\_(ツ)_/¯<br/><br/>Veuillez résoudre ce problème dans l'onglet \"Paramètres\"</html>");
        int margin = 15;
        text.setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));
        output.add(text);
        return output;
    }

    protected void setControllers(ControllerFromFileInterface[] ctrls) {
        this.ctrls = ctrls;
    }

    public boolean areControllersFresh(boolean refreshFromFile) {
        // Refresh controllers from files
        if (refreshFromFile) {
            try {
                for (ControllerFromFileInterface ctrl : ctrls) {
                    ctrl.refreshFromFile();
                }
            } catch (IOException e) {
                return false;
            }
        }
        // Check if a controller failed
        for (ControllerFromFileInterface ctrl : ctrls) {
            if (ctrl.getIfFileImportFailed()) {
                return false;
            }
        }
        return true;
    }

    public JComponent getComponent(boolean refreshFromFile) {
        (new Exception("This method should be implemented")).printStackTrace();
        return null;
    }
}