package org.antogautjean.view.components.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import org.antogautjean.controller.FactoryController;
import org.antogautjean.model.ProductionLineState;
import org.antogautjean.view.HomeView;

/**
 * Cette classe permet de gérer le rendu du tableau Factory
 */
public class TableCellRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 1L;
    protected FactoryController factoryCtrl;
    protected String[] orderedKeys;
    protected JButton button = new JButton();

    /**
     * Construit de TableCellRenderer et initialise orderedKeys
     * @param factoryCtrl
     */
    public TableCellRenderer(FactoryController factoryCtrl, HomeView parentComponent) {
        this.factoryCtrl = factoryCtrl;

        // factory.getTableLineFormat() => Object[][] => Object[i][1]
        Object[][] tblLineFormat = factoryCtrl.getTableLineFormat(parentComponent);
        this.orderedKeys = new String[tblLineFormat.length];
        for (int i = 0; i < tblLineFormat.length; i++) {
            Object[] line = tblLineFormat[i];
            this.orderedKeys[i] = (String) line[1]; // [1] car on tente de récupérer la colonne qui correspond à l'ID de
                                                    // la ligne de production
        }
    }

    public TableCellRenderer() {
        this.factoryCtrl = null;
    }

    /**
     * L'appel de cette fonction permet de savoir comment doit etre afficher la cellule entrée en parametre
     * @param table
     * @param value
     * @param isSelected
     * @param hasFocus
     * @param row
     * @param column
     * @return
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);// IMPORTANT

        // border
        setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        // setHorizontalAlignment(JLabel.CENTER);

        // alternate color
        if (isSelected) {
            setForeground(Color.WHITE);
            setBackground(Color.decode("#0066CC"));
        } else {
            setForeground(Color.BLACK);
            setBackground(row % 2 == 0 ? Color.white : Color.decode("#E8E8E8"));
        }

        // Dans le cas de la partie Factory on a un truc custom
        // if (this.factoryCtrl != null) {
        //     if (table.getTableHeader().getColumnModel().getColumn(column).getHeaderValue().toString()
        //             .equals("Etat de la chaîne")
        //             && factoryCtrl.getProductionLine(this.orderedKeys[row])
        //                     .getState() == ProductionLineState.IMPOSSIBLE) {
        //         setForeground(Color.WHITE);
        //         if (isSelected) {
        //             setBackground(Color.decode("#B30000"));
        //         } else {
        //             setBackground(Color.decode("#FF3333"));
        //         }
        //     }
        // }

        return this;
    }
}
