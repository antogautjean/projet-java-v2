package org.antogautjean.view.tabs;

import org.antogautjean.controller.ConfigController;
import org.antogautjean.view.HomeView;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class SettingsTab extends DefaultTab implements TabInterface, ActionListener {
    JPanel container;
    JButton addStock;
    JButton addLines;
    JButton addPrices;
    JButton addStaff;
    JButton confirm;

    JLabel stockPath;
    JLabel pricesPath;
    JLabel linesPath;
    JLabel staffPath;

    HomeView parentComponent;

    public SettingsTab(HomeView parentComponent) {
        this.parentComponent = parentComponent;
    }

    @Override
    public JComponent getComponent() {
        container = new JPanel();
        container.setBorder(BorderFactory.createTitledBorder("Configuration des fichiers source (CSV)"));
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setPreferredSize(new Dimension(800, 250));

        this.addStock = new JButton("Modifier");
        this.addLines = new JButton("Modifier");
        this.addPrices = new JButton("Modifier");
        this.addStaff = new JButton("Modifier");
        // this.confirm = new JButton("Enregistrer");

        JPanel settingsPanel = new JPanel();

        Font font = new Font("Arial", Font.BOLD, 12);

        // JLabel spacer = new JLabel(" ");

        JLabel labelStock = new JLabel("Fichier de stock");
        this.stockPath = new JLabel(ConfigController.getProperty("stockFile"));
        labelStock.setFont(font);

        JLabel labelPrices = new JLabel("Fichier des prix");
        this.pricesPath = new JLabel(ConfigController.getProperty("pricesFile"));
        labelPrices.setFont(font);

        JLabel labelList = new JLabel("Fichier de chaine de production");
        this.linesPath = new JLabel(ConfigController.getProperty("linesFile"));
        labelList.setFont(font);

        JLabel labelStaff = new JLabel("Fichier des employées");
        this.staffPath = new JLabel(ConfigController.getProperty("staffFile"));
        labelStaff.setFont(font);

        container.add(labelStock);
        container.add(this.addStock);
        container.add(this.stockPath);
        container.add(new JSeparator(JSeparator.HORIZONTAL));
        container.add(labelPrices);
        container.add(this.addPrices);
        container.add(this.pricesPath);
        container.add(new JSeparator(JSeparator.HORIZONTAL));
        container.add(labelList);
        container.add(this.addLines);
        container.add(this.linesPath);
        container.add(new JSeparator(JSeparator.HORIZONTAL));
        container.add(labelStaff);
        container.add(this.addStaff);
        container.add(this.staffPath);
        // container.add(spacer);
        // container.add(new JSeparator(JSeparator.HORIZONTAL));
        // container.add(confirm);
        // container.add(spacer);

        this.addStock.addActionListener(this);
        this.addLines.addActionListener(this);
        this.addPrices.addActionListener(this);
        this.addStaff.addActionListener(this);

        settingsPanel.add(container);
        
        this.ifRenderedCorrectly = true;
        return settingsPanel;
    }

    @Override
    public String getTabTitle() {
        return "Paramètres";
    }

    @Override
    public void actionPerformed(ActionEvent evt) {

        if (evt.getSource() == this.confirm) {
            ConfigController.commit();
        } else {
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int returnValue = jfc.showOpenDialog(null);
            File selectedFile = jfc.getSelectedFile();

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                if (evt.getSource() == this.addStock) {
                    ConfigController.setProperty("stockFile", selectedFile.getAbsolutePath());
                    ConfigController.setProperty("linesFile", ConfigController.getProperty("linesFile"));
                    ConfigController.setProperty("pricesFile", ConfigController.getProperty("pricesFile"));
                    ConfigController.setProperty("staffFile", ConfigController.getProperty("staffFile"));
                    stockPath.setText(selectedFile.getAbsolutePath());
                    ConfigController.commit();
                }
                if (evt.getSource() == this.addPrices) {
                    ConfigController.setProperty("stockFile", ConfigController.getProperty("stockFile"));
                    ConfigController.setProperty("pricesFile", selectedFile.getAbsolutePath());
                    ConfigController.setProperty("linesFile", ConfigController.getProperty("linesFile"));
                    ConfigController.setProperty("staffFile", ConfigController.getProperty("staffFile"));
                    pricesPath.setText(selectedFile.getAbsolutePath());
                    ConfigController.commit();
                }
                if (evt.getSource() == this.addLines) {
                    ConfigController.setProperty("stockFile", ConfigController.getProperty("stockFile"));
                    ConfigController.setProperty("pricesFile", ConfigController.getProperty("pricesFile"));
                    ConfigController.setProperty("linesFile", selectedFile.getAbsolutePath());
                    ConfigController.setProperty("staffFile", ConfigController.getProperty("staffFile"));
                    linesPath.setText(selectedFile.getAbsolutePath());
                    ConfigController.commit();
                }
                if (evt.getSource() == this.addStaff) {
                    ConfigController.setProperty("stockFile", ConfigController.getProperty("stockFile"));
                    ConfigController.setProperty("pricesFile", ConfigController.getProperty("pricesFile"));
                    ConfigController.setProperty("linesFile", ConfigController.getProperty("linesFile"));
                    ConfigController.setProperty("staffFile", selectedFile.getAbsolutePath());
                    staffPath.setText(selectedFile.getAbsolutePath());
                    ConfigController.commit();
                }
                try {
                    this.parentComponent.refreshTabs();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
