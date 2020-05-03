package org.antogautjean.view;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class CustomJTable extends JTable {

    DefaultTableModel model;

    public CustomJTable(DefaultTableModel model){
        super(model);
        this.model = model;
    }

    @Override
    public TableCellRenderer getCellRenderer(final int rowIndex, int colIndex) {

        int reaRowlIndex = convertRowIndexToModel(rowIndex);
        int realColumnIndex = convertColumnIndexToModel(colIndex);

        Object o = this.model.getValueAt(reaRowlIndex, realColumnIndex);

        if (o instanceof TableCellRenderer) {
            return (TableCellRenderer) o;
        } else {
            return super.getCellRenderer(reaRowlIndex, realColumnIndex);
        }
    }

    //
    @Override
    public TableCellEditor getCellEditor(final int rowIndex, int colIndex) {

        int reaRowlIndex = convertRowIndexToModel(rowIndex);
        int realColumnIndex = convertColumnIndexToModel(colIndex);

        Object o = this.model.getValueAt(reaRowlIndex, realColumnIndex);

        if (o instanceof TableCellEditor) {
            return (TableCellEditor) o;
        } else {
            return super.getCellEditor(reaRowlIndex, realColumnIndex);
        }
    }


}
