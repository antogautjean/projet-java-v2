package org.antogautjean.view.components.LinesTable;

import org.antogautjean.Controller.FactoryController;
import org.antogautjean.model.ProductionLineState;
import org.antogautjean.view.tabs.FactoryTab;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class LinesTableCellRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 1L;
    protected FactoryController factoryCtrl;
    protected String[] orderedKeys;

    public LinesTableCellRenderer(FactoryController factoryCtrl) {
        this.factoryCtrl = factoryCtrl;

        // factory.getTableLineFormat() => Object[][] => Object[i][1]
        Object[][] tblLineFormat = factoryCtrl.getTableLineFormat();
        this.orderedKeys = new String[tblLineFormat.length];
        for (int i = 0; i < tblLineFormat.length; i++) {
            Object[] line = tblLineFormat[i];
            this.orderedKeys[i] = (String) line[1]; // [1] car on tente de récupérer la colonne qui correspond à l'ID de la ligne de production
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        //super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        //cell text align: center
        setHorizontalAlignment(JLabel.CENTER);

        //border
        //setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        //alternate color
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        cell.setBackground(row%2==0 ? Color.white : Color.decode("#E8E8E8"));

        if(table.getTableHeader().getColumnModel().getColumn(column).getHeaderValue().toString() == "Etat de la chaîne"){
            if(factoryCtrl.getProductionLine(this.orderedKeys[row]).getState() == ProductionLineState.IMPOSSIBLE) setBackground(Color.RED);
        }

        return this;
    }
}
