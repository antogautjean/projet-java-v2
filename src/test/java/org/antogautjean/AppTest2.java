package org.antogautjean;

import org.antogautjean.view.components.SpinnerCell;
import org.antogautjean.view.components.CustomJTable;
import org.antogautjean.view.components.StockTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.Vector;

public class AppTest2 {

    static int[] selectedRows = new int[0];

    final static String[] columns = new String[] { "Code", "Nom", "Quantit� actuelle", "Quantit� � acheter", "Co�t d'achat pr�visionnel", "Nouvelle quantit� apr�s achat", "Quantit� simul�e apr�s calcul" };

    final static DefaultTableModel model = new StockTableModel(new Vector(), new Vector(Arrays.asList(columns)));

    public static void main(String[] a) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JTable table = new CustomJTable(model);

        table.getTableHeader().setReorderingAllowed(false);
        table.getSelectionModel().addListSelectionListener(arg0 -> {
                if (table.getSelectedColumn() == 0) {
                    selectedRows = table.getSelectedRows();
                    System.out.println("Selected Rows before " + Arrays.toString(selectedRows));
                }
            });

        final ListSelectionModel columnListSelectionModel = table.getColumnModel().getSelectionModel();
        columnListSelectionModel.addListSelectionListener(e -> {

                if (table.getSelectedColumn() != 0) {

                    table.clearSelection();

                    System.out.println("Selected Rows during " + Arrays.toString(table.getSelectedRows()));

                    for (int selectedRow : selectedRows) {
                        table.getSelectionModel().addSelectionInterval(selectedRow, selectedRow);
                    }

                    System.out.println("Selected Rows after " + Arrays.toString(table.getSelectedRows()));
                }

            });

        model.addRow(new Object[] { "Bird", null, null,new SpinnerCell(new JSpinner()) });
        model.addRow(new Object[] { "Cat", null, null,new SpinnerCell(new JSpinner()) });

        frame.add(new JScrollPane(table));
        frame.setSize(1200, 800);
        frame.setVisible(true);
    }
}
