package org.antogautjean.view;

import org.antogautjean.Controller.FactoryController;
import org.antogautjean.Controller.StockController;
import org.antogautjean.model.ProductionLine;
import org.antogautjean.model.ProductionLineState;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.event.ComponentAdapter;
import java.util.HashMap;

public class HomeView extends JPanel {

    private JPanel homePanel;
    private JTabbedPane Dashboard;
    private JButton chargerCeFichierButton1;
    private JTextField srcMainJavaOrgTextField;
    private JTextField srcMainJavaOrgTextField1;
    private JButton chargerCeFichierButton2;
    protected JTable stockTable;
    protected JTable factoryTable;
    private JButton chargerCeFichierButton;
    private JTextField srcMainJavaOrgTextField2;

    private int increment = 0;
    protected StockController stockList;
    protected FactoryController factoryList;

    public HomeView(StockController stockList, FactoryController factoryList) {

        JFrame frame = new JFrame("Factory");
        frame.setSize(1280, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this.homePanel);
        frame.setVisible(true);

        this.stockList = stockList;
        this.factoryList = factoryList;

        createUIComponents();
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

    private void configFactoryTable(){
        //HashMap<Integer, ProductionLine> p = linesList.getProductionLines();

        DefaultTableModel tableModel = new DefaultTableModel(this.factoryList.getProductionLines().size(), 7);
        this.factoryTable.setModel(tableModel);
        this.factoryTable.setRowHeight(40);

        JTableHeader header = this.factoryTable.getTableHeader();
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

        this.factoryTable.setAutoCreateRowSorter(true);
    }

    public void loadStock(){

        this.increment = 0;

        this.stockList.getStock().forEach((code, product) -> {
            try {
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
        this.stockTable.getRowSorter().toggleSortOrder(0);
    }

    public void loadFactory(){

        this.increment = 0;

        this.factoryList.getProductionLines().forEach((code, productLine) -> {
            try {
                int column = 0;
                this.factoryTable.setValueAt(productLine.getVerificationOrder(), this.increment, column++);
                this.factoryTable.setValueAt(code, this.increment, column++);
                this.factoryTable.setValueAt(productLine.getName(), this.increment, column++);
                this.factoryTable.setValueAt(String.join("\n", productLine.getOutputList()), this.increment, column++);
                this.factoryTable.setValueAt(productLine.getActivationLevel(), this.increment, column++);

                switch (productLine.getState()) {
                    case IMPOSSIBLE:
                        this.factoryTable.setValueAt("⚠ Production impossible", this.increment, column++);
                        break;
                    case POSSIBLE:
                        this.factoryTable.setValueAt("👍 Production possible", this.increment, column++);
                        break;
                    default: // NONE
                        this.factoryTable.setValueAt("Aucune production", this.increment, column++);
                }

                this.factoryTable.setValueAt("42% (42 / 100 kg)", this.increment, column++);

                this.increment++;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        });
        this.factoryTable.getRowSorter().toggleSortOrder(1);
    }

    private void createUIComponents() {

        configStockTable();
        configFactoryTable();

        loadStock();
        loadFactory();
    }
}
