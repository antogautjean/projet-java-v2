package org.antogautjean;

import org.antogautjean.Controller.FactoryController;
import org.antogautjean.Controller.StockController;
import org.antogautjean.model.FileImporter;
import org.antogautjean.view.HomeView;
import org.antogautjean.view.StockTableModel;

import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class App {

    public static void main(String[] args) throws IOException {
        // init depending of args
        boolean isUIVisible = true;
        for (String s : args) {
            if (s.equals("--no-ui")) {
                isUIVisible = false;
            }
        }
        System.out.println("starting : OK");

        System.out.println("FileImporter : Reading CSV Stock file");
        String dataPath = "./src/main/java/org/antogautjean/data/";
        // Stock controller
        StockController stockController = FileImporter.fileToStock(dataPath + "elements.csv", dataPath + "prix.csv");
        // Prod line controller
        FactoryController factory = FileImporter.fileToFactory(dataPath + "chaines.csv", stockController);


        final String[] columns = new String[] { "Code", "Nom", "Quantité actuelle", "Quantité à acheter",
                "Coût d'achat prévisionnel", "Nouvelle quantité après achat", "Quantité simulée après calcul" };

        final DefaultTableModel model = new StockTableModel(new Vector<>(), new Vector<>(Arrays.asList(columns)));

        if (isUIVisible) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Attention: setLookAndFeel ne fonctionne pas");
            }
            new HomeView(stockController, factory);
            System.out.println("Runing with UI");
        } else {
            System.out.println("Runing with NO UI");
        }
    }
}