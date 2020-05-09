package org.antogautjean.view.components;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.util.EventObject;
import java.awt.Component;

public class CheckboxCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {

    private static final long serialVersionUID = 1L;
    private JCheckBox checkBox;

    public CheckboxCell(JCheckBox inputCheckBox) {
        checkBox = inputCheckBox;
    }

    @Override
    public Object getCellEditorValue() {
        return checkBox.isSelected();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,int column) {

        return checkBox;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row, int column) {

        return checkBox;
    }

    public JCheckBox getCheckBox() {
        return checkBox;
    }

    @Override
    public boolean isCellEditable(EventObject evt) {
        return true;
    }

    public String toString() {
        return checkBox.isSelected() + "";
    }

}