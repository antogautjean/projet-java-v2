package org.antogautjean.view.tabs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.antogautjean.Controller.FactoryController;
import org.antogautjean.Controller.StockController;
import org.antogautjean.view.components.*;

public class FactoryTab implements TabInterface {
    protected StockController stockList;
    protected FactoryController linesList;

    protected CustomJTable stockTable;
    protected CustomJTable linesTable;

    protected DefaultTableModel stockTableModel;
    protected DefaultTableModel linesTableModel;

    StockTableCellRenderer stockCellRenderer = new StockTableCellRenderer();
    LinesTableCellRenderer linesCellRenderer;

    public static boolean[] linesState = {false, false};

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

        // Test à destination des développeurs
        if (!Arrays.asList(linesColumns).contains("Etat de la chaîne")) {
            System.err.println("Attention : la colonne \"Etat de la chaîne\" est introuvable\n"
                    + "Or il y a un getTableCellRendererComponent qui utilise le nom de cette colonne");
        }

        JPanel topPanel = new JPanel();
        configStockTable(topPanel, stockColumns);

        JPanel bottomPanel = new JPanel();
        configLinesTable(bottomPanel, linesColumns, this.linesList);

        if(this.stockList != null && this.linesList != null){
            configPanel(this.stockTable, this.stockTableModel, this.stockList);
            configPanel(this.linesTable, this.linesTableModel, this.linesList);
        }

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, topPanel, bottomPanel);
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

        Font font = new Font("Arial", Font.BOLD, 14);
        Color color = Color.BLACK;
        topPanel.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(30, 10, 10, 10), "Stock", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, font, color));

        this.stockTableModel = new StockTableModel(new Vector<>(), new Vector<>(Arrays.asList(stockColumns)));
        this.stockTable = new CustomJTable(stockTableModel);
        this.stockTable.setRowHeight(30);
        this.stockTable.setDefaultRenderer(Object.class, this.stockCellRenderer);

        TableColumnModel columnModel = this.stockTable.getColumnModel();
        columnModel.getColumn(0).setMaxWidth(50);
        columnModel.getColumn(1).setPreferredWidth(300);
        columnModel.getColumn(3).setPreferredWidth(100);


        topPanel.add(new JScrollPane(this.stockTable));
    }

    // Lines Table
    private void configLinesTable(JPanel bottomPanel, String[] linesColumns, FactoryController factory){
        this.linesCellRenderer = new LinesTableCellRenderer(factory);
        configJPanel(bottomPanel);

        Font font = new Font("Arial", Font.BOLD, 14);
        Color color = Color.BLACK;
        bottomPanel.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(30, 10, 10, 10), "Chaînes de production", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, font, color));

        this.linesTableModel = new LinesTableModel(new Vector<>(), new Vector<>(Arrays.asList(linesColumns)));
        this.linesTable = new CustomJTable(linesTableModel);
        this.linesTable.setRowHeight(30);
        this.linesTable.setDefaultRenderer(Object.class, this.linesCellRenderer);


        TableColumnModel columnModel = this.linesTable.getColumnModel();
        columnModel.getColumn(0).setMinWidth(120);
        columnModel.getColumn(0).setMaxWidth(120);
        columnModel.getColumn(1).setMaxWidth(50);
        columnModel.getColumn(4).setMinWidth(110);
        columnModel.getColumn(4).setMaxWidth(100);

        bottomPanel.add(new JScrollPane(this.linesTable));
    }

    private void configPanel(CustomJTable cjt, DefaultTableModel ctm, TableRowFormatInterface factory) {
        cjt.getTableHeader().setReorderingAllowed(true);
        cjt.getSelectionModel().addListSelectionListener(arg0 -> {
            int[] selectedRows;
            if (cjt.getSelectedColumn() == 0) {
                selectedRows = cjt.getSelectedRows();
                System.out.println("Selected Rows before " + Arrays.toString(selectedRows));
            }
        });

        final ListSelectionModel columnListSelectionModel = cjt.getColumnModel().getSelectionModel();
        columnListSelectionModel.addListSelectionListener(e -> {
            if (cjt.getSelectedColumn() != 0) {
                cjt.clearSelection();
                System.out.println("Selected Rows during " + Arrays.toString(cjt.getSelectedRows()));
                System.out.println("Selected Rows after " + Arrays.toString(cjt.getSelectedRows()));
            }
        });

        // Load data
        for (Object[] line : factory.getTableLineFormat()) {

            ctm.addRow(line);
        }
    }
}
