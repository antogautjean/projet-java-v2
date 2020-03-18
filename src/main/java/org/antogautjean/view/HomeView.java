package org.antogautjean.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class HomeView {

    private JPanel homePanel;
    private JTabbedPane Dashboard;

    public HomeView(){

        JFrame frame = new JFrame("Factory");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this.getHomePanel());

        frame.setVisible(true);;
    }

    private JPanel getHomePanel() {
        return new JPanel();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}
