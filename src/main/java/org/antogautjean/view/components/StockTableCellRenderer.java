package org.antogautjean.view.components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class StockTableCellRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 1L;




    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        //super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        //cell text align: center
        setHorizontalAlignment(JLabel.CENTER);

        //border
        //setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        //alternate color
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        c.setBackground(row%2==0 ? Color.white : Color.decode("#E8E8E8"));

        return this;
    }
}
