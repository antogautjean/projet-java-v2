package org.antogautjean.view.tabs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
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

import org.antogautjean.Controller.StaffController;
import org.antogautjean.view.components.CustomJTable;
import org.antogautjean.view.components.StaffTableModel;
import org.antogautjean.view.components.TableRowFormatInterface;

public class StaffTab extends DefaultTab implements TabInterface {

    protected StaffController staffCtrl;
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

    public StaffTab(StaffController staffCtrl) {
        this.staffCtrl = staffCtrl;
    }

    @Override
    public JComponent getComponent() {
        if (isControllerFresh()) {
            final String[] staffColumns = new String[] { "Code", "Qualification", "0H", "1H", "2H", "3H", "4H", "5H",
                    "6H", "7H", "8H", "9H", "10H", "11H", "12H", "13H", "14H", "15H", "16H", "17H", "18H", "19H", "20H",
                    "21H", "22H", "23H", "24H", "25H", "26H", "27H", "28H", "29H", "30H", "31H", "32H", "33H", "34H",
                    "35H" };

            JPanel staffPanel = new JPanel();
            configStaffTable(staffPanel, staffColumns);

            configPanel(this.staffTable, this.staffTableModel, this.staffCtrl);

            this.ifRenderedCorrectly = true;
            return staffPanel;
        } else {
            this.ifRenderedCorrectly = false;
            return notFoundDesign();
        }
    }

    private void configJPanel(JPanel panel) {
        // panel.setBorder(new EmptyBorder(30, 30, 30, 30)); // rajoute des marges
        panel.setLayout(new BorderLayout());
        panel.setMinimumSize(new Dimension(300, 200));
    }

    // Stock Table
    private void configStaffTable(JPanel panel, String[] staffColumns) {
        configJPanel(panel);

        Font font = new Font("Arial", Font.BOLD, 14);
        Color color = Color.BLACK;
        panel.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(30, 10, 10, 10), "Staff",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, font, color));

        this.staffTableModel = new StaffTableModel(new Vector<>(), new Vector<>(Arrays.asList(staffColumns)));
        this.staffTable = new CustomJTable(staffTableModel);
        this.staffTable.setRowHeight(30);
        this.staffTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        TableColumnModel columnModel = this.staffTable.getColumnModel();
        this.staffTable.setDefaultRenderer(Object.class, this.cellRenderer);

        JScrollPane scrollPanel = new JScrollPane(this.staffTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        columnModel.getColumn(0).setMaxWidth(50);
        columnModel.getColumn(1).setPreferredWidth(100);

        panel.add(scrollPanel);
    }

    @Override
    public String getTabTitle() {
        return "Gestion du personnel";
    }

    private void configPanel(CustomJTable cjt, DefaultTableModel ctm, TableRowFormatInterface staff) {
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
        for (Object[] line : staff.getTableLineFormat()) {
            ctm.addRow(line);
        }
    }

    public boolean isControllerFresh() {
        try {
            this.staffCtrl.refreshFromFile();
        } catch (IOException e) {
            return false;
        }
        return !this.staffCtrl.getIfFileImportFailed();
    }
}
