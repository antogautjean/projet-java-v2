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

    private void configJPanel(JPanel panel){
        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // rajoute des marges
        panel.setLayout(new BorderLayout());
        panel.setMinimumSize(new Dimension(300,200));
    }


    private void configStockTable(JPanel topPanel, String[] stockColumns) {

        configJPanel(topPanel);

        this.stockTableModel = new StockTableModel(new Vector<>(), new Vector<>(Arrays.asList(stockColumns)));
        this.stockTable = new CustomJTable(stockTableModel);
        this.stockTable.setRowHeight(30);

        TableColumnModel columnModel = this.stockTable.getColumnModel();
        this.stockTable.setDefaultRenderer(Object.class, createCellRenderer());
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(300);
        columnModel.getColumn(3).setPreferredWidth(100);

        topPanel.add(new JScrollPane(this.stockTable));
    }

    private DefaultTableCellRenderer createCellRenderer(){
        return new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                // setHorizontalAlignment(JLabel.CENTER);
                setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
                return this;
            }
        };
    }

    private JFrame mainFrame;

    public void frameInit(){
        this.mainFrame = new JFrame("Factory");
        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.mainFrame.setMinimumSize(new Dimension(1200,500));
        this.mainFrame.setTitle("Gestionnaire de production");
        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.setIconImage(new ImageIcon("./src/main/java/org/antogautjean/data/factory_icon.png").getImage());
    }


    public HomeView(StockController stockList, FactoryController lineList) {

        frameInit();

        this.stockList = stockList;
        this.linesList = lineList;
        final String[] stockColumns = new String[] { "Code", "Nom", "Quantité actuelle", "Quantité à acheter", "Coût d'achat prévisionnel", "Nouvelle quantité après achat", "Quantité simulée après calcul" };
        final String[] linesColumns = new String[] { "Ordre de vérification", "Code", "Nom", "Code éléments en sortie", "Niveau d'activation", "Etat de la chaîne", "Quantité produite / quantité demandée" };

        JPanel topPanel = new JPanel();
        configStockTable(topPanel, stockColumns);
        
        JPanel bottomPanel = new JPanel();
        configJPanel(bottomPanel);

        this.linesTableModel = new LinesTableModel(new Vector<>(), new Vector<>(Arrays.asList(linesColumns)));
        this.linesTable = new CustomJTable(linesTableModel);
        this.linesTable.setRowHeight(30);
        this.linesTable.setDefaultRenderer(Object.class, createCellRenderer()); // adds padding
        bottomPanel.add(new JScrollPane(this.linesTable));

        configPanel(this.stockTable, this.stockTableModel, this.stockList);
        configPanel(this.linesTable, this.linesTableModel, this.linesList);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);

        // Onglets
        // Onglet 1 : Usine
        onglets = new JTabbedPane();
        onglets.add("", splitPane);
        JLabel tab0Label = new JLabel("Usine");
        tab0Label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        onglets.setTabComponentAt(0, tab0Label);
        
        // Onglet 2 : Param�tres
        JPanel settingsPanel = new JPanel();
        settingsPanel.add(new JLabel("En construction")); // TODO: à remplacer par le vrai truc
        onglets.add("", settingsPanel);
        JLabel tab1Label = new JLabel("Paramètres");
        tab1Label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        onglets.setTabComponentAt(1, tab1Label);

        this.mainFrame.getContentPane().add(onglets);
        this.mainFrame.setVisible(true);

        splitPane.setResizeWeight(.5);
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
