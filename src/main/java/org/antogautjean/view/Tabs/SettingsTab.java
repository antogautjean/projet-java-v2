package org.antogautjean.view.Tabs;

import org.antogautjean.model.Config;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class SettingsTab implements TabInterface, ActionListener {

    Config cfg;

    JPanel container;
    JButton addStock;
    JButton addLines;
    JButton confirm;

    JLabel stockPath;
    JLabel linesPath;

    @Override
    public JComponent getComponent() throws Exception {

        this.cfg = new Config("src/main/java/org/antogautjean/settings.properties");

        container = new JPanel();
        container.setBorder(BorderFactory.createTitledBorder("Configuration des fichiers source (CSV)"));
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setPreferredSize(new Dimension(400, 210));

        this.addStock = new JButton("Modifier");
        this.addLines = new JButton("Modifier");
        // this.confirm = new JButton("Enregistrer");

        JPanel settingsPanel = new JPanel();

        Font font = new Font("Arial", Font.BOLD,12);

        JLabel spacer = new JLabel(" ");

        JLabel labelStock = new JLabel("Fichier de stock");
        this.stockPath = new JLabel(this.cfg.getProperty("stockFile"));
        labelStock.setFont(font);

        JLabel labelList = new JLabel("Fichier de chaine de production");
        this.linesPath = new JLabel(this.cfg.getProperty("linesFile"));
        labelList.setFont(font);

        container.add(spacer);
        container.add(labelStock);
        container.add(this.addStock);
        container.add(this.stockPath);
        container.add(spacer);
        container.add(labelList);
        container.add(this.addLines);
        container.add(this.linesPath);
        // container.add(spacer);
        // container.add(new JSeparator(JSeparator.HORIZONTAL));
        // container.add(confirm);
        // container.add(spacer);

        addStock.addActionListener(this);
        addLines.addActionListener(this);
        //confirm.addActionListener(this);

        settingsPanel.add(container);
        return settingsPanel;
    }

    @Override
    public String getTabTitle() {
        return "Paramètres";
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == this.confirm){
            try {
                this.cfg.commit();
                this.cfg = new Config("src/main/java/org/antogautjean/settings.properties");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int returnValue = jfc.showOpenDialog(null);
            File selectedFile = jfc.getSelectedFile();

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                if (e.getSource() == this.addStock){
                    this.cfg.setProperty("stockFile", selectedFile.getAbsolutePath());
                    this.cfg.setProperty("linesFile", this.cfg.getProperty("linesFile"));
                    stockPath.setText(selectedFile.getAbsolutePath());
                    this.cfg.commit();
                }
                if (e.getSource() == this.addLines){
                    this.cfg.setProperty("stockFile", this.cfg.getProperty("stockFile"));
                    this.cfg.setProperty("linesFile", selectedFile.getAbsolutePath());
                    linesPath.setText(selectedFile.getAbsolutePath());
                    this.cfg.commit();
                }
            }
        }
    }
}
