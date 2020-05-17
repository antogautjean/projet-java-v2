package org.antogautjean.view.tabs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Font;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.antogautjean.Controller.FactoryController;
import org.antogautjean.Controller.StockController;
import org.antogautjean.view.components.CustomJTable;
import org.antogautjean.view.components.TableRowFormatInterface;
import org.antogautjean.view.components.LinesTable.LinesTableModel;
import org.antogautjean.view.components.StockTable.StockTableModel;

public class FactoryTab implements TabInterface {
    protected StockController stockCtrl;
    protected FactoryController linesCtrl;

    protected CustomJTable stockTable;
    protected CustomJTable linesTable;

    protected DefaultTableModel stockTableModel;
    protected DefaultTableModel linesTableModel;

    protected DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
        private static final long serialVersionUID = 1L;

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setHorizontalAlignment(JLabel.CENTER);
            setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            c.setBackground(row % 2 == 0 ? Color.white : Color.decode("#E8E8E8"));
            return this;
        }
    };

    public FactoryTab(StockController stockCtrl, FactoryController lineCtrl) {
        this.stockCtrl = stockCtrl;
        this.linesCtrl = lineCtrl;
    }

    @Override
    public JComponent getComponent() throws Exception {
        // Refresh from file
        this.stockCtrl.refreshFromFile();
        this.linesCtrl.refreshFromFile();

        final String[] stockColumns = new String[] { "Code", "Nom", "Quantité actuelle", "Quantité à acheter",
                "Coût d'achat prévisionnel", "Nouvelle quantité après achat", "Quantité simulée après calcul" };
        final String[] linesColumns = new String[] { "Ordre de vérification", "Code", "Nom", "Code éléments en sortie",
                "Niveau d'activation", "Etat de la chaîne", "Quantité produite / quantité demandée" };

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

        configPanel(this.stockTable, this.stockTableModel, (TableRowFormatInterface) this.stockCtrl);
        configPanel(this.linesTable, this.linesTableModel, (TableRowFormatInterface) this.linesCtrl);

        JSplitPane SLsplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, stockPanel, linesPanel);
        SLsplitPane.setResizeWeight(.5);

        JSplitPane SLIsplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, SLsplitPane, indicatorsPanel);
        SLIsplitPane.setResizeWeight(.9);

        return SLIsplitPane;
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
        order.setFont(font);

        JPanel a = new JPanel();
        // a.setBackground(Color.GRAY);
        a.add(order);
        a.add(orders);

        Label value = new Label("Indicateur de commandes (Valeur totale du stock vendable)");
        Label total = new Label("1500€");
        total.setFont(font2);

        value.setFont(font);

        JPanel b = new JPanel();
        // b.setBackground(Color.GRAY);
        b.add(value);
        b.add(total);

        indicatorsPanel.add(new JSeparator(JSeparator.HORIZONTAL));
        indicatorsPanel.add(a);
        indicatorsPanel.add(b);

        // table_jt.setBounds(30,40,200,300);
        // JScrollPane scrollPane = new JScrollPane();
        // scrollPane.setViewportView(table);

        // indicatorsPanel.add(table);

    }

    class CustomPanel extends JPanel {
        private static final long serialVersionUID = 1L;

        public CustomPanel(Color backGroundColour) {
            setOpaque(true);
            setBackground(backGroundColour);
        }

        @Override
        public Dimension getPreferredSize() {
            return (new Dimension(200, 150));
        }
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

        this.stockTableModel = new StockTableModel(new Vector<>(), new Vector<>(Arrays.asList(stockColumns)));
        this.stockTable = new CustomJTable(stockTableModel);
        this.stockTable.setRowHeight(30);
        this.stockTable.setDefaultRenderer(Object.class, this.cellRenderer);

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

        this.linesTableModel = new LinesTableModel(new Vector<>(), new Vector<>(Arrays.asList(linesColumns)));
        this.linesTable = new CustomJTable(linesTableModel);
        this.linesTable.setRowHeight(30);
        this.linesTable.setDefaultRenderer(Object.class, this.cellRenderer);

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

    @Override
    public boolean isComponentRenderable() {
        return !this.linesCtrl.getIfFileImportFailed() && !this.stockCtrl.getIfFileImportFailed();
    }

    @Override
    public void refreshFromFile() throws IOException {
        this.linesCtrl.refreshFromFile();
        this.stockCtrl.refreshFromFile();
    }
}
