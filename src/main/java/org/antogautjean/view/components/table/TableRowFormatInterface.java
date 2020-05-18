package org.antogautjean.view.components.table;

import org.antogautjean.view.HomeView;

public interface TableRowFormatInterface {
    /**
     * Récupère un tableau d'objet pour les afficher ensuite dans un
     * DefaultTableModel
     * 
     * @param parentComponent pour pouvoir appeler des fonctions globales lors
     *                        d'intéractions avec les éléments du tableau retourné
     * @return tableau d'objets qui doivent êtres affichés
     */
    Object[][] getTableLineFormat(HomeView parentComponent);
}