package org.antogautjean.view.components.spinnercell;

import org.antogautjean.controller.ControllerFromFileInterface;
import org.antogautjean.controller.StockController;
import org.antogautjean.controller.StockMetaController;
import org.antogautjean.view.HomeView;

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

public class ActivationLevelSpinnerCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer, Comparable<ActivationLevelSpinnerCell> {

    private static final long serialVersionUID = 1L;
    private JSpinner editSpinner, renderSpinner;
    protected HomeView parentComponent;

    public ActivationLevelSpinnerCell(JSpinner showSpinner, String code, HomeView parentComponent) {

        ChangeListener listener = e -> {
            JSpinner s = (JSpinner) e.getSource();
            parentComponent.refreshTabs(false);
            System.out.println("Activation level: " + code + " -> " + s.getValue());
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

    @Override
    public int compareTo(ActivationLevelSpinnerCell o) {
        //System.out.println(o.getSpinner().getValue() + " " + this.getSpinner().getValue());
        if(Integer.parseInt(o.getSpinner().getValue().toString()) <= Integer.parseInt(this.getSpinner().getValue().toString())){
            return 0;
        }
        if(Integer.parseInt(o.getSpinner().getValue().toString()) > Integer.parseInt(this.getSpinner().getValue().toString())){
            return 1;
        }
        else {
            return 0;
        }
    }
}
