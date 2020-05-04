package org.antogautjean.view;

import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.antogautjean.Controller.FactoryController;
import org.antogautjean.Controller.StockController;
import org.antogautjean.view.Tabs.FactoryTab;
import org.antogautjean.view.Tabs.SettingsTab;
import org.antogautjean.view.Tabs.StaffTab;
import org.antogautjean.view.Tabs.TabInterface;
import org.antogautjean.view.elements.*;

public class HomeView {

    protected StockController stockList;
    protected FactoryController linesList;
    protected JTabbedPane tabs;
    protected CustomJTable stockTable;
    protected CustomJTable linesTable;
    protected DefaultTableModel stockTableModel;
    protected DefaultTableModel linesTableModel;

    public HomeView(StockController stockList, FactoryController lineList) {

        frameInit();

        TabInterface[] tabs = { new FactoryTab(), new StaffTab(),  new SettingsTab() };
        this.initTabs(tabs);
        // initTabs(factoryTab(stockList, lineList), settingTab());

        this.mainFrame.setVisible(true);
    }

    private void configJPanel(JPanel panel){
        //panel.setBorder(new EmptyBorder(30, 30, 30, 30)); // rajoute des marges
        panel.setLayout(new BorderLayout());
        panel.setMinimumSize(new Dimension(300,200));
    }

    private void configStockTable(JPanel topPanel, String[] stockColumns) {

        configJPanel(topPanel);

        Font font = new Font("Arial", Font.PLAIN, 14);
        Color color = Color.BLACK;
        topPanel.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(30, 10, 10, 10), "Stock", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, font, color));

        this.stockTableModel = new StockTableModel(new Vector<>(), new Vector<>(Arrays.asList(stockColumns)));
        this.stockTable = new CustomJTable(stockTableModel);
        this.stockTable.setRowHeight(30);

        TableColumnModel columnModel = this.stockTable.getColumnModel();
        this.stockTable.setDefaultRenderer(Object.class, createCellRenderer());
        columnModel.getColumn(0).setMaxWidth(50);
        columnModel.getColumn(1).setPreferredWidth(300);
        columnModel.getColumn(3).setPreferredWidth(100);

        topPanel.add(new JScrollPane(this.stockTable));
    }

    private void configLinesTable(JPanel bottomPanel, String[] linesColumns){

        configJPanel(bottomPanel);
        Font font = new Font("Arial", Font.PLAIN, 14);
        Color color = Color.BLACK;
        bottomPanel.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(30, 10, 10, 10), "Chaînes de production", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, font, color));

        this.linesTableModel = new LinesTableModel(new Vector<>(), new Vector<>(Arrays.asList(linesColumns)));
        this.linesTable = new CustomJTable(linesTableModel);
        this.linesTable.setRowHeight(30);
        this.linesTable.setDefaultRenderer(Object.class, createCellRenderer()); // adds padding


        TableColumnModel columnModel = this.linesTable.getColumnModel();
        this.linesTable.setDefaultRenderer(Object.class, createCellRenderer());
        columnModel.getColumn(0).setMinWidth(120);
        columnModel.getColumn(0).setMaxWidth(120);
        columnModel.getColumn(1).setMaxWidth(50);
        columnModel.getColumn(4).setMinWidth(110);
        columnModel.getColumn(4).setMaxWidth(100);

        bottomPanel.add(new JScrollPane(this.linesTable));
    }

    private DefaultTableCellRenderer createCellRenderer(){
        return new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(JLabel.CENTER);
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

    private JSplitPane factoryTab(StockController stockList, FactoryController lineList){

        this.stockList = stockList;
        this.linesList = lineList;
        final String[] stockColumns = new String[] { "Code", "Nom", "Quantité actuelle", "Quantité à acheter", "Coût d'achat prévisionnel", "Nouvelle quantité après achat", "Quantité simulée après calcul" };
        final String[] linesColumns = new String[] { "Ordre de vérification", "Code", "Nom", "Code éléments en sortie", "Niveau d'activation", "Etat de la chaîne", "Quantité produite / quantité demandée" };

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

    private void initTabs(TabInterface[] tabs){
        this.tabs = new JTabbedPane();

        for (int i = 0; i < tabs.length; i++) {
            TabInterface tab = tabs[i];
            this.tabs.add(tab.getTabTitle(), tab.getComponent());
            JLabel tabLabel = new JLabel(tab.getTabTitle());
            tabLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            this.tabs.setTabComponentAt(i, tabLabel);
        }

        this.mainFrame.getContentPane().add(this.tabs);
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
