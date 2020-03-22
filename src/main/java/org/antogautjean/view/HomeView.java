package org.antogautjean.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HomeView extends JPanel {

    private JPanel homePanel;
    private JTabbedPane Dashboard;
    private JButton button1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton loadButton;
    private JTable stockTable;
    private JScrollPane stockScroll;

    public HomeView(){

        JFrame frame = new JFrame("Factory");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this.homePanel);
        frame.setVisible(true);

        createUIComponents();
    }

    private void createUIComponents() {


        DefaultTableModel tableModel = new DefaultTableModel(50,3);
        stockTable.setModel(tableModel);

    }
}
