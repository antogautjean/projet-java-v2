package org.antogautjean.view;

import org.antogautjean.Controller.FactoryController;
import org.antogautjean.Controller.StockController;
import org.antogautjean.model.ProductionLine;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class HomeView {

    protected StockController stockList;
    protected FactoryController linesList;

    protected CustomJTable stockTable;
    protected CustomJTable linesTable;
    final DefaultTableModel stockTableModel;
    final DefaultTableModel linesTableModel;

    public HomeView(StockController stockList, FactoryController lineList) {

        JFrame frame = new JFrame("Factory");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setTitle("Gestion d'usine");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        topPanel.setLayout(new BorderLayout());
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(new EmptyBorder(0, 10, 10, 10));
        bottomPanel.setLayout(new BorderLayout());
        frame.getContentPane().add(bottomPanel, BorderLayout.CENTER);

        this.stockList = stockList;
        this.linesList = lineList;

        // Stock panel - Top
        final String[] stockColumns = new String[] { "Code", "Nom", "Quantité actuelle", "Quantité à acheter",
                "Coût d'achat prévisionnel", "Nouvelle quantité après achat", "Quantité simulée après calcul" };
        this.stockTableModel = new CustomTableModel(new Vector<>(), new Vector<>(Arrays.asList(stockColumns)));
        this.stockTable = new CustomJTable(stockTableModel);
        this.stockTable.setRowHeight(30);
        topPanel.add(new JScrollPane(this.stockTable));

        // Lines panel - Bottom
        final String[] linesColumns = new String[] { "Ordre de vérification", "Code", "Nom", "Code éléments en sortie",
                "Niveau d'activation", "Etat de la chaîne", "Quantité produite / quantité demandée" };
        this.linesTableModel = new CustomTableModel(new Vector<>(), new Vector<>(Arrays.asList(linesColumns)));
        this.linesTable = new CustomJTable(linesTableModel);
        this.linesTable.setRowHeight(30);
        bottomPanel.add(new JScrollPane(this.linesTable));

        configPanel(this.stockTable, this.stockTableModel, this.stockList);
        configPanel(this.linesTable, this.linesTableModel, this.linesList);

        frame.setVisible(true);
    }

    private void configPanel(CustomJTable cjt, DefaultTableModel ctm, TableLinesFormatInterface factory) {
        cjt.getTableHeader().setReorderingAllowed(true);
        cjt.getSelectionModel().addListSelectionListener(arg0 -> {
            int[] selectedRows = new int[0];
            if (cjt.getSelectedColumn() == 0) {
                selectedRows = cjt.getSelectedRows();
                System.out.println("Selected Rows before " + Arrays.toString(selectedRows));
            }
        });

        final ListSelectionModel columnListSelectionModel = cjt.getColumnModel().getSelectionModel();
        columnListSelectionModel.addListSelectionListener(e -> {
            int[] selectedRows = new int[0];

            if (cjt.getSelectedColumn() != 0) {
                cjt.clearSelection();
                System.out.println("Selected Rows during " + Arrays.toString(cjt.getSelectedRows()));
                for (int selectedRow : selectedRows) {
                    cjt.getSelectionModel().addSelectionInterval(selectedRow, selectedRow);
                }
                System.out.println("Selected Rows after " + Arrays.toString(cjt.getSelectedRows()));
            }
        });

        // Load data
        // for (Object[] line : factory.getTableLineFormat()) {
        //     ctm.addRow(line);
        // }
    }
}
