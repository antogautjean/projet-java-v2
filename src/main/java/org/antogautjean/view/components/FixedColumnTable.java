package org.antogautjean.view.components;

import java.beans.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

/*
 *  Prevent the specified number of columns from scrolling horizontally in
 *  the scroll pane. The table must already exist in the scroll pane.
 *
 *  The functionality is accomplished by creating a second JTable (fixed)
 *  that will share the TableModel and SelectionModel of the main table.
 *  This table will be used as the row header of the scroll pane.
 *
 *  The fixed table created can be accessed by using the getFixedTable()
 *  method. will be returned from this method. It will allow you to:
 *
 *  You can change the model of the main table and the change will be
 *  reflected in the fixed model. However, you cannot change the structure
 *  of the model.
 */
public class FixedColumnTable implements ChangeListener, PropertyChangeListener
{
    private JTable main;
    private JTable fixed;
    private JScrollPane scrollPane;

    /*
     *  Specify the number of columns to be fixed and the scroll pane
     *  containing the table.
     */
    public FixedColumnTable(int fixedColumns, JScrollPane scrollPane)
    {
        this.scrollPane = scrollPane;

        main = ((JTable)scrollPane.getViewport().getView());
        main.setAutoCreateColumnsFromModel( false );
        main.addPropertyChangeListener( this );

        //  Use the existing table to create a new table sharing
        //  the DataModel and ListSelectionModel

        int totalColumns = main.getColumnCount();

        fixed = new JTable();
        fixed.setAutoCreateColumnsFromModel( false );
        fixed.setModel( main.getModel() );
        fixed.setSelectionModel( main.getSelectionModel() );
        fixed.setFocusable( false );

        //  Remove the fixed columns from the main table
        //  and add them to the fixed table

        for (int i = 0; i < fixedColumns; i++)
        {
            TableColumnModel columnModel = main.getColumnModel();
            TableColumn column = columnModel.getColumn( 0 );
            columnModel.removeColumn( column );
            fixed.getColumnModel().addColumn( column );
        }

        //  Add the fixed table to the scroll pane

        fixed.setPreferredScrollableViewportSize(fixed.getPreferredSize());
        scrollPane.setRowHeaderView( fixed );
        scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, fixed.getTableHeader());

        // Synchronize scrolling of the row header with the main table

        scrollPane.getRowHeader().addChangeListener( this );
    }

    /*
     *  Return the table being used in the row header
     */
    public JTable getFixedTable()
    {
        return fixed;
    }
    //
//  Implement the ChangeListener
//
    public void stateChanged(ChangeEvent e)
    {
        //  Sync the scroll pane scrollbar with the row header

        JViewport viewport = (JViewport) e.getSource();
        scrollPane.getVerticalScrollBar().setValue(viewport.getViewPosition().y);
    }
    //
//  Implement the PropertyChangeListener
//
    public void propertyChange(PropertyChangeEvent e)
    {
        //  Keep the fixed table in sync with the main table

        if ("selectionModel".equals(e.getPropertyName()))
        {
            fixed.setSelectionModel( main.getSelectionModel() );
        }

        if ("model".equals(e.getPropertyName()))
        {
            fixed.setModel( main.getModel() );
        }
    }
}