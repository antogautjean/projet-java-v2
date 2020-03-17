package org.antogautjean;

import javax.swing.*;

public class GUI {

    private JButton TESTButton;
    private JPanel Home;
    private JTabbedPane Dashboard;
    private JLabel globalState;

    public void startGUI(){
        JFrame frame = new JFrame("Factory");
        frame.setContentPane(new GUI().Home);
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
