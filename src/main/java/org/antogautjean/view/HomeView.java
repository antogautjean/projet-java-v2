package org.antogautjean.view;

import org.antogautjean.Controller.FactoryController;
import org.antogautjean.Controller.StockController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.Vector;

public class HomeView {

    protected StockController stockList;
    protected FactoryController linesList;

    protected customJTable stockTable;
    final DefaultTableModel model;

    public HomeView(StockController stockList, FactoryController lineList) {

        JFrame frame = new JFrame("Factory");
        frame.setSize(1280, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.stockList = stockList;
        this.linesList = lineList;

        final String[] columns = new String[] { "Code", "Nom", "Quantité actuelle", "Quantité à acheter", "Coût d'achat prévisionnel", "Nouvelle quantité après achat", "Quantité simulée après calcul" };

        this.model = new customTableModel(new Vector(), new Vector(Arrays.asList(columns)));
        this.stockTable = new customJTable(model);

        this.stockTable.setRowHeight(30);

        frame.add(new JScrollPane(this.stockTable));

        createUIComponents();

        frame.setVisible(true);
    }

    private void createUIComponents() {

        configStockTable();

        //configLinesTable();

        loadStock();

    }

    private void loadStock() {

        stockList.getStock().forEach((code, product) -> {

            String prevision = product.getBuyPrice() == null ? "N/A" : (product.getBuyPrice() * product.getDemand()) + "";

            model.addRow(new Object[] { product.getCode(), product.getName(), product.getQuantity(),new SpinnerCell(new JSpinner()), prevision, product.getQuantity() + product.getQuantityToBuy(), product.getQuantity() - product.getQuantityToBuy()});
        });

    }

    private void configStockTable() {

        stockTable.getTableHeader().setReorderingAllowed(false);
        stockTable.getSelectionModel().addListSelectionListener(arg0 -> {
            int[] selectedRows = new int[0];
            if (stockTable.getSelectedColumn() == 0) {
                selectedRows = stockTable.getSelectedRows();
                System.out.println("Selected Rows before " + Arrays.toString(selectedRows));
            }
        });

        final ListSelectionModel columnListSelectionModel = stockTable.getColumnModel().getSelectionModel();
        columnListSelectionModel.addListSelectionListener(e -> {

            int[] selectedRows = new int[0];

            if (stockTable.getSelectedColumn() != 0) {

                stockTable.clearSelection();

                System.out.println("Selected Rows during " + Arrays.toString(stockTable.getSelectedRows()));

                for (int selectedRow : selectedRows) {
                    stockTable.getSelectionModel().addSelectionInterval(selectedRow, selectedRow);
                }

                System.out.println("Selected Rows after " + Arrays.toString(stockTable.getSelectedRows()));
            }

        });
    }
}
