package org.antogautjean.view.components;

import org.antogautjean.controller.StockMetaController;
import java.awt.Color;
import java.awt.Component;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class SpinnerCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {

    private static final long serialVersionUID = 1L;
    private JSpinner editSpinner, renderSpinner;

    public SpinnerCell(JSpinner showSpinner, String code, String className, StockMetaController metaController) {

        ChangeListener listener = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSpinner s = (JSpinner) e.getSource();
                System.out.println(className + " " + code + " " + s.getValue());
                metaController.getProductAfterCalculation(code).setQuantityToBuy(Integer.parseInt(s.getValue().toString()));
            }
        };

        showSpinner.addChangeListener(listener);

        editSpinner = showSpinner;
        JTextField tf = ((JSpinner.DefaultEditor) editSpinner.getEditor()).getTextField();
        tf.setForeground(Color.black);
        renderSpinner = showSpinner;
        JTextField tf2 = ((JSpinner.DefaultEditor) renderSpinner.getEditor()).getTextField();
        tf2.setForeground(Color.black);
    }

    @Override
    public Object getCellEditorValue() {
        return editSpinner.getValue();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        return editSpinner;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return renderSpinner;
    }

    public String toString() {
        return editSpinner.getValue().toString();
    }

    public JSpinner getSpinner() {
        return editSpinner;
    }

    @Override
    public boolean isCellEditable(EventObject evt) {
        return true;
    }
}
