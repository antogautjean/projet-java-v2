package org.antogautjean.view;

import org.antogautjean.Controller.FactoryController;
import org.antogautjean.Controller.StockController;
import org.antogautjean.model.ProductionLine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.event.ComponentAdapter;
import java.util.HashMap;

public class HomeView extends JPanel {

    private JPanel homePanel;
    private JTabbedPane Dashboard;
    private JButton button1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton loadButton;
    protected JTable stockTable;
    protected JTable linesTable;

    private int increment = 0;
    protected StockController stockList;
    protected FactoryController linesList;

    public HomeView(StockController stockList, FactoryController lineList) {

        JFrame frame = new JFrame("Factory");
        frame.setSize(1280, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this.homePanel);
        frame.setVisible(true);

        this.stockList = stockList;
        this.linesList = lineList;

        createUIComponents();
        linesTable.addComponentListener(new ComponentAdapter() {
        });
    }

    public void updateLine(){

    }

    public void updateStock(){

    }

    private void configStockTable(){

        DefaultTableModel tableModel = new DefaultTableModel(this.stockList.getStock().size(), 7);
        this.stockTable.setModel(tableModel);

        JTableHeader header = this.stockTable.getTableHeader();
        TableColumnModel columnModel = header.getColumnModel();

        columnModel.getColumn(0).setHeaderValue("Code");
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setHeaderValue("Nom");
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setHeaderValue("Quantité actuelle");
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setHeaderValue("Quantité à acheter");
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(4).setHeaderValue("Coût d'achat prévisionnel");
        columnModel.getColumn(4).setPreferredWidth(60);
        columnModel.getColumn(5).setHeaderValue("Nouvelle quantité après achat");
        columnModel.getColumn(5).setPreferredWidth(100);
        columnModel.getColumn(6).setHeaderValue("Quantité simulée après calcul");
        columnModel.getColumn(6).setPreferredWidth(100);
        header.repaint();

        this.stockTable.setAutoCreateRowSorter(true);
    }

    private void configLinesTable(){
        //HashMap<Integer, ProductionLine> p = linesList.getProductionLines();

        DefaultTableModel tableModel = new DefaultTableModel(/*this.linesList.getProductionLines().size()*/ 10, 7);
        this.linesTable.setModel(tableModel);
        this.linesTable.setRowHeight(40);

        JTableHeader header = this.linesTable.getTableHeader();
        TableColumnModel columnModel = header.getColumnModel();

        columnModel.getColumn(0).setHeaderValue("Ordre");
        columnModel.getColumn(0).setPreferredWidth(20);
        columnModel.getColumn(1).setHeaderValue("Code");
        columnModel.getColumn(1).setPreferredWidth(40);
        columnModel.getColumn(2).setHeaderValue("Nom");
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setHeaderValue("Code éléments en sortie");
        columnModel.getColumn(3).setPreferredWidth(60);
        columnModel.getColumn(4).setHeaderValue("Niveau d'activation");
        columnModel.getColumn(4).setPreferredWidth(100);
        columnModel.getColumn(5).setHeaderValue("Etat de la chaine");
        columnModel.getColumn(5).setPreferredWidth(60);
        columnModel.getColumn(6).setHeaderValue("Quantité produite / demandée");
        columnModel.getColumn(6).setPreferredWidth(80);
        header.repaint();

        this.linesTable.setAutoCreateRowSorter(true);
    }

    public void loadStock(){

        this.increment = 0;

        this.stockList.getStock().forEach((code, product) -> {
            try {
                System.out.println(code + product.getName() + product.getQuantity());
                this.stockTable.setValueAt(code, this.increment, 0);
                this.stockTable.setValueAt(product.getName(), this.increment, 1);
                this.stockTable.setValueAt(product.getQuantity(), this.increment, 2);
                this.stockTable.setValueAt(0, this.increment, 3);

                if (product.getBuyPrice() == null)
                    this.stockTable.setValueAt("N/A", this.increment, 4);
                else
                    this.stockTable.setValueAt(product.getBuyPrice() * product.getDemand(), this.increment, 4);

                this.stockTable.setValueAt(product.getQuantity() + product.getDemand(), this.increment, 5);
                this.stockTable.setValueAt(	product.getQuantity() - product.getDemand(), this.increment, 6);

                this.increment++;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        });
    }

    private void createUIComponents() {

        configStockTable();
        configLinesTable();

        loadStock();
    }
}
