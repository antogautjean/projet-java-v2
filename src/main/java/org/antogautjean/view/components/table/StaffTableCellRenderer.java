package org.antogautjean.view.components.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import org.antogautjean.controller.StaffController;

public class StaffTableCellRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 1L;
    protected StaffController staffCtrl;
    protected String[] orderedKeys;

    public StaffTableCellRenderer(StaffController staffCtrl) {
        this.staffCtrl = staffCtrl;

        // factory.getTableLineFormat() => Object[][] => Object[i][1]
        Object[][] tblLineFormat = staffCtrl.getTableLineFormat();
        this.orderedKeys = new String[tblLineFormat.length];
        for (int i = 0; i < tblLineFormat.length; i++) {
            Object[] line = tblLineFormat[i];
            this.orderedKeys[i] = (String) line[0]; // [0] car on tente de récupérer la colonne qui correspond à l'ID de
                                                    // la ligne de production
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {

        // super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
        // column);

        // cell text align: center
        setHorizontalAlignment(JLabel.CENTER);

        // border
        // setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        // alternate color
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        c.setBackground(row % 2 == 0 ? Color.white : Color.decode("#E8E8E8"));
        if ((table.getTableHeader().getColumnModel().getColumn(column).getHeaderValue().toString() != "Code") && (table
                .getTableHeader().getColumnModel().getColumn(column).getHeaderValue().toString() != "Qualification")) {
            String CodeChaine = staffCtrl.getEmployee(this.orderedKeys[row]).getPlanningOn(column - 2);
            if ((CodeChaine != null) && (CodeChaine.startsWith("C"))) {
                String[] colors = { "#FF0033", "#FF6600", "#FFCC00", "#FFCCFF", "#CC9999", "#009966", "#3399FF",
                        "#33CCCC", "#CC99CC", "#FFCC99", "#66CCCC", "#993333", "#996633" };
                setBackground(Color.decode(colors[Integer.parseInt(CodeChaine.substring(1)) % 12]));
            }
        }
        ;

        // if(staffCtrl.getEmployee(this.orderedKeys[row]).getPlanningOn()
        return this;
    }
}
