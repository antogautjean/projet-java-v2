package org.antogautjean;

import org.antogautjean.Controller.FactoryController;
import org.antogautjean.Controller.StockController;
import org.antogautjean.model.FileImporter;
import org.antogautjean.view.HomeView;

import java.io.IOException;

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
        System.out.println(factory);

        if (isUIVisible) {
            new HomeView(stockController, factory);
            System.out.println("Runing with UI");
        } else {
            System.out.println("Runing with NO UI");
        }

    }
}
