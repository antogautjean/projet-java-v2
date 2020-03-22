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
    private StockController stock;
    private JScrollPane stockScroll;

    private int increment = 0;

    /**
     *
     * @param stock
     * @param line
     */
    public HomeView(StockController stock, LineController line) {

        JFrame frame = new JFrame("Factory");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this.homePanel);
        frame.setVisible(true);
        this.stock = stock;

        createUIComponents();
    }

    /**
     *
     */
    private void configStockList() {

        DefaultTableModel tableModel = new DefaultTableModel(this.stock.getStock().size(), 3);
        stockTable.setModel(tableModel);

        JTableHeader header = stockTable.getTableHeader();
        TableColumnModel columnModel = header.getColumnModel();

        columnModel.getColumn(0).setHeaderValue("Identifiant");
        columnModel.getColumn(0).setPreferredWidth(80);
        columnModel.getColumn(1).setHeaderValue("Nom");
        columnModel.getColumn(1).setPreferredWidth(500);
        columnModel.getColumn(2).setHeaderValue("Quantité");
        columnModel.getColumn(2).setPreferredWidth(60);
        columnModel.getColumn(2).setCellEditor(new SpinnerEditor());

        header.repaint();

        stockTable.setAutoCreateRowSorter(true);
    }

    /**
     *
     */
    public void loadStock(){

        this.increment = 0;

        this.stock.getStock().forEach((code, product) -> {
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

    /**
     * Génére la première interface
     */
    private void createUIComponents() {

        configStockList();

        loadStock();

    }
}
