package org.antogautjean.view;

import org.antogautjean.Controller.LineController;
import org.antogautjean.Controller.StockController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class HomeView extends JPanel {

    private JPanel homePanel;
    private JTabbedPane Dashboard;
    private JButton button1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton loadButton;
    private JTable stockTable;
    private JTable lines;
    private JTable indicators;

    private int increment = 0;
    private StockController stockList;

    public HomeView(StockController stockList, LineController line) {

        JFrame frame = new JFrame("Factory");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this.homePanel);
        frame.setVisible(true);
        this.stockList = stockList;

        createUIComponents();
    }

    private void configStockList() {

        DefaultTableModel tableModel = new DefaultTableModel(this.stockList.getStock().size(), 8);
        stockTable.setModel(tableModel);

        JTableHeader header = stockTable.getTableHeader();
        TableColumnModel columnModel = header.getColumnModel();

        columnModel.getColumn(0).setHeaderValue("Code");
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setHeaderValue("Nom");
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setHeaderValue("Quantité actuelle");
        columnModel.getColumn(2).setPreferredWidth(60);
        columnModel.getColumn(3).setHeaderValue("Quantité à acheter");
        columnModel.getColumn(3).setPreferredWidth(60);
        columnModel.getColumn(4).setHeaderValue("Coût d'achat prévisionnel");
        columnModel.getColumn(4).setPreferredWidth(60);
        columnModel.getColumn(5).setHeaderValue("Quantité actuelle");
        columnModel.getColumn(5).setPreferredWidth(60);
        columnModel.getColumn(6).setHeaderValue("Nouvelle quantité après achat");
        columnModel.getColumn(6).setPreferredWidth(60);
        columnModel.getColumn(7).setHeaderValue("Quantité simulée après calcul");
        columnModel.getColumn(7).setPreferredWidth(100);
        header.repaint();

        stockTable.setAutoCreateRowSorter(true);
    }

    public void loadStock(){

        this.increment = 0;

        this.stockList.getStock().forEach((code, product) -> {
            try {
                System.out.println(code + product.getName() + product.getQuantity());
                this.stockTable.setValueAt(code, this.increment, 0);
                this.stockTable.setValueAt(product.getName(), this.increment, 1);
                this.stockTable.setValueAt(product.getQuantity(), this.increment, 2);
                this.increment++;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        });
    }

    private void createUIComponents() {

        configStockList();

        loadStock();

    }
}
