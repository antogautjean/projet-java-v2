package org.antogautjean.view.components.spinnercell;

import java.awt.Color;
import java.awt.Component;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import org.antogautjean.controller.StockMetaController;
import org.antogautjean.view.HomeView;

public class QuantityToBuySpinnerCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{

    private static final long serialVersionUID = 1L;
    private JSpinner editSpinner, renderSpinner;

    public QuantityToBuySpinnerCell(JSpinner showSpinner, String code, StockMetaController metaStock, HomeView parentComponent) {

        ChangeListener listener = e -> {
            JSpinner s = (JSpinner) e.getSource();
            metaStock.getRealProduct(code).setQuantityToBuy(Integer.parseInt(s.getValue().toString()));
            parentComponent.refreshStockPanel();
            parentComponent.refreshIndicPanel();
            System.out.println("Quantity to buy : " + code + " -> " + s.getValue());
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
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
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
