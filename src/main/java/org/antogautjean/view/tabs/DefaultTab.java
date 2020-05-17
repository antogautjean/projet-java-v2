package org.antogautjean.view.tabs;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.FlowLayout;

public class DefaultTab {
    protected boolean ifRenderedCorrectly = false;

    public void setIfRenderedCorrectly(boolean ifRendered) {
        this.ifRenderedCorrectly = ifRendered;
    }

    public boolean getIfRenderedCorrectly() {
        return this.ifRenderedCorrectly;
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
}