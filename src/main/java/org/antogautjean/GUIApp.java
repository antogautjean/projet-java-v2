package org.antogautjean;

import javax.swing.*;

public class GUIApp {
    private JButton TESTButton;
    private JPanel Home;
    private JTabbedPane Dashboard;
    private JLabel globalState;

    public GUIApp() {

    }

    public static void main(String[] args){
        JFrame frame = new JFrame("Factory");
        frame.setContentPane(new GUIApp().Home);
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
