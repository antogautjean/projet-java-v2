package org.antogautjean.view.components.table;

import org.antogautjean.view.components.CheckboxCell;
import org.antogautjean.view.components.spinnercell.SpinnerCell;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

/**
 * Classe représentant un modèle tableau
 */
public class TableModel extends DefaultTableModel {
    private static final long serialVersionUID = 1L;
    protected Object concernedIndex;

    public TableModel(Vector<? extends Vector> v1, Vector<?> v2, Object concernedIndex){
        super(v1, v2);
        this.concernedIndex = concernedIndex;
    }

    /**
     * Permet de definir la valeur d'une cellule
     * @param obj
     * @param row
     * @param col
     */
    @Override
    public void setValueAt(Object obj, int row, int col) {

        if (obj instanceof Boolean || obj instanceof Integer) {
            Object localObject = super.getValueAt(row, col);
            if (localObject instanceof Integer) {

                Integer val = (Integer) localObject;

                ((SpinnerCell) obj).getSpinner().setValue(val);
            } else if (localObject instanceof Boolean) {

                Boolean val = (Boolean) localObject;

                ((CheckboxCell) obj).getCheckBox().setEnabled(val);
            }

        } else {
            super.setValueAt(obj, row, col);
        }

    }

    /**
     * Permet de savoir si une cellule est éditable
     * @param rowIndex
     * @param colIndex
     * @return
     */
    @Override
    public boolean isCellEditable(int rowIndex, int colIndex) {
        // [0, 4] pour Factory
        // [3] pour Stock
        if(this.concernedIndex.equals("stockPanel") ){
            return colIndex == 3;
        }
        if(this.concernedIndex.equals("linesPanel") ){
            return colIndex == 0 || colIndex == 4;
        }
        else {
            System.err.println("Erreur d'appel à TableModel");
            return false;
        }

    }

    @Override
    public Class getColumnClass(int column) {

        if(this.concernedIndex.equals("stockPanel") ){
            switch (column) {
                case 0:
                case 3:
                    return Integer.class;
                default:
                    return String.class;
            }
        }

        if(this.concernedIndex.equals("linesPanel") ){
            switch (column) {
                case 0:
                    return Integer.class;
                case 4:
                    return Integer.class;
                default:
                    return String.class;
            }
        }

        return String.class;

    }

}
