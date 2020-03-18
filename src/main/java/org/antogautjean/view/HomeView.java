package org.antogautjean.view;

import javax.swing.*;

public class HomeView {

    private JPanel homePanel;
    private JTabbedPane Dashboard;
    private JProgressBar progressBar1;
    private JButton button1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton loadButton;

    static void guiMain(boolean DISPLAY){
        if (DISPLAY){
            AppRunner.LOGGER.log(0, "App", "GUI", "Runing with GUI");
            new HomeView();
        }
        else{
            AppRunner.LOGGER.log(0, "App", "GUI", "Runing without GUI");
        }
    }

    public HomeView(){

        JFrame frame = new JFrame("Factory");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this.getHomePanel());

        frame.setVisible(true);

    }

    private JPanel getHomePanel() {
        return new JPanel();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}
