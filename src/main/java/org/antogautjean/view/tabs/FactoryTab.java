package org.antogautjean.view.tabs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.antogautjean.Controller.FactoryController;
import org.antogautjean.Controller.StockController;
import org.antogautjean.view.elements.CustomJTable;
import org.antogautjean.view.elements.LinesTableModel;
import org.antogautjean.view.elements.StockTableModel;
import org.antogautjean.view.elements.TableLinesFormatInterface;

public class FactoryTab implements TabInterface {
    protected StockController stockList;
    protected FactoryController linesList;

    protected CustomJTable stockTable;
    protected CustomJTable linesTable;

    protected DefaultTableModel stockTableModel;
    protected DefaultTableModel linesTableModel;

    DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
        private static final long serialVersionUID = 1L;

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setHorizontalAlignment(JLabel.CENTER);
            setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
            return this;
        }
    };

    public FactoryTab(StockController stockList, FactoryController lineList) {
        this.stockList = stockList;
        this.linesList = lineList;
    }

    @Override
    public JComponent getComponent() {
        final String[] stockColumns = new String[] { "Code", "Nom", "Quantité actuelle", "Quantité à acheter",
                "Coût d'achat prévisionnel", "Nouvelle quantité après achat", "Quantité simulée après calcul" };
        final String[] linesColumns = new String[] { "Ordre de vérification", "Code", "Nom", "Code éléments en sortie",
                "Niveau d'activation", "Etat de la chaîne", "Quantité produite / quantité demandée" };

        JPanel topPanel = new JPanel();
        configStockTable(topPanel, stockColumns);

        JPanel bottomPanel = new JPanel();
        configLinesTable(bottomPanel, linesColumns);

        configPanel(this.stockTable, this.stockTableModel, this.stockList);
        configPanel(this.linesTable, this.linesTableModel, this.linesList);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
        splitPane.setResizeWeight(.5);
        return splitPane;
    }

    @Override
    public String getTabTitle() {
        return "Gestion de l'usine";
    }

    private void configJPanel(JPanel panel) {
        // panel.setBorder(new EmptyBorder(30, 30, 30, 30)); // rajoute des marges
        panel.setLayout(new BorderLayout());
        panel.setMinimumSize(new Dimension(300, 200));
    }

    // Stock Table
    private void configStockTable(JPanel topPanel, String[] stockColumns) {
        configJPanel(topPanel);

        Font font = new Font("Arial", Font.PLAIN, 14);
        Color color = Color.BLACK;
        topPanel.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(30, 10, 10, 10), "Stock",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, font, color));

        this.stockTableModel = new StockTableModel(new Vector<>(), new Vector<>(Arrays.asList(stockColumns)));
        this.stockTable = new CustomJTable(stockTableModel);
        this.stockTable.setRowHeight(30);

        TableColumnModel columnModel = this.stockTable.getColumnModel();
        this.stockTable.setDefaultRenderer(Object.class, this.cellRenderer);
        columnModel.getColumn(0).setMaxWidth(50);
        columnModel.getColumn(1).setPreferredWidth(300);
        columnModel.getColumn(3).setPreferredWidth(100);

        topPanel.add(new JScrollPane(this.stockTable));
    }

    // Lines Table
    private void configLinesTable(JPanel bottomPanel, String[] linesColumns){
        configJPanel(bottomPanel);
        
        Font font = new Font("Arial", Font.PLAIN, 14);
        Color color = Color.BLACK;
        bottomPanel.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(30, 10, 10, 10), "Chaînes de production", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, font, color));

        this.linesTableModel = new LinesTableModel(new Vector<>(), new Vector<>(Arrays.asList(linesColumns)));
        this.linesTable = new CustomJTable(linesTableModel);
        this.linesTable.setRowHeight(30);
        this.linesTable.setDefaultRenderer(Object.class, this.cellRenderer); // adds padding


        TableColumnModel columnModel = this.linesTable.getColumnModel();
        this.linesTable.setDefaultRenderer(Object.class, this.cellRenderer);
        columnModel.getColumn(0).setMinWidth(120);
        columnModel.getColumn(0).setMaxWidth(120);
        columnModel.getColumn(1).setMaxWidth(50);
        columnModel.getColumn(4).setMinWidth(110);
        columnModel.getColumn(4).setMaxWidth(100);

        bottomPanel.add(new JScrollPane(this.linesTable));
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
