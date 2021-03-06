package org.antogautjean.view.components.table;

import org.antogautjean.view.components.CheckboxCell;
import org.antogautjean.view.components.spinnercell.SpinnerCell;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

/**
 * Classe représentant le tableau du staff
 */
public class StaffTableModel extends DefaultTableModel {
    private static final long serialVersionUID = 1L;

    public StaffTableModel(Vector<? extends Vector> v1, Vector<?> v2){
        super(v1, v2);
    }

    /**
     * Classe permettant de définit la valeur d'une case
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

    @Override
    public boolean isCellEditable(int rowIndex, int colIndex) {
        return colIndex == 3;
    }

}
