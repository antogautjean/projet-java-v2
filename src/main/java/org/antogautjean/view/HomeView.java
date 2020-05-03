package org.antogautjean.view;

import org.antogautjean.Controller.FactoryController;
import org.antogautjean.Controller.StockController;

import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.*;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

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
        frame.setTitle("Gestionnaire de production");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // rajoute des marges
        topPanel.setLayout(new BorderLayout());
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(new EmptyBorder(0, 10, 10, 10)); // rajoute des marges
        bottomPanel.setLayout(new BorderLayout());
        frame.getContentPane().add(bottomPanel, BorderLayout.CENTER);

        this.stockList = stockList;
        this.linesList = lineList;

        // Stock panel - Top
        final String[] stockColumns = new String[] { "Code", "Nom", "Quantité actuelle", "Quantité à acheter",
                "Coût d'achat prévisionnel", "Nouvelle quantité après achat", "Quantité simulée après calcul" };
        this.stockTableModel = new StockTableModel(new Vector<>(), new Vector<>(Arrays.asList(stockColumns)));
        this.stockTable = new CustomJTable(stockTableModel);
        this.stockTable.setRowHeight(30);

        TableColumnModel columnModel = this.stockTable.getColumnModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );

        ((DefaultTableCellRenderer)this.stockTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        columnModel.getColumn(0).setMinWidth(50);
        columnModel.getColumn(0).setMaxWidth(50);
        columnModel.getColumn(0).setCellRenderer(centerRenderer);

        columnModel.getColumn(1).setMinWidth(300);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);

        columnModel.getColumn(2).setMinWidth(100);
        columnModel.getColumn(2).setMaxWidth(100);
        columnModel.getColumn(2).setCellRenderer(centerRenderer);

        columnModel.getColumn(3).setMinWidth(120);
        columnModel.getColumn(3).setMaxWidth(120);

        columnModel.getColumn(4).setMinWidth(150);
        columnModel.getColumn(4).setMaxWidth(150);
        columnModel.getColumn(4).setCellRenderer(centerRenderer);

        columnModel.getColumn(5).setMinWidth(180);
        columnModel.getColumn(5).setMaxWidth(180);
        columnModel.getColumn(5).setCellRenderer(centerRenderer);

        columnModel.getColumn(6).setMinWidth(160);
        columnModel.getColumn(6).setMaxWidth(160);
        columnModel.getColumn(6).setCellRenderer(centerRenderer);


        topPanel.add(new JScrollPane(this.stockTable));

        // Lines panel - Bottom
        final String[] linesColumns = new String[] { "Ordre de vérification", "Code", "Nom", "Code éléments en sortie",
                "Niveau d'activation", "Etat de la chaîne", "Quantité produite / quantité demandée" };
        this.linesTableModel = new LinesTableModel(new Vector<>(), new Vector<>(Arrays.asList(linesColumns)));
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
        for (Object[] line : factory.getTableLineFormat()) {
            ctm.addRow(line);
        }
    }
}
