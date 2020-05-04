package org.antogautjean.view.tabs;

import org.antogautjean.Controller.StaffController;
import org.antogautjean.view.elements.CustomJTable;
import org.antogautjean.view.elements.StaffTableModel;
import org.antogautjean.view.elements.TableStaffFormatInterface;

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
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class StaffTab implements TabInterface {

    protected StaffController staffList;
    protected CustomJTable staffTable;
    protected DefaultTableModel staffTableModel;

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

    public StaffTab(StaffController staffList) {
        this.staffList = staffList;
    }

    @Override
    public JComponent getComponent() {
    	final String[] staffColumns = new String[] { "Code", "Nom", "0H","1H","2H","3H","4H","5H","6H","7H","8H","9H","10H",
    												"11H","12H","13H","14H","15H","16H","17H","18H","19H","20H","21H",
    												"22H","23H"};

        JPanel staffPanel = new JPanel();
        configStaffTable(staffPanel, staffColumns);
        configPanel(this.staffTable, this.staffTableModel, this.staffList);


        staffPanel.add(new JLabel(getTabTitle() + " en construction")); // TODO: � remplacer par le vrai truc
        return staffPanel;
    }


    private void configJPanel(JPanel panel) {
        // panel.setBorder(new EmptyBorder(30, 30, 30, 30)); // rajoute des marges
        panel.setLayout(new BorderLayout());
        panel.setMinimumSize(new Dimension(300, 200));
    }

    // Stock Table
    private void configStaffTable(JPanel topPanel, String[] staffColumns) {
        configJPanel(topPanel);

        Font font = new Font("Arial", Font.PLAIN, 14);
        Color color = Color.BLACK;
        topPanel.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(30, 10, 10, 10), "Staff",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, font, color));

        this.staffTableModel = new StaffTableModel(new Vector<>(), new Vector<>(Arrays.asList(staffColumns)));
        this.staffTable = new CustomJTable(staffTableModel);
        this.staffTable.setRowHeight(30);

        TableColumnModel columnModel = this.staffTable.getColumnModel();
        this.staffTable.setDefaultRenderer(Object.class, this.cellRenderer);
        columnModel.getColumn(0).setMaxWidth(50);
        columnModel.getColumn(1).setPreferredWidth(300);
        columnModel.getColumn(3).setPreferredWidth(100);

        topPanel.add(new JScrollPane(this.staffTable));
    }

    @Override
    public String getTabTitle() {
        return "Gestion du personnel";
    }

    private void configPanel(CustomJTable cjt, DefaultTableModel ctm, TableStaffFormatInterface staff) {
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
        for (Object[] line : staff.getTableStaffFormat()) {
            ctm.addRow(line);
        }
    }
}
