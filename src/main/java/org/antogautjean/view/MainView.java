package org.antogautjean.view;

import javax.swing.*;

public class MainView {
    public MainView(boolean setVisible) {
        // Fenêtre principale
        JFrame frame = new JFrame("Factory");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setContentPane(this.getHomePanel());

        // dernière action, pour rendre la fenêtre visible
        frame.setVisible(setVisible);
    }

    private JPanel getHomePanel() {
        // Home panel
        JPanel Home = new JPanel();

        // TODO: Rajouter des trucs utiles dans ce panel

        return Home;
    }
}
