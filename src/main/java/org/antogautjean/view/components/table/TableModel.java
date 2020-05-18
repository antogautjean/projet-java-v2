package org.antogautjean.view.components.table;

import org.antogautjean.view.components.CheckboxCell;
import org.antogautjean.view.components.SpinnerCell;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class TableModel extends DefaultTableModel {
    private static final long serialVersionUID = 1L;
    protected int[] concernedIndexes;

    public TableModel(Vector<? extends Vector> v1, Vector<?> v2, int[] concernedIndexes){
        super(v1, v2);
        this.concernedIndexes = concernedIndexes;
    }

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
        // [0, 4] pour Factory
        // [3] pour Stock
        boolean res = false;
        for (int i = 0; i < this.concernedIndexes.length && !res; i++) {
            res = this.concernedIndexes[i] == colIndex;
        }
        return res;
    }

}
