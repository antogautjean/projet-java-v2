package org.antogautjean;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class JListSimple extends JPanel
{
    // Le contenu de la JList
    String label[] = { "Mars","Vénus","Mercure","Jupiter","Saturne","Uranus","Six",
            "Neptune" };
    JList list;

    public JListSimple( ) {
        this.setLayout(new BorderLayout( ));
        list = new JList(label);
        // Ajouter la JList dans le JScrolPane
        JScrollPane pane = new JScrollPane(list);
        JButton btnPrint = new JButton("Afficher");
        btnPrint.addActionListener(new PrintListener( ));

        add(pane, BorderLayout.CENTER);
        add(btnPrint, BorderLayout.SOUTH);
    }

    public static void main(String s[]) {
        JFrame frame = new JFrame("Exemple de JList");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new JListSimple( ));
        frame.setSize(250, 200);
        frame.setVisible(true);
    }
    // Afficher le éléments sélectionnés de la JList
    class PrintListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selected[] = list.getSelectedIndices( );
            System.out.println("Selected Elements:  ");

            for (int i=0; i < selected.length; i++) {
                String element =
                        (String)list.getModel( ).getElementAt(selected[i]);
                System.out.println("  " + element);
                JOptionPane.showMessageDialog(null, element);
            }
        }
    }
}