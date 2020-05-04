package org.antogautjean;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


class Fenetre extends JFrame
{
    JPanel container = new JPanel(), fieldset = new JPanel();

    JPanel contentListe = new JPanel();
    JLabel label = new JLabel("Voici une liste : ");
    JComboBox liste = new JComboBox();

    JCheckBox case1 = new JCheckBox("Case 1");
    JCheckBox case2 = new JCheckBox("Case 2");

    public Fenetre()
    {
        this.setTitle("Liste de widgets");
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fieldset.setPreferredSize(new Dimension(300, 120));
        fieldset.setLayout(new BoxLayout(fieldset, BoxLayout.Y_AXIS));

        liste.setPreferredSize(new Dimension(100, 20));
        liste.addItem("Bleu");
        liste.addItem("Blanc");
        liste.addItem("Rouge");

        contentListe.add(label);
        contentListe.add(liste);

        fieldset.add(contentListe);
        fieldset.add(case1);
        fieldset.add(case2);

        fieldset.setBorder(BorderFactory.createTitledBorder(" Widgets "));

        container.add(fieldset);
        this.setContentPane(container);
        this.setVisible(true);
    }
}