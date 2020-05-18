package org.antogautjean.view.tabs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.antogautjean.Controller.FactoryController;
import org.antogautjean.Controller.StockController;
import org.antogautjean.view.components.*;

public class FactoryTab extends DefaultTab implements TabInterface {
    protected StockController stockCtrl;
    protected FactoryController factoryCtrl;

    protected CustomJTable stockTable;
    protected CustomJTable linesTable;

    protected DefaultTableModel stockTableModel;
    protected DefaultTableModel linesTableModel;

    protected TableCellRenderer factoryCellRenderer;
    protected TableCellRenderer stockCellRenderer;

    public FactoryTab(StockController stockCtrl, FactoryController factoryCtrl) {
        this.stockCtrl = stockCtrl;
        this.factoryCtrl = factoryCtrl;

        factoryCellRenderer = new TableCellRenderer(factoryCtrl);
        stockCellRenderer = new TableCellRenderer();
    }

    @Override
    public JComponent getComponent() {
        if (areControllersFresh()) {

            final String[] stockColumns = new String[] { "Code", "Nom", "Quantité actuelle", "Quantité à acheter",
                    "Coût d'achat prévisionnel", "Nouvelle quantité après achat", "Quantité simulée après calcul" };
            final String[] linesColumns = new String[] { "Ordre de vérification", "Code", "Nom",
                    "Code éléments en sortie", "Niveau d'activation", "Etat de la chaîne",
                    "Quantité produite / quantité demandée" };

            // Test à destination des développeurs
            if (!Arrays.asList(linesColumns).contains("Etat de la chaîne")) {
                System.err.println("Attention : la colonne \"Etat de la chaîne\" est introuvable\n"
                        + "Or il y a un getTableCellRendererComponent qui utilise le nom de cette colonne");
            }

            JPanel stockPanel = new JPanel();
            configStockTable(stockPanel, stockColumns);

            JPanel linesPanel = new JPanel();
            configLinesTable(linesPanel, linesColumns);

            JPanel indicatorsPanel = new JPanel();
            configIndicatiorsTable(indicatorsPanel);

            configPanel(this.stockTable, this.stockTableModel, this.stockCtrl);
            configPanel(this.linesTable, this.linesTableModel, this.factoryCtrl);

            JSplitPane SLsplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, stockPanel, linesPanel);
            SLsplitPane.setResizeWeight(.5);

            JSplitPane SLIsplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, SLsplitPane, indicatorsPanel);
            SLIsplitPane.setResizeWeight(.9);

            this.ifRenderedCorrectly = true;
            return SLIsplitPane;
        } else {
            this.ifRenderedCorrectly = false;
            return notFoundDesign();
        }
    }

    private void configIndicatiorsTable(JPanel indicatorsPanel) {

        String[][] table_data = { { "1001", "Cherry" } };
        String[] table_column = { "Indicateur de valeur (Commande satisfaites)",
                "Indicateur de commandes (Valeur totale du stock vendable)" };
        JTable table = new JTable(table_data, table_column);
        table.setAlignmentX(Component.LEFT_ALIGNMENT);

        indicatorsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        indicatorsPanel.setLayout(new BoxLayout(indicatorsPanel, BoxLayout.Y_AXIS));

        // indicatorsPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        Font font = new Font("Arial", Font.BOLD, 14);
        Font font2 = new Font("Arial", Font.PLAIN, 18);

        JProgressBar orders;
        orders = new JProgressBar(0, 100);
        orders.setValue(50);
        orders.setStringPainted(true);

        Label order = new Label("Indicateur de valeurs (Commande satisfaites)");
        Label value = new Label("Indicateur de commandes (Valeur totale du stock vendable)");
        Label total = new Label("1500€");

        order.setFont(font);
        value.setFont(font);
        total.setFont(font2);

        JPanel a = new JPanel();
        JPanel b = new JPanel();

        a.add(order);
        a.add(orders);

        b.add(value);
        b.add(total);

        indicatorsPanel.add(new JSeparator(JSeparator.HORIZONTAL));
        indicatorsPanel.add(a);
        indicatorsPanel.add(b);

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
        topPanel.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(30, 10, 10, 10), "Stock",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, font, color));

        this.stockTableModel = new TableModel(new Vector<>(), new Vector<>(Arrays.asList(stockColumns)), new int[] { 3 });
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
    private void configLinesTable(JPanel bottomPanel, String[] linesColumns) {
        configJPanel(bottomPanel);

        Font font = new Font("Arial", Font.BOLD, 14);
        Color color = Color.BLACK;
        bottomPanel.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(30, 10, 10, 10), "Chaînes de production",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, font, color));

        this.linesTableModel = new TableModel(new Vector<>(), new Vector<>(Arrays.asList(linesColumns)),
                new int[] { 0, 4 });
        this.linesTable = new CustomJTable(linesTableModel);
        this.linesTable.setRowHeight(30);
        this.linesTable.setDefaultRenderer(Object.class, this.factoryCellRenderer);

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

        // Load data
        for (Object[] line : factory.getTableLineFormat()) {

            ctm.addRow(line);
        }
    }

    public boolean areControllersFresh() {
        try {
            this.factoryCtrl.refreshFromFile();
            this.stockCtrl.refreshFromFile();
        } catch (IOException e) {
            return false;
        }
        return this.factoryCtrl.getIfFileImportFailed() && this.stockCtrl.getIfFileImportFailed();
    }
}
