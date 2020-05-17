package org.antogautjean;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Arrays;
import java.util.EventObject;
import java.util.Vector;

class AppTest {

    static int[] selectedRows = new int[0];

    final static String[] columns = new String[] { "Layer", "Enabled", "Read Only", "Storage" };

    final static DefaultTableModel model = new DefaultTableModel(new Vector<>(), new Vector<>(Arrays.asList(columns))) {
        private static final long serialVersionUID = 1L;

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

            return colIndex != 0;
        }

    };

    public static void main(String[] a) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                final JTable table = new JTable(model) {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public TableCellRenderer getCellRenderer(final int rowIndex, int colIndex) {

                        int reaRowlIndex = convertRowIndexToModel(rowIndex);
                        int realColumnIndex = convertColumnIndexToModel(colIndex);

                        Object o = model.getValueAt(reaRowlIndex, realColumnIndex);

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

                        Object o = model.getValueAt(reaRowlIndex, realColumnIndex);

                        if (o instanceof TableCellEditor) {
                            return (TableCellEditor) o;
                        } else {
                            return super.getCellEditor(reaRowlIndex, realColumnIndex);
                        }
                    }

                };

                table.getTableHeader().setReorderingAllowed(false);

                table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

                    @Override
                    public void valueChanged(ListSelectionEvent arg0) {
                        if (table.getSelectedColumn() == 0) {
                            selectedRows = table.getSelectedRows();

                            System.out.println("Selected Rows before " + Arrays.toString(selectedRows));
                        }

                    }
                });

                final ListSelectionModel columnListSelectionModel = table.getColumnModel().getSelectionModel();
                columnListSelectionModel.addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {

                        if (table.getSelectedColumn() != 0) {

                            table.clearSelection();

                            System.out.println("Selected Rows during " + Arrays.toString(table.getSelectedRows()));

                            for (int i = 0; i < selectedRows.length; i++) {
                                table.getSelectionModel().addSelectionInterval(selectedRows[i], selectedRows[i]);
                            }

                            System.out.println("Selected Rows after " + Arrays.toString(table.getSelectedRows()));
                        }

                    }
                });

                model.addRow(new Object[] { "Bird", new CheckboxCell(new JCheckBox()),
                        new CheckboxCell(new JCheckBox()), new SpinnerCell(new JSpinner()) });

                model.addRow(new Object[] { "Cat", new CheckboxCell(new JCheckBox()), new CheckboxCell(new JCheckBox()),
                        new SpinnerCell(new JSpinner()) });

                model.addRow(new Object[] { "Dog", new CheckboxCell(new JCheckBox()), new CheckboxCell(new JCheckBox()),
                        new SpinnerCell(new JSpinner()) });

                model.addRow(new Object[] { "Fish", new CheckboxCell(new JCheckBox()),
                        new CheckboxCell(new JCheckBox()), new SpinnerCell(new JSpinner()) });

                model.addRow(new Object[] { "Pig", new CheckboxCell(new JCheckBox()), new CheckboxCell(new JCheckBox()),
                        new SpinnerCell(new JSpinner()) });

                frame.add(new JScrollPane(table));

                frame.setSize(300, 200);
                frame.setVisible(true);

            }

        });

    }

    static class CheckboxCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {

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
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                                                     int column) {

            return checkBox;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {

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

    static class SpinnerCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {

        private static final long serialVersionUID = 1L;
        private JSpinner editSpinner, renderSpinner;

        public SpinnerCell() {
            editSpinner = new JSpinner();
            JTextField tf = ((JSpinner.DefaultEditor) editSpinner.getEditor()).getTextField();
            tf.setForeground(Color.black);
            renderSpinner = new JSpinner();
            JTextField tf2 = ((JSpinner.DefaultEditor) renderSpinner.getEditor()).getTextField();
            tf2.setForeground(Color.black);
        }

        public SpinnerCell(JSpinner showSpinner) {
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
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                                                     int column) {

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

}
