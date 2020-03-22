package org.antogautjean;

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

        if (isUIVisible) {
            new HomeView();
            System.out.println("Runing with UI");
        } else {
            System.out.println("Runing with NO UI");
        }

        StockController koalaController = new StockController("koala");

        System.out.println("FileImporter : Reading CSV Stock file");
        FileImporter.fileToStock("./src/main/java/org/antogautjean/data/elements.csv",
                "./src/main/java/org/antogautjean/data/prix.csv", koalaController);
    }
}
