package org.antogautjean.view;

import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.antogautjean.Controller.FactoryController;
import org.antogautjean.Controller.StockController;

import java.awt.Component;
import java.awt.Dimension;

public class HomeView {

    protected StockController stockList;
    protected FactoryController linesList;

    protected JTabbedPane onglets;

    protected CustomJTable stockTable;
    protected CustomJTable linesTable;
    protected DefaultTableModel stockTableModel;
    protected DefaultTableModel linesTableModel;

    public HomeView(StockController stockList, FactoryController lineList) {

        JFrame frame = new JFrame("Factory");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setMinimumSize(new Dimension(1200,500));
        frame.setTitle("Gestionnaire de production");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setIconImage(new ImageIcon("./src/main/java/org/antogautjean/data/factory_icon.png").getImage());

        JPanel topPanel = new JPanel();
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // rajoute des marges
        topPanel.setLayout(new BorderLayout());
        topPanel.setMinimumSize(new Dimension(300,200));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // rajoute des marges
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setMinimumSize(new Dimension(300,200));

        this.stockList = stockList;
        this.linesList = lineList;

        // Stock panel - Top
        final String[] stockColumns = new String[] { "Code", "Nom", "Quantité actuelle", "Quantité à acheter",
                "Coût d'achat prévisionnel", "Nouvelle quantité après achat", "Quantité simulée après calcul" };
        this.stockTableModel = new StockTableModel(new Vector<>(), new Vector<>(Arrays.asList(stockColumns)));
        this.stockTable = new CustomJTable(stockTableModel);
        this.stockTable.setRowHeight(30);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                // setHorizontalAlignment(JLabel.CENTER);
                setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
                return this;
            }
        };
        TableColumnModel columnModel = this.stockTable.getColumnModel();
        this.stockTable.setDefaultRenderer(Object.class, cellRenderer);
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(300);
        columnModel.getColumn(3).setPreferredWidth(100);

        topPanel.add(new JScrollPane(this.stockTable));

        // Lines panel - Bottom
        final String[] linesColumns = new String[] { "Ordre de vérification", "Code", "Nom", "Code éléments en sortie",
                "Niveau d'activation", "Etat de la chaîne", "Quantité produite / quantité demandée" };
        this.linesTableModel = new LinesTableModel(new Vector<>(), new Vector<>(Arrays.asList(linesColumns)));
        this.linesTable = new CustomJTable(linesTableModel);
        this.linesTable.setRowHeight(30);
        this.linesTable.setDefaultRenderer(Object.class, cellRenderer); // adds padding
        bottomPanel.add(new JScrollPane(this.linesTable));

        configPanel(this.stockTable, this.stockTableModel, this.stockList);
        configPanel(this.linesTable, this.linesTableModel, this.linesList);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
        
        frame.getContentPane().add(splitPane);
        frame.setVisible(true);

        splitPane.setDividerLocation(.5);
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
